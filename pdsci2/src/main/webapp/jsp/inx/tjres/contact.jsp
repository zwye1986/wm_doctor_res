<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>天津市卫生健康委员会住院医师规范化培训网络管理平台</title>

  <jsp:include page="/jsp/inx/tjres/htmlhead_tjres.jsp" flush="true">
    <jsp:param name="bootstrap" value="true" />
  </jsp:include>
  <link rel="stylesheet" href="<s:url value='/jsp/inx/tjres/css/contact.css'/>?v=${applicationScope.sysCfgMap['sys_version']}">
  <link rel="stylesheet" href="<s:url value='/jsp/inx/tjres/iconfont.css'/>?v=${applicationScope.sysCfgMap['sys_version']}">
  <%--<script>
    $(document).ready(function(){
      $(".navbar_content ul li").click(function(){
        $(this).find("a").addClass("choose");
        $(this).siblings().children().removeClass("choose");
      })
    });
  </script>--%>
</head>
<body>
  <!-- 顶部导航条开始 -->
  <nav class="navbar navbar-default">
    <div class="navbar_content">
      <div class="navbar-header">
        <img src="<s:url value='/jsp/inx/tjres/images/logo.png'/>">
      </div>
      <div>
        <ul class="nav navbar-nav">
          <li>
            <a href="<s:url value='/inx/tjres'/>">首页</a>
          </li>
          <li>
            <a href="<s:url value='/inx/tjres/loadInfoList?columnId=LM01'/>">公示公告</a>
          </li>
          <li>
            <a href="<s:url value='/inx/tjres/loadInfoList?columnId=LM02'/>">培训资讯</a>
          </li>
          <li>
            <a href="<s:url value='/inx/tjres/loadInfoList?columnId=LM03'/>">使用帮助</a>
          </li>
          <li>
            <a class="choose" href="<s:url value='/inx/tjres/contact'/>">联系我们</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- 顶部导航条结束 -->
  <!-- banner图部分开始 -->
  <div class="container-fluid bg">
    <div class="row">
      <div class="col-xs-12">
        <img src="<s:url value='/jsp/inx/tjres/images/notice_banner1.png'/>" class="img_banner">
        <div class="banner_box">
          <div class="banner-box_left">
            <img src="<s:url value='/jsp/inx/tjres/images/notice_txt.png'/>">
          </div>
          
        </div>
      </div>
    </div>
  </div>
  <!-- banner图部分结束 -->
  <!-- 联系我们部分开始 -->
  <div class="container notice_container">
    <div class="row">
      <div class="notice_left">
        <div class="notice_header">
          <div class="notice_title">
            <div class="n_border"></div>
            <p class="n_title">联系我们 <span>  ABOUT US </span></p>
            <p></p>
          </div>          
          <span class="span_border"></span>
        </div>
        <div class="contact_box">
          <div class="contact-b_left">
            <ul>
              <li>
                <img src="<s:url value='/jsp/inx/tjres/images/pdlogo.png'/>">
              </li>
              <li>周一到周五（8:30—18:00）</li>
              <li>(025)－69815356</li>
              <li>(025)－69815357</li>
              <li>(025)－68581968</li>
            </ul>
          </div>
          <div class="contact-b_right">
            <ul>
              <li><span>地址：</span>南京市定淮门12号世界之窗软件园1、12号楼</li>
              <li><span>邮编：</span>210013</li>
              <li><span>QQ：</span>2885400132&nbsp;&nbsp;&nbsp;&nbsp;2885400144</li>
              <li><span>网址：</span>http://www.njpdxx.com</li>
            </ul>
          </div>
        </div>
        <div class="contact-b_img">
          <img src="<s:url value='/jsp/inx/tjres/images/contact_banner.png'/>">
        </div>
      </div>

    </div>
    
  </div>
  <!-- 联系我们结束 -->
  <!-- 底栏开始 -->
  <div class="container-fluid footer_container">
    <div class="row">
      <div class="footer_title">
          Copyright &copy; 2015 主办单位：天津市卫生健康委员会
      </div>
      <div class="footer_text">
        <div class="footer-text_box">
          
        
          <div class="footer-t_left">
            <p>技术支持</p>
            <p>南京品德信息网络技术有限公司</p>
          </div>
          <div class="footer-t_right">
            <p>电话：025-69815356 69815357</p>
            <p>天津客服QQ：2885400132 2885400144</p>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 底栏结束 -->
</body>
</html>