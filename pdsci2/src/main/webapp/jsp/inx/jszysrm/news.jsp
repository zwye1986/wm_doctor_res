<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <c:set var="min" value=".min"/>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/jszysrm/style.css'/>"/>
    <link rel="stylesheet" type="text/css" href="<s:url value='/css/skin/${skinPath}/fenye.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function () {
            columnTitle("${param.columnId}");
            loadInfoList("${param.columnId}");
        });

        function queryByColumnId(columnId) {
            location.href = "<s:url value='/inx/jszysrm/queryByColumnId?columnId='/>" + columnId;
        }

        function columnTitle(columnId) {
            var title = "新闻中心";
            if (columnId == "LM02") {
                title = "下载中心";
            } else if (columnId == "LM03") {
                title = "通知公告";
            }
            $("#navUl li").removeClass("active");
            $("#nav_" + columnId).addClass("active");
            $("#new_title").text(title);
        }

        function loadInfoList(columnId) {
            var currentPage = $("#currentPage").val();
            var url = "<s:url value='/inx/jszysrm/loadInfoList?isWithBlobs=${param.isWithBlobs}&columnId=${param.columnId}&currentPage='/>" + currentPage;
            jboxLoad("content", url, true);
        }

        function toPage(page) {
            $("#currentPage").val(page);
            loadInfoList("${param.columnId}");
        }
    </script>
</head>

<body>
<div class="">
    <div class="top">
        <span class="logo"><img src="<s:url value='/jsp/inx/jszysrm/images/logo.png'/>"></span>
    </div>
    <%--<div style="height: 605px; background: url('<s:url--%>
            <%--value='/jsp/inx/jszysrm/images/banner.png'/>') bottom center;background-image: no-repeat;">--%>
        <div class="newChild">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <span class="new_title" id="new_title">新闻中心</span>
            <div id="content">
                <!-- 新闻列表 -->
            </div>
        </div>
    <%--</div>--%>
</div>
<div class="contentbox_02">
    <div class="friendly_01">
        <img src="<s:url value='/'/>jsp/inx/jszysrm/images/xx.png" class="fl">
        <a class="fl title">友情链接</a>
        <%--<a href="" class="fr more"><img src="<s:url value='/'/>jsp/inx/jszysrm/images/more.png"></a>--%>
    </div>
    <div class="friendly_02">
        <ul class="fl link">
            <a href="http://www.jstcm.gov.cn/" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/logo_170.gif"></a>
            <a href="http://www.gov.cn/" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/loge_172.gif"></a>
            <a href="http://www.satcm.gov.cn/" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/loge_174.gif"></a>
            <a href="http://www.moh.gov.cn/2.htm" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/loge_176.gif"></a>
            <a href="http://www.js.gov.cn/" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/loge_178.gif"></a>
            <a  class="last-link" href="http://wskj.jswst.gov.cn/pdsci/" class="link_01" target="_blank"><img src="<s:url value='/jsp/inx/jszysrm/'/>images/loge_173.png"></a>
        </ul>
    </div>
</div>
<div class="footer">
    <div style="width: 100%;text-align: center"><span>主办单位：江苏省中医药局</span></div>
    <div style="width: 100%;text-align: center">
        <span>技术支持：<a href="http://www.njpdkj.com/">南京品德网络信息技术有限公司</a>&nbsp;&nbsp;|&nbsp;&nbsp;TEL：025-68581986 68581968</span>
    </div>
</div>
</body>
</html>
