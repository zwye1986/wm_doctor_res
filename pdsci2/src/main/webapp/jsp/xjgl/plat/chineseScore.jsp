<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style>
        .xllist_1 {
            border: 1px solid #D0E3F2;
            width: 100%;
        }

        .xllist_1 tr:nth-child(2n) {
            background-color: #fffdf5;
            transition: all .125s ease-in-out;
        }

        .xllist_1 tr:hover {
            background: #fbf8e9;
        }

        .xllist_1 th, .xllist_1 td {
            border-right: 1px solid #D0E3F2;
            border-bottom: 1px solid #D0E3F2;
            text-align: center;
        }

        .xllist_1 th {
            background: #ECF2FA;
            color: #333;
            height: 30px;
        }

        .xllist_1 td {
            text-align: center;
            line-height: 25px;
            word-break: break-all;
        }
    </style>
</head>
<body>
<div class="printDiv">
    <div style="text-align: center;">
        <img src="<s:url value='/jsp/xjgl/images/xxlogo.png'/>"/>
    </div>
    <div>
        <table class="xllist_1" style="width: 100%;font-size: 6px;line-height: 10px;margin-top:5px;">
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
                        <c:forEach items="${dictTypeEnumXjIsPassedList}" var="rlt">
                            <c:if test="${rlt.dictId eq studentCourseExt.courseGrade}">
                                ${rlt.dictName}<c:set var="exitFlag" value="Y"/>
                            </c:if>
                        </c:forEach>
                        <c:if test="${exitFlag ne 'Y'}">
                            <script>
                                var val2 = "${studentCourseExt.courseGrade}"==""?"":Number('${studentCourseExt.courseGrade}').toFixed(1)
                                $("#courseGrade${studentCourseExt.courseCode}2").text(val2);
                            </script>
                        </c:if>
                        <c:remove var="exitFlag"/>
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
            <img id="printStmp" <c:if test="${printStmp eq '1'}"> src="<s:url value='/jsp/xjgl/images/stmp2.png'/>"</c:if>
                 style="float: right;margin-top:10px; text-align:right; position:relative;z-index:50;right: 15px; top: -10px; width:155px;"/>
            <font style="font-size: 16px; position:absolute; right:18px; top:40px;width:200px;">
                南方医科大学研究生院<br/><br/>
                ${time }
            </font>

        </div>
    </div>
</div>
</div>
</body>
</html>