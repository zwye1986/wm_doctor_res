<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="rsasecurity" value="true"/>
</jsp:include>
	<style>
	 #cnzz_stat_icon_1260790632 a {color: #fff;font-size: 11px;cursor: default;}
	 .news{
		 width: 1200px;
	 }
	 .news_title{
		 float: left;
		 width: 600px;
		 height: auto;
		 padding-bottom: 50px;
	 }
	 .news_title p{
		 padding-left: 0;
		 text-align: center;
	 }
	 .news_con1{
		 margin-left: 70px!important;
	 }
	 .news_con2{
		 margin-left: 40px!important;
	 }
	 .more a{
		 bottom: 8px;
	 }
	 .more1 a{
		 left: 72%;
	 }
	 .more2 a{
		 left: 66%;
	 }
	 .ques_img{
		 position: fixed;
		 right: 80px;
		 top: 300px;
	 }
	 .ques_img img{
		 width: 100px;
	 }
	</style>
<script>
$(function(){
	if(top!=self){top.location = self.location;}
	//获取当前星期几
	var weekDate = '星期'+'日一二三四五六'.charAt(new Date().getDay());
	$("#weekDateSpan").html(weekDate);
});

$(function(){
    var pwd = $("#placepwd");
    var password = $("#userPasswd");
    if (navigator.appName.indexOf("Microsoft Internet Explorer")>-1) {//处理ie浏览器placeholder和password的不兼容问题
    	password.hide();
    	pwd.show();
    	
    	pwd.focus(function(){
            pwd.hide();
            password.show().focus();
        });
         
        password.focusout(function(){
            if(password.val().trim() === ""){
                password.hide();
                pwd.show();
            }
        });
    }

});

function register(){
	window.location.href="<s:url value='/inx/jsres/register'/>";
}
function forgetPasswd() {
	window.location.href="<s:url value='/inx/jsres/forgetPassword'/>";
}
function reloadVerifyCode()
{
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}
function checkForm(){
	if($("#userCode").val()==""){
		$(".log_tips").html("用户名不能为空!");
		return false;
	}
	if($("#userPasswd").val()==""){
		$(".log_tips").html("密码不能为空!");
		return false;
	}
	if($("#verifyCode").val()==""){
		$(".log_tips").html("验证码不能为空!");
		return false;
	}

	var f=checkUser();
	if(f){
		return true;
	}else {
		return false;
	}
//	return true;
}

function checkUser()
{
	var userCode = $("#userCode").val();
	var url = "<s:url value='/inx/jsres/checkUserCodeInBlack'/>";
	var data = {userCode:userCode};
	jboxPost(url,data,
			function(resp){
//				console.log(resp);
//				console.log(resp != ""&&resp != null &&typeof(resp) != 'undefined');
				if(resp != "" && resp != null && typeof(resp) != 'undefined'){
					var height=(window.screen.height)*0.3;
					var width=(window.screen.width)*0.5;
					jboxOpenContent(resp,"提示信息",width,height,true);
					return false;
				}else {
                    var loginUrl = "<s:url value='/inx/jsres/login'/>";
                    var data = {userCode : $("#userCode").val(),userPasswd : $("#userPasswd").val(), verifyCode : $("#verifyCode").val()};
                    var param = JSON.stringify(data);
                    // 加密  公钥指数  ""  公钥系数
                    if("${pkExponent}" && "${pkModulus}"){
                        var key = RSAUtils.getKeyPair("${pkExponent}", "", "${pkModulus}");
                        data = RSAUtils.encryptedString(key, encodeURI(param));
                    }
                    document.write("<form action='"+ loginUrl +"' method='post' id='form' name='form' style='display:none'>");
                    document.write("<input type='hidden' name='data' value='"+ data +"' />");
                    document.write("</form>");
                    document.form.submit();
					return true;
				}
			}, null, false);
}

function showQr(type){
	$(".QR").hide();
	$(".QR_"+type).show();
}
function hideThis(type){
	$(".QR_"+type).hide();
}

// 问答专区
function changeWenda() {
    var url = "<s:url value='/jsres/consult/main'/>";
    window.open(url,"_blank");
}
</script>
</head>

