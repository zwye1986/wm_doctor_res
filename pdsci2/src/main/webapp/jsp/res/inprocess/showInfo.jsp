<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
</script>
<style type="text/css">
	.table {
		border: 1px solid #e3e3e3;
		font-size:13px;
	}
	.table th, .table td {
		border-bottom: 1px solid #e3e3e3;
		border-right: 1px solid #e3e3e3;
		text-align: center;
		font-size:13px;
	}
	.table th {
		background: rgba(0, 0, 0, 0) url("<s:url value='/jsp/res/disciple/images/table.png'/>") repeat-x scroll 0 0;
		color: #585858;
		height: 30px;
		font-size:13px;
	}
	.table td {
		height: 30px;
		line-height: 25px;
		text-align: center;
		word-break: break-all;
		padding:10px;
		font-size:13px;
	}
</style>
</head>
<body>
<div class="mainright">
	<div class="content">
		<c:if test="${empty info}">
			<div width="100%" height="100%" style="margin-top: 20px">科室暂未维护入科教育!</div>
		</c:if>
		<c:if test="${not empty info}">
			<div width="100%" height="100%" style="margin-top: 20px">
				<table class="table" width="100%" style="border:0px;">
					<tr style="height: 58px">
						<td style="text-align: center;border:0px; "><span style="font-size: 25px;">${dept.deptName}入科教育信息规范</span></td>
					</tr>
				</table>
				<br>
				<table class="table" width="100%">
					<tr>
						<td style="text-align: right;width:123px;font-weight: bold;" align="right" valign="middle"><span style="font-size: 13px;margin-right: 14px">科室简介：</span></td>
						<td style="text-align: left;" colspan="2">${info.deptBriefing}</td>
					</tr>
					<tr>
						<td style="text-align: right;font-weight: bold;" align="right" valign="middle">
							<span style="font-size: 13px;margin-right: 14px">科室人员：</span>
						</td>
						<td style="text-align: left;">
							<table style="font-size: 13px;width:100%;border:1px solid #e3e3e3;">
								<thead>
								<tr>
									<th>职务</th>
									<th>姓名</th>
									<th>联系方式</th>
									<th>职称</th>
								</tr>
								</thead>
								<tbody id="memberList">
								<c:if test="${not empty members}">
									<c:forEach  items="${members}" var="member">
										<tr>
											<td>
													${member.memberPostName}
											</td>
											<td>${member.memberName}</td>
											<td>${member.memberPhone}</td>
											<td>${member.memberTitleName }
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty members}">
									<tr>
										<td colspan="4">
											暂无成员信息
										</td>
									</tr>
								</c:if>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;font-weight: bold;" align="right" valign="middle"><span style="font-size: 13px;margin-right: 14px">人员简介：</span></td>
						<td style="text-align: left;" colspan="2">${info.deptFramework}</td>
					</tr>
					<tr>
						<td style="text-align: right;font-weight: bold;" align="right" valign="middle"><span style="font-size: 13px;margin-right: 14px">环境介绍：</span></td>
						<td style="text-align: left;" colspan="2">${info.workEnvironment}</td>
					</tr>
					<tr>
						<td style="text-align: right;font-weight: bold;" align="right" valign="middle"><span style="font-size: 13px;margin-right: 14px">诊治范围：</span></td>
						<td style="text-align: left;" colspan="2">${info.workScope}</td>
					</tr>
					<tr>
						<td style="text-align: right;font-weight: bold;" align="right" valign="middle"><span style="font-size: 13px;margin-right: 14px">科室固定学术及&#12288;<br/>业务活动安排：</span></td>
						<td style="text-align: left;" colspan="2">
							<table style="font-size: 13px;width: 100%;border:1px solid #e3e3e3;">
								<thead>
								<tr>
									<th style="width:50px;">时间</th>
									<th style="width:280px;">地点</th>
									<th>内容</th>
								</tr>
								</thead>
								<tbody id="teaching">
								<tr>
									<td>周一</td>
									<td>${arrangeMap['address1']}</td>
									<td>${arrangeMap['content1']}</td>
								</tr>
								<tr>
									<td>周二</td>
									<td>${arrangeMap['address2']}</td>
									<td>${arrangeMap['content2']}</td>
								</tr>
								<tr>
									<td>周三</td>
									<td>${arrangeMap['address3']}</td>
									<td>${arrangeMap['content3']}</td>
								</tr>
								<tr>
									<td>周四</td>
									<td>${arrangeMap['address4']}</td>
									<td>${arrangeMap['content4']}</td>
								</tr>
								<tr>
									<td>周五</td>
									<td>${arrangeMap['address5']}</td>
									<td>${arrangeMap['content5']}</td>
								</tr>
								<tr>
									<td>周六</td>
									<td>${arrangeMap['address6']}</td>
									<td>${arrangeMap['content6']}</td>
								</tr>
								<tr>
									<td>周日</td>
									<td>${arrangeMap['address7']}</td>
									<td>${arrangeMap['content7']}</td>
								</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;font-weight: bold;"  align="right" valign="middle"><span style="font-size: 13px;margin-right: 14px">考勤制度：</span></td>
						<td style="text-align: left;" colspan="2">${info.attendanceInfo}</td>
					</tr>
					<tr>
						<td style="text-align: right;font-weight: bold;" align="right" valign="middle"><span style="font-size: 13px;margin-right: 14px">入科教育文档：</span></td>
						<td style="text-align: left;">
							<table  id="files" style="font-size: 13px;width: 86.65%;border: 0px;">
								<c:if test="${not empty files}">
									<c:forEach  items="${files}" var="file">
										<tr>
											<td style="border:0px;text-align:left;padding-left: 2px;">
												<a href="<s:url value='/res/inprocess/fileDown'/>?fileFlow=${file.fileFlow}">${file.fileName}</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
								<c:if test="${empty files}">
									<tr>
										<td style="border:0px;text-align:left;padding-left: 2px;color:#999;">
											（暂无入科教育文档信息）
										</td>
									</tr>
								</c:if>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value="关&#12288;闭" class="search" onclick="top.jboxClose();" style="margin-top: 5px;margin-bottom: 5px;"/>
						</td>
					</tr>
				</table>
			</div>
		</c:if>

	</div>
</div>
</div>
</body>
</html>