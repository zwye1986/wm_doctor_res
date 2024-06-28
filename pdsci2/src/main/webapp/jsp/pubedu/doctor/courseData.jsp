<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/pubedu/htmlhead-pubedu.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        function showDetailInfo(detailInfoFlow,typeId) {
            var url = "<s:url value="/pubedu/doctor/showDetailInfo"/>?detailInfoFlow=" + detailInfoFlow+"&typeId="+typeId;
            window.open(url);
        }
        function handoutIsExist(flow,handoutUrl) {
            var url = "<s:url value="/pubedu/doctor/handoutIsExist"/>";
            jboxPost(url,{"recordFlow":flow,"typeId":"${courseDetailTypeEnumDataHandout.id}"},function(resp){
                if(resp == 'Y'){
                    var newTab=window.open('about:blank');
                    //window.open("${sysCfgMap["upload_base_url"]}/${detailMap[courseDetailTypeEnumChapterHandout.id].detailUrl}");
                    newTab.location.href="${sysCfgMap["upload_base_url"]}/"+handoutUrl;//防止窗口被浏览器拦截
                }else{
                    jboxTip("未找到该资料讲义");
                }
            } , null , false);
        }
    </script>
    <style type="text/css">
        .appoint{
            cursor: pointer
        }
    </style>
</head>
<body>
<div style="height:100%;overflow: auto">
<jsp:include page="doctorHead.jsp" flush="true"/>
<div class="body">
    <div class="container">
        <a style="padding-left: 30px" href="javascript:history.back(-1);" title="返回">&lt;&lt;&lt;返回上一页</a>
        <table class="xllist" style="width:78%; margin:auto; margin-top:28px;">
            <tbody>
            <tr>
                <th style=" color:#000;">资料PPT</th>
            </tr>
            <c:forEach items="${detailInfoCoursePPT}" var="detailInfo">
            <tr>
                <td class="appoint" title="请点击进入" onclick="showDetailInfo('${detailInfo.recordFlow}','${detailInfo.detailTypeId}')" >${detailInfo.detailName}</td>
            </tr>
            </c:forEach>
            </tbody>
        </table>

        <table class="xllist" style="width:78%; margin:auto; margin-top:20px;">
            <tbody>
            <tr>
                <th style=" color:#000;">资料讲义</th>
            </tr>
            <c:forEach items="${detailInfoChapterHandout}" var="detailInfo">
                <tr>
                    <td class="appoint" title="请点击进入" onclick="handoutIsExist('${detailInfo.recordFlow}','${pdfn:encodeString2(detailInfo.detailUrl)}')" >${detailInfo.detailName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <table class="xllist" style="width:78%; margin:auto; margin-top:20px;">
            <tbody>
            <tr>
                <th style=" color:#000;">资料视频</th>
            </tr>
            <c:forEach items="${detailInfoChapterVideo}" var="detailInfo">
                <tr>
                    <td class="appoint" title="请点击进入" onclick="showDetailInfo('${detailInfo.recordFlow}','${detailInfo.detailTypeId}')" >${detailInfo.detailName}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="foot">
    <div class="foot_inner">
        <span>技术支持：南京品德网络信息技术有限公司&nbsp;&nbsp;服务电话：025-69815356&nbsp;69815357</span>
    </div>
</div>
</div>
</body>
</html>
