<%@include file="/jsp/common/doctype.jsp" %>
<script type="text/javascript">
<c:if test="${columnId eq 'LM03'}">
	var currindex = 0;
	var roundTimer;

	function roundGo(){
		roundTimer = setTimeout(function(){
			overNews($('.LM03.first'));
			currindex = (currindex>($('.LM03').length-1))?0:currindex;
			runNews($('.LM03:eq('+(currindex++)+')'));
			roundGo();
		},3000);
	}

	$(function(){
		if($('.LM03').length){
			runNews($('.LM03:eq('+(currindex++)+')'));
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
		var imgUrl = img==""?"<s:url value='/jsp/inx/cdedudoor/images/newpic/defaultImg.jpg'/>":baseUrl+img;
		$('#imgNews').attr("src", imgUrl);
		$('#partInfo'+i).html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+content);
		$(obj).addClass("first");
	}

	function showImgNewsManual(baseUrl,img,content,i,obj){
		overNews($('.LM03.first'));
		stopRound(obj);
		showImgNews(baseUrl,img,content,i,obj);
	}

	function closeNews(i,obj){
		$('#partInfo'+i).html("");
		$(obj).removeClass("first");
	}

	function closeNewsManual(i,obj){
		closeNews(i,obj);
		runNews($('.LM03:eq('+(currindex++)+')'));
		roundGo();
	}

	function stopRound(obj){
		clearTimeout(roundTimer);
		currindex = $('.LM03').index(obj);
	}
</c:if>
</script>
<ul class="Limg">
	<c:forEach items="${infoList}" var="info" varStatus="i">
		<li>
			<a
					<c:if test="${columnId eq 'LM03'}">
						class="LM03"
						baseurl="${imageBaseUrl}"
						thisindex="${i.index}"
						titleimg="${info.titleImg}"
						newscontent="${pdfn:cutString(info.infoContent,45,true,4)}"
						onmouseover="showImgNewsManual('${imageBaseUrl}','${info.titleImg}','${pdfn:cutString(info.infoContent,45,true,4)}',${i.index},this)"
						onmouseout="closeNewsManual(${i.index},this)"
					</c:if>
					href="<s:url value='/inx/cdedudoor/getByInfoFlow?infoFlow=${info.infoFlow }'/>"
			>
					${pdfn:cutString(info.infoTitle,15,true,3)}
			</a>
			<p id="partInfo${i.index}"></p>
			<c:if test="${info.columnId ne 'LM02' && info.columnId ne 'LM03' && info.columnId ne 'LM09'}">&#12288;<span>${info.infoTime}</span></c:if>
		</li>
	</c:forEach>
</ul>