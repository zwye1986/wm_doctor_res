<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>江苏省中医消化病临床医学研究系统</title>
  <jsp:include page="/jsp/inx/jsszPortal/htmlhead_portal.jsp">
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
      <div class="newslist-page">
        <div class="location">当前位置：首页>${column.columnName}</div>
        <div class="listPbox">
          <div class="listPtitle">
            <span class="listPtitle1">${column.columnName}</span>
          </div>
          <div class="listPcontent">
            <ul class="listPul">
              <c:choose>
                <c:when test="${param.columnId eq 'LM14'}">
                  <table class="downloadTable">
                    <thead>
                    <tr>
                      <th>文件名称</th>
                      <th>上传时间</th>
                      <th>大小</th>
                      <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${fileList}" var="file">
                    <tr>
                      <td>${file.fileName}</td>
                      <td>${file.uploadTime}</td>
                      <td>${file.fileSize}</td>
                      <td><a style="cursor: pointer" href="${sysCfgMap['upload_base_url']}${file.fileUrl}" target="_blank">查看</a>&nbsp;
                        <a style="cursor: pointer" href="${sysCfgMap['upload_base_url']}${file.fileUrl}" download="${file.fileName}">下载</a>
                      </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </c:when>
                <c:otherwise>
                  <c:forEach items="${infoList}" var="info">
                    <li class="clearfix">
                      <a href="<s:url value='/inx/jsszPortal/loadInfo?infoFlow=${info.infoFlow}'/>" class="listPul-itemTitle">· ${pdfn:cutString(info.infoTitle,40,true,6)}</a>
                      <div class="listPul-itemDate">${info.infoTime}</div>
                    </li>
                  </c:forEach>
                  <c:if test="${empty infoList}">
                    <li class="clearfix">
                      <a class="listPul-itemTitle">暂无信息</a>
                    </li>
                  </c:if>
                </c:otherwise>
              </c:choose>
            </ul>
          </div>
          <%--<div class="paginationBox">--%>
            <%--<a href="javascript:;">&lt;前一页 </a>--%>
            <%--<a href="javascript:;" class="p-num">1</a>--%>
            <%--<a href="javascript:;" class="p-num link-chose">2</a>--%>
            <%--<a href="javascript:;" class="p-num">3</a>--%>
            <%--<a href="javascript:;" class="p-num">4</a>--%>
            <%--<a href="javascript:;" class="p-num">5</a>--%>
            <%--<a href="javascript:;" class="p-num">6</a>--%>
            <%--<a href="javascript:;" class="p-num">7</a>--%>
            <%--<a href="javascript:;" class="p-num">8</a>--%>
            <%--<a href="javascript:;" class="p-num">9</a>--%>
            <%--<a href="javascript:;">……</a>--%>
            <%--<a href="javascript:;" class="p-num">21</a>--%>
            <%--<a href="javascript:;" class="p-num">22</a>--%>
            <%--<a href="javascript:;" class="p-num">23</a>--%>
            <%--<a href="javascript:;">后一页&gt;</a>&nbsp;--%>
            <%--<span>跳转至</span>--%>
            <%--<input type="text" class="pagination-input">--%>
            <%--<span>页</span>&nbsp;--%>
            <%--<a href="javascript:;">确认</a>--%>
          <%--</div>--%>
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