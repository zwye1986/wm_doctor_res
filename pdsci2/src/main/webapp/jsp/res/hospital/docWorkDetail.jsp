<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
	<style type="text/css">
		table{ margin:10px 0;border-collapse: collapse;}
		caption,th,td{height:40px;}
		caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;margin-bottom: 10px;}
		th{text-align:right;font-weight:500;padding-right:5px; color:#333;}
		td{text-align:left; padding-left:5px; }
		[type='text']{width:150px;height: 22px;}
		select{width: 153px;height: 27px;}
	</style>

	<script type="text/javascript">
		function doClose(){
			top.jboxClose();
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix" style="padding:0; ">
			<div class="div_table"  style="width:100%;" >
				<table class="basic" width="100%">
					<tr>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">标准科室</th>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">科室名称</th>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">时间（月）</th>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">轮转时间</th>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">轮转状态</th>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">带教老师</th>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">科主任</th>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;min-width:80px;width:1%;">是否已评价带教/科主任</th>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">培训数据<br>要求数/完成数/审核数</th>
					</tr>
					<c:forEach items="${rltLst}" var = "obj">
						<tr>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
								<c:if test="${obj.teacherUserFlow ne ''}">
									${obj.standDeptName}
								</c:if>
							</td>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${obj.schDeptName}</td>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
								<c:if test="${!(empty obj.month)}">
									<fmt:formatNumber type="number" pattern="0.0" value="${obj.month+0.001}"/>
								</c:if>
							</td>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
								<c:choose >
									<c:when test="${ ( empty obj.schStartDate) and  (empty obj.schEndDate)}">

									</c:when>
									<c:otherwise>
										${obj.schStartDate}至${obj.schEndDate}
									</c:otherwise>
								</c:choose>

							</td>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
								<c:choose>
									<c:when test="${(!(obj.isCurrentFlag eq 'Y') and !(obj.schFlag eq 'Y')) or empty obj.processFlow }">
										未轮转
									</c:when>
									<c:otherwise>
										<c:if test="${obj.isCurrentFlag eq 'Y'}">
											轮转中
										</c:if>
										<c:if test="${obj.schFlag eq 'Y'}">
											已出科
										</c:if>
									</c:otherwise>
								</c:choose>
							</td>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${obj.teacherUserName}</td>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${obj.headUserName}</td>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${obj.evaluationFlag}</td>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${obj.reqNum}/${obj.completeNum}/${obj.checkNum}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty rltLst}">
						<tr>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;" colspan="99">无数据记录！</td>
						</tr>
					</c:if>
				</table>
			</div>
			<p style="text-align: center;">
				<input type="button" onclick="doClose();" class="search" value="关&#12288;闭"/>
			</p>
		</div>
	</div>
</div>
</body>
</html>
