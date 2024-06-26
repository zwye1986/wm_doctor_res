<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="false"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>


    <script type="text/javascript">

        function doBack() {
            var target;
            target = "<s:url value='/recruit/examMain/main'/>";
            window.location.href = target;
        }
        function addExamInfoForm(examFlow) {
            jboxOpen("<s:url value='/recruit/examInfo/addExamInfoForm'/>?mainFlow=${main.mainFlow}&examFlow="+examFlow, "考试信息配置", 900,500);
        }
        function showExamInfo(examFlow) {
            jboxOpen("<s:url value='/recruit/examInfo/showExamInfo'/>?mainFlow=${main.mainFlow}&examFlow="+examFlow, "考试信息", 900,500);
        }
        function delExamInfo(examFlow) {
            var url = '<s:url value="/recruit/examInfo/delExamInfo"/>?examFlow='+examFlow;
            jboxConfirm("确认删除？" , function(){
                jboxPost(url , null , function(resp){
                    jboxTip(resp);
                    if(resp=="${ GlobalConstant.DELETE_SUCCESSED}")
                    {
                        window.location.reload(true);
                    }
                } , null , true);
            });
        }

    </script>

</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 ">
            <input type="hidden" id="mainFlow" name="mainFlow" value="${main.mainFlow}"/>
            <div class="queryDiv">
                <div class="inputDiv_" style="margin-left: 50px">
                    ${main.recruitYear}年招录考试信息考场配置
                </div>
                <div class="lastDiv" style="min-width: 180px;max-width: 180px;">
                    <c:if test="${main.isPublish ne 'Y'}">
                        <input type="button" value="新&#12288;增" class="searchInput" onclick="addExamInfoForm('');"/>
                    </c:if>
                    <input type="button" value="返&#12288;回" class="searchInput" onclick="doBack();"/>
                </div>
            </div>
        </div>
        <table class="xllist examRoomData" style="width: 100%;">
            <tr>
                <th width="5%" >序号</th>
                <th width="15%" style="text-align: center">笔试时间</th>
                <th width="15%" style="text-align: center">面试时间</th>
                <th width="15%" style="text-align: center">面试地点</th>
                <th width="15%" style="text-align: center">面试备注</th>
                <th width="10%" style="text-align: center">操作</th>
            </tr>
            <tbody>
            <c:forEach items="${examInfos}" var="examInfo" varStatus="seq">
                <tr>
                    <td>${seq.index + 1}</td>
                    <td>${examInfo.examStartTime}~${examInfo.examEndTime}</td>
                    <td>${examInfo.interviewStartTime}~${examInfo.interviewEndTime}</td>
                    <td>${examInfo.interviewAddress}</td>
                    <td>${examInfo.interviewDemo}</td>
                    <td>
                        <c:if test="${main.isPublish eq 'Y'}">
                            <a href="javascript:showExamInfo('${examInfo.examFlow}')" style="color: blue">查看</a>
                        </c:if>
                        <c:if test="${main.isPublish ne 'Y'}">
                            <a href="javascript:addExamInfoForm('${examInfo.examFlow}')" style="color: blue">编辑</a>
                            |
                            <a href="javascript:delExamInfo('${examInfo.examFlow}')" style="color: blue">删除</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty examInfos}">
                <tr><td align="center" colspan="8">无记录</td></tr>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>