<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="inform">
       <div class="new_01">
		   <img src="<s:url value='/'/>jsp/inx/yhwsj/images/style.png" class="fl">
           <a href="<s:url value='/inx/yhwsj/queryByColumnId?columnId=LM02&id=xzzx'/>" class="fl title">下载中心</a>
           <a href="<s:url value='/inx/yhwsj/queryByColumnId?columnId=LM02&id=xzzx'/>" class="fr more">more&gt;&gt;</a>
       </div>
       <div class="new_02">
           <ul class="fl">
           	   <c:forEach items="${infoList}" var="info">
					<li>
						<a href="<s:url value='/inx/yhwsj/getByInfoFlow?infoFlow=${info.infoFlow}&endDate=${info.createTime}&columnId=${param.columnId}'/>" 
						target="_blank">${pdfn:cutString(info.infoTitle,50,true,6)}</a>
					</li>
			    </c:forEach>
			    <c:if test="${empty infoList}">
					<li>无记录</li>
			   	</c:if>
           </ul>
           <dl class="fr no_03">
           	   <c:forEach items="${infoList}" var="info">
				<dt>[${info.infoTime}]</dt>
			   </c:forEach>
           </dl>
   	</div>
</div>