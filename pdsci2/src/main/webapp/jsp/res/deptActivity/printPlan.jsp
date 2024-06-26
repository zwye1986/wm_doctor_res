<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="font" value="true"/>
</jsp:include>
	<link rel="stylesheet" type="text/css" href="<s:url value='/css/skin/${skinPath}/basic.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
	<style type="text/css">
		.xllist td{
			text-align: center;height: 35px;
		}
		.xllist{
			margin-top: 5px;
		}
		.A4 {
			page-break-before: auto;
			page-break-after: auto;
		}
	</style>
<script type="text/javascript">
	function printDiv() {
			var headstr = "<html><head><title></title><style type='text/css'>.xllist td{text-align: center;height: 35px;}" +
					"	.xllist{margin-top: 5px;}.A4 {page-break-before: always;page-break-after: always;	}</style>" +
					"</head><body>";
			var footstr = "</body>";
			var newstr = document.getElementById('infoDiv').innerHTML;
			var oldstr = document.body.innerHTML;
			document.body.innerHTML = headstr+newstr+footstr;
			window.print();
			document.body.innerHTML = oldstr;
		return false;
	}
</script>
</head>
<body>
<div class="button" style="text-align: right;">
			<input type="button" class="search" onclick="printDiv();" value="打&#12288;印"/>&#12288;
		</div>
