<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        <jsp:param name="jquery_cxselect" value="true"/>
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
    <style type="text/css">
        table {
            margin: 10px 0;
            border-collapse: collapse;
        }

        caption, th, td {
            height: 40px;
        }

        caption {
            line-height: 40px;
            text-align: left;
            font-weight: bold;
            padding-left: 10px;
            border-bottom: 1px solid #ddd;
            color: #1461c0;
            margin-bottom: 10px;
        }

        th {
            text-align: right;
            font-weight: 500;
            padding-right: 5px;
            color: #333;
        }

        td {
            text-align: left;
            padding-left: 5px;
        }

        [type='text'] {
            width: 150px;
            height: 22px;
        }

        select {
            width: 153px;
            height: 27px;
        }

        .fuck:hover {
            background: black;
        }
    </style>

    <script type="text/javascript">
        var isSch = {
            <c:forEach items="${recDocCategoryEnumList}" var="category">
            <c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
            <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
            <c:set var="isSchKey" value="res_doctor_category_${category.id}_sch"/>
            "${category.id}": "${sysCfgMap[isSchKey]}",
            </c:if>
            </c:forEach>
        };

        var condition = "._${doctor.doctorCategoryId}._${doctor.trainingSpeId}";
        var condition2 = "._${doctor.secondSpeId}";
        function saveDoc() {
            if ($("#resDoctor").validationEngine("validate")) {
                var idNo = $("#idNo").val();
                if (idNo != "") {
                    if (!(/^[0-9a-zA-Z]{8}$/.test(idNo)) &&!(/^[0-9a-zA-Z]{9}$/.test(idNo)) && !(/^[0-9a-zA-Z]{11}$/.test(idNo)) && !(/^[0-9a-zA-Z]{15}$/.test(idNo)) && !(/^[0-9a-zA-Z]{18}$/.test(idNo))) {
                        jboxTip("请输入8位或9位或11位或15位或18位的证件号（只能是数字和字母组合）");
                        return false;
                    }
                }
                var url = "<s:url value='/sch/manager/saveDocSimple'/>";
                var getdata = $('#resDoctor').serialize();
                jboxPost(url, getdata, function (data) {
                    if ('${GlobalConstant.SAVE_SUCCESSED}' == data) {
                        window.parent.frames['mainIframe'].window.searchUser();
                        jboxClose();
                    }
                }, null, true);
            }
        }

        function calculation() {
            var y;
            if ($("#trainingYears").val() == '1') y = 1;
            else if ($("#trainingYears").val() == '2') y = 2;
            else if ($("#trainingYears").val() == '3') y = 3;
            else y = 0;
            var year = parseInt($("#sessionNumber").val()) + parseInt(y);
            if (year != 0 && !isNaN(year)) {
                $("#graduationYear").val(year);
                $("#graduationYearLebal").text(year);
            }
        }

        $(function () {
            $(".xltext,.xlselect").css("margin-right", "0px");
            $("._rotation").hide();
            <c:if test="${param.editButtonFlag eq 'no'}">
            $(":input[type='button']").hide();
            $("#close").show();
            </c:if>
            if ('${doctor.graduationYear}' == "" || '${doctor.graduationYear}' == null) {
                calculation();
            }
        });

        function cutBirthday(idNo) {
            $("[name='userBirthday']").val(idNo.substr(6, 4) + "-" + idNo.substr(10, 2) + "-" + idNo.substr(12, 2));
        }

        function changeDocCategory() {
            selRotation();
            selSecondRotation();
            var doctorCategoryId = $("#doctorCategoryId").val();
            changeUserInfo(doctorCategoryId);
        }
        function selRotation() {
            $("#rotationFlow").val("");
            $("._rotation").hide();
            var doctorCategoryId = $("#doctorCategoryId").val();
            var trainingSpeId = $("#trainingSpeId").val();
            if (doctorCategoryId && trainingSpeId) {
                var selector = "._" + doctorCategoryId + "._" + trainingSpeId;
                $("#rotationFlow").find(selector).show();
                if (condition == selector) {
                    $("#rotationFlow").find(selector + "[value='${doctor.rotationFlow}']").attr("selected", "selected");
                } else {
                    $("#rotationFlow").find(selector + ":first").attr("selected", "selected");
                }
            }
        }
        function selSecondRotation() {
            $("#secondRotationFlow").val("");
            $("._secondRotation").hide();
            var doctorCategoryId = $("#doctorCategoryId").val();
            var trainingSpeId = $("#secondSpeId").val();
            if (doctorCategoryId && trainingSpeId) {
                var selector = "._" + trainingSpeId;
                $("#secondRotationFlow").find(selector).show();
                if (condition2 == selector) {
                    $("#secondRotationFlow").find(selector + "[value='${doctor.secondRotationFlow}']").attr("selected", "selected");
                } else {
                    $("#secondRotationFlow").find(selector + ":first").attr("selected", "selected");
                }
            }
        }

        function changeUserInfo(doctorCategoryId) {
            $("#workInfo").hide();
            $("#work").hide();
            $(".userNameSpan").html("学员");
            $(".schoolNameSpan").html("在读");
            $(".graduateSpan").hide();
            $(".educationSpan").show();
            $(".degreeSpan").hide();
            $(".deptSpan").hide();
            $(".trainNameSpan").html("培训");
            $(".trainExtralSpan").html("培养");
            if (doctorCategoryId == "${recDocCategoryEnumDoctor.id}" ||
                    doctorCategoryId == "${recDocCategoryEnumInDoctor.id}" ||
                    doctorCategoryId == "${recDocCategoryEnumOutDoctor.id}" ||
                    doctorCategoryId == "${recDocCategoryEnumFieldTrain.id}" ||
                    doctorCategoryId == "${recDocCategoryEnumUnderGrad.id}" ||
                    doctorCategoryId == "${recDocCategoryEnumSpecialist.id}" ||
                    doctorCategoryId == "${recDocCategoryEnumGeneralDoctor.id}") {//住院医师、本院规培、外院规培、外地委培、本科生、专科医师、普通医生
                $(".userNameSpan").html("医师");
                $(".schoolNameSpan").html("毕业");
                $(".graduateSpan").show();	//增加毕业证号和毕业时间
                $(".degreeSpan").show();	//增加学位
                $("#workInfo").show();	//增加工作单位和职务
                $("#work").show();	//增加工作经历
                $(".deptSpan").hide();
                $(".${recDocCategoryEnumGraduate.id}").hide();
            } else if (doctorCategoryId == "${recDocCategoryEnumScholar.id}") {//进修生
                $(".schoolNameSpan").html("毕业");
                $(".graduateSpan").show();	//增加毕业证号和毕业时间
                $(".degreeSpan").show();	//增加学位
                $(".trainNameSpan").html("进修");
                $("#work").show();	//增加工作经历
                $(".deptSpan").hide();
                $(".${recDocCategoryEnumGraduate.id}").hide();
            } else if (doctorCategoryId == "${recDocCategoryEnumEightYear.id}") {//八年制
                $(".educationSpan").hide();	//隐藏学历
                $(".${recDocCategoryEnumGraduate.id}").hide();
            } else if (doctorCategoryId == "${recDocCategoryEnumIntern.id}") {//实习生
                $(".trainNameSpan").html("实习");
                $(".deptSpan").show();
                $(".${recDocCategoryEnumGraduate.id}").hide();
            } else if (doctorCategoryId == "${recDocCategoryEnumGraduate.id}") {//研究生
                $(".${recDocCategoryEnumGraduate.id}").show();
            }
            if(doctorCategoryId =="${recDocCategoryEnumChineseMedicine.id}")
            {
                $(".secondTr").show();
            }else{
                $("#secondSpeId").val("");
                $("#secondRotationFlow").val("");
                $(".secondTr").hide();
            }
            if(doctorCategoryId!="") {
                $("#trainingSpeId").empty();
                $("#trainingSpeId").append($("." + doctorCategoryId).clone().show());
            }
        }

        function checkCondition() {
            if (!$("#doctorCategoryId").val()) {
                jboxTip("请选择人员类型!");
                return;
            }
            if (!$("#trainingSpeId").val()) {
                jboxTip("请选择专业!");
                return;
            }
        }
        function checkSecondCondition() {
            if (!$("#doctorCategoryId").val()) {
                jboxTip("请选择人员类型!");
                return;
            }
            if (!$("#secondSpeId").val()) {
                jboxTip("请选择二级专业!");
                return;
            }
        }

        $(document).ready(function () {
            changeUserInfo("${doctor.doctorCategoryId}");
            selRotation();
            selSecondRotation();
        });
        function lock(userFlow) {
            jboxConfirm("确认锁定该用户吗？锁定后该用户将不能登录系统！", function () {
                var url = "<s:url value='/sys/user/lock?userFlow='/>" + userFlow;
                jboxGet(url, null, function () {
                    var url = "javascript:activate('${user.userFlow}');";
                    $("#userStatusSpan").html('${userStatusEnumLocked.name}&#12288;[<a href=' + url + '>解锁</a>]');
                });
            });
        }

        function activate(userFlow) {
            jboxConfirm("确认解锁该用户吗？", function () {
                var url = "<s:url value='/sys/user/activate?userFlow='/>" + userFlow;
                jboxGet(url, null, function () {
                    var url = "javascript:lock('${user.userFlow}');";
                    $("#userStatusSpan").html('${userStatusEnumActivated.name}&#12288;[<a href=' + url + '>锁定</a>]');
                });
            });
        }

        function doClose() {
            jboxCloseMessager();
        }

        function resetPasswd(userFlow) {
            jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？", function () {
                var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>" + userFlow;
                jboxGet(url, null, function () {
                    //searchUser();
                });
            });
        }


    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix" style="padding:0; ">
            <form id="resDoctor" style="position: relative;">
                <input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
                <input type="hidden" name="userFlow" value="${user.userFlow}"/>
                <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
                <table style="width:100%;">
                    <caption>个人信息</caption>
                    <colgroup>
                        <col width="13%"/>
                        <col width="19%"/>
                        <col width="12%"/>
                        <col width="20%"/>
                        <col width="16%"/>
                        <col width="20%"/>
                    </colgroup>
                    <tr>
                        <th>
                            <font color="red">*</font>培训类别：
                        </th>
                        <td>
                            <c:if test="${!rotationInUse}">
                            <select name="doctorCategoryId" class="validate[required]" id="doctorCategoryId"
                                    onchange="changeDocCategory();">
                                <option></option>
                                <c:forEach items="${recDocCategoryEnumList}" var="category">
                                    <c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
                                    <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
                                        <option value="${category.id}" ${doctor.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            </c:if>
                            <c:if test="${rotationInUse}">
                                ${doctor.doctorCategoryName}
                            </c:if>
                        </td>
                        <th>
                            <c:if test="${empty user}">
                                <font color="red">*</font>
                            </c:if>用户名：
                        </th>
                        <td>
                            <c:if test="${empty user}">
                                <input type="text" name="userCode" value="${user.userCode}"
                                       class="validate[required,custom[userCode]]">
                            </c:if>
                            <c:if test="${!empty user}">
                                ${user.userCode}
                                <input type="hidden" name="userCode" value="${user.userCode}"/>
                            </c:if>
                        </td>
                        <th><font color="red">*</font>真实姓名：</th>
                        <td>
                            <input type="text" name="userName" value="${user.userName}" class="validate[required]"/>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red">*</font>性别：</th>
                        <td>
                            <label><input type="radio" name="sexId" class="validate[required]"
                                          <c:if test="${user.sexId eq userSexEnumMan.id}">checked="checked"</c:if>
                                          value="${userSexEnumMan.id}"/>男</label>&#12288;
                            <label><input type="radio" name="sexId" class="validate[required]"
                                          <c:if test="${user.sexId eq userSexEnumWoman.id}">checked="checked"</c:if>
                                          value="${userSexEnumWoman.id}"/>女</label>

                        </td>
                        <th><font color="red">*</font>证件类型：</th>
                        <td>
                            <select name="cretTypeId" class="validate[required]">
                                <c:forEach items="${certificateTypeEnumList}" var="certType">
                                    <option value="${certType.id}"
                                            <c:if test="${user.cretTypeId eq certType.id}">selected</c:if>>${certType.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th><font color="red">*</font>证件号码：</th>
                        <td>
                            <input type="text" name="idNo" id="idNo" value="${user.idNo}" class="validate[required]">
                        </td>
                    </tr>
                    <tr>
                        <script>
                            function changeNationName() {
                                var nationName = $("#nationId").find("option:selected").text()
                                $("#nationName").val(nationName);
                            }
                        </script>
                        <th><font color="red">*</font>民族：</th>
                        <td>
                            <input type="hidden" id="nationName" name="nationName" value="">
                            <select id="nationId" name="nationId" class="validate[required]"
                                    onchange="changeNationName();">
                                <option/>
                                <c:forEach items="${userNationEnumList}" var="nation">
                                    <option value="${nation.id}" ${user.nationId eq nation.id?'selected':''}>${nation.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red">*</font>手机：</th>
                        <td>
                            <input type="text" name="userPhone" value="${user.userPhone}"
                                   class="validate[required,custom[mobile]]"/>
                        </td>
                        <th><font color="red">*</font>邮箱：</th>
                        <td>
                            <input type="text" name="userEmail" value="${user.userEmail}"
                                   class="validate[required,custom[email]]"/>
                        </td>
                        <th>
                            <font color="red">*</font>学员类型：
                        </th>
                        <td>
                            <select name="doctorTypeId" class="validate[required]" id="doctorTypeId"
                                    onchange="checkDoctorType(this.value);">
                                <option></option>
                                <c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">
                                    <option value="${dict.dictId}"
                                            <c:if test="${doctor.doctorTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
                <table id="rotationInfo" style="width:100%;">
                    <caption>培训信息</caption>
                    <colgroup>
                        <col width="12%"/>
                        <col width="20%"/>
                        <col width="12%"/>
                        <col width="20%"/>
                        <col width="16%"/>
                        <col width="20%"/>
                    </colgroup>
                    <tr>
                        <th><font color="red">*</font>参培年份：</th>
                        <td>
                            <select name="sessionNumber"  class="validate[required]" id="sessionNumber" onchange="calculation();">
                                <option></option>
                                <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
                                    <option value="${dict.dictName}" ${doctor.sessionNumber eq dict.dictName?'selected="selected"':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>

                        </td>
                        <th><font color="red">*</font><span class="trainNameSpan trainExtralSpan">培养</span>年限：</th>
                        <td>
                            <select name="trainingYears"  class="validate[required]" id="trainingYears" onchange="calculation();">
                                <option></option>
                                <c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
                                    <option value="${dict.dictId}" ${doctor.trainingYears eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th><font color="red">*</font>结业考核年份：</th>
                        <td>
                            <label id="graduationYearLebal">${doctor.graduationYear}</label>
                            <input type="hidden" name="graduationYear" id="graduationYear"
                                   value="${doctor.graduationYear}">
                        </td>
                    </tr>
                    <tr>
                        <input name="orgFlow" type="hidden" value="${doctor.orgFlow}"/>
                        <th><font color="red">*</font><span class="trainNameSpan">培训</span>专业：</th>
                        <td>
                            <c:if test="${!rotationInUse}">
                                <select name="trainingSpeId"  class="validate[required]" id="trainingSpeId" onchange="selRotation();">
                                    <option></option>
                                </select>

                                <div style="display: none;">
                                    <option  class="
								${recDocCategoryEnumDoctor.id} |
								${recDocCategoryEnumGraduate.id} |
								${recDocCategoryEnumIntern.id} |
								${recDocCategoryEnumScholar.id} |
								${recDocCategoryEnumSpecialist.id} |
								${recDocCategoryEnumGeneralDoctor.id} |
								${recDocCategoryEnumWMFirst.id} |
								${recDocCategoryEnumWMSecond.id} |
								${recDocCategoryEnumAssiGeneral.id} |
								${recDocCategoryEnumChineseMedicine.id} |
								${recDocCategoryEnumTCMGeneral.id} |
								${recDocCategoryEnumTCMAssiGeneral.id} |
								" value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                        <option
                                                class="
								${recDocCategoryEnumDoctor.id} |
								${recDocCategoryEnumGraduate.id} |
								${recDocCategoryEnumIntern.id} |
								${recDocCategoryEnumScholar.id} |
								${recDocCategoryEnumSpecialist.id} |
								${recDocCategoryEnumGeneralDoctor.id} |
								" value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                    <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
                                        <option class="${recDocCategoryEnumWMFirst.id}" value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                    <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
                                        <option class="${recDocCategoryEnumWMSecond.id}" value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                    <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
                                        <option class="${recDocCategoryEnumAssiGeneral.id}" value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                    <c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
                                        <option class="${recDocCategoryEnumChineseMedicine.id}" value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                    <c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
                                        <option class="${recDocCategoryEnumTCMGeneral.id}" value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                    <c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
                                        <option class="${recDocCategoryEnumTCMAssiGeneral.id}" value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                </div>
                            </c:if>
                            <c:if test="${rotationInUse}">
                                <label>${doctor.trainingSpeName}</label>
                            </c:if>
                        </td>
                        <th><font color="red">*</font><label class="rotationAttr">轮转方案：</label></th>
                        <td colspan="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag?'1':'3'}">
                            <c:if test="${!rotationInUse}">
                                <select name="rotationFlow" class="validate[required] rotationAttr" id="rotationFlow"
                                        onclick="checkCondition();">
                                    <option></option>
                                    <c:forEach items="${rotationList}" var="rotation">
                                        <option class="_${rotation.doctorCategoryId} _${rotation.speId} _rotation
									<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag && rotation.isOrgView eq GlobalConstant.RECORD_STATUS_Y}">
										_ isOrg
										<c:forEach items="${map[rotation.rotationFlow]}" var="resRotationOrg">
										_ ${resRotationOrg.orgFlow}
										</c:forEach>
									</c:if>
									"
                                                value="${rotation.rotationFlow}" ${(doctor.rotationFlow eq rotation.rotationFlow)?'selected':''}>${rotation.rotationName}</option>
                                    </c:forEach>
                                </select>
                                <font class="rotationAttr" color="red"></font>
                            </c:if>
                            <c:if test="${rotationInUse}">
                                <label>${doctor.rotationName}</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr class="secondTr" style="display:none;">
                        <th id="secondSpeIdTh"><font color="red">*</font>二级专业：</th>
                        <td>
                            <c:if test="${!rotationInUse}">
                                <select name="secondSpeId"  class="validate[required]" id="secondSpeId" onchange="selSecondRotation();">
                                    <option></option>
                                    <c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="dict">
                                        <option value="${dict.dictId}" ${doctor.secondSpeId eq dict.dictId?'selected="selected"':''}>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                            <c:if test="${rotationInUse}">
                                <label>${doctor.secondSpeName}</label>
                            </c:if>
                        </td>
                        <th id="secondRotationTh"><font color="red">*</font>二级轮转方案:</th>
                        <td colspan="3">
                            <c:if test="${!rotationInUse}">
                                <select name="secondRotationFlow" class="validate[required] rotationAttr" id="secondRotationFlow"   onclick="checkSecondCondition();">
                                    <option></option>
                                    <c:forEach items="${rotationList}" var="rotation">
                                        <c:if test="${rotation.doctorCategoryId eq recDocCategoryEnumChineseMedicine.id or rotation.doctorCategoryId eq recDocCategoryEnumTCMGeneral.id}">
                                            <option class="_${rotation.doctorCategoryId} _${rotation.speId} _secondRotation
                                        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag && rotation.isOrgView eq GlobalConstant.RECORD_STATUS_Y}">
                                            _ isOrgSecond
                                            <c:forEach items="${map[rotation.rotationFlow]}" var="resRotationOrg">
                                            _ ${resRotationOrg.orgFlow}
                                            </c:forEach>
                                        </c:if>
									"   value="${rotation.rotationFlow}" ${(doctor.rotationFlow eq rotation.rotationFlow)?'selected':''}>${rotation.rotationName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <font class="rotationAttr" color="red"></font>
                            </c:if>
                            <c:if test="${rotationInUse}">
                                <label>${doctor.secondRotationName}</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red">*</font><span class="userNameSpan">医师</span>状态：</th>
                        <td>
                            <select name="doctorStatusId" class="validate[required]">
                                <option></option>
                                <c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
                                    <option value="${dict.dictId}" ${doctor.doctorStatusId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>

                        </td>
                    </tr>
                    <c:if test="${(!empty user.userFlow)&&(param.editButtonFlag ne 'no')}">
                        <tr>
                            <th>账号状态：</th>
                            <td colspan="5">
					<span id="userStatusSpan">
						${user.statusDesc }&#12288;
						<c:if test="${user.statusId==userStatusEnumActivated.id}">
                            [<a href="javascript:lock('${user.userFlow}');" >锁定</a>]
                        </c:if>
						<c:if test="${user.statusId==userStatusEnumLocked.id}">
                            [<a href="javascript:activate('${user.userFlow}');" >解锁</a>]
                        </c:if>
						[<a href="javascript:resetPasswd('${user.userFlow}');">重置密码</a>]

						<c:if test="${(sysCfgMap['res_org_ad_doc'] eq GlobalConstant.FLAG_Y && !(param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL)) || param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
                            <script type="text/javascript">
                                function disabledDocAndUser() {
                                    jboxConfirm("确认停用该学员？", function () {
                                        jboxGet("<s:url value='/res/doc/disabledDocAndUser'/>?userFlow=${user.userFlow}", null, function (resp) {
                                            if (resp = "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
                                                top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
                                                top.document.mainIframe.location.reload();
                                                jboxClose();
                                            }
                                        }, null, false);
                                    }, null);
                                }
                            </script>

                            [<a onclick="disabledDocAndUser();" style="cursor: pointer;">停用</a>]
                        </c:if>
					</span>
                            </td>
                        </tr>
                    </c:if>
                </table>
            </form>
            <p style="text-align: center;">
                <input type="button" onclick="saveDoc();" id="saveButton1" class="search" value="保&#12288;存"/>
                <c:if test="${!param.isMenu}">
                    <input type="button" onclick="doClose();" id="close" class="search" value="关&#12288;闭"/>
                </c:if>
            </p>
        </div>
    </div>
</div>
</body>
</html>