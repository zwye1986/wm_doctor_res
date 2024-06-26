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
        <%--var condition2 = "._${doctor.doctorCategoryId}._${doctor.secondSpeId}";--%>
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

                getUserResumeExtName();
                getFantasticFour();
                getRenYuanShuXing();
                var url = "<s:url value='/res/doc/user/saveDocSimple4jszy'/>";
                var getdata = $('#resDoctor').serialize();
                jboxPost(url, getdata, function (data) {
                    if ('${GlobalConstant.SAVE_SUCCESSED}' == data) {
                        window.parent.frames['mainIframe'].window.searchUser();
                        jboxClose();
                    }
                }, null, true);
            }
        }
        function showId(radio) {
            if (radio == 'Y') $(".qualifiedNoTd, .qualifiedNoTh").show();
            else $(".qualifiedNoTd, .qualifiedNoTh").hide();
        }

        function calculation() {
            var y;
            if ($("#trainingYears").val() == '1') y = 1;
            else if ($("#trainingYears").val() == '2') y = 2;
            else if ($("#trainingYears").val() == '3') y = 3;
            else y = 0;
            var year = parseInt($("#sessionNumber").val()) + parseInt(y);
            if (year != 0 && !isNaN(year) && year > '${doctor.graduationYear}') {
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
            $("input[type!='button']").attr("disabled",true);
            $("select").attr("disabled",true);
            </c:if>
            if ('${doctor.graduationYear}' == "" || '${doctor.graduationYear}' == null) {
                calculation();
            }
            $("#d").append($(".isOrg"));
            $("#d2").append($(".isOrgSecond"));
            if ('${doctor.doctorLicenseFlag}' == 'N') $(".qualifiedNoTd, .qualifiedNoTh").hide();

            if ('${role}' == 'hospital') {
                $("#trainingSpeId").css("background-color", "#ccc");
                $("#secondSpeId").css("background-color", "#ccc");
                $("#secondRotationFlow").css("background-color", "#ccc");
                $("#sessionNumber").css("background-color", "#ccc");
                $("#rotationFlow").css("background-color", "#ccc");
                $("#inHosDate").css("background-color", "#ccc");
                //$("#graduationYear").css("background-color", "#ccc");
                $("#trainingSpeId").attr("disabled", true);
                $("#secondSpeId").attr("disabled", true);
                $("#secondRotationFlow").attr("disabled", true);
                $("#sessionNumber").attr("disabled", true);
                $("#rotationFlow").attr("disabled", true);
                $("#inHosDate").attr("disabled", true);
                //$("#graduationYear").attr("disabled", true);
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
        function secondSpe(speId)
        {

            <%--var doctorCategoryId = $("#doctorCategoryId").val();--%>
            <%--if(doctorCategoryId=="${recDocCategoryEnumChineseMedicine.id}")--%>
            <%--{--%>
                <%--$(".second").show();--%>
                <%--$("#secondSpeId").addClass("validate[required]");--%>
                <%--$("#secondRotationFlow").addClass("validate[required]");--%>
                <%--$("#secondSpeIdTh").html("<font color='red' class='second'>*</font>二级专业：");--%>
                <%--$("#secondRotationTh").html("<font color='red' class='second'>*</font>二级轮转方案：");--%>
            <%--}else{--%>
                <%--$(".second").hide();--%>
                <%--$("#secondSpeId").removeClass("validate[required]");--%>
                <%--$("#secondRotationFlow").removeClass("validate[required]");--%>
                <%--$("#secondSpeIdTh").html("二级专业：");--%>
                <%--$("#secondRotationTh").html("二级轮转方案：");--%>
            <%--}--%>
        }
        function selRotation() {
            $("#rotationFlow").val("");
            $("._rotation").hide();
// 		var sessionNumber = $("#sessionNumber").val();
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
//                var selector = "._" + doctorCategoryId + "._" + trainingSpeId;
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
            $("#rotationInfo").toggle("${GlobalConstant.FLAG_N}" != isSch[doctorCategoryId]);
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
// 		if(!$("#sessionNumber").val()){
// 			jboxTip("请选择年级!");
// 			return;
// 		}
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
            changeTerminat("${doctor.doctorStatusId}");
            loadEdu();
            loadWork();
            checkIsPartner("${doctor.isPartner}");
        });

        function loadEdu() {
            jboxLoad("edu", "<s:url value='/pub/resume/loadView?type=education&userFlow=${user.userFlow}&editFlag=${GlobalConstant.FLAG_N}&source=resDoc'/>");
        }

        function loadWork() {
            jboxLoad("work", "<s:url value='/pub/resume/loadView?type=work&userFlow=${user.userFlow}&editFlag=${GlobalConstant.FLAG_N}&source=resDoc'/>");
        }

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

        //根据医师状态决定展示终止还是结业内容
        function changeTerminat(doctorStatusId) {
            if ("${resDoctorStatusEnumTerminat.id}" == doctorStatusId) {
                $(".${resDoctorStatusEnumTerminat.id}").show();
                $(".${resDoctorStatusEnumGraduation.id}").hide();
            } else if ("${resDoctorStatusEnumGraduation.id}" == doctorStatusId) {
                $(".${resDoctorStatusEnumGraduation.id}").show();
                $(".${resDoctorStatusEnumTerminat.id}").hide();
            } else {
                $(".${resDoctorStatusEnumTerminat.id}").hide();
                $(".${resDoctorStatusEnumGraduation.id}").hide();
            }
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
        function option(obj) {
            $("#d").append($(".isOrg"));
            $("#rotationFlow").append($("#d .isOrg." + obj));
            $("#d2").append($(".isOrg"));
            $("#secondRotationFlow").append($("#d2 .isOrgSecond." + obj));
        }

        /**
         *模糊查询加载
         */
        (function ($) {
            $.fn.likeSearchInit = function (option) {
                option.clickActive = option.clickActive || null;

                var searchInput = this;
                searchInput.on("keyup focus", function () {
                    $("#boxHome").show();
                    if ($.trim(this.value)) {
                        $("#boxHome .item").hide();
                        var items = $("#boxHome .item[value*='" + this.value + "']").show();
                        if (!items) {
                            $("#boxHome").hide();
                        }
                    } else {
                        $("#boxHome .item").show();
                    }
                });
                searchInput.on("blur", function () {
                    if (!$("#boxHome.on").length) {
                        $("#boxHome").hide();
                    }
                });
                $("#boxHome").on("mouseenter mouseleave", function () {
                    $(this).toggleClass("on");
                });
                $("#boxHome .item").click(function () {
                    $("#boxHome").hide();
                    var value = $(this).attr("value");
                    $("#itemName").val(value);
                    searchInput.val(value);
                    if (option.clickActive) {
                        option['clickActive']($(this).attr("flow"));
                    }
                });
            };
        })(jQuery);

        $(function () {
            $("#orgSel").likeSearchInit({
                clickActive: function (flow) {
                    $("#discipleTeacherFlow").val(flow);
                }
            });
        });
        function getFantasticFour() {
            if ($(':radio[name="userResumeExt.isDoctor"]:checked').val() == '${GlobalConstant.FLAG_Y}') {
                $("#uEducationName").val("博士");
                $("#dGraduatedName").val($("#bsbyyx").val());
                $("#dSpecialized").val($("#bsbyzy").val());
                $("#dGraduationTime").val($("#bsbysj").val());
            } else if ($(':radio[name="userResumeExt.isMaster"]:checked').val() == '${GlobalConstant.FLAG_Y}') {
                $("#uEducationName").val("硕士");
                $("#dGraduatedName").val($("#byyx").val());
                $("#dSpecialized").val($("#ssbyzy").val());
                $("#dGraduationTime").val($("#ssbysj").val());
            } else {
                $("#uEducationName").val($("#degree  option:selected").text());
                $("#dGraduatedName").val($("#bkbyyx").val());
                $("#dSpecialized").val($("#bkbyzy").val());
                $("#dGraduationTime").val($("#bkbysj").val());
            }
        }
        function getRenYuanShuXing(){
            var personnelAttributeName = $("#personnelAttributeId").find("option:selected").text();
            $("#personnelAttributeName").val(personnelAttributeName);
        }
        function getUserResumeExtName() {
            var technologyQualificationId = $("#technologyQualificationId").val();
            var technologyQualificationName = "";
            if (technologyQualificationId != "") {
                technologyQualificationName = $("#technologyQualificationId :selected").text();
            }
            $("#technologyQualificationName").val(technologyQualificationName);

            var qualificationMaterialId = $("#qualificationMaterialId").val();
            var qualificationMaterialName = "";
            if (qualificationMaterialId != "") {
                qualificationMaterialName = $("#qualificationMaterialId :selected").text();
            }
            $("#qualificationMaterialName").val(qualificationMaterialName);

            var practicingCategoryId = $("#practicingCategoryId").val();
            var practicingCategoryName = "";
            if (practicingCategoryId != "") {
                practicingCategoryName = $("#practicingCategoryId :selected").text();
            }
            $("#practicingCategoryName").val(practicingCategoryName);

            var practicingScopeId = $("#practicingScopeId").val();
            var practicingScopeName = "";
            if (practicingScopeId != "") {
                practicingScopeName = $("#practicingScopeId :selected").text();
            }
            $("#practicingScopeName").val(practicingScopeName);
        }
        function changeSourceProvincesName() {
            var sname = $("#orgProvId").find("option:selected").text();
            $("#sourceProvincesName").val(sname);
        }
        function selectWorkOrg() {
            var workOrgName = $("#workOrgId option:selected").text();
            $("#workOrgName").val(workOrgName);
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
                <input type="hidden" name="sendCityId" value="${doctor.sendCityId}"/>
                <input type="hidden" name="sendCityName" value="${doctor.sendCityName}"/>
                <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
                <input type="hidden" name="emergencyRelation" value="${extInfo.emergencyRelation}" />
                <input type="hidden" name="emergencyAddress" value="${extInfo.emergencyAddress}" />
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
                                <%--class="<c:if test="${!(sysCfgMap['sys_user_check_user_code'] eq GlobalConstant.FLAG_N)}">validate[required,custom[userCode]]</c:if>"/>--%>

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
                        <th><font color="red">*</font>年龄：</th>
                        <td>
                            <input type="text" name="age" value="${extInfo.age}" class="validate[required]"/>

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
                    </tr>
                    <tr>
                        <th><font color="red">*</font>证件号码：</th>
                        <td>
                            <input type="text" name="idNo" id="idNo" value="${user.idNo}" class="validate[required]">
                        </td>
                        <script>
                            function changeNationName() {
                                var nationName = $("#nationId").find("option:selected").text()
                                $("#nationName").val(nationName);
                            }
                        </script>
                        <th><font color="red">*</font>民族：</th>
                        <td>
                            <input type="hidden" id="nationName" name="nationName" value="${user.nationName}">
                            <select id="nationId" name="nationId" class="validate[required]"
                                    onchange="changeNationName();">
                                <option/>
                                <c:forEach items="${userNationEnumList}" var="nation">
                                    <option value="${nation.id}" ${user.nationId eq nation.id?'selected':''}>${nation.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th><font color="red">*</font>是否取得执业资格证：</th>
                        <td>
                            <label><input type="radio" name="doctorLicenseFlag"
                                          <c:if test="${doctor.doctorLicenseFlag eq 'Y'}">checked="checked"</c:if>
                                          value="Y" class="validate[required]" onchange="showId(this.value);"/>是</label>&#12288;
                            <label><input type="radio" name="doctorLicenseFlag"
                                          <c:if test="${doctor.doctorLicenseFlag eq 'N'}">checked="checked"</c:if>
                                          value="N" class="validate[required]" onchange="showId(this.value);"/>否</label>
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
                        <th class="qualifiedNoTh">
                            <font color="red">*</font>执业医师资格证号：
                        </th>
                        <td class="qualifiedNoTd">
                            <input type="text" name="doctorLicenseNo" value="${doctor.doctorLicenseNo}"
                                   class="validate[required]">
                        </td>
                    </tr>
                    <script>
                        $(function () {
                            checkDoctorType('${doctor.doctorTypeId}');
                        })
                        function checkDoctorType(value) {
                            if (value == 'Company' || value == 'CompanyEntrust') {
                                $(".companyType").show();//工作单位和单位人类型
                                $(".medicalInstitution").show();//医疗卫生机构
                                $(".graduateType").hide();//派送学校
                                $("input[name='workOrgName']").eq(1).attr("disabled",true);//在读院校为不可用
                                $("select[name='workOrgId']").val("");//在读院校不可用
                                $("input[name='workOrgName']").eq(0).removeAttr("disabled");//工作单位为可用
                                var name = $("#medicalHeaithOrgId").val();
                                changeMedicalThings(name)
                            }else if(value == 'Graduate'){
                                $(".graduateType").show();//派送学校
                                $(".companyType").hide();//工作单位和单位人类型
                                $(".medicalInstitution").hide();//医疗卫生机构
                                $(".hospitalAttr").hide();//医院性质相关
                                $(".basicmedicalInstitutionType").hide();//基层医疗卫生机构相关
                                $("input[name='workOrgName']").eq(0).attr("disabled",true);//工作单位为不可用
                                $("input[name='workOrgName']").eq(1).removeAttr("disabled");//在读院校可用
                                $("select[name='workOrgId']").removeAttr("disabled");//在读院校可用
                            }else{
                                $(".medicalInstitution").hide();//医疗卫生机构
                                $(".companyType").hide();//工作单位和单位人类型
                                $(".graduateType").hide();//派送学校
                                $(".hospitalAttr").hide();//医院性质相关
                                $(".basicmedicalInstitutionType").hide();//基层医疗卫生机构相关
                                $("input[name='workOrgName']").eq(0).attr("disabled",true);//工作单位为不可用
                                $("input[name='workOrgName']").eq(1).attr("disabled",true);//在读院校为不可用
                                $("select[name='workOrgId']").val("");//在读院校不可用
                            }
                        }
                    </script>
                    <tr>
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
                        <th class="companyType">
                            <font color="red">*</font>工作单位：
                        </th>
                        <td class="companyType">
                            <input name="workOrgName" value="${doctor.workOrgName}"
                                   class="companyType validate[required]" type="text">
                        </td>
                        <%--<th class="companyType">--%>
                            <%--<font color="red">*</font>单位人类型：--%>
                        <%--</th>--%>
                        <%--<td class="companyType">--%>
                            <%--<select name="companyType" class="companyType validate[required]">--%>
                                <%--<option>请选择</option>--%>
                                <%--<option value="1"--%>
                                        <%--<c:if test="${doctor.companyType eq '1'}">selected="selected"</c:if> >本单位人--%>
                                <%--</option>--%>
                                <%--<option value="2"--%>
                                        <%--<c:if test="${doctor.companyType eq '2'}">selected="selected"</c:if> >委培单位人--%>
                                <%--</option>--%>
                            <%--</select>--%>
                        <%--</td>--%>
                        <th class="graduateType">
                            <font color="red">*</font>在读院校：
                        </th>
                        <td class="graduateType">
                            <select name="workOrgId" id="workOrgId" class="<c:if test="${!addFlag}">validate[required]</c:if> workOrgNameTd1" onchange="selectWorkOrg();">
                                <option></option>
                                <c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${doctor.workOrgId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
                                </c:forEach>
                            </select>
                            <input name="workOrgName" id="workOrgName" value="${doctor.workOrgName}" type="hidden" class="workOrgNameTd1">
                            <%--<input name="doctor.workOrgName" value="${doctor.workOrgName}"--%>
                                   <%--class="graduateType validate[required]" type="text">--%>
                        </td>
                        <th>
                            <font color="red">*</font>固定电话：
                        </th>
                        <td>
                            <input name="telephone" value="${extInfo.telephone}" class="input validate[required,custom[phone2]]"/>
                        </td>
                    </tr>
                    <tr>
                        <script>
                            $(function () {
                                var name = "${extInfo.medicalHeaithOrgId}";
                                changeMedicalThings(name);
                            });
                            function changeMedicalThings(name){
                                if(name=='1'){
                                    $(".hospitalAttr").show();//医院性质相关
                                    $("select[name='hospitalAttrId']").eq(0).attr("disabled",true);//后一个医院性质置为不可用
                                    $("select[name='hospitalAttrId']").eq(1).removeAttr("disabled");//前一个医院性质置为可用
                                    $(".basicmedicalInstitutionType").hide();//基层医疗卫生机构相关
                                }else if(name=='3'){
                                    $(".hospitalAttr").hide();//医院性质相关
                                    $("select[name='hospitalAttrId']").eq(1).attr("disabled",true);//前一个医院性质置为不可用
                                    $("select[name='hospitalAttrId']").eq(0).removeAttr("disabled");//后一个医院性质置为可用
                                    $(".basicmedicalInstitutionType").show();//基层医疗卫生机构相关
                                }else{
                                    $(".hospitalAttr").hide();//医院性质相关
                                    $(".basicmedicalInstitutionType").hide();//基层医疗卫生机构相关
                                }
                            }
                        </script>

                        <th><span class="red">*</span>人员属性：</th>
                        <td>
                            <select id="personnelAttributeId" class="select validate[required]" name="personnelAttributeId">
                                <option value="">请选择</option>
                                <c:forEach items="${personnelAttributeEnumList}" var="tra">
                                    <option value="${tra.id}" <c:if test="${extInfo.personnelAttributeId eq tra.id}">selected="selected"</c:if>>${tra.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th class="medicalInstitution ">
                            <font color="red">*</font>医疗卫生机构：
                        </th>
                        <td class="medicalInstitution">
                            <select class="validate[required]" id="medicalHeaithOrgId" name="medicalHeaithOrgId"
                                    onchange="changeMedicalThings(this.value);">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="tra">
                                <option value="${tra.dictId}"
                                        <c:if test="${extInfo.medicalHeaithOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                                </c:forEach>
                        </td>
                    </tr>
                    <tr style="display: none" class="basicmedicalInstitutionType">
                        <th>
                            <font color="red">*</font>医院性质：
                        </th>
                        <td>
                            <select class="validate[required]" name="hospitalAttrId">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
                                    <option value="${tra.dictId}"
                                            <c:if test="${extInfo.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th>
                            <font color="red">*</font>基层医疗卫生机构：
                        </th>
                        <td>
                            <select class="validate[required]" name="basicHealthOrgId">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra">
                                    <option value="${tra.dictId}"
                                            <c:if test="${extInfo.basicHealthOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th>
                            <font color="red">*</font>基层医疗卫生机构等级：
                        </th>
                        <td>
                            <select class="validate[required]" name="basicHealthOrgLevelId">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumBasicHealthOrgLevelList}" var="tra">
                                    <option value="${tra.dictId}"
                                            <c:if test="${extInfo.basicHealthOrgLevelId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr style="display: none" class="hospitalAttr">
                        <th>
                            <font color="red">*</font>医院性质：
                        </th>
                        <td>
                            <select class="validate[required]" name="hospitalAttrId">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
                                    <option value="${tra.dictId}"
                                            <c:if test="${extInfo.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th>
                            <font color="red">*</font>医院类别：
                        </th>
                        <td>
                            <select class="validate[required]" name="hospitalCategoryId">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
                                    <option value="${tra.dictId}"
                                            <c:if test="${extInfo.hospitalCategoryId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th>
                            <font color="red">*</font>单位等级：
                        </th>
                        <td>
                            <select class="validate[required]" name="baseAttributeId">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
                                    <option value="${tra.dictId}"
                                            <c:if test="${extInfo.baseAttributeId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <font color="red">*</font>是否是对口支援：
                        </th>
                        <td>
                            <label><input onclick="checkIsPartner(this.value)" type="radio" name="isPartner"
                                          class="validate[required]"
                                          <c:if test="${doctor.isPartner eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                          value="${GlobalConstant.FLAG_Y}"/>是</label>&#12288;
                            <label><input onclick="checkIsPartner(this.value)" type="radio" name="isPartner"
                                          class="validate[required]"
                                          <c:if test="${doctor.isPartner eq GlobalConstant.FLAG_N}">checked="checked"</c:if>
                                          value="${GlobalConstant.FLAG_N}"/>否</label>
                        </td>
                        <th style="display: none" class="sourceProvinces">
                            <font color="red">*</font>生源省份：
                        </th>
                        <td style="display: none" class="sourceProvinces">
						<span id="provCityAreaId">
							<select id="orgProvId" name="sourceProvincesId" onchange="changeSourceProvincesName();"
                                    class="province validate[required]" data-value="${doctor.sourceProvincesId}"
                                    data-first-title="选择省"></select>
						</span>
                        </td>
                    </tr>
                    <script type="text/javascript">
                        // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                        $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                        $.cxSelect.defaults.nodata = "none";

                        $("#provCityAreaId").cxSelect({
//								selects : ["province", "city", "area"],
                            selects: ["province"],
                            nodata: "none",
                            firstValue: ""
                        });
                        function checkIsPartner(item) {
                            if (item == 'Y') {
                                $(".sourceProvinces").show();
                            } else {
                                $(".sourceProvinces").hide();
                                $("#sourceProvincesName").val("");
                                $("#orgProvId").attr("data-value", "");
                            }
                        }
                    </script>
                    <tr>
                        <th><font color="red">*</font>本科毕业院校：</th>
                        <td>
                            <input name="graduatedName" value="${extInfo.graduatedName}" type="text"
                                   class="validate[required]">
                        </td>
                        <th><font color="red">*</font>本科毕业年份：</th>
                        <td>
                            <input name="graduationTime" value="${extInfo.graduationTime}" type="text"
                                   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
                                   class="validate[required]">
                        </td>
                        <th><font color="red">*</font>本科毕业专业：</th>
                        <td>
                            <input name="specialized" value="${extInfo.specialized}" type="text"
                                   class="validate[required]">
                        </td>
                    </tr>
                    <script>
                        function changeEducation() {
                            var educationName = $("#educationId").find("option:selected").text()
                            $("#educationName").val(educationName);
                        }
                    </script>
                    <tr>
                        <th><font color="red">*</font>学历：</th>
                        <td>
                            <input type="hidden" id="educationName" name="educationName" value="${user.educationName}"/>
                            <select id="educationId" name="educationId" class="validate[required] "
                                    onchange="changeEducation();">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
                                    <option value="${dict.dictId }"
                                            <c:if test="${user.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red">*</font>是否为应届生：</th>
                        <td>
                            <label><input type="radio" name="isYearGraduate" class="validate[required]"
                                          <c:if test="${doctor.isYearGraduate eq 'Y'}">checked="checked"</c:if>
                                          value="Y"/>是</label>&#12288;
                            <label><input type="radio" name="isYearGraduate" class="validate[required]"
                                          <c:if test="${doctor.isYearGraduate eq 'N'}">checked="checked"</c:if>
                                          value="N"/>否</label>

                        </td>
                        <th><font color="red">*</font>订单定向培训：</th>
                        <td>
                            <label><input type="radio" name="isGeneralOrderOrientationTrainee"
                                          class="validate[required]"
                                          <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'Y'}">checked="checked"</c:if>
                                          value="Y"/>是</label>&#12288;
                            <label><input type="radio" name="isGeneralOrderOrientationTrainee"
                                          class="validate[required]"
                                          <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'N'}">checked="checked"</c:if>
                                          value="N"/>否</label>

                        </td>
                        <th><font color="red">*</font>学位：</th>
                        <td>
                            <select id="degree" name="degreeId" class="validate[required]">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
                                    <option value="${dict.dictId}"
                                            <c:if test="${ dict.dictId eq extInfo.degreeId }">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <script>
                        $(function () {
                            var isMaster = '${extInfo.isMaster}';
                            checkIsMaster(isMaster);
                            var isDoctor = '${extInfo.isDoctor}';
                            checkIsDoctor(isDoctor);
                        })
                        function checkIsMaster(value) {
                            if (value == 'Y') {
                                $(".masterInfo").show();
                                var masterStatue = $("input[name='masterStatue']:checked").val();
                                checkIsStudy(masterStatue, 'master');
                            } else {
                                $(".masterInfo").hide();
                            }
                        }
                        function checkIsDoctor(value) {
                            if (value == 'Y') {
                                $(".doctorInfo").show();
                                var doctorStatue = $("input[name='doctorStatue']:checked").val();
                                checkIsStudy(doctorStatue, 'doctor');
                            } else {
                                $(".doctorInfo").hide();
                            }
                        }
                        function checkIsStudy(value, typeId) {
                            if (value == "1") {
                                $("." + typeId + "IsGrad").hide();//就读相关
                            } else {
                                $("." + typeId + "IsGrad").show();//毕业相关
                            }
                        }
                    </script>
                    <tr>
                        <th><font color="red">*</font>是否为硕士：</th>
                        <td>
                            <label><input type="radio" name="isMaster" class="validate[required]"
                                          <c:if test="${extInfo.isMaster eq 'Y'}">checked="checked"</c:if> value="Y"
                                          onchange="checkIsMaster(this.value)"/>是</label>&#12288;
                            <label><input type="radio" name="isMaster" class="validate[required]"
                                          <c:if test="${extInfo.isMaster eq 'N'}">checked="checked"</c:if> value="N"
                                          onchange="checkIsMaster(this.value)"/>否</label>
                        </td>
                        <th style="display: none" class="masterInfo"><font color="red">*</font>状态：</th>
                        <td style="display: none" class="masterInfo">
                            <label><input type="radio" name="masterStatue" class="validate[required]"
                                          <c:if test="${extInfo.masterStatue eq '1'}">checked="checked"</c:if> value="1"
                                          onchange="checkIsStudy(this.value,'master')"/>在读</label>&#12288;
                            <label><input type="radio" name="masterStatue" class="validate[required]"
                                          <c:if test="${extInfo.masterStatue eq '2'}">checked="checked"</c:if> value="2"
                                          onchange="checkIsStudy(this.value,'master')"/>已毕业</label>
                        </td>
                        <th style="display: none" class="masterInfo"><font color="red">*</font>硕士就读院校：</th>
                        <td style="display: none" class="masterInfo">
                            <input name="masterGraSchoolName" value="${extInfo.masterGraSchoolName}" type="text"
                                   class="validate[required]">
                        </td>
                    </tr>
                    <tr style="display: none" class="masterInfo">
                        <th><font color="red">*</font>硕士入学时间：</th>
                        <td>
                            <input name="masterStartTime" value="${extInfo.masterStartTime}" type="text"
                                   class="validate[required]" readonly="readonly"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">

                        </td>
                        <th><font color="red">*</font>所学专业：</th>
                        <td>
                            <input name="masterMajor" value="${extInfo.masterMajor}" type="text"
                                   class="validate[required]">

                        </td>
                    </tr>
                    <tr style="display: none" class="masterInfo masterIsGrad">
                        <th><font color="red">*</font>学位：</th>
                        <td>
                            <select name="masterDegreeId" class="select validate[required] master">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
                                    <option value="${dict.dictId}"
                                            <c:if test="${ dict.dictId eq extInfo.masterDegreeId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th>
                            <font color="red">*</font>学位类型：
                        </th>
                        <td>
                            <select name="masterDegreeTypeId" class="validate[required]">
                                <option value="">请选择</option>
                                <option value="1"
                                        <c:if test="${extInfo.masterDegreeTypeId eq '1'}">selected</c:if>
                                >专业型
                                </option>
                                <option value="2"
                                        <c:if test="${extInfo.masterDegreeTypeId eq '2'}">selected</c:if>
                                >科学型
                                </option>
                            </select>
                        </td>
                        <th><font color="red">*</font>硕士毕业时间：</th>
                        <td>
                            <input name="masterGraTime" value="${extInfo.masterGraTime}" type="text"
                                   class="validate[required]" readonly="readonly"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">

                        </td>
                    </tr>
                    <tr>
                        <th>
                            <font color="red">*</font>是否为博士：
                        </th>
                        <td>
                            <label><input type="radio" name="isDoctor" class="validate[required]"
                                          <c:if test="${extInfo.isDoctor eq 'Y'}">checked="checked"</c:if> value="Y"
                                          onchange="checkIsDoctor(this.value)"/>是</label>&#12288;
                            <label><input type="radio" name="isDoctor" class="validate[required]"
                                          <c:if test="${extInfo.isDoctor eq 'N'}">checked="checked"</c:if> value="N"
                                          onchange="checkIsDoctor(this.value)"/>否</label>

                        </td>
                        <th style="display: none" class="doctorInfo">
                            <font color="red">*</font>状态：
                        </th>
                        <td style="display: none" class="doctorInfo">
                            <label><input type="radio" name="doctorStatue" class="validate[required]"
                                          <c:if test="${extInfo.doctorStatue eq '1'}">checked="checked"</c:if> value="1"
                                          onchange="checkIsStudy(this.value,'doctor')"/>在读</label>&#12288;
                            <label><input type="radio" name="doctorStatue" class="validate[required]"
                                          <c:if test="${extInfo.doctorStatue eq '2'}">checked="checked"</c:if> value="2"
                                          onchange="checkIsStudy(this.value,'doctor')"/>已毕业</label>

                        </td>
                        <th style="display: none" class="doctorInfo"><font color="red">*</font>博士就读院校：</th>
                        <td style="display: none" class="doctorInfo">
                            <input name="doctorGraSchoolName" value="${extInfo.doctorGraSchoolName}" type="text"
                                   class="validate[required]">

                        </td>
                    </tr>
                    <tr style="display: none" class="doctorInfo">
                        <th><font color="red">*</font>博士入学时间：</th>
                        <td>
                            <input name="doctorStartTime" value="${extInfo.doctorStartTime}" type="text"
                                   class="validate[required]" readonly="readonly"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">

                        </td>
                        <th><font color="red">*</font>所学专业：</th>
                        <td>
                            <input name="doctorMajor" value="${extInfo.doctorMajor}" type="text"
                                   class="validate[required]">
                        </td>
                    </tr>
                    <tr style="display: none" class="doctorInfo doctorIsGrad">
                        <th><font color="red">*</font>学位：</th>
                        <td>
                            <select name="doctorDegreeId" class="validate[required]">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
                                    <option value="${dict.dictId}"
                                            <c:if test="${ dict.dictId eq extInfo.doctorDegreeId }">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th><font color="red">*</font>学位类型：</th>
                        <td>
                            <select name="doctorDegreeTypeId" class="validate[required]">
                                <option value="">请选择</option>
                                <option value="1"
                                        <c:if test="${extInfo.doctorDegreeTypeId eq '1'}">selected</c:if>
                                >专业型
                                </option>
                                <option value="2"
                                        <c:if test="${extInfo.doctorDegreeTypeId eq '2'}">selected</c:if>
                                >科学型
                                </option>
                            </select>
                        </td>
                        <th><font color="red">*</font>博士毕业时间：</th>
                        <td>
                            <input name="doctorGraTime" value="${extInfo.doctorGraTime}" type="text"
                                   class="validate[required]" readonly="readonly"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">

                        </td>
                    </tr>
                </table>
                <div style="display:none">
                    <table>
                        <input type="hidden" id="technologyQualificationName" name="technologyQualificationName"
                               value="${extInfo.technologyQualificationName}"/>
                        <input type="hidden" id="qualificationMaterialName" name="qualificationMaterialName"
                               value="${extInfo.qualificationMaterialName}"/>
                        <input type="hidden" id="practicingCategoryName" name="practicingCategoryName"
                               value="${extInfo.practicingCategoryName}"/>
                        <input type="hidden" id="practicingScopeName" name="practicingScopeName"
                               value="${extInfo.practicingScopeName}"/>
                        <!-- 学位类型Name-->
                        <input type="hidden" id="masterDegreeTypeName" name="masterDegreeTypeName"
                               value="${extInfo.masterDegreeTypeName}"/>
                        <input type="hidden" id="doctorDegreeTypeName" name="doctorDegreeTypeName"
                               value="${extInfo.doctorDegreeTypeName }"/>
                        <!-- 最高学历相关信息-->
                        <input type="hidden" id="uEducationName" name="user.educationName"
                               value="${user.educationName}"/>
                        <input type="hidden" id="dGraduatedName" name="doctor.graduatedName"
                               value="${doctor.graduatedName}"/>
                        <input type="hidden" id="dSpecialized" name="doctor.specialized" value="${doctor.specialized}"/>
                        <input type="hidden" id="dGraduationTime" name="doctor.graduationTime"
                               value="${doctor.graduationTime}"/>
                        <!-- 生源省份Name-->
                        <input type="hidden" id="sourceProvincesName" name="sourceProvincesName"
                               value="${doctor.sourceProvincesName}"/>
                        <%--人员属性--%>
                        <input type="hidden" id="personnelAttributeName" name="personnelAttributeName" value="${extInfo.personnelAttributeName}"/>
                        <th>最高毕业证书编号：</th>
                        <td><input name="doctor.certificateNo" value="${doctor.certificateNo}"
                                   class="input validate[]"/></td>
                        <th>最高毕业证书上传：</th>
                        <td>
                            <input type="hidden" id="certificateUriValue" name="certificateUri"
                                   value="${extInfo.certificateUri}"/>
                        </td>
                        </tr>
                        <tr>
                            <th>最高学位证书编号：</th>
                            <td><input name="doctor.degreeNo" value="${doctor.degreeNo}" class="input"/>&#12288;</td>
                            <th>最高学位证书上传：</th>
                            <td>
                                <input type="hidden" id="degreeUriValue" name="degreeUri" value="${extInfo.degreeUri}"/>
                            </td>
                        </tr>
                    </table>
                    <table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
                        <colgroup>
                            <col width="14%"/>
                            <col width="36%"/>
                            <col width="14%"/>
                            <col width="36%"/>
                        </colgroup>
                        <tbody>
                        <tr>
                            <th>专业技术资格：</th>
                            <td style="padding-left:10px;">
                                <select id="technologyQualificationId" name="technologyQualificationId" class="select"
                                        style="width: 160px;">
                                    <option value="">请选择</option>
                                    <option value="171" ${extInfo.technologyQualificationId eq '171'?'selected':''}>
                                        住院医师
                                    </option>
                                    <option value="172" ${extInfo.technologyQualificationId eq '172'?'selected':''}>
                                        主治医师
                                    </option>
                                    <option value="173" ${extInfo.technologyQualificationId eq '173'?'selected':''}>
                                        住院中医师
                                    </option>
                                    <option value="174" ${extInfo.technologyQualificationId eq '174'?'selected':''}>
                                        主治中医师
                                    </option>
                                    <option value="196" ${extInfo.technologyQualificationId eq '196'?'selected':''}>无
                                    </option>
                                </select>
                            </td>
                            <th>取得日期：</th>
                            <td colspan="2">
                                <input name="getTechnologyQualificationDate"
                                       value="${extInfo.getTechnologyQualificationDate}" class="input datepicker"
                                       style="width: 149px;" readonly="readonly"/>
                            </td>
                        </tr>
                        <tr>
                            <th>执业资格材料：</th>
                            <td style="padding-left:10px;">
                                <select id="qualificationMaterialId" name="qualificationMaterialId" class="select"
                                        style="width: 160px;">
                                    <option value="">请选择</option>
                                    <option value="176" ${extInfo.qualificationMaterialId eq '176'?'selected':''}>
                                        医师执业证书
                                    </option>
                                    <option value="177" ${extInfo.qualificationMaterialId eq '177'?'selected':''}>
                                        医师资格证书
                                    </option>
                                    <option value="178" ${extInfo.qualificationMaterialId eq '178'?'selected':''}>
                                        已通过国家执业医师考试的成绩单
                                    </option>
                                    <option value="200" ${extInfo.qualificationMaterialId eq '200'?'selected':''}>
                                        助理执业医师证书（定向大专）
                                    </option>
                                    <option value="201" ${extInfo.qualificationMaterialId eq '201'?'selected':''}>
                                        已通过国家助理执业医师考试的成绩单（定向大专）
                                    </option>
                                    <option value="202" ${extInfo.qualificationMaterialId eq '202'?'selected':''}>无
                                    </option>
                                </select>
                            </td>
                            <th>资格材料编码：</th>
                            <td colspan="2"><input name="qualificationMaterialCode"
                                                   value="${extInfo.qualificationMaterialCode}" class="input"/></td>
                        </tr>
                        <tr>
                            <th>执业类型：</th>
                            <td style="padding-left:10px;">

                                <select id="practicingCategoryId" name="practicingCategoryId" class="select"
                                        style="width: 160px;" onchange="changeCategoryId(this.value)">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType">
                                        <option value="${dictTypeEnumPracticeType.dictId}"
                                                <c:if test='${extInfo.practicingCategoryId==dictTypeEnumPracticeType.dictId}'>selected</c:if>>
                                                ${dictTypeEnumPracticeType.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>执业范围：</th>
                            <td style="padding-left:10px;">
                                <select id="practicingScopeId" name="practicingScopeId" class="select"
                                        style="width: 160px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
                                        <c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
                                        <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                            <option class="${scope.dictTypeId}" value="${scope.dictId}"
                                                    <c:if test='${extInfo.practicingScopeId==scope.dictId and dict.dictId == extInfo.practicingCategoryId}'>selected</c:if>>
                                                    ${scope.dictName}</option>
                                        </c:forEach>
                                    </c:forEach>
                                </select>
                            </td>
                            <script>
                                function changeCategoryId(dictId) {
                                    if (dictId == "") {
                                        $('#practicingScopeId').val("");
                                    }
                                    $('#practicingScopeId option').hide();
                                    $('#practicingScopeId option[value=""]').show();
                                    //$('#practicingScopeId').val("${extInfo.practicingScopeId}");
                                    $('#practicingScopeId' + ' option.${dictTypeEnumPracticeType.id}\\.' + dictId).show();
                                }
                                $(function () {
                                    changeCategoryId('${extInfo.practicingCategoryId}');
                                });
                            </script>
                        </tr>
                        <tr>
                            <th>资格证书上传：</th>
                            <td colspan="3">
                                <input type="hidden" id="qualificationMaterialUriValue" name="qualificationMaterialUri"
                                       value="${extInfo.qualificationMaterialUri}"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div id="d" style="display: none;">

                </div>
                <div id="d2" style="display: none;">

                </div>
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
                        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
                            <th><span class="deptSpan"><font color="red">*</font>所属部门：</span></th>
                            <td>
                                <span class="deptSpan">
                                <select name="deptFlow" class="validate[required]">
                                    <option></option>
                                    <c:forEach items="${deptList}" var="dept">
                                        <option value="${dept.deptFlow}" ${user.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName}</option>
                                    </c:forEach>
                                </select>
                                </span>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
                            <th><font color="red">*</font><span class="trainNameSpan">培训</span>基地：</th>
                            <td>
                                <select name="orgFlow" class="validate[required]" onchange="option(this.value)">
                                    <option>
                                        <c:forEach items="${orgList}" var="org">
                                    <option value="${org.orgFlow}" ${org.orgFlow eq doctor.orgFlow?'selected':''}>${org.orgName}</option>
                                    </c:forEach>
                                </select>

                            </td>
                        </c:if>
                        <c:if test="${GlobalConstant.USER_LIST_GLOBAL != param.roleFlag}">

                            <input name="orgFlow" type="hidden" value="${doctor.orgFlow}"/>
                        </c:if>
                        <th><font color="red">*</font><span class="trainNameSpan">培训</span>专业：</th>
                        <td>
                            <c:if test="${!rotationInUse}">
                                <select name="trainingSpeId"  class="validate[required]" id="trainingSpeId" onchange="selRotation();secondSpe();">
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
                            <select name="doctorStatusId" class="validate[required]"
                                    onchange="changeTerminat(this.value);">
                                <option></option>
                                <c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
                                    <option value="${dict.dictId}" ${doctor.doctorStatusId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>

                        </td>
                        <th class="${recDocCategoryEnumGraduate.id}">导师姓名：</th>
                        <td class="${recDocCategoryEnumGraduate.id}">
                            <select name="tutorFlow" onchange="$('#tutorName').val($(':selected',this).text());">
                                <option/>
                                <c:forEach items="${tutorList}" var="tutor">
                                    <option value="${tutor.userFlow}"
                                            <c:if test="${tutor.userFlow eq doctor.tutorFlow}">selected</c:if>>${tutor.userName}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" id="tutorName" name="tutorName" value="${doctor.tutorName}"/>
                        </td>
                        <th class="${recDocCategoryEnumGraduate.id}">研究方向：</th>
                        <td class="${recDocCategoryEnumGraduate.id}">
                            <input type="text" name="researchDirection" value="${doctor.researchDirection}"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="${resDoctorStatusEnumTerminat.id}">终止原因：</th>
                        <td class="${resDoctorStatusEnumTerminat.id}">
                            <input type="text" name="terminatReason" value="${doctor.terminatReason}"/>
                        </td>
                        <th class="${resDoctorStatusEnumTerminat.id}">终止时间：</th>
                        <td class="${resDoctorStatusEnumTerminat.id}">
                            <input type="text" name="terminatDate" value="${doctor.terminatDate}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/>
                        </td>
                        <th class="${resDoctorStatusEnumGraduation.id}">结业证书号：</th>
                        <td class="${resDoctorStatusEnumGraduation.id}">
                            <input type="text" name="completeNo" value="${doctor.completeNo}"/>
                        </td>
                        <th class="${resDoctorStatusEnumGraduation.id}">结业时间：</th>
                        <td class="${resDoctorStatusEnumGraduation.id}">
                            <input type="text" name="completeDate" value="${doctor.completeDate}"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <c:if test="${!empty applicationScope.sysCfgMap['res_disciple_role_flow']}">

                            <th><c:if test="${role ne 'global'}"><font color="red">*</font></c:if>师承老师：</th>
                            <td>
                                <input id="orgSel" class="toggleView <c:if test="${role ne 'global'}">validate[required]</c:if>" type="text"
                                       name="discipleTeacherName" value="${doctor.discipleTeacherName}"
                                       autocomplete="off"/>
                                <input class="toggleView xltext" hidden type="text" name="discipleTeacherFlow"
                                       id="discipleTeacherFlow" value="${doctor.discipleTeacherFlow}"
                                       autocomplete="off"/>
                                <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                                    <div id="boxHome"
                                         style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                                        <c:forEach items="${discipleList}" var="disciple">
                                            <p class="item" flow="${disciple.userFlow}" value="${disciple.userName}" style="height: 30px;padding-left: 10px;">${disciple.userName}[${disciple.orgName}]</p>
                                        </c:forEach>
                                    </div>
                                </div>
                            </td>
                        </c:if>
                        <th><label>实际培训&#12288;<br/>开始时间：</label></th>
                        <td>
                            <input type="text" value="${doctor.inHosDate}" name="inHosDate" id="inHosDate"
                                   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
                        </td>
                        <th><font color="red">*</font>结业考核年份：</th>
                        <td>

                            <c:if test="${!(role eq 'hospital')}">
                                <input type="text" readonly="readonly" name="graduationYear" id="graduationYear"
                                       value="${doctor.graduationYear}"
                                       onclick="WdatePicker({dateFmt:'yyyy'})">
                            </c:if>
                            <c:if test="${role eq 'hospital'}">
                                <label id="graduationYearLebal">${doctor.graduationYear}</label>
                                <input type="hidden" name="graduationYear" id="graduationYear"
                                       value="${doctor.graduationYear}">
                            </c:if>
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
                <%--
                <table style="width:100%;">
                    <caption>教育背景</caption>
                    <colgroup>
                    <col width="14%"/>
                    <col width="20%"/>
                    <col width="12%"/>
                    <col width="20%"/>
                    <col width="12%"/>
                    <col width="20%"/>
                    </colgroup>
                    <tr>
                        <th><span class="schoolNameSpan">毕业</span>院校：</th>
                        <td>
                            <select name="graduatedId">
                                <option></option>
                                <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict">
                                    <option value="${dict.dictId}" ${doctor.graduatedId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th><span class="educationSpan">学历：</span></th>
                        <td>
                            <span class="educationSpan">
                                <select name="educationId">
                                    <option></option>
                                    <c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
                                        <option value="${dict.dictId}" ${user.educationId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </span>
                        </td>
                        <th><span class="degreeSpan">学位：</span></th>
                        <td >
                            <span class="degreeSpan">
                                <select name="degreeId">
                                    <option></option>
                                    <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
                                        <option value="${dict.dictId}" ${user.degreeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <th>所学专业：</th>
                        <td>
                            <input type="text" name="specialized" value="${doctor.specialized}"/>
                        </td>
                        <th><span class="graduateSpan">毕业证书号：</span></th>
                        <td>
                            <span class="graduateSpan"><input type="text" name="certificateNo" value="${doctor.certificateNo}"/></span>
                        </td>
                        <th><span class="graduateSpan">毕业时间：</span></th>
                        <td>
                            <span class="graduateSpan"><input type="text" name="graduationTime" value="${doctor.graduationTime}"
                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/></span>
                        </td>
                    </tr>
                    <tr>
                        <th>计算机能力：</th>
                        <td>
                            <input type="text" name="computerSkills" value="${doctor.computerSkills}"/>
                        </td>
                        <th>外语能力：</th>
                        <td colspan="3">
                            <input type="text" name="foreignSkills" value="${doctor.foreignSkills}"/>
                        </td>
                    </tr>
                </table>

                <div id="workInfo" style="display: ;">
                <table style="width:100%;">
                    <caption>工作信息</caption>
                    <colgroup>
                    <col width="14%"/>
                    <col width="20%"/>
                    <col width="12%"/>
                    <col width="20%"/>
                    <col width="12%"/>
                    <col width="20%"/>
                    </colgroup>
                    <tr>
                        <th>工作单位：</th>
                        <td><input type="text" name="workOrgName" value="${doctor.workOrgName}"/></td>
                        <th>入院时间：</th>
                        <td>
                            <input type="text" name="inHosDate" value="${doctor.inHosDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/>
                        </td>
                        <!--
                        <th>科室：</th>
                        <td>
                            <select name="deptFlow">
                                <option></option>
                                <c:forEach items="${deptList}" var="dept">
                                    <option value="${dept.deptFlow}" ${user.deptFlow eq dept.deptFlow?'selected="selected"':''}>${dept.deptName}</option>
                                </c:forEach>
                            </select>
                        </td>
                         -->
                         <th></th>
                         <td></td>
                    </tr>
                    <tr>
                        <th>职称：</th>
                        <td>
                            <select name="titleId">
                                <option></option>
                                <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                       <option value="${title.dictId}" ${user.titleId eq title.dictId?'selected="selected"':''}>${title.dictName}</option>
                                   </c:forEach>
                            </select>
                        </td>
                        <th>职务：</th>
                        <td colspan="3">
                            <select name="postId">
                                <option></option>
                                <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                     <option value="${post.dictId}" ${user.postId eq post.dictId?'selected="selected"':''}>${post.dictName}</option>
                                   </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>
                </div>

                <table style="width:100%;">
                    <caption>证书情况</caption>
                    <colgroup>
                    <col width="14%"/>
                    <col width="20%"/>
                    <col width="12%"/>
                    <col width="20%"/>
                    <col width="12%"/>
                    <col width="20%"/>
                    </colgroup>
                    <tr>
                        <th>执业医师资格证号：</th>
                        <td><input type="text" name="qualifiedNo" value="${doctor.qualifiedNo}"/></td>
                        <th>获取时间：</th>
                        <td colspan="3"><input type="text" name="qualifiedDate" value="${doctor.qualifiedDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <th>执业医师注册证号：</th>
                        <td><input type="text" name="regNo" value="${doctor.regNo}"/></td>
                        <th>获取时间：</th>
                        <td><input type="text" name="regDate" value="${doctor.regDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/></td>
                        <th>执业地点：</th>
                        <td><input type="text" name="regAddress" value="${doctor.regAddress}"/></td>
                    </tr>
                </table>

                <!-- 教育经历 -->
                <div id="edu" style="padding-top: 10px;"></div>

                <!-- 工作经历 -->
                <div id="work" style="padding-top: 10px;"></div>
                --%>
            </form>
            <form id="returnFrom">
                <input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
                <input type="hidden" name="typeId" value="${resRecTypeEnumReturnTraining.id}"/>
                <input type="hidden" name="typeName" value="${resRecTypeEnumReturnTraining.name}"/>
                <table id="returnTable" style="width:100%;display: none;">
                    <caption>退培信息</caption>
                    <colgroup>
                        <col width="20%"/>
                        <col width="20%"/>
                        <col width="60%"/>
                    </colgroup>
                    <tr>
                        <th>
                            退培类型：
                        </th>
                        <td>
                            <select class="validate[required]" name="policyId" style="width: 90%;">
                                <option value="">请选择</option>
                                <option value="1">协议退培</option>
                                <option value="2">违约退培</option>
                            </select>
                        </td>
                        <td>
                            <input name="policy" value="" class="validate[required]"
                                   style="width: 86%;height: 70%"/><span class="red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            退培主要原因：
                        </th>
                        <td>
                            <select class="validate[required]" name="reasonId" style="width: 90%;">
                                <option value="">请选择</option>
                                <option value="1">辞职</option>
                                <option value="2">考研</option>
                                <option value="3">其他</option>
                            </select>
                        </td>
                        <td>
                            <input name="reason" value="" class="validate[required]"
                                   style="width: 86%;height: 70%"/><span class="red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <th>学员去向：</th>
                        <td colspan="2">
                            <input name="dispositon" value="" class="validate[required]"
                                   style="width: 90%;height: 70%"/><span class="red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <th>备注（培训基地意见）：</th>
                        <td colspan="2">
                            <input name="remark" value="" class="validate[required]"
                                   style="width: 90%;height: 70%"/><span class="red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            上传附件：
                        </th>
                        <td colspan="2">
                            <input type="file" name="file" class="validate[required]"/><span class="red">*</span>
                        </td>
                    </tr>
                </table>
            </form>
            <form id="delayForm">
                <input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
                <table id="delayTable" style="width:100%;display: none;">
                    <caption>延期信息</caption>
                    <colgroup>
                        <col width="10%"/>
                        <col width="40%"/>
                        <col width="10%"/>
                        <col width="30%"/>
                    </colgroup>
                    <tr>
                        <th>延期原因：</th>
                        <td colspan="3">
                            <input class="validate[required]" id="delayreason" name="delayreason" style="width: 91%;height: 70%;"
                                   value=""><span class="red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <th>上传附件：</th>
                        <td colspan="3">
                            <input type="file" id="file" name="file" class="validate[required]"/><span class="red">*</span>
                    </tr>
                    <tr>
                        <th>结业考核年份：</th>
                        <td style="text-align: left">
                            <input class="validate[required]" name="delayYear" id="delayYear" readonly="readonly"
                                   onclick="WdatePicker({dateFmt:'yyyy'});"
                                   style="width: 20%;height: 70%;" value=""><span class="red">*</span>
                        </td>
                    </tr>

                </table>
            </form>
            <p style="text-align: center;">
                <c:if test="${!(waitReturn eq 'waitReturn')}">
                    <input type="button" onclick="saveDoc();" id="saveButton1" class="search" value="保&#12288;存"/>
                </c:if>
                <input type="button" onclick="saveRede();" id="saveButton2" style="display: none" class="search2"
                       value="确认退培"/>
                <input type="button" onclick="saveDelay();" id="saveButton3" style="display: none" class="search2"
                       value="确认延期"/>
                <c:if test="${!param.isMenu}">
                    <input type="button" onclick="doClose();" id="close" class="search" value="关&#12288;闭"/>
                </c:if>
                <c:if test="${role eq 'hospital'}">
                    <c:if test="${sysCfgMap['res_org_delay_return'] eq 'Y'}">
                        <script>
                            function reCheck(obj) {
                                $(obj).hide();
                                $("#down").hide();
                                $("#file").show();
                            }
                            function saveDelay() {
                                if ($("#delayForm").validationEngine("validate")) {
                                    if(!$("#delayreason").val())
                                    {
                                        jboxTip("请输入延期原因!");
                                        return false;
                                    }
                                    if(!$("#file").val())
                                    {
                                        jboxTip("请上传附件!");
                                        return false;
                                    }
                                    if("${doctor.graduationYear}">=$("#delayYear").val()){
                                        jboxTip("延期结业年份应大于现结业年份");
                                        return false;
                                    }
                                    jboxConfirm("确认延期？", function () {
                                        var url = '<s:url value="/res/doc/delayDoc"/>';
                                        jboxSubmit($("#delayForm"), url, function (resp) {
                                            if (resp == 1) {
                                                jboxTip("延期成功!");
                                                window.parent.frames['mainIframe'].search();
                                                jboxClose();
                                            } else if (resp == 3) {
                                                jboxTip("上传附件不得大于10M!");
                                            }
                                            else  jboxTip("延期失败!");
                                        }, null, false)
                                    })
                                }
                            }
                            function saveRede() {
                                if ($("#returnFrom").validationEngine("validate")) {
                                    jboxConfirm("确认退培？", function () {
                                        var url = '<s:url value="/res/doc/returnDoc"/>';
                                        jboxSubmit($("#returnFrom"), url, function (resp) {
                                            if (resp == 1) {
                                                jboxTip("退培成功!");
                                                window.parent.frames['mainIframe'].search();
                                                jboxClose();
                                            } else if (resp == 3) {
                                                jboxTip("上传附件不得大于10M!");
                                            }
                                            else jboxTip("退培失败!");
                                        }, null, false)
                                    })
                                }
                            }
                            var count = 0;
                            var count2 = 0;
                            function returnModel() {
                                count2 = 0;
                                $("#delayButton").val("延    期");
                                $("#delayTable").hide();
                                $("#saveButton3").hide();
                                $("#returnTable").toggle();
                                $("#saveButton2").toggle();
                                if (count % 2 == 0) {
                                    $("#returnButton").val("取消退培");
                                    $("#saveButton1").hide();
                                } else {
                                    $("#returnButton").val("退    培");
                                    $("#saveButton1").show();
                                }
                                count += 1;
                            }

                            function delayModel() {
                                count = 0;
                                $("#returnButton").val("退    培");
                                $("#returnTable").hide();
                                $("#saveButton2").hide();
                                $("#delayTable").toggle();
                                $("#saveButton3").toggle();
                                if (count2 % 2 == 0) {
                                    $("#delayButton").val("取消延期");
                                    $("#saveButton1").hide();
                                } else {
                                    $("#delayButton").val("延    期");
                                    $("#saveButton1").show();
                                }
                                count2 += 1;
                            }
                        </script>
                        <%--江苏中医退培走招录系统--%>
                        <%--<c:if test="${!(waitReturn eq 'waitReturn')}">--%>

                            <%--<input type="button" id="delayButton" onclick="delayModel();" class="search2"--%>
                                   <%--value="延&#12288;期"/>--%>
                            <%--<input type="button" id="returnButton" onclick="returnModel();" class="search2"--%>
                                   <%--value="退&#12288;培"/>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${waitReturn eq 'waitReturn'}">--%>
                            <%--<span style="font-size: inherit;color:red">退培待省厅审核中</span>--%>
                        <%--</c:if>--%>
                    </c:if>
                </c:if>
            </p>
        </div>
    </div>
</div>
</body>
</html>