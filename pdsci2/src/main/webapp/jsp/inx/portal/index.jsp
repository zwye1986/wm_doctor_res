<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>湖北省住院医师规范化培训公共服务平台</title>
    <jsp:include page="/jsp/inx/portal/htmlhead_portal.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="bootstrap" value="true"/>
    </jsp:include>
    <script>
        $(function(){
            var weekDate = '星期'+'日一二三四五六'.charAt(new Date().getDay());
            $("#weekDateSpan").html(weekDate);
            document.onkeydown = function(e){
                var ev = document.all ? window.event : e;
                if(ev.keyCode==13) {// 如（ev.ctrlKey && ev.keyCode==13）为ctrl+Center 触发
                    checkForm();
                }
            }
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
        function goImg(recordFlow){
            window.location.href="<s:url value='/inx/portal/loadInfo?infoFlow='/>"+recordFlow;
        }

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
    <div class="row row1">
    <div class="content-left col-xs-9">
      <div class="left-item1 left-item1-banner">
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <c:forEach items="${imgList}" var="img">
                    <div class="swiper-slide">
                      <img src="${img.url}" style="cursor: pointer" onclick="goImg('${img.recordFlow}')">
                      <div class="banner-title"><span>${img.title}</span></div>
                    </div>
                </c:forEach>
                <c:if test="${empty imgList}">
                    <div class="swiper-slide">
                        <img src="<s:url value='/jsp/inx/portal/images/banner1.png'/>">
                        <div class="banner-title"><span>住院医师规范化培训系统正式住院医师规范化培训系统正式</span></div>
                    </div>
                </c:if>
            </div>
            <!-- 如果需要分页器 -->
            <div class="swiper-pagination"></div>
        </div>
      </div>
      <div class="left-item2">
        <div class="item-title">
          <div class="item-title-left">
            <div class="title-icon"><img src="<s:url value='/jsp/inx/portal/images/news-icon.png'/>"></div>
            <div class="title-txt">新闻中心</div>
          </div>
          <a href="<s:url value='/inx/portal/loadInfoList?columnId=LM07'/>" class="item-title-right">
            <div class="title-Rtxt" columnId="LM07">更多</div>
            <div class="title-Ricon"><img src="<s:url value='/jsp/inx/portal/images/arrow-icon-right.png'/>"></div>
          </a>
        </div>
        <div class="item-content">
            <ul>
                <c:forEach items="${lm07List}" var="lm07">
                    <li>
                        <a class="item-Clist" href="<s:url value='/inx/portal/loadInfo?infoFlow=${lm07.infoFlow}'/>">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Ltitle"> ${pdfn:cutString(lm07.infoTitle,17,true,3)}</span>
                                <span class="item-Ltime">${lm07.infoTime}</span>
                            </div>
                        </a>
                    </li>
                </c:forEach>
                <c:if test="${empty lm07List}">
                    <li>无记录！</li>
                </c:if>
            </ul>
        </div>
      </div>
      <div class="left-item1">
        <div class="item-title">
          <div class="item-title-left">
            <div class="title-icon"><img src="<s:url value='/jsp/inx/portal/images/notice-icon.png'/>"></div>
            <div class="title-txt">通知公告</div>
          </div>
          <a href="<s:url value='/inx/portal/loadInfoList?columnId=LM01'/>" class="item-title-right">
            <div class="title-Rtxt"  columnId="LM01">更多</div>
            <div class="title-Ricon"><img src="<s:url value='/jsp/inx/portal/images/arrow-icon-right.png'/>"></div>
          </a>
        </div>
        <div class="item-content">
            <ul>
                <c:forEach items="${lm01List}" var="info">
                    <li>
                        <a class="item-Clist" href="<s:url value='/inx/portal/loadInfo?infoFlow=${info.infoFlow}'/>">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Ltitle"> ${pdfn:cutString(info.infoTitle,17,true,3)}</span>
                                <span class="item-Ltime">${info.infoTime}</span>
                            </div>
                        </a>
                    </li>
                </c:forEach>
                <c:if test="${empty lm01List}">
                    <li>无记录！</li>
                </c:if>
            </ul>
        </div>
      </div>
      <div class="left-item2">
        <div class="item-title">
          <div class="item-title-left">
            <div class="title-icon"><img src="<s:url value='/jsp/inx/portal/images/rule-icon.png'/>"></div>
            <div class="title-txt">政策法规</div>
          </div>
          <a href="<s:url value='/inx/portal/loadInfoList?columnId=LM02'/>" class="item-title-right">
            <div class="title-Rtxt"  columnId="LM02">更多</div>
            <div class="title-Ricon"><img src="<s:url value='/jsp/inx/portal/images/arrow-icon-right.png'/>"></div>
          </a>
        </div>
        <div class="item-content">
            <ul>
                <c:forEach items="${lm02List}" var="lm02">
                    <li>
                        <a class="item-Clist" href="<s:url value='/inx/portal/loadInfo?infoFlow=${lm02.infoFlow}'/>">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Ltitle"> ${pdfn:cutString(lm02.infoTitle,17,true,3)}</span>
                                <span class="item-Ltime">${lm02.infoTime}</span>
                            </div>
                        </a>
                    </li>
                </c:forEach>
                <c:if test="${empty lm02List}">
                    <li>无记录！</li>
                </c:if>
            </ul>
        </div>
      </div>
      <div class="left-item1">
        <div class="item-title">
          <div class="item-title-left">
            <div class="title-icon"><img src="<s:url value='/jsp/inx/portal/images/show-base-icon.png'/>"></div>
            <div class="title-txt">基地风采</div>
          </div>
          <a href="<s:url value='/inx/portal/loadInfoList?columnId=LM03'/>" class="item-title-right">
            <div class="title-Rtxt"  columnId="LM03">更多</div>
            <div class="title-Ricon"><img src="<s:url value='/jsp/inx/portal/images/arrow-icon-right.png'/>"></div>
          </a>
        </div>
        <div class="item-content">
            <ul>
                <c:forEach items="${lm03List}" var="lm03">
                    <li>
                        <a class="item-Clist" href="<s:url value='/inx/portal/loadInfo?infoFlow=${lm03.infoFlow}'/>">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Ltitle"> ${pdfn:cutString(lm03.infoTitle,17,true,3)}</span>
                                <span class="item-Ltime">${lm03.infoTime}</span>
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
      <div class="left-item2">
        <div class="item-title">
          <div class="item-title-left">
            <div class="title-icon"><img src="<s:url value='/jsp/inx/portal/images/theory-icon.png'/>"></div>
            <div class="title-txt">理论研究</div>
          </div>
          <a href="<s:url value='/inx/portal/loadInfoList?columnId=LM08'/>" class="item-title-right">
            <div class="title-Rtxt"  columnId="LM08">更多</div>
            <div class="title-Ricon"><img src="<s:url value='/jsp/inx/portal/images/arrow-icon-right.png'/>"></div>
          </a>
        </div>
        <div class="item-content">
            <ul>
                <c:forEach items="${lm08List}" var="lm08">
                    <li>
                        <a class="item-Clist" href="<s:url value='/inx/portal/loadInfo?infoFlow=${lm08.infoFlow}'/>">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Ltitle"> ${pdfn:cutString(lm08.infoTitle,17,true,3)}</span>
                                <span class="item-Ltime">${lm08.infoTime}</span>
                            </div>
                        </a>
                    </li>
                </c:forEach>
                <c:if test="${empty lm08List}">
                    <li>无记录！</li>
                </c:if>
            </ul>
        </div>
      </div>
    </div>
    <div class="content-right col-xs-3">
        <div class="right-item1">
            <div class="item-title no-border">
                <div class="login-Ltitlebox change-Lbg"><a href="javascript:;" class="login-Ltitle">西医住培平台</a></div>
                <div class="login-Rtitlebox change-Rbg"><a href="javascript:;" class="login-Rtitle">中医住培平台</a></div>
            </div>
            <div class="login-content">
                <form id="loginForm" action="<s:url value='/inx/hbres/login'/>" method="post">
                <div class="login-item-box">
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
                <div class="login-item-box clearfix">
                    <button type="button" onclick="checkForm();" class="login-btn login-btn4">登录</button>
                    <%--<button type="button" class="login-btn login-btn3"  onclick="window.location.href='<s:url value="/inx/hbres/register"/>'">学员注册</button>--%>
                    <%--<button type="button" class="login-btn login-btn3" onclick="window.location.href='<s:url value="/loginPortal"/>'">信息发布</button>--%>
                </div>
                </form>
            </div>
            <div class="login-content1 conceal">
                建设中...
                <%--<div class="login-item-box">--%>
                    <%--<span class="spanIndex1"></span>--%>
                    <%--<input id="userCode2" name="userCode" type="text" class="loginInput1" placeholder="用户名／手机号">--%>
                <%--</div>--%>
                <%--<div class="login-item-box">--%>
                    <%--<span class="spanIndex2"></span>--%>
                    <%--<input  id="userPasswd2" name="userPasswd" type="password" class="loginInput2" placeholder="密码">--%>
                <%--</div>--%>
                <%--<div class="login-item-box">--%>
                    <%--<span class="spanIndex3"></span>--%>
                    <%--<input  id="verifyCode2" name="verifyCode" placeholder="验证码" value="" type="text" class="loginInput3" placeholder="验证码">--%>
                    <%--<a href="javascript:;" class="yzm"><img id="verifyImage2" src="<s:url value='/captcha'/>" onclick="reloadVerifyCode();" title="点击更换验证码" alt="看不清，换一张" ></a>--%>
                <%--</div>--%>
                <%--<div class="forget-pwd clearfix">--%>
                    <%--<span class="point-info">--%>
                     <%--<c:if test="${not empty loginErrorMessage}">--%>
                         <%--登录失败：${loginErrorMessage}--%>
                     <%--</c:if>--%>
                    <%--</span>--%>
                    <%--<a href="<s:url value='/inx/hbres/forgetpasswd'/>" class="link-forget">忘记密码？</a>--%>
                <%--</div>--%>
                <%--<div class="login-item-box clearfix">--%>
                    <%--<button type="button" onclick="checkForm();" class="login-btn1">登录2</button>--%>
                    <%--<button type="button" class="login-btn2"  onclick="window.location.href='<s:url value="/inx/hbres/register"/>'">学员注册</button>--%>
                    <%--<button type="button" class="login-btn login-btn3" onclick="window.location.href='<s:url value="/loginPortal"/>'">信息发布</button>--%>
                <%--</div>--%>
            </div>
        </div>
        <div class="right-item2">
            <div class="item-Rtitle">
                <div class="item-Rtitle-left">
                    <div class="title-icon"><img src="<s:url value='/jsp/inx/portal/images/base-icon.png'/>"></div>
                    <div class="title-txt">西医基地</div>
                </div>
                <a href="<s:url value='/jsp/inx/portal/xiyijidi-list.jsp'/>" class="item-title-right">
                    <div class="title-Rtxt">更多</div>
                    <div class="title-Ricon"><img src="<s:url value='/jsp/inx/portal/images/arrow-icon-right.png'/>"></div>
                </a>
            </div>
            <div class="item-content2">
                <ul>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">华中科技大学同济医学院附属协和医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">华中科技大学同济医学院附属同济医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">武汉大学人民医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">武汉大学中南医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">武汉大学口腔医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">中国人民解放军中部战区总医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">湖北武警总医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">湖北省妇幼保健院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">湖北省第三人民医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">武汉市中心医院</span>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="right-item2">
            <div class="item-Rtitle">
                <div class="item-Rtitle-left">
                    <div class="title-icon"><img src="<s:url value='/jsp/inx/portal/images/base-icon.png'/>"></div>
                    <div class="title-txt">中医基地</div>
                </div>
                <a href="<s:url value='/jsp/inx/portal/zhongyijidi-list.jsp'/>" class="item-title-right">
                    <div class="title-Rtxt">更多</div>
                    <div class="title-Ricon"><img src="<s:url value='/jsp/inx/portal/images/arrow-icon-right.png'/>"></div>
                </a>
            </div>
            <div class="item-content2">

                <ul>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">湖北省中医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">武汉市中医医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">襄阳市中医医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">宜昌市中医医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">荆州市中医医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">武汉市中西医结合医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">湖北省中西医结合医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">荆门市中医医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">十堰市中医医院</span>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a  class="item-Clist">
                            <div class="circle"></div>
                            <div class="item-Lcontent">
                                <span class="item-Crtitle">黄石市中医医院</span>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
  </div>
      <div class="row row1">
          <div class="col-xs-12 link-box">
              <div class="info-item">
                  <div class="item-title">
                      <div class="item-title-left">
                          <div class="title-icon"><img src="<s:url value='/jsp/inx/portal/images/news-icon.png'/>"></div>
                          <div class="title-txt">信息统计</div>
                      </div>
                  </div>
                  <div class="info-content">
                      <table class="info-table">
                          <thead>
                          <tr>
                              <th>序号</th>
                              <th>单位名称</th>
                              <th>基地类型</th>
                              <th>信息发布数</th>
                              <th>信息审核通过数</th>
                          </tr>
                          </thead>
                          <tbody>
                          <c:forEach items="${resultMapList}" var="result" varStatus="s">
                              <tr>
                                  <td>${s.index+1}</td>
                                  <td>${result.USER_NAME}</td>
                                  <td>${result.WEI_XIN_STATUS_ID eq '1'?'协同基地':'国家基地'}</td>
                                  <td>${result.A}</td>
                                  <td>${result.B}</td>
                              </tr>
                          </c:forEach>
                          </tbody>
                      </table>
                  </div>
              </div>
          </div>
      </div>
      <div class="row row1">
          <div class="col-xs-12 link-box">
              <div class="link-item">
                  <div class="item-title">
                      <div class="item-title-left">
                          <div class="title-icon"><img src="<s:url value='/jsp/inx/portal/images/news-icon.png'/>"></div>
                          <div class="title-txt">友情链接</div>
                      </div>
                  </div>
                  <div class="link-content container">
                      <div class="row">
                          <div class="col-xs-3">
                              <a href="http://www.nhfpc.gov.cn/" target="_Blank" class="link-img">国家卫生健康委员会</a>
                          </div>
                          <div class="col-xs-3">
                              <a href="http://www.cmda.net" target="_Blank" class="link-img">中国医师协会</a>
                          </div>
                          <div class="col-xs-3">
                              <a href="http://www.sic.gov.cn/" target="_Blank" class="link-img">国家信息中心</a>
                          </div>
                          <div class="col-xs-3">
                              <a href="http://www.ccgme-cmda.cn/" target="_Blank" class="link-img">毕业后医学教育网</a>
                          </div>
                      </div>
                      <div class="row">
                          <div class="col-xs-3">
                              <a href="http://www.satcm.gov.cn/" target="_Blank" class="link-img">国家中医药管理局</a>
                          </div>
                          <div class="col-xs-3">
                              <a href="http://www.hbma.org.cn" target="_Blank" class="link-img">湖北省医学会</a>
                          </div>
                          <div class="col-xs-3">
                              <a href="http://www.hbwsjs.gov.cn/" target="_Blank" class="link-img">湖北卫生健康委员会</a>
                          </div>
                          <div class="col-xs-3">
                              <a href="http://www.cma.org.cn/" target="_Blank" class="link-img">中华医学会</a>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>
      <%--<div class="ewmBox">--%>
          <%--<a href="<s:url value='/inx/portal/loadInfo?infoFlow=52cb5a45692b48c5989607b0de26d462'/>" class="ewmLogo">--%>
              <%--<img src="<s:url value='/jsp/inx/portal/images/usersc.png'/>" class="code">--%>
              <%--<div>用户手册</div>--%>
          <%--</a>--%>
      <%--</div>--%>
  </div>
  </div>
  <div class="footer">
    <div class="footer-content">
      <div class="footer-left">
        <%--<div class="footer-row">--%>
          <%--<span>服务热线：</span>--%>
          <%--<span>027-87185108 | 027-87185208</span>--%>
        <%--</div>--%>
            <div class="footer-row">&#12288;</div>
        <div class="footer-row"><span>为了达到更好的用户体验，建议您尽量使用谷歌浏览器进行登录&#12288;<a  style="color:blue;" target="_blank" href="${sysCfgMap['upload_base_url']}/chromeFile/chrome.exe">谷歌浏览器下载</a></span></div>
        <div class="footer-row">
            <span>主管单位：</span>
            <span>湖北卫生健康委员会</span>
        </div>
        <div class="footer-row">
          <span>技术支持：</span>
          <span>南京品德科技有限责任公司 &nbsp; 工信部备案号：苏ICP 备14054231号</span>
        </div>
        <%--<div class="footer-row">--%>
          <%--<span>当前访问量：<font color="red">${pdfn:getOnlineUserCount()}</font></span>--%>
        <%--</div>--%>
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
          <span>&nbsp;2885400153</span>
        </li>
        <li>
          <img src="<s:url value='/jsp/inx/portal/images/zqq.png'/>">
          <span>&nbsp;2885400156</span>
        </li>
        <li>
          <img src="<s:url value='/jsp/inx/portal/images/zphone.png'/>">
          <span>&nbsp;027-87185108</span>
        </li>
        <li>
          <img src="<s:url value='/jsp/inx/portal/images/zphone.png'/>">
          <span>&nbsp;027-87185208</span>
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
  <script>
      $(".login-Ltitle").on("click",function(){
          if(!$(this).parent(".login-Ltitlebox").hasClass("change-Lbg")){
              $(this).parent(".login-Ltitlebox").addClass("change-Lbg");
              $(".login-Rtitlebox").addClass("change-Rbg");
              $(".login-content1").hide();
              $(".login-content").show();
          }
      })
      $(".login-Rtitle").on("click",function(){
          if($(this).parent(".login-Rtitlebox").hasClass("change-Rbg")){
              $(this).parent(".login-Rtitlebox").removeClass("change-Rbg");
              $(".login-Ltitlebox").removeClass("change-Lbg");
              $(".login-content1").show();
              $(".login-content").hide();
          }
      })

  </script>
</body>
</html>