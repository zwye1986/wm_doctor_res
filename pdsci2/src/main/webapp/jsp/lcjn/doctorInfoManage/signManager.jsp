<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <script>
        function toPage2(page){
            $("#currentPage2").val(page);
            jboxPostLoad("signDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/signList"/>",$("#signForm").serialize(),true);
        }
        function exportSign(){
            var sign=$("select[name='sign']").val();
            var userName=$("input[name='userName']").val();
            var courseFlow=$("input[name='courseFlow']").val();
            var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/exportSign'/>?courseFlow="+courseFlow+"&userName="+userName+"&sign="+sign;
            jboxTip("导出中....");
            $("#exportA2").attr("href",url);
            $("#outToExcelSpan2").click();

        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
            <div id="signDiv" class="labelDiv">
                <form id="signForm" action="<s:url value="/lcjn/lcjnDoctorTrainInfo/signList"/>" method="post">
                    <div class="choseDivNewStyle">
                    <input type="hidden" name="courseFlow" value="${courseFlow}"/>
                    <a class="btn" id="exportA2" type="hidden"><span id="outToExcelSpan2"> </span></a>
                    <input id="currentPage2" type="hidden" name="currentPage2" value="${currentPage2}"/>
                    <table class="basic" style="width:100%;border:0px;margin:10px 0px;">
                        <tr>
                            <td style="border:0px;">
                                <span style="margin-left: -10px;"></span>是否签到：
                                <select name="sign" style="width:137px;" class="select">
                                    <option value="">全部</option>
                                    <c:forEach items="${lcjnSignStatusEnumList}" var="status">
                                        <option value="${status.id}" ${param.sign eq status.id ?'selected':''}>${status.name}</option>
                                    </c:forEach>
                                </select>
                                <span style="padding-left:10px;"></span>姓名：
                                <input type="text" name="userName" value="${param.userName}">
                                <span style="padding-left:20px;"></span>
                                <input type="button" class="search" value="查&#12288;询" onclick="toPage2(1)"/>
                                <input type="button" class="search" value="导&#12288;出" onclick="exportSign()"/>
                            </td>
                        </tr>
                    </table>
                    </div>
                </form>
                <table class="xllist" style="width:100%;min-width: 999px;">
                    <%--<colgroup>--%>
                        <%--<col width="5%"/>--%>
                        <%--<col width="10%"/>--%>
                        <%--<col width="10%"/>--%>
                        <%--<col width="7%"/>--%>
                        <%--<col width="10%"/>--%>
                        <%--<col width="8%"/>--%>
                        <%--<col width="50%"/>--%>
                    <%--</colgroup>--%>
                    <tr style="background-color:#F5F5F5;">
                        <th>序号</th>
                        <th>用户名</th>
                        <th>姓名</th>
                        <th>培训专业</th>
                        <th>工作单位</th>
                        <th>所在科室</th>
                        <th>签到时间</th>
                    </tr>
                    <c:forEach items="${doctorSignList}" var="info" varStatus="i">
                        <tr>
                            <td>${i.index + 1}</td>
                            <td style="line-height:150%;">${info.USER_CODE}</td>
                            <td style="line-height:150%;">${info.USER_NAME}</td>
                            <td>${info.LCJN_SPE_NAME}</td>
                            <td style="line-height:150%;">${info.ORG_NAME}</td>
                            <td>${info.DEPT_NAME}</td>
                            <td <c:if test="${not empty info.ST and fn:length(fn:split(info.ST,',')) gt 4}">
                                title="<c:forEach items="${fn:split(info.ST,',')}" var="sta" varStatus="i">
                                            ${sta}/&#12288;
                                            <c:if test="${(i.index+1)%2 == 0}">
                                                <br/>
                                            </c:if>
                                        </c:forEach>"
                            </c:if> style="line-height:150%;text-align: left;">
                                <div style="margin-left: 5px;">
                                    <c:if test="${not empty info.ST and fn:length(fn:split(info.ST,',')) lt 4 or fn:length(fn:split(info.ST,',')) eq 4}">
                                        <c:forEach items="${fn:split(info.ST,',')}" var="sta">
                                            ${sta}/&#12288;
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${not empty info.ST and fn:length(fn:split(info.ST,',')) gt 4}">
                                        <c:forEach items="${fn:split(info.ST,',')}" var="sta" begin="0" end="3">
                                            ${sta}/&#12288;
                                        </c:forEach>
                                        ...
                                    </c:if>
                                </div>

                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty doctorSignList}">
                        <tr><td colspan="99">暂无记录</td></tr>
                    </c:if>
                </table>
                <div id="detail"></div>
                <div style="margin-top:100px;">
                    <c:set var="pageView" value="${pdfn:getPageView(doctorSignList)}" scope="request"/>
                    <pd:pagination toPage="toPage2"/>
                </div>
            </div>
    </div>
</div>
</body>
</html>
