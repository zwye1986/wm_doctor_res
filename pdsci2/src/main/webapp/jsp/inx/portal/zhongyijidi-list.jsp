<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>湖北省住院医师规范化培训公众服务平台</title>
    <jsp:include page="/jsp/inx/portal/htmlhead_portal.jsp" flush="true">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="bootstrap" value="true"/>
    </jsp:include>
    <script>
        $(function () {
            $(".header-nav ul li").each(function () {
                var li=$(this);
                $(this).find("a").each(function(){
                    var href =$(this).attr("href");
                    var columnId = "${column.columnId}".substr(0,4);
                    if(columnId != "" && href.indexOf(columnId) != -1){
                        $(li).addClass("pick");
                        $("#shouye").removeClass("pick");
                    }
                });
            });
        });

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
    <div class="row">
    <div class="content-left col-xs-9">
      <div class="notice-list col-xs-3">
        <div class="location-title">
          <img src="<s:url value='/jsp/inx/portal/images/location-icon.png'/>" class="location-icon">
          <span>您现在的位置：</span>
          <span>首页</span>
          <span>&minus;</span>
          <span>中医基地名单</span>
        </div>
          <div class="list-details">
              <div class="details-title">中医基地名单</div>
              <table>
                  <thead>
                  <tr>
                      <th>基地名称</th>
                  </tr>
                  </thead>
                  <tbody>
                  <tr>
                      <td>湖北省中医院</td>
                  </tr>
                  <tr>
                      <td>武汉市中医医院</td>
                  </tr>
                  <tr>
                      <td>襄阳市中医医院</td>
                  </tr>
                  <tr>
                      <td>宜昌市中医医院</td>
                  </tr>
                  <tr>
                      <td>荆州市中医医院</td>
                  </tr>
                  <tr>
                      <td>襄阳市中医医院</td>
                  </tr>
                  <tr>
                      <td>宜昌市中医医院</td>
                  </tr>
                  <tr>
                      <td>荆州市中医医院</td>
                  </tr>
                  <tr>
                      <td>武汉市中西医结合医院</td>
                  </tr>
                  <tr>
                      <td>湖北省中西医结合医院</td>
                  </tr>
                  <tr>
                      <td>荆门市中医医院</td>
                  </tr>
                  <tr>
                      <td>十堰市中医医院</td>
                  </tr>
                  <tr>
                      <td>黄石市中医医院</td>
                  </tr>
                  <tr>
                      <td>黄冈市中医医院</td>
                  </tr>
                  <tr>
                      <td>黄陂区中医医院</td>
                  </tr>
                  <tr>
                      <td>咸宁市中医医院</td>
                  </tr>
                  <tr>
                      <td>随州市中医医院</td>
                  </tr>
                  <tr>
                      <td>恩施州民族医院</td>
                  </tr>
                  <tr>
                      <td>湖北民族学院附属民大医院</td>
                  </tr>
                  <tr>
                      <td>仙桃市中医医院</td>
                  </tr>
                  <tr>
                      <td>公安县中医医院</td>
                  </tr>
                  <tr>
                      <td>华中科技大学同济医学院附属同济医院</td>
                  </tr>
                  <tr>
                      <td>华中科技大学同济医学院附属协和医院</td>
                  </tr>
                  <tr>
                      <td>鄂州市中医医院</td>
                  </tr>
                  <tr>
                      <td>洪湖市中医医院</td>
                  </tr>
                  <tr>
                      <td>天门市中医医院</td>
                  </tr>
                  <tr>
                      <td>利川市民族中医院</td>
                  </tr>
                  <tr>
                      <td>十堰市中西医结合医院</td>
                  </tr>
                  <tr>
                      <td>建始县中医医院</td>
                  </tr>
                  </tbody>
              </table>

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
  </div>
  <div class="footer">
      <div class="footer-content">
          <div class="footer-left">
              <%--<div class="footer-row">--%>
              <%--<span>服务热线：</span>--%>
              <%--<span>027-87185108 | 027-87185208</span>--%>
              <%--</div>--%>
              <div class="footer-row">&#12288;</div>
                  <div class="footer-row"><span>为了达到更好的用户体验，建议您尽量使用谷歌浏览器进行登录&#12288;<a style="color:blue;" target="_blank" href="${sysCfgMap['upload_base_url']}/chromeFile/chrome.exe">谷歌浏览器下载</a></span></div>
              <div class="footer-row">
                  <span>主管单位：</span>
                  <span>湖北省卫生和计划生育委员会</span>
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