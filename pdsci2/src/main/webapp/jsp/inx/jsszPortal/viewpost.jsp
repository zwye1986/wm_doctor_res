<%--<!DOCTYPE html>--%>
<%@include file="/jsp/common/doctype.jsp" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <jsp:include page="/jsp/inx/jsszPortal/htmlhead_portal.jsp" flush="true">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="bootstrap" value="true"/>
  </jsp:include>
  <jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="ueditor" value="true"/>
  </jsp:include>
  <title>江苏省中医消化病临床医学研究系统</title>
  <script>
    $(function () {
      $("#shouye").removeClass("pick");
      $("#yhjl").addClass("pick");
    });
    $(document).ready(function(){
      var ue = UE.getEditor('ueditor', {
        autoHeight: false,
        imagePath: '${sysCfgMap['upload_base_url']}/',
        imageManagerPath: '${sysCfgMap['upload_base_url']}/',
        filePath: '${sysCfgMap['upload_base_url']}/',
        videoPath: '${sysCfgMap['upload_base_url']}/',
        wordImagePath: '${sysCfgMap['upload_base_url']}/',
        snapscreenPath: '${sysCfgMap['upload_base_url']}/',
        catcherPath: '${sysCfgMap['upload_base_url']}/',
        scrawlPath: '${sysCfgMap['upload_base_url']}/'
      });//实例化编辑器
    });
    function saveRe(){
      <c:if test="${not empty sessionScope.currUser}">
      var infoContent = $.trim(UE.getEditor('ueditor').getContent());
      if(!infoContent){
        jboxTip("回复不能为空")
      }else {
        var requestData ={
          "mainFlow":$("#mainFlow").val(),
          "content":infoContent
        };
        var url = "<s:url value='/inx/jsszPortal/saveCommunicationRe'/>";
        jboxPost(url,requestData,function(resp){
          if(resp==1){
            jboxTip("操作成功")
            window.location.href="<s:url value='/inx/jsszPortal/communicationDetailList'/>?recordFlow=${communicationMain.recordFlow}";
          }
        },null,false);
      }
      </c:if>
      <c:if test="${empty sessionScope.currUser}">
      jboxTip("发帖请先登录")
      </c:if>
    }

    function onTopClick() {
      window.location.hash = "#ueditor";
    }
    function delMain(){
      jboxConfirm("删除后不可恢复，确认删除？",function(){
        var requestData ={
          "recordFlow":$("#mainFlow").val(),
          "recordStatus":"N"
        };
        var url = "<s:url value='/inx/jsszPortal/saveCommunicationMain'/>";
        jboxPost(url,requestData,function(resp){
          if(resp==1){
            jboxTip("操作成功")
            window.location.href="<s:url value='/inx/jsszPortal/communicationMainList'/>";
          }
        },null,false);
      })
    }
    function delRe(recordFlow){
      jboxConfirm("确认删除？",function(){
        var requestData ={
          "mainFlow":$("#mainFlow").val(),
          "recordFlow":recordFlow
        };
        var url = "<s:url value='/inx/jsszPortal/delCommunicationRe'/>";
        jboxPost(url,requestData,function(resp){
          if(resp==1){
            jboxTip("操作成功")
            window.location.href="<s:url value='/inx/jsszPortal/communicationDetailList'/>?recordFlow=${communicationMain.recordFlow}";
          }
        },null,false);
      })
    }
    function toPage(page){
      if(page){
        window.location.href="<s:url value='/inx/jsszPortal/communicationDetailList'/>?recordFlow=${communicationMain.recordFlow}&currentPage="+page;
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
      
    </div>
    <div class="content-body">
      <div class="viewpost-page">
        <div class="viewpost-top">医患交流</div>
        <div class="viewpost-titleBox clearfix">
          <div class="viewpost-title">${communicationMain.title}</div>
          <div class="viewpost-btnBox">
            <button type="button" onclick="onTopClick()">回复本帖</button>
            <c:if test="${sessionScope.currentRole eq sysCfgMap['portals_charge_role_flow'] ||
            sessionScope.currUser.userFlow eq communicationMain.uploadUserFlow}">
              <button type="button" class="delete-btn" onclick="delMain();">删除本帖</button>
            </c:if>
          </div>
        </div>
        <div class="postBox">
          <div class="post-item clearfix">
            <div class="post-item-left">
              <ul>
                <li class="face-bg">
                  <img src="${sysCfgMap['upload_base_url']}/${mainUser.userHeadImg}">
                </li>
                <li>姓名：${communicationMain.uploadUserName}</li>
                <li><c:if test="${not empty mainUser.titleName}">职务：${mainUser.titleName}</c:if></li>
              </ul>
            </div>
            <div class="post-item-right">
              <div class="post-item-content clearfix">
                <div class="post-content-txt">${communicationMain.content}</div>
                <%--<button type="button" class="delete-btn2" onclick="delRe()">删除</button>--%>
              </div>
              <div class="post-item-time">${communicationMain.uploadTime}</div>
            </div>
          </div>
          <c:forEach items="${communicationReList}" var="communicationRe">
          <div class="post-item clearfix">
            <div class="post-item-left">
              <ul>
                <li class="face-bg">
                  <img src="${sysCfgMap['upload_base_url']}/${userMap[communicationRe.recordFlow].userHeadImg}"
                       onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'">
                </li>
                <li>姓名：${communicationRe.uploadUserName }</li>
                <li><c:if test="${not empty userMap[communicationRe.recordFlow].titleName}">职务：${userMap[communicationRe.recordFlow].titleName}</c:if></li>
              </ul>
            </div>
            <div class="post-item-right">
              <div class="post-item-content clearfix">
                <div class="post-content-txt">${communicationRe.content}</div>
                <c:if test="${sessionScope.currentRole eq sysCfgMap['portals_charge_role_flow'] ||
                  sessionScope.currUser.userFlow eq communicationRe.uploadUserFlow}">
                <button type="button" class="delete-btn2" onclick="delRe('${communicationRe.recordFlow}')">删除</button>
                </c:if>
              </div>
              <div class="post-item-time">${communicationRe.uploadTime}</div>
            </div>
          </div>
          </c:forEach>
        </div>
        <div class="paginationBox">
          <c:set var="pageView" value="${pdfn:getPageView2(communicationReList,10)}" scope="request"></c:set>
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
        <form id="submitForm">
        <input type="hidden" name="mainFlow" id="mainFlow" value="${communicationMain.recordFlow}">
        <div class="replayPost-box">
          <div id="ueditor" type="text/plain" style="width:980px;height:280px;" class="replayPost">

          </div>
          <div class="replayPost-btnBox">
            <input type="button" class="border-btn1" onclick="saveRe()" value="发布"/>
          </div>

        </div>
        </form>
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