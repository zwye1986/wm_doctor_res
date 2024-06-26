<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>

    <link href="<s:url value='/css/UCFORM.css'/>" rel="stylesheet" type="text/css">
    <script src="<s:url value='/js/jQuery.UCSelect.js'/>" type="text/javascript"></script>

    <script type="text/javascript">
        function saveUser() {
            if (false == $("#sysUserForm").validationEngine("validate")) {
                return;
            }
            var idNo = $("#idNo").val();
            if (idNo != "") {
                if (!(/^[0-9a-zA-Z]{8}$/.test(idNo)) &&!(/^[0-9a-zA-Z]{9}$/.test(idNo)) && !(/^[0-9a-zA-Z]{11}$/.test(idNo)) && !(/^[0-9a-zA-Z]{15}$/.test(idNo)) && !(/^[0-9a-zA-Z]{18}$/.test(idNo))) {
                    jboxTip("请输入8位或9位或11位或15位或18位的证件号（只能是数字和字母组合）");
                    return false;
                }
            }
            $("#resTrainingSpeName").val($("#resTrainingSpeId").find("option:selected").text());
            var url = "<s:url value='/sys/user/save'/>";
            var getdata = $('#sysUserForm').serialize();
            jboxSubmit($('#sysUserForm'),url, function (data) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == data) {
                    try {
                        window.parent.frames['mainIframe'].window.searchUser();
                    } catch (e) {

                    }
                    jboxClose();
                }
            }, null, true);
        }
        function reChoose(obj){
            $(obj).hide();
            $("#isRe").val("Y");
            $("#down").hide();
            $("#file").show();
        }
        function addOrRemove(id)
        {
            if(id!="4"&&id)
            {
                $("#certificateTimeTh").html("<font color='red'>*</font>&nbsp;取得日期：");
                $("#certificateTime").addClass("validate[required]");
                $("#fileTh").html("<font color='red'>*</font>&nbsp;证书附件：");
                $("#file").addClass("validate[required]");
            }else{
                $("#certificateTimeTh").html("取得日期：");
                $("#certificateTime").removeClass("validate[required]");
                $("#fileTh").html("证书附件：");
                $("#file").removeClass("validate[required]");
            }
        }
        function checkFileType(obj){
            var fileName= $.trim($(obj).val());
            var suffix = fileName.substring(fileName.indexOf(".")+1);
            if(suffix!="jpg"&&suffix!="JPG"&&suffix!="PNG"&&suffix!="png")
            {
                jboxTip("请选择jpg或png格式的文件！");
                $(obj).val("");
                return ;
            }
        }
        function searchDept(orgFlow, deptFlow) {
            if (orgFlow == "") {
                return;
            }
            var url = "<s:url value='/sys/user/getDept'/>?orgFlow=" + orgFlow + "&deptFlow=" + deptFlow;
            jboxGet(url, null, function (resp) {
                $("#deptSelectId").html(resp);
                if ($("#mulDeptTr").is(":visible")) {
                    $("#" + $("#deptFlow").val()).hide();
                }
            }, null, false);
        }
        function makeBirthdayByIdCard(idNo) {
            var sId = idNo;
            var birthDayObj = $("#userBirthday");
            var vBi = birthDayObj.val();
            if (vBi == "") {
                if (sId.length == 15) {
                    birthDayObj.val("19" + sId.substr(6, 2) + "-" + sId.substr(8, 2) + "-" + sId.substr(10, 2));
                } else if (sId.length == 18) {
                    birthDayObj.val(sId.substr(6, 4) + "-" + sId.substr(10, 2) + "-" + sId.substr(12, 2));
                }
            }
        }

        function mulDept() {
            if ($("#deptFlow").val() == "") {
                jboxTip("默认科室必须选择!");
                return;
            }
            $("#mulDeptTr").toggle();
            $("#" + $("#deptFlow").val()).hide();
        }
        function mulChange(deptFlow) {
            $(".otherDept").show();
            $("#" + $("#deptFlow").val()).hide();
        }

        $(document).ready(function () {
            if ('${isUniversitySon}'=='Y'){
                $('select[name=school]').UCFormSelect();
                $(".SelectVal").css("width","165px").css("height","25px").css("margin-top","4px").css("margin-left","10px");
                $(".SelectBox").css("width","165px").css("margin-left","10px");
                $(".SelectVal span").css("margin-top","-5px");
            }
        });
    </script>
</head>
<body>

