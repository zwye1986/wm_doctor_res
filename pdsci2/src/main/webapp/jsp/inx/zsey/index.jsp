<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>中山大学孙逸仙纪念医院住院医师规范化培训管理平台</title>
    <link rel="shortcut icon" href="<s:url value='/jsp/inx/zsey/images/favicon.ico'/>" />
    <link href="<s:url value='/jsp/inx/zseyGate/css/style.css'/>" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<s:url value='/js/jquery-1.8.3${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/json2${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/common.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/common-jbox.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            jboxLoad("news1","<s:url value='/inx/zseyGate/queryDataByRoleFlowAndColumnId?roleFlow=All&columnId=LM01&jsp=inx/zseyGate/index-display&endIndex=10'/>");
            jboxLoad("news2","<s:url value='/inx/zseyGate/queryDataByRoleFlowAndColumnId?roleFlow=All&columnId=LM02&jsp=inx/zseyGate/index-display&endIndex=10'/>");
            jboxLoad("news3","<s:url value='/inx/zseyGate/queryDataByRoleFlowAndColumnId?roleFlow=All&columnId=LM03&jsp=inx/zseyGate/index-display&endIndex=10'/>");
            jboxLoad("news4","<s:url value='/inx/zseyGate/queryDataByRoleFlowAndColumnId?roleFlow=All&columnId=LM04&jsp=inx/zseyGate/index-display&endIndex=10'/>");
        });
    </script>
</head>
<body>
<div class="centerbox">
    <%@include file="header.jsp" %>

    <div class="news">
        <div class="">
            <div class="title">
                <span class="fl">新闻动态</span>
                <a class="fr" href="<s:url value='/inx/zseyGate/queryDataByRoleFlowAndColumnId?roleFlow=All&columnId=LM01'/>">>>more</a>
            </div>
            <ul class="newtext fl" id="news1">

            </ul>
            <div class="newpic fr">
                <img style="width:100%;height: 100%;margin-bottom: 10px;" id="imgNews"
                     src="<s:url value='/jsp/inx/zseyGate/images/newpic/defaultImg.jpg'/>">
            </div>
        </div>
    </div>

    <div class="content">
        <div class="left fl">
            <div class="title">
                <span class="fl">规章制度</span>
                <a class="s fr" href="<s:url value='/inx/zseyGate/queryByColumnId?columnId=LM02'/>">>>more</a>
            </div>
            <ul class="text" id="news2">

            </ul>
        </div>
        <div class="right fr">
            <ul id="tags">
                <li class="selectTag">
                    <a onclick="selectTag('dsContent0',this)" href="javascript:void(0)">办事流程</a>
                </li>
                <li>
                    <a onclick="selectTag('dsContent1',this)" href="javascript:void(0)">规范化培训</a>
                </li>
                <li>
                    <a onclick="selectTag('dsContent2',this)" href="javascript:void(0)">对外培训</a>
                </li>
            </ul>
            <div id="tagContent">
                <div class="dsContent selectTag" id="dsContent0" style="display:block">
                    <div class="inquiry">
                        <a class="mmore" href="<s:url value='/inx/zseyGate/queryByColumnId?columnId=LM10'/>">>>more</a>
                        <ul id="news3">

                        </ul>
                    </div>
                </div>
                <div class="dsContent" id="dsContent1" style="display:none">
                    <div class="inquiry">
                        <a class="mmore" href="<s:url value='/inx/zseyGate/queryByColumnId?columnId=LM04'/>">>>more</a>
                        <ul  id="news4">

                        </ul>
                    </div>
                </div>
                <div class="dsContent" id="dsContent2" style="display:none">
                    <div class="inquiry">
                        <a class="mmore" href="<s:url value='/inx/zseyGate/queryByColumnId?columnId=LM05'/>">>>more</a>
                        <ul  id="news5">

                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <p>
            <script type="text/javascript">
                function selectTag(showContent, selfObj) {
                    // 操作标签
                    var tag = document.getElementById("tags").getElementsByTagName("li");
                    var taglength = tag.length;
                    for (i = 0; i < taglength; i++) {
                        tag[i].className = "";
                    }
                    selfObj.parentNode.className = "selectTag";
                    // 操作内容
                    for (i = 0; j = document.getElementById("dsContent" + i); i++) {
                        j.style.display = "none";
                    }
                    document.getElementById(showContent).style.display = "block";
                }
            </script>
        </p>
    </div>

    <div class="content">
        <div class="left fl">
            <div class="title">
                <span class="fl">下载中心</span>
                <a class="s fr" href="<s:url value='/inx/zseyGate/queryByColumnId?columnId=LM09'/>">>>more</a>
            </div>
            <ul class="text" id="news6">

            </ul>
        </div>
        <div class="right fr">
            <ul id="tagss">
                <li class="selecttag">
                    <a onclick="selecttag('sdContent0',this)" href="javascript:void(0)">学分管理</a>
                </li>
                <li>
                    <a onclick="selecttag('sdContent1',this)" href="javascript:void(0)">人才培养</a>
                </li>
                <li>
                    <a onclick="selecttag('sdContent2',this)" href="javascript:void(0)">继续项目</a>
                </li>
            </ul>
            <div id="tagContent2">
                <div class="sdContent selecttag" id="sdContent0" style="display:block">
                    <div class="inquiry">
                        <a class="mmore" href="<s:url value='/inx/zseyGate/queryByColumnId?columnId=LM06'/>">>>more</a>
                        <ul id="news7">

                        </ul>
                    </div>
                </div>
                <div class="sdContent" id="sdContent1" style="display:none">
                    <div class="inquiry">
                        <a class="mmore" href="<s:url value='/inx/zseyGate/queryByColumnId?columnId=LM07'/>">>>more</a>
                        <ul id="news8">

                        </ul>
                    </div>
                </div>
                <div class="sdContent" id="sdContent2" style="display:none">
                    <div class="inquiry">
                        <a class="mmore" href="<s:url value='/inx/zseyGate/queryByColumnId?columnId=LM08'/>">>>more</a>
                        <ul id="news9">

                        </ul>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                function selecttag(showContent, selfObj) {
                    // 操作标签
                    var tag = document.getElementById("tagss").getElementsByTagName("li");
                    var taglength = tag.length;
                    for (i = 0; i < taglength; i++) {
                        tag[i].className = "";
                    }
                    selfObj.parentNode.className = "selecttag";
                    // 操作内容
                    for (i = 0; j = document.getElementById("sdContent" + i); i++) {
                        j.style.display = "none";
                    }
                    document.getElementById(showContent).style.display = "block";
                }
            </script>
        </div>
    </div>

    <div class="link">
        <span><img src="<s:url value='/jsp/inx/zseyGate/images/linktitle.png'/>"></span>
        <a href="http://www.satcm.gov.cn/"  target="_blank"><img src="<s:url value='/jsp/inx/zseyGate/images/linkgj.png'/>"></a>
        <a href="http://www.sctcm.gov.cn/"  target="_blank"><img src="<s:url value='/jsp/inx/zseyGate/images/linksc.png'/>"></a>
        <a href="http://www.sctcm120.com/"  target="_blank"><img src="<s:url value='/jsp/inx/zseyGate/images/linksczy.png'/>"></a>
        <a href="http://www.nhfpc.gov.cn/"  target="_blank"><img src="<s:url value='/jsp/inx/zseyGate/images/linkgjwjw.png'/>"></a>
        <a href="http://www.scwst.gov.cn/"  target="_blank"><img src="<s:url value='/jsp/inx/zseyGate/images/linkscwjw.png'/>"></a>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>