<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <link href="<s:url value='/jsp/pubedu/css/font.css' />" type="text/css" rel="stylesheet"/>
    <jsp:include page="/jsp/pubedu/htmlhead-pubedu.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(function () {
            jboxStartLoading();
            if ($( "#myPlayer:has(iframe)" ).length!=0){
                $( "#myPlayer iframe" ).width("100%");
                $( "#myPlayer iframe" ).height("100%");
            }
            if ($( "#myPlayer:has(embed)" ).length!=0){
                $( "#myPlayer embed" ).width("100%");
                $( "#myPlayer embed" ).height("100%");
            }
            setTimeout(jboxEndLoading,1000)
        });

    </script>

</head>
<style>
    .mainDiv {
        width: 800px;
        height: 700px;
        margin: 0 auto;
        text-align: center;

    }

    .videobg{
        height:600px; display:block; background:#c0c0c0;position: relative;z-index: 999;
    }

</style>
<body>
<jsp:include page="doctorHead.jsp" flush="true"/>
<div class="body">
    <div class="container">
        <div id="mainDiv" class="mainDiv">
            <div class="videoPage cbody clearfix" >
                <div style="height: 30px">
                </div>
                <div class="videoBox" style="border: 1px solid #c0c0c0">
                    <div id="myPlayer" class="videobg">
                        ${detailInfo.detailUrl}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="foot">
    <div class="foot_inner">
        <span>技术支持：南京品德网络信息技术有限公司&nbsp;&nbsp;服务电话：025-69815356&nbsp;69815357</span>
    </div>
</div>
</body>
</html>
