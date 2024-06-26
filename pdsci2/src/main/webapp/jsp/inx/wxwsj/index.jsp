<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无锡市卫计委科教管理平台</title>


<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/wxwsj/css/style.css'/>" />

<style>
body,html{
	overflow:auto;
	position: relative;
}
#player{position:relative;width:286px;height:240px;overflow:hidden; border-right:1px solid #ccc;float: left; float: right;}
#player a{color:#333;}
#player *{border:0;padding:0;margin:0;}
#player .Limg{position:relative;}
#player .Limg li{position:absolute;top:0;left:0;background:#fff;}
#player .Limg li img{border:1px solid #FFFFFF; margin:0; width:286px;height:210px;}
#player .Limg li p{line-height:25px; font-size:14px; text-align:center; color:#000;}
#player .Nubbt{position:absolute;z-index:9;right:5px;bottom:38px; line-height:20px;}
#player .Nubbt span{ float:left; border:1px solid #999999;background:#121210;padding:1px 5px;margin:0 2px; font-style:normal;cursor:pointer; color:#FFFFFF;}
#player .Nubbt span.on{background:#CC0000;color:#FFFFFF;}
</style>
<script>
	function reloadVerifyCode(){
		$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
	}
	
	function checkForm(){
		if(false==$("#loginForm").validationEngine("validate")){
			return false;
		}
		$("#loginForm").submit();
	}
	
	function regist(){
		window.location.href="<s:url value='/reg/srm/go'/>";
	}
	
	function logout(){
		window.location.href="<s:url value='/logout.do'/>";
	}

	function regist(){
		window.location.href="<s:url value='/reg/srm/go'/>";
	}
	
	$(document).ready(function(){
		//jboxLoad("container_02","<s:url value='/inx/wxwsj/queryData?columnId=LM01&jsp=inx/wxwsj/index_news&endIndex=8'/>");
		jboxLoad("news","<s:url value='/inx/wxwsj/queryData?columnId=LM03&jsp=inx/wxwsj/index_notice&endIndex=10'/>");
		jboxLoad("dow_box","<s:url value='/inx/wxwsj/queryData?columnId=LM02&jsp=inx/wxwsj/index_downloads&endIndex=8'/>");
		jboxLoad("dow_box_01","<s:url value='/inx/wxwsj/queryData?columnId=LM04&jsp=inx/wxwsj/index_law&endIndex=8'/>");
		jboxLoad("expert","<s:url value='/inx/wxwsj/queryData?columnId=LM05&jsp=inx/wxwsj/index_expert&endIndex=7&imgUrl=${GlobalConstant.FLAG_Y}'/>");
	});
	
	
	function mouseover(){
		$(".news_text dt").mouseover(function(){
			$(this).css({color:"#0659b6",textDecoration:"underline"});
		}).mouseout(function(){
			$(this).css({color:"#333",textDecoration:"none"});	
		})
	}
	
	function selOrg(){
		if($("#orgAreaId").val()!=""){
			$("#orgFlow").empty().append('<option/>');
			jboxGet("<s:url value='/inx/wxwsj/selOrg'/>?orgAreaId="+$("#orgAreaId").val(),null,function(resp){
				if(resp.length){
					for(var index in resp){
						var option = $('<option/>');
						option.attr({"value":resp[index].orgFlow}).text(resp[index].orgName);
						$("#orgFlow").append(option);
					}
				}
			},null,false);
		}
	}
</script>

</head>


<body style="background:url(<s:url value='/'/>jsp/inx/wxwsj/images/bg.png) repeat-x;margin:0;">
<%@include file="header.jsp" %>

<div class="banner">
	<div class="tip"><img src="<s:url value='/'/>jsp/inx/wxwsj/images/banner.png"></div>
    <div class="projection"></div>
</div>

