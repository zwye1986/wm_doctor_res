<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/teacher/Style.css'/>"></link>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
	</jsp:include>

	<script type="text/javascript">
		function scroll(){
			setTimeout(function(){
				$(".list li:first").animate({marginTop : "-25px"},500,function(){
					$(".list").append($(this).css({marginTop : "0px"}));
					scroll();
				});
			},3000);
		}
		$(function(){
			if($(".list li").length>1){
				scroll();
			}


		});

		function changeDept(deptFlow){
			location.href = "<s:url value='/res/teacher/showView/${roleFlag}'/>?schDeptFlow="+deptFlow;
		}

		function showView(){
			$("#searchForm").submit();
		}

		function toPage(page) {
			if (page) {
				$("#currentPage").val(page);
			}
			showView();
		}


		function defaultImg(img){
			img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
		}

		//评分
		function grade(doctorFlow,recTypeName,recTypeId,recFlow,processFlow,resultFlow,schDeptFlow){
			jboxOpen("<s:url value='/res/teacher/grade'/>?roleFlag=${roleFlag}&recTypeId="+recTypeId+"&recFlow="+recFlow+"&processFlow="+processFlow+"&doctorFlow="+doctorFlow+
                    "&resultFlow="+resultFlow+"&schDeptFlow="+schDeptFlow,
					recTypeName,900,500);
		}

	</script>
</head>
<body>
<form id="doctorForm" action="<s:url value='/res/teacher/auditListContent'/>" method="post">
	<input id="doctorName" type="hidden" name="doctorName" />
	<input type="hidden" name="roleFlag" value="${roleFlag}" />
