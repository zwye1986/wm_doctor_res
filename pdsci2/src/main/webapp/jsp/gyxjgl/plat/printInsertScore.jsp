<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style>
        .cjTab{width:100%;margin-top:10px;border-collapse:collapse;}
        .cjTab td{border:1px solid grey;}
    </style>
</head>
<body>
<div class="printDiv">
    <div style="text-align: center;">
        <img src="<s:url value='/jsp/gyxjgl/images/xxlogo${baseInfo.studyForm eq "tdxl"?"_same":""}.png'/>"/>
    </div>
    <table style="margin-top:10px;">
            <tr>
                <td style="min-width:110px;width:1%;border-left:2px solid grey;">&nbsp;培养类型：<br/>&nbsp;Cultivation Style:</td>
                <td style="min-width:${baseInfo.trainType eq '专业型'?'127px':'113px'};width:1%;">${baseInfo.trainType}<br/>
                    ${baseInfo.trainTypeEn}
                </td>
                <td style="min-width:75px;width:1%;border-left:2px solid grey;">&nbsp;学号：<br/>&nbsp;Student ID:</td>
                <td style="min-width:80px;width:1%;">${baseInfo.stuNo}<br/>${baseInfo.stuNo}</td>
                <td style="min-width:50px;width:1%;border-left:2px solid grey;">&nbsp;姓名：<br/>&nbsp;Name:</td>
                <td style="width:95%;">${baseInfo.userName}<br/>${baseInfo.userNameEn}</td>
            </tr>
            <tr>
                <td style="border-left:2px solid grey;">&nbsp;学位类别：<br/>&nbsp;Degree Type:</td>
                <td>${baseInfo.degreeType}<br/>
                    ${baseInfo.degreeTypeEn}
                </td>
                <td style="border-left:2px solid grey;">&nbsp;性别：<br/>&nbsp;Gender:</td>
                <td>${baseInfo.sex}<br/>${baseInfo.sexEn}</td>
                <td style="border-left:2px solid grey;">&nbsp;专业：<br/>&nbsp;Major:</td>
                <td>${baseInfo.major}<br/>${baseInfo.majorEn}</td>
            </tr>
        </table>
    <table class="cjTab">
        <tr>
            <td style="min-width:200px;width:96%;">&ensp;课程名称<br/>&ensp;Course Name</td>
            <td style="text-align:center;min-width:74px;width:1%;">学时<br/>Hours</td>
            <td style="text-align:center;min-width:74px;width:1%;">学分<br/>Credits</td>
            <td style="text-align:center;min-width:74px;width:1%;">成绩<br/>Scores</td>
            <td style="text-align:center;min-width:84px;width:1%;">绩点<br/>Grade Points</td>
        </tr>
        <c:set var="creditCount" value="0" />
        <c:set var="pointCount" value="0" />
        <c:forEach items="${gradeList}" var="gra">
            <tr>
                <c:set var="creditCount" value="${creditCount+gra.courseCredit}"/>
                <td ><div style="border-bottom:1px dashed grey;padding-left:7px;">${gra.courseName}</div><div style="padding-left:7px;">${gra.courseNameEn}</div></td>
                <td style="text-align:center;">${gra.courseHour}</td>
                <td style="text-align:center;">${gra.courseCredit}</td>
                <fmt:parseNumber var="gradeScore" type='number' value='${gra.gradeScore}'/>
                <td style="text-align:center;">${gra.gradeScore}</td><td style="text-align:center;">
                <fmt:formatNumber type="number" value="${gradeScore lt 60?0.0:((gradeScore-50)/10 gt 5?5:(gradeScore-50)/10)}" pattern="0.0" maxFractionDigits="1"/></td>
                <c:set var="pointCount" value="${pointCount+(gradeScore lt 60?0:((gradeScore-50)/10 gt 5?5:(gradeScore-50)/10))}"/>
            </tr>
        </c:forEach>
    </table>
    <table class="cjTab">
        <tr>
            <td style="text-align:center;width:49%;"><div style="border-bottom:1px dashed grey;">总学分</div>Total Credits</td>
            <td style="text-align:center;min-width:110px;width:1%;">${creditCount}</td>
            <td style="text-align:center;width:49%;"><div style="border-bottom:1px dashed grey;">平均绩点</div>Grade Points Average</td>
            <td style="text-align:center;min-width:110px;width:1%;"><fmt:formatNumber type="number" value="${fn:length(gradeList) eq 0?0:pointCount/fn:length(gradeList)+0.0001}" pattern="0.0" maxFractionDigits="1"/></td>
        </tr>
    </table>
    <div style="text-align: right;margin-top: 15px;">
        <div style="height:150px;float:right; position:relative;">
            <font style="position:absolute;right:18px;top:25px;width:200px;">广州医科大学研究生院</font>
            <c:set var="dateStr" value="${fn:split(pdfn:getCurrDate(),'-')}"/>
            <font style="position:absolute;right:18px;top:45px;width:400px;">Graduate School of Guangzhou Medical University<br/>${dateStr[0]}年 ${dateStr[1]}月 ${dateStr[2]}日<br/>
                ${dateStr[1] eq '01'?'Jan':''}${dateStr[1] eq '02'?'Feb':''}${dateStr[1] eq '03'?'Mar':''}${dateStr[1] eq '04'?'Apr':''}
                ${dateStr[1] eq '05'?'May':''}${dateStr[1] eq '06'?'Jun':''}${dateStr[1] eq '07'?'Jul':''}${dateStr[1] eq '08'?'Aug':''}
                ${dateStr[1] eq '09'?'Sep':''}${dateStr[1] eq '10'?'Oct':''}${dateStr[1] eq '11'?'Nov':''}${dateStr[1] eq '12'?'Dec':''}.<c:choose><c:when test="${dateStr[2] eq '01' || dateStr[2] eq '21' || dateStr[2] eq '31'}">${dateStr[2]}st</c:when>
                    <c:when test="${dateStr[2] eq '02' || dateStr[2] eq '22'}">${dateStr[2]}nd</c:when>
                    <c:when test="${dateStr[2] eq '03' || dateStr[2] eq '23'}">${dateStr[2]}rd</c:when><c:otherwise>${dateStr[2]}th</c:otherwise>
                </c:choose>, ${dateStr[0]}</font>
        </div>
    </div>
</div>
</div>
</body>
</html>