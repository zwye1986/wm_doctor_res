<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>江苏省中医消化病临床医学研究系统</title>
  <jsp:include page="/jsp/inx/jsszPortal/htmlhead_portal.jsp" flush="true">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="bootstrap" value="true"/>
  </jsp:include>
  <script>
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
    function reloadVerifyCode(){
      $("#verifyImage").attr("src","<s:url value='/captcha'/>?random="+Math.random());
    }
    function checkForm(type){
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
      if(type=='stage'){
        $("#successLoginPage").val("redirect:/inx/jsszPortal")
        $("#loginForm").submit();
      }else{
        $("#successLoginPage").val("redirect:/main?time="+new Date());
        window.location.href="<s:url value='/main?time='/>"+new Date();
      }
    }
    function logout(){
      window.location.href="<s:url value='/logout.do'/>";
    }
    function doClose(){
      window.opener = null;
      window.open("about:blank","_self").close()
    }

  </script>
</head>
<body>
  <div class="body-box">
    <div class="header">
      <div class="header-nav clearfix">
        <div class="header-nav-logo clearfix">
          <div class="top-left-logo">
            <img src="<s:url value='/jsp/inx/jsszPortal/images/top-left-logo.png'/>" >
          </div>
          <div class="top-right-logo">
            <img src="<s:url value='/jsp/inx/jsszPortal/images/logo.png'/>" >
            <img src="<s:url value='/jsp/inx/jsszPortal/images/banner-txt.png'/>">
          </div>
        </div>
        <ul class="header-navUl clearfix">
          <li>
            <a href="<s:url value='/inx/jsszPortal'/>" class="pick">首页</a>
          </li>
          <li>
            <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM09'/>">通知公告</a>
          </li>
          <li>
            <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM10'/>">动态新闻</a>
          </li>
          <li>
            <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM11'/>">消化病知识</a>
          </li>
          <li>
            <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM12'/>">公益活动</a>
          </li>
          <li>
            <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM13'/>">工作动态</a>
          </li>
          <li>
            <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM14'/>">下载中心</a>
          </li>
          <li>
            <a href="<s:url value='/inx/jsszPortal/communicationMainList'/>">医患交流</a>
          </li>
          <li class="dropdown">
            <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">系统平台</a>
            <ul class="dropdown-menu">
              <li><a target="_blank" href="http://192.168.2.91:8090/">江苏省中医院脾胃病中医药知识库平台</a></li>
              <li><a target="_blank" href="http://61.155.106.62:7070/pdsci/">江苏省中医院脾胃病中医药临床研究科研信息、数据管理平台</a></li>
              <li><a target="_blank" href="http://61.155.106.62:5050/login/">江苏省中医院脾胃病慢病管理平台</a></li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="banner clearfix">
        <div class="banner-login">
          <c:choose>
          <c:when test="${empty sessionScope.currUser }">
          <div class="login-title">登录</div>
          <form id="loginForm" action="<s:url value='/inx/jsszPortal/login'/>" method="post">
          <input type="hidden" name="successLoginPage" id="successLoginPage">
          <input type="hidden" name="type" id="type">
          <div class="login-itemBox">
            <span class="loginLogo1"></span>
            <input id="userCode" name="userCode" type="text" class="loginInput" placeholder="用户名">
          </div>
          <div class="login-itemBox">
            <span class="loginLogo2"></span>
            <input id="userPasswd" name="userPasswd" type="password" class="loginInput" placeholder="密码">
          </div>
          <div class="login-itemBox no-margin">
            <span class="loginLogo3"></span>
            <input id="verifyCode" name="verifyCode" type="text" class="loginInput yzmInput" placeholder="验证码">
            <a href="javascript:;" class="yzm">
              <img id="verifyImage" src="<s:url value='/captcha'/>" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张">
            </a>
          </div>
          <div class="forgetPwd clearfix">
            <span class="info">
               <c:if test="${not empty loginErrorMessage}">
                 登录失败：${loginErrorMessage}
               </c:if>
            </span>
            <a href="javascript:;" class="forgetPwd-txt">
              <span class="forgetPwd-txt">忘记密码？</span>
              <div class="tip top">请联系管理员</div>
            </a>
          </div>
          </form>
          <div class="btn-box clearfix">
            <button type="button" onclick="checkForm('stage');" class="login-btn">登录</button>
          </div>
          </c:when>
          <c:otherwise>
          <div class="login-title">登录</div>
          <div class="userBox">
            <div class="user">${sessionScope.currUser.userName}</div>
            <div class="userBox-info">您好，欢迎登录本系统！</div>
            <div class="userBox-btnBox clearfix" style="text-align:center;">
              <button type="button" class="login-btn" id="logout" onclick="logout()" ${param.glyFlag?"style='width:48%'":""}>退出</button>
              <c:if test="${param.glyFlag}">
                <button type="button" onclick="checkForm('back');" class="login-btn login-info" style="width:48%">网站管理</button>
              </c:if>
            </div>
          </div>
          </c:otherwise>
          </c:choose>
        </div>
      </div>
      
    </div>
    <div class="content-body">
      <div class="content-header">
        <%--<div class="search-box">--%>
          <%--<img src="<s:url value='/jsp/inx/jsszPortal/images/search-left.png'/>" class="search-img">--%>
          <%--<span>本站搜索</span>--%>
          <%--<input class="search-input" type="text" placeholder="输入标题关键字">--%>
          <%--<img src="<s:url value='/jsp/inx/jsszPortal/images/search-icon.png'/>" class="search-icon">--%>
        <%--</div>--%>
      </div>
      <div class="container">
        <div class="row common-row">
          <div class="clearfix module-title">
            <div class="module-Tleft">
              <span class="module-cn">通知公告</span>
            </div>
            <div class="module-Tright">
              <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM09'/>">
                <span>更多</span>
                <img src="<s:url value='/jsp/inx/jsszPortal/images/more-icon.png'/>">
              </a>
            </div>
            <div class="border-block"></div>            
          </div>
          <div class="col-xs-6 setpadding">
            <div class="list-box clearfix">
              <div class="list-left">1</div>
              <div class="list-right">
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${lm09Map["0"].infoFlow}'/>" class="list-Ctitle clearfix">
                 <div class="list-Ct_txt">${pdfn:cutString(lm09Map['0'].infoTitle,34,true,3)}</div>
                 <div class="list-Ct_date">${lm09Map['0'].infoTime}</div>
                </a>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${lm09Map["0"].infoFlow}'/>" class="list-Cbody">
                  ${pdfn:cutString(lm09Map['0'].infoContent,40,true,3)}
                </a>
              </div>
            </div>
            <div class="list-box clearfix">
              <div class="list-left">2</div>
              <div class="list-right">
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${lm09Map["1"].infoFlow}'/>" class="list-Ctitle clearfix">
                 <div class="list-Ct_txt">${pdfn:cutString(lm09Map['1'].infoTitle,34,true,3)}</div>
                 <div class="list-Ct_date">${lm09Map['1'].infoTime}</div>
                </a>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${lm09Map["1"].infoFlow}'/>" class="list-Cbody">
                  ${pdfn:cutString(lm09Map['1'].infoContent,40,true,3)}
                </a>
              </div>
            </div>
          </div>
          <div class="col-xs-6 setpadding">
            <ul class="newsUl">
              <li>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${lm09Map["2"].infoFlow}'/>" class="clearfix newsList">
                  <div class="newsListD">${lm09Map['2'].infoTime}</div>
                  <div class="newsListT">${pdfn:cutString(lm09Map['2'].infoTitle,24,true,3)}</div>
                </a>
              </li>
              <li>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${lm09Map["3"].infoFlow}'/>" class="clearfix newsList">
                  <div class="newsListD">${lm09Map['3'].infoTime}</div>
                  <div class="newsListT">${pdfn:cutString(lm09Map['3'].infoTitle,24,true,3)}</div>
                </a>
              </li>
              <li>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${lm09Map["4"].infoFlow}'/>" class="clearfix newsList">
                  <div class="newsListD">${lm09Map['4'].infoTime}</div>
                  <div class="newsListT">${pdfn:cutString(lm09Map['4'].infoTitle,24,true,3)}</div>
                </a>
              </li>
              <li>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${lm09Map["5"].infoFlow}'/>" class="clearfix newsList">
                  <div class="newsListD">${lm09Map['5'].infoTime}</div>
                  <div class="newsListT">${pdfn:cutString(lm09Map['5'].infoTitle,24,true,3)}</div>
                </a>
              </li>
              <li>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${lm09Map["6"].infoFlow}'/>" class="clearfix newsList">
                  <div class="newsListD">${lm09Map['6'].infoTime}</div>
                  <div class="newsListT">${pdfn:cutString(lm09Map['6'].infoTitle,24,true,3)}</div>
                </a>
              </li>
              <li>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${lm09Map["7"].infoFlow}'/>" class="clearfix newsList">
                  <div class="newsListD">${lm09Map['7'].infoTime}</div>
                  <div class="newsListT">${pdfn:cutString(lm09Map['7'].infoTitle,24,true,3)}</div>
                </a>
              </li>
            </ul>
          </div>
        </div>
        <div class="row common-row">
          <div class="clearfix module-title">
            <div class="module-Tleft">
              <span class="module-cn">动态新闻</span>
            </div>
            <div class="module-Tright">
              <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM10'/>">
                <span>更多</span>
                <img src="<s:url value='/jsp/inx/jsszPortal/images/more-icon.png'/>">
              </a>
            </div>
            <div class="border-block"></div>
          </div>
          <div class="col-xs-7 setpadding2">
            <ul class="newsUl2">
              <c:forEach items="${lm10List}" var="info">
              <li>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${info.infoFlow}'/>" class="clearfix newsList">
                  <div class="newsListD">【${info.infoTime}】</div>
                  <div class="newsListT2">${pdfn:cutString(info.infoTitle,28,true,3)}</div>
                </a>
                <div class="newsListIntro">
                    ${pdfn:cutString(info.infoContent,70,true,3)}
                </div>
              </li>
              </c:forEach>
            </ul>
          </div>
          <div class="col-xs-5 ">
            <div class="swiper-container swiper-container1">
              <div class="swiper-wrapper">
                <c:forEach items="${imgList}" var="img">
                  <div class="swiper-slide">
                    <a href="javascript:;"><img src="${img.url}"></a>
                  </div>
                </c:forEach>
              </div>
              <!-- 如果需要分页器 -->
              <div class="swiper-pagination"></div>
              
              <!-- 如果需要导航按钮 -->
              <div class="swiper-button-prev"></div>
              <div class="swiper-button-next"></div>
            </div>
          </div>
        </div>
        <div class="row common-row">
          <div class="col-xs-6 setpadding3">
            <div class="clearfix module-title">
              <div class="module-Tleft">
                <span class="module-cn">消化病知识</span>
              </div>
              <div class="module-Tright">
                <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM11'/>">
                  <span>更多</span>
                  <img src="<s:url value='/jsp/inx/jsszPortal/images/more-icon.png'/>">
                </a>
              </div>
              <div class="border-block"></div>            
            </div>
            <ul class="newsUl3">
              <c:forEach items="${lm11List}" var="info">
              <li>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${info.infoFlow}'/>" class="clearfix newsList">
                  <div class="newsListD">【${info.infoTime}】</div>
                  <div class="newsListT">${pdfn:cutString(info.infoTitle,20,true,3)}</div>
                </a>
              </li>
              </c:forEach>
            </ul>
          </div>
          <div class="col-xs-6 setpadding4">
            <div class="clearfix module-title">
              <div class="module-Tleft">
                <span class="module-cn">公益活动</span>
              </div>
              <div class="module-Tright">
                <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM12'/>">
                  <span>更多</span>
                  <img src="<s:url value='/jsp/inx/jsszPortal/images/more-icon.png'/>">
                </a>
              </div>
              <div class="border-block"></div>            
            </div>
            <ul class="newsUl3">
              <c:forEach items="${lm12List}" var="info">
              <li>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${info.infoFlow}'/>" class="clearfix newsList">
                  <div class="newsListD">【${info.infoTime}】</div>
                  <div class="newsListT">${pdfn:cutString(info.infoTitle,20,true,3)}</div>
                </a>
              </li>
              </c:forEach>
            </ul>
          </div>
        </div>
        <div class="row common-row">
          <div class="col-xs-6 setpadding3">
            <div class="clearfix module-title">
              <div class="module-Tleft">
                <span class="module-cn">工作动态</span>
              </div>
              <div class="module-Tright">
                <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM13'/>">
                  <span>更多</span>
                  <img src="<s:url value='/jsp/inx/jsszPortal/images/more-icon.png'/>">
                </a>
              </div>
              <div class="border-block"></div>            
            </div>
            <ul class="newsUl3">
              <c:forEach items="${lm13List}" var="info">
              <li>
                <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${info.infoFlow}'/>" class="clearfix newsList">
                  <div class="newsListD">【${info.infoTime}】</div>
                  <div class="newsListT">${pdfn:cutString(info.infoTitle,20,true,3)}</div>
                </a>
              </li>
              </c:forEach>
            </ul>
          </div>
          <div class="col-xs-6 setpadding4">
            <div class="clearfix module-title">
              <div class="module-Tleft">
                <span class="module-cn">下载中心</span>
              </div>
              <div class="module-Tright">
                <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM14'/>">
                  <span>更多</span>
                  <img src="<s:url value='/jsp/inx/jsszPortal/images/more-icon.png'/>">
                </a>
              </div>
              <div class="border-block"></div>            
            </div>
            <ul class="newsUl3">
              <c:forEach items="${fileList}" var="info">
              <li>
                <a href="<s:url value='/inx/jsszPortal/loadInfoList?columnId=LM14'/>" class="clearfix newsList">
                  <div class="newsListD">【${info.uploadTime}】</div>
                  <div class="newsListT">${pdfn:cutString(info.fileName,20,true,3)}</div>
                </a>
              </li>
              </c:forEach>
            </ul>
          </div>
        </div>
        <div class="row common-row">
          <div class="clearfix module-title">
            <div class="module-Tleft">
              <span class="module-cn">医患交流</span>
              <img class="yihuan" src="<s:url value='/jsp/inx/jsszPortal/images/yihuan.png'/>">
            </div>
            <div class="module-Tright yihuanMore">
              <a href="<s:url value='/inx/jsszPortal/communicationMainList'/>">
                <span>更多</span>
                <img src="<s:url value='/jsp/inx/jsszPortal/images/more-icon.png'/>">
              </a>
            </div>
            <div class="border-block"></div>            
          </div>
          <div class="yihuanUl clearfix">
            <span class="yihuanUl-title">常见消化疾病：</span>
            <ul class="clearfix">
              <li><a href="<s:url value='/inx/jsszPortal/communicationMainList'/>" class="pick2">全部</a></li>
              <c:forEach items="${dictTypeEnumDigestiveDiseasesTypeList}" var="dict">
                <li><a href="<s:url value='/inx/jsszPortal/communicationMainList'/>?diseaseId=${dict.dictId}">${dict.dictName}</a></li>
              </c:forEach>
            </ul>
          </div>
          <div class="col-xs-12 setpadding2">
            <ul class="newsUl2">
              <c:forEach items="${communicationMainList}" var="communicationMain">
              <li>
                <a href="<s:url value='/inx/jsszPortal/communicationDetailList'/>?recordFlow=${communicationMain.recordFlow}" class="clearfix newsList">
                  <div class="newsListD">【${communicationMain.uploadTime}】</div>
                  <div class="newsListT2">${pdfn:cutString(communicationMain.title,40,true,3)}</div>
                </a>
              </li>
              </c:forEach>
            </ul>
          </div>
        </div>
      </div>
      <div class="link-box">
        <div class="link-banner">
          <div class="swiper-container swiper-container2">
            <div class="swiper-wrapper">
              <div class="swiper-slide swiper-slide1">
                <a href="http://www.jstd.gov.cn/" target="_blank"><img src="<s:url value='/jsp/inx/jsszPortal/images/link1.png'/>"></a>
              </div>
              <div class="swiper-slide swiper-slide1">
                <a href="http://program.most.gov.cn/" target="_blank"><img src="<s:url value='/jsp/inx/jsszPortal/images/link2.png'/>"></a>
              </div>
              <div class="swiper-slide swiper-slide1">
                <a href="http://www.njutcm.edu.cn/" target="_blank"><img src="<s:url value='/jsp/inx/jsszPortal/images/link3.png'/>"></a>
              </div>
            </div>
          </div>
        </div>
        <div class="link-text">
          <div class="line-row"></div>
          <div class="middle-txt">友情链接</div>
          <div class="line-row"></div>
        </div>
      </div>
    </div>
    <div class="ewmBox">
      <div class="ewmLogo">
        <%--<a href="javascript:;">--%>
        <img src="<s:url value='/jsp/inx/jsszPortal/images/code2.png'/>" class="code">
        <div style="color: blue">江苏省中医消化病临床医学研究系统</div>
        <%--</a>--%>
        <%--<a href="javascript:;">--%>
        <%--<img src="<s:url value='/jsp/inx/jsszPortal/images/icon-weibo.png'/>">--%>
        <%--</a>--%>
        <%--<img src="<s:url value='/jsp/inx/jsszPortal/images/1534833929.png'/>" class="code">--%>
      </div>
      <%--<div class="ewmLogo">--%>
      <%--<a href="javascript:;">--%>
      <%--<img src="<s:url value='/jsp/inx/jsszPortal/images/icon-weixin.png'/>">--%>
      <%--</a>--%>
      <%--<img src="<s:url value='/jsp/inx/jsszPortal/images/1534833929.png'/>" class="code">--%>
      <%--</div>--%>
      <%--<div class="ewmLogo">--%>
      <%--<a href="javascript:;">--%>
      <%--<img src="<s:url value='/jsp/inx/jsszPortal/images/icon-qq.png'/>">--%>
      <%--</a>--%>
      <%--<img src="<s:url value='/jsp/inx/jsszPortal/images/1534833929.png'/>" class="code">--%>
      <%--</div>--%>
    </div>
  </div>
  <div class="footer">
    <div class="footer-link">
      技术支持：<a href="javascript:;">南京品德科技有限责任公司</a>&nbsp;&nbsp;电话：025-83699386 68581968
    </div>
  </div>

</body>
</html>