<%@include file="/jsp/common/doctype.jsp" %>
<style>
	.infoTable td {
		background-color:white;
		text-align: center;
		line-height: 25px;
		height: 30px;
		word-break: break-all;
		border-bottom:1px solid #bbbbbb;
	}
	.infoTable th {
		background-color:white;
		text-align: center;
		line-height: 25px;
		height: 30px;
		word-break: break-all;
	}
</style>
<script>
	function removeThis(obj){
		$(obj).parent().parent().remove();
	}
	function goList(){
		var lurl=window.frames['mainIframe'].location.href;
		var url="<s:url value='/res/doc/noticeList'/>?fromSch=${param.fromSch}&isDoctor=${param.isDoctor}&paramUrl="+encodeURI(lurl);
		window.frames['mainIframe'].location.href = url;
		$("#layerd").hide();
	}
</script>
<c:if test="${not empty infos}">
<div id="layerd" style="position: fixed; border:1px solid #bbbbbb;bottom: 0px; right: 0px;line-height: 0px; z-index: 1000; width: 400px; height: 278px;    background-color: white;">
	<script>
		function closeLay()
		{
			$('#layerd').hide();
		}
	</script>
	<div class="J_close layer_close" style="cursor: pointer;background-color:#efefef;
		padding:0px;color:#333;font:12px/24px Helvetica,Tahoma,Arial,sans-serif;text-align:right;"><a href="javascript:void(0);" onclick="closeLay();">关闭&#12288;</a></div>

	<table class="infoTable" style=" width:100%;">
		<tr>
			<td width="80px">未读通知</td>
			<td>
			</td>
			<td width="80px">
				<span style="text-align:center;"> <a style="cursor:pointer;color: #2f8cef" onclick="goList()" >>>查看全部</a></span>
			</td>
		</tr>
	</table>
	<div id="cpro_u2895327" style=" max-height: 220px;overflow: auto;background-color: white;">
		<table class="infoTable" style=" width:100%;">
			<c:forEach items="${infos}" var="info">
				<tr>
					<td colspan="3" style="text-align: left;padding-left: 15px;">
						<a href="<s:url value='/res/platform/noticeView'/>?infoFlow=${info.infoFlow}" onclick="removeThis(this);" target="_blank">${info.infoTitle}</a>
						<img src="<s:url value='/css/skin/new.png'/>"/>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</c:if>