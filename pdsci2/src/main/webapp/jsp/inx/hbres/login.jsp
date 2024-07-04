<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style>#cnzz_stat_icon_1273185525 a {width: 100%;color: #fff;font-size: 12px;cursor: default;padding-top: 8px;}</style>
<script>
$(function(){
	var weekDate = '星期'+'日一二三四五六'.charAt(new Date().getDay());
	$("#weekDateSpan").html(weekDate);
});

$(function(){
    var pwd = $("#placepwd");
    var password = $("#userPasswd");
    if (navigator.appName.indexOf("Microsoft Internet Explorer")>-1) {<%--处理ie浏览器placeholder和password的不兼容问题--%>
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
    <c:if test="${recruitCfg != null && recruitCfg.registrationBeginDate<=currDate && recruitCfg.registrationEndDate>=currDate}">
        jboxConfirm("当前为线下招录时段，是线下招录或四证合一学员请点击“确认”",function(){
            window.location.href="<s:url value='/inx/hbres/register'/>";
        })
    </c:if>
    <c:if test="${recruitCfg == null || !(recruitCfg.registrationBeginDate<=currDate && recruitCfg.registrationEndDate>=currDate)}">
	    window.location.href="<s:url value='/inx/hbres/register'/>";
    </c:if>
}
function reloadVerifyCode(){
	$("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
}
function checkForm(){
	if($("#userCode").val()==""){
		$(".log_tip").html("用户名不能为空!");
		return false;
	}
	if($("#userPasswd").val()==""){
		$(".log_tip").html("密码不能为空!");
		return false;
	}
	if($("#verifyCode").val()==""){
		$(".log_tip").html("验证码不能为空!");
		return false;
	}
    var f=checkUser();
    if(f){
        return true;
    }else {
        return false;
    }
}
function checkUser(){
    var userCode = $("#userCode").val();
    var url = "<s:url value='/inx/hbres/checkUserCodeInBlack'/>";
    var data = {userCode:userCode};
    jboxGet(url,data,
        function(resp){
            if(resp != "" && resp != null && typeof(resp) != 'undefined'){
                var height=(window.screen.height)*0.3;
                var width=(window.screen.width)*0.5;
                jboxOpenContent(resp,"提示信息",width,height,true);
                return false;
            }else {
                $("#loginForm").submit();
                return true;
            }
        }, null, false
    );
}

function showQr(type){
    $(".QR").hide();
    $(".QR_"+type).show();
}
function hideThis(type){
    $(".QR_"+type).hide();
}
</script>
</head>
<body>
<div class="hb_CN">
    <div class="index_header">
      <div class="header_box">
        <h1 class="fl">${pdfn:getCurrDate() }&nbsp;&nbsp;<font id="weekDateSpan"></font></h1>
          <div class="code fr">
              <div class="android" onmouseover="showQr('android');" onmouseout="hideThis('android')"></div>
              <div class="iphone" onmouseover="showQr('iphone');" onmouseout="hideThis('iphone')"></div>
              <font class="fr">轮转手册登记请扫描二维码</font>
              <img class="Q_hand fr" src="<s:url value='/jsp/hbres/images/Q_hand.png'/>" onerror="javascript:this.src='<s:url value="/jsp/hbres/images/Q_hand.png"/>';this.onerror=null;" />
              <div class="QR_iphone QR" style="display: none;">
                  <img class="QR_code" src="<s:url value='/jsp/hbres/images/download.png'/>" onerror="javascript:this.src='<s:url value="/jsp/hbres/images/download.png"/>';this.onerror=null;" />
              </div>
              <div class="QR_android QR" style="display: none;">
                  <img class="QR_code" src="<s:url value='/jsp/hbres/images/weixin_hbres.png'/>" onerror="javascript:this.src='<s:url value="/jsp/hbres/images/weixin_hbres.png"/>';this.onerror=null;" />
              </div>
          </div>
      </div>
    </div>
    <div class="content global">
        <div class="banner">
           <div class="banner_box">
             <div class="banner_logo"><img src="<s:url value='/jsp/hbres/images/banner_logo.png'/>" onerror="javascript:this.src='<s:url value="/jsp/hbres/images/banner_logo.png"/>';this.onerror=null;" /></div>
                 <div class="mainlogin">
                    <form id="loginForm" action="<s:url value='/inx/hbres/login'/>" method="post">
                        <table class="logintb" border="0" cellpadding="0" cellspacing="0">
                              <tbody>
                                  <tr><td height="50px;" align="center">用户登录</td></tr>
                                  <tr>
                                    <td height="40px"><span class="username"><i></i><input type="text" class="loginsr" id="userCode" name="userCode" style="width:202px;" placeholder="用户名/手机号" value=""></span></td>
                                  </tr>
                                  <tr><td height="12px"></td></tr>
                                  <tr>
                                      <%--onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false"--%>
                                    <td height="40px"><span class="password"><i></i><input type="text" class="loginsr" id="placepwd" style="width:202px;display: none;" placeholder="密码" value=""/><input type="password" class="loginsr" id="userPasswd" style="width:202px;" placeholder="密码" name="userPasswd" value=""/></span></td>
                                  </tr>
                                  <tr><td height="12px"></td></tr>
                                  <tr>
                                    <td height="40px" class="logintb_td">
                                        <span class="hbVerifyCode">
                                            <div style="float: left;">
                                                <i></i><input type="text" style="width:130px;" class="loginsr_yz" id="verifyCode" name="verifyCode" placeholder="验证码" value="">
                                            </div>
                                            <div style="float: right;">
                                                <img id="verifyImage" src="<s:url value='/captcha'/>" style="cursor:pointer;margin-right: -40px;" height="40px" width="100px" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" />
                                            </div>
                                        </span>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td height="50px" class="log_tips">
                                    <font class="log_tip red fl">
                                    <c:if test="${not empty loginErrorMessage}">
                                                        登录失败：${loginErrorMessage}
                                    </c:if>
                                    </font>
                                    <span class="fr"><a href="<s:url value='/inx/hbres/forgetpasswd'/>" target="_blank">忘记密码？</a></span></td>
                                  </tr>
                                  <tr>
                                    <td valign="top">
                                      <button class="btn_login" type="submit" onclick="return checkForm();">登&nbsp;&nbsp;录</button>&nbsp;
                                      <button class="btn_login" type="button" onclick="register();">立即注册</button>
                                    </td>
                                  </tr>
                            </tbody>
                        </table>
                    </form>
              </div>
           </div>
        </div>
    </div>
    <div class="notice_login">
        <c:if test="${applicationScope.sysCfgMap['online_service']=='Y'}">
            <jsp:include page="/jsp/service.jsp" flush="true"></jsp:include>
        </c:if>
       <h1 class="index_title">系统公告<p>Announcement</p></h1>
        <%-- 临时处理 by ma --%>
       <div class="notice_bd">
           <c:forEach items="${infos}" var="info">
               <dl>
                <dt><h1>${pdfn:split(pdfn:transDate(info.infoTime),'-')[2] }</h1>
                <h2>${pdfn:split(pdfn:transDate(info.infoTime),'-')[0] }.${pdfn:split(pdfn:transDate(info.infoTime),'-')[1] }</h2></dt>
                <dd>
                  <h1><a href="<s:url value='/inx/hbres/noticeview'/>?infoFlow=${info.infoFlow}" target="_blank">${info.infoTitle}</a></h1>
                  <h2>${pdfn:escapeHtmlTag(info.infoContent,'50') }</h2>
                </dd>
              </dl>
           </c:forEach>
       </div>

        <%--<div class="notice_bd">--%>
            <%--<dl>--%>
                <%--<dt><h1>04</h1>--%>
                    <%--<h2>2018.04</h2></dt>--%>
                <%--<dd>--%>
                    <%--<h1><a href="/pdsci/inx/hbres/notice/view?infoFlow=441ddb91f9fd4e8d9b45a26254779f39" target="_blank">【重要通知】关于2018年度湖北省住院医师规范化培训招录考试的通知</a></h1>--%>
                    <%--<h2>招录考试的通知</h2>--%>
                <%--</dd>--%>
            <%--</dl>--%>
            <%--<dl>--%>
                <%--<dt><h1>26</h1>--%>
                    <%--<h2>2018.03</h2></dt>--%>
                <%--<dd>--%>
                    <%--<h1><a href="/pdsci/inx/hbres/notice/view?infoFlow=1b0af1e6c94c45678cd3494d85f4a3ee" target="_blank">新学员注册报名操作指南及常见问题</a></h1>--%>
                    <%--<h2>新学员注册报名操作指南</h2>--%>
                <%--</dd>--%>
            <%--</dl>--%>
            <%--<dl>--%>
                <%--<dt><h1>26</h1>--%>
                    <%--<h2>2018.03</h2></dt>--%>
                <%--<dd>--%>
                    <%--<h1><a href="/pdsci/inx/hbres/noticeview?infoFlow=b8ba846f1a354a05a6b2cbc8b26a1275" target="_blank">2018年湖北省住院医师规范化培训专业基地招录计划容量表</a></h1>--%>
                    <%--<h2>2018年住培招生通知附件.xlsx</h2>--%>
                <%--</dd>--%>
            <%--</dl>--%>
            <%--<dl>--%>
                <%--<dt><h1>22</h1>--%>
                    <%--<h2>2018.03</h2></dt>--%>
                <%--<dd>--%>
                    <%--<h1><a href="/pdsci/inx/hbres/noticeview?infoFlow=4858a534f9084313be8ee38bcf4febc6" target="_blank">关于2018年度湖北省住院医师规范化培训（西医）招录工作的通知</a></h1>--%>
                    <%--<h2>根据国家和湖北省住院医师规范化培训等文件精神，湖北省2018年度住院医师规范化培训（西医）招录工作于3月份......</h2>--%>
                <%--</dd>--%>
            <%--</dl>--%>
            <%--<dl>--%>
                <%--<dt><h1>08</h1>--%>
                    <%--<h2>2017.05</h2></dt>--%>
                <%--<dd>--%>
                    <%--<h1><a href="/pdsci/inx/hbres/noticeview?infoFlow=ca7a253c07ec43be863c05ed64c74ea1" target="_blank">2017年湖北省住院医师规范化培训各基地招生一览表</a></h1>--%>
                    <%--<h2>2017湖北省住院医师各基地招生一览表.xls</h2>--%>
                <%--</dd>--%>
            <%--</dl>--%>
            <%--<dl>--%>
                <%--<dt><h1>03</h1>--%>
                    <%--<h2>2017.05</h2></dt>--%>
                <%--<dd>--%>
                    <%--<h1><a href="/pdsci/inx/hbres/noticeview?infoFlow=011951e542584d4592e6d86d310e0e20" target="_blank">武汉中美全科住院医师规范化培训试点项目招生简章</a></h1>--%>
                    <%--<h2>武汉中美全科住院医师规范化培训试点项目招生简章-最终版-2017-05-02.docx</h2>--%>
                <%--</dd>--%>
            <%--</dl>--%>
        <%--</div>--%>

        <div class="notice_more"><a href="<s:url value='/inx/hbres/noticelist'/>" target="_blank">更多</a></div>
    </div>
    
    <div class="function_box">
       <div class="function"> 
          <h1>功能模块<p>Function</p></h1>
          <ul class="function_hd" id="itags">
            <li class="function_select"><a onclick="selectTag('tagContent0',this)" href="javascript:void(0)">报名招录</a></li>
            <li class="function_ui"><a onclick="selectTag('tagContent1',this)" href="javascript:void(0)">过程管理</a></li>
            <li class="function_ui"><a onclick="selectTag('tagContent2',this)" href="javascript:void(0)">结业管理</a></li>
            <li class="function_ui"><a onclick="selectTag('tagContent3',this)" href="javascript:void(0)">统计查询</a></li> 
          </ul>
          <div class="function_bd bmzl"  id="tagContent0" style="display:block;">
            <dl>
              <dt class="name"><span class="bmzl_zc"></span>在线信息注册</dt>
              <dd>线上完成个人信息注册，获取招录考核资格初审条件</dd> 
            </dl>
            <dl>
              <dt class="name"><span class="bmzl_zkzdy"></span>准考证打印</dt>
              <dd>线上完成准考证打印，获取参加招录考核资格</dd> 
            </dl>
            <dl>
              <dt class="name"><span class="bmzl_zyxxtb"></span>志愿信息填报</dt>
              <dd>线上完成个人志愿填报，获取志愿基地考核资格</dd> 
            </dl>
           <dl>
              <dt class="name"><span class="bmzl_xxgd"></span>录取信息归档</dt>
              <dd>录取人员信息自动汇总、归档，便捷、高效</dd> 
           </dl>
          </div>
          
         <div class="function_bd gcgl" id="tagContent1">
            <dl>
              <dt class="name"><span class="gcgl_lzap"></span>轮转安排</dt>
              <dd>住院医师轮转计划安排</dd> 
            </dl>
            <dl>
              <dt class="name"><span class="gcgl_sjdj"></span>数据登记</dt>
              <dd>住院医师个人培训数据登记</dd> 
            </dl>
            <dl>
              <dt class="name"><span class="gcgl_sxkp"></span>双向考评</dt>
              <dd>住院医师与带教老师、培训科室之前双向互评</dd> 
            </dl>
           <dl>
              <dt class="name"><span class="gcgl_ckkh"></span>出科考核</dt>
              <dd>住院医师轮转科室出科考核</dd> 
           </dl>
           <dl>
              <dt class="name"><span class="gcgl_dzsc"></span>电子手册</dt>
              <dd>住院医师个人培训轮转手册生成</dd> 
           </dl>
         </div>
         
         <div class="function_bd bmzl" id="tagContent2">
           <dl>
              <dt class="name"><span class="jygl_zgsc"></span>结业资格审查</dt>
              <dd>审查医师结业考核资格信息</dd> 
            </dl>
            <dl>
              <dt class="name"><span class="jygl_khgl"></span>结业考核管理</dt>
              <dd>结业考核（公共课、理论、技能）组织安排</dd> 
            </dl>
            <dl>
              <dt class="name"><span class="jygl_cjcx"></span>结业成绩查询</dt>
              <dd>医师结业考核（公共课、理论、技能）成绩查询</dd> 
            </dl>
           <dl>
              <dt class="name"><span class="jygl_zscx"></span>结业证书查询</dt>
              <dd>医师个人证书信息查询</dd> 
           </dl>  
         </div>
         
         <div class="function_bd tjcx" id="tagContent3">
            <dl class="tjcx_first">
              <dt class="name"><span class="tjcx_lnsjcx"></span>历年数据查询</dt>
              <dd>查询全省各基地历年招收、在培、结业医师人数信息</dd> 
            </dl>
           <dl>
              <dt class="name"><span class="tjcx_ysxxcx"></span>医师信息查询</dt>
              <dd>医师数据溯源，查询医师从报名至结业，整个培训过程中的数据信息</dd> 
           </dl>   
         </div>
          <script type="text/javascript">
            function selectTag(showContent, selfObj) {
                // 操作标签
                var tag = document.getElementById("itags").getElementsByTagName("li");
                var taglength = tag.length;
                for (i = 0; i < taglength; i++) {
                    tag[i].className = "function_ui";
                }
                selfObj.parentNode.className = "function_select";
                // 操作内容
                for (i = 0; j = document.getElementById("tagContent" + i); i++) {
                    j.style.display = "none";
                }
                document.getElementById(showContent).style.display = "block";
            }
          </script>
       </div>
    </div>
    <div class="link">
      <h1 class="index_title">友情链接<p>Link</p></h1>
      <div class="link_bd">
      	<ul>
			<li>
			  <div style="display:inline-block;">
				<dl>
		          <dt><img src="<s:url value='/jsp/hbres/images/logo_cn.png'/>" onerror="javascript:this.src='<s:url value="/jsp/hbres/images/logo_cn.png"/>';this.onerror=null;" /></dt>
		          <dd>国家卫生和计划生育委员会<span><a href="<s:url value='http://www.nhfpc.gov.cn/'/>" target="_blank">查看</a></span></dd>
		        </dl>
		        </div>
			</li>
			<li>
			<div style="display:inline-block;">
				<dl>
		          <dt><img src="<s:url value='/jsp/hbres/images/logo_cn.png'/>" onerror="javascript:this.src='<s:url value="/jsp/hbres/images/logo_cn.png"/>';this.onerror=null;" /></dt>
		          <dd>湖北省卫生计生委<span><a href="<s:url value='http://www.hbwsjs.gov.cn/'/>" target="_blank">查看</a></span></dd>
		        </dl>
		        </div>
			</li>
			<li>
			<div style="display:inline-block;">
				<dl>
		          <dt><img src="<s:url value='/jsp/hbres/images/logo_hbyxh.png'/>" onerror="javascript:this.src='<s:url value="/jsp/hbres/images/logo_hbyxh.png"/>';this.onerror=null;" /></dt>
		          <dd>湖北省医学会<span><a href="<s:url value='http://www.hbma.org.cn/index.asp'/>" target="_blank">查看</a></span></dd>
		        </dl>
		        </div>
			</li>
			<li>
			<div style="display:inline-block;">
				<dl>
		          <dt><img src="<s:url value='/jsp/hbres/images/logo_whxh.png'/>" onerror="javascript:this.src='<s:url value="/jsp/hbres/images/logo_whxh.png"/>';this.onerror=null;" /></dt>
		          <dd>武汉协和<span><a href="<s:url value='http://www.whuh.com/index.php'/>" target="_blank">查看</a></span></dd>
		        </dl>
		        </div>
			</li>
		</ul>
		<%--<div class="link_next">--%>
	      <%--<a><span class="prev"></span></a>--%>
	      <%--<a><span class="next"></span></a>--%>
	    <%--</div>--%>
      </div>
      <script type="text/javascript">jQuery(".link_bd").slide({ mainCell:"ul",autoPlay:true,effect:"left", vis:4, scroll:3, autoPage:true, pnLoop:false });</script>
    </div>
    <div class="index_footer" style="margin-top: 30px;">
        <div align="center" style="font-size: 15px;">
            <%--<img src="<s:url value='/jsp/jsres/images/count.png'/>" />&#12288;总访问量：<font color="red">${count}</font>&#12288;当前访问量：<font color="red">${pdfn:getOnlineUserCount()}</font>--%>
            <%--<br/><br/>主管单位：湖北省卫生和计划生育委员会科教处   |  技术支持：南京品德网络信息技术有限公司--%>
                主管单位：湖北省卫生和计划生育委员会科教处   |  技术支持：<a href="http://www.njpdxx.com/" style="color:#fff;" target="_blank">南京品德网络信息技术有限公司</a><br /><br />
                <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1273185525'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s13.cnzz.com/z_stat.php%3Fid%3D1273185525%26online%3D1%26show%3Dline' type='text/javascript'%3E%3C/script%3E"));</script>
        </div>
      <div class="footer_box">
      </div>
    </div>
  </div>

</body>
</html>
