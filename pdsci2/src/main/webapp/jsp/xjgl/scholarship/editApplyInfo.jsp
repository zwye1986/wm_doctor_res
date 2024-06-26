<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            if($("input[name='applyFlag']").val() == "${GlobalConstant.FLAG_N}"){
                jboxTip("档案未到齐或者学费未缴清无法申请");
                return;
            }
            var applyTypeText = $("select[name='applyTypeId'] option:selected").text();
            $("input[name='applyTypeName']").val(applyTypeText);
            jboxPost("<s:url value='/xjgl/scholarship/saveApplyInfo'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="recordFlow" value="${param.recordFlow}">
            <input type="hidden" name="userFlow" value="${sysUser.userFlow}">
            <input type="hidden" name="classId" value="${eduUser.classId}">
            <input type="hidden" name="studentId" value="${eduUser.sid}">
            <input type="hidden" name="sessionNumber" value="${eduUser.period}">
            <input type="hidden" name="applyFlag" value="${applyFlag}">
            <input type="hidden" name="doctorFlow" value="${eduUser.firstTeacherFlow}">
            <input type="hidden" name="secondDoctorFlow" value="${eduUser.secondTeacherFlow}">
            <input type="hidden" name="pydwOrgFlow" value="${resDoctor.orgFlow}">
            <input type="hidden" name="fwhOrgFlow" value="${sysUser.deptFlow}">
            <div>奖助类型：
                <select name="applyTypeId" style="width:156px;" class="validate[required] select">
                    <c:forEach items="${scholarshipTypeEnumList}" var="ship">
                        <c:if test="${(empty param.recordFlow && ship.id eq param.applyTypeId) || (!empty param.recordFlow && ship.id eq scholarship.applyTypeId)}"><option value="${ship.id}">${ship.name}</option></c:if>
                    </c:forEach>
                </select>
                <input type="hidden" name="applyTypeName">
            </div>
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <c:if test="${empty param.recordFlow}">
                    <c:if test="${param.applyTypeId eq 'Gjzxj' || param.applyTypeId eq 'Zggw'}">
                        <tr>
                            <th style="text-align:right;padding-right:0px;min-width:85px;width:1%;">姓&#12288;&#12288;名：</th>
                            <td style="min-width:160px;">
                                <input type="text" class="validate[required]" name="userName" value="${sysUser.userName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:85px;width:1%;">出生年月：</th>
                            <td style="min-width:160px;">
                                <input type="text" class="validate[required]" name="userBirthday" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM'})" value="${fn:substring(sysUser.userBirthday,0,7)}"/>
                            </td>
                            <td style="text-align:center;" colspan="2" rowspan="5">
                                <img src="${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}" style="width:100px;height:150px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">籍&#12288;&#12288;贯：</th>
                            <td>
                                <input type="text" class="validate[required]" name="nativePlace" value="${sysUser.nativePlaceProvName}${sysUser.nativePlaceCityName}${sysUser.nativePlaceAreaName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                            <td>
                                <input type="text" class="validate[required]" name="politicsStatusName" value="${sysUser.politicsStatusName}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">培养单位：</th>
                            <td>
                                <input type="text" class="validate[required]" name="pydwOrgName" value="${resDoctor.orgName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">专&#12288;&#12288;业：</th>
                            <td>
                                <input type="text" class="validate[required]" name="majorName" value="${eduUser.majorName}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">导&#12288;&#12288;师：</th>
                            <td>
                                <input type="text" class="validate[required]" name="tutorName" value="${eduUser.firstTeacher}&#12288;${eduUser.secondTeacher}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                            <td>
                                <input type="text" class="validate[required]" name="sid" value="${eduUser.sid}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">所在教班：</th>
                            <td>
                                <input type="text" class="validate[required]" name="className" value="${eduUser.className}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">毕业院校：</th>
                            <td>
                                <input type="text" class="validate[required]" name="graduateSchool"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">联系方式：</th>
                            <td>
                                <input type="text" class="validate[required]" name="userPhone" value="${sysUser.userPhone}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">Q&#12288;&#12288;Q：</th>
                            <td>
                                <input type="text" class="validate[required]" name="userQq"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:60px;width:1%;">E-mail：</th>
                            <td>
                                <input type="text" class="validate[required]" name="userEmail" value="${sysUser.userEmail}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">特&#12288;&#12288;长：</th>
                            <td colspan="5">
                                <textarea style="width:98%;height:90%;" name="specialty"></textarea>
                            </td>
                        </tr>
                        <c:if test="${param.applyTypeId eq 'Zggw'}">
                            <tr>
                                <th style="text-align:right;padding-right:0px;">应聘职位：</th>
                                <td colspan="5">
                                    兼职辅导员：<input type="text" style="width:100px;" class="validate[required]" name="jzfdyDesc" placeholder="具体职位" />
                                    研究生会骨干：<input type="text" style="width:100px;" class="validate[required]" name="yjsggDesc" placeholder="具体职位" />
                                    学生助理：<input type="text" style="width:100px;" class="validate[required]" name="xszlDesc" placeholder="学生助理" />
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">个人简历：</th>
                            <td colspan="5">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="resumeDesc"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">获奖情况：</th>
                            <td colspan="5">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="awardDesc"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">备&#12288;&#12288;注：</th>
                            <td colspan="5">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="memo"></textarea>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${param.applyTypeId eq 'Yxyjs' || param.applyTypeId eq 'Yxyjsgg' || param.applyTypeId eq 'Yxbyyjs'}">
                        <tr>
                            <th style="text-align:right;padding-right:0px;min-width:80px;width:1%;">姓&#12288;&#12288;名：</th>
                            <td>
                                <input type="text" class="validate[required]" name="userName" value="${sysUser.userName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:80px;width:1%;">性&#12288;&#12288;别：</th>
                            <td>
                                <input type="text" class="validate[required]" name="sexName" value="${sysUser.sexName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:80px;width:1%;">籍&#12288;&#12288;贯：</th>
                            <td>
                                <input type="text" class="validate[required]" name="nativePlace" value="${sysUser.nativePlaceProvName}${sysUser.nativePlaceCityName}${sysUser.nativePlaceAreaName}"/>
                            </td>
                        </tr>
                        <c:if test="${param.applyTypeId eq 'Yxyjs' || param.applyTypeId eq 'Yxyjsgg'}">
                            <tr>
                                <th style="text-align:right;padding-right:0px;">民&#12288;&#12288;族：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="nationName" value="${sysUser.nationName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="politicsStatusName" value="${sysUser.politicsStatusName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="sid" value="${eduUser.sid}"/>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align:right;padding-right:0px;">培养单位：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="pydwOrgName" value="${resDoctor.orgName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">专业年级：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="periodYear" value="${eduUser.period}"/>
                                </td>
                                <c:if test="${param.applyTypeId eq 'Yxyjs'}">
                                    <th style="text-align:right;padding-right:0px;">导&#12288;&#12288;师：</th>
                                    <td>
                                        <input type="text" class="validate[required]" name="tutorName" value="${eduUser.firstTeacher}&#12288;${eduUser.secondTeacher}"/>
                                    </td>
                                </c:if>
                                <c:if test="${param.applyTypeId eq 'Yxyjsgg'}">
                                    <th style="text-align:right;padding-right:0px;">骨干职务：</th>
                                    <td>
                                        <input type="text" class="validate[required]" name="backbonePosition"/>
                                    </td>
                                </c:if>
                            </tr>
                        </c:if>
                        <c:if test="${param.applyTypeId eq 'Yxbyyjs'}">
                            <tr>
                                <th style="text-align:right;padding-right:0px;">出生年月：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="userBirthday" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM'})" value="${fn:substring(sysUser.userBirthday,0,7)}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">民&#12288;&#12288;族：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="nationName" value="${sysUser.nationName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="politicsStatusName" value="${sysUser.politicsStatusName}"/>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align:right;padding-right:0px;">培养单位：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="pydwOrgName" value="${resDoctor.orgName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">专业年级：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="periodYear" value="${eduUser.period}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="sid" value="${eduUser.sid}"/>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th colspan="6" style="text-align:center;">主要事迹：</th>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="mainDeeds"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding:4px 0px;">何时受过何&ensp;<br/>种奖励：</th>
                            <td colspan="6">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="awardExperience"></textarea>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${param.applyTypeId eq 'Gjjxj' || param.applyTypeId eq 'Gjjxjbcjh' || param.applyTypeId eq 'Xyjxj'}">
                        <tr>
                            <th rowspan="5" style="text-align:center;padding-right:0px;min-width:40px;">基本<br/>情况</th>
                            <th style="text-align:right;padding-right:0px;min-width:72px;width:1%;">姓&#12288;&#12288;名：</th>
                            <td style="min-width:160px;width:1%;">
                                <input type="text" class="validate[required]" name="userName" value="${sysUser.userName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:72px;width:1%;">出生年月：</th>
                            <td style="min-width:160px;width:1%;">
                                <input type="text" class="validate[required]" name="userBirthday" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM'})" value="${fn:substring(sysUser.userBirthday,0,7)}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:72px;width:1%;">性&#12288;&#12288;别：</th>
                            <td>
                                <input type="text" class="validate[required]" name="sexName" value="${sysUser.sexName}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                            <td>
                                <input type="text" class="validate[required]" name="politicsStatusName" value="${sysUser.politicsStatusName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">民&#12288;&#12288;族：</th>
                            <td>
                                <input type="text" class="validate[required]" name="nationName" value="${sysUser.nationName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">入学时间：</th>
                            <td>
                                <input type="text" class="validate[required]" name="periodYear" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/>
                            </td>
                        </tr>
                        <c:if test="${param.applyTypeId eq 'Gjjxj' || param.applyTypeId eq 'Gjjxjbcjh'}">
                            <tr>
                                <th style="text-align:right;padding-right:0px;">基层单位：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="baseUnit" value="${resDoctor.orgName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">专&#12288;&#12288;业：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="majorName" value="${eduUser.majorName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">攻读学位：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="studyDegree"/>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;制：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="schoolSystem"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">学习阶段：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="studyPeriod" value="${eduUser.trainTypeName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="sid" value="${eduUser.sid}"/>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${param.applyTypeId eq 'Xyjxj'}">
                            <tr>
                                <th style="text-align:right;padding-right:0px;">基层单位：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="baseUnit" value="${resDoctor.orgName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">专&#12288;&#12288;业：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="majorName" value="${eduUser.majorName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">导&#12288;&#12288;师：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="tutorName" value="${eduUser.firstTeacher}<c:if test="${!empty eduUser.firstTeacher && !empty eduUser.secondTeacher}">&ensp;</c:if>${eduUser.secondTeacher}"/>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="sid" value="${eduUser.sid}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">学习层次：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="studyPeriod" value="${eduUser.trainTypeName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">申报等级：</th>
                                <td>
                                    <select name="applyLevel" style="width:156px;" class="validate[required] select">
                                        <option value="一等奖">一等奖</option>
                                        <option value="二等奖">二等奖</option>
                                        <option value="新生奖学金">新生奖学金</option>
                                    </select>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">身份证号：</th>
                            <td colspan="6">
                                <input type="text" class="validate[required]" name="cardId" style="width:230px;" value="${sysUser.idNo}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:center;padding:4px 0px;">申请<br/>理由</th>
                            <td colspan="6">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="applyReason"></textarea>
                            </td>
                        </tr>
                    </c:if>
                </c:if>
                <c:if test="${!empty param.recordFlow}">
                    <c:if test="${scholarship.applyTypeId eq 'Gjzxj' || scholarship.applyTypeId eq 'Zggw'}">
                        <tr>
                            <th style="text-align:right;padding-right:0px;min-width:85px;width:1%;">姓&#12288;&#12288;名：</th>
                            <td style="min-width:160px;">
                                <input type="text" class="validate[required]" name="userName" value="${scholarshipForm.userName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:85px;width:1%;">出生年月：</th>
                            <td style="min-width:160px;">
                                <input type="text" class="validate[required]" name="userBirthday" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM'})" value="${scholarshipForm.userBirthday}"/>
                            </td>
                            <td style="text-align:center;" colspan="2" rowspan="5">
                                <img src="${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}" style="width:100px;height:150px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">籍&#12288;&#12288;贯：</th>
                            <td>
                                <input type="text" class="validate[required]" name="nativePlace" value="${scholarshipForm.nativePlace}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                            <td>
                                <input type="text" class="validate[required]" name="politicsStatusName" value="${scholarshipForm.politicsStatusName}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">培养单位：</th>
                            <td>
                                <input type="text" class="validate[required]" name="pydwOrgName" value="${scholarshipForm.pydwOrgName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">专&#12288;&#12288;业：</th>
                            <td>
                                <input type="text" class="validate[required]" name="majorName" value="${scholarshipForm.majorName}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">导&#12288;&#12288;师：</th>
                            <td>
                                <input type="text" class="validate[required]" name="tutorName" value="${scholarshipForm.tutorName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                            <td>
                                <input type="text" class="validate[required]" name="sid" value="${scholarshipForm.sid}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">所在教班：</th>
                            <td>
                                <input type="text" class="validate[required]" name="className" value="${scholarshipForm.className}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">毕业院校：</th>
                            <td>
                                <input type="text" class="validate[required]" name="graduateSchool" value="${scholarshipForm.graduateSchool}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">联系方式：</th>
                            <td>
                                <input type="text" class="validate[required]" name="userPhone" value="${scholarshipForm.userPhone}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">Q&#12288;&#12288;Q：</th>
                            <td>
                                <input type="text" class="validate[required]" name="userQq" value="${scholarshipForm.userQq}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:60px;width:1%;">E-mail：</th>
                            <td>
                                <input type="text" class="validate[required]" name="userEmail" value="${scholarshipForm.userEmail}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">特&#12288;&#12288;长：</th>
                            <td colspan="5">
                                <textarea style="width:98%;height:90%;" name="specialty">${scholarshipForm.specialty}</textarea>
                            </td>
                        </tr>
                        <c:if test="${scholarship.applyTypeId eq 'Zggw'}">
                            <tr>
                                <th style="text-align:right;padding-right:0px;">应聘职位：</th>
                                <td colspan="5">
                                    兼职辅导员：<input type="text" style="width:100px;" class="validate[required]" name="jzfdyDesc" placeholder="具体职位" value="${scholarshipForm.jzfdyDesc}"/>
                                    研究生会骨干：<input type="text" style="width:100px;" class="validate[required]" name="yjsggDesc" placeholder="具体职位" value="${scholarshipForm.yjsggDesc}"/>
                                    学生助理：<input type="text" style="width:100px;" class="validate[required]" name="xszlDesc" placeholder="学生助理" value="${scholarshipForm.xszlDesc}"/>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">个人简历：</th>
                            <td colspan="5">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="resumeDesc">${scholarshipForm.resumeDesc}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">获奖情况：</th>
                            <td colspan="5">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="awardDesc">${scholarshipForm.awardDesc}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">备&#12288;&#12288;注：</th>
                            <td colspan="5">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="memo">${scholarshipForm.memo}</textarea>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${scholarship.applyTypeId eq 'Yxyjs' || scholarship.applyTypeId eq 'Yxyjsgg' || scholarship.applyTypeId eq 'Yxbyyjs'}">
                        <tr>
                            <th style="text-align:right;padding-right:0px;min-width:80px;width:1%;">姓&#12288;&#12288;名：</th>
                            <td>
                                <input type="text" class="validate[required]" name="userName" value="${scholarshipForm.userName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:80px;width:1%;">性&#12288;&#12288;别：</th>
                            <td>
                                <input type="text" class="validate[required]" name="sexName" value="${scholarshipForm.sexName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:80px;width:1%;">籍&#12288;&#12288;贯：</th>
                            <td>
                                <input type="text" class="validate[required]" name="nativePlace" value="${scholarshipForm.nativePlace}"/>
                            </td>
                        </tr>
                        <c:if test="${scholarship.applyTypeId eq 'Yxyjs' || scholarship.applyTypeId eq 'Yxyjsgg'}">
                            <tr>
                                <th style="text-align:right;padding-right:0px;">民&#12288;&#12288;族：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="nationName" value="${scholarshipForm.nationName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="politicsStatusName" value="${scholarshipForm.politicsStatusName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="sid" value="${scholarshipForm.sid}"/>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align:right;padding-right:0px;">培养单位：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="pydwOrgName" value="${scholarshipForm.pydwOrgName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">专业年级：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="periodYear" value="${scholarshipForm.periodYear}"/>
                                </td>
                                <c:if test="${param.applyTypeId eq 'Yxyjs'}">
                                    <th style="text-align:right;padding-right:0px;">导&#12288;&#12288;师：</th>
                                    <td>
                                        <input type="text" class="validate[required]" name="tutorName" value="${scholarshipForm.tutorName}"/>
                                    </td>
                                </c:if>
                                <c:if test="${scholarship.applyTypeId eq 'Yxyjsgg'}">
                                    <th style="text-align:right;padding-right:0px;">骨干职务：</th>
                                    <td>
                                        <input type="text" class="validate[required]" name="backbonePosition" value="${scholarshipForm.backbonePosition}"/>
                                    </td>
                                </c:if>
                            </tr>
                        </c:if>
                        <c:if test="${scholarship.applyTypeId eq 'Yxbyyjs'}">
                            <tr>
                                <th style="text-align:right;padding-right:0px;">出生年月：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="userBirthday" onClick="WdatePicker({dateFmt:'yyyy-MM'})" value="${scholarshipForm.userBirthday}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">民&#12288;&#12288;族：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="nationName" value="${scholarshipForm.nationName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="politicsStatusName" value="${scholarshipForm.politicsStatusName}"/>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align:right;padding-right:0px;">培养单位：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="pydwOrgName" value="${scholarshipForm.pydwOrgName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">专业年级：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="periodYear" value="${scholarshipForm.periodYear}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="sid" value="${scholarshipForm.sid}"/>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th colspan="6" style="text-align:center;">主要事迹：</th>
                        </tr>
                        <tr>
                            <td colspan="6">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="mainDeeds">${scholarshipForm.mainDeeds}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding:4px 0px;">何时受过何&ensp;<br/>种奖励：</th>
                            <td colspan="6">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="awardExperience">${scholarshipForm.awardExperience}</textarea>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${scholarship.applyTypeId eq 'Gjjxj' || scholarship.applyTypeId eq 'Gjjxjbcjh' || scholarship.applyTypeId eq 'Xyjxj'}">
                        <tr>
                            <th rowspan="5" style="text-align:center;padding-right:0px;min-width:40px;">基本<br/>情况</th>
                            <th style="text-align:right;padding-right:0px;min-width:72px;width:1%;">姓&#12288;&#12288;名：</th>
                            <td style="min-width:160px;width:1%;">
                                <input type="text" class="validate[required]" name="userName" value="${scholarshipForm.userName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:72px;width:1%;">出生年月：</th>
                            <td style="min-width:160px;width:1%;">
                                <input type="text" class="validate[required]" name="userBirthday" onClick="WdatePicker({dateFmt:'yyyy-MM'})" value="${scholarshipForm.userBirthday}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;min-width:72px;width:1%;">性&#12288;&#12288;别：</th>
                            <td>
                                <input type="text" class="validate[required]" name="sexName" value="${scholarshipForm.sexName}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">政治面貌：</th>
                            <td>
                                <input type="text" class="validate[required]" name="politicsStatusName" value="${scholarshipForm.politicsStatusName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">民&#12288;&#12288;族：</th>
                            <td>
                                <input type="text" class="validate[required]" name="nationName" value="${scholarshipForm.nationName}"/>
                            </td>
                            <th style="text-align:right;padding-right:0px;">入学时间：</th>
                            <td>
                                <input type="text" class="validate[required]" name="periodYear" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM'})" value="${scholarshipForm.periodYear}"/>
                            </td>
                        </tr>
                        <c:if test="${scholarship.applyTypeId eq 'Gjjxj' || scholarship.applyTypeId eq 'Gjjxjbcjh'}">
                            <tr>
                                <th style="text-align:right;padding-right:0px;">基层单位：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="baseUnit" value="${scholarshipForm.baseUnit}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">专&#12288;&#12288;业：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="majorName" value="${scholarshipForm.majorName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">攻读学位：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="studyDegree" value="${scholarshipForm.studyDegree}"/>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;制：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="schoolSystem" value="${scholarshipForm.schoolSystem}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">学习阶段：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="studyPeriod" value="${scholarshipForm.studyPeriod}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="sid" value="${scholarshipForm.sid}"/>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${scholarship.applyTypeId eq 'Xyjxj'}">
                            <tr>
                                <th style="text-align:right;padding-right:0px;">基层单位：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="baseUnit" value="${scholarshipForm.baseUnit}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">专&#12288;&#12288;业：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="majorName" value="${scholarshipForm.majorName}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">导&#12288;&#12288;师：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="tutorName" value="${scholarshipForm.tutorName}"/>
                                </td>
                            </tr>
                            <tr>
                                <th style="text-align:right;padding-right:0px;">学&#12288;&#12288;号：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="sid" value="${scholarshipForm.sid}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">学习层次：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="studyPeriod" value="${scholarshipForm.studyPeriod}"/>
                                </td>
                                <th style="text-align:right;padding-right:0px;">申报等级：</th>
                                <td>
                                    <input type="text" class="validate[required]" name="applyLevel" value="${scholarship.applyLevel}"/>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th style="text-align:right;padding-right:0px;">身份证号：</th>
                            <td colspan="6">
                                <input type="text" class="validate[required]" name="cardId" style="width:230px;" value="${scholarshipForm.cardId}"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="text-align:center;padding:4px 0px;">申请<br/>理由</th>
                            <td colspan="6">
                                <textarea style="width:98%;height:90%;" class="validate[required]" name="applyReason">${scholarshipForm.applyReason}</textarea>
                            </td>
                        </tr>
                    </c:if>
                </c:if>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>