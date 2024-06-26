
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>

	<script type="text/javascript">
		$(document).ready(function(){
			init();
		});

		function init(){
			$(".operDiv").on("mouseenter mouseleave",function(){
				$(this).find("span").toggle();
			});
		}
		</script>
</head>
<body>
			<div class="search_table">
				<table border="0" cellpadding="0" cellspacing="0" class="grid">
				<c:forEach items="${infos}" var="msg">
		            <tr  class="operDiv">
		            	<td style="padding-right: 10px;">
		            		<div style="float: left;">
		            			<a href="<s:url value='/inx/hbzy/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank">${msg.infoTitle}</a>
		            			<c:if test="${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.infoTime))<=7}">
									<img src="<s:url value='/css/skin/new.png'/>"/>
							     </c:if>
		            		</div>
		            		<div style="float: right;">
		            			<span>${pdfn:transDate(msg.infoTime)}</span>
		            			<span style="display:none;">
		            				<a href="javascript:edit('${msg.infoFlow}');" style="color: gray;">编辑</a> |
									<a href="<s:url value='/inx/hbzy/noticeView'/>?infoFlow=${msg.infoFlow}" target="_blank" style="color: gray;">查看</a> |
									<a href="javascript:delNotice('${msg.infoFlow}');" style="color: gray;">删除</a>
		            			</span>
		            		</div>
		                </td>
		             </tr>
		         </c:forEach>
		         <c:if test="${empty infos}">
				     <tr>
				     	<td style="text-align: center;"><strong>无记录!</strong></td>
					 </tr>
				 </c:if>
			</table>
			</div>
			<div class="page" style="padding-right: 40px;">
				<c:set var="pageView" value="${pdfn:getPageView(infos)}" scope="request"></c:set>
				<pd:pagination-jsres toPage="toPage"/>
			</div>
</body>
</html>
