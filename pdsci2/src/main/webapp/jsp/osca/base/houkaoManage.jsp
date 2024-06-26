
<form id="houkaoForm" method="post">
    <input type="hidden" name="clinicalName" value="${clinicalName}"/>
    <input type="hidden" name="speName" value="${speName}"/>
    <input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
    <input type="hidden" name="subjectFlow" value="${param.subjectFlow}"/>
</form>
<script>
    function skipDoc(recordFlow) {

        jboxConfirm("确认跳过此学员？",function() {
            var url = "<s:url value='/osca/base/skipDoc?recordFlow='/>" + recordFlow;
            jboxPost(url, null, function (resp) {
                jboxTip(resp);
                if (resp == "操作成功！")
                    reloadPage();
            }, null, false);
        });
    }
    var c=0;
    function nextDoc(stationFlow,roomFlow,roomName,recordFlow,doctorName,obj) {
        var url = "<s:url value='/osca/base/nextDocYuYin'/>";
        $(obj).hide();
        $(".btnA").hide();
        jboxPost(url, {roomFlow:roomFlow,roomName:roomName,recordFlow:recordFlow,
            stationFlow:stationFlow,clinicalFlow:"${param.clinicalFlow}"}, function (resp) {
            var code=resp.code;
            if(code=="1")
            {
                jboxTip("正在呼叫"+doctorName+"考生!");
                bofan(resp.url);
                setTimeout(function () {
                    jboxTip("正在呼叫"+doctorName+"考生!");
                    bofan(resp.url);
                },10000);
                setTimeout(function () {
                    jboxTip("正在呼叫"+doctorName+"考生!");
                    bofan(resp.url);
                    saveNextDoc(roomFlow,roomName,recordFlow);
                },20000);
            }else{
                $(obj).show();
                $(".btnA").show();
                jboxTip(resp.msg);
            }

        }, null, false);
    }
    function reloadPage() {
        jboxPostLoad("houkaoDiv","<s:url value="/osca/base/houkaoManage"/>",$("#houkaoForm").serialize(),true);
    }
    function bofan(url) {
        $("#bofanSrc").attr("src",url);
    }
    function saveNextDoc(roomFlow,roomName,recordFlow)
    {
        var url = "<s:url value='/osca/base/nextDoc'/>";
        jboxPost(url, {roomFlow:roomFlow,roomName:roomName,recordFlow:recordFlow}, function (resp) {

            jboxTip(resp);
            setTimeout(function () {
                reloadPage();
            },10000);
        }, null, false);
    }
</script>
<div style="min-width: 1200px;overflow: auto;max-height: 600px;">
    <table class="xllist" style="width:100%;margin-top: 10px;">
        <tr style="background-color:#F5F5F5;">
            <th style="min-width: 120px;">考试队列</th>
            <c:forEach items="${stationList}" var="station">
                <th style="min-width: 320px;">${station.stationName}</th>
            </c:forEach>
        </tr>
        <tr>
            <td>考场信息</td>
            <c:forEach items="${stationList}" var="station">
                <td style="vertical-align:top">
                    <c:set var="docStations" value="${waitDocMap[station.stationFlow]}"></c:set>

                    <c:set var="docLength" value="${fn:length(docStations)+0}"></c:set>
                    <c:set var="rooms" value="${roomMap[station.stationFlow]}"></c:set>
                    <c:if test="${not empty rooms}">
                        <table class="xllist nofix" style="margin-top: 0px;">
                            <c:forEach items="${rooms}" var="room">
                                <tr>
                                    <td width="120px">${room.roomName}</td>
                                    <c:set var="key" value="${station.stationFlow}${room.roomFlow}"></c:set>
                                    <td width="100px">${assessingDocMap[key].doctorName}</td>
                                    <td width="100px">
                                        <c:if test="${docLength>0}">
                                            <a class="btnA" style="color:deepskyblue;cursor:pointer;" onclick="nextDoc('${station.stationFlow}','${room.roomFlow}','${room.roomName}','${docStations[0].recordFlow}','${docStations[0].doctorName}',this);">下一位</a>
                                            <c:if test="${not empty assessingDocMap[key].recordFlow}">
                                                <a class="btnA" style="color:deepskyblue;cursor:pointer;" onclick="skipDoc('${assessingDocMap[key].recordFlow}');">&#12288;&#12288;跳过</a>
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </td>
            </c:forEach>
        </tr>
        <c:if test="${maxLength>0}">
            <c:forEach var="i" step="1" begin="0" end="9">
                <tr>
                    <td>候考考生${i+1}</td>
                    <c:forEach items="${stationList}" var="station">
                        <td>
                            <c:set var="docStations" value="${waitDocMap[station.stationFlow]}"></c:set>
                            <c:set var="docLength" value="${fn:length(docStations)+0}"></c:set>
                            <c:if test="${fn:length(docStations)>i}">
                                <c:set var="docStation" value="${docStations[i]}"></c:set>
                                ${docStation.doctorName}
                                <c:if test="${i==0&&docLength>1}">
                                    <a class="btnA" style="color:deepskyblue;cursor:pointer;" onclick="skipDoc('${docStation.recordFlow}');">&#12288;&#12288;跳过</a>
                                </c:if>
                            </c:if>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
                <tr>
                    <td>候考考生</td>
                    <c:forEach items="${stationList}" var="station">
                        <td style="text-align: left;">
                            <c:set var="docStations" value="${waitDocMap[station.stationFlow]}"></c:set>
                            <c:forEach var="i" step="1" begin="10" end="${maxLength-1}">
                                <c:if test="${fn:length(docStations)>i}">
                                    <c:set var="docStation" value="${docStations[i]}"></c:set>
                                    ${docStation.doctorName}&#12288;
                                </c:if>
                            </c:forEach>
                        </td>
                    </c:forEach>
                </tr>
        </c:if>
        <c:if test="${maxLength<=0}">
        <tr>
            <td>候考考生</td>
            <c:forEach items="${stationList}" var="station">
                <td>
                </td>
            </c:forEach>
        </tr>
        </c:if>
    </table>
</div>
<iframe style="display: none;" src="" id="bofanSrc"></iframe>