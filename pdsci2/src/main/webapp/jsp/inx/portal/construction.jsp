<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>湖北省住院医师规范化培训公众服务平台</title>
    <jsp:include page="/jsp/inx/portal/htmlhead_portal.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="bootstrap" value="true"/>
    </jsp:include>
    <script>
        $(function () {
            $(".header-nav ul li a").each(function () {
                var href =  $(this).attr("href");
                var columnId = "${column.columnId}".substr(0,4);
                if(columnId != "" && href.indexOf(columnId) != -1){
                    $(this).addClass("pick");
                    $("#shouye").removeClass("pick");
                }
            });
        });
        function showQr1(type){
            $("#"+type).show();
        }
        function hideThis1(type){
            $("#"+type).hide();
        }
    </script>
</head>
<body>
  <div class="header">
    <div class="manage-web">
        <%--<a href="<s:url value='/loginPortal'/>">网站管理</a>--%>
    </div>
    <div class="header-nav">
        <ul class="clearfix">
            <li class="pick"><a id = "shouye"  href="<s:url value='/inx/portal/'/>" >首页</a></li>
            <li class="dropdown">
                <a href="<s:url value='/inx/portal/loadInfoList?columnId=LM07'/>">
                    新闻中心
                    <img src="<s:url value='/jsp/inx/portal/images/arrow-icon-down.png'/>" class="dropdown-toggle" data-toggle="dropdown">
                </a>
                <ul class="dropdown-menu">
                    <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM0701'/>">国家新闻</a></li>
                    <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM0702'/>">省级新闻</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="<s:url value='/inx/portal/loadInfoList?columnId=LM01'/>">
                    通知公告
                    <img src="<s:url value='/jsp/inx/portal/images/arrow-icon-down.png'/>" class="dropdown-toggle" data-toggle="dropdown">
                </a>
                <ul class="dropdown-menu">
                    <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM0101'/>">省级公告</a></li>
                    <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM0102'/>">基地公告</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a href="<s:url value='/inx/portal/loadInfoList?columnId=LM02'/>" >
                    政策法规
                    <img src="<s:url value='/jsp/inx/portal/images/arrow-icon-down.png'/>" class="dropdown-toggle" data-toggle="dropdown">
                </a>
                <ul class="dropdown-menu">
                    <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM0201'/>">国家政策</a></li>
                    <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM0202'/>">省级政策</a></li>
                </ul>
            </li>
            <li  class="dropdown">
                <a href="<s:url value='/inx/portal/loadInfoList?columnId=LM03'/>">
                    基地风采
                    <img src="<s:url value='/jsp/inx/portal/images/arrow-icon-down.png'/>"  class="dropdown-toggle" data-toggle="dropdown">
                </a>
                <ul class="dropdown-menu">
                    <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM0305'/>">基地信息</a></li>
                    <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM0306'/>">学员心得</a></li>
                </ul>
            </li>
            <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM04'/>">师资培训</a></li>
            <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM05'/>">下载中心</a></li>
            <li class="dropdown">
                <a href="<s:url value='/inx/portal/loadInfoList?columnId=LM06'/>">
                    公众互动
                    <img src="<s:url value='/jsp/inx/portal/images/arrow-icon-down.png'/>"  class="dropdown-toggle" data-toggle="dropdown">
                </a>
                <ul class="dropdown-menu">
                    <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM0601'/>">网上调查</a></li>
                    <li><a href="<s:url value='/inx/portal/loadInfoList?columnId=LM0602'/>">咨询投诉</a></li>
                </ul>
            </li>
            <li>
                <form action="<s:url value='/inx/portal/searchInfoList'/>" method="post">
                    <div class="input-box clearfix">
                        <input type="text" class="search-input" name ="searchInfo"  placeholder="搜索关键字">
                        <button class="search-btn" type="submit" onclick="searchInfo()"><img src="<s:url value='/jsp/inx/portal/images/search-icon.png'/>"></button>
                    </div>
                </form>
            </li>
        </ul>
    </div>
  </div>
  <div class="content-body container">
    <div class="content-left col-xs-9">
      <div class="notice-list">
        <div class="location-title">
          <img src="<s:url value='/jsp/inx/portal/images/location-icon.png'/>" class="location-icon">
          <span>您现在的位置：</span>
          <span>首页</span>
          <span>&minus;</span>
          <span>基地管理</span>
        </div>
        <div class="construct-content">
          <img src="<s:url value='/jsp/inx/portal/images/construction.png'/>">
        </div>
      </div>
    </div>
    <div class="content-right col-xs-3">
      <div class="right-item1">
        <div class="item-title">
          <a href="javascript:;" class="login-Ltitle">住培平台登录</a>
            <a href="<s:url value='/inx/hbres/register'/>" class="login-Rtitle">住培平台注册</a>
        </div>
          <div class="login-content">
              <form id="loginForm" action="<s:url value='/inx/hbres/login'/>" method="post">
                  <div class="login-item-box">
                      <!-- <img src="<s:url value='/jsp/inx/portal/images/icon-user.png'/>"> -->
                      <span class="spanIndex1"></span>
                      <input id="userCode" name="userCode" type="text" class="loginInput1" placeholder="用户名／手机号">
                  </div>
                  <div class="login-item-box">
                      <span class="spanIndex2"></span>
                      <input  id="userPasswd" name="userPasswd" type="password" class="loginInput2" placeholder="密码">
                  </div>
                  <div class="login-item-box">
                      <span class="spanIndex3"></span>
                      <input  id="verifyCode" name="verifyCode" placeholder="验证码" value="" type="text" class="loginInput3" placeholder="验证码">
                      <a href="javascript:;" class="yzm"><img id="verifyImage" src="<s:url value='/captcha'/>" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" ></a>
                  </div>
                  <div class="forget-pwd clearfix">
                <span class="point-info">
                     <c:if test="${not empty loginErrorMessage}">
                         登录失败：${loginErrorMessage}
                     </c:if>
                </span>
                      <a href="<s:url value='/inx/hbres/forgetpasswd'/>" class="link-forget">忘记密码？</a>
                  </div>
                  <div class="login-item-box">
                      <button  type="submit" onclick="return checkForm();" class="login-btn">登录</button>
                  </div>
              </form>
          </div>
      </div>
      <div class="right-item2">
        <div class="item-Rtitle">
          <div class="item-Rtitle-left">
            <div class="title-icon"><img src="<s:url value='/jsp/inx/portal/images/base-icon.png'/>"></div>
            <div class="title-txt">基地列表</div>
          </div>
         
        </div>
          <div class="item-content2">
              <ul>
                  <c:forEach items="${lm03List}" var="lm03">
                      <li>
                          <a class="item-Clist" href="<s:url value='/inx/portal/loadInfo?infoFlow=${lm03.infoFlow}'/>">
                              <div class="circle"></div>
                              <div class="item-Lcontent">
                                  <span class="item-Ltitle"> ${pdfn:cutString(lm03.infoTitle,17,true,3)}</span>
                              </div>
                          </a>
                      </li>
                  </c:forEach>
                  <c:if test="${empty lm03List}">
                      <li>无记录！</li>
                  </c:if>
              </ul>
          </div>
      </div>
    </div>
  </div>
  <div class="footer">
    <div class="footer-content">
      <div class="footer-left">
          <div class="footer-row">&#12288;</div>
          <div class="footer-row"><span>为了达到更好的用户体验，建议您尽量使用谷歌浏览器进行登录&#12288;<a  style="color:blue;" target="_blank" href="${sysCfgMap['upload_base_url']}/chromeFile/chrome.exe">谷歌浏览器下载</a></span></div>
          <div class="footer-row">
              <span>主管单位：</span>
              <span>湖北省卫生和计划生育委员会</span>
          </div>
          <div class="footer-row">
              <span>技术支持：</span>
              <span>南京品德科技有限责任公司 &nbsp; 工信部备案号：苏ICP 备14054231号</span>
          </div>
          <%-- <div class="footer-row">
             <span>站长统计：</span>
             <span>今日IP</span>
             <span>[967] | </span>
             <span>今日PV</span>
             <span>[967] | </span>
             <span>昨日IP</span>
             <span>[967] | </span>
             <span>昨日PV</span>
             <span>[967] | </span>
             <span>当前在线</span>
             <span>[31]</span>
           </div>
           <div class="footer-row">
             <span>相关链接：</span>
             <select name="" id="" title="各省、市卫生计生委" class="select-icon">
               <option value="">各省、市卫生计生委</option>
             </select>
             <select name="" id="" title="市州卫生计生委" class="select-icon">
               <option value="">市州卫生计生委</option>
             </select>
             <select name="" id="" title="部省属医疗卫生机构" class="select-icon">
               <option value="">部省属医疗卫生机构</option>
             </select>
             <select name="" id="" title="子网链接" class="select-icon">
               <option value="">子网链接</option>
             </select>
             <select name="" id="" title="常用链接" class="select-icon">
               <option value="">常用链接</option>
             </select>
           </div>--%>
      </div>
      <%--<div class="footer-right">--%>
        <%--<div class="ewm-box">--%>
          <%--<span>APP下载</span>--%>
          <%--<div class="ewm-bg">--%>
            <%--<img src="<s:url value='/jsp/inx/portal/images/download.png'/>">--%>
          <%--</div>--%>
        <%--</div>--%>
        <%--<div class="ewm-box">--%>
          <%--<span>微信公众号</span>--%>
          <%--<div class="ewm-bg">--%>
            <%--<img src="<s:url value='/jsp/inx/portal/images/weixin_hbres.png'/>">--%>
          <%--</div>--%>
        <%--</div>--%>
      <%--</div>--%>
    </div>
  </div>
  <div class="ol-service">
    <div class="olService-left">
      <img src="<s:url value='/jsp/inx/portal/images/kefu-icon.png'/>">
      <div class="olService-title">在线客服</div>
    </div>
    <div class="olService-right">
      <ul>
        <li>
          <img src="<s:url value='/jsp/inx/portal/images/zqq.png'/>">
          <span>2885400153</span>
        </li>
        <li>
          <img src="<s:url value='/jsp/inx/portal/images/zqq.png'/>">
          <span>2885400156</span>
        </li>
        <li>
          <img src="<s:url value='/jsp/inx/portal/images/zphone.png'/>">
          <span>027-87185108</span>
        </li>
        <li>
          <img src="<s:url value='/jsp/inx/portal/images/zphone.png'/>">
          <span>027-87185208</span>
        </li>
      </ul>
    </div>
  </div>
  <div class="ol-service1">
      <div class="olService-left1" onmouseover="showQr1('QRAPP');" onmouseout="hideThis1('QRAPP')">
          <img src="<s:url value='/jsp/inx/portal/images/app_icon.png'/>">
      </div>
      <div class="QR_portalWX" id="QRAPP" style="display: none;">
          <img src="<s:url value='/jsp/inx/portal/images/download.png'/>" width="123" height="123">
      </div>
  </div>
  <div class="ol-service2">
      <div class="olService-left1" onmouseover="showQr1('QRWX');" onmouseout="hideThis1('QRWX')">
          <img src="<s:url value='/jsp/inx/portal/images/wx_icon.png'/>">
      </div>
      <div class="QR_portalWX" id="QRWX" style="display: none;">
          <img src="<s:url value='/jsp/inx/portal/images/weixin_hbres.png'/>" width="123" height="123">
      </div>
  </div>
</body>
</html>