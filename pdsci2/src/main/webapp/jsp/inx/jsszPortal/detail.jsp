<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <jsp:include page="/jsp/inx/jsszPortal/htmlhead_portal.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="bootstrap" value="true"/>
  </jsp:include>
  <title>江苏省中医消化病临床医学研究系统</title>
  <script>
    $(function () {
      $(".header-nav ul li").each(function () {
        var li=$(this);
        $(this).find("a").each(function(){
          var href =$(this).attr("href");
          var columnId = "${column.columnId}".substr(0,4);
          if(columnId != "" && href.indexOf(columnId) != -1){
            $(this).addClass("pick");
            $("#shouye").removeClass("pick");
          }
        });
      });
    });
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
            <a href="<s:url value='/inx/jsszPortal'/>" id="shouye" class="pick">首页</a>
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
    </div>
    <div class="content-body">
      <div class="detail-page">
        <div class="location">当前位置：首页>${info.columnName}</div>
        <div class="detailBox">
          <div class="detail-titleA">${info.infoTitle}</div>
          <div class="detail-titleB">添加者：${createUser.userName}&nbsp;&nbsp;添加时间：${info.infoTime}</div>
          <div class="detail-content">
            ${info.infoContent}
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="footer">
    <div class="footer-link">
      技术支持：<a href="javascript:;">南京品德科技有限责任公司</a>&nbsp;&nbsp;电话：025-83699386 68581968
    </div>
  </div>
</body>
</html>