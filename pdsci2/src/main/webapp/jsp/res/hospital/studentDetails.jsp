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
			jboxClose();
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix" style="padding:0; ">
			<div class="div_table"  style="width:100%;" >
				<table border="0" cellpadding="0" cellspacing="0"  class="basic" style="width:100%;border: 1px solid #e3e3e3;">
					<colgroup>
						<col width="20%"/>
						<col width="15%"/>
						<col width="10%"/>
                        <c:if test="${isShow ne 'Y'}">
						<col width="20%"/>
                        </c:if>
						<col width="17%"/>
						<col width="18%"/>
					</colgroup>
					<tr>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">轮转科室</th>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">姓名</th>
                        <c:if test="${isShow ne 'Y'}">
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">年级</th>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">培训专业</th>
                        </c:if>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">轮转开始时间</th>
						<th style="text-align: center;border: 1px solid #e3e3e3;padding-left: 10px;">轮转结束时间</th>
					</tr>
					<c:forEach items="${doctorListMap}" var="doctor">
						<tr>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${doctor.schDeptName}</td>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${doctor.doctorName}</td>
                            <c:if test="${isShow ne 'Y'}">
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${doctor.sessionNumber}</td>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">
								<c:if test="${jszyFlag ne 'Y'}">${doctor.trainingSpeName}</c:if>
								<c:if test="${jszyFlag eq 'Y'}">${doctor.doctorCategoryName}</c:if>
							</td>
							</c:if>
                            <td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${doctor.schStartDate}</td>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;">${doctor.schEndDate}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty doctorListMap}">
						<tr>
							<td style="text-align: center;border: 1px solid #e3e3e3;padding-left: 0px;" colspan="6" style="text-align: center;">暂无记录！</td>
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
