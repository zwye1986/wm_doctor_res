<%--<!DOCTYPE html>--%>
<%@include file="/jsp/common/doctype.jsp" %>
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
    $(function () {
      $("#shouye").removeClass("pick");
      $("#yhjl").addClass("pick");
      $("border-btn").removeClass("border-btn-click");
      $("#${param.order}").addClass("border-btn-click");
    });
    function search(order){
      window.location.href="<s:url value='/inx/jsszPortal/communicationMainList'/>?"+$("#searchForm").serialize()+"&order="+order;
    }
    function add(){
      <c:if test="${not empty sessionScope.currUser}">
      jboxOpen("<s:url value='/inx/jsszPortal/addCommunication'/>","详情", 900, 700);
      </c:if>
      <c:if test="${empty sessionScope.currUser}">
      jboxTip("发帖请先登录")
      </c:if>
    }
    function goDetail(flow){
      window.location.href="<s:url value='/inx/jsszPortal/communicationDetailList'/>?recordFlow="+flow;
    }
    function toPage(page){
      if(page){
        $("#currentPage").val(page);
        window.location.href="<s:url value='/inx/jsszPortal/communicationMainList'/>?"+$("#searchForm").serialize()+"&order=${param.order}";
      }
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
            <a id="yhjl" href="<s:url value='/inx/jsszPortal/communicationMainList'/>">医患交流</a>
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
      <%--<div class="banner clearfix">--%>
        <%--<div class="banner-login">--%>
          <%--<div class="login-title">登录</div>--%>
          <%--<div class="login-itemBox">--%>
            <%--<span class="loginLogo1"></span>--%>
            <%--<input type="text" class="loginInput" placeholder="用户名">--%>
          <%--</div>--%>
          <%--<div class="login-itemBox">--%>
            <%--<span class="loginLogo2"></span>--%>
            <%--<input type="password" class="loginInput" placeholder="密码">--%>
          <%--</div>--%>
          <%--<div class="login-itemBox no-margin">--%>
            <%--<span class="loginLogo3"></span>--%>
            <%--<input type="text" class="loginInput yzmInput" placeholder="验证码">--%>
            <%--<a href="javascript:;" class="yzm">--%>
              <%--<img src="./images/link1.png">--%>
            <%--</a>--%>
          <%--</div>--%>
          <%--<div class="forgetPwd">--%>
            <%--<a href="javascript:;" class="forgetPwd-txt">忘记密码？</a>--%>
          <%--</div>--%>
          <%--<button type="button" class="login-btn">登录</button>--%>
        <%--</div>--%>
      <%--</div>--%>
      
    </div>
    <div class="content-body">
      <div class="list-page">
        <div class="list-page-top">
          <div class="list-page-title">医患交流</div>
          <div class="list-page-search clearfix">
            <form id="searchForm">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <div class="search-left">
              <button type="button" class="border-btn" id="time" onclick="search('time')">时间</button>
              <button type="button" class="border-btn" id="hot" onclick="search('hot')">热度</button>
              <select name="diseaseId" id="diseaseId" class="select-bg" onchange="search()">
                <option value="">全部</option>
                <c:forEach items="${dictTypeEnumDigestiveDiseasesTypeList}" var="dict">
                  <option value="${dict.dictId}" ${param.diseaseId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                </c:forEach>
              </select>
            </div>

            <div class="search-right clearfix">
              <div class="search-right-inputBox">
                <input type="text" name="title" value="${param.title}" class="search-right-input">
                <button type="button" class="search-right-btn" onclick="search()">搜索</button>
              </div>
              <button type="button" class="bg-btn" onclick="search()">刷新</button>
              <input type="button" class="bg-btn" onclick="add()" value="发帖"/>
            </div>
            </form>
          </div>
        </div>
        <table class="listTable">
          <thead>
            <tr>
              <th>疾病类别</th>
              <th>标题</th>
              <th>发布人</th>
              <th>发布时间</th>
              <th>回复数</th>
              <th>回复时间</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${communicationMainList}" var="communicationMain">
            <tr onclick="goDetail('${communicationMain.recordFlow}')">
              <td>· ${communicationMain.diseaseName}</td>
              <td>${pdfn:cutString(communicationMain.title,25,true,3)}</td>
              <td>${communicationMain.uploadUserName}</td>
              <td>${communicationMain.uploadTime}</td>
              <td>${communicationMain.replayTimes}</td>
              <td>${communicationMain.newestReplayTime}</td>
            </tr>
            </c:forEach>
          </tbody>
        </table>
        <div class="paginationBox">
          <c:set var="pageView" value="${pdfn:getPageView2(communicationMainList,10)}" scope="request"></c:set>
          <pd:pagination toPage="toPage"/>
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