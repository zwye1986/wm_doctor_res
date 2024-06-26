<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
<title>南方医科大学学员成绩查询</title>
<jsp:include page="/jsp/xjgl/htmlhead-xjgl.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script>
$(document).ready(function(){
	$("#s").text($("#zongShi").val());
	$("#f").text($("#zongFen").val());
});
</script>
<style>
   body{height:100%;text-align:center;}
   img{ max-width:100%;}
   h1,h2,h3,h4,h5,h6{font-size:1em;}
</style>
</head>

<body>
<div class="p_bd_bg">
<div class="yw">

 <div class="head">
   <div class="p_head_inner">
     <h1 class="p_logo">
                     南方医科大学学员成绩查询
     </h1>
   </div>
 </div>

 <div class="p_body">
   <div class="p_container">
      <div class="p_notice">
      	<c:if test="${empty sysuser.userFlow}">
      	    <div class="p_nmes">
      		<dl>
				<dt><img src="<s:url value='/'/>jsp/xjgl/images/tanhao.png"/></dt>
      		  <dd style="margin-top: 20px;">抱歉！没有找到您需要的信息！</dd>
      		</dl>
      		</div>
      	</c:if>
      	<c:if test="${!empty sysuser.userFlow}">
      	  <h1 class="p_tit"><span>姓&#12288;名：<label>${sysuser.userName}</label></span><span>学&#12288;号：<label>${eduuser.sid}</label></span></h1>
          <h1 class="p_tit"><span>总学时：<label id="s">${zongShi}</label></span><span>总学分：<label id="f">${zongFen}</label></span></h1>
			<h1 class="p_tit"><span>培养单位：${doctor.orgName}</span></h1>
			<table  class="grid" id="tempTable" style="width: 100%;">
			<tr>
				<th style="width: 20%;">课程编码及名称</th>
				<th style="width: 10%;">课程类别</th>
				<th style="width: 5%;">学时</th>
				<th style="width: 5%;">学分</th>
				<!-- 
				<th style="width: 10%;">修读方式</th>
				<th style="width: 10%;">考察方式</th>
				<th style="width: 10%;">平时成绩</th>
				<th style="width: 10%;">期末成绩</th>
				 -->
				<th style="width: 10%;">总成绩</th>
				<!-- 
				<th style="width: 10%;">获得学期</th>
				 -->
			</tr>
			<tbody id="appendTbody">
				<c:forEach items="${studentCourseMajor.courseExtsList}" var="studentCourseExt">
					<tr class="each">
							<input  type="hidden" name="currFlowSun" value=""/>
							<td style="text-align: left;padding-left: 10px;">
								<label>
									[${studentCourseExt.courseCode}]${studentCourseExt.courseName}
								</label>
							</td>
							<td><label class="courseTypeName">${studentCourseExt.courseTypeName}</label></td>
							<td><label class="coursePeriod">${studentCourseExt.coursePeriod}</label></td>
							<td><label class="courseCredit">${studentCourseExt.courseCredit}</label></td>
							<%-- <td>
								<label class="studyWayId">${studentCourseExt.studyWayName}</label>
							</td>
							<td>
								<label class="assessTypeId">${studentCourseExt.assessTypeName}</label>
							</td>
							<td>
								<label class="pacificGrade">${studentCourseExt.pacificGrade}</label>
							</td>
							<td>
								<label class="teamEndGrade">${studentCourseExt.teamEndGrade}</label>
							</td> --%>
							<td>
								<c:choose>
									<c:when test="${studentCourseExt.courseGrade==GlobalConstant.FLAG_Y }">
											合格
										</c:when>
										<c:when test="${studentCourseExt.courseGrade==GlobalConstant.FLAG_N}">
											不合格
										</c:when>
										<c:otherwise>
											${studentCourseExt.courseGrade}
										</c:otherwise>
								</c:choose>
							</td>
							<%-- <td style="width: 130px">
								<label>${studentCourseExt.gradeTermName }</label>
							</td> --%>
					</tr>
					<c:set var="zongShi" value="${zongShi+studentCourseExt.coursePeriod}"/>
					<c:set var="zongFen" value="${zongFen+studentCourseExt.courseCredit}"/>
				</c:forEach>
				<c:if test="${empty studentCourseMajor.courseExtsList}">
				<tr>
					<td colspan="6">
						无记录
					</td>
				</tr>
				</c:if>
				</tbody>
				<input type="hidden" id="zongShi" value="${zongShi}"/>
				<input type="hidden" id="zongFen" value="${zongFen}"/>
		</table>
		</c:if>
      </div>
   </div>
 </div>
</div>
</div>
 
 <div class="p_foot">
 技术支持：南京品德网络信息技术有限公司  <br/> 电话：025-68581986   68591968
 </div>

</body>
</html>
