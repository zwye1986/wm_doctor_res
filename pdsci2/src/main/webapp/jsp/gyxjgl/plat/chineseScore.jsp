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
        <%--studyFormId=5 同等学力形式--%>
        <img src="<s:url value='/jsp/gyxjgl/images/xxlogo${baseInfo.trainTypeId eq "5"?"_same":""}.png'/>"/>
    </div>
    <table style="margin-top:10px;">
            <tr>
                <td style="min-width:90px;width:1%;border-left:2px solid grey;">&nbsp;学位类型：<br/>&nbsp;Degree Type:</td>
                <td style="min-width:${baseInfo.trainCategoryId eq '5'?'127px':'113px'};width:1%;">${baseInfo.trainCategoryId eq '4'?'学术学位':''}${baseInfo.trainCategoryId eq '5'?'专业学位':''}<br/>
                    ${baseInfo.trainCategoryId eq '4'?'Academic Degree':''}${baseInfo.trainCategoryId eq '5'?'Professional Degree':''}
                </td>
                <td style="min-width:75px;width:1%;border-left:2px solid grey;">&nbsp;学号：<br/>&nbsp;Student ID:</td>
                <td style="min-width:80px;width:1%;">${baseInfo.sid}<br/>${baseInfo.sid}</td>
                <td style="min-width:50px;width:1%;border-left:2px solid grey;">&nbsp;姓名：<br/>&nbsp;Name:</td>
                <td style="width:95%;">${baseInfo.userName}<br/>${baseInfo.userEnName}</td>
            </tr>
            <tr>
                <td style="border-left:2px solid grey;">&nbsp;学位级别：<br/>&nbsp;Degree Level:</td>
                <td>${baseInfo.degreeType eq 'Master'?'硕士学位':''}${baseInfo.degreeType eq 'Doctor'?'博士学位':''}<br/>
                    ${baseInfo.degreeType}
                </td>
                <td style="border-left:2px solid grey;">&nbsp;性别：<br/>&nbsp;Gender:</td>
                <td>${baseInfo.sexName}<br/>${baseInfo.sexId eq 'Woman'?'Female':''}${baseInfo.sexId eq 'Man'?'Male':''}</td>
                <td style="border-left:2px solid grey;">&nbsp;专业：<br/>&nbsp;Major:</td>
                <td>${baseInfo.majorName}<br/>${baseInfo.majorEnName}</td>
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
                <td style="text-align:center;">${gra.coursePeriod}</td>
                <td style="text-align:center;">${gra.courseCredit}</td>
                <c:choose>
                    <c:when test="${gra.courseGrade eq 'Y'}">
                        <td style="text-align:center;"><div style="border-bottom:1px dashed grey;">合格</div>Pass</td><td style="text-align:center;">3.0</td>
                        <c:set var="pointCount" value="${pointCount+3}"/>
                    </c:when>
                    <c:when test="${gra.courseGrade eq 'N'}">
                        <td style="text-align:center;"><div style="border-bottom:1px dashed grey;">不合格</div>UnPass</td><td style="text-align:center;">0.0</td>
                    </c:when>
                    <c:when test="${gra.courseGrade eq 'Excellent'}">
                        <td style="text-align:center;"><div style="border-bottom:1px dashed grey;">优秀</div>Excellent</td><td style="text-align:center;">4.0</td>
                        <c:set var="pointCount" value="${pointCount+4}"/>
                    </c:when>
                    <c:when test="${gra.courseGrade eq 'Good'}">
                        <td style="text-align:center;"><div style="border-bottom:1px dashed grey;">良好</div>Good</td><td style="text-align:center;">3.0</td>
                        <c:set var="pointCount" value="${pointCount+3}"/>
                    </c:when>
                    <c:when test="${gra.courseGrade eq 'Secondary'}">
                        <td style="text-align:center;"><div style="border-bottom:1px dashed grey;">中等</div>Secondary</td><td style="text-align:center;">2.0</td>
                        <c:set var="pointCount" value="${pointCount+2}"/>
                    </c:when>
                    <c:when test="${gra.courseGrade eq 'Pass'}">
                        <td style="text-align:center;"><div style="border-bottom:1px dashed grey;">及格</div>Pass</td><td style="text-align:center;">1.0</td>
                        <c:set var="pointCount" value="${pointCount+1}"/>
                    </c:when>
                    <c:when test="${gra.courseGrade eq 'UnPass'}">
                        <td style="text-align:center;"><div style="border-bottom:1px dashed grey;">不及格</div>UnPass</td><td style="text-align:center;">0.0</td>
                    </c:when>
                    <c:otherwise>
                        <fmt:parseNumber var="courseGrade" type='number' value='${gra.courseGrade}'/>
                        <td style="text-align:center;">${gra.courseGrade}</td><td style="text-align:center;">
                        <fmt:formatNumber type="number" value="${courseGrade lt 60?0.0:((courseGrade-50)/10 gt 5?5:(courseGrade-50)/10)}" pattern="0.0" maxFractionDigits="1"/></td>
                        <c:set var="pointCount" value="${pointCount+(courseGrade lt 60?0:((courseGrade-50)/10 gt 5?5:(courseGrade-50)/10))}"/>
                    </c:otherwise>
                </c:choose>
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