<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style>
        table{border-collapse:collapse;}
        .custom td{text-align:center;border:1px solid grey;height:30px;}
    </style>
</head>
<body style="overflow:auto;">
<div style="text-align:center;font-weight:600;margin-bottom:20px;">研究生国家奖学金申请审批表</div>
<table class="custom" style="width:100%;">
    <tr>
        <td rowspan="5" style="width:6%;font-weight:600;">基本<br>情况</td>
        <td style="width:15%;">姓名</td>
        <td style="width:15%;">${scholarshipForm.userName}</td>
        <td style="width:15%;">性别</td>
        <td style="width:15%;">${scholarshipForm.sexName}</td>
        <td style="width:15%;">出生年月</td>
        <td style="width:15%;">${scholarshipForm.userBirthday}</td>
    </tr>
    <tr>
        <td>政治面貌</td>
        <td>${scholarshipForm.politicsStatusName}</td>
        <td>民族</td>
        <td>${scholarshipForm.nationName}</td>
        <td>入学时间</td>
        <td>${scholarshipForm.periodYear}</td>
    </tr>
    <tr>
        <td>基层单位</td>
        <td>${scholarshipForm.baseUnit}</td>
        <td>专业</td>
        <td>${scholarshipForm.majorName}</td>
        <td>攻读学位</td>
        <td>${scholarshipForm.studyDegree}</td>
    </tr>
    <tr>
        <td>学制</td>
        <td>${scholarshipForm.schoolSystem}</td>
        <td>学习阶段</td>
        <td>
            <table style="width:100%;">
                <tr><td style="border-width:0px;height:auto;border-bottom-width:1px">${fn:contains(scholarshipForm.studyPeriod,'硕士')?'√':'□'}硕士</td></tr>
                <tr><td style="border-width:0px;height:auto;">${fn:contains(scholarshipForm.studyPeriod,'博士')?'√':'□'}博士</td></tr>
            </table>
        </td>
        <td>学号</td>
        <td>${scholarship.studentId}</td>
    </tr>
    <tr>
        <td>身份证号</td>
        <td colspan="5">
            <table style="width:100%;height:30px;">
                <tr><c:forEach var="card" begin="0" end="18" step="1" varStatus="i">
                    <td style="width:5.6%;border-width:0px;${!i.last?'border-right-width:1px;':''}">${fn:substring(scholarshipForm.cardId,i.index,i.index+1)}</td>
                </c:forEach></tr>
            </table>
        </td>
    </tr>
    <tr>
        <td style="font-weight:600;">申请<br>理由</td>
        <td colspan="6" style="height:550px;text-align:left;">
            <div style="width:100%;height:100%;">
                <div style="padding:20px 10px 0px 10px;">
                    &#12288;&#12288;${scholarshipForm.applyReason}
                </div>
                <div style="text-align:right;margin-right:150px;margin-top:420px;">申请人签名：</div>
                <div style="text-align:right;margin:20px 30px 0px 0px;">年<span style="padding-left:30px;"></span>月<span style="padding-left:30px;"></span>日</div>
            </div>
        </td>
    </tr>
    <tr>
        <td style="font-weight:600;">推荐<br>意见</td>
        <td colspan="6" style="height:250px;text-align:left;">
            <div style="width:100%;height:100%;">
                <div style="padding:20px 10px 0px 10px;">
                    <c:set var="doctorFlag" value="${empty scholarship.doctorFlow || empty scholarship.secondDoctorFlow}"/>
                    <c:if test="${doctorFlag}">&#12288;&#12288;${scholarship.doctorAuditAdvice}${scholarship.secondAuditAdvice}</c:if>
                    <c:if test="${!doctorFlag}">&#12288;&#12288;${scholarship.doctorAuditAdvice}（导师一）<br>&#12288;&#12288;${scholarship.secondAuditAdvice}（导师二）</c:if>
                </div>
                <div style="text-align:right;margin-right:150px;margin-top:120px;">推荐人签名：</div>
                <div style="text-align:right;margin:20px 30px 0px 0px;">年<span style="padding-left:30px;"></span>月<span style="padding-left:30px;"></span>日</div>
            </div>
        </td>
    </tr>
    <tr><td colspan="7" style="border:0px;height:50px;"></td></tr>
    <tr>
        <td style="font-weight:600;">评审<br>情况</td>
        <td colspan="6" style="height:250px;text-align:left;">
            <div style="width:100%;height:100%;">
                <div style="padding:20px 10px 0px 10px;">
                    &#12288;&#12288;${scholarship.fwhAuditAdvice}
                </div>
                <div style="text-align:right;margin-right:150px;margin-top:120px;">评审委员会主任委员签名：</div>
                <div style="text-align:right;margin:20px 30px 0px 0px;">年<span style="padding-left:30px;"></span>月<span style="padding-left:30px;"></span>日</div>
            </div>
        </td>
    </tr>
    <tr>
        <td style="font-weight:600;">基<br>层<br>单<br>位<br>意<br>见</td>
        <td colspan="6" style="height:250px;text-align:left;">
            <div style="width:100%;height:100%;">
                <div style="padding:20px 10px 0px 10px;line-height:35px;">
                    &#12288;&#12288;经评审，并在本单位内公示<input type="text" style="text-align:center;width:50px;border:0px;border-bottom:1px solid black;" value="${scholarship.workDay}">个工作日，无异议，本单位申报该同学获得研究生国家奖学金。现报请研究生国家奖学金评审领导小组审定。
                </div>
                <div style="text-align:right;margin-right:150px;margin-top:30px;">基层单位主管领导签名：</div>
                <div style="text-align:right;margin-right:100px;margin-top:20px;">（基层单位公章）</div>
                <div style="text-align:right;margin:20px 30px 0px 0px;">年<span style="padding-left:30px;"></span>月<span style="padding-left:30px;"></span>日</div>
            </div>
        </td>
    </tr>
    <tr>
        <td style="font-weight:600;">培<br>养<br>单<br>位<br>意<br>见</td>
        <td colspan="6" style="height:250px;text-align:left;">
            <div style="width:100%;height:100%;">
                <div style="padding:20px 10px 0px 10px;line-height:35px;">
                    &#12288;&#12288;经审核，并在本单位公示<input type="text" style="text-align:center;width:50px;border:0px;border-bottom:1px solid black;" value="${scholarship.workDay}">个工作日，无异议，现批准该同学获得研究生国家奖学金。
                </div>
                <div style="text-align:right;margin-right:100px;margin-top:110px;">（培养单位公章）</div>
                <div style="text-align:right;margin:20px 30px 0px 0px;">年<span style="padding-left:30px;"></span>月<span style="padding-left:30px;"></span>日</div>
            </div>
        </td>
    </tr>
</table>
</body>
</html>