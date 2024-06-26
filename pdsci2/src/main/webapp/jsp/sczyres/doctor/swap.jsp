<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script>
function getCatSpe(){
	var orgFlow = $("#orgFlow").val();
	if(orgFlow){
		var url = "<s:url value='/sczyres/doctor/findcatspe'/>?orgFlow="+orgFlow;
		jboxGet(url , null , function(resp){
			$("#catSpeId").empty();
			$("#catSpeId").append("<option value=''>请选择</option>");
			$.each(resp , function(i , n){
				$("#catSpeId").append("<option value='"+n.speId+"'>"+n.speName+"</option>");
			});
		} , null , false);
	}
	$("#speId").empty();
	$("#speId").append("<option value=''>请选择</option>");
}

function getSpe(){
	var catSpeId = $("#catSpeId").val();
	if(catSpeId){
		var url = "<s:url value='/sczyres/doctor/findspe'/>?catSpeId="+catSpeId;
		jboxGet(url , null , function(resp){
			$("#speId").empty();
			$("#speId").append("<option value=''>请选择</option>");
			$.each(resp , function(i , n){
				$("#speId").append("<option value='"+n.dictId+"'>"+n.dictName+"</option>");
			});
		} , null , false);
	}
	
}

function swapRecruit(){
	if($("#orgFlow").val()==""){
		jboxTip("请选择培训基地!");
		return;
	}
	if($("#catSpeId").val()==""){
		jboxTip("请选择培训专业!");
		return;
	}
	if($("#speId").val()==""){
		jboxTip("请选择第二阶段意向专业!");
		return;
	}
	jboxConfirm("确认提交报名信息,提交后数据将无法修改?",function(){
		//设置catspename spename
		$("#catSpeName").val($("#catSpeId :selected").text());
		$("#speName").val($("#speId :selected").text());
		jboxPost("<s:url value='/sczyres/doctor/saveRecruit'/>" , $("#recruitForm").serialize() , function(resp){
			if(resp=="1"){
				jboxTip("提交成功");
				setTimeout(function(){
					window.location.href="<s:url value='/sczyres/doctor/home'/>";
				},1000);
				
			}else{
				jboxTip(resp);
			}
		} , null , false);
	});
}
</script>
<div class="col_main">
<div class="main_hd"><h2 class="underline">志愿调剂</h2></div>
<form id="recruitForm" style="position:relative;">
   <div class="div_table">
   <input type="hidden" name="recruitFlow" value="${recruit.recruitFlow}"/>
   <table class="base_info">
     <tr>
       <th><font color="red">*</font>培训基地：</th>
       <td>   
         <c:choose>
       	 	<c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
       	 		${recruit.orgName }
       	 	</c:when>
       	 	<c:otherwise>
       	 		 <select class="select" id="orgFlow" name="orgFlow"  onchange="getCatSpe();">
		             <option value="">请选择</option>
		             <c:forEach items="${hospitals}" var="hosptial">
		                <option value='${hosptial.orgFlow}' <c:if test='${recruit.orgFlow eq hosptial.orgFlow}'>selected="selected"</c:if>>${hosptial.orgName}</option>
		             </c:forEach>      
          		</select>
       	 	</c:otherwise>
       	 </c:choose>   
       </td>
     </tr>
     <tr>
       <th><font color="red">*</font>培训专业：</th>
       <td>   
          <c:choose>
       		<c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
       			${recruit.catSpeName}
       		</c:when>
      		<c:otherwise>
      			 <select class="select" id="catSpeId" name="catSpeId" style="width: 150px;" onchange="getSpe();">
		            <option value="">请选择</option>
		            <c:forEach items="${catspes}" var="catspe">
		               <option value="${catspe.speId}" <c:if test='${recruit.catSpeId eq catspe.speId}'>selected="selected"</c:if>>${catspe.speName}</option>
		            </c:forEach>
		          </select>
         		 <input type="hidden" id='catSpeName' name="catSpeName" value='${recruit.catSpeName}'/>
      		</c:otherwise>
       	</c:choose>
       </td>
     </tr>
     <tr>
       <th><font color="red">*</font>第二阶段意向专业：</th>
       <td>   
          <c:choose>
	       		<c:when test="${doctor.doctorStatusId==regStatusEnumPassing.id }">
	       			${recruit.speName}
	       		</c:when>
	       		<c:otherwise>
	       			 	<select class="select" id="speId"  style="width: 150px;" name="speId">
			            <option value="">请选择</option>
			            <c:forEach items="${spes}" var="spe">
			               <option value="${spe.dictId}" <c:if test='${recruit.speId eq spe.dictId}'>selected="selected"</c:if>>${spe.dictName}</option>
			            </c:forEach>
			          </select>
			          <input type="hidden" id="speName" name="speName" value='${recruit.speName}'/>
	       		</c:otherwise>
       		</c:choose>
       </td>
     </tr>
   </table>
  </div>
</form>
   <div class="btn_info">
       <a class="btn" onclick="swapRecruit();">提&nbsp;&nbsp;交</a>
   </div>
</div>