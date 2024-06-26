
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<title>学习中心-查看课程</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<jsp:include page="/jsp/njmuedu/htmlhead-njmuedu.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="video" value="true"/>
	<jsp:param name="jwplayer" value="true"/>
</jsp:include>

<script type="text/javascript">
$(function(){
	 loadChapterVideo('N');
});
function onEvent(src,e){
    if(window.event)
        keyPressed = window.event.keyCode; // IE
    else
       keyPressed = e.which; // Firefox
    if(keyPressed==13)
    {          
      return false;
    }
}
function loadChapterVideo(flag){
	if(flag=='Y'){
		clearInterval(interval);
		clearInterval(currentTimeInterval);
	}
	var level=$("#videoLevelSelect").val();
	if(level===undefined){
		level="${sysCfgMap['njmuedu_acquiesce_video']}";
	}
	var url="<s:url value='/njmuedu/course/loadChapterVideo'/>?chapterFlow=${chapter.chapterFlow}&level="+level+"&method=preview";
	jboxLoad("videoBox",url,false);
}
</script>
<style>
#volume{margin-left:15px;}
#mySlider{
width:100px;
border:1px solid #dbdbdb;
background:#898989;
height:2px;
}
.ui-widget-header {
border: 1px solid #aaa;
background: #2783d9;
color: #222;
font-weight: bold;
}
.ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
border: 2px solid #2783d9;
background: #edf0f2;
font-weight: normal;
color: #555;
}
.ui-slider .ui-slider-handle {
position: absolute;
z-index: 2;
width: 8px;
height: 8px;
border-radius:5px;
cursor: default;
}
.ui-slider-horizontal .ui-slider-handle {
margin-left:0;
margin-right:0;
}
.ui-dialog{position: relative;}

#register {
background: url("<s:url value='/css/skin/${skinPath}/images/station_bg.jpg' />") repeat-x;
height: 75px;
text-align: left;
}
#logo {
margin: 0 auto;
width: 1150px;
height: 75px;
line-height: 75px;
}
#logo img {
vertical-align: middle;
padding-top: 20px;
}
#footer {
margin: 0 auto;
margin-top: 15px;
margin-bottom: 30px;
width: 900px;
text-align: center;
color: #999999;
font-size: 13px;
height: 100px;
}
.video-logo{padding-top: 8px;}
</style>
</head>
<body onkeydown="return onEvent(this,event);">
<div id="register">
   <div class="video-logo"><img src="<s:url value='/css/skin/${skinPath}/images/${applicationScope.sysCfgMap["sys_login_img"]}_head.png'/>" /></div>
</div>
<div style="overflow: hidden;height: 100%;width: 100%" id="mainDiv">
<!--videoPage-->
<div class="videoPage cbody clearfix video-sig">
   <div class="video-title">
     <h3 class="fl">&nbsp;&nbsp;预览视频${chapter.chapterFileName }</h3>
  </div>
   <div class="videoBox" id="videoBox">
       
  </div> 
  <div class="clear"></div>
  
</div>

<!--/content-->
<div id="footer">技术支持：南京品德信息技术有限公司<br />Copyright © 2001- 2018 Character Technology, Inc. All rights reserved.</div>
</div>
</body>
</html>