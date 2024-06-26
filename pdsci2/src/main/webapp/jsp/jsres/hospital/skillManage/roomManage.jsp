<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function showDiv(obj,resp){
            if(resp=='in'){
                $("#"+obj).removeAttr("hidden");
            }else {
                $("#"+obj).attr("hidden","hidden");
            }
        }
    </script>
</head>
<body>
<form id="roomForm" method="post">
    <input type="hidden" name="clinicalName" value="${clinicalName}"/>
    <input type="hidden" name="speName" value="${speName}"/>
    <input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
    <input type="hidden" name="subjectFlow" value="${param.subjectFlow}"/>
    <table class="basic" style="width:100%;border:0px;margin: 10px 0px;">
        <tr>
            <td style="border:0px;">
                <input type="button" class="btn_green" value="新增考场" onclick="addRoom('')"/>
                <input type="button" class="btn_green" value="清空考场" onclick="delRoom('')"/>
            </td>
        </tr>
    </table>
</form>
<div class="main_bd clearfix" style="margin-top:20px;">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr style="background-color:#F5F5F5;heigh:36px;">
            <th style="width:15%;">考站</th>
            <c:forEach items="${stationMap}" var="map" begin="0" end="0">
                <c:set var="colNum" value="${map.value.MAXROOMNUM gt 4?map.value.MAXROOMNUM:4}"></c:set>
            </c:forEach>
            <c:if test="${empty colNum}"><c:set var="colNum" value="4"></c:set></c:if>
            <th colspan="${colNum}" style="width:70%;">考场</th>
            <th style="width:15%;">未考核人数</th>
        </tr>
        <c:forEach items="${stationMap}" var="map">
            <tr style="height:50px;">
                <td style="width:15px;">${map.value.STATION_NAME}<a onclick="uploadFile('${param.clinicalFlow}','${map.key}')" style="cursor:pointer;color:#4195c5;">上传试卷</a></td>
                <c:forEach items="${roomList}" var="info">
                    <c:if test="${not empty info.RECORD_FLOW && info.STATION_FLOW eq map.key}">
                        <td style="width:${70 / colNum}%" onmouseover="showDiv('div_${info.RECORD_FLOW}','in');" onmouseout="showDiv('div_${info.RECORD_FLOW}','out');">
                            <div style="float:left;margin-left:40px;">${info.ROOM_NAME}<br/>等候人数${info.PDNUM}人</div>
                            <%--<div style="float:right;" id="div_${info.RECORD_FLOW}" class="addDiv" hidden="hidden">--%>
                                <%--<img style="cursor:pointer;float:right;margin:15px 10px 0px 0px;" src="<s:url value='/jsp/inx/osce/images/add.png'/>" onclick="addRoomNew('${map.key}')" title="新增考场" />--%>
                            <%--</div>--%>
                            <a onclick="addRoom('${info.RECORD_FLOW}')" style="cursor:pointer;color:#4195c5;">编辑</a><br>
                            <a onclick="delRoom('${info.RECORD_FLOW}')" style="cursor:pointer;color:#4195c5;">删除</a>
                        </td>
                    </c:if>
                </c:forEach>
                <c:forEach begin="1" end="${colNum - map.value.ROOMNUM}" step="1" var="i">
                    <td style="width:${70 / colNum}%" onmouseover="showDiv('div_${map.key}_${i}','in');" onmouseout="showDiv('div_${map.key}_${i}','out');">
                    <div style="float:right;" id="div_${map.key}_${i}" class="addDiv" hidden="hidden">
                        <img style="cursor:pointer;float:right;margin:10px 10px 5px 0px;" src="<s:url value='/jsp/inx/osce/images/add.png'/>" onclick="addRoomNew('${map.key}')" title="新增考场" />
                    </div>
                </td></c:forEach>
                <td style="width:15px;">${map.value.DKHNUM}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>