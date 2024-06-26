<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="findCourse" value="true"/>
</jsp:include>
<script type="text/javascript">
//var height=(window.screen.height)*0.6;
//var width=(window.screen.width)*0.6;
	function lookUser(userFlow){
		var url = "<s:url value='/njmuedu/user/studentInfo?userFlow='/>"+ userFlow;
		jboxOpen(url, "学生信息", 900, 450);
	}
	
	$(function(){
		$("#condition").keyup(function(){   
			  if(event.keyCode == 13){
				  submitForm("<s:url value='/njmuedu/user/userList'/>");
			  }
		  });
		
	});
</script>
<body>
<div class="registerMsg-m2 fl">
   <div class="registerMsg-m-inner registerBgw">
      <div class="registerMsg-tabs">
		  <c:set var="szFlag" value="${applicationScope.sysCfgMap['study_platform'] eq 'szslyy'}"/>
    <form id="searchForm"  method="post" onsubmit="return false;">
    <input id="currentPage" type="hidden" name="currentPage"  value=""/>
    <input id="isAdmin" type="hidden" name="isAdmin"  value="${param.isAdmin}"/>
    <input type="hidden" id="url" value="<s:url value='/njmuedu/user/userList'/>">
   	<div class="module-tabs">
   	  <ul class="fl type">
		<li class="on">学生信息</li>
	 </ul>
      <div class="title-r fr">
      	<div class="searchBox fl">
      	   <input type="text"  placeholder="请输入${szFlag?'基地':'学校'}名称/学生姓名" name="condition" id="condition" value="${param.condition}" />
      	   <img onclick="submitForm('<s:url value='/njmuedu/user/userList'/>');" src="<s:url value='/jsp/njmuedu/css/images/search1.png'/>" style="cursor: pointer;" title="搜索"/> 
      	</div>
		<c:if test="${isAdmin eq 'Y' && sysCfgMap['njmuedu_add_stu']==GlobalConstant.FLAG_Y}">
			<div class="module-tabs fl">
				<ul>
					<li><a  href="javascript:void(0);" onclick="addStudent();" class="add"><img src="<s:url value='/jsp/njmuedu/css/images/add.png'/>" />新增</a></li>
				</ul>
			</div>
		</c:if>
          <%-- <div class="tabs-title  module-tabs fl">
          	<ul>
              	<li <c:if test="${empty param.statusId}">class="on"</c:if>  onclick="changeStatusId('','<s:url value='/njmuedu/user/userList'/>');"><a href="javascript:void(0);" >全部</a></li>
                  <li <c:if test="${param.statusId==userStatusEnumReged.id }">class="on"</c:if> onclick="changeStatusId('${userStatusEnumReged.id }','<s:url value='/njmuedu/user/userList'/>');"><a href="javascript:void(0);" >${userStatusEnumReged.name }</a></li>
              </ul>
              <input type="hidden" name="statusId" id="statusId" />
          </div> --%>
      </div>
    </div>
	<div style="margin-top: 10px;"> 
	<table border="0" cellpadding="0" cellspacing="0" class="course-table">
		<tr>
			<th width="9%">姓名</th>
			<th width="11%">${szFlag?'基地':'学校'}</th>
			<th width="12%">专业</th>
			<th width="7%">届别</th>
			<th width="11%">学号</th>
			<th width="16%">身份证</th>
			<th width="18%">学分</th>
			<th width="8%">状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${userList }" var="userExt">
		<tr>
			<td>${userExt.sysUser.userName }</td>
			<td>${userExt.sysUser.orgName }</td>
			<td>${userExt.majorName }</td>
			<td>${userExt.period }</td>
			<td>${userExt.sid }</td>
			<td>${userExt.sysUser.idNo }</td>
			<td>${userExt.credit}</td>
			<td>${userExt.sysUser.statusDesc}</td>
			<td>
				<c:if test="${userExt.sysUser.statusId==userStatusEnumReged.id}">[<a href="javascript:checkUser('${userExt.userFlow }')">审核</a>]</c:if>
				[<a href="javascript:lookUser('${userExt.userFlow }')">查看</a>]
				<c:if test="${isAdmin eq 'Y' and sysCfgMap['njmuedu_add_stu'] eq GlobalConstant.FLAG_Y}">
			     [<a href="javascript:editStudent('${userExt.userFlow }')">编辑</a>]
				</c:if>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${empty userList}">
		<tr style="background: none;"><td colspan="9" style="border:none;"><br><br><img src="<s:url value='/jsp/njmuedu/css/images/tanhao.png'/>"/></td></tr>
		<tr><td colspan="9" style="border:none;">暂无记录！</td></tr>
		</c:if>
	</table>
	<div></div>
	</div>
	</form>
	<c:if test="${not empty userList}">
	      <div class="pages text-center">
      <div class="pagination">
    <ul class="pagination">
      <c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"></c:set>
	   <pd:pagination-njmuedu toPage="toPage"/>	   
    </ul>
  </div>
  </div> 
   </c:if>    
</div>
  
          </div>
        </div>
    
</body>