<form id="sysUserForm" style="height: 100px;">
    <input type="hidden" name="roleFlow" value="${param.roleFlow}"/>
    <div class="content" style="height: 400px;overflow: auto;">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <table width="800" cellpadding="0" cellspacing="0" class="basic">
                        <c:set var="gzFlag" value="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}"/>
                        <tr>
                            <th width="20%"><font color="red">*</font>&nbsp;登录名：</th>
                            <td width="30%">
                                <input type="hidden" name="userFlow" value="${sysUser.userFlow}"/>
                                <c:if test="${sysUser.statusId == userStatusEnumAdded.id  }">
                                    未注册
                                    <input type="hidden" name="userCode" value="${sysUser.userCode}"/>
                                </c:if>
                                <c:if test="${sysUser.statusId != userStatusEnumAdded.id  }">
                                    <input class="validate[required<c:if test="${!(sysCfgMap['sys_user_check_user_code'] eq GlobalConstant.FLAG_N)}">,custom[userCode]</c:if>] xltext"
                                           name="userCode" <c:if test="${not empty sysUser}">readonly="readonly"</c:if>  type="text" value="${sysUser.userCode}" type="text"/>
                                    <%-- <c:if test="${empty sysUser.userFlow }">
                                        <input class="validate[required,custom[userCode]] xltext"  name="userCode" type="text" value="${sysUser.userCode}" type="text"/>
                                    </c:if>
                                   <c:if test="${not empty sysUser.userFlow }">
                                        ${sysUser.userCode}
                                        <input type="hidden" name="userCode" value="${sysUser.userCode}" />
                                    </c:if> --%>
                                </c:if>
                            </td>
                            <th width="20%"><c:if
                                    test="${(isTeacher eq 'N') and (sessionScope.currWsId eq 'res')}"><font color="red">*</font>&nbsp;</c:if>身份证号：
                            </th>
                            <td width="30%">
                                <input class="<c:if test="${(isTeacher eq 'N') and (sessionScope.currWsId eq 'res')}">validate[required]</c:if> xltext"
                                       name="idNo" id="idNo" type="text" value="${sysUser.idNo}">
                                <!--onblur="makeBirthdayByIdCard(this.value)"-->
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>&nbsp;姓名：</th>
                            <td>
                                <input class="validate[required] xltext" name="userName" <c:if test="${not empty sysUser and !gzFlag}">readonly="readonly"</c:if> type="text"
                                       value="${sysUser.userName}">
                            </td>
                            <th><font color="red">*</font>&nbsp;性别：</th>
                            <td>
                                <input id="${userSexEnumMan.id }" class="validate[required]" type="radio" name="sexId"
                                       value="${userSexEnumMan.id }"
                                       <c:if test="${userSexEnumMan.id == sysUser.sexId}">checked</c:if> />
                                <label for="${userSexEnumMan.id }">${userSexEnumMan.name}</label>&#12288;
                                <input id="${userSexEnumWoman.id }" class="validate[required]" type="radio" name="sexId"
                                       value="${userSexEnumWoman.id }"
                                       <c:if test="${userSexEnumWoman.id == sysUser.sexId}">checked</c:if> />
                                <label for="${userSexEnumWoman.id }">${userSexEnumWoman.name }</label>
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>&nbsp;手机号码：</th>
                            <td>
                                <c:choose>
                                    <c:when test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_PERSONAL && GlobalConstant.FLAG_N eq sysCfgMap['user_edit_phone']}">
                                        ${sysUser.userPhone}
                                        <input name="userPhone" type="hidden" value="${sysUser.userPhone}">
                                    </c:when>
                                    <c:otherwise>
                                        <input class="validate[custom[mobile] required] xltext" name="userPhone" type="text"
                                               value="${sysUser.userPhone}">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <th>电子邮箱：</th>
                            <td>
                                <c:choose>
                                    <c:when test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_PERSONAL && GlobalConstant.FLAG_N eq sysCfgMap['user_edit_mail']}">
                                        ${sysUser.userEmail}
                                        <input name="userEmail" type="hidden" value="${sysUser.userEmail}">
                                    </c:when>
                                    <c:otherwise>
                                        <input class="validate[custom[email]] xltext" name="userEmail" type="text"
                                               value="${sysUser.userEmail}">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>出生日期：</th>
                            <td>
                                <input class="xltext ctime" id="userBirthday" name="userBirthday" type="text"
                                       value="${sysUser.userBirthday}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                            </td>
                            <th>学历：</th>
                            <td>
                                <select name="educationId" class="xlselect">
                                    <option></option>
                                    <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
                                        <option value="${dict.dictId }"
                                                <c:if test="${sysUser.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>学位：</th>
                            <td>
                                <select name="degreeId" class="xlselect">
                                    <option></option>
                                    <c:forEach items="${dictTypeEnumUserDegreeList}" var="degree">
                                        <option value="${degree.dictId}"
                                                <c:if test='${sysUser.degreeId==degree.dictId}'>selected="selected"</c:if>>${degree.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>职称：</th>
                            <td>
                                <c:if test="${sessionScope.currWsId eq 'gzykdx'}">
                                    <input name="titleName" value="${sysUser.titleName}" type="text" class="xltext" />
                                </c:if>
                                <c:if test="${sessionScope.currWsId ne 'gzykdx'}">
                                    <select name="titleId" class="xlselect">
                                        <option></option>
                                        <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                            <option value="${title.dictId}"
                                                    <c:if test='${sysUser.titleId==title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th>中医西医类型：</th>
                            <td>
                                <select name="medicineTypeId" class="xlselect">
                                    <option></option>
                                    <c:forEach items="${medicineTypeEnumList}" var="type">
                                        <option value="${ type.id}" <c:if test="${sysUser.medicineTypeId== type.id}">selected="selected"</c:if> >${type.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th></th>
                            <td>
                            </td>
                        </tr>
                        <c:if test="${sessionScope.currWsId==GlobalConstant.SRM_WS_ID}">
                        <tr>
                            <th>个人科研账号：</th>
                            <td>
                                <input type="text" class="xltext validate[custom[onlyLetterNumber],maxSize[30]]" name="accountNo" value="${sysUser.accountNo}"/>
                            </td>
                            <th>工号：</th>
                            <td>
                                <input type="text" class="xltext validate[custom[onlyLetterNumber],maxSize[20]]" name="workCode" value="${sysUser.workCode}"/>
                            </td>
                        </tr>
                        </c:if>
                        <tr>
                            <th>职务：</th>
                            <td>
                                <select name="postId" class="xlselect">
                                    <option></option>
                                    <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                        <option value="${post.dictId}"
                                                <c:if test='${sysUser.postId==post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_PERSONAL }">
                                <th></th>
                                <td></td>
                            </c:if>
                            <c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_PERSONAL }">
                            <th>机构名称：</th>
                            <td>
                                <c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL }">
                                    <select class="xlselect" name="orgFlow"
                                            onchange="searchDept(this.value,'');">
                                        <option value="">请选择</option>
                                        <c:forEach var="sysOrg" items="${applicationScope.sysOrgList}">
                                            <option value="${sysOrg.orgFlow}"
                                                    <c:if test="${sysOrg.orgFlow==sysUser.orgFlow || sysOrg.orgFlow eq param.orgFlow}">selected</c:if>>${sysOrg.orgName}</option>
                                        </c:forEach>
                                    </select>
                                    <script type="text/javascript">
                                        $(document).ready(function () {
                                            <c:if test="${empty sysUser}">
                                            $("[name='orgFlow']").change();
                                            </c:if>
                                            <c:if test="${not empty sysUser}">
                                            searchDept('${sysUser.orgFlow}', '${sysUser.deptFlow}');
                                            </c:if>
                                        });
                                    </script>
                                </c:if>
                                <c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_CHARGE}">
                                    <select class="xlselect" name="orgFlow"
                                            onchange="searchDept(this.value,'');">
                                        <option value="">请选择</option>
                                        <c:forEach var="sysOrg" items="${orgList}">
                                            <option value="${sysOrg.orgFlow}"
                                                    <c:if test="${sysOrg.orgFlow==sysUser.orgFlow || sysOrg.orgFlow eq param.orgFlow}">selected</c:if>>${sysOrg.orgName}</option>
                                        </c:forEach>
                                    </select>
                                    <script type="text/javascript">
                                        $(document).ready(function () {
                                            <c:if test="${empty sysUser}">
                                            $("[name='orgFlow']").change();
                                            </c:if>
                                            <c:if test="${not empty sysUser}">
                                            searchDept('${sysUser.orgFlow}', '${sysUser.deptFlow}');
                                            </c:if>
                                        });
                                    </script>
                                </c:if>
                                <c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_LOCAL }">
                                    <input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow }"/>
                                    ${sessionScope.currUser.orgName}
                                    <script type="text/javascript">
                                        $(document).ready(function () {
                                            searchDept('${sessionScope.currUser.orgFlow}', '${sysUser.deptFlow}');
                                        });
                                    </script>
                                </c:if>
                            </td>
                        </c:if>
                        </tr>
                        <tr>
                            <th>专业：</th>
                            <td>
                                <input hidden name="resTrainingSpeName" id="resTrainingSpeName">
                                <select name="resTrainingSpeId" id="resTrainingSpeId" class="xlselect">
                                    <option></option>
                                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                        <c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
                                            <option <c:if test="${sysUser.resTrainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
