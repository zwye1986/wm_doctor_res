<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
	<jsp:include page="../../service.jsp"></jsp:include>
</c:if>
<div id="footer">
	主办单位：${sysCfgMap['the_competent_unit']}&#12288;地址：南京市玄武区中央路42号 <br />
	&#12288;&#12288;&#12288;技术支持：<a href="http://www.njpdkj.com" target="_blank">南京品德信息网络技术有限公司</a>&#12288;电话：025-69815356 69815357&#12288;
</div>