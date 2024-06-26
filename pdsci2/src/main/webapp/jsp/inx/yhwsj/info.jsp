<script type="text/javascript">
$(document).ready(function(){
	$("html,body").animate({scrollTop:$("#news_top").offset().top},10);
});
</script>

<div class="news_text_box_01" >
<a class="title_da">${info.infoTitle}</a>
<a class="riqi">浏览量：${info.viewNum}&#12288;&#12288;&#12288;日期：${info.infoTime}</a>
</div>
<div class="news_text_box_02">${info.infoContent}</div>

