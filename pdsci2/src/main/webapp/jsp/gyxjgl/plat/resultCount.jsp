<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    <style type="text/css">

        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:13px;
            color:#333333;
            border-width: 1px;
            border-color: #bbbbbb;
            border-collapse: collapse;
            text-align: center;
            line-height:20px;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #bbbbbb;
            background-color: #f9f9f9;
            text-align: center;
            line-height:20px;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #bbbbbb;
            background-color: #ffffff;
            text-align: center;
            line-height:20px;
        }
    </style>
    <script type="text/javascript">
        var courseLists = [];
        $(document).ready(function(){
            $("#s").text($("#zongShi").val());
            $("#f").text($("#zongFen").val());
            <c:forEach items="${eduUserExt.courseExtsList}" var="edu">
            <c:forEach items="${dictTypeEnumGyXjIsPassedList}" var="dict">
            if("${dict.dictId}"=="${edu.courseGrade }"){
                <%--changeGrade("${fn:replace(edu.recordFlow,'.','')}");--%>
                $("#"+"${edu.recordFlow}"+'label').text("${dict.dictName}");
            }
            </c:forEach>
            </c:forEach>
            initCourseCredit();
        });
        //学分统计
        function initCourseCredit(){
            var allCourseCredit=0;
            var degreeCourseCredit=0;
            <c:forEach items="${eduUserExt.courseExtsList}" var="record">
            if(${record.courseGrade eq 'Y'}){
                allCourseCredit+=Number('${record.courseCredit}');
                if(${record.degreeCourseFlag eq 'Y'}){
                    degreeCourseCredit+=Number('${record.courseCredit}');
                }
            }
            else if(${record.courseGrade eq 'N'}){}
            else if(${record.courseGrade eq 'F'}){}
            else if(${record.courseGrade eq 'T'}){}
            else {
                var v = "${record.courseGrade}"==""?"":Number('${record.courseGrade}').toFixed(1);
                if(v!=""){
                    if(${record.degreeCourseFlag eq 'Y'}){
                        if(v>=75.0){
                            allCourseCredit+=Number('${record.courseCredit}');
                            degreeCourseCredit+=Number('${record.courseCredit}');
                        }
                    }else{
                        if(v>=60.0){
                            allCourseCredit+=Number('${record.courseCredit}');
                            <%--degreeCourseCredit+=Number('${record.courseCredit}');--%>
                        }
                    }
                }
            }
            </c:forEach>
            $("#allCourseCredit").html("学分："+allCourseCredit);
            $("#degreeCourseCredit").html("学分："+degreeCourseCredit);
        }
        function printThis(){
            jboxTip("打印中......");
            setTimeout(function(){
                var newstr = $("#printInfoDiv").html();
                var oldstr = document.body.innerHTML;
                document.body.innerHTML =newstr;
                window.print();
                document.body.innerHTML = oldstr;
                jboxEndLoading();
            },2000);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <c:set var="degreeCourseCount" value="0"/>
        <c:set var="coursePeriodSum" value="0"/>
        <c:set var="courseCreditSum" value="0"/>
        <c:set var="degreeCoursePeriodSum" value="0"/>
        <c:set var="degreeCourseCreditSum" value="0"/>
        <c:forEach items="${eduUserExt.courseExtsList}" var="recordCount">
            <c:set var="coursePeriodSum" value="${coursePeriodSum + recordCount.coursePeriod}"/>
            <%--<c:set var="courseCreditSum" value="${courseCreditSum + recordCount.courseCredit}"/>--%>
            <c:if test="${recordCount.degreeCourseFlag eq GlobalConstant.FLAG_Y}">
                <c:set var="degreeCourseCount" value="${degreeCourseCount + 1}"/>
                <c:set var="degreeCoursePeriodSum" value="${degreeCoursePeriodSum + recordCount.coursePeriod}"/>
                <%--<c:set var="degreeCourseCreditSum" value="${degreeCourseCreditSum + recordCount.courseCredit}"/>--%>
            </c:if>
        </c:forEach>
        <div id="printInfoDiv">
            <div style="overflow-y: auto;overflow-x: visible;">
        <table class="gridtable" style="width: 100%;margin-bottom: 5px;margin-top: 0px;">
            <tr>
                <td style="text-align: left;">
                    &#12288;
                    姓名：<label>${sysuser.userName}</label>
                    &#12288;
                    学号：<label>${eduuser.sid}</label>
                    <br/>&#12288;
                    总课程合计：&#12288;已选${fn:length(eduUserExt.courseExtsList)}门课程&#12288;&#12288;&#12288;学时：${coursePeriodSum}&#12288;&#12288;<label id="allCourseCredit">学分：${courseCreditSum}</label>
                    <br/>&#12288;
                    学位课程合计：已选${degreeCourseCount}门学位课程&#12288;学时：${degreeCoursePeriodSum}&#12288;&#12288;<label id="degreeCourseCredit">学分：${degreeCourseCreditSum}</label>
                </td>
            </tr>
        </table>
        <c:set var="gzFlag" value="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}"/>
        <table  class="gridtable" id="tempTable" style="width: 100%;">
            <tr>
                <th style="width:120px;">课程名称</th>
                <c:if test="${gzFlag}">
                    <th style="width:60px;">学位课程</th>
                </c:if>
                <th style="width:60px;">课程类型</th>
                <th style="width:60px;">学时</th>
                <th style="width:60px;">应获学分</th>
                <th style="width:60px;">实获学分</th>
                <th style="width:60px;">平时成绩</th>
                <th style="width:60px;">期末成绩</th>
                <th style="width:60px;">成绩</th>
            </tr>
            <tbody id="appendTbody">
            <c:forEach items="${eduUserExt.courseExtsList}" var="studentCourseExt">
                <c:set var="recordIdClassVar" value="${fn:replace(studentCourseExt.recordFlow, '.', '')}"/>
                <c:set var="isHaveScore" value="1"></c:set>
                <tr class="each">
                    <input  type="hidden" name="currFlowSun" value="">
                    <td>
                        <label>
                            ${studentCourseExt.courseName}
                        </label>
                    </td>
                    <c:if test="${gzFlag}">
                        <td>
                            <label>${studentCourseExt.degreeCourseFlag eq 'Y'?'是':'否'}</label>
                        </td>
                        <td>
                            <label>${studentCourseExt.courseTypeId eq 'Public'?'选修课':'必修课'}</label>
                        </td>
                        <td><label>${studentCourseExt.coursePeriod}</label></td>
                        <td><label>${studentCourseExt.courseCredit}</label></td>
                        <td id="courseCredit${studentCourseExt.recordFlow}">
                            <script>
                                if(${studentCourseExt.courseGrade==GlobalConstant.FLAG_Y }){$("#courseCredit${studentCourseExt.recordFlow}").html('${studentCourseExt.courseCredit}');}
                                else if(${studentCourseExt.courseGrade==GlobalConstant.FLAG_N }){$("#courseCredit${studentCourseExt.recordFlow}").html(0);}
                                else if(${studentCourseExt.courseGrade eq 'F'}){$("#courseCredit${studentCourseExt.recordFlow}").html(0);}
                                else if(${studentCourseExt.courseGrade eq 'T'}){$("#courseCredit${studentCourseExt.recordFlow}").html(0);}
                                else {
                                    var v = "${studentCourseExt.courseGrade}"==""?"":Number('${studentCourseExt.courseGrade}').toFixed(1);
                                    if(v!=""){
                                        if(${studentCourseExt.degreeCourseFlag eq 'Y'}){
                                            if(v>=75.0){
                                                $("#courseCredit${studentCourseExt.recordFlow}").html('${studentCourseExt.courseCredit}');
                                            }else{
                                                $("#courseCredit${studentCourseExt.recordFlow}").html(0);
                                            }
                                        }else{
                                            if(v>=60.0){
                                                $("#courseCredit${studentCourseExt.recordFlow}").html('${studentCourseExt.courseCredit}');
                                            }else{
                                                $("#courseCredit${studentCourseExt.recordFlow}").html(0);
                                            }
                                        }
                                    }else{
                                        $("#courseCredit${studentCourseExt.recordFlow}").html(0);
                                    }
                                }
                            </script>
                        </td>
                    </c:if>
                    <td>
                        <c:set value="${recordIdClassVar}pacificGrade" var="pac"/>
                        <label class="pacificGrade ${pac}">${studentCourseExt.pacificGrade}</label>
                        <label id="${pac}" style="display: none;">--</label>
                        <c:if test="${ not empty studentCourseExt.pacificGrade }">
                            <c:set var="isHaveScore" value="0"></c:set>
                        </c:if>
                    </td>
                    <td>
                        <c:set value="${recordIdClassVar}teamEndGrade" var="team"/>
                        <label class="teamEndGrade ${team }">${studentCourseExt.teamEndGrade}</label>
                        <label id="${team }" style="display: none;">--</label>
                        <c:if test="${ not empty studentCourseExt.teamEndGrade }">
                            <c:set var="isHaveScore" value="0"></c:set>
                        </c:if>
                    </td>
                    <td class="courseGrade${studentCourseExt.courseCode}">
                        <label id="${studentCourseExt.recordFlow}label">${studentCourseExt.courseGrade}</label>
                        <script>
                            var val1 = "${studentCourseExt.courseGrade}";
                            if(val1 == "Y"){
                                val1 = "通过";
                            }else if(val1 == "N"){
                                val1 = "不通过";
                            }else if(val1 == "T"){
                                val1 = "缺考";
                            }else{
                                val1 = ""!=val1 && !isNaN(val1)?Number('${studentCourseExt.courseGrade}').toFixed(1):"";
                            }
                            var len = $(".courseGrade${studentCourseExt.courseCode}").length;
                            if(len > 1){
                                $($(".courseGrade${studentCourseExt.courseCode}").eq(len-1).find("label")).text(val1);
                            }else{
                                $(".courseGrade${studentCourseExt.courseCode} label").text(val1);
                            }
                        </script>
                        <c:if test="${ not empty studentCourseExt.courseGrade }">
                            <c:set var="isHaveScore" value="0"></c:set>
                        </c:if>
                    </td>
                    <input type="hidden" name="recordFlow" value="${studentCourseExt.recordFlow}"/>
                    <input type="hidden" name="courseFlow" class="courseFlow" value="${studentCourseExt.courseFlow}"/>
                    <input type="hidden" name="userFlow" value="${param.userFlow}"/>
                </tr>
                <c:set var="zongShi" value="${zongShi+studentCourseExt.coursePeriod}"/>
                <c:set var="zongFenTemp" value="${(studentCourseExt.courseGrade eq GlobalConstant.FLAG_Y || (studentCourseExt.courseGrade !='N' and studentCourseExt.courseGrade !='T' and studentCourseExt.courseGrade>'0'))?studentCourseExt.courseCredit:0}"/>
                <c:set var="zongFen" value="${zongFen+zongFenTemp}"/>
            </c:forEach>
            <c:if test="${empty eduUserExt.courseExtsList}">
                <tr>
                    <td colspan="99">无记录</td>
                </tr>
            </c:if>
            </tbody>
            <input type="hidden" id="zongShi" value="<fmt:formatNumber type="number" value="${zongShi}" maxFractionDigits="2"/>"/>
            <input type="hidden" id="zongFen" value="<fmt:formatNumber type="number" value="${zongFen}" maxFractionDigits="2"/>"/>
            <tr class="each" style="display: none;" id="temp">
                <td><input type="checkbox"/></td>
                <td>
                    <select style="width: 80%;" class="courseName" name="courseName" onchange="xiaLa(this);" onclick="currFlow(this)" class="currSelect xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${currCourseMajorlist}" var="currCourseMajor">
                            <option value="${currCourseMajor.courseFlow}"   <c:if test="${studentCourseExt.courseFlow==currCourseMajor.courseFlow}">selected="selected"</c:if>>${currCourseMajor.courseName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td><label class="courseTypeName"></label></td>
                <td><label class="coursePeriod"></label></td>
                <td><label class="courseCredit"></label></td>
                <td>
                    <select style="width:  80%;text-align: center;" class="studyWayId" name="studyWayId">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumGyXjStudyWayList}" var="dict">
                            <option value="${dict.dictId}">${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <select style="width:  80%;text-align: center;" class="assessTypeId" name="assessTypeId">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumGyXjEvaluationModeList}" var="dict">
                            <option value="${dict.dictId}">${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input name="pacificGrade" class="pacificGrade" type="text" style="width: 70%;text-align: center;" value=""/>
                    <label class="pacificGradeLabel" style="display: none;">--</label>
                </td>
                <td>
                    <input name="teamEndGrade" class="teamEndGrade" type="text" style="width: 70%;text-align: center;" value=""/>
                    <label class="teamEndGradeLabel" style="display: none;">--</label>
                </td>
                <td>
                    <input name="courseGrade" class="courseGrade" type="text" style="width: 70%;text-align: center;" value=""/>
                    <select  style="width:  73%;text-align: center;display: none;" class="courseGradeSelect validate[required]" name="courseGradeSelect" >
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumGyXjIsPassedList }" var="dict">
                            <option value="${dict.dictId}"<c:if test="${dict.dictId eq studentCourseExt.courseGrade}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
                <input type="hidden" name="courseFlow" class="courseFlow" value=""/>
                <input type="hidden" name="userFlow" value="${param.userFlow}"/>
            </tr>
        </table>
        </div></div>
        <div class="button">
            <input class="search" type="button" value="打&#12288;印" onclick="printThis();"/>
            <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
        </div>
    </div>
    <div id="printDiv2" style="display: none;"></div>
</div>
</body>
</html>