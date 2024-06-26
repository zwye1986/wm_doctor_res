﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
</jsp:include>
<form id="saveCfgForm" >
	<fieldset>
		<legend>考试次数</legend>
		<table class="xllist">
			<tr>
				<th width="20%">配置项</th>
				<th width="80%">配置内容</th>
			</tr>
			<tr>
				<td style="text-align: right" width="100px">学员出科考试次数：</td>
				<td style="text-align: left;padding-left: 5px" width="200px">
					<c:set var="key" value="out_test_limit_${sessionScope.currUser.orgFlow}"></c:set>
					<input type="hidden" name="cfgCode" value="${key}">
					<input type="text" class="xltext validate[custom[integer],min[1]]" name="${key}" value="${pdfn:resPowerCfgMap(key).cfgValue}" style="width: 200px;"/>
					<input type="hidden" name="${key}_ws_id"  value="sys">
					<input type="hidden" name="${key}_desc"  value="出科考次数限制">
					<span>说明：未设置表示不限制考试次数</span>
				</td>
			</tr>
		</table>
	</fieldset>
</form>
<div style="text-align: center;padding-bottom:10px;">
	<input type="button" class="search" onclick="save();" value="保&#12288;存">
</div>