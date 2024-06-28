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
    <link rel="stylesheet" href="<s:url value='/jsp/inx/tjres/css/notice.css'/>?v=${applicationScope.sysCfgMap['sys_version']}">
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
            <a href="<s:url value='/inx/tjres/contact'/>">联系我们</a>
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
  <!-- 公示公告部分开始 -->
  <div class="container noticeinfo_container">
    <div class="row">
      <div class="noticeinfo_title">
        <span>${info.infoTitle}</span><br/><span style="float: right">${pdfn:transDateTime(info.infoTime)}</span>
        <%--<p>2017年天津市住院医师规范化培训结业考核 </p>--%>
      </div>
      <div class="noticeinfo_content">
        <div>
          <%--<span class="laiyuan">浏览量：${info.viewNum}&#12288;&#12288;&#12288;日期：</span>--%>
          <p>${info.infoContent}</p>
        </div>

      </div>
      <div class="noticeinfo_footer">
        <c:choose>
          <c:when test="${not empty prevInfo}">
            <a href="<s:url value='/inx/tjres/loadInfo?infoFlow=${prevInfo.infoFlow}'/>">上一条：${prevInfo.infoTitle}</a>
          </c:when>
          <c:otherwise>
            <a href="javascript:void(0)">上一条：无</a>
          </c:otherwise>
        </c:choose>
        <c:choose>
          <c:when test="${not empty nextInfo}">
            <a href="<s:url value='/inx/tjres/loadInfo?infoFlow=${nextInfo.infoFlow}'/>">下一条：${nextInfo.infoTitle}</a>
          </c:when>
          <c:otherwise>
            <a href="javascript:void(0)">下一条：无</a>
          </c:otherwise>
        </c:choose>

      </div>
    </div>
  </div>
  <!-- 公告部分结束 -->
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