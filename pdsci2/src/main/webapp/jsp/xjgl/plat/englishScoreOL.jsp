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
                if('${GlobalConstant.FLAG_Y}'==val){
                    $(this).val('PASS');
                }else if('${GlobalConstant.FLAG_N}'==val){
                    $(this).val('');
                }else if('${GlobalConstant.FLAG_F}'==val){
                    $(this).val('');
                }else{
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
                <c:set var="eName" value="${data['eName']}"/>
                <c:set var="eMajorName" value="${data['eMajorName']}"/>
                <c:set var="eduuser" value="${data['eduuser']}"/>
                <c:set var="eduUserExt" value="${data['eduUserExt']}"/>
                <c:set var="totleCredit" value="${data['totleCredit']}"/>
                <c:set var="qrcode" value="${data['qrcode']}"/>
                <c:set var="currDate" value="${data['currDate']}"/>
                <c:set var="printStmp" value="${data['printStmp']}"/>
            <div style='text-align: center;margin-top: 6.8cm;
            font-family: Georgia, "Times New Roman", Times, Kai, "Kaiti SC", KaiTi, BiauKai, "FontAwesome", serif'>
                <b style="font-size: 20px; ">SOUTHERN MEDICAL UNIVERSITY</b>
                <br>
                <b style="font-size: 20px; ">ACADEMIC RECORD</b>
            </div>
            <div style="margin: 10px 2.5cm 2.5cm 2.5cm">
                <table style="width: 100%;margin: 10px 0">
                    <tr>
                        <td style="width: 60%">NAME:${eName} </td>
                        <td>MAJOR:${eMajorName} </td>
                    </tr>
                    <tr>
                        <td>ROLLMENT YEAR:${eduuser.period } </td>
                        <td>STU.NO: ${eduuser.sid }</td>
                    </tr>
                </table>
                <div>
                    <table class="gridtable" style="width: 100%;font-size: 6px;line-height: 10px;margin-top:5px;">
                        <tr>
                            <td colspan="3">COURSES</td>
                            <td>HOUR</td>
                            <td>CREDITS</td>
                            <td>SCORES</td>
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
                                    <input style="border: none;background-color: snow;text-align: center;width: 100%;" value="${studentCourseExt.courseGrade}" class="courseGradeInput"/>
                                    <%--<c:choose>--%>
                                        <%--<c:when test="${studentCourseExt.courseGrade==GlobalConstant.FLAG_Y }">--%>
                                            <%--PASS--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${studentCourseExt.courseGrade==GlobalConstant.FLAG_N}">--%>
                                            <%--&nbsp;--%>
                                        <%--</c:when>--%>
                                        <%--<c:when test="${studentCourseExt.courseGrade==GlobalConstant.FLAG_F}">--%>
                                            <%--&nbsp;--%>
                                        <%--</c:when>--%>
                                        <%--<c:otherwise>--%>
                                            <%--<script>--%>
                                                <%--var val = "${studentCourseExt.courseGrade}"==""?"":Number('${studentCourseExt.courseGrade}').toFixed(1)--%>
                                                <%--$("#courseGrade${studentCourseExt.courseCode}3").text(val);--%>
                                            <%--</script>--%>
                                        <%--</c:otherwise>--%>
                                    <%--</c:choose>--%>
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
                        <c:if test="${printStmp == 'Y'}">
                            <img id="printStmp" src="<s:url value='/jsp/xjgl/images/stmp.png'/>"
                                 style="float: right;margin-top:10px; text-align:right; position:relative;z-index:50;right: 15px; top: -10px; width:155px;"/>
                        </c:if>
                        <font style="font-size: 16px; position:absolute; right:18px; top:40px;width:350px;">
                            REGISTER: GRADUATE SCHOOL OF<br/>
                            SOUTHERN MEDICAL UNIVERSITY
                            <br/><br/>
                            ${currDate}
                        </font>

                    </div>
                </div>
            </div>
        </td>
    </tr>
</table>
</c:forEach>
</div>
</body>
</html>