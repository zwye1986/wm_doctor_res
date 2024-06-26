<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <c:if test="${!param.head}">
        <jsp:include page="/jsp/common/htmlhead.jsp">
            <jsp:param name="basic" value="true"/>
            <jsp:param name="jbox" value="true"/>
            <jsp:param name="jquery_ui_tooltip" value="true"/>
            <jsp:param name="jquery_cxselect" value="true"/>
            <jsp:param name="jquery_validation" value="true"/>
            <jsp:param name="jquery_datePicker" value="true"/>
            <jsp:param name="jquery_fixedtableheader" value="true"/>
            <jsp:param name="jquery_placeholder" value="true"/>
        </jsp:include>
    </c:if>
    <style type="text/css">
        table td {
            text-align: left;
        }
        #tags li {
            margin-top: 5px;
        }
        .before{
            color: blue;cursor: pointer;
        }
        .after{
            color:dimgrey;
        }
    </style>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        function drop(time){
            if(time!=null&&time!=""){
                $("#schoolRollStatusId").val("5");
            }else{
                $("#schoolRollStatusId").val("2");
            }
        }
        function apply() {
            jboxOpen("<s:url value='/jsp/gyxjgl/student/application.jsp'/>", "异动申请", 900, 500);
        }
        function save() {
            if (!$("#addForm").validationEngine("validate")) {
                return;
            }
            //学位类别 名称 赋值
            var degreeLevelNameText = $("select[name='eduUser.degreeLevelId'] option:selected").text();
            $("input[name='eduUser.degreeLevelName']").val(degreeLevelNameText);
            var form = $("#addForm").serialize();
            jboxPost("<s:url value='/gyxjgl/user/saveEduUser'/>?roleFlag=${roleFlag}", form, function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    location.reload();
                    jboxClose();
                }
            });
        }
        function uploadImage() {
            $.ajaxFileUpload({
                url: "<s:url value='/gyxjgl/user/userHeadImgUpload'/>?userFlow=${sysUser.userFlow}",
                secureuri: false,
                fileElementId: 'imageFile',
                dataType: 'json',
                success: function (data, status) {
                    if (data.indexOf("success") == -1) {
                        jboxTip(data);
                    } else {
                        jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
                        var arr = [];
                        arr = data.split(":");
                        var url = "${sysCfgMap['upload_base_url']}/" + arr[1];
                        $("#showImg").attr("src", url);
                        $("#img").val(arr[1]);
                    }
                },
                error: function (data, status, e) {
                    jboxTip('${GlobalConstant.UPLOAD_FAIL}');
                },
                complete: function () {
                    $("#imageFile").val("");
                }
            });
        }
        //上传材料证明
        function uploadMaterialCert(obj){
            var id = $(obj).attr("id");
            $.ajaxFileUpload({
                url:"<s:url value='/gyxjgl/user/uploadMaterialCert?userFlow=${eduUser.userFlow}&columnName='/>"+id,
                secureuri:false,
                fileElementId:id,
                dataType: 'json',
                success: function (data, status){
                    if(data.indexOf("success")==-1){
                        jboxTip(data);
                    }else{
                        jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
                        $("."+id).show();
                        var url = "${sysCfgMap['upload_base_url']}/"+ data.split(":")[1];
                        $("#arg_"+id).val(data.split(":")[1]);
                        $("."+id).children("a").attr("href",url);
                    }
                },
                error: function (data, status, e){
                    jboxTip('${GlobalConstant.UPLOAD_FAIL}');
                },
                complete:function(){
                    $(obj).val("");
                }
            });
        }
        $(document).ready(function () {
            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                //管理员全部可以编辑，不做控制

                $("input:not(.notBlank)").removeClass("validate[required]");
                $("select:not(.notBlank)").removeClass("validate[required]");
            </c:if>
            jboxEndLoading();
            loadIdCheck();

            //政治面貌初始化
            if($('.politics').val() == '12' || $('.politics').val() == '13' || $('.politics').val() == '99'){
                $('.redStar').hide();
                $('.needFlag').removeClass("validate[required]");
            }else{
                $('.redStar').show();
                $('.needFlag').addClass("validate[required]");
            }
            //政治面貌change绑定
            $(".politics").change(function () {
                var value = $(this).val();
                if (value == "12" || value == "13" || value == "99") {
                    $('.redStar').hide();
                    $('.needFlag').removeClass("validate[required]");
                } else {
                    $('.redStar').show();
                    $('.needFlag').addClass("validate[required]");
                }
            });
            //学位类别初始化
            if($('#degreeLevelFlag').val() == "4" || $('#degreeLevelFlag').val() == "5" ){
                $('#degreeLevelFlagTh').show();
                $('#degreeLevelFlagTd').show();
                $('#degreeLevelFlagTh1').hide();
                $('#degreeLevelFlagTd1').hide();
                optionShow($('#degreeLevelFlag').val());
            }else{
                $('#degreeLevelFlagTh').hide();
                $('#degreeLevelFlagTd').hide();
                $('#degreeLevelFlagTh1').show();
                $('#degreeLevelFlagTd1').show();
            }
            //学位类别change绑定
            $("#degreeLevelFlag").change(function () {
                if($('#degreeLevelFlag').val() == "4" || $('#degreeLevelFlag').val() == "5" ){
                    $('#degreeLevelFlagTh').show();
                    $('#degreeLevelFlagTd').show();
                    $('#degreeLevelFlagTh1').hide();
                    $('#degreeLevelFlagTd1').hide();
                    var value = $(this).val();
                    optionShow(value);
                }else{
                    $('#degreeLevelFlagTh').hide();
                    $('#degreeLevelFlagTd').hide();
                    $('#degreeLevelFlagTh1').show();
                    $('#degreeLevelFlagTd1').show();
                    $("input[name='eduUser.degreeLevelId']").val("");
                }
            });
            //默认展示基本信息
            $("#tags").find("li").eq(0).addClass("selectTag");
            selectTag(this,'baseInfo');
            loadOrgAndTeacher();
        });
        function optionShow(value){
            if (value == "4") {
                $("#degreeLevelSelect option[type='level11']").prop("style","display:block;");
                $("#degreeLevelSelect option[type='level12']").prop("style","display:block;");
                $("#degreeLevelSelect option[type='level13']").prop("style","display:block;");
                $("#degreeLevelSelect option[type='level14']").prop("style","display:block;");
                $("#degreeLevelSelect option[type='level25']").prop("style","display:none;");
                $("#degreeLevelSelect option[type='level26']").prop("style","display:none;");
                $("#degreeLevelSelect option[type='level27']").prop("style","display:none;");
                $("#degreeLevelSelect option[type='level28']").prop("style","display:none;");
                $("#degreeLevelSelect option[type='level29']").prop("style","display:none;");
            }else if (value == "5") {
                $("#degreeLevelSelect option[type='level11']").prop("style","display:none;");
                $("#degreeLevelSelect option[type='level12']").prop("style","display:none;");
                $("#degreeLevelSelect option[type='level13']").prop("style","display:none;");
                $("#degreeLevelSelect option[type='level14']").prop("style","display:none;");
                $("#degreeLevelSelect option[type='level25']").prop("style","display:block;");
                $("#degreeLevelSelect option[type='level26']").prop("style","display:block;");
                $("#degreeLevelSelect option[type='level27']").prop("style","display:block;");
                $("#degreeLevelSelect option[type='level28']").prop("style","display:block;");
                $("#degreeLevelSelect option[type='level29']").prop("style","display:block;");
            }
        }
        function getOrgName() {
            $("#resDoctorOrgName").val($("#resDoctorOrgFlow option:selected").text());
        }
        function loadIdCheck() {
            var idNoObj = $("#idNo");
            var cretTypeId = $("#cretTypeId").val();
            idNoObj.removeClass("validate[custom[chinaId]]");
            if (cretTypeId == "${certificateTypeEnumShenfenzheng.id}") {
                idNoObj.addClass("validate[custom[chinaId]]");
            }
        }

        function doDisabled(obj) {
            var id = $(obj).attr("id");
            var value = obj.value;
            var $date = $("#" + id + "Date");
            if ("${GlobalConstant.FLAG_Y}" == value||(!value)) {
                var oldValue = "";
                if (id == "backToSchool") {
                    oldValue = "${extInfoForm.backToSchoolDate}";
                } else if (id == "outOfSchool") {
                    oldValue = "${extInfoForm.outOfSchoolDate}";
                }
                $date.val(oldValue);
                $("#" + id + "Td").next().next().next().remove();
                $("#" + id + "Td").next().next().next().remove();
                $("." + id + "Td").show();
            } else {
                $date.val(null);
                $("#" + id + "Td").parent().append("<th></th><td></td>")
                $("." + id + "Td").hide();
            }
        }

        function editRegisterDate(roleFlag) {
            var title = "编辑";
            var url = "<s:url value='/gyxjgl/user/editRegisterDate'/>?userFlow=${sysUser.userFlow}";
            if ("${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}" == roleFlag) {
                url += "&view=${GlobalConstant.FLAG_Y}";
                title = "查看";
            }
            jboxOpen(url, title + "学籍注册时间", 500, 300);
        }

        function selectSingle(obj) {
            var value = $(obj).val();
            var name = $(obj).attr("name");
            $("input[name=" + name + "][value!=" + value + "]:checked").attr("checked", false);
        }

        function setIsMdfInfo(obj) {
            if ($(obj).attr("checked") == "checked") {
                $("#isMdfInfo").val("${GlobalConstant.FLAG_Y}");
            } else {
                $("#isMdfInfo").val("${GlobalConstant.FLAG_N}");
            }
        }
        function resetPasswd(userFlow) {
            jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？", function () {
                var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>" + userFlow;
                jboxGet(url, null, function () {
                });
            });
        }
        function changeSchoolStatus(obj)
        {
            var value=$(obj).val();
            var elements=$("#atSchoolStatusId option");
            for(var i=0;i<elements.length;i++)
            {
                var b=elements[i];
                if(b.text=='已毕业')
                {
                    if(value=="${GlobalConstant.FLAG_Y}"){
                        $(b).attr("selected",true);
                    }else{
                        $(b).removeAttr("selected");
                    }
                }
            }

        }

        function confirmStatus(partId,temp){
            jboxConfirm("是否确定本页信息无误？" , function(){
                jboxPost("<s:url value='/gyxjgl/course/manage/savePartStatus?userFlow=${eduUser.userFlow}&partId='/>"+partId, null, function (obj) {
                    if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                        jboxTip("信息确认成功！");
                        $(temp).removeClass();
                        $(temp).addClass("after");
                        $(temp).html("本页信息已确认");
                        $(temp).removeAttr("onclick");
                    }
                },null,false);
            });
        }

        function loadOrgAndTeacher(){
            var firstTeacherArray = new Array();
            if(${not empty tutorList}){
                <c:forEach items="${tutorList}" var="tutor" varStatus="i">
                var firstTeacherFlow='${tutor.doctorFlow}';
                if(${empty tutor.doctorFlow}){
                    firstTeacherFlow="";
                }
                var firstTeacher='${tutor.doctorName}';
                var courseName='${tutor.doctorName}';
                if(${empty tutor.doctorName}){
                    firstTeacher="";
                    courseName="";
                }
                firstTeacherArray[${i.index}]=new Array(firstTeacherFlow,firstTeacher,courseName);
                if($("#firstTeacherFlow").val() == firstTeacherFlow){
                    $("#param_firstTeacher").val(firstTeacher);
                }
                </c:forEach>
                jboxStartLoading();
                $("#param_firstTeacher").suggest(firstTeacherArray,{
                    attachObject:'#suggest_firstTeacher',
                    dataContainer:'#firstTeacherFlow',
                    courseName:'#firstTeacher',
                    triggerFunc:function(orgFlow){
                    },
                    enterFunc:function(orgFlow){
                    }
                });
                jboxEndLoading();
            }
        }
        function adjustResults1() {
            $("#suggest_firstTeacher").css("left",$("#param_firstTeacher").offset().left);
            $("#suggest_firstTeacher").css("top",$("#param_firstTeacher").offset().top+$("#param_firstTeacher").outerHeight());
        }

        function selectTag(selfObj,url) {
            var selLi = $(selfObj).parent();
            selLi.siblings("li").removeClass("selectTag");
            selLi.addClass("selectTag");
            $("div.spreadOne").hide();
            $("#"+url).show();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="width: 100%;background-color: white; padding-top: 10px;" align="center">
            <table class="basic" style="width: 100%; background-color: white;" align="center">
                <tr>
                    <td>
                        <!-- 针对特定学员 开放 就业信息修改 -->
                        <c:set var="employmentN" value="${permissionCfg.cfgValue eq 'N'&& eduUser.isMdfInfo eq 'Y' && roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openEmploymentStartDate.cfgValue && pdfn:getCurrDate() le closeEmploymentEndDate.cfgValue}"/>
                        <!-- 针对所有学员 开放 就业信息修改 -->
                        <c:set var="employmentY" value="${permissionCfg.cfgValue eq 'Y' && roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openEmploymentStartDate.cfgValue && pdfn:getCurrDate() le closeEmploymentEndDate.cfgValue
                            && (eduUser.period eq yearCfg.cfgValue || empty yearCfg.cfgValue) && (eduUser.trainTypeId eq trainTypeCfg.cfgValue || empty trainTypeCfg.cfgValue)}"/>
                        <c:if test="${employmentN or employmentY}">
                            <c:set var="employmentFlag" value="true"/>
                        </c:if>
                        <!-- 针对特定学员 开放 学位论文修改 -->
                        <c:set var="paperN" value="${permissionCfg.cfgValue eq 'N'&& eduUser.isMdfInfo eq 'Y' && roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openPaperStartDate.cfgValue && pdfn:getCurrDate() le closePaperEndDate.cfgValue}"/>
                        <!-- 针对所有学员 开放 学位论文修改 -->
                        <c:set var="paperY" value="${permissionCfg.cfgValue eq 'Y' && roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openPaperStartDate.cfgValue && pdfn:getCurrDate() le closePaperEndDate.cfgValue
                            && (eduUser.period eq yearCfg.cfgValue || empty yearCfg.cfgValue) && (eduUser.trainTypeId eq trainTypeCfg.cfgValue || empty trainTypeCfg.cfgValue)}"/>
                        <c:if test="${paperN or paperY}">
                            <c:set var="paperFlag" value="true"/>
                        </c:if>
                        <!-- 针对特定学员 开放 学业和学位授予信息修改 -->
                        <c:set var="degreeGrantN" value="${permissionCfg.cfgValue eq 'N'&& eduUser.isMdfInfo eq 'Y' && roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openDegreeGrantStartDate.cfgValue && pdfn:getCurrDate() le closeDegreeGrantEndDate.cfgValue}"/>
                        <!-- 针对所有学员 开放 学业和学位授予信息修改 -->
                        <c:set var="degreeGrantY" value="${permissionCfg.cfgValue eq 'Y' && roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openDegreeGrantStartDate.cfgValue && pdfn:getCurrDate() le closeDegreeGrantEndDate.cfgValue
                            && (eduUser.period eq yearCfg.cfgValue || empty yearCfg.cfgValue) && (eduUser.trainTypeId eq trainTypeCfg.cfgValue || empty trainTypeCfg.cfgValue)}"/>
                        <c:if test="${degreeGrantN or degreeGrantY}">
                            <c:set var="degreeGrantFlag" value="true"/>
                        </c:if>
                        <!-- 针对特定学员 开放 必填信息修改 -->
                        <c:set var="requiredN" value="${permissionCfg.cfgValue eq 'N'&& eduUser.isMdfInfo eq 'Y' && roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openRequiredStartDate.cfgValue && pdfn:getCurrDate() le closeRequiredEndDate.cfgValue}"/>
                        <!-- 针对所有学员 开放 必填信息修改 -->
                        <c:set var="requiredY" value="${permissionCfg.cfgValue eq 'Y' && roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openRequiredStartDate.cfgValue && pdfn:getCurrDate() le closeRequiredEndDate.cfgValue
                            && (eduUser.period eq yearCfg.cfgValue || empty yearCfg.cfgValue) && (eduUser.trainTypeId eq trainTypeCfg.cfgValue || empty trainTypeCfg.cfgValue)}"/>
                        <c:if test="${requiredN or requiredY}">
                            <c:set var="requiredFlag" value="true"/>
                        </c:if>
                        <!-- 针对特定学员 开放 选填信息修改 -->
                        <c:set var="optionalN" value="${permissionCfg.cfgValue eq 'N'&& eduUser.isMdfInfo eq 'Y' && roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openOptionalStartDate.cfgValue && pdfn:getCurrDate() le closeOptionalEndDate.cfgValue}"/>
                        <!-- 针对所有学员 开放 选填信息修改 -->
                        <c:set var="optionalY" value="${permissionCfg.cfgValue eq 'Y' && roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openOptionalStartDate.cfgValue && pdfn:getCurrDate() le closeOptionalEndDate.cfgValue
                            && (eduUser.period eq yearCfg.cfgValue || empty yearCfg.cfgValue) && (eduUser.trainTypeId eq trainTypeCfg.cfgValue || empty trainTypeCfg.cfgValue)}"/>
                        <c:if test="${optionalN or optionalY}">
                            <c:set var="optionalFlag" value="true"/>
                        </c:if>

                        <!-- 学校管理员下未选课的学员可编辑专业和培养层次 -->
                        <c:if test="${(roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster) && (empty eduUser.chooseCourseStatusId or (eduUser.chooseCourseStatusId eq xjChooseCourseStatusEnumUnChoose.id))}">
                            <c:set var="importModifyFlag" value="true"/>
                        </c:if>
                        <c:if test="${permissionCfg.cfgValue eq 'N' && roleFlag ne GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
                            &#12288;是否允许修改学籍：
                            <input name="isMdfInfo" type="checkbox" onclick="setIsMdfInfo(this);" value="${GlobalConstant.FLAG_Y}" ${GlobalConstant.FLAG_Y eq eduUser.isMdfInfo?'checked':''} style="cursor: pointer;"/>
                        </c:if>
                        <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
                            <lable style="color:red;">&#12288;&#12288;注意：学籍如有错误请联系培养科老师或学校管理员</lable>
                        </c:if>
                        <div style="float: right;margin-top: 3px;">
                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster}">
                                <a href="javascript:resetPasswd('${eduUser.userFlow}');" style="color: blue;">重置密码</a>&#12288;
                            </c:if>
                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR or employmentFlag or paperFlag or degreeGrantFlag or requiredFlag or optionalFlag or isFeeMaster}">
                                <input type="button" class="search" onclick="save();" style="" value="保&#12288;存"/>
                            </c:if>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        <div style="background-color: white;height: 10px;width: 100%;"></div>
        <form id="addForm" action="<s:url value="/gyxjgl/user/saveEduUser"/>" method="post" style="position: relative;">
            <input type="hidden" id="isMdfInfo" name="eduUser.isMdfInfo" value="${eduUser.isMdfInfo }">
            <input type="hidden" name="eduUser.userFlow" value="${eduUser.userFlow }">
            <input type="hidden" name="sysUser.userFlow" value="${sysUser.userFlow }">
            <input type="hidden" name="resDoctor.doctorFlow" value="${resDoctor.doctorFlow }">
            <%--<table class="basic" style="width:100%;">--%>
            <%--<tr>--%>
            <%--<th style="text-align:left;">&#12288;学生基本信息</th>--%>
            <%--</tr>--%>
            <%--</table>--%>
            <div style="margin-left:-2px;margin-top:10px;">
                <div style="max-width: 1187px;">
                    <ul id="tags" style="margin-left: 0px;">
                        <li>
                            <a onclick="selectTag(this,'baseInfo')" href="javascript:void(0)">基本信息</a>
                        </li>
                    </ul>
                </div>
                <div id="tagContent" style="margin-top: 3px;">
                </div>
                <div class="spreadOne" style="margin-bottom:10px;" hidden="hidden" id="baseInfo">
                    <input type="hidden" id="baseStatus" name="baseStatus" value="${param.baseStatus}">
                    <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;基本信息--%>
                    <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'}">
                        <span style="float:right;margin-right:100px;margin-top: 5px;margin-bottom: 5px;"><c:if test="${statusMap['BaseInfo'] ne 'Y'}"><a class="before" onclick="confirmStatus('BaseInfo',this)">本页信息未确认，点击确认</a></c:if>
                        <c:if test="${statusMap['BaseInfo'] eq 'Y'}"><a class="after">本页信息已确认</a></c:if></span>
                    </c:if>
                    <%--<span style="float:right;margin-right:${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'?30:118}px;line-height:27px;color:blue;cursor:pointer;" onclick="baseCheck()">查看</span>--%>
                    <%--</th></tr></table>--%>
                    <div class="spreadOneOne">
                        <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                            <tr>
                                <th style="width:20%;"><span style="color: red;">*</span>&#12288;<span >学号：</span></th>
                                <td style="width:30%;">
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster && !isFeeMaster }">
                                        <input type="text" style="width: 140px;text-align: left;" class="inputText validate[required] notBlank" name="eduUser.sid" id="sid" value="${eduUser.sid}"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${eduUser.sid}</label>
                                    </c:if>
                                </td><th style="width:20%;"><span >证件类型：</span></th>
                                <td style="width:30%;">
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <select id="cretTypeId" style="width: 144px;" name="sysUser.cretTypeId" class="inputText" onchange="loadIdCheck();">
                                            <option/>
                                            <c:forEach items="${certificateTypeEnumList}" var="cretType">
                                                <option value="${cretType.id}" ${sysUser.cretTypeId eq cretType.id?'selected':''}>${cretType.name}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${sysUser.cretTypeName }</label>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th><span >姓名：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <input type="text" class="inputText" style="width: 140px;text-align: left;" name="sysUser.userName" value="${sysUser.userName }"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${sysUser.userName }</label>
                                    </c:if>
                                </td>
                                <th><span >证件号码：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <input type="text" id="idNo" class="inputText" style="width: 143px;text-align: left;" name="sysUser.idNo" value="${sysUser.idNo }"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${sysUser.idNo }</label>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th><span >性别：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <select style="width: 144px;" name="sysUser.sexId" class="inputText">
                                            <option></option>
                                            <option value="${userSexEnumMan.id}" ${sysUser.sexId eq userSexEnumMan.id?'selected':''}>${userSexEnumMan.name}</option>
                                            <option value="${userSexEnumWoman.id}" ${sysUser.sexId eq userSexEnumWoman.id?'selected':''}>${userSexEnumWoman.name}</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${sysUser.sexName }</label>
                                    </c:if>
                                </td>
                                <th><span >民族：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <select style="width: 144px;" name="sysUser.nationId" class="inputText">
                                            <option/>
                                            <c:forEach items="${userNationEnumList}" var="nation">
                                                <option value="${nation.id}" ${sysUser.nationId eq nation.id?'selected':''}>${nation.name}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${sysUser.nationName}</label>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th><span >出生日期：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="sysUser.userBirthday" value="${sysUser.userBirthday }"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${sysUser.userBirthday }</label>
                                    </c:if>
                                </td>
                                <th><span style="color: red;">*</span>&#12288;<span >政治面貌：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <select style="width: 144px;" name="sysUser.politicsStatusId" class="notBlank inputText validate[required] politics">
                                            <option/>
                                            <c:forEach items="${politicsAppearanceEnumList}" var="politics">
                                                <option value="${politics.id}" ${sysUser.politicsStatusId eq politics.id?'selected':''}>${politics.name}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <c:if test="${requiredFlag}">
                                            <select style="width: 144px;" name="sysUser.politicsStatusId" class="notBlank inputText validate[required] politics">
                                                <option/>
                                                <c:forEach items="${politicsAppearanceEnumList}" var="politics">
                                                    <option value="${politics.id}" ${sysUser.politicsStatusId eq politics.id?'selected':''}>${politics.name}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${!requiredFlag}">
                                            ${sysUser.politicsStatusName}
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th><span style="color: red;">*</span>&#12288;<span >工作单位：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <input type="text" class="notBlank inputText validate[required]" style="width:140px;text-align: left;" name="eduUserExtInfoForm.workUnitTDXL" value="${extInfoForm.workUnitTDXL }"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <c:if test="${requiredFlag}">
                                            <input type="text" class="notBlank inputText validate[required]" style="width:140px;text-align: left;" name="eduUserExtInfoForm.workUnitTDXL" value="${extInfoForm.workUnitTDXL }"/>
                                        </c:if>
                                        <c:if test="${!requiredFlag}">
                                            <label>${extInfoForm.workUnitTDXL }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.workUnitTDXL" value="${extInfoForm.workUnitTDXL}">
                                        </c:if>
                                    </c:if>
                                </td>
                                <th><span >专业：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <c:if test="${importModifyFlag}">
                                            <select style="width: 144px;" id="majorId" name="eduUser.majorId" class="notBlank inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyMajorList}" var="major">
                                                    <option value="${major.dictId}" ${eduUser.majorId eq major.dictId?'selected':''}>
                                                        [${major.dictId}]${major.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${!importModifyFlag}">
                                            [${eduUser.majorId}]${eduUser.majorName}
                                            <input type="hidden" name="eduUser.majorId" value="${eduUser.majorId}" />
                                        </c:if>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>[${eduUser.majorId}]${eduUser.majorName}</label>
                                        <input type="hidden" name="eduUser.majorId" value="${eduUser.majorId}" />
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th><span >报班时间：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <input type="text" class="notBlank inputText validate[required]" style="width:140px;text-align: left;" name="eduUserExtInfoForm.reportTime" value="${extInfoForm.reportTime }"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        ${extInfoForm.reportTime }
                                    </c:if>
                                </td>
                                <th><span style="color: red;">*</span>&#12288;<span >联系电话：</span></th>
                                <td>
                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" placeholder="不清楚请填无" name="sysUser.userPhone" value="${sysUser.userPhone }">
                                </td>
                            </tr>
                            <tr>
                                <th><span style="color: red;">*</span>&#12288;<span >地址：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <input type="text" style="width: 270px;text-align: left;" class="notBlank inputText validate[required]" name="eduUserExtInfoForm.addressTDXL" value="${extInfoForm.addressTDXL }">
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <c:if test="${requiredFlag}">
                                            <input type="text" style="width: 270px;text-align: left;" class="notBlank inputText validate[required]" name="eduUserExtInfoForm.addressTDXL" value="${extInfoForm.addressTDXL }">
                                        </c:if>
                                        <c:if test="${!requiredFlag}">
                                            <label>${extInfoForm.addressTDXL }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.addressTDXL" value="${extInfoForm.addressTDXL}">
                                        </c:if>
                                    </c:if>
                                </td>
                                <th><span style="color: red;">*</span>&#12288;<span >获得学位时间：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.degreeTimeTDXL" value="${extInfoForm.degreeTimeTDXL }"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <c:if test="${degreeGrantFlag}">
                                            <input type="text" class="notBlank inputText validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.degreeTimeTDXL" value="${extInfoForm.degreeTimeTDXL }"/>
                                        </c:if>
                                        <c:if test="${!degreeGrantFlag}">
                                            <label>${extInfoForm.degreeTimeTDXL }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.degreeTimeTDXL" value="${extInfoForm.degreeTimeTDXL}">
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th><span style="color: red;">*</span>&#12288;<span >毕业学校：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.graduateSchooTDXL" value="${extInfoForm.graduateSchooTDXL }"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <c:if test="${degreeGrantFlag}">
                                            <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.graduateSchooTDXL" value="${extInfoForm.graduateSchooTDXL }"/>
                                        </c:if>
                                        <c:if test="${!degreeGrantFlag}">
                                            <label>${extInfoForm.graduateSchooTDXL }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.graduateSchooTDXL" value="${extInfoForm.graduateSchooTDXL}">
                                        </c:if>
                                    </c:if>
                                </td>
                                <th><span >导师姓名：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <%--<input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUser.firstTeacher" value="${eduUser.firstTeacher }"/>--%>
                                        <input id="param_firstTeacher"  placeholder="输入导师姓名" class="inputText"  style="width:141px;text-align: left;" onkeydown="adjustResults1();" onfocus="adjustResults1();" value="${eduUser.firstTeacher}"/>
                                        <div id="suggest_firstTeacher" class="ac_results" style="margin:0;position: fixed; z-index: 100;width:140px;"></div>
                                        <input type="hidden" id="firstTeacherFlow" name="eduUser.firstTeacherFlow" value="${eduUser.firstTeacherFlow}"/>
                                        <input type="hidden" id="firstTeacher" name="eduUser.firstTeacher" value="${eduUser.firstTeacher}"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${eduUser.firstTeacher }</label>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th><span >导师单位：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster && !isFeeMaster }">
                                        <input type="text" class="inputText" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.tutorUnit" value="${extInfoForm.tutorUnit }"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${extInfoForm.tutorUnit }</label>
                                    </c:if>
                                </td>
                                <th><span >导师联系方式：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <input type="text" class="inputText" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.tutorContact" value="${extInfoForm.tutorContact }"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${extInfoForm.tutorContact}</label>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th><span >入学年级：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" class="notBlank inputText ctime" style="width: 140px;text-align: left;" name="eduUser.period" id="period" value="${eduUser.period }"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        ${eduUser.period }
                                    </c:if>
                                </td>
                                <th><span >学习形式：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <select style="width: 144px;" name="eduUser.studyFormId" class="inputText">
                                            <option/>
                                            <c:forEach items="${dictTypeEnumGyStudyFormList}" var="studyForm">
                                                <option value="${studyForm.dictId}" ${eduUser.studyFormId eq studyForm.dictId?'selected':''}>${studyForm.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${eduUser.studyFormName}</label>
                                    </c:if>
                                </td>
                            </tr>
                            <%--<tr>--%>
                                <%--<th><span >入学年级：</span></th>--%>
                                <%--<td>--%>
                                    <%--<c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">--%>
                                        <%--<input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" class="notBlank inputText ctime" style="width: 140px;text-align: left;" name="eduUser.period" id="period" value="${eduUser.period }"/>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">--%>
                                        <%--${eduUser.period }--%>
                                    <%--</c:if>--%>
                                <%--</td>--%>
                                <%--<th></th>--%>
                                <%--<td></td>--%>
                            <%--</tr>--%>
                            <tr>
                                <th><span >学位级别：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <select style="width: 144px;" name="eduUser.degreeType" class="inputText">
                                            <option/>
                                            <option value="Master" ${eduUser.degreeType eq 'Master'?'selected':''}>硕士学位</option>
                                            <option value="Doctor" ${eduUser.degreeType eq 'Doctor'?'selected':''}>博士学位</option>
                                        </select>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${eduUser.degreeType eq 'Master'?'硕士学位':''}${eduUser.degreeType eq 'Doctor'?'博士学位':''}</label>
                                    </c:if>
                                </td>
                                <th></th>
                                <td></td>
                            </tr>
                            <tr>
                                <th><span >学位类型：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <select style="width: 144px;" name="eduUser.trainCategoryId" class="inputText" id="degreeLevelFlag">
                                            <option/>
                                            <c:forEach items="${dictTypeEnumGyTrainCategoryList}" var="train">
                                                <option value="${train.dictId}" ${eduUser.trainCategoryId eq train.dictId?'selected':''}>${train.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${eduUser.trainCategoryName}</label>
                                    </c:if>
                                </td>
                                <th id="degreeLevelFlagTh"><span >学位类别：</span></th>
                                <td id="degreeLevelFlagTd">
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <select style="width: 144px;" name="eduUser.degreeLevelId" class="inputText"  id="degreeLevelSelect">
                                            <option/>
                                            <c:forEach items="${dictTypeEnumGyXjDegreeLevelList}" var="level">
                                                <option value="${level.dictId}" ${eduUser.degreeLevelId eq level.dictId?'selected':''} type="${level.dictId lt 5?'Level1':'Level2'}${level.dictId}">${level.dictName}</option>
                                            </c:forEach>
                                        </select><input type="hidden" name="eduUser.degreeLevelName" value="${eduUser.degreeLevelName}">
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <label>${eduUser.degreeLevelName}</label>
                                    </c:if>
                                </td>
                                <th id="degreeLevelFlagTh1"></th>
                                <td id="degreeLevelFlagTd1"></td>
                            </tr>
                            <tr>
                                <th><span >授予学位证书编号：</span></th>
                                <td>
                                    <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                        <input type="text" class="inputText" style="text-align: left;width: 140px;" name="eduUser.awardDegreeCertCode" value="${eduUser.awardDegreeCertCode }"/>
                                    </c:if>
                                    <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                        <c:if test="${degreeGrantFlag}">
                                            <input type="text" class="notBlank inputText validate[required]" style="text-align: left;width: 140px;" name="eduUser.awardDegreeCertCode" value="${eduUser.awardDegreeCertCode }"/>
                                        </c:if>
                                        <c:if test="${!degreeGrantFlag}">
                                            ${eduUser.awardDegreeCertCode }
                                        </c:if>
                                    </c:if>
                                </td>
                                <th></th>
                                <td></td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>