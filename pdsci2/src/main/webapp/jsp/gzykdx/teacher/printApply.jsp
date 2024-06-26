<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <script src="<s:url value='/js/jquery-1.9.1.min.js'/>" type="text/javascript"></script>
    <style type="text/css">
        .basic1{
            border:1px solid #D0E3F2;
        }
        .content div{margin-top:5px;}
        .basic1 th,.basic1 td { border-right:1px solid #D0E3F2; border-bottom:1px solid #D0E3F2; height:35px;}
        .basic1 tbody th { color:#333; height:35px; text-align:right; padding-right:10px; background:#ECF2FA;}
        .basic1 td { text-align:left; padding-left:10px; line-height:35px;}
        .basic1{
            border:1px solid #D0E3F2;
        }
        .basic1 .bs_name1, .bs_tb .bs_name1,.bs_title{
            background:#6cb4e7;
            font-size:14px;
            line-height:35px;
            font-weight:bold;
            color:#fff;
            text-align:left;
            padding-left:10px;
        }
        .bs_tb .add, .basic1 .add{
            background:#6cb4e7;
            font-size:14px;
            line-height:35px;
            font-weight:bold;
            color:#fff;
            text-align:left;
            padding-left:10px;
            background-repeat:no-repeat;
            background-position:98% center;
            cursor:pointer;
        }

        .bs_name1 img{
            border:0;
            position:relative;
            float:right;
            padding-top:10px;
            padding-right:10px;
            vertical-align:middle;
        }
        .basic_title{
            line-height:40px;
            padding-left:20px;
            font-size:16px;
            color:#070707;
        }
        .basic_title span{padding-right:30px;}
    </style>
    <script>
        $(function(){
            window.print();
        });
    </script>
</head>
<body>
    <div >
        <div >
            <div>
                <table class="basic1" style="width: 100%">
                    <tr>
                        <th colspan="8" style="text-align: center">
                            广州医科大学${targetApply.recruitYear}年硕士研究生招生申请表</th>
                    </tr>
                    <tr>
                        <th>所在单位
                        </th>
                        <td colspan="7">
                            <label style="text-align: center">${targetApply.orgName}</label>
                        </td>
                    </tr>

                    <tr>
                        <th>姓名
                        </th>
                        <td>
                            <label style="text-align: center">${user.userName}</label>
                        </td>
                        <th>性别
                        </th>
                        <td>
                            <label style="text-align: center">${user.sexName}</label>
                        </td>
                        <th>出生年月
                        </th>
                        <td>
                            <label style="text-align: center">${user.userBirthday}</label>
                        </td>
                        <th>年龄
                        </th>
                        <td>
                            <label style="text-align: center">${extInfo.age}</label>
                        </td>
                    </tr>

                    <tr>
                        <th>身份证号码（必填）
                        </th>
                        <td colspan="3">
                            <label style="text-align: center">${user.idNo}</label>
                        </td>
                        <th>手机号码
                        </th>
                        <td colspan="3">
                            <label style="text-align: center">${user.userPhone}</label>
                        </td>
                    </tr>

                    <tr>
                        <th>最高学历
                        </th>
                        <td colspan="2">
                            <label style="text-align: center">${user.educationName}</label>
                        </td>
                        <th>最高学位
                        </th>
                        <td colspan="2">
                            <label style="text-align: center">${user.degreeName}</label>
                        </td>
                        <th>职称
                        </th>
                        <td>
                            <label style="text-align: center">${user.titleName}</label>
                        </td>
                    </tr>
                    <tr>
                        <th>最高学历毕业学校
                        </th>
                        <td colspan="3">
                            <label style="text-align: center">${extInfo.educationSchool}</label>
                        </td>
                        <th>最高学历毕业时间
                        </th>
                        <td colspan="3">
                            <label style="text-align: center">${extInfo.educationDate}</label>
                        </td>
                    </tr>

                    <tr>
                        <th>最高学位获得单位
                        </th>
                        <td colspan="3">
                            <label style="text-align: center">${extInfo.degreeOrg}</label>
                        </td>
                        <th>最高学位获得时间
                        </th>
                        <td colspan="3">
                            <label style="text-align: center">${extInfo.degreeDate}</label>
                        </td>
                    </tr>

                    <tr>
                        <th>所在领域工作年限
                        </th>
                        <td colspan="3">
                            <label style="text-align: center">${extInfo.workAreaYear}</label>
                        </td>
                        <th>参加工作时间
                        </th>
                        <td colspan="3">
                            <label style="text-align: center">${extInfo.workDate}</label>
                        </td>
                    </tr>

                    <tr>
                        <th>申请招生专业
                        </th>
                        <td colspan="3">
                            <label style="text-align: center">${targetApply.speName}[${targetApply.speId}]</label>
                        </td>
                        <th>研究方向
                        </th>
                        <td colspan="3" style="line-height: 130%;max-width: 300px;">
                            <label style="text-align: center;">${targetApply.researchDirection}</label>
                        </td>
                    </tr>

                    <tr>
                        <th>申请招生类型
                        </th>
                        <td colspan="7">
                            <input type="checkbox" name="isAcademic" id="isAcademic" value="Y"
                                   <c:if test="${targetApply.isAcademic eq 'Y'}">checked="checked"</c:if>/><label for="isAcademic">&nbsp;学术学位硕士研究生&nbsp;</label>
                            <input type="checkbox" name="isSpecialized" id="isSpecialized" value="Y"
                                   <c:if test="${targetApply.isSpecialized eq 'Y'}">checked="checked"</c:if>/><label for="isSpecialized">&nbsp;专业学位硕士研究生&nbsp;</label>
                        </td>
                    </tr>

                </table>
                <table class="basic1" style="width: 100%">
                    <tr>
                        <th colspan="6" class="bs_name1" style="text-align: center;">一、发表论文情况（第一作者或通讯作者）</th>
                    </tr>
                    <tr>
                        <th rowspan="${empty teacherThesisList?1:fn:length(teacherThesisList)+1}" style="text-align: center; width: 15%;">
                            1、导师本人近三年发表论文情况（可另加行）</th>
                        <th style="text-align: center; width: 15%;">刊物名称</th>
                        <th style="text-align: center; width: 25%;">论文题目</th>
                        <th style="text-align: center; width: 10%;">作者排名</th>
                        <th style="text-align: center; width: 10%;">发表时间</th>
                        <th style="text-align: center; width: 10%;">SCI点数</th>
                    </tr>
                    <c:if test="${not empty teacherThesisList}">
                        <c:forEach items="${teacherThesisList}" var="teacherThesis">
                            <tr>
                                <td><label style="text-align: center">${teacherThesis.teacherThesis.thesisName}</label></td>
                                <td><label style="text-align: center">${teacherThesis.teacherThesis.thesisTitle}</label></td>
                                <td><label style="text-align: center">${teacherThesis.teacherThesis.authorRankingName}</label></td>
                                <td><label style="text-align: center">${teacherThesis.teacherThesis.reportTime}</label></td>
                                <td><label style="text-align: center">${teacherThesis.teacherThesis.sciPoint}</label></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <tr>
                        <th rowspan="${empty doctorThesisList?1:fn:length(doctorThesisList)+1}" style="text-align: center; width: 15%;">
                            2、近三年指导研究生发表论文情况（可另加行）</th>
                        <th style="text-align: center; width: 15%;">刊物名称</th>
                        <th style="text-align: center; width: 25%;">论文题目</th>
                        <th style="text-align: center; width: 10%;">作者排名</th>
                        <th style="text-align: center; width: 10%;">发表时间</th>
                        <th style="text-align: center; width: 10%;">SCI点数</th>
                    </tr>
                    <c:if test="${not empty doctorThesisList}">
                        <c:forEach items="${doctorThesisList}" var="doctorThesis">
                            <tr>
                                <td><label style="text-align: center">${doctorThesis.doctorThesis.thesisName}</label></td>
                                <td><label style="text-align: center">${doctorThesis.doctorThesis.thesisTitle}</label></td>
                                <td><label style="text-align: center">${doctorThesis.doctorThesis.authorRankingName}</label></td>
                                <td><label style="text-align: center">${doctorThesis.doctorThesis.reportTime}</label></td>
                                <td><label style="text-align: center">${doctorThesis.doctorThesis.sciPoint}</label></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
                <table class="basic1" style="width: 100%">
                    <tr>
                        <th colspan="3" style="width: 130px;text-align: left">&#12288;导师近三年发表SCI论文篇数:&#12288;<c:if test="${not empty teacherThesisList}">${fn:length(teacherThesisList)}</c:if></th>
                        <th colspan="3" style="width: 130px;text-align: left">&#12288;指导硕/博士生近三年发表SCI论文篇数:&#12288;<c:if test="${not empty teacherThesisList}">${fn:length(doctorThesisList)}</c:if></th>
                    </tr>
                </table>
                <table class="basic1" style="width: 100%">
                    <tr>
                        <th colspan="6" class="bs_name1" style="text-align: center;">二、科研项目情况（可另加行）</th>
                    </tr>
                    <tr>
                        <th style="text-align: center; width: 20%;">在研项目名称</th>
                        <th style="text-align: center; width: 10%;">排名</th>
                        <th style="text-align: center; width: 10%;">立项时间</th>
                        <th style="text-align: center; width: 10%;">立项单位</th>
                        <th style="text-align: center; width: 10%;">项目编号</th>
                        <th style="text-align: center; width: 10%;">资助金额(万元)</th>
                    </tr>
                    <c:if test="${not empty researchProjectList}">
                        <c:forEach items="${researchProjectList}" var="researchProject">
                            <tr>
                                <td><label style="text-align: center">${researchProject.researchProject.thesisName}</label></td>
                                <td><label style="text-align: center">${researchProject.researchProject.authorRankingName}</label></td>
                                <td><label style="text-align: center">${researchProject.researchProject.reportTime}</label></td>
                                <td><label style="text-align: center">${researchProject.researchProject.productWorkName}</label></td>
                                <td><label style="text-align: center">${researchProject.researchProject.productCode}</label></td>
                                <td><label style="text-align: center">${researchProject.researchProject.productAmount}</label></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
                <table class="basic1" style="width: 100%">
                    <tr>
                        <th colspan="7" class="bs_name1" style="text-align: left;">申请人申报签名</th>
                    </tr>
                    <tr>
                        <th colspan="5" style="height: 60px;text-align: right;border-right-width: 0px;">
                            本人所填写的以上信息全部属实。
                        </th>
                        <th colspan="2" style="height: 60px;text-align: right;line-height: 150%;border-left-width: 0px;">
                            <br/><br/>
                            &#12288;&#12288;&#12288;申请人签名：${targetApply.userName}<br/>
                            &#12288;${targetApply.teacherApplyTime==null?'   ':fn:split(targetApply.teacherApplyTime,'-')[0]}年${targetApply.teacherApplyTime==null?'   ':fn:split(targetApply.teacherApplyTime,'-')[1]}月${targetApply.teacherApplyTime==null?'   ':fn:split(targetApply.teacherApplyTime,'-')[2]}日
                        </th>
                    </tr>
                </table>
            </div>
                <table class="basic1" style="width: 100%">
                    <tr>
                        <th colspan="4" class="bs_name1" style="text-align: left;">所在单位审核意见</th>
                        <th colspan="3" class="bs_name1" style="text-align: left;">学校审核意见</th>
                    </tr>
                    <tr>
                        <td colspan="4" style="height: 60px;text-align: center;">
                            <label style="text-align: center">${targetApply.orgAuditAdvice}</label>
                        </td>
                        <td colspan="3" style="height: 60px;text-align: center;">
                            <label style="text-align: center">${targetApply.schoolAuditAdvice}</label>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="4" style="height: 30px;text-align: right;line-height: 150%">
                            负责人签名：${targetApply.orgUserName}&#12288;&#12288;&#12288;<br/>
                            &#12288;${targetApply.orgAuditTime==null?'   ':fn:split(targetApply.orgAuditTime,'-')[0]}年${targetApply.orgAuditTime==null?'   ':fn:split(targetApply.orgAuditTime,'-')[1]}月${targetApply.orgAuditTime==null?'   ':fn:split(targetApply.orgAuditTime,'-')[2]}日(盖章)&#12288;&#12288;
                        </th>
                        <th colspan="3" style="height: 30px;text-align: right;line-height: 150%">
                            <br/>
                            ${targetApply.schoolAuditTime==null?'   ':fn:split(targetApply.schoolAuditTime,'-')[0]}年${targetApply.schoolAuditTime==null?'   ':fn:split(targetApply.schoolAuditTime,'-')[1]}月${targetApply.schoolAuditTime==null?'   ':fn:split(targetApply.schoolAuditTime,'-')[2]}日(盖章)&#12288;&#12288;
                        </th>
                    </tr>
                </table>
        </div>
    </div>
</body>
</html>