<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script src="<s:url value='/js/jquery-1.9.1.min.js'/>" type="text/javascript"></script>
    <style type="text/css">

        table.gridtable {
            font-family: verdana,arial,sans-serif;
            color:#333333;
            border-width: 1px;
            border-color: #D0E3F2;
            border-collapse: collapse;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #D0E3F2;
            background-color: #dedede;
            text-align: center;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #D0E3F2;
            background-color: #ffffff;
            text-align: center;
        }
    </style>

    <script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $(".courseGradeInput").each(function(){
                var val = $(this).val();
                var flag="Y";
                <c:forEach items="${dictTypeEnumXjIsPassedList}" var="rlt">
                    if('${rlt.dictId}'==val){
                        $(this).val('${rlt.dictName}');
                        flag="N";
                    }
                </c:forEach>
                if(flag=="Y"){
                    var val2 = val==""?"":Number(val).toFixed(1)
                    $(this).val(val2);
                }
            });
            window.print();
        });
    </script>
</head>
<body>
<div class="printDiv">
    <c:forEach items="${dataList}" var="data">
        <table style="width: 100%;border: none;page-break-after:always;">
            <tr style="border: none;">
                <td style="border: none;">
                    <c:set var="eduuser" value="${data['eduuser']}"/>
                    <c:set var="sysuser" value="${data['sysuser']}"/>
                    <c:set var="eduUserExt" value="${data['eduUserExt']}"/>
                    <c:set var="qrcode" value="${data['qrcode']}"/>
                    <c:set var="totleCredit" value="${data['totleCredit']}"/>
                    <c:set var="time" value="${data['time']}"/>
                    <c:set var="printStmp" value="${data['printStmp']}"/>
                    <div style="text-align: center;">
                        <img src="<s:url value='/jsp/xjgl/images/xxlogo.png'/>"/>
                    </div>
                    <div>
                        <table class="gridtable" style="width: 100%;font-size: 6px;line-height: 10px;margin-top:5px;">
                            <tr>
                                <td>年级</td>
                                <td>${eduuser.period }</td>
                                <td>学号</td>
                                <td>${eduuser.sid }</td>
                                <td>姓名</td>
                                <td colspan="2">${sysuser.userName }</td>
                            </tr>
                            <tr>
                                <td>性别</td>
                                <td>${sysuser.sexName }</td>
                                <td>出生年月</td>
                                <td>${sysuser.userBirthday}</td>
                                <td>导师姓名</td>
                                <td colspan="2">${eduuser.firstTeacher }</td>
                            </tr>
                            <tr>
                                <td>专业</td>
                                <td>${eduuser.majorName}</td>
                                <td>培养类型</td>
                                <td>${eduuser.trainCategoryName }</td>
                                <td>学生类别</td>
                                <td colspan="2">${eduuser.trainTypeName }</td>
                            </tr>
                            <tr>
                                <td colspan="3">课程编码及名称</td>
                                <td>课程类别</td>
                                <td>学时</td>
                                <td>学分</td>
                                <td>成绩</td>
                            </tr>
                            <c:set var="totleCredit" value=""/>
                            <c:forEach items="${eduUserExt.courseExtsList}" var="studentCourseExt">
                                <tr>
                                    <td colspan="3" style="text-align: left;padding-left: 10px;">
                                        [${studentCourseExt.courseCode}]${studentCourseExt.courseName}</td>
                                    <td>${studentCourseExt.course.courseTypeName}</td>
                                    <td>${studentCourseExt.coursePeriod}</td>
                                    <td width="100px;">${studentCourseExt.courseCredit}</td>
                                    <c:set var="totleCredit" value="${totleCredit+studentCourseExt.courseCredit}"/>
                                    <td id="courseGrade${studentCourseExt.courseCode}2" width="100px;">
                                        <input style="border: none;background-color: snow;text-align: center;width: 100%;" value="${studentCourseExt.courseGrade}" class="courseGradeInput"/>
                                        <%--<c:forEach items="${dictTypeEnumXjIsPassedList}" var="rlt">--%>
                                            <%--<c:if test="${rlt.dictId eq studentCourseExt.courseGrade}">--%>
                                                <%--${rlt.dictName}<c:set var="exitFlag" value="Y"/>--%>
                                            <%--</c:if>--%>
                                        <%--</c:forEach>--%>
                                        <%--<c:if test="${exitFlag ne 'Y'}">--%>
                                            <%--<script>--%>
                                                <%--var val2 = "${studentCourseExt.courseGrade}"==""?"":Number('${studentCourseExt.courseGrade}').toFixed(1)--%>
                                                <%--$("#courseGrade${studentCourseExt.courseCode}2").text(val2);--%>
                                            <%--</script>--%>
                                        <%--</c:if>--%>
                                        <%--<c:remove var="exitFlag"/>--%>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="5" style="text-align: right;padding-right: 10px;">总学分&#12288;</td>
                                <td style="border-right: none;">${totleCredit}</td>
                                <td style="border-left: none;"></td>
                            </tr>
                        </table>
                    </div>
                    <div style="text-align: right;margin-top: 15px;">
                        <img src="${qrcode}" id="qrimg" style="width: 100px;height: 100px;float: left;"/>
                        <div style="height:150px;float:right; position:relative;">
                            <c:if test="${printStmp == 'Y'}">
                                <img id="printStmp" src="<s:url value='/jsp/xjgl/images/stmp2.png'/>"
                                     style="float: right;margin-top:10px; text-align:right; position:relative;z-index:50;right: 15px; top: -10px; width:155px;"/>
                            </c:if>
                            <font style="font-size: 16px; position:absolute; right:18px; top:40px;width:200px;">
                                南方医科大学研究生院<br/><br/>
                                    ${time }
                            </font>

                        </div>
                    </div>

                </td>
            </tr>
        </table>
    </c:forEach>
</div>
</body>
</html>