<body>
<div class="header">
	<span>
	  <div class="fl">${pdfn:getCurrDate() }&nbsp;&nbsp;<font id="weekDateSpan"></font></div>
	  <div class="code fr">
<%--	     <div class="android" onmouseover="showQr('android');" onmouseout="hideThis('android')"></div>--%>
	     <div class="iphone" onmouseover="showQr('iphone');" onmouseout="hideThis('iphone')"></div>
	     <font class="fr">轮转手册登记请扫描二维码</font>
	      <img class="Q_hand fr" src="<s:url value='/jsp/jsres/images/Q_hand.png'/>" />
<%--	      <div class="QR_android QR" style="display: none;"><img class="QR_code" src="<s:url value='/jsp/jsres/images/jsQrcode.jpg'/>" /></div>--%>
		      <div class="QR_iphone QR" style="display: none;"><img style=" width: 150px;height: 150px;padding-top: 10px; padding-left: 9px;"  class="QR_code" src="<s:url value='/jsp/jsres/images/tjapp.png'/>" /></div>
<%--	      <div class="QR_iphone QR" style="display: none;"><img style=" width: 150px;height: 150px;padding-top: 10px; padding-left: 9px;" class="QR_code" src="${applicationScope.sysCfgMap['res_app_cfg_url']}" /></div>--%>
	  </div>
	</span>
</div>
<div class="content global">
	<div class="banner">
		<div class="logo_index_new">
    		<div class="load fr">
    		<form id="loginForm" method="post">
            	<div class="con">
					<div class="user_load"><input type="text" id="userCode" name="userCode" class="text" placeholder="用户名" value="${param.userCode }" /></div>
                    <div class="pass"><input type="text" id="placepwd" class="text" style="display: none;" placeholder="密&nbsp;&nbsp;码" value=""/><input  type="password" id="userPasswd" name="userPasswd" class="text" value="" placeholder="密&nbsp;&nbsp;码"/></div>
                    <div class="verifyCode" style="margin-bottom: 0px;"><input id="verifyCode" style="margin-left: 50px;width: 240px;" name="verifyCode" type="text" class="yz" value="" placeholder="验&nbsp;证&nbsp;码" /><img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;height: 40px; position: absolute;margin-left: -90px;float: left" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" /></div>

					<div class="ts" style="margin-right: 25px">
						<div class="fl"  style="font-weight: 700;font-size:14px">
							<font class="log_tips">
							<c:if test="${not empty loginErrorMessage}">
								登录失败：${loginErrorMessage}
							</c:if>
							</font>
						</div>
						<div style="float: right;height: 30px;width: 100px;text-align: right;font-size:14px;color:#fff;">
							<a style="color:#fff;" onclick="forgetPasswd()" >忘记密码？</a>
						</div>
					</div>
                   <%-- <div class="ts">
                    	<div class="fl" style="font-weight: 700">
                    	<font class="log_tips">
						&lt;%&ndash;<c:if test="${fn:endsWith(loginErrorMessage, '您的信息已被纳入我省医务人员诚信系统，5年内不得进入我省培训基地接受住院医师规范化培训。如有相关疑问，请与相关管理部门联系。')}">&ndash;%&gt;
						&lt;%&ndash;</c:if>&ndash;%&gt;
                    	<c:if test="${not empty loginErrorMessage}">
							登录失败：${loginErrorMessage}
					    </c:if>
					    </font>
					    <c:if test="${empty loginErrorMessage}">&#12288;</c:if>
					    </div>
					    
					    &lt;%&ndash;<div class="fr"><a href="<s:url value='/inx/jsres/forgetpasswd'/>" target="_blank">忘记密码?</a></div>&ndash;%&gt;
						<div class="fr" style="color:#fff;">忘记密码，请联系医院管理员进行重置</div>
					</div>--%>
                    <div>
                      <c:if test="${empty sysCfgMap['jsres_is_register'] or sysCfgMap['jsres_is_register'] eq 'Y' }">
                    	<input type="button" onclick="return checkForm();" class="butt_load" value="登&nbsp;&nbsp;录" />
                        <input type="button" onclick="register();" class="butt_cancel" value="注&nbsp;&nbsp;册" />
                      </c:if>
                      <c:if test="${sysCfgMap['jsres_is_register'] eq 'N' }">
                        <input type="button" onclick="return checkForm();" class="butt_load" style="width:300px; " value="登&nbsp;&nbsp;录" />
                      </c:if>
                    </div>
                    <div class="hint">最低分辨率支持：1200*800px</div>
                </div>
               </form>
			</div>
    		
    	</div>
    </div> 
