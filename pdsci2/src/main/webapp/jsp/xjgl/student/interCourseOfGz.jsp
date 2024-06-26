
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <style>
        .basic td{border: 0}
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
            <c:set var="gzFlag" value="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}"/>
//            loadInfo();
            LoadEditCourseList();
        });

        function loadInfo(){
            if(${xjChooseCourseStatusEnumChoose.id eq eduUser.chooseCourseStatusId}){//补选

                <c:if test="${choseCourseFlag eq true || gzFlag}">
                $("#replenishBtn").show();
                $("#scheduleCourse").show();
                </c:if>
                $("#saveBtn").hide();
                LoadCourseList();
            }else{//首次选课
                <c:if test="${choseCourseFlag eq true || gzFlag}">
                $("#saveBtn").show();//学籍必填和选填信息确认后方可选课
                $("#saveBeforeBtn").show();//学籍必填和选填信息确认后方可选课
                </c:if>
                $("#replenishBtn").hide();
                $("#scheduleCourse").hide();
                LoadEditCourseList();
            }
        }
        /**
         * 加载该专业所有课程选择列表
         */
        function LoadEditCourseList(){
            var url = "<s:url value='/xjgl/student/course/editCourseList'/>?period=${eduUser.period}&majorId=${eduUser.majorId}&trainTypeId=${eduUser.trainTypeId}&userFlow=${eduUser.userFlow}";
            jboxLoad("contentDiv", url, true);
        }

        function LoadCourseList(){
            var url = "<s:url value='/xjgl/student/course/courseList'/>?studentPeriod=${eduUser.period}&majorId=${eduUser.majorId}&userFlow=${eduUser.userFlow}";
            jboxLoad("contentDiv", url, true);
        }

        function replenish(){
            var url = "<s:url value='/xjgl/student/course/replenish'/>?period=${eduUser.period}&majorId=${eduUser.majorId}&trainTypeId=${eduUser.trainTypeId}&userFlow=${eduUser.userFlow}";
            jboxOpen(url, "其他选课", 850, 500);
        }
        function scheduleCourse() {
            jboxStartLoading();
            var url ="<s:url value='/xjgl/student/course/choseCourse'/>?userFlow=${eduUser.userFlow}&classId=${eduUser.classId}&majorId=${eduUser.majorId}&trainTypeId=${eduUser.trainTypeId}";
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,'选课',1000,650);
            jboxEndLoading();
        }
        function print(userFlow,majorId,period){
            jboxOpen("<s:url value='/xjgl/user/resultSun'/>?openType=${GlobalConstant.FLAG_Y}&userFlow="+userFlow+"&majorId="+majorId+"&period="+period,"我的成绩单",1000,500);
        }
        function searchAllCourse(year){
            jboxStartLoading();
            var url ="<s:url value='/xjgl/course/manage/searchAllCourse?year='/>"+year;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"课程查看",960,500);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">

        <%-- ${ chooseCourseStartTimeCfg.cfgValue}----${chooseCourseEndTimeCfg.cfgValue} --%>
        <!-- 是否在选课开始/结束时间内  -->
        <c:set var="inChooseTime" value="${(pdfn:getCurrDate() >= chooseCourseStartTimeCfg.cfgValue) and ( pdfn:getCurrDate() <= chooseCourseEndTimeCfg.cfgValue)}"/>

        <table class="basic" style="width: 100%;margin: 10px 0px;">
            <tr>
                <td>
                    姓&#12288;&#12288;名：${sysUser.userName}
                </td>
                <td>
                    学&#12288;&#12288;号：${eduUser.sid}
                </td>
                <td>
                    专&#12288;&#12288;业：(${eduUser.majorId})${eduUser.majorName}
                </td>
                <td>
                    <font color="red">总学分不低于：${eduMajorCredit.credit}</font>
                </td>
                <td colspan="2">
                    <input type="button"style="float: right;" class="search" value="查看成绩" onclick="print('${eduUser.userFlow}','${eduUser.majorId}','${eduUser.period}');"/>
                </td>
            </tr>
            <tr>
                <td>
                    培养层次：${eduUser.trainTypeName}
                </td>
                <td>
                    入学年级：${eduUser.period}
                </td>
                <td>
                    <font color="red">注意：红色表示补选课程!</font>
                    <c:set var="curYear" value="${pdfn:getCurrYear()}"/>
                    <a style="cursor:pointer;color:blue;margin-left:15px;text-decoration:underline;" onclick="searchAllCourse(${gzFlag?curYear:'2016'});">${pdfn:getCurrYear()}年研究生课程（全部）</a>
                </td>
                <td>
                    <font color="red">学位课程总学分不低于：${eduMajorCredit.degreeCredit}</font>
                </td>
                <td  colspan="2">
                    <div style="float: right;">
                        <c:if test="${inChooseTime}">
                            <input type="button" id="saveBeforeBtn" class="search" value="保&#12288;&#12288;存" onclick="saveBefore();"/>
                            <%--<input type="button" id="saveBtn" class="search" value="提&#12288;&#12288;交" onclick="save();" style="display: none;"/>--%>
                            <%--<input type="button" id="replenishBtn" class="search" value="其他选课" onclick="replenish();" style="display: none;"/>--%>
                            <%--<input type="button" id="scheduleCourse" class="search" value="课表选课" onclick="scheduleCourse();" style="display: none;background-color: #0b58a2;border: 1px solid #0b58a2;"/>--%>
                        </c:if>
                        <c:if test="${!inChooseTime}">
                            <font color="red">注意：当前时间范围内不可选择课程!</font>&#12288;
                        </c:if>
                    </div>
                </td>
            </tr>
        </table>

        <div id="contentDiv">
            <!-- 加载 -->
        </div>
    </div>
</div>
</body>
</html>