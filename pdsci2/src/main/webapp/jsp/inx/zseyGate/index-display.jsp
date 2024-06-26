<%@include file="/jsp/common/doctype.jsp" %>
<style>
	.LM01 a{
		width: 100%;
	}
</style>
<script type="text/javascript">
<c:if test="${columnId eq 'LM01'}">
	var currindex = 0;
	var roundTimer;

	function roundGo(){
		roundTimer = setTimeout(function(){
			overNews($('.LM01.first'));
			currindex = (currindex>($('.LM01').length-1))?0:currindex;
			runNews($('.LM01:eq('+(currindex++)+')'));
			roundGo();
		},3000);
	}

	$(function(){
		if($('.LM01').length){
			runNews($('.LM01:eq('+(currindex++)+')'));
			roundGo();
		}
	});

	function runNews(theNews){
		var baseurl = theNews.attr('baseurl');
		var thisindex = theNews.attr('thisindex');
		var titleimg = theNews.attr('titleimg');
		var newscontent = theNews.attr('newscontent');
		showImgNews(baseurl,titleimg,newscontent,thisindex,theNews);
	}

	function overNews(theNews){
		var thisindex = theNews.attr('thisindex');
		closeNews(thisindex,theNews);
	}

	function showImgNews(baseUrl,img,content,i,obj){
		if(img!="") {
			var imgUrl = img == "" ? "<s:url value='/jsp/inx/zseyGate/images/newpic/defaultImg.jpg'/>" : baseUrl + img;
			$('#imgNews').attr("src", imgUrl);
			$('#imgNews').show();
		}
		$('#partInfo' + i).html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + content);
		$(obj).addClass("first");
	}

	function showImgNewsManual(baseUrl,img,content,i,obj){
		overNews($('.LM01.first'));
		stopRound(obj);
		showImgNews(baseUrl,img,content,i,obj);
	}

	function closeNews(i,obj){
		$('#partInfo'+i).html("");
		$(obj).removeClass("first");
	}

	function closeNewsManual(i,obj){
		closeNews(i,obj);
		runNews($('.LM01:eq('+(currindex++)+')'));
		roundGo();
	}

	function stopRound(obj){
		clearTimeout(roundTimer);
		currindex = $('.LM01').index(obj);
	}
</c:if>
</script>
<ul class="Limg">
	<c:forEach items="${infoList}" var="info" varStatus="i">
		<li>
			<p><a
					<c:if test="${columnId eq 'LM01'}">
						class="LM01"
						baseurl="${imageBaseUrl}"
						thisindex="${i.index}"
						titleimg="${info.titleImg}"
						newscontent="${pdfn:cutString(info.infoContent,45,true,4)}"
						onmouseover="showImgNewsManual('${imageBaseUrl}','${info.titleImg}','${pdfn:cutString(info.infoContent,45,true,4)}',${i.index},this)"
						onmouseout="closeNewsManual(${i.index},this)"
					</c:if>
					target="_blank"
					href="<s:url value='/inx/zseyGate/getByInfoFlow?infoFlow=${info.infoFlow }&roleFlow=${empty param.roleFlow?\'All\': param.roleFlow}&modelId=${empty param.modelId?\'All\': param.modelId}&columnId=${columnId}'/>"
			>
					${pdfn:cutString(info.infoTitle,15,true,3)}
			</a>
			&#12288;<span>${info.infoTime}</span>
			</p>
			<p id="partInfo${i.index}"></p>
		</li>
	</c:forEach>
</ul>