</div>
<div class="ques_img">
	<img src="<s:url value='/jsp/jsres/images/wdimg.png'/>" onclick="changeWenda()">
</div>
<div class="zhongy global"><a href="http://tj.ezhupei.com:8080/pdsci/inx/tjResExam/login" target="_blank" >${pdfn:getCurrYear()}年结业技能考试准考证打印平台 >></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<s:url value='/inx/search'/>" target="_blank" >证书信息查询 >></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<s:url value='/inx/osce'/>" target="_blank" >临床技能考核 >></a></div>
<%--<div class="zhongy global"><a href="http://jszy.ezhupei.com/" target="_blank" >江苏省中医住院医师规范化培训管理平台 >></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<s:url value='/inx/pubedu'/>" target="_blank" >公共科目学习平台 >></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<s:url value='/inx/search'/>" target="_blank" >证书信息查询 >></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="<s:url value='/inx/osce'/>" target="_blank" >临床技能考核 >></a></div>--%>
<div class="news global">
	<div class="news_title"<c:if test="${infos.size()<=2 and infos.size()>0}">style="height: 300px"</c:if>
	<c:if test="${infos.size()==0}">style="height: 54px"</c:if>>
    	<p>通知公告</p>
	       <c:forEach items="${infos}" var="info">
      			<div class="news_con fl news_con1" style="margin:6px;">
		      	<h1><a href="<s:url value='/inx/jsres/noticeView'/>?infoFlow=${info.infoFlow}" target="_blank">${pdfn:escapeHtmlTag(info.infoTitle,'18')}</a></h1>
		          <div class="data"><b>${pdfn:split(pdfn:transDate(info.infoTime),'-')[2] }</b><br /><label >${pdfn:split(pdfn:transDate(info.infoTime),'-')[0] }</label>-<label>${pdfn:split(pdfn:transDate(info.infoTime),'-')[1] }</label></div>
		          <div class="info wrap">${pdfn:escapeHtmlTag(info.infoContent,'50') }</div>
	     		</div>
			</c:forEach>
        <div class="more" <c:if test="${empty infos }">style="display: none;"</c:if>>
        	<div class="more more1"><a href="<s:url value='/inx/jsres/allNotice'/>" target="_blank">更多+</a></div>
        </div>
    </div>
	<div class="news_title"<c:if test="${messages.size()<=2 and messages.size()>0}"></c:if>
		<c:if test="${messages.size()==0}">style="height: 54px"</c:if>>
		<p>招录信息</p>
		<c:forEach items="${messages}" var="message">
			<div class="news_con fl news_con2" style="margin:6px;">
				<h1><a href="<s:url value='/inx/jsres/messageView'/>?messageFlow=${message.messageFlow}" target="_blank">${pdfn:escapeHtmlTag(message.messageTitle,'18')}</a></h1>
				<div class="data"><b>${pdfn:split(pdfn:transDate(message.modifyTime),'-')[2] }</b><br /><label >${pdfn:split(pdfn:transDate(message.modifyTime),'-')[0] }</label>-<label>${pdfn:split(pdfn:transDate(message.modifyTime),'-')[1] }</label></div>
				<div class="info wrap">${pdfn:escapeHtmlTag(message.messageContent,'50') }</div>
			</div>
		</c:forEach>
		<div class="more" <c:if test="${empty messages }">style="display: none;"</c:if>>
			<div class="more more2"><a href="<s:url value='/inx/jsres/allMessage'/>" target="_blank">更多+</a></div>
		</div>
	</div>
	<div style="clear:both;"></div>
