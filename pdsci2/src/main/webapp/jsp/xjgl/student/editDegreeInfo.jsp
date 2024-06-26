<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        .holderClass::-webkit-input-placeholder {  /* WebKit browsers */
            font-size: 12px;
        }
        .holderClass:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            font-size: 12px;
        }
        .holderClass::-moz-placeholder { /* Mozilla Firefox 19+ */
            font-size: 12px;
        }
        .holderClass:-ms-input-placeholder { /* Internet Explorer 10+ */
            font-size: 12px;
        }
        input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            $("#politicsStatusName").val($("#politicsStatusId option:selected").text());
            jboxPost("<s:url value='/xjgl/user/saveDegreeInfo'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].window.toPage(1);
                    jboxClose();
                }
            });
        }
        function loadMajorName(obj){
            var data=$(obj).val();
            <c:forEach items="${userDegreeMajorEnumList}" var="degree">
                if(data=='${degree.id}'){
                    $(obj).val('${degree.name}');
                }
            </c:forEach>
        }
        function loadSubjectName(obj){
            var data=$(obj).val();
            <c:forEach items="${userDegreeSubjectEnumList}" var="sub">
                if(data=='${sub.id}'){
                    $(obj).val('${sub.name}');
                }
            </c:forEach>
        }
        $(document).ready(function () {
            loadMajorName($("input[name='underMajor']"));
            loadMajorName($("input[name='gotMasterCertSpe']"));
            loadSubjectName($("input[name='gotBachelorCertSubject']"));
            loadSubjectName($("input[name='masterSubject']"));
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="userFlow" value="${main.userFlow}">
            <input type="hidden" name="sid" value="${empty param.userFlow?eduUser.sid:main.sid}">
            <input type="hidden" name="userName" value="${empty param.userFlow?sysUser.userName:main.userName}">
            <input type="hidden" name="sexId" value="${empty param.userFlow?sysUser.sexId:main.sexId}">
            <input type="hidden" name="sexName" value="${empty param.userFlow?sysUser.sexName:main.sexName}">
            <input type="hidden" name="nationId" value="${empty param.userFlow?sysUser.nationId:main.nationId}">
            <input type="hidden" name="nationName" value="${empty param.userFlow?sysUser.nationName:main.nationName}">
            <input type="hidden" name="trainCategoryId" value="${empty param.userFlow?eduUser.trainCategoryId:main.trainCategoryId}">
            <input type="hidden" name="trainCategoryName" value="${empty param.userFlow?eduUser.trainCategoryName:main.trainCategoryName}">
            <input type="hidden" name="trainTypeId" value="${empty param.userFlow?eduUser.trainTypeId:main.trainTypeId}">
            <input type="hidden" name="trainTypeName" value="${empty param.userFlow?eduUser.trainTypeName:main.trainTypeName}">
            <input type="hidden" name="userBirthday" value="${empty param.userFlow?sysUser.userBirthday:main.userBirthday}">
            <input type="hidden" name="domicilePlaceId" value="${empty param.userFlow?sysUser.domicilePlaceId:main.domicilePlaceId}">
            <input type="hidden" name="domicilePlaceName" value="${empty param.userFlow?sysUser.domicilePlaceName:main.domicilePlaceName}">
            <input type="hidden" name="cretTypeId" value="${empty param.userFlow?sysUser.cretTypeId:main.cretTypeId}">
            <input type="hidden" name="cretTypeName" value="${empty param.userFlow?sysUser.cretTypeName:main.cretTypeName}">
            <input type="hidden" name="idNo" value="${empty param.userFlow?sysUser.idNo:main.idNo}">
            <input type="hidden" name="period" value="${empty param.userFlow?eduUser.period:main.period}">
            <input type="hidden" name="firstTeacherFlow" value="${empty param.userFlow?eduUser.firstTeacherFlow:main.firstTeacherFlow}">
            <input type="hidden" name="firstTeacher" value="${empty param.userFlow?eduUser.firstTeacher:main.firstTeacher}">
            <input type="hidden" name="secondTeacherFlow" value="${empty param.userFlow?eduUser.secondTeacherFlow:main.secondTeacherFlow}">
            <input type="hidden" name="secondTeacher" value="${empty param.userFlow?eduUser.secondTeacher:main.secondTeacher}">
            <input type="hidden" name="studentCode" value="${empty param.userFlow?eduUser.studentCode:main.studentCode}">
            <input type="hidden" name="majorId" value="${empty param.userFlow?eduUser.majorId:main.majorId}">
            <input type="hidden" name="majorName" value="${empty param.userFlow?eduUser.majorName:main.majorName}">
            <input type="hidden" name="orgFlow" value="${empty param.userFlow?doctor.orgFlow:main.orgFlow}">
            <input type="hidden" name="orgName" value="${empty param.userFlow?doctor.orgName:main.orgName}">
            <input type="hidden" name="deptFlow" value="${empty param.userFlow?sysUser.deptFlow:main.deptFlow}">
            <input type="hidden" name="deptName" value="${empty param.userFlow?sysUser.deptName:main.deptName}">
            <table class="basic" width="100%">
                <tr>
                    <th style="width:14%">学号：</th>
                    <td style="width:19%">${empty param.userFlow?eduUser.sid:main.sid}</td>
                    <th style="width:14%">姓名：</th>
                    <td style="width:19%">${empty param.userFlow?sysUser.userName:main.userName}</td>
                    <th style="width:14%">性别：</th>
                    <td style="width:19%">${empty param.userFlow?sysUser.sexName:main.sexName}</td>
                </tr>
                <tr>
                    <th>培养类型：</th>
                    <td>${empty param.userFlow?eduUser.trainCategoryName:main.trainCategoryName}</td>
                    <th>培养层次：</th>
                    <td>${empty param.userFlow?eduUser.trainTypeName:main.trainTypeName}</td>
                    <th>民族：</th>
                    <td>${empty param.userFlow?sysUser.nationName:main.nationName}</td>
                </tr>
                <tr>
                    <th>政治面貌：</th>
                    <td>
                        <select style="width: 144px;" name="politicsStatusId" id="politicsStatusId" class="validate[required]">
                            <option/>
                            <c:forEach items="${politicsAppearanceEnumList}" var="politics">
                                <option value="${politics.id}" ${(empty param.userFlow?sysUser.politicsStatusId:main.politicsStatusId) eq politics.id?'selected':''}>${politics.name}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="politicsStatusName" id="politicsStatusName">
                    </td>
                    <th>出生日期：</th>
                    <td>${empty param.userFlow?sysUser.userBirthday:main.userBirthday}</td>
                    <th>入学前户口所在地：</th>
                    <td>${empty param.userFlow?fn:replace(sysUser.domicilePlaceName, ',', ''):fn:replace(main.domicilePlaceName, ',', '')}</td>
                </tr>
                <tr>
                    <th>证件类型：</th>
                    <td>${empty param.userFlow?sysUser.cretTypeName:main.cretTypeName}</td>
                    <th>证件号码：</th>
                    <td>${empty param.userFlow?sysUser.idNo:main.idNo}</td>
                    <th>国家或地区：</th>
                    <td>
                        <select name="countryArea" class="select" style="width:137px;">
                            <option></option>
                            <c:forEach items="${userCountryAreaEnumList}" var="area">
                                <option value="${area.id}" ${main.countryArea eq area.id?'selected':''}>${area.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>入学年级：</th>
                    <td>${empty param.userFlow?eduUser.period:main.period}</td>
                    <th>毕业时间：</th>
                    <td><input type="text" class="validate[required]" name="graduateTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${empty param.userFlow?eduUser.graduateTime:main.graduateTime}"></td>
                    <th>导师姓名：</th>
                    <td><c:if test="${empty param.userFlow}">${eduUser.firstTeacher}${!empty eduUser.firstTeacher && !empty eduUser.secondTeacher?'，':''}${eduUser.secondTeacher}</c:if>
                        <c:if test="${!empty param.userFlow}">${main.firstTeacher}${!empty main.firstTeacher && !empty main.secondTeacher?'，':''}${main.secondTeacher}</c:if>
                    </td>
                </tr>
                <tr>
                    <th>招录方式：</th>
                    <td>
                        <select name="recruitType" class="select" style="width:137px;">
                            <option></option>
                            <c:forEach items="${dictTypeEnumRecruitTypeList}" var="dict">
                                <option value="${dict.dictId}" ${(empty param.userFlow && extForm.recruitType eq dict.dictId) || main.recruitType eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>考生编号：</th>
                    <td>${empty param.userFlow?eduUser.studentCode:main.studentCode}</td>
                    <th>学习方式：</th>
                    <td>
                        <select name="studyType" class="validate[required] select" style="width:137px;">
                            <option></option>
                            <option value="tc" ${main.studyType eq 'tc'?'selected':''}>脱产</option>
                            <option value="btc" ${main.studyType eq 'btc'?'selected':''}>半脱产</option>
                            <option value="yy" ${main.studyType eq 'yy'?'selected':''}>业余</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>考试方式：</th>
                    <td>
                        <select name="testType" class="validate[required] select" style="width:137px;">
                            <option></option>
                            <option value="qgtk" ${main.testType eq 'qgtk'?'selected':''}>全国统考(联考)</option>
                            <option value="tjms" ${main.testType eq 'tjms'?'selected':''}>推荐免试</option>
                            <option value="ddks" ${main.testType eq 'ddks'?'selected':''}>单独考试</option>
                            <option value="zzqgtk" ${main.testType eq 'zzqgtk'?'selected':''}>在职人员攻读硕士学位全国联考(MBA，工程硕士等)</option>
                            <option value="tdxl" ${main.testType eq 'tdxl'?'selected':''}>同等学力(临床医学等)</option>
                            <option value="zzks" ${main.testType eq 'zzks'?'selected':''}>招生单位自主考试(软件工程等领域工程硕士)</option>
                            <option value="other" ${main.testType eq 'other'?'selected':''}>其它</option>
                        </select>
                    </td>
                    <th>综合考试合格编号：</th>
                    <td><input type="text" name="testQualifiedNo" value="${main.testQualifiedNo}" placeholder="同等学力研究生填写"></td>
                    <th>专业名称：</th>
                    <td>[${empty param.userFlow?eduUser.majorId:main.majorId}]${empty param.userFlow?eduUser.majorName:main.majorName}</td>
                </tr>
                <tr>
                    <th>拟获学位名称：</th>
                    <td>
                        <select name="gainPreDegree" class="validate[required] select" style="width:137px;">
                            <option></option>
                            <c:forEach items="${userDegreeEnumList}" var="degree">
                                <option value="${degree.id}" ${main.gainPreDegree eq degree.id?'selected':''}>${degree.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>是否一级学科授予：</th>
                    <td>
                        <select name="oneSubjectFlag" class="validate[required] select" style="width:137px;">
                            <option></option>
                            <option value="Y" ${main.oneSubjectFlag eq 'Y'?'selected':''}>是</option>
                            <option value="N" ${main.oneSubjectFlag eq 'N'?'selected':''}>否</option>
                        </select>
                    </td>
                    <th>拟授予学位时间：</th>
                    <td>
                        <input type="text" name="awardDegreeTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${empty param.userFlow?eduUser.awardDegreeTime:main.awardDegreeTime}" placeholder="暂不填，非必填">
                    </td>
                </tr>
                <tr>
                    <th>拟授予学位证书编号：</th>
                    <td><input type="text" name="awardDegreeCertCode" value="${empty param.userFlow?eduUser.awardDegreeCertCode:main.awardDegreeCertCode}" placeholder="暂不填，非必填"></td>
                    <th>论文题目：</th>
                    <td><input type="text" class="validate[required]" name="paperTitle" value="${empty param.userFlow?extForm.paperTitle:main.paperTitle}"></td>
                    <th>论文关键词：</th>
                    <td><input type="text" class="validate[required]" name="paperKey" value="${empty param.userFlow?extForm.paperKey:main.paperKey}" placeholder="关键词必须填写三到五个，用逗号隔开" title="关键词必须填写三到五个，用逗号隔开"></td>
                </tr>
                <tr>
                    <th>论文类型：</th>
                    <td>
                        <select name="paperType" class="validate[required] select" style="width:137px;">
                            <option></option>
                            <c:forEach items="${dictTypeEnumPaperTypeList}" var="dict">
                                <option value="${dict.dictId}" ${(empty param.userFlow && extForm.paperType eq dict.dictId) || main.paperType eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>论文选题来源：</th>
                    <td>
                        <select name="paperSource" class="validate[required] select" style="width:137px;">
                            <option></option>
                            <c:forEach items="${dictTypeEnumPaperSourceList}" var="dict">
                                <option value="${dict.dictId}" ${(empty param.userFlow && extForm.paperSource eq dict.dictId) || main.paperSource eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>前置学历：</th>
                    <td>
                        <select name="preGraduation" class="validate[required] select" style="width:137px;">
                            <option></option>
                            <c:forEach items="${dictTypeEnumPreGraduationList}" var="dict">
                                <option value="${dict.dictId}" ${(empty param.userFlow && extForm.preGraduation eq dict.dictId) || main.preGraduation eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>前置学位：</th>
                    <td>
                        <select name="preDegreeId" class="select" style="width:137px;">
                            <option></option>
                            <c:forEach items="${userDegreeEnumList}" var="degree">
                                <c:if test="${fn:contains(degree.name,'学士') || fn:contains(degree.name,'硕士') || degree.id eq 'Jw' || degree.id eq 'No'}">
                                    <option value="${degree.id}" ${main.preDegreeId eq degree.id?'selected':''}>${degree.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <th>前置学位一级学科名称：</th>
                    <td>
                        <select name="firstLevelSubjectId" class="select" style="width:137px;">
                            <option></option>
                            <c:forEach items="${userDegreeSubjectEnumList}" var="sub">
                                <option value="${sub.id}" ${main.firstLevelSubjectId eq sub.id?'selected':''}>${sub.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>前置学位证书编号：</th>
                    <td><input type="text" name="preDegreeCertificateNo" value="${main.preDegreeCertificateNo}"></td>
                </tr>
                <tr>
                    <th>获前置学位年月：</th>
                    <td><input type="text" name="underPreDegreeTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${main.underPreDegreeTime}"></td>
                    <th>获前置学位单位：</th>
                    <td colspan="3"><input type="text" name="underPreDegreeOrg" value="${main.underPreDegreeOrg}"></td>
                </tr>
                <tr>
                    <th>获学士学位专业名称：</th>
                    <td>
                        <input name="underMajor" class="holderClass" value="${empty param.userFlow?extForm.underMajor:main.underMajor}" type="text" style="width: 140px;text-align: left;" title="所填数据必须与学位证书上一致" placeholder="必须与学位证书上一致"/>
                    </td>
                    <th>学士学位证书编号：</th>
                    <td><input type="text" name="underDegreeCertCode" value="${empty param.userFlow?extForm.underDegreeCertCode:main.underDegreeCertCode}"></td>
                    <th>获得学士学位学科门类：</th>
                    <td colspan="2">
                        <input name="gotBachelorCertSubject" class="holderClass" value="${empty param.userFlow?extForm.gotBachelorCertSubject:main.gotBachelorCertSubject}" type="text" style="width: 140px;text-align: left;" title="所填数据必须与学位证书上一致" placeholder="必须与学位证书上一致"/>
                    </td>
                </tr>
                <tr>
                    <th>获得学士学位年月：</th>
                    <td><input type="text" name="underAwardDegreeTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${empty param.userFlow?extForm.underAwardDegreeTime:main.underAwardDegreeTime}"></td>
                    <th>获学士学位单位名称：</th>
                    <td colspan="3"><input type="text" name="underAwardDegreeOrg" value="${empty param.userFlow?extForm.underAwardDegreeOrg:main.underAwardDegreeOrg}"></td>
                </tr>
                <c:if test="${fn:contains(main.trainTypeName,'博士') || fn:contains(eduUser.trainTypeName,'博士')}">
                    <tr>
                        <th>获硕士学位专业名称：</th>
                        <td>
                            <input name="gotMasterCertSpe" class="holderClass" value="${empty param.userFlow?extForm.gotMasterCertSpe:main.gotMasterCertSpe}" type="text" style="width: 140px;text-align: left;" title="所填数据必须与学位证书上一致" placeholder="必须与学位证书上一致"/>
                        </td>
                        <th>硕士学位证书编号：</th>
                        <td><input type="text" name="masterDegreeCertCode" value="${empty param.userFlow?extForm.masterDegreeCertCode:main.masterDegreeCertCode}"></td>
                        <th>获得硕士学位学科门类：</th>
                        <td>
                            <input name="masterSubject" class="holderClass" value="${empty param.userFlow?extForm.masterSubject:main.masterSubject}" type="text" style="width: 140px;text-align: left;" title="所填数据必须与学位证书上一致" placeholder="必须与学位证书上一致"/>
                        </td>
                    </tr>
                    <tr>
                        <th>获得硕士学位年月：</th>
                        <td><input type="text" name="masterAwardDegreeTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${empty param.userFlow?extForm.masterAwardDegreeTime:main.masterAwardDegreeTime}"></td>
                        <th>获硕士学位单位名称：</th>
                        <td colspan="3"><input type="text" name="masterAwardDegreeOrg" value="${empty param.userFlow?extForm.masterAwardDegreeOrg:main.masterAwardDegreeOrg}"></td>
                    </tr>
                </c:if>
                <tr>
                    <th>培养单位：</th>
                    <td style="line-height:20px;">${empty param.userFlow?doctor.orgName:main.orgName}</td>
                    <th>获学位后去向：</th>
                    <td>
                        <select name="degreeDirection" class="validate[required] select" style="width:137px;">
                            <option></option>
                            <c:forEach items="${dictTypeEnumGotDegreeDirectionList}" var="dict">
                                <option value="${dict.dictId}" ${(empty param.userFlow && extForm.degreeDirection eq dict.dictId) || main.degreeDirection eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>工作性质</th>
                    <td>
                        <select name="workNature" class="validate[required] select" style="width:137px;">
                            <option></option>
                            <c:forEach items="${dictTypeEnumWorkNatureList}" var="dict">
                                <option value="${dict.dictId}" ${(empty param.userFlow && extForm.workNature eq dict.dictId) || main.workNature eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>就业单位名称：</th>
                    <td><input type="text" class="validate[required]" name="unitName" value="${empty param.userFlow?extForm.unitName:main.unitName}"></td>
                    <th>就业单位性质：</th>
                    <td>
                        <select name="unitNature" class="validate[required] select" style="width:137px;">
                            <option></option>
                            <c:forEach items="${dictTypeEnumUnitNatureList}" var="dict">
                                <option value="${dict.dictId}" ${(empty param.userFlow && extForm.unitNature eq dict.dictId) || main.unitNature eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>就业单位所在省：</th>
                    <td><input type="text" class="validate[required]" name="unitAddress" value="${empty param.userFlow?extForm.unitAddress:main.unitAddress}"></td>
                </tr>
                <tr>
                    <th>联系电话：</th>
                    <td><input type="text" class="validate[required]" name="userPhone" value="${empty param.userFlow?sysUser.userPhone:main.userPhone}"></td>
                    <th>电子邮箱：</th>
                    <td><input type="text" class="validate[required]" name="userEmail" value="${empty param.userFlow?sysUser.userEmail:main.userEmail}"></td>
                    <th>QQ号码</th>
                    <td><input type="text" class="validate[required]" name="userQq" value="${empty param.userFlow?sysUser.userQq:main.userQq}"></td>
                </tr>
                <tr>
                    <th>微信账号：</th>
                    <td><input type="text" class="validate[required]" name="weiXinName" value="${empty param.userFlow?sysUser.weiXinName:main.weiXinName}"></td>
                    <th>学位分委员会：</th>
                    <td colspan="3">${empty param.userFlow?sysUser.deptName:main.deptName}</td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <input type="button" class="search" onclick="jboxClose()" value="关&#12288;闭"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>