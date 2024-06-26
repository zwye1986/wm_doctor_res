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
</head>
<body>
	<div class="content">
		<div class="title1 clearfix">
			<table class="basic" style="width: 100%;margin: 10px 0px;">
				<tr>
					<th>课程名称</th>
					<td colspan="3" style="text-align: center;"><input type="text" style="width: 420px;" name="" value="心理测量学"/></td>
					<th>课程代码</th>
					<td style="text-align: center;"><input type="text" style="width: 137px;" name="" value="000002"/></td>
				</tr>
				<tr>
					<th>英文名称</th>
					<td colspan="3" style="text-align: center;"><input type="text" style="width:420px" name="" value="Transduction"/></td>
					<th>授课层次</th>
					<td style="text-align: center;">
					<select style="width: 137px;">
						<option>硕士</option><option>博士</option>
					</select>
				</tr>
				<tr>
					<th >讲授学时</th>
					<td style="text-align: center;"><input type="text" style="width: 137px;" name="" value="13"/></td>
					<th>实验学时</th>
					<td style="text-align: center;"><input type="text" style="width: 137px;" name="" value="12"/></td>
					<th>课程类别</th>
					<td style="text-align: center;"><select style="width: 137px;">
						<option>试验课</option><option>理论课</option>
					</select></td>
				</tr>
				<tr>
					<th>上机学时</th>
					<td style="text-align: center;"><input type="text" style="width: 137px;" name="" value="13"/></td>
					<th>其他学时</th>
					<td style="text-align: center;"><input type="text" style="width: 137px;" name="" value="5"/></td>
					<th>总学时</th>
					<td style="text-align: center;"><input type="text" style="width: 137px;" name="" value="43"/></td>	
				</tr>
				
				
				<tr>
					<th>承担单位</th>
					<td colspan="3" style="text-align: center;">
						<select style="width: 424px;">
							<option>请选择</option>
							<option>研究生学院</option>
						</select>
					</td>
					<th>学&#12288;&#12288;分</th>
					<td style="text-align: center;"><input type="text" style="width: 137px;" name="" value="3"/></td>
				</tr>
			</table>
			<div style="text-align: center;">
				<input type="button" class="search" value="保&#12288;存"/>
				<input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
			</div>
		</div>
	</div>
</body>
</html>