<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style>
        .xllist_1 {
            border: 1px solid black;
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
            border-right: 1px solid black;
            border-bottom: 1px solid black;
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
    <div style='text-align: center;margin-top: 6.8cm;
    font-family: Georgia, "Times New Roman", Times, Kai, "Kaiti SC", KaiTi, BiauKai, "FontAwesome", serif'>
        <b style="font-size: 20px; ">SOUTHERN MEDICAL UNIVERSITY</b>
        <br>
        <b style="font-size: 20px; ">ACADEMIC RECORD</b>
    </div>
    <div style="margin: 10px 2.5cm 2.5cm 2.5cm">
    <table style="width: 100%;margin: 10px 0">
        <tr>
        <td style="width: 45%">NAME:${eName} </td>
        <td style="width: 55%">MAJOR:${eMajorName} </td>
        </tr>
        <tr>
        <td>ROLLMENT YEAR:${eduuser.period } </td>
        <td>STU.NO: ${eduuser.sid }</td>
        </tr>
    </table>
    <div>
        <table class="xllist_1" style="width: 100%;font-size: 6px;line-height: 10px;margin-top:5px;">
            <tr>
                <td colspan="3" style="width:70%">COURSES</td>
                <td style="width:10%">HOURS</td>
                <td style="width:10%">CREDITS</td>
                <td style="width:10%">SCORES</td>
            </tr>
            <c:set var="totleCredit" value=""/>
            <c:forEach items="${eduUserExt.courseExtsList}" var="studentCourseExt">
                <tr>
                    <td colspan="3" style="text-align: left;padding-left: 10px;">
                        [${studentCourseExt.courseCode}]${studentCourseExt.courseNameEn}</td>
                    <td>${studentCourseExt.coursePeriod}</td>
                    <td width="100px;">${studentCourseExt.courseCredit}</td>
                    <c:set var="totleCredit" value="${totleCredit+studentCourseExt.courseCredit}"/>
                    <td id="courseGrade${studentCourseExt.courseCode}3" width="100px;">
                        <c:choose>
                            <c:when test="${studentCourseExt.courseGrade==GlobalConstant.FLAG_Y }">
                                PASS
                            </c:when>
                            <c:when test="${studentCourseExt.courseGrade==GlobalConstant.FLAG_N}">
                                &nbsp;
                            </c:when>
                            <c:when test="${studentCourseExt.courseGrade==GlobalConstant.FLAG_F}">
                                &nbsp;
                            </c:when>
                            <c:otherwise>
                                <script>
                                    var val = "${studentCourseExt.courseGrade}"==""?"":Number('${studentCourseExt.courseGrade}').toFixed(1)
                                    $("#courseGrade${studentCourseExt.courseCode}3").text(val);
                                </script>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="4" style="text-align: right;padding-right: 10px;">Total&#12288;</td>
                <td style="border-right: none;">${totleCredit}</td>
                <td style="border-left: none;"></td>
            </tr>
            <tr>
                <td colspan="6" style="text-align: left">
                    Note:<br/>
                    Scores of examinations will be stated as the following grades:<br/>
                    A: 85-100, A-: 80-84, B: 75-79, B-: 70-74, C: 65-69, C-: 60-64, D: failed.
                </td>
            </tr>
        </table>
    </div>
    <div style="text-align: right;margin-top: 15px;">
        <img src="${qrcode}" id="qrimg" style="width: 100px;height: 100px;float: left;"/>
        <div style="height:150px;float:right; position:relative;">
            <img id="printStmp" <c:if test="${printStmp eq '1'}"> src="<s:url value='/jsp/xjgl/images/stmp.png'/>"</c:if>
                 style="float: right;margin-top:10px; text-align:right; position:relative;z-index:50;right: 15px; top: -10px; width:155px;"/>
            <font style="font-size: 16px; position:absolute; right:18px; top:40px;width:350px;">
                REGISTER: GRADUATE SCHOOL OF<br/>
                SOUTHERN MEDICAL UNIVERSITY
                <br/><br/>
                ${currDate}
            </font>

        </div>
    </div>
</div>
</div>
</div>
</body>
</html>