<div id="infoDiv" class="search_table" style="padding-top: 5px;">
			<c:if test="${plan.planTypeId eq deptActivityTypeEnumDept.id}">
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 20px;">${dept.deptName}科室${plan.planDate}教学工作安排计划表</th>
					</tr>
					<c:if test="${not empty heads}" >
						<c:forEach items="${heads}" var="user" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<th rowspan="${fn:length(heads)}">教学主任</th>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty heads}" >

						<tr>
							<th>教学主任</th>
							<td>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty secretarys}" >
						<c:forEach items="${secretarys}" var="user" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<th rowspan="${fn:length(secretarys)}">教学秘书</th>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty secretarys}" >
						<tr>
							<th>教学秘书</th>
							<td>
							</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				</div>
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 15px;">教学查房安排（2周/次）带组主任负责</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th>内容</th>
						<th>地点</th>
					</tr>
					</tbody>
					<tbody id="${deptActivityItemTypeEnumJXCFAP.id}">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumJXCFAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
										${b.planTime}
								</td>
								<td>
										${userMap[b.joinUserFlow].userName}
								</td>
								<td>
										${userMap[b.joinUserFlow].userCode}
								</td>
								<td>${b.content}</td>
								<td>${b.address}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td colspan="5">未添加安排</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				</div>
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 15px;">病例讨论安排（2周/次）高级职称主持</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th>内容</th>
						<th>地点</th>
					</tr>
					</tbody>
					<tbody id="${deptActivityItemTypeEnumBLTLAP.id}">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumBLTLAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
										${b.planTime}
								</td>
								<td>
										${userMap[b.joinUserFlow].userName}
								</td>
								<td>
										${userMap[b.joinUserFlow].userCode}
								</td>
								<td>${b.content}</td>
								<td>${b.address}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td colspan="5">未添加安排</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				</div>
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 15px;">小讲课安排（2周/次）主治/住院医师负责</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th>内容</th>
						<th>地点</th>
					</tr>
					</tbody>
					<tbody id="${deptActivityItemTypeEnumXJKAP.id}">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumXJKAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
										${b.planTime}
								</td>
								<td>
										${userMap[b.joinUserFlow].userName}
								</td>
								<td>
										${userMap[b.joinUserFlow].userCode}
								</td>
								<td>${b.content}</td>
								<td>${b.address}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td colspan="5">未添加安排</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				</div>
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 15px;">其他活动</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th>内容</th>
						<th>地点</th>
					</tr>
					</tbody>
					<tbody id="${deptActivityItemTypeEnumQTHD.id}">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumQTHD.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
										${b.planTime}
								</td>
								<td>
										${userMap[b.joinUserFlow].userName}
								</td>
								<td>
										${userMap[b.joinUserFlow].userCode}
								</td>
								<td>${b.content}</td>
								<td>${b.address}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td colspan="5">未添加安排</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				</div>
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 15px;">读书报告会安排（1月/次）</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th>内容</th>
						<th>地点</th>
					</tr>
					</tbody>
					<tbody id="${deptActivityItemTypeEnumDSBGHAP.id}">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumDSBGHAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
										${b.planTime}
								</td>
								<td>
										${userMap[b.joinUserFlow].userName}
								</td>
								<td>
										${userMap[b.joinUserFlow].userCode}
								</td>
								<td>${b.content}</td>
								<td>${b.address}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td colspan="5">未添加安排</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				</div>
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 15px;">出科考核安排（加地点）</th>
					</tr>
					</tbody>
					<tbody id="${deptActivityItemTypeEnumCKKHAP.id}">
					<tr>
						<th style="width: 150px;">理论考（系统考就无此项）</th>
						<td style="width: 120px;">${plan.theoryExamTime}</td>
						<th style="width: 120px;">负责人</th>
						<td style="width: 120px;">${examInfoMap[deptActivityUserTypeEnumCharge.id][0].examUserName}</td>
						<td style="width: 120px;">15人以上2人监考</td>
					</tr>
					<c:set var="list" value="${examInfoMap[deptActivityUserTypeEnumInvigilator.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<td style="width: 150px;" rowspan="${fn:length(list)}">监考人</td>
									<td colspan="4">
											${b.examUserName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td colspan="4">${b.examUserName}</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td style="width: 150px;">监考人</td>
							<td colspan="4">
								未安排
							</td>
						</tr>
					</c:if>
					<tr>
						<th style="width: 150px;">操作考</th>
						<td style="width: 120px;">${plan.skillExamTime}</td>
						<th style="width: 120px;">组长</th>
						<td colspan="2">${examInfoMap[deptActivityUserTypeEnumGroupLeader.id][0].examUserName}</td>
					</tr>
					<c:set var="list" value="${examInfoMap[deptActivityUserTypeEnumMember.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<td rowspan="${fn:length(list)}">考核组成员</td>
									<td colspan="4">
											${b.examUserName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td colspan="4">${b.examUserName}</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td>考核组成员</td>
							<td colspan="4">
								未安排
							</td>
						</tr>
					</c:if>
					<tr>
						<th>备注</th>
						<td colspan="4">${plan.planDemo}</td>
					</tr>
					</tbody>
				</table>
				</div>
			</c:if>
			<c:if test="${plan.planTypeId eq deptActivityTypeEnumScientific.id}">
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 20px;">${dept.deptName}教研室${plan.planDate}教学工作安排计划表</th>
					</tr>
					<c:if test="${not empty heads}" >
						<c:forEach items="${heads}" var="user" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<th rowspan="${fn:length(heads)}">教学主任</th>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty heads}" >

						<tr>
							<th>教学主任</th>
							<td>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty secretarys}" >
						<c:forEach items="${secretarys}" var="user" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<th rowspan="${fn:length(secretarys)}">教学秘书</th>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty secretarys}" >
						<tr>
							<th>教学秘书</th>
							<td>
							</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				</div>
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 15px;">集体备课安排（月/次）</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th colspan="2">内容</th>
					</tr>
					</tbody>
					<tbody id="${deptActivityItemTypeEnumJTBKAP.id}">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumJTBKAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
										${b.planTime}
								</td>
								<td>
										${userMap[b.joinUserFlow].userName}
								</td>
								<td>
										${userMap[b.joinUserFlow].userCode}
								</td>
								<td colspan="2">${b.content}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td colspan="5">未添加安排</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				</div>
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 15px;">听课安排（月/次）</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>听课人</th>
						<th>工号</th>
						<th>主讲人</th>
						<th>授课题目</th>
					</tr>
					</tbody>
					<tbody id="${deptActivityItemTypeEnumTKAP.id}">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumTKAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
										${b.planTime}
								</td>
								<td>
										${userMap[b.joinUserFlow].userName}
								</td>
								<td>
										${userMap[b.joinUserFlow].userCode}
								</td>
								<td>${b.content}</td>
								<td>${b.title}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td colspan="5">未添加安排</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				</div>
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="3" style="text-align: center;font-size: 15px;">督导安排（月/次）教学秘书和主任各一次，交叉教研室</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>督导内容</th>
						<th>督导人</th>
					</tr>
					</tbody>
					<tbody id="${deptActivityItemTypeEnumDDAP.id}">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumDDAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
										${b.planTime}
								</td>
								<td>${b.content}</td>
								<td>
										${userMap[b.joinUserFlow].userName}
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td colspan="3">未添加安排</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				</div>
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="3" style="text-align: center;font-size: 15px;">其他活动</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
					</tr>
					</tbody>
					<tbody id="${deptActivityItemTypeEnumSQTHD.id}">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumSQTHD.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
										${b.planTime}
								</td>
								<td>
										${userMap[b.joinUserFlow].userName}
								</td>
								<td>
										${userMap[b.joinUserFlow].userCode}
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td colspan="3">未添加安排</td>
						</tr>
					</c:if>
					</tbody>
				</table>
				</div>
				<div class="A4">
				<table class="xllist A4">
					<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 15px;">教研室考核安排</th>
					</tr>
					</tbody>
					<tbody id="${deptActivityItemTypeEnumCKKHAP.id}">
					<tr>
						<th style="width: 150px;">理论考（系统考就无此项）</th>
						<td style="width: 120px;">${plan.theoryExamTime}</td>
						<th style="width: 120px;">负责人</th>
						<td style="width: 120px;">${examInfoMap[deptActivityUserTypeEnumCharge.id][0].examUserName}</td>
						<td style="width: 120px;">15人以上2人监考</td>
					</tr>
					<c:set var="list" value="${examInfoMap[deptActivityUserTypeEnumInvigilator.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<td style="width: 150px;" rowspan="${fn:length(list)}">监考人</td>
									<td colspan="4">
											${b.examUserName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td colspan="4">${b.examUserName}</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td style="width: 150px;">监考人</td>
							<td colspan="4">
								未安排
							</td>
						</tr>
					</c:if>
					<tr>
						<th style="width: 150px;">操作考</th>
						<td style="width: 120px;">${plan.skillExamTime}</td>
						<th style="width: 120px;">组长</th>
						<td colspan="2">${examInfoMap[deptActivityUserTypeEnumGroupLeader.id][0].examUserName}</td>
					</tr>
					<c:set var="list" value="${examInfoMap[deptActivityUserTypeEnumMember.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<td style="width: 150px;" rowspan="${fn:length(list)}">考核组成员</td>
									<td colspan="4">
											${b.examUserName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td colspan="4">${b.examUserName}</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty list}" >
						<tr>
							<td style="width: 150px;">考核组成员</td>
							<td colspan="4">
								未安排
							</td>
						</tr>
					</c:if>
					<tr>
						<th>备注</th>
						<td colspan="4">${plan.planDemo}</td>
					</tr>
					</tbody>
				</table>
				</div>
			</c:if>
		</div>
</body>
</html>