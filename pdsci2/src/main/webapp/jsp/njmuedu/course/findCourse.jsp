<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${applicationScope.sysCfgMap['study_platform'] eq 'szslyy'?'苏州市住院医师公共科目学习平台':'南京医科大学研究生在线课程学习平台'}-发现课程</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="findCourse" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
</jsp:include>
	
<script type="text/javascript">

$(document).ready(function(){
	loadCourse();
});
function changeCourseName(obj){
	$("#courseName").val($(obj).val());
	
}
function changeSort(sortVal){
	var orderBy=$("#sort");
	orderBy.val(sortVal);
	loadCourse();
	
}
function changeMajor(majorId){
	$("#courseMajorId").val(majorId);
	loadCourse();
}
function loadCourse(){
	var form=$("#searchForm");
	var url="<s:url value ='/njmuedu/course/showCourse'/>";
	var requestData = form.serialize();
	jboxPost(url,requestData,function(resp){
		$("#content").html("");
		$("#content").html(resp);
	},null,false);
}
function checkRole(courseFlow){
	var url="<s:url value ='/njmuedu/user/checkRole'/>";
	jboxPost(url,null,function(resp){
		if(resp=='${GlobalConstant.STUDENT_ROLE}'){
			url="<s:url value ='/njmuedu/course/stuCourseDetail'/>"+"/"+courseFlow;
			window.location.href=url;
		}else if(resp!=''){
			url="<s:url value ='/njmuedu/course/courseDetail'/>"+"/"+courseFlow;
			window.location.href=url;
		}else{
			jboxInfo("您的权限不明，请联系管理员！");
		}
	},null,false);
}
</script>
</head>
<body style="background:#f4f4f4;">
<jsp:include page="/jsp/njmuedu/include/top.jsp" flush="true"/>
<!--content-->
<div class="content cbody clearfix">
   <div class="menu-warp fl">
    <ul class="menu">
      <li class="level1"><a href="#" class="cur">发现课程</a>
        <ul class="level2" style="display:block;">
           <li onclick="changeMajor('');"><a <c:if test="${empty param.courseMajorId }">class="hove1"</c:if> href="javascript:void(0);">全部</a></li>
         <c:forEach items="${dictTypeEnumNjmuCourseMajorList }" var="dict">
           <li onclick="changeMajor('${dict.dictId }');"><a id="${dict.dictId }" href="javascript:void(0);" <c:if test="${dict.dictId eq param.courseMajorId }">class="hove1"</c:if>>${dict.dictName }</a></li>
        </c:forEach>
      </ul>
     </ul>
   </div>
   <!--right-->
    <div class="right fr">
        <!--发现课程-->
    	<div class="wrap">
    	 <form id="searchForm">
    	  <input type="hidden" id="sort" name="sort" />
    	  <input type="hidden" id="courseName" name="courseName"/>
    	  <input type="hidden" id="courseMajorId" name="courseMajorId" value="${param.courseMajorId }"/>
    	 </form>
        	<div class="courseBg" id="content">
           <!-- 此处填充课程信息 -->
            </div>
         </div>
    
    </div><!--right-->
</div>
<!--/content-->
<jsp:include page="/jsp/njmuedu/include/foot.jsp" flush="true"/>
</body>
</html>