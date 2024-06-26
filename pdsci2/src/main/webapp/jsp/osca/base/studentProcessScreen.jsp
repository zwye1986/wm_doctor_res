
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <style type="text/css">
        .xllistNew th{font-size:40px;line-height:60px;border: 0px;background: none;color: white;
            font-weight: normal;border: white 1px solid;padding-left: 5px}
        .xllistNew td{font-size:30px;line-height:48px;border: 0px;background: none;color: white;
            border: white 1px solid;padding-left: 5px}
    </style>
    <script type="text/javascript">
        $(function(){
            setInterval(carousel,10000);
        });
        function carousel(){
            var page = $("#currentPage").val();
            if(page != "" && !isNaN(page) && Math.ceil(${total/10}) > page){
                page = parseInt(page) + 1;
            }else{
                page = 1;
            }
            $("#currentPage").val(page);
            $("#screenForm").submit();
        }
    </script>
</head>
<body id="initCont" background="<s:url value='/jsp/inx/osce/images/${applicationScope.sysCfgMap["osce_screenBg"]}.png'/>">
<div class="mainright">
    <div class="content" style="background: none;">
        <div style="text-align: center;margin-top: 30px;margin-bottom: 20px;">
            <label style="font-size: 44px;color: white;">${orgName}临床技能考核考生进度信息</label>
        </div>
        <div style="text-align: center;width:100%;height:80%;">
            <form id="screenForm" method="get">
                <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
                <c:forEach items="${clinicalFlows}" var="flow">
                    <input type="hidden" name="clinicalFlow" value="${flow}">
                </c:forEach>
            </form>
            <table class="xllistNew" style="width:100%;border: white 3px solid;">
                <tr>
                    <th>考生姓名</th>
                    <th>考核专业</th>
                    <c:forEach begin="1" end="${stationNum}" step="1" var="i">
                        <th>第${i}站</th>
                    </c:forEach>
                </tr>
                <c:forEach items="${resultMapList}" var="result" varStatus="s">
                        <tr>
                            <td style="text-align: center">${result.DOCTOR_NAME}</td>
                            <td style="text-align: center">${result.OSA_NAME}</td>
                            <c:forEach begin="1" end="${stationNum}" step="1" var="i">
                            <c:set var="key" value="STATION${i}"></c:set>
                            <td style="height:50px;text-align: center">
                                <c:if test="${result[key] eq 'Waiting'}"><img src="<s:url value="/jsp/osca/images/hkz.png" />"></c:if>
                                <c:if test="${result[key] eq 'StayAssessment' || empty result[key]}"><img src="<s:url value="/jsp/osca/images/wkh.png" />"></c:if>
                                <c:if test="${result[key] eq 'Assessment'}"><img src="<s:url value="/jsp/osca/images/ywc.png" />"></c:if>
                                <c:if test="${result[key] eq 'AssessIng'}"><img src="<s:url value="/jsp/osca/images/yks.png" />"></c:if>
                            </td>
                            </c:forEach>
                        </tr>
                </c:forEach>
            </table>
            <div  style="font-size: 28px;color: white;line-height: 50px;text-align: left;">
                <img src="<s:url value="/jsp/osca/images/b-ywc.png" />">&nbsp;考生完成本站考核&#12288;
                <img src="<s:url value="/jsp/osca/images/b-yks.png" />">&nbsp;考生已开始考核&#12288;
                <img src="<s:url value="/jsp/osca/images/b-hkz.png" />">&nbsp;考生候考中&#12288;
                <img src="<s:url value="/jsp/osca/images/b-wkh.png" />">&nbsp;考生本站未考核
            </div>
        <div style="text-align:center;">
            <label style="font-size: 28px;color: white;line-height: 100px;">技术支持：南京品德网络信息技术有限公司</label>
        </div>
        </div>
    </div>
</div>
</body>
</html>