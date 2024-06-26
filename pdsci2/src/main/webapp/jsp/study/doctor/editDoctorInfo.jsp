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
    <script type="text/javascript">
        function saveDocInfo() {
            if (false == $(".doctorForm").validationEngine("validate")) {
                return;
            }
            var idNo = $(".idNo").val();
            if (idNo != "") {
                if (!(/^[0-9a-zA-Z]{8}$/.test(idNo)) && !(/^[0-9a-zA-Z]{9}$/.test(idNo)) && !(/^[0-9a-zA-Z]{11}$/.test(idNo)) && !(/^[0-9a-zA-Z]{15}$/.test(idNo)) && !(/^[0-9a-zA-Z]{18}$/.test(idNo))) {
                    jboxTip("请输入8位或9位或11位或15位或18位的证件号（只能是数字和字母组合）");
                    return false;
                }
            }
            var url = "<s:url value='/study/doctor/addDoctor'/>";
            var getdata = $('.doctorForm').serialize();
            alert(14);
            jboxPost(url,getdata, function (data) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == data) {
                    try {
                        window.parent.frames['mainIframe'].location.reload();
                    } catch (e) {

                    }
                    jboxClose();
                }
            }, null, true);
        }
        function reChoose(obj) {
            $(obj).hide();
            $("#isRe").val("Y");
            $("#down").hide();
            $("#file").show();
        }
        function addOrRemove(id) {
            if (id != "4" && id) {
                $("#certificateTimeTh").html("<font color='red'>*</font>&nbsp;取得日期：");
                $("#certificateTime").addClass("qtext");
                $("#fileTh").html("<font color='red'>*</font>&nbsp;证书附件：");
                $("#file").addClass("qtext");
            } else {
                $("#certificateTimeTh").html("取得日期：");
                $("#certificateTime").removeClass("qtext");
                $("#fileTh").html("证书附件：");
                $("#file").removeClass("qtext");
            }
        }
        function checkFileType(obj) {
            var fileName = $.trim($(obj).val());
            var suffix = fileName.substring(fileName.indexOf(".") + 1);
            if (suffix != "jpg" && suffix != "JPG" && suffix != "PNG" && suffix != "png") {
                jboxTip("请选择jpg或png格式的文件！");
                $(obj).val("");
                return;
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

    </script>
</head>
<body>
<br class="infoAudit" <c:if test="${empty param.openType}">style="height: 1150px;"</c:if>>
    <br id="doctorForm" style="position:relative;">
        <input type="hidden" name="user.userFlow" value="${user.userFlow}"/>
        <input type="hidden" id="doctorFlow" name="doctor.doctorFlow" value="${doctor.doctorFlow}"/>
        <input type="hidden" id="technologyQualificationName" name="userResumeExt.technologyQualificationName"
               value="${userResumeExt.technologyQualificationName}"/>
        <input type="hidden" id="qualificationMaterialName" name="userResumeExt.qualificationMaterialName"
               value="${userResumeExt.qualificationMaterialName}"/>
        <input type="hidden" id="practicingCategoryName" name="userResumeExt.practicingCategoryName"
               value="${userResumeExt.practicingCategoryName}"/>
        <input type="hidden" id="practicingScopeName" name="userResumeExt.practicingScopeName"
               value="${userResumeExt.practicingScopeName}"/>
        <!-- 学位类型Name-->
        <input type="hidden" id="masterDegreeTypeName" name="userResumeExt.masterDegreeTypeName"
               value="${userResumeExt.masterDegreeTypeName}"/>
        <input type="hidden" id="doctorDegreeTypeName" name="userResumeExt.doctorDegreeTypeName"
               value="${userResumeExt.doctorDegreeTypeName }"/>
        <tr>
            <th><span class="red">*</span>姓&#12288;&#12288;名：</th>
            <td>
                <input name="user.userName" value="${user.userName}" class="qtext"/>&#12288;
            </td>
            <th><span class="red" style=" padding-left: 225px;">*</span>性&#12288;&#12288;别：</th>
            <td>
                &nbsp;<label><input type="radio" class='qtext' style="width:auto;"
                                    name="user.sexId"
                                    value='${userSexEnumMan.id}' ${user.sexId eq userSexEnumMan.id?'checked':''}/>&nbsp;${userSexEnumMan.name}
            </label>&#12288;
                <label><input type="radio" class='qtext' style="width:auto;" name="user.sexId"
                              value='${userSexEnumWoman.id}' ${user.sexId eq userSexEnumWoman.id?'checked':''}/>&nbsp;${userSexEnumWoman.name}
                </label>&#12288;
            </td>
            </tr>
            <tr>
            <td rowspan="5" style="text-align: center;">
                <font id="idNoWatermark"
                      style="display:none;font-size:9px;color:red; position:relative; top: 110px;">${user.idNo }</font>
                <img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg"
                     style="margin-top: -30px;" width="130px" height="150px"
                     onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
                <div style="cursor: pointer; display:block;padding-top: 10px;">
                    [<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
                    <img class="icon-head" src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/>
                    <div id="changeInfo" class="pxxx" style="display: none;">
                        <div class="changeinfo" id="changeInfoContent" style="height: 150px;">
                            <i class="icon_up"></i>
                            <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%">
                                <caption
                                        style="border-bottom: none;line-height: 20px;color: black;font-size: xx-small;">
                                    <label class="red">*&#12288;</label>照片要求：<br/>1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，大小不超过300K，人像正立。<br/>2、该照片用于结业证书，请慎重认真上传！
                                </caption>
                            </table>
                        </div>
                    </div>
                </div>
                <input type="file" id="imageFile" name="headImg" style="display: none"
                       onchange="uploadImage();"/>
                <input type="hidden" id="headimgurl" value=""/>
            </td>
        </tr>
        <tr>
            <th><span class="red">*</span>证件类型：</th>
            <td style="padding-left:10px;">
                <select name="user.cretTypeId" id="cretTypeId" onchange="addValidate(this.value)"
                        class="qtext select" style="width: 160px;">
                    <option value="">请选择</option>
                    <c:forEach items="${certificateTypeEnumList}" var="certificateType">
                        <option value="${certificateType.id}" ${certificateType.id eq user.cretTypeId?'selected':''}>${certificateType.name}</option>
                    </c:forEach>
                </select>&#12288;&nbsp;
            </td>
            <th><span class="red"style="padding-left: 225px;">*</span>证&nbsp;件&nbsp;号&nbsp;码：</th>
            <td>
                <c:if test="${empty doctor.graduationStatusId}">
                    <input type="text" name="user.idNo" id="idNo" value="${user.idNo}"
                           onchange="checkUserUnique();" class="qtext input"/>&#12288;
                </c:if>
                <c:if test="${!empty doctor.graduationStatusId}"><label>${user.idNo}</label><input id="idNo"
                                                                                                   name="user.idNo"
                                                                                                   value="${user.idNo}"
                                                                                                   type="hidden"></c:if>
            </td>
        </tr><br/>
        <tr>
            <th><span class="red">*</span>注册邮箱：</th>
            <td colspan="4"><input name="user.userEmail" value="${user.userEmail}"
                                   class="validate[required]"/></td>
            <th><span class="red" style="
    padding-left: 225px;">*</span>手机号码：</th>
            <td><input name="user.userPhone" value="${user.userPhone}" onchange="checkUserUnique();"
                       class="validate[required]"/>&#12288;</td>
        </tr></br>
        <tr>
            <th class="address" id="address"><span class="red">*</span>培训类型：</th>
            <td colspan="2" class="address">
                <input id="psdw" value="${doctor.workOrgName}"
                       <c:if test="${isPassed}">disabled</c:if> class="toggleView input qtext"
                       type="text" onchange="changeOrg();"/>

            </td>
            <th><span class="red" style="
    padding-left: 225px;">*</span>培训专业：</th>
            <td>
                <select class="select hospital  qtext" name="userResumeExt.hospitalCategoryId"
                        style="width: 160px">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
                        <option value="${tra.dictId}"
                                <c:if test="${userResumeExt.hospitalCategoryId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr></br>
        <tr>
            <th><span class="red">*</span>年&#12288;&#12288;级：</th>
            <td><input name="user.userName" value="${user.userName}" class="qtext input"/>&#12288;</td>
            <th><span class="red" style="
    padding-left: 225px;">*</span>培训基地：</th>
            <td><select class="select hospital  qtext" name="userResumeExt.hospitalCategoryId"
                        style="width: 160px">
                <option value="">请选择</option>
            </select>
            </td>
        </tr></br>
        <tr>
            <th><span class="red">*</span>所在单位：</th>
            <td><input type="text" id="workOrgName" name="doctor.workOrgName"
                       value="${doctor.workOrgName}"/>&#12288;
            </td>
        </tr>
        <div class="btn_info">
            <div class="button" style="width: 800px;">
                <input class="search" type="button" value="保&#12288;存" onclick="saveDocInfo();"/>
            </div>
            <c:if test="${param.openType eq 'open'}">
                <input type="button" style="width:100px;" class="btn_green" onclick="jboxClose();"
                       value="关&#12288;闭"></input>
            </c:if>
        </div>
    </form>
</div>
</body>
</html>
