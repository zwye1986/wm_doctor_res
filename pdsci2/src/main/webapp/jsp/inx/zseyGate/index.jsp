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
    <link rel="stylesheet" type="text/css" href="<s:url value='/js/artDialog/css/ui-dialog.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
    <script type="text/javascript" src="<s:url value='/js/artDialog/dialog-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/artDialog/dialog-plus-min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/common-edu-jbox.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/jsp\inx\zseyGate\funcMap.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            jboxLoad("news1","<s:url value='/inx/zseyGate/queryDataByRoleFlowAndColumnId?roleFlow=${empty param.roleFlow?\'All\': param.roleFlow}&columnId=LM01&jsp=inx/zseyGate/index-display&endIndex=10'/>");
            jboxLoad("news2","<s:url value='/inx/zseyGate/queryDataByRoleFlowAndColumnId?roleFlow=${empty param.roleFlow?\'All\': param.roleFlow}&columnId=LM02&jsp=inx/zseyGate/index-display&endIndex=10'/>");
            jboxLoad("news3","<s:url value='/inx/zseyGate/queryDataByRoleFlowAndColumnId?roleFlow=${empty param.roleFlow?\'All\': param.roleFlow}&columnId=LM03&jsp=inx/zseyGate/index-display&endIndex=10'/>");
            jboxLoad("news4","<s:url value='/inx/zseyGate/queryDataByRoleFlowAndColumnId?roleFlow=${empty param.roleFlow?\'All\': param.roleFlow}&columnId=LM04&jsp=inx/zseyGate/index-display&endIndex=10'/>");
        });
        function  showInfo(roleFlow,modelId,src)
        {
            var roleFlow2="${empty param.roleFlow?'All': param.roleFlow}";
            var modelId2="${empty param.modelId?'All': param.modelId}";
            if(roleFlow2=="All") {
                if(modelId!="PXMK"&&modelId!="JXMK"&&modelId!="XTMK"&&modelId!="KSKH") {
                    var url = "<s:url value='/inx/zseyGate'/>?roleFlow=" + roleFlow + "&modelId=" + modelId + "&notTab=Y";
                    window.open(url);
                }else{
                    if(src)
                    {
                        var url = "<s:url value='/'/>" + src;
                        if(src.indexOf("http")>=0)
                        {
                            url=src;
                        }
                        window.open(url);
                    }else{
                        jboxTip("正在建设中");
                    }
                }
            }else{
                if(src)
                {
                    var url = "<s:url value='/'/>" + src;
                    if(src.indexOf("http")>=0)
                    {
                        url=src;
                    }
                    window.open(url);
                }else{
                    jboxTip("正在建设中");
                }
            }
        }
    </script>
</head>
<body>
<div class="centerbox" >
    <jsp:include page="header.jsp" flush="true">
        <jsp:param value="${ param.roleFlow }" name="roleFlow"/>
        <jsp:param value="${ param.modelId }" name="modelId"/>
    </jsp:include>
    <div id="centerbox">
    <div class="news"style="min-height: 400px;">
        <div class="">
            <div class="title">
                <span class="fl">新闻动态</span>
                <a class="fr" href="<s:url value='/inx/zseyGate/queryByRoleFlowAndColumnId?roleFlow=${empty param.roleFlow?\'All\': param.roleFlow}&modelId=${empty param.modelId?\'All\': param.modelId}&columnId=LM01'/>">>>more</a>
            </div>
            <ul class="newtext inquiry fl" id="news1">

            </ul>
            <div class="newpic fr">
                <img style="width:100%;height: 100%;margin-bottom: 10px;display: none;" id="imgNews" src="<s:url value='/jsp/inx/zseyGate/images/newpic/defaultImg.jpg'/>">
            </div>
        </div>
    </div>

    <div class="content">
        <div class="left fl">
            <div class="title">
                <span class="fl">招生动态</span>
                <a class="s fr" href="<s:url value='/inx/zseyGate/queryByRoleFlowAndColumnId?roleFlow=${empty param.roleFlow?\'All\': param.roleFlow}&modelId=${empty param.modelId?\'All\': param.modelId}&columnId=LM02'/>">>>more</a>
            </div>
            <ul class="text inquiry" id="news2">

            </ul>
        </div>
        <div class="right fr">
            <div class="title">
                <span class="fl">教学动态</span>
                <a class="s fr" href="<s:url value='/inx/zseyGate/queryByRoleFlowAndColumnId?roleFlow=${empty param.roleFlow?\'All\': param.roleFlow}&modelId=${empty param.modelId?\'All\': param.modelId}&columnId=LM03'/>">>>more</a>
            </div>
            <ul class="text inquiry" id="news3">

            </ul>
        </div>
    </div>

    <div class="content">
        <div class="left fl"  style="height: 420px; ">
            <div class="title">
                <span class="fl">资料下载</span>
                <a class="s fr" href="<s:url value='/inx/zseyGate/queryByRoleFlowAndColumnId?roleFlow=${empty param.roleFlow?\'All\': param.roleFlow}&modelId=${empty param.modelId?\'All\': param.modelId}&columnId=LM04'/>">>>more</a>
            </div>
            <ul class="text inquiry" id="news4">

            </ul>
        </div>
        <div class="right fr" style="height: 420px; ">
            <div id="tagContent2" >
            </div>
            <style>
                .btnDiv{
                    width: 48%;
                    height: 115px;
                    margin-left: 1%;
                    border: 0px solid blueviolet;
                    float: left;
                    text-align: center;
                    vertical-align: middle;
                }
                .btnDiv2{
                    width: 48%;
                    margin-left: 1%;
                    border: 0px solid blueviolet;
                    float: left;
                    text-align: center;
                    vertical-align: middle;
                }
                .btn{
                    font-size: 17px;
                    font-weight: bold;
                    color: #fff;
                    cursor: pointer;
                    outline: none;
                    padding: 10px 10px;
                    width: 150px;
                    text-align: center;
                    border: 2px solid #44d18c;
                    background: #44d18c;
                    border-radius: 0.3em;
                    -o-border-radius: 0.3em;
                    -webkit-border-radius: 0.3em;
                    -moz-border-radius: 0.3em;
                    margin-top: 35px;
                }
            </style>
            <script>

                $(document).ready(function(){
                    var roleFlow="${empty param.roleFlow?'All': param.roleFlow}";
                    var btns=funcMap[roleFlow];
                    console.log(btns);
                    for(var i=0;i<btns.funcList.length;i++)
                    {
                        var b=btns.funcList[i];
                        if(null!=sysMap[b.id]) {
                            var flows = sysMap[b.id].funcList;
                            if (flows) {
                                roleFlow = flows.split(",")[0];
                            }
                        }
                        var btnDiv=$("<div></div>");
                        if("${empty param.roleFlow?'All': param.roleFlow}"=="All")
                        {
                            $(btnDiv).addClass("btnDiv2");
                        }else{
                            $(btnDiv).addClass("btnDiv2");
                        }

                        var clickStr="onclick=showInfo('"+roleFlow+"','"+ b.id+"','"+ b.src+"')";
                        var btn=$("<input class='btn' value='"+ b.name+"' "+clickStr+" >");
                        $(btnDiv).append(btn);
                        $("#tagContent2").append(btnDiv);
                    }
                });
            </script>
        </div>
    </div>
    </div>
    <%@include file="footer.jsp" %>
</div>
</body>
</html>