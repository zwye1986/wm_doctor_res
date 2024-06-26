<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
</jsp:include>
<style type="text/css">
	.xllist td{
		text-align: center;height: 35px;
	}
	.xllist{
		margin-top: 10px;
	}
	img{
		padding-right: 10px;
	}
</style>
<script type="text/javascript">
function exportItemEval(evalFlow)
{
	var url = "<s:url value='/res/deptActivityStatistics/exportItemEval'/>?itemFlow=${item.itemFlow}&evalFlow="+evalFlow ;
	jboxExpLoading(null,url);
}

function detailShow(span,clazz){
	$("."+clazz+"Show").fadeToggle(100);
}
</script>
</head>
<body>
<div class="infoAudit" style="overflow: auto;height: 500px;">
		<div id="infoDiv" class="div_table" style="padding-top: 5px;">
			<table class="xllist">
				<tbody>
					<tr>
						<th>学员</th>
						<th>评价时间</th>
						<th>标准分</th>
						<th>得分</th>
						<th>
							<c:if test="${empty evals}">
								操作
							</c:if>
							<c:if test="${not empty evals}">
								<a style="color: #459ae9;cursor: pointer;" onclick="exportItemEval('All');">批量导出</a>
							</c:if>
						</th>
					</tr>
				</tbody>
				<c:forEach items="${evals}" var="eval">
					<tr>
						<td>
							<span style="cursor: pointer;color: blue;font-size: 0.8em;line-height: 5px;" onclick="detailShow(this,'${eval.evalFlow}');">${eval.operUserName}</span>
						</td>
						<td>${pdfn:transDateTime(eval.createTime)}</td>
						<td>${all}</td>
						<td>${eval.evalScore}</td>
						<td>
							<a style="color: #459ae9;cursor: pointer;" onclick="exportItemEval('${eval.evalFlow}');">导出</a>
						</td>
					</tr>
					<c:set var="baseScore" value="0"></c:set>
					<c:set var="score" value="0"></c:set>
					<c:set var="gradeMap" value="${gradeAllMap[eval.evalFlow]}"></c:set>
					<c:if test="${item.itemTypeId ne 'JXCFAP'}">
						<tr>
							<td class="${eval.evalFlow}Show" colspan="5" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
									基本信息
							</td>
						</tr>
						<tr class="${eval.evalFlow}Show"  style="text-align: left;padding-left: 10px;;display: none;">
							<td>时间</td>
							<td>${gradeMap['planTime']}</td>
							<td>姓名</td>
							<td>${gradeMap['userName']}</td>
							<td></td>
						</tr>
						<tr class="${eval.evalFlow}Show"  style="text-align: left;padding-left: 10px;;display: none;">
							<td>基地名称</td>
							<td colspan="4">${gradeMap['orgName']}</td>
						</tr>
						<tr class="${eval.evalFlow}Show"  style="text-align: left;padding-left: 10px;;display: none;">
							<td>课程名称</td>
							<td colspan="4">${gradeMap['name']}</td>
						</tr>
						<tr class="${eval.evalFlow}Show"  style="text-align: left;padding-left: 10px;;display: none;">
							<td>授课教师</td>
							<td>${gradeMap['teaName']}</td>
							<td>职称</td>
							<td>${gradeMap['teaTitle']}</td>
							<td></td>
						</tr>
						<tr class="${eval.evalFlow}Show"  style="text-align: left;padding-left: 10px;;display: none;">
							<td>科室</td>
							<td colspan="4">${gradeMap['deptName']}</td>
						</tr>
					</c:if>
					<c:forEach items="${titleFormList}" var="title">
						<tr>
							<td class="${eval.evalFlow}Show" colspan="5" style="text-align: left;padding-left: 10px;font-weight: bold;display: none;">
									${title.name}
							</td>
						</tr>
						<c:forEach items="${title.itemList}" var="item">
							<c:set var="scoreKey" value="${eval.evalFlow}${item.id}"/>

							<c:set var="baseScore" value="${baseScore+item.score}"></c:set>
							<c:set var="score" value="${score+scoreMap[scoreKey]}"></c:set>
							<tr>
								<td class="${eval.evalFlow}Show" style="text-align: left;padding-left: 10px;display: none;" colspan="2">${item.name}</td>
								<td class="${eval.evalFlow}Show" style="display: none;text-align: center;padding-left: 0px;">${item.score}</td>
								<td class="${eval.evalFlow}Show" style="display: none;text-align: center;padding-left: 0px;">${scoreMap[scoreKey]}</td>
								<td class="${eval.evalFlow}Show" style="display: none;text-align: center;padding-left: 0px;"></td>
							</tr>
						</c:forEach>
					</c:forEach>
					<c:if test="${item.itemTypeId eq 'JXCFAP'}">
						<tr class="${eval.evalFlow}Show"  style="text-align: left;padding-left: 10px;;display: none;">
							<td>参加本次查房后，认为收获:</td>
							<td colspan="4">${gradeMap['shouHuo']}</td>
						</tr>
					</c:if>
					<tr class="${eval.evalFlow}Show"  style="text-align: left;padding-left: 10px;;display: none;">
						<td>意见或建议:</td>
						<td colspan="4">${eval.evalContent}</td>
					</tr>
					<tr>
						<td class="${eval.evalFlow}Show" style="text-align: left;padding-left: 10px;display: none;" colspan="2">总分</td>
						<td class="${eval.evalFlow}Show" style="display: none;text-align: center;padding-left: 0px;">${baseScore}</td>
						<td class="${eval.evalFlow}Show" style="display: none;text-align: center;padding-left: 0px;">${score}</td>
						<td class="${eval.evalFlow}Show" style="display: none;text-align: center;padding-left: 0px;"></td>
					</tr>
				</c:forEach>
				<c:if test="${empty evals}">
					<tr>
						<td colspan="99">暂无评分</td>
					</tr>
				</c:if>
			</table>
		</div>
</div>
</body>
</html>