<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
    </jsp:include>
    <style type="text/css">
        input[type='text']{width:100px;border-width:0px;}
        input[type='text']{border-bottom:1px solid #e3e3e3;}
    </style>
    <script type="text/javascript" src="<s:url value='/js/jquery.jqprint-0.3.js'/>"></script>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        function search(){
            $("#searchForm").submit();
        }
        function save(){
            if (!$("#saveForm").validationEngine("validate")) {
                return;
            }
            var nativePlaceProvNameText = $("#nativePlaceProvinceId option:selected").text();
            if (nativePlaceProvNameText == "选择省") {
                nativePlaceProvNameText = "";
            }
            var nativePlaceCityNameText = $("#nativePlaceCityId option:selected").text();
            if (nativePlaceCityNameText == "选择市") {
                nativePlaceCityNameText = "";
            }
            var nativePlaceAreaNameText = $("#nativePlaceAreaId option:selected").text();
            if (nativePlaceAreaNameText == "选择地区") {
                nativePlaceAreaNameText = "";
            }
            $("#nativePlaceProvince").val(nativePlaceProvNameText);
            $("#nativePlaceCity").val(nativePlaceCityNameText);
            $("#nativePlaceArea").val(nativePlaceAreaNameText);
            var birthPlaceProvNameText = $("#birthPlaceProvinceId option:selected").text();
            if (birthPlaceProvNameText == "选择省") {
                birthPlaceProvNameText = "";
            }
            var birthPlaceCityNameText = $("#birthPlaceCityId option:selected").text();
            if (birthPlaceCityNameText == "选择市") {
                birthPlaceCityNameText = "";
            }
            var birthPlaceAreaNameText = $("#birthPlaceAreaId option:selected").text();
            if (birthPlaceAreaNameText == "选择地区") {
                birthPlaceAreaNameText = "";
            }
            $("#birthPlaceProvince").val(birthPlaceProvNameText);
            $("#birthPlaceCity").val(birthPlaceCityNameText);
            $("#birthPlaceArea").val(birthPlaceAreaNameText);
            $("#studentSourceAreaId").val($("#studentSourceArea").attr("flow"));
            <c:if test="${roleFlag ne 'global'}">
                var count = 0;
                $(".eduDate").each(function(i){
                    if($.trim($(this).val()) != "" && $.trim($(".eduUnit").val()) != "" && $.trim($(".eduPost").val()) != "" && $.trim($(".eduAddress").val()) != ""){
                        count ++;
                    }
                });
                if(count == 0){
                    jboxTip("学历及经历一栏至少填写一条完整信息");
                    return;
                }
                count = 0;
                $(".famName").each(function(i){
                    if($.trim($(this).val()) != "" && $.trim($(".famAge").val()) != "" && $.trim($(".famRelation").val()) != "" && $.trim($(".famPolitics").val()) != ""
                            && $.trim($(".famUnit").val()) != "" && $.trim($(".famPost").val()) != "" && $.trim($(".famAddress").val()) != "" && $.trim($(".famPhone").val()) != ""){
                        count ++;
                    }
                });
                if(count == 0){
                    jboxTip("家庭成员一栏至少填写一条完整信息");
                    return;
                }
            </c:if>
            jboxPost("<s:url value='/gyxjgl/user/saveSchoolRegister'/>", $("#saveForm").serialize(), function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    location.reload();
                    jboxClose();
                }
            });
        }
        function uploadImage() {
            $.ajaxFileUpload({
                url: "<s:url value='/gyxjgl/user/uploadImage'/>",
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
        <%--function printOpt(){--%>
            <%--var url = "<s:url value='/gyxjgl/user/printXjTbl?userFlow=${param.userFlow}'/>";--%>
            <%--jboxTip("正在准备打印…");--%>
            <%--document.getElementById('printDivIframe').src=url;--%>
        <%--}--%>
        function printOpt(){
            var url = "<s:url value='/gyxjgl/user/printXjTbl'/>";
            jboxStartLoading();
            jboxPost(url, $("#searchForm").serialize(), function(data) {
                $("#printDivIframe").html(data);
                $("#printDivIframe").removeAttr("hidden");
                setTimeout(function(){
                    $("#printDivIframe").jqprint({
                        debug: false,
                        importCSS: true,
                        printContainer: true,
                        operaSupport: false
                    });
                    $("#printDivIframe").attr("hidden","hidden");
                    jboxEndLoading();
                    return false;
                },2000);
            },null,false);
        }
        function expOpt(){
            var url = "<s:url value='/gyxjgl/user/exportXjTbl'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }

        /**
         *模糊查询加载
         */
        (function($){
            $.fn.likeSearchInit = function(option){
                option.clickActive = option.clickActive || null;
                var searchInput = this;
                searchInput.on("keyup focus",function(){
                    $("#boxHome").show();
                    if($.trim(this.value)){
                        $("#boxHome .item").hide();
                        var items = $("#boxHome .item[value*='"+this.value+"']").show();
                        if(!items){
                            $("#boxHome").hide();
                        }
                    }else{
                        $("#boxHome .item").show();
                    }
                });
                searchInput.on("blur",function(){
                    if(!$("#boxHome.on").length){
                        $("#boxHome").hide();
                    }
                });
                $("#boxHome").on("mouseenter mouseleave",function(){
                    $(this).toggleClass("on");
                });
                $("#boxHome .item").click(function(){
                    $("#boxHome").hide();
                    var value = $(this).attr("value");
                    $("#itemName").val(value);
                    searchInput.val(value);
                    searchInput.attr("flow",$(this).attr("flow"));
                    if(option.clickActive){
                        option['clickActive']($(this).attr("flow"));
                    }
                });
            };
        })(jQuery);
        $(function(){
            var degreeType=$("input[name='degreeType']:checked").val();
            console.log(degreeType);
            if(degreeType==undefined||degreeType=='undefined'){
                degreeType=$("input[name='degreeType']").val();
            }
            console.log(degreeType);
            if("major"==degreeType){//专业学位
                $("option[type='学术']").hide();
            }
            if("academic"==degreeType){//学术学位
                $("option[type='专业']").hide();
            }
            $("#studentSourceArea").likeSearchInit({});
        });
        function changeDegreeType(){
            var degreeType=$("input[name='degreeType']:checked").val();
            if("major"==degreeType){//专业学位
                $("option[type='学术']").hide();
                $("option[type='专业']").show();
            }
            if("academic"==degreeType){//学术学位
                $("option[type='学术']").show();
                $("option[type='专业']").hide();
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <!-- 针对特定学员 开放 学籍登记信息修改 -->
        <c:set var="registerN" value="${permissionCfg.cfgValue eq 'N'&& eduUser.isMdfInfo eq 'Y' && roleFlag eq 'doctor' && pdfn:getCurrDate() ge openRegisterStartDate.cfgValue && pdfn:getCurrDate() le closeRegisterEndDate.cfgValue}"/>
        <!-- 针对所有学员 开放 学籍登记信息修改 -->
        <c:set var="registerY" value="${permissionCfg.cfgValue eq 'Y' && roleFlag eq 'doctor' && pdfn:getCurrDate() ge openRegisterStartDate.cfgValue && pdfn:getCurrDate() le closeRegisterEndDate.cfgValue}"/>
        <c:if test="${registerN or registerY}">
            <c:set var="registerFlag" value="true"/>
        </c:if>
        <!-- 学校以及有修改权限的学员 -->
        <c:if test="${roleFlag eq 'global' || (roleFlag eq 'doctor' && registerFlag)}">
            <form id="searchForm" action="<s:url value="/gyxjgl/user/schoolRegister"/>" method="post">
                <input type="hidden" name="roleFlag" value="${roleFlag}">
                <input type="hidden" name="userFlow" value="${param.userFlow}">
                <div style="margin:20px 0px 10px 40px;">
                    学历类型：
                    <select style="width:137px;" name="educationType" onchange="search()">
                        <option value="master" ${param.educationType eq 'master'?'selected':''}>硕士研究生</option>
                        <option value="doctor" ${param.educationType eq 'doctor'|| (empty param.educationType && resume.educationType eq 'doctor')?'selected':''}>博士研究生</option>
                    </select>
                    <input type="button" class="search" onclick="save();" value="保&#12288;存" />
                    <input type="button" class="search" onclick="printOpt();" value="打&#12288;印" />
                    <input type="button" class="search" onclick="expOpt();" value="导&#12288;出" />
                </div>
            </form>
            <form id="saveForm">
                <input type="hidden" name="roleFlag" value="${roleFlag}">
                <input type="hidden" name="userFlow" value="${param.userFlow}">
                <input type="hidden" name="educationType" value="${empty param.educationType?resume.educationType:param.educationType}">
                <div style="text-align:center;font-size:30px;font-weight:500;">
                    广州医科大学攻读<c:if test="${param.educationType eq 'master'}">硕士</c:if><c:if test="${param.educationType eq 'doctor'}">博士</c:if>
                    <c:if test="${empty param.educationType}">${resume.educationType eq 'doctor'?'博士':'硕士'}</c:if>研究生学籍登记表
                </div>
                <div><table style="width:100%;margin:30px 0px 10px 0px;"><tr>
                    <c:if test="${param.educationType eq 'master'}">
                        <td style="width:25%;">&#12288;&#12288;导师单位：
                            <select class="${roleFlag eq 'global'?'':'validate[required]'} inputText" style="width:100px;" name="tutorUnit">
                                <option value=""></option>
                                <c:forEach items="${applicationScope.sysOrgList}" var="tu">
                                    <c:if test="${tu.isSecondFlag eq 'Y'}">
                                        <option value="${tu.orgName}" ${resume.tutorUnit eq tu.orgName?'selected':''}>${tu.orgName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="width:25%;">学号：<input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="studentId" value="${resume.studentId}"/></td>
                        <td style="width:25%;">准考证号：<input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="ticketNumber" value="${resume.ticketNumber}"/></td>
                        <td style="width:25%;">学位类型：<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="degreeType" value="major" ${resume.degreeType eq 'major'?'checked':''} onchange="changeDegreeType();">专业学位&#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="degreeType" value="academic" ${resume.degreeType eq 'academic'?'checked':''} onchange="changeDegreeType();">学术学位</td>
                    </c:if>
                    <c:if test="${param.educationType eq 'doctor'}">
                        <td style="width:33%;">&#12288;&#12288;导师单位：
                            <select class="${roleFlag eq 'global'?'':'validate[required]'} inputText" style="width:100px;" name="tutorUnit">
                                <option value=""></option>
                                <c:forEach items="${applicationScope.sysOrgList}" var="tu">
                                    <c:if test="${tu.isSecondFlag eq 'Y'}">
                                        <option value="${tu.orgName}" ${resume.tutorUnit eq tu.orgName?'selected':''}>${tu.orgName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="width:33%;">学号：<input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="studentId" value="${resume.studentId}"/></td>
                        <td style="width:33%;">学位类型：学术学位<input type="hidden" name="degreeType" value="academic"></td>
                    </c:if>
                    <c:if test="${empty param.educationType && (resume.educationType eq 'master' || empty resume.educationType)}">
                        <td style="width:25%;">&#12288;&#12288;导师单位：
                            <select class="${roleFlag eq 'global'?'':'validate[required]'} inputText" style="width:100px;" name="tutorUnit">
                                <option value=""></option>
                                <c:forEach items="${applicationScope.sysOrgList}" var="tu">
                                    <c:if test="${tu.isSecondFlag eq 'Y'}">
                                        <option value="${tu.orgName}" ${resume.tutorUnit eq tu.orgName?'selected':''}>${tu.orgName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="width:25%;">学号：<input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="studentId" value="${resume.studentId}"/></td>
                        <td style="width:25%;">准考证号：<input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="ticketNumber" value="${resume.ticketNumber}"/></td>
                        <td style="width:25%;">学位类型：<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="degreeType" value="major" ${resume.degreeType eq 'major'?'checked':''} onchange="changeDegreeType();">专业学位&#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="degreeType" value="academic" ${resume.degreeType eq 'academic'?'checked':''} onchange="changeDegreeType();">学术学位</td>
                    </c:if>
                    <c:if test="${empty param.educationType && resume.educationType eq 'doctor'}">
                        <td style="width:33%;">&#12288;&#12288;导师单位：
                            <select class="${roleFlag eq 'global'?'':'validate[required]'} inputText" style="width:100px;" name="tutorUnit">
                                <option value=""></option>
                                <c:forEach items="${applicationScope.sysOrgList}" var="tu">
                                    <c:if test="${tu.isSecondFlag eq 'Y'}">
                                        <option value="${tu.orgName}" ${resume.tutorUnit eq tu.orgName?'selected':''}>${tu.orgName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="width:33%;">学号：<input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="studentId" value="${resume.studentId}"/></td>
                        <td style="width:33%;">学位类型：学术学位<input type="hidden" name="degreeType" value="academic"></td>
                    </c:if>
                </tr></table></div>
                <div style="min-width:1340px;">
                    <table class="basic" style="width:100%;">
                        <tr>
                            <th style="min-width:80px;">姓名</th>
                            <td style="min-width:115px;"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="userName" value="${resume.userName}"/></td>
                            <th style="min-width:80px;">姓名拼音</th>
                            <td style="min-width:115px;"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="nameSpell" value="${resume.nameSpell}"/></td>
                            <th style="min-width:80px;">性别</th>
                            <td style="min-width:115px;"><input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="sexName" value="Man" ${resume.sexName eq 'Man'?'checked':''}>男&#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="sexName" value="Woman" ${resume.sexName eq 'Woman'?'checked':''}>女</td>
                            <th style="min-width:80px;">出生日期</th>
                            <td style="min-width:115px;"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'} inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px;text-align:left;" name="birthDate" value="${resume.birthDate}"/></td>
                            <th style="min-width:80px;">民族</th>
                            <td style="min-width:115px;">
                                <select class="${roleFlag eq 'global'?'':'validate[required]'} inputText" style="width: 100px;" name="nation">
                                    <option value=""></option>
                                    <c:forEach items="${userNationEnumList}" var="na">
                                        <option value="${na.name}" ${resume.nation eq na.name?'selected':''}>${na.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th style="min-width:80px;">婚否</th>
                            <td style="min-width:115px;"><input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="marriage" value="Y" ${resume.marriage eq 'Y'?'checked':''}>是&#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="marriage" value="N" ${resume.marriage eq 'N'?'checked':''}>否</td>
                            <td rowspan="4" style="width:140px;text-align:center;">
                                <img src="${sysCfgMap['upload_base_url']}/${resume.headImgUrl}" id="showImg" width="100px;" height="130px;" onerror="this.src='<s:url value="/jsp/gyxjgl/images/up-pic.jpg"/>'"/>
                                <div>[<a onclick="$('#imageFile').click();" style="cursor: pointer;">${empty resume.headImgUrl?'上传照片':'重新上传'}</a>]</div>
                                <input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
                                <input id="img" type="hidden" name="headImgUrl" value="${resume.headImgUrl}">
                            </td>
                        </tr>
                        <tr>
                            <th>导师</th>
                            <td><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="tutor" value="${empty resume.tutor?eduUser.firstTeacher:resume.tutor}"/></td>
                            <th>专业</th>
                            <td>
                                <select class="${roleFlag eq 'global'?'':'validate[required]'} inputText" style="width:100px;" name="speName">
                                    <option value=""></option>
                                    <c:forEach items="${dictTypeEnumGyMajorList}" var="spe">
                                        <option value="${spe.dictName}" ${resume.speName eq spe.dictName?'selected':''} type="${spe.dictDesc}">${spe.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>年级</th>
                            <td><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="grade" value="${resume.grade}"/></td>
                            <th>户口是否<br/>转入学校</th>
                            <td><input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="hkInSchool" value="Y" ${resume.hkInSchool eq 'Y'?'checked':''}>是&#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="hkInSchool" value="N" ${resume.hkInSchool eq 'N'?'checked':''}>否</td>
                            <th>户口类型</th>
                            <td colspan="3"><input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="hkType" value="N" ${resume.hkType eq 'N'?'checked':''} />非农业&#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="hkType" value="Y" ${resume.hkType eq 'Y'?'checked':''} />农业</td>
                        </tr>
                        <tr>
                            <th>籍贯</th>
                            <td colspan="3">
                                <div id="native">
                                    <select id="nativePlaceProvinceId" name="nativePlaceProvinceId" style="width: 75px;" class="notBlank province inputText ${roleFlag eq 'global'?'':'validate[required]'}" data-value="${resume.nativePlaceProvinceId}" data-first-title="选择省"></select>
                                    <select id="nativePlaceCityId" name="nativePlaceCityId" style="width: 75px;" class="notBlank city inputText ${roleFlag eq 'global'?'':'validate[required]'}" data-value="${resume.nativePlaceCityId}" data-first-title="选择市"></select>
                                    <select id="nativePlaceAreaId" name="nativePlaceAreaId" style="width: 75px;" class="notBlank area inputText ${roleFlag eq 'global'?'':'validate[required]'}" data-value="${resume.nativePlaceAreaId}" data-first-title="选择地区"></select>
                                    <%--<input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="nativePlaceCountry" style="width:60px;text-align:center;" value="${resume.nativePlaceCountry}"/>乡--%>
                                </div>
                                <input id="nativePlaceProvince" name="nativePlaceProvince" type="hidden" value="${resume.nativePlaceProvince}">
                                <input id="nativePlaceCity" name="nativePlaceCity" type="hidden" value="${resume.nativePlaceCity}">
                                <input id="nativePlaceArea" name="nativePlaceArea" type="hidden" value="${resume.nativePlaceArea}">
                                <script type="text/javascript">
                                    // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                                    $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                                    $.cxSelect.defaults.nodata = "none";

                                    $("#native").cxSelect({
                                        selects: ["province", "city", "area"],
                                        nodata: "none",
                                        firstValue: ""
                                    });
                                </script>
                            </td>
                            <th>是否应届</th>
                            <td><input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="whetherCurrent" value="Y" ${resume.whetherCurrent eq 'Y'?'checked':''}>是&#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="whetherCurrent" value="N" ${resume.whetherCurrent eq 'N'?'checked':''}>否</td>
                            <th>政治面貌</th>
                            <td>
                                <select class="${roleFlag eq 'global'?'':'validate[required]'} inputText" style="width:100px;" name="politicalOutlook">
                                    <option value=""></option>
                                    <c:forEach items="${politicsAppearanceEnumList}" var="politics">
                                        <option value="${politics.name}" ${resume.politicalOutlook eq politics.name?'selected':''}>${politics.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th>身份证号</th>
                            <td colspan="3"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="idNumber" style="width:180px;" value="${resume.idNumber}"/></td>
                        </tr>
                        <tr>
                            <th>生源地</th>
                            <td colspan="3">
                                <input id="studentSourceArea" type="text" name="studentSourceArea" value="${resume.studentSourceArea}" autocomplete="off" title="${resume.studentSourceArea}" flow="${resume.studentSourceAreaId}" style="width:210px;" placeholder="关键字检索，单击选项选择信息"/>&#12288;
                                <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:31px;">
                                    <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                                        <c:forEach items="${dictTypeEnumGyXjStudentSourceAreaList}" var="dict">
                                            <p class="item" flow="${dict.dictId}" value="${dict.dictName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${dict.dictName}</p>
                                        </c:forEach>
                                    </div>
                                </div>
                                <input type="hidden" id="studentSourceAreaId" name="studentSourceAreaId" value="${resume.studentSourceAreaId }"/>
                                <%--<div id="birthPlace">--%>
                                    <%--<select id="birthPlaceProvinceId" name="birthPlaceProvinceId" style="width: 75px;" class="notBlank province inputText ${roleFlag eq 'global'?'':'validate[required]'}" data-value="${resume.birthPlaceProvinceId}" data-first-title="选择省"></select>--%>
                                    <%--<select id="birthPlaceCityId" name="birthPlacePrefectureLevelCityId" style="width: 75px;" class="notBlank city inputText ${roleFlag eq 'global'?'':'validate[required]'}" data-value="${resume.birthPlacePrefectureLevelCityId}" data-first-title="选择市"></select>--%>
                                    <%--<select id="birthPlaceAreaId" name="birthPlaceCountyLevelCityId" style="width: 75px;" class="notBlank area inputText ${roleFlag eq 'global'?'':'validate[required]'}" data-value="${resume.birthPlaceCountyLevelCityId}" data-first-title="选择地区"></select>--%>
                                <%--</div>--%>
                                <%--<input id="birthPlaceProvince" name="birthPlaceProvince" type="hidden" value="${resume.birthPlaceProvince}">--%>
                                <%--<input id="birthPlaceCity" name="birthPlacePrefectureLevelCity" type="hidden" value="${resume.birthPlacePrefectureLevelCity}">--%>
                                <%--<input id="birthPlaceArea" name="birthPlaceCountyLevelCity" type="hidden" value="${resume.birthPlaceCountyLevelCity}">--%>
                                <%--<script type="text/javascript">--%>
                                    <%--// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件--%>
                                    <%--$.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";--%>
                                    <%--$.cxSelect.defaults.nodata = "none";--%>

                                    <%--$("#birthPlace").cxSelect({--%>
                                        <%--selects: ["province", "city", "area"],--%>
                                        <%--nodata: "none",--%>
                                        <%--firstValue: ""--%>
                                    <%--});--%>
                                <%--</script>--%>
                            </td>
                            <th>出生地</th>
                            <td><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="homePlace" value="${resume.homePlace}"/></td>
                            <th>入学时间</th>
                            <td><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'} inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px;text-align:left;" name="entranceTime" value="${resume.entranceTime}"/></td></td>
                            <th>手机</th>
                            <td colspan="3"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="mobilePhone" style="width:180px;" value="${resume.mobilePhone}"/></td>
                        </tr>
                        <tr>
                            <th>家庭通讯<br/>地址</th>
                            <td colspan="5"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="homeAddress" style="width:360px;" value="${resume.homeAddress}"/></td>
                            <th>邮政编码</th>
                            <td><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="postCode" value="${resume.postCode}"/></td>
                            <th>家庭电话</th>
                            <td colspan="4"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="homePhone" style="width:360px;" value="${resume.homePhone}"/></td>
                        </tr>
                        <tr>
                            <th>培养方式</th>
                            <td colspan="3"><input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="cultureType" value="Y" ${resume.cultureType eq 'Y'?'checked':''}>定向&#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="cultureType" value="N" ${resume.cultureType eq 'N'?'checked':''}>非定向</td>
                            <th>是否华侨<br/>港澳台学生</th>
                            <td colspan="3"><input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="overseasChinese" value="Y" ${resume.overseasChinese eq 'Y'?'checked':''}>是&#12288;<input type="radio" class="${roleFlag eq 'global'?'':'validate[required]'}" name="overseasChinese" value="N" ${resume.overseasChinese eq 'N'?'checked':''}>否</td>
                            <th>特长</th>
                            <td colspan="4"><input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="specialty" style="width:360px;" value="${resume.specialty}"/></td>
                        </tr>
                        <tr>
                            <th>最后学历</th>
                            <td colspan="12">
                                <input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="finalEducationYear" style="text-align:center;" value="${resume.finalEducationYear}"/>年毕业于
                                <input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="finalEducationSchool" style="text-align:center;" value="${resume.finalEducationSchool}"/>大学
                                <input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="finalEducationCollege" style="text-align:center;" value="${resume.finalEducationCollege}"/>学院
                                <input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="finalEducationDepartment" style="text-align:center;" value="${resume.finalEducationDepartment}"/>系
                                <input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="finalEducationSpe" style="text-align:center;" value="${resume.finalEducationSpe}"/>专业（修学年限
                                <input type="text" class="${roleFlag eq 'global'?'':'validate[required]'}" name="finalEducationDropOutYear" style="text-align:center;" value="${resume.finalEducationDropOutYear}"/>年）
                            </td>
                        </tr>
                        <tr>
                            <th rowspan="6">学历及经历<br/>（从初中到现在）</th>
                            <th style="text-align:center;" colspan="2">起止年月</th>
                            <th style="text-align:center;" colspan="4">学校或单位</th>
                            <th style="text-align:center;" colspan="2">职务</th>
                            <th style="text-align:center;" colspan="4">单位地址</th>
                        </tr>
                        <c:set var="educationCount" value="0"/>
                        <c:forEach items="${resume.educationList}" var="edu" varStatus="i">
                            <c:set var="educationCount" value="${educationCount+1}" />
                            <tr>
                                <td colspan="2"><input type="text" class="eduDate" name="educationList[${i.index}].beginAndEndDate" style="width:180px;" value="${edu.beginAndEndDate}"/></td>
                                <td colspan="4"><input type="text" class="eduUnit" name="educationList[${i.index}].schoolAndUnit" style="width:360px;" value="${edu.schoolAndUnit}"/></td>
                                <td colspan="2"><input type="text" class="eduPost" name="educationList[${i.index}].postName" style="width:180px;" value="${edu.postName}"/></td>
                                <td colspan="4"><input type="text" class="eduAddress" name="educationList[${i.index}].unitAddress" style="width:360px;" value="${edu.unitAddress}"/></td>
                            </tr>
                        </c:forEach>
                        <c:forEach begin="${educationCount}" end="4" var="index">
                            <tr>
                                <td colspan="2"><input type="text" class="eduDate" name="educationList[${index}].beginAndEndDate" style="width:180px;"/></td>
                                <td colspan="4"><input type="text" class="eduUnit" name="educationList[${index}].schoolAndUnit" style="width:360px;"/></td>
                                <td colspan="2"><input type="text" class="eduPost" name="educationList[${index}].postName" style="width:180px;"/></td>
                                <td colspan="4"><input type="text" class="eduAddress" name="educationList[${index}].unitAddress" style="width:360px;"/></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <th rowspan="6">家庭成员</th>
                            <th style="text-align:center;">姓名</th>
                            <th style="text-align:center;">年龄</th>
                            <th style="text-align:center;">与本人关系</th>
                            <th style="text-align:center;">政治面貌</th>
                            <th style="text-align:center;" colspan="3">工作单位</th>
                            <th style="text-align:center;">职务</th>
                            <th style="text-align:center;" colspan="3">单位地址</th>
                            <th style="text-align:center;">手机电话</th>
                        </tr>
                        <c:set var="familyCount" value="0"/>
                        <c:forEach items="${resume.familyList}" var="fam" varStatus="i">
                            <c:set var="familyCount" value="${familyCount+1}" />
                            <tr>
                                <td><input type="text" class="famName" name="familyList[${i.index}].name" value="${fam.name}"/></td>
                                <td><input type="text" class="famAge" name="familyList[${i.index}].age" value="${fam.age}"/></td>
                                <td><input type="text" class="famRelation" name="familyList[${i.index}].relation" value="${fam.relation}"/></td>
                                <td><input type="text" class="famPolitics" name="familyList[${i.index}].politics" value="${fam.politics}"/></td>
                                <td colspan="3"><input type="text" class="famUnit" name="familyList[${i.index}].workUnit" style="width:220px;" value="${fam.workUnit}"/></td>
                                <td><input type="text" class="famPost" name="familyList[${i.index}].postName" value="${fam.postName}"/></td>
                                <td colspan="3"><input type="text" class="famAddress" name="familyList[${i.index}].unitAddress" style="width:220px;" value="${fam.unitAddress}"/></td>
                                <td><input type="text" class="famPhone" name="familyList[${i.index}].mobilePhone" value="${fam.mobilePhone}"/></td>
                            </tr>
                        </c:forEach>
                        <c:forEach begin="${familyCount}" end="4" var="index">
                            <tr>
                                <td><input type="text" class="famName" name="familyList[${index}].name"/></td>
                                <td><input type="text" class="famAge" name="familyList[${index}].age"/></td>
                                <td><input type="text" class="famRelation" name="familyList[${index}].relation"/></td>
                                <td><input type="text" class="famPolitics" name="familyList[${index}].politics"/></td>
                                <td colspan="3"><input type="text" class="famUnit" name="familyList[${index}].workUnit" style="width:220px;"/></td>
                                <td><input type="text" class="famPost" name="familyList[${index}].postName"/></td>
                                <td colspan="3"><input type="text" class="famAddress" name="familyList[${index}].unitAddress" style="width:220px;"/></td>
                                <td><input type="text" class="famPhone" name="familyList[${index}].mobilePhone"/></td>
                            </tr>
                        </c:forEach>
                        <c:if test="${roleFlag eq 'global'}">
                            <tr>
                                <th rowspan="3">学籍异动</th>
                                <th style="text-align:center;">年</th>
                                <th style="text-align:center;">月</th>
                                <th style="text-align:center;">日</th>
                                <th style="text-align:center;" colspan="2">异动</th>
                                <th style="text-align:center;">学年度</th>
                                <th style="text-align:center;" colspan="4">原因</th>
                                <th style="text-align:center;" colspan="2">备注</th>
                            </tr>
                            <c:set var="changeCount" value="0"/>
                            <c:forEach items="${resume.changeList}" var="change" varStatus="i">
                                <c:set var="changeCount" value="${changeCount+1}" />
                                <tr>
                                    <td><input type="text" name="changeList[${i.index}].year" value="${change.year}"/></td>
                                    <td><input type="text" name="changeList[${i.index}].month" value="${change.month}"/></td>
                                    <td><input type="text" name="changeList[${i.index}].day" value="${change.day}"/></td>
                                    <td colspan="2"><input type="text" name="changeList[${i.index}].changeItem" value="${change.changeItem}" style="width:180px;"/></td>
                                    <td><input type="text" name="changeList[${i.index}].termYear" value="${change.termYear}"/></td>
                                    <td colspan="4"><input type="text" name="changeList[${i.index}].reason" value="${change.reason}" style="width:360px;"/></td>
                                    <td colspan="2"><input type="text" name="changeList[${i.index}].memo" value="${change.memo}" style="width:180px;"/></td>
                                </tr>
                            </c:forEach>
                            <c:forEach begin="${changeCount}" end="1" var="index">
                                <tr>
                                    <td><input type="text" name="changeList[${index}].year"/></td>
                                    <td><input type="text" name="changeList[${index}].month"/></td>
                                    <td><input type="text" name="changeList[${index}].day"/></td>
                                    <td colspan="2"><input type="text" name="changeList[${index}].changeItem" style="width:180px;"/></td>
                                    <td><input type="text" name="changeList[${index}].termYear"/></td>
                                    <td colspan="4"><input type="text" name="changeList[${index}].reason" style="width:360px;"/></td>
                                    <td colspan="2"><input type="text" name="changeList[${index}].memo" style="width:180px;"/></td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th rowspan="4">奖惩情况</th>
                                <th style="text-align:center;" colspan="2">日期</th>
                                <th style="text-align:center;">级别</th>
                                <th style="text-align:center;" colspan="2">名称</th>
                                <th style="text-align:center;">单位</th>
                                <th style="text-align:center;" colspan="4">原因及说明</th>
                                <th style="text-align:center;" colspan="2">备注</th>
                            </tr>
                            <c:set var="bonusPenaltyCount" value="0"/>
                            <c:forEach items="${resume.bonusPenaltyList}" var="bonusPenalty" varStatus="i">
                                <c:set var="bonusPenaltyCount" value="${bonusPenaltyCount+1}" />
                                <tr>
                                    <td colspan="2"><input type="text" name="bonusPenaltyList[${i.index}].date" value="${bonusPenalty.date}" style="width:180px;"/></td>
                                    <td><input type="text" name="bonusPenaltyList[${i.index}].level" value="${bonusPenalty.level}"/></td>
                                    <td colspan="2"><input type="text" name="bonusPenaltyList[${i.index}].name" value="${bonusPenalty.name}" style="width:180px;"/></td>
                                    <td><input type="text" name="bonusPenaltyList[${i.index}].unit" value="${bonusPenalty.unit}"/></td>
                                    <td colspan="4"><input type="text" name="bonusPenaltyList[${i.index}].reason" value="${bonusPenalty.reason}" style="width:360px;"/></td>
                                    <td colspan="2"><input type="text" name="bonusPenaltyList[${i.index}].memo" value="${bonusPenalty.memo}" style="width:180px;"/></td>
                                </tr>
                            </c:forEach>
                            <c:forEach begin="${bonusPenaltyCount}" end="2" var="index">
                                <tr>
                                    <td colspan="2"><input type="text" name="bonusPenaltyList[${index}].date" style="width:180px;"/></td>
                                    <td><input type="text" name="bonusPenaltyList[${index}].level"/></td>
                                    <td colspan="2"><input type="text" name="bonusPenaltyList[${index}].name" style="width:180px;"/></td>
                                    <td><input type="text" name="bonusPenaltyList[${index}].unit"/></td>
                                    <td colspan="4"><input type="text" name="bonusPenaltyList[${index}].reason" style="width:360px;"/></td>
                                    <td colspan="2"><input type="text" name="bonusPenaltyList[${index}].memo" style="width:180px;"/></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <!-- 防止学员修改后将学校填写的异动和奖惩栏清空 -->
                        <c:if test="${roleFlag ne 'global'}">
                            <c:set var="changeCount" value="0"/>
                            <c:forEach items="${resume.changeList}" var="change" varStatus="i">
                                <c:set var="changeCount" value="${changeCount+1}" />
                                <input type="hidden" name="changeList[${i.index}].year" value="${change.year}"/>
                                <input type="hidden" name="changeList[${i.index}].month" value="${change.month}"/>
                                <input type="hidden" name="changeList[${i.index}].day" value="${change.day}"/>
                                <input type="hidden" name="changeList[${i.index}].changeItem" value="${change.changeItem}"/>
                                <input type="hidden" name="changeList[${i.index}].termYear" value="${change.termYear}"/>
                                <input type="hidden" name="changeList[${i.index}].reason" value="${change.reason}"/>
                                <input type="hidden" name="changeList[${i.index}].memo" value="${change.memo}"/>
                            </c:forEach>
                            <c:forEach begin="${changeCount}" end="1" var="index">
                                <input type="hidden" name="changeList[${index}].year"/></td>
                                <input type="hidden" name="changeList[${index}].month"/></td>
                                <input type="hidden" name="changeList[${index}].day"/></td>
                                <input type="hidden" name="changeList[${index}].changeItem"/></td>
                                <input type="hidden" name="changeList[${index}].termYear"/></td>
                                <input type="hidden" name="changeList[${index}].reason"/></td>
                                <input type="hidden" name="changeList[${index}].memo"/></td>
                            </c:forEach>
                            <c:set var="bonusPenaltyCount" value="0"/>
                            <c:forEach items="${resume.bonusPenaltyList}" var="bonusPenalty" varStatus="i">
                                <c:set var="bonusPenaltyCount" value="${bonusPenaltyCount+1}" />
                                <input type="hidden" name="bonusPenaltyList[${i.index}].date" value="${bonusPenalty.date}"/>
                                <input type="hidden" name="bonusPenaltyList[${i.index}].level" value="${bonusPenalty.level}"/>
                                <input type="hidden" name="bonusPenaltyList[${i.index}].name" value="${bonusPenalty.name}"/>
                                <input type="hidden" name="bonusPenaltyList[${i.index}].unit" value="${bonusPenalty.unit}"/>
                                <input type="hidden" name="bonusPenaltyList[${i.index}].reason" value="${bonusPenalty.reason}"/>
                                <input type="hidden" name="bonusPenaltyList[${i.index}].memo" value="${bonusPenalty.memo}"/>
                            </c:forEach>
                            <c:forEach begin="${bonusPenaltyCount}" end="2" var="index">
                                <input type="hidden" name="bonusPenaltyList[${index}].date"/>
                                <input type="hidden" name="bonusPenaltyList[${index}].level"/>
                                <input type="hidden" name="bonusPenaltyList[${index}].name"/>
                                <input type="hidden" name="bonusPenaltyList[${index}].unit"/>
                                <input type="hidden" name="bonusPenaltyList[${index}].reason"/>
                                <input type="hidden" name="bonusPenaltyList[${index}].memo"/>
                            </c:forEach>
                        </c:if>
                    </table>
                </div>
            </form>
        </c:if>
        <c:if test="${!(roleFlag eq 'global' || (roleFlag eq 'doctor' && registerFlag))}">
            <div style="text-align:center;font-size:30px;font-weight:500;">
                广州医科大学攻读<c:if test="${param.educationType eq 'master'}">硕士</c:if><c:if test="${param.educationType eq 'doctor'}">博士</c:if>
                <c:if test="${empty param.educationType}">${resume.educationType eq 'doctor'?'博士':'硕士'}</c:if>研究生学籍登记表
            </div>
            <div><table style="width:100%;margin:30px 0px 10px 0px;"><tr>
                <c:if test="${param.educationType eq 'master'}">
                    <td style="width:25%;">&#12288;&#12288;导师单位：${resume.tutorUnit}</td>
                    <td style="width:25%;">学号：${resume.studentId}</td>
                    <td style="width:25%;">准考证号：${resume.ticketNumber}</td>
                    <td style="width:25%;">学位类型：${resume.degreeType eq 'major'?'专业学位':''}${resume.degreeType eq 'doctor'?'学术学位':''}</td>
                </c:if>
                <c:if test="${param.educationType eq 'doctor'}">
                    <td style="width:33%;">&#12288;&#12288;导师单位：${resume.tutorUnit}</td>
                    <td style="width:33%;">学号：${resume.studentId}</td>
                    <td style="width:33%;">学位类型：学术学位</td>
                </c:if>
                <c:if test="${empty param.educationType && (resume.educationType eq 'master' || empty resume.educationType)}">
                    <td style="width:25%;">&#12288;&#12288;导师单位：${resume.tutorUnit}</td>
                    <td style="width:25%;">学号：${resume.studentId}</td>
                    <td style="width:25%;">准考证号：${resume.ticketNumber}</td>
                    <td style="width:25%;">学位类型：${resume.degreeType eq 'major'?'专业学位':''}${resume.degreeType eq 'doctor'?'学术学位':''}</td>
                </c:if>
                <c:if test="${empty param.educationType && resume.educationType eq 'doctor'}">
                    <td style="width:33%;">&#12288;&#12288;导师单位：${resume.tutorUnit}</td>
                    <td style="width:33%;">学号：${resume.studentId}</td>
                    <td style="width:33%;">学位类型：学术学位</td>
                </c:if>
            </tr></table></div>
            <div style="min-width:1540px;">
                <table class="basic" style="width:100%;">
                    <tr>
                        <th>姓名</th>
                        <td>${resume.userName}</td>
                        <th>姓名拼音</th>
                        <td>${resume.nameSpell}</td>
                        <th>性别</th>
                        <td>${resume.sexName eq 'man'?'男':''}${resume.sexName eq 'woman'?'女':''}</td>
                        <th>出生日期</th>
                        <td>${resume.birthDate}</td>
                        <th>民族</th>
                        <td>${resume.nation}</td>
                        <th>婚否</th>
                        <td>${resume.marriage eq 'Y'?'是':''}${resume.marriage eq 'N'?'否':''}</td>
                        <td rowspan="4" style="width:140px;text-align:center;">
                            <img src="${sysCfgMap['upload_base_url']}/${resume.headImgUrl}" width="100px;" height="130px;" onerror="this.src='<s:url value="/jsp/gyxjgl/images/up-pic.jpg"/>'"/>
                        </td>
                    </tr>
                    <tr>
                        <th>导师</th>
                        <td>${resume.tutor}</td>
                        <th>专业</th>
                        <td> ${resume.speName}</td>
                        <th>年级</th>
                        <td>${resume.grade}</td>
                        <th>户口是否<br/>转入学校</th>
                        <td>${resume.hkInSchool eq 'Y'?'是':''}${resume.hkInSchool eq 'N'?'否':''}</td>
                        <th>户口类型</th>
                        <td colspan="3">${resume.hkType eq 'N'?'非农业':''}${resume.hkType eq 'Y'?'农业':''}</td>
                    </tr>
                    <tr>
                        <th>籍贯</th>
                        <td colspan="3">
                            ${resume.nativePlaceProvince}${resume.nativePlaceCity}${resume.nativePlaceArea}
                        </td>
                        <th>是否应届</th>
                        <td>${resume.whetherCurrent eq 'Y'?'是':''}${resume.whetherCurrent eq 'N'?'否':''}</td>
                        <th>政治面貌</th>
                        <td>${resume.politicalOutlook}</td>
                        <th>身份证号</th>
                        <td colspan="3">${resume.idNumber}</td>
                    </tr>
                    <tr>
                        <th>生源地</th>
                        <td colspan="3">
                            ${resume.birthPlaceProvince}${resume.birthPlacePrefectureLevelCity}${resume.birthPlaceCountyLevelCity}
                        </td>
                        <th>出生地</th>
                        <td>${resume.homePlace}</td>
                        <th>入学时间</th>
                        <td>${resume.entranceTime}</td>
                        <th>手机</th>
                        <td colspan="3">${resume.mobilePhone}</td>
                    </tr>
                    <tr>
                        <th>家庭通讯<br/>地址</th>
                        <td colspan="5">${resume.homeAddress}</td>
                        <th>邮政编码</th>
                        <td>${resume.postCode}</td>
                        <th>家庭电话</th>
                        <td colspan="4">${resume.homePhone}</td>
                    </tr>
                    <tr>
                        <th>培养方式</th>
                        <td colspan="3">${resume.cultureType eq 'Y'?'定向':''}${resume.cultureType eq 'N'?'非定向':''}</td>
                        <th>是否华侨<br/>港澳台学生</th>
                        <td colspan="3">${resume.overseasChinese eq 'Y'?'是':''}${resume.overseasChinese eq 'N'?'否':''}</td>
                        <th>特长</th>
                        <td colspan="4">${resume.specialty}</td>
                    </tr>
                    <tr>
                        <th>最后学历</th>
                        <td colspan="12">
                            ${resume.finalEducationYear}年毕业于${resume.finalEducationSchool}大学${resume.finalEducationCollege}学院${resume.finalEducationDepartment}系${resume.finalEducationSpe}专业（修学年限${resume.finalEducationDropOutYear}年）
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="6">学历及经历<br/>（从初中到现在）</th>
                        <th style="text-align:center;" colspan="2">起止年月</th>
                        <th style="text-align:center;" colspan="4">学校或单位</th>
                        <th style="text-align:center;" colspan="2">职务</th>
                        <th style="text-align:center;" colspan="4">单位地址</th>
                    </tr>
                    <c:set var="educationCount" value="0"/>
                    <c:forEach items="${resume.educationList}" var="edu" varStatus="i">
                        <c:set var="educationCount" value="${educationCount+1}" />
                        <tr>
                            <td colspan="2">${edu.beginAndEndDate}</td>
                            <td colspan="4">${edu.schoolAndUnit}</td>
                            <td colspan="2">${edu.postName}</td>
                            <td colspan="4">${edu.unitAddress}</td>
                        </tr>
                    </c:forEach>
                    <c:forEach begin="${educationCount}" end="4" var="index">
                        <tr>
                            <td colspan="2"></td>
                            <td colspan="4"></td>
                            <td colspan="2"></td>
                            <td colspan="4"></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th rowspan="6">家庭成员</th>
                        <th style="text-align:center;">姓名</th>
                        <th style="text-align:center;">年龄</th>
                        <th style="text-align:center;">与本人关系</th>
                        <th style="text-align:center;">政治面貌</th>
                        <th style="text-align:center;" colspan="3">工作单位</th>
                        <th style="text-align:center;">职务</th>
                        <th style="text-align:center;" colspan="3">单位地址</th>
                        <th style="text-align:center;">手机电话</th>
                    </tr>
                    <c:set var="familyCount" value="0"/>
                    <c:forEach items="${resume.familyList}" var="fam" varStatus="i">
                        <c:set var="familyCount" value="${familyCount+1}" />
                        <tr>
                            <td>${fam.name}</td>
                            <td>${fam.age}</td>
                            <td>${fam.relation}</td>
                            <td>${fam.politics}</td>
                            <td colspan="3">${fam.workUnit}</td>
                            <td>${fam.postName}</td>
                            <td colspan="3">${fam.unitAddress}</td>
                            <td>${fam.mobilePhone}</td>
                        </tr>
                    </c:forEach>
                    <c:forEach begin="${familyCount}" end="4" var="index">
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td colspan="3"></td>
                            <td></td>
                            <td colspan="3"></td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    <c:if test="${roleFlag eq 'doctor' && empty registerFlag}">
                        <tr>
                            <th rowspan="3">学籍异动</th>
                            <th style="text-align:center;">年</th>
                            <th style="text-align:center;">月</th>
                            <th style="text-align:center;">日</th>
                            <th style="text-align:center;" colspan="2">异动</th>
                            <th style="text-align:center;">学年度</th>
                            <th style="text-align:center;" colspan="4">原因</th>
                            <th style="text-align:center;" colspan="2">备注</th>
                        </tr>
                        <c:set var="changeCount" value="0"/>
                        <c:forEach items="${resume.changeList}" var="change" varStatus="i">
                            <c:set var="changeCount" value="${changeCount+1}" />
                            <tr>
                                <td>${change.year}</td>
                                <td>${change.month}</td>
                                <td>${change.day}</td>
                                <td colspan="2">${change.changeItem}</td>
                                <td>${change.termYear}</td>
                                <td colspan="4">${change.reason}</td>
                                <td colspan="2">${change.memo}</td>
                            </tr>
                        </c:forEach>
                        <c:forEach begin="${changeCount}" end="1" var="index">
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td colspan="2"></td>
                                <td></td>
                                <td colspan="4"></td>
                                <td colspan="2"></td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <th rowspan="4">奖惩情况</th>
                            <th style="text-align:center;" colspan="2">日期</th>
                            <th style="text-align:center;">级别</th>
                            <th style="text-align:center;" colspan="2">名称</th>
                            <th style="text-align:center;">单位</th>
                            <th style="text-align:center;" colspan="4">原因及说明</th>
                            <th style="text-align:center;" colspan="2">备注</th>
                        </tr>
                        <c:set var="bonusPenaltyCount" value="0"/>
                        <c:forEach items="${resume.bonusPenaltyList}" var="bonusPenalty" varStatus="i">
                            <c:set var="bonusPenaltyCount" value="${bonusPenaltyCount+1}" />
                            <tr>
                                <td colspan="2">${bonusPenalty.date}</td>
                                <td>${bonusPenalty.level}</td>
                                <td colspan="2">${bonusPenalty.name}</td>
                                <td>${bonusPenalty.unit}</td>
                                <td colspan="4">${bonusPenalty.reason}</td>
                                <td colspan="2">${bonusPenalty.memo}</td>
                            </tr>
                        </c:forEach>
                        <c:forEach begin="${bonusPenaltyCount}" end="2" var="index">
                            <tr>
                                <td colspan="2"></td>
                                <td></td>
                                <td colspan="2"></td>
                                <td></td>
                                <td colspan="4"></td>
                                <td colspan="2"></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </div>
        </c:if>
    </div>
</div>
<div hidden="hidden" id="printDivIframe" name="printDivIframe"></div>
</body>
</html>