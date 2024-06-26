<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <c:set var="min" value=".min"/>
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <link rel="stylesheet" type="text/css" href="<s:url value='/jsp/inx/jszysrm/style.css'/>"/>
    <link rel="stylesheet" type="text/css"
          href="<s:url value='/css/skin/${skinPath}/fenye.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#navUl li").removeClass("active");
            $("#nav_${param.columnId}").addClass("active");
            loadInfo("${param.infoFlow}");
        });

        function loadInfo(infoFlow) {
            //load
            var url = "<s:url value='/inx/jszysrm/loadInfo?infoFlow='/>" + infoFlow;
            jboxLoad("news-icontent", url, true);
            //nextInfo
            var $nextInfo = $("#" + infoFlow).next();
            var nextInfoFlow = $nextInfo.val();
            if (nextInfoFlow != "") {
                var nextInfoTitle = $nextInfo.attr("infoTitle");
                $("#nextInfo").show();
                $a = $("#nextInfo").find("a");
                $a.text(nextInfoTitle);
                $a.attr("onclick", "loadInfo('" + nextInfoFlow + "')");
            } else {
                $("#nextInfo").hide();
            }
        }
    </script>
    <style>
        body, html {
            height: 100%;
            padding: 0px;
            margin: 0px;
        }

        #b_container {
            width: 100%;
            min-height: 100%;
            position: relative;
        }

        #b_footer {
            height: 140px;
            width: 100%;
            position: absolute;
            bottom: 0;
        }
    </style>
</head>
<body>
<div id="b_container">
    <div class="top">
        <span class="logo"><img src="<s:url value='/jsp/inx/jszysrm/images/logo.png'/>"></span>
    </div>
    <c:forEach items="${infoList}" var="info">
        <input type="hidden" id="${info.infoFlow}" value="${info.infoFlow}"
               infoTitle="${pdfn:cutString(info.infoTitle,32,true,6)}"/>
    </c:forEach>

    <div class="newChildren">
        <div class="news-icontent" id="news-icontent">

        </div>

        <div class="fenxiang">
            <div class="fright fr" id="nextInfo">
                下一条：<a href="javascript:void(0)"></a>
            </div>
        </div>
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
</div>
</body>
</html>