</form>
<div class="mainright">
	<div class="content" >
		<div>
			<table class="" style="margin-top: 10px; width:100%;">
				<tr>
					<td width="40px"> 最新通知：</td>
					<td width="500px">
						<div class="scroll">
							<ul class="list">
								<c:forEach items="${infos}" var="info">
									<li><a href="<s:url value='/res/platform/noticeView'/>?infoFlow=${info.infoFlow}"
										   target="_blank">${info.infoTitle}</a> <img
											src="<s:url value='/css/skin/new.png'/>"/></li>
								</c:forEach>
								<c:if test="${empty infos}">
									<li>暂无最新通知!</li>
								</c:if>
							</ul>
						</div>
					</td>
					<td width="40px">
						<span style="text-align:center;"> <a style="cursor:pointer;color: #2f8cef" href="<s:url value='/res/doc/noticeList'/>?fromSch=true&isView=true&roleFlag=${roleFlag}">>>查看全部</a></span>
					</td>
				</tr>
			</table>

			<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
				<form id="searchForm" action="<s:url value='/res/teacher/evaluate/${roleFlag}'/>" method="post">
					<input type="hidden" name="currentPage" id="currentPage">
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">姓&#12288;&#12288;名：</label>
							<input type="text" class="qtext" name="doctorName" value="${param.doctorName}"  <%--onchange="showView();"--%>>
						</div>
						<div class="qcheckboxDiv">
							&#12288;<label class="qlable">
							<input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""}<%-- onchange="showView();"--%>>
							轮转中学员
						</label>
							<input type="button" value="查&#12288;询" class="searchInput" onclick="showView();"/>
						</div>

					</div>
				</form>
			</c:if>
		</div>


		<div style="padding-top: 5px;">

			<table class="xllist" style="/*margin-top: 10px;*/ width:100%;">
				<tr>
					<th style="width: 10%;">姓名</th>
					<th style="width: 5%;">性别</th>
					<th style="width: 10%;">手机</th>
					<th style="width: 10%;">入院时间</th>
					<th style="width: 12%;">人员类型</th>
					<th style="width: 15%;">轮转科室</th>
					<th style="width: 18%;">计划轮转时间</th>
					<th style="width: 10%;">入科时间</th>
					<th style="width: 5%;">状态</th>
					<th style="width: 5%;">操作</th>

				</tr>
				<%-- 			<c:forEach items="${doctorList}" var="doctor"> --%>
				<c:forEach items="${processList}" var="process">
					<c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
					<tr>
						<td onclick="edit('${doctor.doctorFlow}');" title="<img src='${sysCfgMap['upload_base_url']}/${userMap[doctor.doctorFlow].userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>"><a style="cursor:pointer;color: #2f8cef">${doctor.doctorName}</a></td>
						<td>${userMap[doctor.doctorFlow].sexName}</td>
						<td>${userMap[doctor.doctorFlow].userPhone}</td>
						<td>${doctor.inHosDate}</td>
						<td>${doctor.doctorCategoryName}</td>
						<td>${process.schDeptName}</td>
						<td>${process.schStartDate} ~ ${process.schEndDate}</td>
						<td>${process.startDate}</td>
						<!-- 					<td> -->
							<%-- 						${finishPreMap[doctor.doctorFlow]+0}% --%>
							<%-- 						<c:if test="${(waitAuditMap[doctor.doctorFlow]+waitAuditAppealMap[doctor.doctorFlow])+0>0 && roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}"> --%>
							<%-- 							<img title="去审核" src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>" style="margin-top:-5px;cursor: pointer;" onclick="goAudit('${doctor.doctorName}');"/> --%>
							<%-- 						</c:if> --%>
						<!-- 					</td> -->

						<td>
							<c:set var="key" value="${process.processFlow}"/>
							<c:if test="${process.schFlag eq GlobalConstant.FLAG_Y}">
								已出科
							</c:if>
							<c:if test="${!(process.schFlag eq GlobalConstant.FLAG_Y)}">
								<c:if test="${process.isCurrentFlag eq GlobalConstant.FLAG_Y}">
									<c:if test="${!empty afterMap[key] || !empty recMap[key]}">
										待出科
									</c:if>
									<c:if test="${!(!empty afterMap[key] || !empty recMap[key])}">
										轮转中
									</c:if>
								</c:if>
								<c:if test="${process.isCurrentFlag ne GlobalConstant.FLAG_Y}">
									待入科
								</c:if>
							</c:if>
						</td>
						<td>

							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
							<c:set var="rec" value="${requestScope[resRecTypeEnumTeacherDoctorGrade.id]}"/>
							<a style="cursor: pointer;color: #2f8cef;" onclick="grade('${doctor.doctorFlow}','${resRecTypeEnumTeacherDoctorGrade.name}','${resRecTypeEnumTeacherDoctorGrade.id}','${rec.recFlow}','${process.processFlow}','${process.schResultFlow}'
                                    ,'${process.schDeptFlow}');">
								评价
							</a>
							</c:if>

							<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">
								<c:set var="rec" value="${requestScope[resRecTypeEnumHeadDoctorGrade.id]}"/>
								<a style="cursor: pointer;color: #2f8cef;" onclick="grade('${doctor.doctorFlow}','${resRecTypeEnumHeadDoctorGrade.name}','${resRecTypeEnumHeadDoctorGrade.id}','${rec.recFlow}','${process.processFlow}','${process.schResultFlow}'
                                        ,'${process.schDeptFlow}');">
									评价
								</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>

				<c:if test="${empty processList}">
					<tr><td colspan="99">没有医师在当前科室轮转！</td></tr>
				</c:if>
			</table>
		</div>
		<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_TEACHER}">
			<p>
				<c:set var="pageView" value="${pdfn:getPageView(processList)}" scope="request"/>
				<pd:pagination toPage="toPage"/>
			</p>
		</c:if>
		<%--<c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_HEAD}">--%>
			<%--<!-- 		<table class="basic" style="margin-top: 10px; width:100%;"> -->--%>
			<%--<!-- 			<tr><th style="text-align:left;padding-left: 10px; font-size:14px; color:#fea527;font-weight:normal;"> 带教老师审核情况</th></tr> -->--%>
			<%--<!-- 			<tr><td style="padding-left:0;"> -->--%>
			<%--<!-- 			 <div style="margin:0 1%;"> -->--%>
			<%--&lt;%&ndash; 			 <c:forEach items="${teacherFlows}" var="teacherFlow"> &ndash;%&gt;--%>
			<%--<!-- 				  <div class="card_tec"> -->--%>
			<%--<!-- 				    <span class="card_inner"> -->--%>
			<%--&lt;%&ndash; 				    <h1><c:out value="${auditMap[teacherFlow].teacherName}" default="${notAuditMap[teacherFlow].teacherName}"/><span class="card_score">评分：6分</span></h1> &ndash;%&gt;--%>
			<%--&lt;%&ndash; 				    <h2>已审核：${auditMap[teacherFlow].auditCount}&nbsp;&nbsp;&nbsp;未审核：${notAuditMap[teacherFlow].auditCount}</h2> &ndash;%&gt;--%>
			<%--<!-- 				    </span> -->--%>
			<%--<!-- 				  </div> -->--%>
			<%--&lt;%&ndash; 			 </c:forEach> &ndash;%&gt;--%>
			<%--&lt;%&ndash; 			 <c:if test="${empty teacherFlows}"> &ndash;%&gt;--%>
			<%--<!-- 			 	暂无带教老师审核信息! -->--%>
			<%--&lt;%&ndash; 			 </c:if> &ndash;%&gt;--%>
			<%--<!-- 			  </div> -->--%>
			<%--<!-- 			</td></tr> -->--%>
			<%--<!-- 		</table> -->--%>

			<%--&lt;%&ndash; 		<c:if test="${!empty willInResult}"> &ndash;%&gt;--%>

			<%--<table class="xllist" style="margin-top: 10px; width:100%;">--%>
				<%--<tr>--%>
					<%--<th colspan="99" style="text-align:left;padding-left: 10px; font-size:14px;font-weight:normal;font-weight: bold;">--%>
						<%--待入科学员信息--%>
							<%--&lt;%&ndash;<font style="float: right;margin-left: 10px;font-weight: normal;">Tip：<font color="red">红色</font>表示已超过入科时间却未入科的学员！不可操作入科的学员为未出科的学员！</font>&ndash;%&gt;--%>
					<%--</th>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<th style="width: 10%;">姓名</th>--%>
					<%--<th style="width: 5%;">性别</th>--%>
					<%--<th style="width: 10%;">手机</th>--%>
					<%--<th style="width: 15%;">入院时间</th>--%>
					<%--<th style="width: 10%;">人员类型</th>--%>
					<%--<th style="width: 15%;">轮转科室</th>--%>
					<%--<th style="width: 15%;">计划轮转时间</th>--%>
					<%--<th style="width: 15%;">备注</th>--%>
					<%--<th style="width: 5%;">操作</th>--%>
				<%--</tr>--%>
				<%--<c:set var="viewDataCount" value="0"/>--%>
				<%--<c:forEach items="${willInResult}" var="result">--%>
					<%--<c:set var="currDate" value="${pdfn:getCurrDate()}"/>--%>
					<%--<c:set var="overDay" value="${pdfn:signDaysBetweenTowDate(currDate,result.schStartDate)}"/>--%>
					<%--<c:if test="${overDay>=-30 and not empty result.schStartDate}">--%>
						<%--<c:set var="viewDataCount" value="${viewDataCount+1}"/>--%>
						<%--<c:set var="willInUser" value="${willInUserMap[result.doctorFlow]}"/>--%>
						<%--<c:set var="willInDoctor" value="${willInDoctorMap[result.doctorFlow]}"/>--%>
						<%--&lt;%&ndash;<c:set var="isRotationProcess" value="${isRotationProcessMap[result.doctorFlow]}"/>&ndash;%&gt;--%>
						<%--&lt;%&ndash;<c:set var="canIn" value="${empty isRotationProcess}"/>&ndash;%&gt;--%>
						<%--<tr class="${!canIn?'cannotIn':''}">--%>
							<%--<td onclick="edit('${willInDoctor.doctorFlow}');" title="<img src='${sysCfgMap['upload_base_url']}/${willInUser.userHeadImg}' onerror='defaultImg(this);' style='width: 110px;height: 130px;'/>" style="cursor:pointer;"><a style="<c:if test="${overDay>0}">color:#2f8cef;</c:if>">${willInDoctor.doctorName}</a></td>--%>
							<%--<td>${willInUser.sexName}</td>--%>
							<%--<td>${willInUser.userPhone}</td>--%>
							<%--<td>${willInDoctor.inHosDate}</td>--%>
							<%--<td>${willInDoctor.doctorCategoryName}</td>--%>
							<%--<td>${result.schDeptName}</td>--%>
							<%--<td>${result.schStartDate} ~ ${result.schEndDate}</td>--%>
							<%--<td>--%>
								<%--<c:if test="${overDay>0}">--%>
									<%--已超过入科日期${overDay}天--%>
								<%--</c:if>--%>
								<%--<c:if test="${!(overDay>0)}">--%>
									<%--还有${-overDay}天入科--%>
								<%--</c:if>--%>
							<%--</td>--%>
							<%--<td>--%>
									<%--&lt;%&ndash;<c:if test="${canIn}">&ndash;%&gt;--%>
									<%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
								<%--<a onclick="openChoose('${result.resultFlow}','${result.schDeptFlow}');" style="color: #2f8cef;cursor: pointer;">--%>
									<%--入科--%>
								<%--</a>--%>
							<%--</td>--%>
						<%--</tr>--%>
					<%--</c:if>--%>
				<%--</c:forEach>--%>
				<%--<c:if test="${empty willInResult || viewDataCount==0}">--%>
					<%--<tr><td colspan="99" style="padding-left:10px;"> 暂无待入科学员信息!</td></tr>--%>
				<%--</c:if>--%>
				<%--<tbody id="cannotOper">--%>

				<%--</tbody>--%>
			<%--</table>--%>
			<%--&lt;%&ndash; 		</c:if> &ndash;%&gt;--%>
		<%--</c:if>--%>
		<%--<script type="text/javascript">--%>
			<%--//拖动demo--%>
			<%--// 			function moveE(d){--%>
			<%--// 				var e = window.event;--%>
			<%--// 				$(d).css({--%>
			<%--// 					position:"absolute",--%>
			<%--// 					top:$(d).offset().top+"px",--%>
			<%--// 					left:$(d).offset().left+"px",--%>
			<%--// 					zIndex:10000--%>
			<%--// 				}).mouseup(function(){--%>
			<%--// 					$(document).off("mousemove");--%>
			<%--// 				});--%>
			<%--// 				var topM = $(d).offset().top-e.pageY;--%>
			<%--// 				var leftM = $(d).offset().left-e.pageX;--%>
			<%--// 				$(document).mousemove(function(ev){--%>
			<%--// 					$(d).css({--%>
			<%--// 						top:ev.pageY+topM+"px",--%>
			<%--// 						left:ev.pageX+leftM+"px"--%>
			<%--// 					});--%>
			<%--// 				});--%>
			<%--// 			}--%>
		<%--</script>--%>
		<%--<!-- 		<div style="width: 100px;height: 100px;background: red;" onmousedown="moveE(this);"></div> -->--%>
	<%--</div>--%>
</div>
<script type="text/javascript">
	$(function(){
		$("#cannotOper").append($(".cannotIn"));
	});
</script>
</body>
</html>