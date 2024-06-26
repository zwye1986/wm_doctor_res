<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<script type="text/javascript">
function doRefresh(){
    var url = "<s:url value='/sys/dict/doRefresh'/>";
    jboxGet(url,null,function(resp){
        jboxTip(resp);
    });
}
function doRemoteRefresh(hostPort){
	var url = "<s:url value='/sys/dict/doRemoteRefresh2'/>?hostPort="+hostPort;
	jboxGet(url,null,function(resp){
		jboxTip(resp);				
	});	
}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
			<table class="xllist" >  
				<tr>
   					<th colspan="10" align="center">当前服务器标识：${pdfn:split(pageContext.session.id,'.')[1] }&#12288;&#12288;<a href="javascript:doRefresh();">刷新本服务</a> </th>
   				</tr>
   				<c:if test="${empty hostPortList }">
					<tr>
						<td colspan="2" style="color:#f00;">未配置刷新服务</td>
					</tr>
					<tr>
						<td width="30%" align="right">配置说明</td>
						<td align="left">在 pdsci.properties 文件内添加 remote.refresh.hosts=127.0.0.1:8080,127.0.0.1:9090 格式为(IP:PORT)多个服务用英文逗号隔开，服务处于内网环境则填写内网IP</td>
					</tr>
   				</c:if>
   				<c:if test="${not empty hostPortList }">
					<tr>
						<th align="center" width="80%">服务器</th>
						<th align="center" width="20%">操作</th>
					</tr>
   					<c:forEach items="${hostPortList}" var="hostPort" varStatus="status">
					<tr>
	   					<td>http://${hostPort}</td>
	   					<td><a href="javascript:doRemoteRefresh('${hostPort}');">刷新</a></td>
	   				</tr>
		   			</c:forEach> 
   				</c:if>
			</table>
			</div>
		</div>
	</div>
</body>
</html>