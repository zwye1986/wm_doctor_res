<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
</head>
<body>
<div class="mainright">
    <div class="content">
            <div>奖助类型：
                <select name="applyTypeId" style="width:156px;" class="select">
                    <c:forEach items="${scholarshipTypeEnumList}" var="ship">
                        <c:if test="${ship.id eq scholarship.applyTypeId}"><option value="${ship.id}">${ship.name}</option></c:if>
                    </c:forEach>
                </select>
            </div>
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <c:if test="${scholarship.applyTypeId eq 'Gjzxj' || scholarship.applyTypeId eq 'Zggw'}">
                    <tr>
                        <th style="text-align:right;padding-right:0px;min-width:85px;width:1%;">姓&#12288;&#12288;名：</th>
                        <td style="min-width:160px;">${scholarshipForm.userName}</td>
                        <th style="text-align:right;padding-right:0px;min-width:85px;width:1%;">出生年月：</th>
                        <td style="min-width:160px;">${scholarshipForm.userBirthday}</td>
                        <td style="text-align:center;min-width:150px;" colspan="2" rowspan="5">
                            <img src="${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}" style="width:100px;height:150px;"/>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding-right:0px;">籍&#12288;&#12288;贯：</th>
                        <td>${scholarshipForm.nativePlace}</td>
                        <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                        <td>${scholarshipForm.politicsStatusName}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding-right:0px;">培养单位：</th>
                        <td>${scholarshipForm.pydwOrgName}</td>
                        <th style="text-align:right;padding-right:0px;">专&#12288;&#12288;业：</th>
                        <td>${scholarshipForm.majorName}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding-right:0px;">导&#12288;&#12288;师：</th>
                        <td>${scholarshipForm.tutorName}</td>
                        <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                        <td>${scholarshipForm.sid}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding-right:0px;">所在教班：</th>
                        <td>${scholarshipForm.className}</td>
                        <th style="text-align:right;padding-right:0px;">毕业院校：</th>
                        <td>${scholarshipForm.graduateSchool}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding-right:0px;">联系方式：</th>
                        <td>${scholarshipForm.userPhone}</td>
                        <th style="text-align:right;padding-right:0px;">Q&#12288;&#12288;Q：</th>
                        <td>${scholarshipForm.userQq}</td>
                        <th style="text-align:right;padding-right:0px;min-width:60px;width:1%;">E-mail：</th>
                        <td>${scholarshipForm.userEmail}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding-right:0px;">特&#12288;&#12288;长：</th>
                        <td colspan="5">
                            <textarea style="width:98%;height:90%;" readonly="readonly">${scholarshipForm.specialty}</textarea>
                        </td>
                    </tr>
                    <c:if test="${scholarship.applyTypeId eq 'Zggw'}">
                        <tr>
                            <th style="text-align:right;padding-right:0px;">应聘职位：</th>
                            <td colspan="5">
                                兼职辅导员：${scholarshipForm.jzfdyDesc}&#12288;
                                研究生会骨干：${scholarshipForm.yjsggDesc}&#12288;
                                学生助理：${scholarshipForm.xszlDesc}
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <th style="text-align:right;padding-right:0px;">个人简历：</th>
                        <td colspan="5">
                            <textarea style="width:98%;height:90%;" readonly="readonly">${scholarshipForm.resumeDesc}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding-right:0px;">获奖情况：</th>
                        <td colspan="5">
                            <textarea style="width:98%;height:90%;" readonly="readonly">${scholarshipForm.awardDesc}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding-right:0px;">备&#12288;&#12288;注：</th>
                        <td colspan="5">
                            <textarea style="width:98%;height:90%;" readonly="readonly">${scholarshipForm.memo}</textarea>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${scholarship.applyTypeId eq 'Yxyjs' || scholarship.applyTypeId eq 'Yxyjsgg' || scholarship.applyTypeId eq 'Yxbyyjs'}">
                    <tr>
                        <th style="text-align:right;padding-right:0px;min-width:80px;width:1%;">姓&#12288;&#12288;名：</th>
                        <td style="min-width:140px;">${scholarshipForm.userName}</td>
                        <th style="text-align:right;padding-right:0px;min-width:80px;width:1%;">性&#12288;&#12288;别：</th>
                        <td style="min-width:140px;">${scholarshipForm.sexName}</td>
                        <th style="text-align:right;padding-right:0px;min-width:80px;width:1%;">籍&#12288;&#12288;贯：</th>
                        <td style="min-width:140px;">${scholarshipForm.nativePlace}</td>
                    </tr>
                    <c:if test="${scholarship.applyTypeId eq 'Yxyjs' || scholarship.applyTypeId eq 'Yxyjsgg'}">
                        <tr>
                            <th style="text-align:right;padding-right:0px;">民&#12288;&#12288;族：</th>
                            <td>${scholarshipForm.nationName}</td>
                            <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                            <td>${scholarshipForm.politicsStatusName}</td>
                            <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                            <td>${scholarshipForm.sid}</td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">培养单位：</th>
                            <td>${scholarshipForm.pydwOrgName}</td>
                            <th style="text-align:right;padding-right:0px;">专业年级：</th>
                            <td>${scholarshipForm.periodYear}</td>
                            <c:if test="${scholarship.applyTypeId eq 'Yxyjs'}">
                                <th style="text-align:right;padding-right:0px;">导&#12288;&#12288;师：</th>
                                <td>${scholarshipForm.tutorName}</td>
                            </c:if>
                            <c:if test="${scholarship.applyTypeId eq 'Yxyjsgg'}">
                                <th style="text-align:right;padding-right:0px;">骨干职务：</th>
                                <td>${scholarshipForm.backbonePosition}</td>
                            </c:if>
                        </tr>
                    </c:if>
                    <c:if test="${scholarship.applyTypeId eq 'Yxbyyjs'}">
                        <tr>
                            <th style="text-align:right;padding-right:0px;">出生年月：</th>
                            <td>${scholarshipForm.userBirthday}</td>
                            <th style="text-align:right;padding-right:0px;">民&#12288;&#12288;族：</th>
                            <td>${scholarshipForm.nationName}</td>
                            <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                            <td>${scholarshipForm.politicsStatusName}</td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">培养单位：</th>
                            <td>${scholarshipForm.pydwOrgName}</td>
                            <th style="text-align:right;padding-right:0px;">专业年级：</th>
                            <td>${scholarshipForm.periodYear}</td>
                            <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                            <td>${scholarshipForm.sid}</td>
                        </tr>
                    </c:if>
                    <tr>
                        <th colspan="6" style="text-align:center;">主要事迹：</th>
                    </tr>
                    <tr>
                        <td colspan="6">
                            <textarea style="width:98%;height:90%;" readonly="readonly">${scholarshipForm.mainDeeds}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:4px 0px;">何时受过何&ensp;<br/>种奖励：</th>
                        <td colspan="6">
                            <textarea style="width:98%;height:90%;" readonly="readonly">${scholarshipForm.awardExperience}</textarea>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${scholarship.applyTypeId eq 'Gjjxj' || scholarship.applyTypeId eq 'Gjjxjbcjh' || scholarship.applyTypeId eq 'Xyjxj'}">
                    <tr>
                        <th rowspan="5" style="text-align:center;padding-right:0px;min-width:72px;width:1%;">基本情况</th>
                        <th style="text-align:right;padding-right:0px;min-width:72px;width:1%;">姓&#12288;&#12288;名：</th>
                        <td style="min-width:100px">${scholarshipForm.userName}</td>
                        <th style="text-align:right;padding-right:0px;min-width:72px;width:1%;">出生年月：</th>
                        <td style="min-width:100px;">${scholarshipForm.userBirthday}</td>
                        <th style="text-align:right;padding-right:0px;min-width:72px;width:1%;">性&#12288;&#12288;别：</th>
                        <td style="min-width:100px;">${scholarshipForm.sexName}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                        <td>${scholarshipForm.politicsStatusName}</td>
                        <th style="text-align:right;padding-right:0px;">民&#12288;&#12288;族：</th>
                        <td>${scholarshipForm.nationName}</td>
                        <th style="text-align:right;padding-right:0px;">入学时间：</th>
                        <td>${scholarshipForm.periodYear}</td>
                    </tr>
                    <c:if test="${scholarship.applyTypeId eq 'Gjjxj' || scholarship.applyTypeId eq 'Gjjxjbcjh'}">
                        <tr>
                            <th style="text-align:right;padding-right:0px;">基层单位：</th>
                            <td>${scholarshipForm.baseUnit}</td>
                            <th style="text-align:right;padding-right:0px;">专&#12288;&#12288;业：</th>
                            <td>${scholarshipForm.majorName}</td>
                            <th style="text-align:right;padding-right:0px;">攻读学位：</th>
                            <td>${scholarshipForm.studyDegree}</td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;制：</th>
                            <td>${scholarshipForm.schoolSystem}</td>
                            <th style="text-align:right;padding-right:0px;">学习阶段：</th>
                            <td>${scholarshipForm.studyPeriod}</td>
                            <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                            <td>${scholarshipForm.sid}</td>
                        </tr>
                    </c:if>
                    <c:if test="${scholarship.applyTypeId eq 'Xyjxj'}">
                        <tr>
                            <th style="text-align:right;padding-right:0px;">基层单位：</th>
                            <td>${scholarshipForm.baseUnit}</td>
                            <th style="text-align:right;padding-right:0px;">专&#12288;&#12288;业：</th>
                            <td>${scholarshipForm.majorName}</td>
                            <th style="text-align:right;padding-right:0px;">导&#12288;&#12288;师：</th>
                            <td>${scholarshipForm.tutorName}</td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                            <td>${scholarshipForm.sid}</td>
                            <th style="text-align:right;padding-right:0px;">学习层次：</th>
                            <td>${scholarshipForm.studyPeriod}</td>
                            <th style="text-align:right;padding-right:0px;">申报等级：</th>
                            <td>${scholarship.applyLevel}</td>
                        </tr>
                    </c:if>
                    <tr>
                        <th style="text-align:right;padding-right:0px;">身份证号：</th>
                        <td colspan="6">${scholarshipForm.cardId}</td>
                    </tr>
                    <tr>
                        <th style="text-align:center;padding-right:0px;">申请理由</th>
                        <td colspan="6">
                            <textarea style="width:98%;height:90%;" readonly="readonly">${scholarshipForm.applyReason}</textarea>
                        </td>
                    </tr>
                </c:if>
            </table>
        <div style="text-align: center;margin-top:20px;">
            <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
        </div>
    </div>
</div>
</body>
</html>