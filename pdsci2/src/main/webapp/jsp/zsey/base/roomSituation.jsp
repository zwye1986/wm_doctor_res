<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function toPage(){
            if (!$("#searchForm").validationEngine("validate")) {
                return;
            }
            $("#searchForm").submit();
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始日期不能大于结束日期！");
                obj.value = "";
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/zsey/base/roomSituation"/>" method="post">
            <div class="choseDivNewStyle">
                <span style=""></span>日&#12288;&#12288;期：
                <input type="text" readonly="readonly" class="validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="trainDate" value="${trainDate}"/>
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage()"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;border:0;">
            <%--时间布局--%>
            <tr>
                <td style="text-align:right;border:0;">08:00</td>
                <c:set var="time" value="8"/>
                <c:forEach begin="1" end="14" varStatus="i">
                    <c:set var="time" value="${time+1}"/>
                    <c:set var="time1" value="0${time}:00"/>
                    <c:set var="time2" value="${time}:00"/>
                    <td colspan="4" style="text-align:right;border:0;">${time<10?time1:time2}</td>
                </c:forEach>
            </tr>
            <%--教室及表格布局--%>
            <c:forEach items="${dictTypeEnumZseyTrainRoomList}" var="dict">
                <tr>
                    <td style="min-width:50px;background-color:#f5f5f5;border-radius:5px" rowspan="${fn:length(roomMap[dict.dictId])}">${dict.dictName}</td>
                    <c:if test="${empty roomMap[dict.dictId]}">
                        <c:forEach begin="1" end="56" varStatus="i">
                            <td style="min-width:18px;max-width:18px;border-right:${i.count % 4 eq 0?'2px solid #00a1e5':'1px solid grey'};border-left:${i.first?'2px solid #00a1e5':'0'};"></td>
                        </c:forEach>
                    </c:if>
                    <c:if test="${!empty roomMap[dict.dictId]}">
                        <c:forEach items="${roomMap[dict.dictId]}" var="ro" varStatus="j">
                            <c:set var="hour" value="${pdfn:split(ro.beginTime,':')[0]}"/>
                            <c:set var="minu" value="${pdfn:split(ro.beginTime,':')[1]}"/>
                            <c:set var="hour2" value="${pdfn:split(ro.endTime,':')[0]}"/>
                            <c:set var="minu2" value="${pdfn:split(ro.endTime,':')[1]}"/>
                            <fmt:parseNumber var="rows" integerOnly="true" value="${minu%15 gt 7?minu/15+1:minu/15}" />
                            <fmt:parseNumber var="rows2" integerOnly="true" value="${minu2%15 gt 7?minu2/15+1:minu2/15}" />
                            <c:set var="begin" value="${(hour-8)*4+rows lt 0?0:(hour-8)*4+rows}"/>
                            <c:set var="end" value="${(hour2-8)*4+rows2 lt 0?0:(hour2-8)*4+rows2}"/>
                            <c:set var="count" value="0"/>
                            <c:forEach begin="1" end="${begin}" var="num" varStatus="i">
                                <td style="min-width:18px;max-width:18px;border-right:${num % 4 eq 0?'2px solid #00a1e5':'1px solid grey'};border-left:${i.first?'2px solid #00a1e5':'0'};"></td>
                                <c:set var="count" value="${count+1}"/>
                            </c:forEach>
                            <td style="min-width:18px;max-width:18px;border-left:${count eq 0?'2px solid #00a1e5':'1px solid grey'};border-right:${end % 4 eq 0?'2px solid #00a1e5':'1px solid grey'};" colspan="${end-begin}">
                                <div title="${ro.projectName}&#10;${ro.beginTime}~${ro.endTime}" style="white-space:nowrap;overflow:hidden;display:inline-block;cursor:pointer;background-color:#f5f5f5;border:1px solid grey;border-radius:5px;width:92%;height:85%;">${ro.deptName}</div>
                            </td>
                            <c:forEach begin="${end+1}" end="${56}" var="num" varStatus="i">
                                <td style="min-width:18px;max-width:18px;border-right:${num % 4 eq 0?'2px solid #00a1e5':'1px solid grey'};border-left:0;"></td>
                            </c:forEach>
                            <c:if test="${!j.last}">
                                </tr><tr>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>