</div>
<div class="global">
	<div class="module">
    	<p>功能模块</p>
        <div class="modu_con">
        	<ul>
            	<li class="bmzl"><h2>报名招录</h2><br />充分利用信息化手段，极为方便的对招生的有关数据进行管理、维护、查找等有关操作，使得繁杂的信息数据能够具体化、直观化、合理化，较大程度上提高工作的效率和准确性。</li>
                <li class="gcgl"><h2>过程管理</h2><br />培训过程管理侧重于培训目标、方法、内容的具体落实细化，实时、准确、快捷的记录住院医师完成的病种、技能操作、手术任务、学习记录、考核成绩等信息，形成一套完整的电子培养登记手册。</li>
                <li class="jygl"><h2>结业管理</h2><br />通过对培训医师个人信息、培训信息、培训手册的分析，系统可初始生成具备结业考核资格人员信息；通过结业理论、技能考核成绩的统计分析，输出结业考核通过合格的人员信息，并生成电子培训合格证书。</li>
                <li class="tjcx"><h2>统计查询</h2><br />通过对数据进行收集、整理、分析，系统可实时的存储、统计、输出各类报表数据。使卫生机构管理者通过本系统能够实时了解住院医师轮转培训各阶段情况，及时发现问题，提升提高管理效率。</li>
            </ul>
        </div>
    </div>	
</div>
<div class="link">
   <div class="scroll-box">
   		<p>友情链接</p>
        <div class="picScroll">
			<ul>
				<li><a href="http://www.nhfpc.gov.cn/"  target="_blank"><img src="<s:url value='/jsp/inx/tjres/images/link1.png'/>" /></a></li>
				<li><a href="http://www.tjwsj.gov.cn/html/WSJn/portal/index/index.htm"  target="_blank"><img src="<s:url value='/jsp/inx/tjres/images/link2.png'/>" /></a></li>
				<li><a href="http://www.njpdxx.com/"  target="_blank"><img src="<s:url value='/jsp/inx/tjres/images/link3.png'/>" /></a></li>
				<li><a href="http://www.tj.gov.cn/"  target="_blank"><img src="<s:url value='/jsp/inx/tjres/images/link4.png'/>" /></a></li>
				<li><a href="http://www.tjmugh.com.cn/"  target="_blank"><img src="<s:url value='/jsp/inx/tjres/images/link5.png'/>" /></a></li>
				<li><a href="http://www.wjyxyfy.com/"  target="_blank"><img src="<s:url value='/jsp/inx/tjres/images/link6.png'/>" /></a></li>
				<li><a href="http://www.tj3zx.cn/"  target="_blank"><img src="<s:url value='/jsp/inx/tjres/images/link7.png'/>" /></a></li>
				<li><a href="http://www.yd2y.com.cn/"  target="_blank"><img src="<s:url value='/jsp/inx/tjres/images/link8.png'/>" /></a></li>
			</ul>
			<a class="prev"></a>
			<a class="next"></a>
		</div>
		<script type="text/javascript">jQuery(".picScroll").slide({ mainCell:"ul",autoPlay:true,effect:"left", vis:4, scroll:3, autoPage:true, pnLoop:false });</script>
    </div>
</div>
 <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
		<jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
	</c:if>
<div class="footer_index">
	<%--<div align="center">--%>
		<%--<img src="<s:url value='/jsp/jsres/images/count.png'/>" />&#12288;总访问量：<font color="red">${count}</font>&#12288;当前访问量：<font color="red">${pdfn:getOnlineUserCount()}</font>--%>
	<%--</div>--%>
	主管单位：天津市卫生健康委员会   |  技术支持：<a href="http://www.njpdxx.com/" style="color:#fff;" target="_blank">南京品德网络信息技术有限公司</a>   |  <a href="https://beian.miit.gov.cn/" target="_blank" style="color:#FFFFFF;">工信部备案号：苏ICP备14054231号-1</a><br />
		<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1260790632'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s95.cnzz.com/z_stat.php%3Fid%3D1260790632%26online%3D1%26show%3Dline' type='text/javascript'%3E%3C/script%3E"));</script>
</div>
</body>
<%--<script src="https://s95.cnzz.com/z_stat.php?id=1260790632&web_id=1260790632" language="JavaScript"></script>--%>
</html>