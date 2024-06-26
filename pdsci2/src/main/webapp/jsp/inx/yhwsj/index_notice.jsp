<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
	.new_02 ul li a{color:#333;line-height:30px;padding-left:8px;background-image:url(../images/dian.png);background-repeat:no-repeat;background-position:center left;}
</style>
<div class="new_01">
	<img src="<s:url value='/'/>jsp/inx/yhwsj/images/style.png" class="fl">
    <a href="<s:url value='/inx/yhwsj/queryByColumnId?columnId=LM03&id=tzgg'/>" class="fl title">通知公告</a>
    <a href="<s:url value='/inx/yhwsj/queryByColumnId?columnId=LM03&id=tzgg'/>" class="fr more">more&gt;&gt;</a>
</div>
<div class="new_02">
    <ul class="fl">
        <c:forEach items="${infoList}" var="info">
			<li>
				<a href="<s:url value='/inx/yhwsj/getByInfoFlow?infoFlow=${info.infoFlow}&endDate=${info.createTime}&columnId=${param.columnId}'/>" 
				target="_blank">${pdfn:cutString(info.infoTitle,16,true,6)}</a>
			</li>
		</c:forEach>
		<c:if test="${empty infoList}">
			<li style="text-align: center;">无记录</li>
		</c:if>
    </ul>
    <dl class="fl">
        <c:forEach items="${infoList}" var="info">
			<dt>[${info.infoTime}]</dt>
		</c:forEach>
    </dl>
</div>