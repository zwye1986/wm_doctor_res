<script type="text/javascript">
$(document).ready(function(){
	$("html,body").animate({scrollTop:$("#news_top").offset().top},1);
});
</script>

<div class="news_text_box_01">
	<a class="title_da">${info.infoTitle}</a>
	<c:if test="${param.nextFlag != GlobalConstant.FLAG_N}">
		<a style="display:block;text-align:right;line-height:34px;">浏览量：${info.viewNum}&#12288;&#12288;&#12288;日期：${info.infoTime}</a>
	</c:if>
</div>
<div class="news_text_box_02">
	<p>${info.infoContent}</p>
</div>