<div class="wx_container">

	<div class="news" id="news">
		<!-- 通知公告 -->
	</div>
	
    <div class="user">
    	<div class="cheak">用户登记</div>
        <div class="un">
    	<form id="loginForm" action="<s:url value='/inx/wxwsj/login'/>" method="post" style="overflow:hidden;position: relative;">
    	<input type="hidden" name="errorLoginPage" value="inx/wxwsj/index"/>
      	<input type="hidden" name="successLoginPage" value="inx/wxwsj/index"/>
    	<c:choose>
			 	<c:when test="${empty sessionScope.currUser}">
			 		<div class="username">
		            	<a>选择机构:</a>
		            </div>
		            <div class="username">
		            	<a style="float:left;"><select class="validate[required]" id="orgAreaId" onchange="selOrg();" style="width: 70px;height: 32px; margin:0;" name="orgAreaId" >
		            	<option value=""></option>
		            	<option value="320202">崇安区</option>
	            		<option value="320203">南长区</option>
	            		<option value="320204">北塘区</option>
	            		<option value="320205">锡山区</option>
	            		<option value="320206">惠山区</option>
	            		<option value="320211">滨湖区</option>
	            		<option value="320281">江阴市</option>
	            		<option value="320282">宜兴市</option>
	            		<option value="320283">新区</option>
		            	</select></a>&nbsp;
		            	<select class="validate[required]" id="orgFlow" style="width: 163px;height: 32px;margin-left:9px" name="orgFlow">
		            	</select>
		            </div>
		        	<div class="username">
		            	<a>用户名:</a>
						<img style="margin-left:18px" src="<s:url value='/'/>jsp/inx/wxwsj/images/mima_01.png">
		            	<input name="userCode" type="text" placeholder="用户名" value="" class="validate[required]"/>
						<img src="<s:url value='/'/>jsp/inx/wxwsj/images/mima_03.png">
		            </div>
		            <div class="username">
		            	<a>密码:</a>
						<img style="margin-left:18px" src="<s:url value='/'/>jsp/inx/wxwsj/images/mima_01.png">
		                <input name="userPasswd" type="password" class="validate[required]"/>
						<img src="<s:url value='/'/>jsp/inx/wxwsj/images/mima_03.png">
		            </div>
		            <div class="username">
		            	<a>验证码:</a>
						<img style="margin-left:18px" src="<s:url value='/'/>jsp/inx/wxwsj/images/mima_01.png">
		                <input name="verifyCode" type="text" class="validate[required] duan"/>
		                <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer; width: 110px; float: left;" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
		            </div>
		            
		            <c:choose>
			      		<c:when test="${not empty loginErrorMessage}">				      		
			     			<div style="font-size:15px; color:#f00;text-align: center;margin-top:-6px">
			     				&nbsp;${loginErrorMessage}
				           		&nbsp;<a href="<s:url value='/reg/forget/first'/>" >忘记密码？</a>
			     			</div>
			      		</c:when>
			      		<c:otherwise>
				            <a href="<s:url value='/reg/forget/first'/>" style="margin-left:94px;float:left;margin-top:-6px">忘记密码？</a>
			      		</c:otherwise>
			      	</c:choose>
		            
		            <input type="submit" class="login" value="登&#12288;录" onclick="return checkForm();" style="cursor: pointer;"/>
		           <!-- 
		            <input type="button" class="register" value="注&#12288;册" onclick="regist();" style="cursor: pointer;"/>
		            -->
			 	</c:when>
			 	<c:otherwise>
			 		<div style="text-align: center; margin-top:60px;">
	 					<span style="font-size:18px; color:#f00;line-height:30px;">${sessionScope.currUser.userName}</span><br/>
 						<span style="font-size:18px;">您好，欢迎登录本系统!</span><br/><br/>
       					<div class="login" onclick="logout();" style="cursor: pointer; margin-left: 110px;">退&#12288;出</div>
 					</div>
			 	</c:otherwise>
		 	</c:choose>
        </form>
        </div>
    </div>
</div>

	<div class="container_02" id="container_02" style="display: none">
		<!-- 新闻中心 -->
	</div>
	
	<div class="container_02" id="expert">
		<!-- 名医 -->
	</div>

	<div class="container_03">
		<div class="dow_box" id="dow_box">
			<!-- 下载中心 -->
	    </div>
	
	    <div class="dow_box_01" id="dow_box_01">
			<!-- 政策法规 -->
	    </div>
	</div>
<%@include file="footer.jsp" %>
</body>
</html>
