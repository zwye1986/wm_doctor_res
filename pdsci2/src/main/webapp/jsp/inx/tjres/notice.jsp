<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>天津市卫生健康委员会住院医师规范化培训网络管理平台</title>
  <jsp:include page="/jsp/inx/tjres/htmlhead_tjres.jsp">
    <jsp:param name="basic" value="true" />
    <jsp:param name="bootstrap" value="true" />
    <jsp:param name="jbox" value="true" />
  </jsp:include>
    <link rel="stylesheet" href="<s:url value='/jsp/inx/tjres/css/notice.css'/>?v=${applicationScope.sysCfgMap['sys_version']}">
    <link rel="stylesheet" href="<s:url value='/jsp/inx/tjres/iconfont.css'/>?v=${applicationScope.sysCfgMap['sys_version']}">
  <script type="text/javascript">

      function toPage(page) {
          if (page != undefined) {
              $("#currentPage").val(page);
          }
          $("#searchForm").submit();
      }
  </script>
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
            <a <c:if test="${param.columnId eq 'LM01'}">class="choose" </c:if> href="<s:url value='/inx/tjres/loadInfoList?columnId=LM01'/>">公示公告</a>
          </li>
          <li>
            <a <c:if test="${param.columnId eq 'LM02'}">class="choose" </c:if> href="<s:url value='/inx/tjres/loadInfoList?columnId=LM02'/>">培训资讯</a>
          </li>
          <li>
            <a <c:if test="${param.columnId eq 'LM03'}">class="choose" </c:if> href="<s:url value='/inx/tjres/loadInfoList?columnId=LM03'/>">使用帮助</a>
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
  <div class="container notice_container">
    <div class="row">
      <div class="notice_left">
        <div class="notice_header">
          <div class="notice_title">
            <div class="n_border"></div>
            <c:choose>
              <c:when test="${param.columnId eq 'LM02'}">
                <p class="n_title">培训资讯 <span>  TRAINING CONSULTATION </span></p>
              </c:when>
              <c:when test="${param.columnId eq 'LM03'}">
                <p class="n_title">使用帮助 <span>  USE HELP </span></p>
              </c:when>
              <c:otherwise>
                <p class="n_title">公示公告 <span>  BULLETIN BOARD </span></p>
              </c:otherwise>
            </c:choose>
            <p></p>
          </div>          
          <span class="span_border"></span>
        </div>
        <div class="notice_info">

          <ul>
            <c:forEach items="${infoList}" var="info">
              <li>
                <a href="<s:url value='/inx/tjres/loadInfo?infoFlow=${info.infoFlow}'/>">${pdfn:cutString(info.infoTitle,35,true,6)}</a><span>${pdfn:transDateTime(info.infoTime)}</span>
              </li>
            </c:forEach>
            <c:if test="${empty infoList}">
              <li>无记录！</li>
            </c:if>
          </ul>
        </div>
        <!-- 分页 -->

          <%--<input type="hidden" name="columnId" value="${}"/>--%>
          <div class="hospital_footer">
            <form id="searchForm" method="post" action="<s:url value='/inx/tjres/loadInfoList?columnId=${param.columnId}' />">
              <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <c:set var="pageView" value="${pdfn:getPageView(infoList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
            </form>
          </div>

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