<%--                            <th>机构名称（南医大）</th>--%>
<%--                            <td>--%>
<%--                                <select class="xlselect" name="orgFlowGx">--%>
<%--                                    <option value="">请选择</option>--%>
<%--                                    <c:forEach var="sysOrg" items="${gxOrgs}">--%>
<%--                                        <option value="${sysOrg.orgFlow}"--%>
<%--                                                <c:if test="${sysOrg.orgFlow==sysUser.orgFlowGx || sysOrg.orgFlow eq param.orgFlowGx}">selected</c:if>>${sysOrg.orgName}</option>--%>
<%--                                    </c:forEach>--%>
<%--                                </select>--%>
<%--                            </td>--%>
                            <th>院校（南医大）</th>
                            <td>
                                <c:if test="${isUniversitySon eq 'Y'}">
                                    <select name="school" class="select" id="" style="width: 250px;" multiple="multiple">
                                        <c:forEach items="${schools}" var="school">
                                            <option value="${school}"
                                                    <c:if test="${pdfn:contains2(sysUser.school,school)}">selected</c:if>
                                            >${school}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>

                                <c:if test="${isUniversitySon ne 'Y'}">
                                    <select class="xlselect" name="school">
                                        <option value="">请选择</option>
                                        <c:forEach var="school" items="${schools}">
                                            <option value="${school}"
                                                    <c:if test="${school==sysUser.school || school eq param.school}">selected</c:if>
                                            >${school}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </td>
                        </tr>
                        <c:if test="${isTeach and sessionScope.userListScope==GlobalConstant.USER_LIST_PERSONAL }">
                            <tr>
                                <th><font color="red">*</font>&nbsp;证书级别：</th>
                                <td>
                                    <select name="certificateLevelId" class="validate[required] xlselect" onchange="addOrRemove(this.value)">
                                        <option></option>
                                        <c:forEach items="${dictTypeEnumCertificatelevelList}" var="title">
                                            <option value="${title.dictId}"
                                                    <c:if test='${sysUser.certificateLevelId==title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <th id="certificateTimeTh"><c:if test="${sysUser.certificateLevelId ne '4'and not empty sysUser.certificateLevelId}"><font color="red">*</font>&nbsp;</c:if>取得日期：</th>
                                <td>
                                    <input class="xltext ctime <c:if test="${sysUser.certificateLevelId ne '4'and not empty sysUser.certificateLevelId}">validate[required]</c:if>" id="certificateTime" name="certificateTime" type="text"
                                           value="${sysUser.certificateTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                                </td>
                            </tr>
                            <tr>
                                <th id="fileTh"><c:if test="${sysUser.certificateLevelId ne '4'and not empty sysUser.certificateLevelId}"><font color="red">*</font>&nbsp;</c:if>证书附件：</th>
                                <td>
                                    <input type="text" id="certificateFile" name="certificateFile" value="${sysUser.certificateFile}" style="display: none"/>
                                    <c:choose>
                                        <c:when test="${not empty file.fileFlow}">
                                            <%--<a id="down" href="<s:url value='/sys/user/downFile'/>?fileFlow=${file.fileFlow}">${file.fileName}</a>--%>
                                            <a id="down" target="_blank" href="${sysCfgMap['upload_base_url']}${file.filePath}">${file.fileName}</a>
                                            <input type="file" id="file" accept="image/jpeg,image/png" class=" <c:if test="${sysUser.certificateLevelId ne '4'and not empty sysUser.certificateLevelId}">validate[required]</c:if>" name="file"onchange="checkFileType(this);"  style="display: none;" />
                                            <input type="text" id="isRe" class="" name="isRe"  style="display: none;" />
                                            <span style="float: right; padding-right: 10px;">
                                                <a id="reCheck" href="javascript:void(0);" onclick="reChoose(this);" style="color: blue;">[重新上传]</a>
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="file" class=" <c:if test="${sysUser.certificateLevelId ne '4'and not empty sysUser.certificateLevelId}">validate[required]</c:if>" accept="image/jpeg,image/png" onchange="checkFileType(this);" id="file" name="file"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td></td>
                                <td></td>
                            </tr>
                        </c:if>
                        <c:if test="${ sessionScope.userListScope!=GlobalConstant.USER_LIST_PERSONAL and not empty sysUser and not empty sysUser.certificateLevelId }">
                            <tr>
                                <th>证书级别：</th>
                                <td>${sysUser.certificateLevelName }</td>
                                <th>取得日期：</th>
                                <td>${sysUser.certificateTime }</td>
                            </tr>
                            <tr>
                                <th>证书附件：</th>
                                <td>
                                    <c:if test="${not empty file}">
                                        <a id="down" target="_blank" href="${sysCfgMap['upload_base_url']}${file.filePath}">${file.fileName}</a>
                                    </c:if>
                                </td>
                                <th></th>
                                <td></td>
                            </tr>
                        </c:if>
                        <c:if test="${sessionScope.userListScope!=GlobalConstant.USER_LIST_PERSONAL }">
                            <tr>
                                    <%-- 什么情况下需要显示部门  --%>
                                <c:set var="deptStype" value="style='visibility:visible;'"></c:set>
                                <c:choose>
                                    <c:when test="${sessionScope.currWsId==GlobalConstant.SRM_WS_ID }">
                                        <c:if test="${applicationScope.sysCfgMap['srm_for_use']=='global' and sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL}">
                                            <c:set var="deptStype" value="style='visibility:hidden;'"></c:set>
                                        </c:if>
                                    </c:when>
                                    <c:when test="${sessionScope.currWsId==GlobalConstant.EDC_WS_ID
							or sessionScope.currWsId==GlobalConstant.EDU_WS_ID
							or sessionScope.currWsId==GlobalConstant.NJMUEDU_WS_ID}">
                                        <c:set var="deptStype" value="style='visibility:hidden;'"></c:set>
                                    </c:when>
                                    <c:when test="${sessionScope.currWsId==GlobalConstant.IRB_WS_ID }">
                                        <c:set var="deptStype" value="style='visibility:visible;'"></c:set>
                                    </c:when>
                                    <c:when test="${sessionScope.currWsId==GlobalConstant.GCP_WS_ID }">
                                        <c:set var="deptStype" value="style='visibility:visible;'"></c:set>
                                    </c:when>
                                    <c:otherwise>

                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${sessionScope.currWsId!=GlobalConstant.EDU_WS_ID and sessionScope.currWsId!=GlobalConstant.NJMUEDU_WS_ID}">
                                    <th ${deptStype}>部门名称
                                        <c:if test="${ applicationScope.sysCfgMap['sys_user_dept_flag']==GlobalConstant.FLAG_Y }">
                                            [<a href="javascript:mulDept();">多选</a>]
                                        </c:if>
                                        ：
                                    </th>
                                    <td id="deptSelectId" ${deptStype}>
                                    </td>
                                    <th>
                                        <c:if test="${sessionScope.currWsId eq GlobalConstant.FSTU_WS_ID}">最后晋升时间：</c:if>
                                        <c:if test="${(isTeacher eq 'Y') and (sessionScope.currWsId eq 'res')}">师资类型：</c:if>
                                    </th>
                                    <td style="border: 1px solid #bbbbbb">
                                        <c:if test="${sessionScope.currWsId eq GlobalConstant.FSTU_WS_ID}">
                                            <input type="text" class="xltext ctime" name="promoteTime"  value="${sysUser.promoteTime}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 160px;"/>
                                        </c:if>
                                        <c:if test="${(isTeacher eq 'Y') and (sessionScope.currWsId eq 'res')}">
                                            <select name="teacherTypeId" class="xlselect">
                                                <option></option>
                                                <c:forEach items="${dictTypeEnumTeachersTypeList}" var="title">
                                                    <option value="${title.dictId}"
                                                            <c:if test='${sysUser.teacherTypeId==title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                    </td>
                                </c:if>
                            </tr>
                            <c:if test="${ applicationScope.sysCfgMap['sys_user_dept_flag']==GlobalConstant.FLAG_Y }">
                                <tr style="display: <c:if test='${empty userDeptMap}'>none</c:if>" id="mulDeptTr">

                                    <th>多部门选择：</th>
                                    <td colspan="3">
                                        <c:forEach items="${sysDeptList}" var="dept">
                                            <div style="width:24.9%;float: left">
                                                <label id="${dept.deptFlow }" class="otherDept">
                                                    <input type="checkbox" name="mulDeptFlow" value="${dept.deptFlow }"
                                                           <c:if test="${!empty userDeptMap[dept.deptFlow ] }">checked</c:if>
                                                    />${dept.deptName}&#12288;&#12288;</label>
                                            </div>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:if>
                        </c:if>
                    </table>
                    <div class="button" style="width: 800px;">
                        <input class="search" type="button" value="保&#12288;存" onclick="saveUser();"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>