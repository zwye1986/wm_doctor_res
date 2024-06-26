<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <c:if test="${!param.head}">
        <jsp:include page="/jsp/common/htmlhead.jsp">
            <jsp:param name="basic" value="true"/>
            <jsp:param name="jbox" value="true"/>
            <jsp:param name="jquery_form" value="false"/>
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
    </c:if>
    <style type="text/css">
        table td {
            text-align: left;
        }
        .before{
            color: red;
        }
        .after{
            color:dimgrey;
        }
    </style>
    <script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
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
            var deptNameText = $("#deptFlow option:selected").text();
            $("#deptName").val(deptNameText);
            var nativePlaceProvNameText = $("#nativePlaceProvId option:selected").text();
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
            $("#nativePlaceProvName").val(nativePlaceProvNameText);
            $("#nativePlaceCityName").val(nativePlaceCityNameText);
            $("#nativePlaceAreaName").val(nativePlaceAreaNameText);

            var domicilePlaceNameText = $("#domicilePlaceId option:selected").text();
            if (domicilePlaceNameText == "选择省") {
                domicilePlaceNameText = "";
            }
            var domicileCityNameText = $("#domicileCityId option:selected").text();
            if (domicileCityNameText == "选择市") {
                domicileCityNameText = "";
            }
            var domicileAreaNameText = $("#domicileAreaId option:selected").text();
            if (domicileAreaNameText == "选择地区") {
                domicileAreaNameText = "";
            }
            $("#domicilePlaceName").val(domicilePlaceNameText);
            $("#domicileCityName").val(domicileCityNameText);
            $("#domicileAreaName").val(domicileAreaNameText);

            var recordPlaceNameText = $("#recordPlaceId option:selected").text();
            if (recordPlaceNameText == "选择省") {
                recordPlaceNameText = "";
            }
            var recordCityNameText = $("#recordCityId option:selected").text();
            if (recordCityNameText == "选择市") {
                recordCityNameText = "";
            }
            var recordAreaNameText = $("#recordAreaId option:selected").text();
            if (recordAreaNameText == "选择地区") {
                recordAreaNameText = "";
            }
            $("#recordPlaceName").val(recordPlaceNameText);
            $("#recordCityName").val(recordCityNameText);
            $("#recordAreaName").val(recordAreaNameText);

            var form = $("#addForm").serialize();
            jboxPost("<s:url value='/gyxjgl/user/saveEduUser'/>", form, function (obj) {
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
        $(document).ready(function () {
            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                doDisabled($("#outOfSchool")[0]);
                doDisabled($("#backToSchool")[0]);
                //管理员全部可以编辑，不做控制

                $("input:not(.notBlank)").removeClass("validate[required]");
                $("select:not(.notBlank)").removeClass("validate[required]");
            </c:if>
            jboxEndLoading();
            loadIdCheck();

            //培养层次初始化
            if($("#trainTypeId").val() == "1"){//培养层次为硕士研究生
                $('.degreeCode').hide();
                $('.linkRecruitType').hide();
                $('.gotMasterCertSpeTr').hide();
            }else{
                $('.degreeCode').show();
                $('.linkRecruitType').show();
                if($("#trainTypeId").val() == "2"){//培养层次为博士研究生
                    $('.gotMasterCertSpeTr').show();
                }
            }
            //培养层次change绑定
            $("#trainTypeId").change(function () {
                var value = $(this).val();
                if(value == "1"){//培养层次为硕士研究生
                    $('.degreeCode').hide();
                    $('.linkRecruitType').hide();
                    $('.gotMasterCertSpeTr').hide();
                }else{
                    $('.degreeCode').show();
                    $('.linkRecruitType').show();
                    if($("#trainTypeId").val() == "2"){//培养层次为博士研究生
                        $('.gotMasterCertSpeTr').show();
                    }
                }
            });
            //标题伸缩初始化
            <%--$(".spreadOneOne").toggle("${param.baseStatus eq GlobalConstant.FLAG_Y}"=="true");--%>
            <%--$(".spreadOneTwo").toggle("${param.recruitStatus eq GlobalConstant.FLAG_Y}"=="true");--%>
            <%--$(".spreadOneThree").toggle("${param.needStatus eq GlobalConstant.FLAG_Y}"=="true");--%>
            <%--$(".spreadOneFour").toggle("${param.selectStatus eq GlobalConstant.FLAG_Y}"=="true");--%>
            <%--$(".spreadOneFive").toggle("${param.feeStatus eq GlobalConstant.FLAG_Y}"=="true");--%>
            <%--$(".spreadTwoOne").toggle("${param.gotCertStatus eq GlobalConstant.FLAG_Y}"=="true");--%>
            <%--$(".spreadTwoTwo").toggle("${param.certReqStatus eq GlobalConstant.FLAG_Y}"=="true");--%>
            <%--$(".spreadThree").toggle("${param.paperStatus eq GlobalConstant.FLAG_Y}"=="true");--%>
            <%--$(".spreadFour").toggle("${param.workStatus eq GlobalConstant.FLAG_Y}"=="true");--%>
            //招录方式初始化
            if($(".recruitType").val() == "3"){
                $('#mbaDbaFlag').val("Y");//硕博连读
//                $('.linkRecruitType').hide();//隐藏硕士研究生学历 及 硕士学位 区域
            }else{
                $('#mbaDbaFlag').val("N");//非硕博连读
//                $('.linkRecruitType').show();
            }
            //招录方式change绑定（同步 "是否硕博连读"）
            $(".recruitType").change(function () {
                var value = $(this).val();
                if(value == "3"){
                    $('#mbaDbaFlag').val("Y");//硕博连读
//                    $('.linkRecruitType').hide();//隐藏硕士研究生学历 及 硕士学位 区域
                }else{
                    $('#mbaDbaFlag').val("N");//非硕博连读
//                    $('.linkRecruitType').show();
                }
            });
            //户口是否迁入我校初始化
            if($(".hkInSchool").val() == "1"){
                $('.hkChangeNo').removeAttr("readonly");
                $('.hkChangeNo').addClass("validate[required]");
                $('.hkChangeNoTd').show();
            }else if($(".hkInSchool").val() == "2"){
                $('.hkChangeNo').val('');
                $('.hkChangeNo').attr("readonly","readonly");
                $('.hkChangeNo').removeClass("validate[required]");
                $('.hkChangeNoTd').hide();
            }
            //户口是否迁入我校change绑定
            $(".hkInSchool").change(function () {
                var value = $(this).val();
                if(value == "1"){
                    $('.hkChangeNo').removeAttr("readonly")
                    $('.hkChangeNo').addClass("validate[required]");
                    $('.hkChangeNoTd').show();
                }else if(value == "2"){
                    $('.hkChangeNo').val('');
                    $('.hkChangeNo').attr("readonly","readonly");
                    $('.hkChangeNo').removeClass("validate[required]");
                    $('.hkChangeNoTd').hide();
                }
            });
            //是否有医师资格证初始化
            if($(".quaCert").val() == "Y"){
                $('.quaCertNo').removeAttr("readonly");
                $('.quaCertNo').addClass("validate[required]");
                $('.quaCertNoTd').show();
                $('.quaCertNoFillTd').hide();
            }else if($(".quaCert").val() == "N"){
                $('.quaCertNo').val('');
                $('.quaCertNo').attr("readonly","readonly");
                $('.quaCertNo').removeClass("validate[required]");
                $('.quaCertNoTd').hide();
                $('.quaCertNoFillTd').show();
            }
            //是否有医师资格证change绑定
            $(".quaCert").change(function () {
                var value = $(this).val();
                if(value == "Y"){
                    $('.quaCertNo').removeAttr("readonly");
                    $('.quaCertNo').addClass("validate[required]");
                    $('.quaCertNoTd').show();
                    $('.quaCertNoFillTd').hide();
                }else if(value == "N"){
                    $('.quaCertNo').val('');
                    $('.quaCertNo').attr("readonly","readonly");
                    $('.quaCertNo').removeClass("validate[required]");
                    $('.quaCertNoTd').hide();
                    $('.quaCertNoFillTd').show();
                }
            });
            //是否有执业医师执照初始化
            if($(".zyCert").val() == "Y"){
                $('.zyCertNo').removeAttr("readonly");
                $('.zyCertNo').addClass("validate[required]");
                $('.zyCertNoTd').show();
                $('.zyCertNoFillTd').hide();
            }else if($(".zyCert").val() == "N"){
                $('.zyCertNo').val('');
                $('.zyCertNo').attr("readonly","readonly");
                $('.zyCertNo').removeClass("validate[required]");
                $('.zyCertNoTd').hide();
                $('.zyCertNoFillTd').show();
            }
            //是否有执业医师执照change绑定
            $(".zyCert").change(function () {
                var value = $(this).val();
                if(value == "Y"){
                    $('.zyCertNo').removeAttr("readonly");
                    $('.zyCertNo').addClass("validate[required]");
                    $('.zyCertNoTd').show();
                    $('.zyCertNoFillTd').hide();
                }else if(value == "N"){
                    $('.zyCertNo').val('');
                    $('.zyCertNo').attr("readonly","readonly");
                    $('.zyCertNo').removeClass("validate[required]");
                    $('.zyCertNoTd').hide();
                    $('.zyCertNoFillTd').show();
                }
            });
            //是否住宿初始化
            if($('.isStay').val() == 'Y'){
                $('.roomNumStay').removeAttr("readonly");
                <%--if(${empty extInfoForm.roomNumStay} ){--%>
                    <%--$('#roomNum').text('等待思政科老师导入');--%>
                <%--}--%>
                $('.roomNumStayTd').show();
                $('.roomNumStayFillTd').hide();
            }else if($('.isStay').val() == 'N'){
                $('.roomNumStay').val('');
                $('#roomNum').text('');
                $('.roomNumStay').attr("readonly","readonly");
                $('.roomNumStayTd').hide();
                $('.roomNumStayFillTd').show();
            }
            //是否住宿change绑定
            $(".isStay").change(function () {
                var value = $(this).val();
                if(value == 'Y'){
                    $('.roomNumStay').removeAttr("readonly");
                    <%--if(${empty extInfoForm.roomNumStay} ){--%>
                        <%--$('#roomNum').text('等待思政科老师导入');--%>
                    <%--}--%>
                    $('.roomNumStayTd').show();
                    $('.roomNumStayFillTd').hide();
                }else if(value == 'N'){
                    $('.roomNumStay').val('');
                    $('#roomNum').text('');
                    $('.roomNumStay').attr("readonly","readonly");
                    $('.roomNumStayTd').hide();
                    $('.roomNumStayFillTd').show();
                }
            });
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
            //党团关系是否转入初始化
            if($('#isRelationInto').val() == 'Y'){
                $('.joinTimeTd').show();
            }else if($('#isRelationInto').val() == 'N') {
                $('.joinTimeTd').hide();
            }
            //党团关系是否转入change绑定
            $("#isRelationInto").change(function () {
                var value = $(this).val();
                if (value == "Y") {
                    $('.joinTimeTd').show();
                }else if(value == "N") {
                    $('.joinTimeTd').hide();
                }
            });
            //是否获得本科学历初始化
            if($('#bkgain').val() == '2'){
                $('.bkgainDisplayContent').hide();
                $('.bkgainFillTD').show();
            }else {
                $('.bkgainDisplayContent').show();
                $('.bkgainFillTD').hide();
            }
            //是否获得本科学历change绑定
            $("#bkgain").change(function () {
                var value = $(this).val();
                if (value == "2") {
                    $('.bkgainDisplayContent').hide();
                    $('.bkgainFillTD').show();
                }else {
                    $('.bkgainDisplayContent').show();
                    $('.bkgainFillTD').hide();
                }
            });
            //是否获得学士学位初始化
            if($('#xsgain').val() == '2'){
                $('.xsgainDisplayContent').hide();
            }else {
                $('.xsgainDisplayContent').show();
            }
            //是否获得学士学位change绑定
            $("#xsgain").change(function () {
                var value = $(this).val();
                if (value == "2") {
                    $('.xsgainDisplayContent').hide();
                }else {
                    $('.xsgainDisplayContent').show();
                }
            });
            //是否获得硕士研究生学历初始化
            if($('#ssyjsgain').val() == '2'){
                $('.ssyjsgainDisplayContent').hide();
            }else if($('#ssyjsgain').val() == '1'){
                $('.ssyjsgainDisplayContent').show();
            }
            //是否获得硕士研究生学历change绑定
            $("#ssyjsgain").change(function () {
                var value = $(this).val();
                if (value == "2") {
                    $('.ssyjsgainDisplayContent').hide();
                }else if(value == "1"){
                    $('.ssyjsgainDisplayContent').show();
                }
            });
            //是否获得硕士学位初始化
            if($('#ssxwgain').val() == '2'){
                $('.ssxwgainDisplayContent').hide();
                $('.ssxwgainFillTD').show();
            }else if($('#ssxwgain').val() == '1'){
                $('.ssxwgainDisplayContent').show();
                $('.ssxwgainFillTD').hide();
            }
            //是否获得硕士学位change绑定
            $("#ssxwgain").change(function () {
                var value = $(this).val();
                if (value == "2") {
                    $('.ssxwgainDisplayContent').hide();
                    $('.ssxwgainFillTD').show();
                }else if(value == "1"){
                    $('.ssxwgainDisplayContent').show();
                    $('.ssxwgainFillTD').hide();
                }
            });
            //家庭是否遭受自然灾害初始化
            if($('#naturalDisasters').val() == 'Y'){
                $('.naturalDisastersTd').show();
                $('.naturalDisastersFillTd').hide();
            }else if($('#naturalDisasters').val() == 'N') {
                $('.naturalDisastersTd').hide();
                $('.naturalDisastersFillTd').show();
            }
            //家庭是否遭受自然灾害change绑定
            $("#naturalDisasters").change(function () {
                var value = $(this).val();
                if (value == "Y") {
                    $('.naturalDisastersTd').show();
                    $('.naturalDisastersFillTd').hide();
                }else if(value == "N") {
                    $('.naturalDisastersTd').hide();
                    $('.naturalDisastersFillTd').show();
                }
            });
            //家庭是否遭受突发意外事件初始化
            if($('#emergency').val() == 'Y'){
                $('.emergencyTd').show();
                $('.emergencyFillTd').hide();
            }else if($('#emergency').val() == 'N') {
                $('.emergencyTd').hide();
                $('.emergencyFillTd').show();
            }
            //家庭是否遭受突发意外事件change绑定
            $("#emergency").change(function () {
                var value = $(this).val();
                if (value == "Y") {
                    $('.emergencyTd').show();
                    $('.emergencyFillTd').hide();
                }else if(value == "N") {
                    $('.emergencyTd').hide();
                    $('.emergencyFillTd').show();
                }
            });
            //家庭主要收入来源类型初始化
            if($('#incomeType').val() == "5" || $('#incomeType').val() == "6"){
                $('#incomeSpan').show();
            }else{
                $('#incomeSpan').hide();
            }
            //家庭主要收入来源类型change绑定
            $("#incomeType").change(function () {
                var value = $(this).val();
                if (value == "5" || value == "6") {
                    $('#incomeSpan').show();
                }else{
                    $('#incomeSpan').hide();
                }
            });
            //是否申请贫困生初始化
            if($('#isPoorStudents').val() == "Y" ){
                $('#poorStudentsTbody').show();
            }else{
                $('#poorStudentsTbody').hide();
            }
            //是否申请贫困生change绑定
            $("#isPoorStudents").change(function () {
                var value = $(this).val();
                if (value == "Y") {
                    $('#poorStudentsTbody').show();
                }else{
                    $('#poorStudentsTbody').hide();
                }
            });
            //本人是否残疾初始化
            if($('#userDisabled').val() == 'Y'){
                $('#userDisabledLevelTh').show();
                $('#userDisabledLevelTd').show();
            }else if($('#userDisabled').val() == 'N') {
                $('#userDisabledLevelTh').hide();
                $('#userDisabledLevelTd').hide();
                $('#userDisabledLevelFileTh').show();
                $('#userDisabledLevelFileTd').show();
            }
            //本人是否残疾change绑定
            $("#userDisabled").change(function () {
                var value = $(this).val();
                if (value == "Y") {
                    $('#userDisabledLevelTh').show();
                    $('#userDisabledLevelTd').show();
                    $('#userDisabledLevelFileTh').hide();
                    $('#userDisabledLevelFileTd').hide();
                }else if(value == "N") {
                    $('#userDisabledLevelTh').hide();
                    $('#userDisabledLevelTd').hide();
                    $('#userDisabledLevelFileTh').show();
                    $('#userDisabledLevelFileTd').show();
                }
            });
            //是否毕业初始化
            if($('#isGraduateFlag').val() == "1" ){
                $('#graduateFlagTbody').show();
            }else{
                $('#graduateFlagTbody').hide();
            }
            //是否毕业change绑定
            $("#isGraduateFlag").change(function () {
                var value = $(this).val();
                if (value == "1") {
                    $('#graduateFlagTbody').show();
                }else{
                    $('#graduateFlagTbody').hide();
                }
            });
            //默认展示基本信息
            $("#tags").find("li").eq(0).addClass("selectTag");
            selectTag(this,'baseInfo');
        });
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
//                $("#" + id + "Td").removeAttr("colspan");
                $("." + id + "Td").show();
            } else {
                $date.val(null);
                $("#" + id + "Td").parent().append("<th></th><td></td>")
//                $("#" + id + "Td").attr("colspan", 3);
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
                    //searchUser();
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
        function baseCheck(){//基本信息伸缩
            var baseStatus = $("#baseStatus").val();
            $("#baseStatus").val(!(baseStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            baseStatus = $("#baseStatus").val();
            $(".spreadOneOne").toggle(baseStatus=="${GlobalConstant.FLAG_Y}");
        }
        function recruitCheck(){//录取信息伸缩
            var recruitStatus = $("#recruitStatus").val();
            $("#recruitStatus").val(!(recruitStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            recruitStatus = $("#recruitStatus").val();
            $(".spreadOneTwo").toggle(recruitStatus=="${GlobalConstant.FLAG_Y}");
        }
        function needCheck(){//必填信息伸缩
            var needStatus = $("#needStatus").val();
            $("#needStatus").val(!(needStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            needStatus = $("#needStatus").val();
            $(".spreadOneThree").toggle(needStatus=="${GlobalConstant.FLAG_Y}");
        }
        function selectCheck(){//选填信息伸缩
            var selectStatus = $("#selectStatus").val();
            $("#selectStatus").val(!(selectStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            selectStatus = $("#selectStatus").val();
            $(".spreadOneFour").toggle(selectStatus=="${GlobalConstant.FLAG_Y}");
        }
        function feeCheck(){//学费信息伸缩
            var feeStatus = $("#feeStatus").val();
            $("#feeStatus").val(!(feeStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            feeStatus = $("#feeStatus").val();
            $(".spreadOneFive").toggle(feeStatus=="${GlobalConstant.FLAG_Y}");
        }
        function gotCertCheck(){//已获得学历或学位证书伸缩
            var gotCertStatus = $("#gotCertStatus").val();
            $("#gotCertStatus").val(!(gotCertStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            gotCertStatus = $("#gotCertStatus").val();
            $(".spreadTwoOne").toggle(gotCertStatus=="${GlobalConstant.FLAG_Y}");
        }
        function certReqCheck(){//学历学位申请证书伸缩
            var certReqStatus = $("#certReqStatus").val();
            $("#certReqStatus").val(!(certReqStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            certReqStatus = $("#certReqStatus").val();
            $(".spreadTwoTwo").toggle(certReqStatus=="${GlobalConstant.FLAG_Y}");
        }
        function paperCheck(){//学位论文信息伸缩
            var paperStatus = $("#paperStatus").val();
            $("#paperStatus").val(!(paperStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            paperStatus = $("#paperStatus").val();
            $(".spreadThree").toggle(paperStatus=="${GlobalConstant.FLAG_Y}");
        }
        function workCheck(){//就业信息伸缩
            var workStatus = $("#workStatus").val();
            $("#workStatus").val(!(workStatus=="${GlobalConstant.FLAG_Y}")?"${GlobalConstant.FLAG_Y}":"${GlobalConstant.FLAG_N}");
            workStatus = $("#workStatus").val();
            $(".spreadFour").toggle(workStatus=="${GlobalConstant.FLAG_Y}");
        }
        function confirmStatus(partId,temp){
            jboxConfirm("是否确认？" , function(){
                jboxPost("<s:url value='/gyxjgl/course/manage/savePartStatus?userFlow=${eduUser.userFlow}&partId='/>"+partId, null, function (obj) {
                    if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                        jboxTip("信息确认成功！");
                        $(temp).parent().html("<a class='after'>信息已确认</a>");
                        save();
                    }
                },null,false);
            });
        }
//        function orphanBindSingle(val){
//            if(val=="isOrphan"){
//                if($(".isOrphan").val() == 'Y'){
//                    $(".isSingleParent").val("N");
//                }else if($(".isOrphan").val() == 'N'){
//                    $(".isSingleParent").val("Y");
//                }
//            }else{
//                if($(".isSingleParent").val() == 'Y'){
//                    $(".isOrphan").val("N");
//                }else if($(".isSingleParent").val() == 'N'){
//                    $(".isOrphan").val("Y");
//                }
//            }
//        }
        function toDecimal(obj) {
            var value = $(obj).val();
            if (value == "" || isNaN(value)) {
                return false;
            }
            if(value.indexOf('.') != -1){
                $(obj).val(value.substring(0,value.indexOf('.')));
            }
        }
        function toDecimal2(obj) {
            var value = $(obj).val();
            if (value == "" || isNaN(value)) {
                return false;
            }
            if(value.indexOf('.') != -1){
                $(obj).val(value.substring(0,value.indexOf('.')+3));
            }
        }


        function selectTag(selfObj,url) {
            var selLi = $(selfObj).parent();
            selLi.siblings("li").removeClass("selectTag");
            selLi.addClass("selectTag");
            $("div.spreadOne").hide();
            $("#"+url).show();
        }

        function printTemp(userFlow){
            var url = '<s:url value="/gyxjgl/user/printUserCertificateInfo?userFlow="/>'+userFlow;
            jboxTip("打印中，请稍等...");
            setTimeout(function(){
                jboxOpen(url,"day",800,600,true);
                setTimeout(function(){jboxClose();},2000);
            },2000);
        }
        function sendNotice(value){
            if(value != ""){
                jboxPost("<s:url value='/gyxjgl/notice/sendPaidFeeNotice?userFlow=${eduUser.userFlow}&noticeFlag='/>"+value, null, function (obj) {

                },null,false);
            }
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
                        <c:set var="employmentY" value="${permissionCfg.cfgValue eq 'Y' && roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openEmploymentStartDate.cfgValue && pdfn:getCurrDate() le closeEmploymentEndDate.cfgValue}"/>
                        <c:if test="${employmentN or employmentY}">
                            <c:set var="employmentFlag" value="true"/>
                        </c:if>
                        <!-- 针对特定学员 开放 学位论文修改 -->
                        <c:set var="paperN" value="${permissionCfg.cfgValue eq 'N'&& eduUser.isMdfInfo eq 'Y' && roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openPaperStartDate.cfgValue && pdfn:getCurrDate() le closePaperEndDate.cfgValue}"/>
                        <!-- 针对所有学员 开放 学位论文修改 -->
                        <c:set var="paperY" value="${permissionCfg.cfgValue eq 'Y' && roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openPaperStartDate.cfgValue && pdfn:getCurrDate() le closePaperEndDate.cfgValue}"/>
                        <c:if test="${paperN or paperY}">
                            <c:set var="paperFlag" value="true"/>
                        </c:if>
                        <!-- 针对特定学员 开放 学业和学位授予信息修改 -->
                        <c:set var="degreeGrantN" value="${permissionCfg.cfgValue eq 'N'&& eduUser.isMdfInfo eq 'Y' && roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openDegreeGrantStartDate.cfgValue && pdfn:getCurrDate() le closeDegreeGrantEndDate.cfgValue}"/>
                        <!-- 针对所有学员 开放 学业和学位授予信息修改 -->
                        <c:set var="degreeGrantY" value="${permissionCfg.cfgValue eq 'Y' && roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openDegreeGrantStartDate.cfgValue && pdfn:getCurrDate() le closeDegreeGrantEndDate.cfgValue}"/>
                        <c:if test="${degreeGrantN or degreeGrantY}">
                            <c:set var="degreeGrantFlag" value="true"/>
                        </c:if>
                        <!-- 针对特定学员 开放 必填信息修改 -->
                        <c:set var="requiredN" value="${permissionCfg.cfgValue eq 'N'&& eduUser.isMdfInfo eq 'Y' && roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openRequiredStartDate.cfgValue && pdfn:getCurrDate() le closeRequiredEndDate.cfgValue}"/>
                        <!-- 针对所有学员 开放 必填信息修改 -->
                        <c:set var="requiredY" value="${permissionCfg.cfgValue eq 'Y' && roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openRequiredStartDate.cfgValue && pdfn:getCurrDate() le closeRequiredEndDate.cfgValue}"/>
                        <c:if test="${requiredN or requiredY}">
                            <c:set var="requiredFlag" value="true"/>
                        </c:if>
                        <!-- 针对特定学员 开放 选填信息修改 -->
                        <c:set var="optionalN" value="${permissionCfg.cfgValue eq 'N'&& eduUser.isMdfInfo eq 'Y' && roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openOptionalStartDate.cfgValue && pdfn:getCurrDate() le closeOptionalEndDate.cfgValue}"/>
                        <!-- 针对所有学员 开放 选填信息修改 -->
                        <c:set var="optionalY" value="${permissionCfg.cfgValue eq 'Y' && roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR && pdfn:getCurrDate() ge openOptionalStartDate.cfgValue && pdfn:getCurrDate() le closeOptionalEndDate.cfgValue}"/>
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
                                <input type="button" class="search" onclick="printTemp('${eduUser.userFlow}');" style="" value="打&#12288;印"/>
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
            <div style="margin-bottom:10px;">
                <%--<table class="basic" style="width:100%;">--%>
                    <%--<tr>--%>
                        <%--<th style="text-align:left;">&#12288;学生基本信息</th>--%>
                    <%--</tr>--%>
                <%--</table>--%>
                <div style="margin-top:10px;">
                    <ul id="tags" style="margin-left: 0px;">
                        <li>
                            <a onclick="selectTag(this,'baseInfo')" href="javascript:void(0)">基本信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'recruitInfo')" href="javascript:void(0)">录取信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'needInfo')" href="javascript:void(0)">必填信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'selectInfo')" href="javascript:void(0)">选填信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'feeInfo')" href="javascript:void(0)">学费信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'gotCertInfo')" href="javascript:void(0)">已获学历</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'certReqInfo')" href="javascript:void(0)">攻读学历</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'paperInfo')" href="javascript:void(0)">论文信息</a>
                        </li>
                        <li>
                            <a onclick="selectTag(this,'workInfo')" href="javascript:void(0)">就业信息</a>
                        </li>
                    </ul>
                    <div id="tagContent" style="margin-top: 3px;">
                    </div>
                    <div class="spreadOne" hidden="hidden" style="margin-bottom:10px;" id="baseInfo">
                        <input type="hidden" id="baseStatus" name="baseStatus" value="${param.baseStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;基本信息--%>
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'}">
                                <span style="float:right;margin-right:100px;margin-top: 5px;margin-bottom: 5px;"><c:if test="${statusMap['BaseInfo'] ne 'Y'}"><a class="before">信息未确认，点击按钮确认</a><input type="button" class="search"  onclick="confirmStatus('BaseInfo',this)" value="确&#12288;认"/></c:if>
                        <c:if test="${statusMap['BaseInfo'] eq 'Y'}"><a class="after">信息已确认</a></c:if></span>
                            </c:if>
                            <%--<span style="float:right;margin-right:${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'?30:118}px;line-height:27px;color:blue;cursor:pointer;" onclick="baseCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadOneOne">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span style="color: red;">*</span>&#12288;<span >学号：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" style="width: 140px;text-align: left;" class="inputText validate[required] notBlank" name="eduUser.sid" id="sid" value="${eduUser.sid}"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.sid}</label>
                                        </c:if>
                                    </td>
                                    <th style="width:20%;"><span >证件类型：</span></th>
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
                                    <th><span >姓名拼音：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUser.nameSpell" value="${eduUser.nameSpell }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.nameSpell }</label>
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
                                    <th><span >班级：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.classId" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyXjClassList}" var="xjclass">
                                                    <option value="${xjclass.dictId}" ${eduUser.classId eq xjclass.dictId?'selected':''}>${xjclass.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.className}</label>
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
                                    <th><span >专业名称：</span></th>
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
                                    <th><span >入学年级：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" class="notBlank inputText ctime" style="width: 140px;text-align: left;" name="eduUser.period" id="period" value="${eduUser.period }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            ${eduUser.period }
                                        </c:if>
                                    </td>
                                    <th><span >培养层次：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <c:if test="${importModifyFlag}">
                                                <select style="width: 144px;" id="trainTypeId" name="eduUser.trainTypeId" class="notBlank inputText">
                                                    <option/>
                                                    <c:forEach items="${dictTypeEnumGyTrainTypeList}" var="trainType">
                                                        <c:if test="${trainType.dictId eq '1' || trainType.dictId eq '2'}">
                                                            <option value="${trainType.dictId}" ${eduUser.trainTypeId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${!importModifyFlag}">
                                                <label>${eduUser.trainTypeName}</label>
                                                <input type="hidden" id="trainTypeId" value="${eduUser.trainTypeId}">
                                            </c:if>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.trainTypeName}</label>
                                            <input type="hidden" id="trainTypeId" value="${eduUser.trainTypeId}">
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >学位分委员会：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" id="deptFlow" name="sysUser.deptFlow" class=" inputText">
                                                <option></option>
                                                <c:forEach items="${deptList }" var="dept">
                                                    <option value="${dept.deptFlow}"
                                                            <c:if test="${dept.deptFlow==sysUser.deptFlow }">selected</c:if>>${dept.deptName}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" id="deptName" name="sysUser.deptName">
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:forEach items="${deptList }" var="dept">
                                                ${dept.deptFlow eq sysUser.deptFlow?dept.deptName:'' }
                                            </c:forEach>
                                        </c:if>
                                    </td>
                                    <th><span >是否5+3培养模式：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.is5plus3" class="inputText">
                                                <option/>
                                                <option value="${GlobalConstant.FLAG_Y}" ${eduUser.is5plus3 eq GlobalConstant.FLAG_Y?'selected':''}>
                                                    是
                                                </option>
                                                <option value="${GlobalConstant.FLAG_N}" ${eduUser.is5plus3 eq GlobalConstant.FLAG_N?'selected':''}>
                                                    否
                                                </option>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>
                                                <c:if test="${eduUser.is5plus3==GlobalConstant.FLAG_Y}">是</c:if>
                                                <c:if test="${eduUser.is5plus3==GlobalConstant.FLAG_N}">否</c:if>
                                            </label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >培养单位：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select id="resDoctorOrgFlow" name="resDoctor.orgFlow" style="width: 144px;" onchange="getOrgName();" class="inputText">
                                                <option />
                                                <c:forEach items="${orgList }" var="org">
                                                    <option value="${org.orgFlow }"
                                                            <c:if test="${org.orgFlow==resDoctor.orgFlow }">selected</c:if>>${org.orgName }</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${resDoctor.orgName }</label>
                                        </c:if>
                                        <input type="hidden" id="resDoctorOrgName" name="resDoctor.orgName" value="${resDoctor.orgName }"/>
                                    </td>
                                    <th><span >研究方向：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" name="eduUser.researchDirName" class="inputText" style="width: 140px;text-align: left;" value="${eduUser.researchDirName }">
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.researchDirName }</label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >培养类型：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.trainCategoryId" class="inputText">
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
                                    <th></th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th><span >导师一：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUser.firstTeacher" value="${eduUser.firstTeacher }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.firstTeacher }</label>
                                        </c:if>
                                    </td>
                                    <th><span >导师二：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUser.secondTeacher" value="${eduUser.secondTeacher }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.secondTeacher }</label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >学籍状态：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" id="schoolRollStatusId" name="eduUser.schoolRollStatusId" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGySchoolRollStatusList}" var="schoolRoll">
                                                    <option value="${schoolRoll.dictId}" ${eduUser.schoolRollStatusId eq schoolRoll.dictId?'selected':''}>${schoolRoll.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.schoolRollStatusName}</label>
                                        </c:if>
                                        <%--[<a href="javascript:void(0)" onclick="editRegisterDate('${roleFlag}')">学籍注册时间</a>]--%>
                                    </td>
                                    <th><span >学籍注册时间：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.xjRegDate" value="${extInfoForm.xjRegDate }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${extInfoForm.xjRegDate }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.xjRegDate" value="${extInfoForm.xjRegDate}">
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >是否报到：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.isReported" class="inputText">
                                                <option/>
                                                <option value="${GlobalConstant.FLAG_Y}" ${eduUser.isReported eq GlobalConstant.FLAG_Y?'selected':''}>
                                                    是
                                                </option>
                                                <option value="${GlobalConstant.FLAG_N}" ${eduUser.isReported eq GlobalConstant.FLAG_N?'selected':''}>
                                                    否
                                                </option>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>
                                                <c:if test="${eduUser.isReported eq GlobalConstant.FLAG_Y}">是</c:if>
                                                <c:if test="${eduUser.isReported eq GlobalConstant.FLAG_N}">否</c:if>
                                            </label>
                                        </c:if>
                                    </td>
                                    <th><span >报到时间：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.reportedDate" value="${extInfoForm.reportedDate}"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${extInfoForm.reportedDate}</label>
                                            <input type="hidden" name="eduUserExtInfoForm.reportedDate" value="${extInfoForm.reportedDate}">
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >在校状态：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;"  id="atSchoolStatusId" name="eduUser.atSchoolStatusId" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyAtSchoolStatusList}" var="atSchool">
                                                    <option value="${atSchool.dictId}" ${eduUser.atSchoolStatusId eq atSchool.dictId?'selected':''}>${atSchool.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.atSchoolStatusName }</label>
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
                                <tr>
                                    <th><span >是否休学：</span></th>
                                    <td id="outOfSchoolTd">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.isOutOfSchool" class="inputText" id="outOfSchool" onchange="doDisabled(this)">
                                                <option/>
                                                <option value="${GlobalConstant.FLAG_Y}" ${eduUser.isOutOfSchool eq GlobalConstant.FLAG_Y?'selected':''}>
                                                    是
                                                </option>
                                                <option value="${GlobalConstant.FLAG_N}" ${eduUser.isOutOfSchool eq GlobalConstant.FLAG_N?'selected':''}>
                                                    否
                                                </option>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>
                                                <c:if test="${eduUser.isOutOfSchool eq GlobalConstant.FLAG_Y}">是</c:if>
                                                <c:if test="${eduUser.isOutOfSchool eq GlobalConstant.FLAG_N}">否</c:if>
                                            </label>
                                        </c:if>
                                    </td>
                                    <th class="outOfSchoolTd"><span >休学日期：</span></th>
                                    <td class="outOfSchoolTd">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.outOfSchoolDate" id="outOfSchoolDate" value="${extInfoForm.outOfSchoolDate}"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${extInfoForm.outOfSchoolDate}</label>
                                            <input type="hidden" name="eduUserExtInfoForm.outOfSchoolDate" value="${extInfoForm.outOfSchoolDate}">
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >是否复学：</span></th>
                                    <td id="backToSchoolTd">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.isBackToSchool" class="inputText" id="backToSchool" onchange="doDisabled(this)">
                                                <option/>
                                                <option value="${GlobalConstant.FLAG_Y}" ${eduUser.isBackToSchool eq GlobalConstant.FLAG_Y?'selected':''}>
                                                    是
                                                </option>
                                                <option value="${GlobalConstant.FLAG_N}" ${eduUser.isBackToSchool eq GlobalConstant.FLAG_N?'selected':''}>
                                                    否
                                                </option>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>
                                                <c:if test="${eduUser.isBackToSchool eq GlobalConstant.FLAG_Y}">是</c:if>
                                                <c:if test="${eduUser.isBackToSchool eq GlobalConstant.FLAG_N}">否</c:if>
                                            </label>
                                        </c:if>
                                    </td>
                                    <%--<th></th>--%>
                                    <%--<td></td>--%>
                                    <th class="backToSchoolTd"><span >复学日期：</span></th>
                                    <td class="backToSchoolTd">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.backToSchoolDate" id="backToSchoolDate" value="${extInfoForm.backToSchoolDate}"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${extInfoForm.backToSchoolDate}</label>
                                            <input type="hidden" name="eduUserExtInfoForm.backToSchoolDate" value="${extInfoForm.backToSchoolDate}">
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >退学时间：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.dropOutSchoolDate" value="${extInfoForm.dropOutSchoolDate}"
                                                   onfocus="drop(this.value);"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${extInfoForm.dropOutSchoolDate}</label>
                                            <input type="hidden" name="eduUserExtInfoForm.dropOutSchoolDate" value="${extInfoForm.dropOutSchoolDate}">
                                        </c:if>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" hidden="hidden" style="margin-bottom:10px;" id="recruitInfo">
                        <input type="hidden" id="recruitStatus" name="recruitStatus" value="${param.recruitStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;录取信息--%>
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'}">
                        <span style="float:right;margin-right:100px;margin-top: 5px;margin-bottom: 5px;"><c:if test="${statusMap['RecruitInfo'] ne 'Y'}"><a class="before">信息未确认，点击按钮确认</a><input type="button" class="search"  onclick="confirmStatus('RecruitInfo',this)" value="确&#12288;认"/></c:if>
                        <c:if test="${statusMap['RecruitInfo'] eq 'Y'}"><a class="after">信息已确认</a></c:if></span>
                            </c:if>
                            <%--<span style="float:right;margin-right:${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'?30:118}px;line-height:27px;color:blue;cursor:pointer;" onclick="recruitCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadOneTwo">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >考生编号：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUser.studentCode" value="${eduUser.studentCode }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.studentCode }</label>
                                        </c:if>
                                    </td>
                                    <th style="width:20%;"><span >考生来源：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.studentSourceId" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyStudentSourceList}" var="studentSource">
                                                    <option value="${studentSource.dictId}" ${eduUser.studentSourceId eq studentSource.dictId?'selected':''}>${studentSource.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>
                                                <c:forEach items="${dictTypeEnumGyStudentSourceList}" var="studentSource">
                                                    ${eduUser.studentSourceId eq studentSource.dictId?studentSource.dictName:''}
                                                </c:forEach>
                                            </label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >录取类别：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.admitTypeId" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyAdmitTypeList}" var="admitType">
                                                    <option value="${admitType.dictId}" ${eduUser.admitTypeId eq admitType.dictId?'selected':''}>${admitType.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.admitTypeName}</label>
                                        </c:if>
                                    </td>
                                    <th><span >招生季节：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.recruitSeasonId" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyRecruitSeasonList}" var="recruit">
                                                    <option value="${recruit.dictId}" ${eduUser.recruitSeasonId eq recruit.dictId?'selected':''}>${recruit.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.recruitSeasonName}</label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >招录方式：</span></th>
                                    <td>
                                        <input type="hidden" id="mbaDbaFlag" name="eduUser.isMbaDba" value="N"/>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.recruitType" class="inputText recruitType">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyRecruitTypeList}" var="dict">
                                                    <%--去掉"是否推免生isRecommendId=1是2否"前，若是则招录方式recruitType=2为推免入学--%>
                                                    <option value="${dict.dictId}"
                                                            <c:choose>
                                                                <c:when test="${eduUser.isRecommendId eq '1'}">
                                                                    ${dict.dictId eq '2'?'selected':''}
                                                                </c:when>
                                                                <c:otherwise>
                                                                    ${extInfoForm.recruitType eq dict.dictId?'selected':''}
                                                                </c:otherwise>
                                                            </c:choose> >${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>
                                                <c:forEach items="${dictTypeEnumGyRecruitTypeList}" var="dict">
                                                    ${extInfoForm.recruitType eq dict.dictId? dict.dictName:''}
                                                </c:forEach>
                                            </label>
                                            <input type="hidden" name="eduUserExtInfoForm.recruitType" value="${extInfoForm.recruitType}">
                                        </c:if>
                                    </td>
                                    <th><span >是否破格：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.isExceptionalId" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyIsExceptionalList }" var="dict">
                                                    <option value="${dict.dictId }"
                                                            <c:if test="${eduUser.isExceptionalId eq dict.dictId }">selected</c:if>>${dict.dictName }</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <label>${eduUser.isExceptionalName}</label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >外国语名称：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.foreignLanguageName" value="${extInfoForm.foreignLanguageName }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.foreignLanguageName" value="${extInfoForm.foreignLanguageName }"/>
                                            <label>${extInfoForm.foreignLanguageName }</label>
                                        </c:if>
                                    </td>
                                    <th><span >外国语成绩：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.foreignLanguageScore" value="${extInfoForm.foreignLanguageScore }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.foreignLanguageScore" value="${extInfoForm.foreignLanguageScore }"/>
                                            <label>${extInfoForm.foreignLanguageScore }</label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >政治理论：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.politicalTheoryName" value="${extInfoForm.politicalTheoryName }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.politicalTheoryName" value="${extInfoForm.politicalTheoryName }"/>
                                            <label>${extInfoForm.politicalTheoryName }</label>
                                        </c:if>
                                    </td>
                                    <th><span >政治理论成绩：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.politicalTheoryScore" value="${extInfoForm.politicalTheoryScore }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.politicalTheoryScore" value="${extInfoForm.politicalTheoryScore }"/>
                                            <label>${extInfoForm.politicalTheoryScore }</label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >业务课一：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.firstSubjectName" value="${extInfoForm.firstSubjectName }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.firstSubjectName" value="${extInfoForm.firstSubjectName }"/>
                                            <label>${extInfoForm.firstSubjectName }</label>
                                        </c:if>
                                    </td>
                                    <th><span >业务课一成绩：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.firstSubjectScore" value="${extInfoForm.firstSubjectScore }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.firstSubjectScore" value="${extInfoForm.firstSubjectScore }"/>
                                            <label>${extInfoForm.firstSubjectScore }</label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >业务课二：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.secondSubjectName" value="${extInfoForm.secondSubjectName }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.secondSubjectName" value="${extInfoForm.secondSubjectName }"/>
                                            <label>${extInfoForm.secondSubjectName }</label>
                                        </c:if>
                                    </td>
                                    <th><span >业务课二成绩：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.secondSubjectScore" value="${extInfoForm.secondSubjectScore }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.secondSubjectScore" value="${extInfoForm.secondSubjectScore }"/>
                                            <label>${extInfoForm.secondSubjectScore }</label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >加试科一：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.firstAddExamName" value="${extInfoForm.firstAddExamName }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.firstAddExamName" value="${extInfoForm.firstAddExamName }"/>
                                            ${extInfoForm.firstAddExamName }
                                        </c:if>
                                    </td>
                                    <th><span >加试科一成绩：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.firstAddExamScore" value="${extInfoForm.firstAddExamScore }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.firstAddExamScore" value="${extInfoForm.firstAddExamScore }"/>
                                            <label>${extInfoForm.firstAddExamScore }</label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >加试科二：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.secondAddExamName" value="${extInfoForm.secondAddExamName }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.secondAddExamName" value="${extInfoForm.secondAddExamName }"/>
                                            <label>${extInfoForm.secondAddExamName }</label>
                                        </c:if>
                                    </td>
                                    <th><span >加试科二成绩：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.secondAddExamScore" value="${extInfoForm.secondAddExamScore }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.secondAddExamScore" value="${extInfoForm.secondAddExamScore }"/>
                                            <label>${extInfoForm.secondAddExamScore }</label>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >复试成绩：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.reexamineScore" value="${extInfoForm.reexamineScore }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.reexamineScore" value="${extInfoForm.reexamineScore }"/>
                                            <label>${extInfoForm.reexamineScore }</label>
                                        </c:if>
                                    </td>
                                    <th><span >总分：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.totalPointsScore" value="${extInfoForm.totalPointsScore }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="hidden" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.totalPointsScore" value="${extInfoForm.totalPointsScore }"/>
                                            <label>${extInfoForm.totalPointsScore }</label>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" hidden="hidden" style="margin-bottom:10px;" id="needInfo">
                        <input type="hidden" id="needStatus" name="needStatus" value="${param.needStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;必填信息--%>
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'}">
                                <span style="float:right;margin-right:100px;margin-top: 5px;margin-bottom: 5px;"><c:if test="${statusMap['NeedInfo'] ne 'Y'}"><a class="before">信息未确认，点击按钮确认</a><input type="button" class="search"  onclick="confirmStatus('NeedInfo',this)" value="确&#12288;认"/></c:if>
                        <c:if test="${statusMap['NeedInfo'] eq 'Y'}"><a class="after">信息已确认</a></c:if></span>
                            </c:if>
                            <%--<span style="float:right;margin-right:${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'?30:118}px;line-height:27px;color:blue;cursor:pointer;" onclick="needCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadOneThree">
                            <form id="spreadOneThreeForm">
                                <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                    <tr >
                                        <td rowspan="5" style="width:20%; text-align:center;margin-top: 2px;">
                                            <img src="${sysCfgMap['upload_base_url']}/${sysUser.userHeadImg}" id="showImg" width="100px;" height="130px;" onerror="this.src='<s:url value="/jsp/gyxjgl/images/up-pic.jpg"/>'"/>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <div style="height:20px;">[<a onclick="$('#imageFile').click();" style="cursor: pointer;">${empty sysUser.userHeadImg?'上传头像':'重新上传'}</a>]
                                                    <%--<span style="font-size:12px;line-height:16px">--%>
                                                        <%--<br>--%>
                                                        <%--注意：图片格式为:${pdfn:getSysCfg('inx_image_support_suffix')},--%>
                                                        <%--<br>--%>
                                                        <%--图片大小不过${pdfn:getSysCfg('inx_image_limit_size')}M--%>
                                                        <%--<br>--%>
                                                        <%--照片要求蓝底--%>
                                                    <%--</span>--%>
                                                    </div>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <div style="height:20px;">[<a onclick="$('#imageFile').click();" style="cursor: pointer;">${empty sysUser.userHeadImg?'上传头像':'重新上传'}</a>]
                                                <%--<span style="font-size:12px;line-height:16px">--%>
                                                    <%--<br>--%>
                                                    <%--注意：图片格式为:${pdfn:getSysCfg('inx_image_support_suffix')},--%>
                                                    <%--<br>--%>
                                                    <%--图片大小不过${pdfn:getSysCfg('inx_image_limit_size')}M--%>
                                                    <%--<br>--%>
                                                    <%--照片要求蓝底--%>
                                                <%--</span>--%>
                                                </div>
                                            </c:if>
                                            <input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
                                            <input id="img" type="hidden" name="sysUser.userHeadImg" value="${sysUser.userHeadImg}">
                                        </td>
                                        <td style="width: 30%" rowspan="5">
                                            <span style="font-size:14px;line-height:16px">
                                            <br>
                                            注意：图片格式为:${pdfn:getSysCfg('inx_image_support_suffix')},
                                            <br><br>
                                            图片大小不过${pdfn:getSysCfg('inx_image_limit_size')}M
                                            <br><br>
                                            照片要求蓝底
                                            </span>
                                        </td>
                                        <th style="width: 20%"><span style="color: red;">*</span>&#12288;<span >籍贯：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <div id="native">
                                                    <select id="nativePlaceProvId" name="sysUser.nativePlaceProvId" style="width: 140px;" class="notBlank province inputText validate[required]" data-value="${sysUser.nativePlaceProvId}" data-first-title="选择省"></select>
                                                    <select id="nativePlaceCityId" name="sysUser.nativePlaceCityId" style="width: 140px;" class="notBlank city inputText validate[required]" data-value="${sysUser.nativePlaceCityId}" data-first-title="选择市"></select>
                                                    <select id="nativePlaceAreaId" name="sysUser.nativePlaceAreaId" style="width: 140px;" class="notBlank area inputText validate[required]" data-value="${sysUser.nativePlaceAreaId}" data-first-title="选择地区"></select>
                                                </div>
                                                <input id="nativePlaceProvName" name="sysUser.nativePlaceProvName" type="hidden" value="${sysUser.nativePlaceProvName}">
                                                <input id="nativePlaceCityName" name="sysUser.nativePlaceCityName" type="hidden" value="${sysUser.nativePlaceCityName}">
                                                <input id="nativePlaceAreaName" name="sysUser.nativePlaceAreaName" type="hidden" value="${sysUser.nativePlaceAreaName}">
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
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <div id="native">
                                                        <select id="nativePlaceProvId" name="sysUser.nativePlaceProvId" style="width: 140px;" class="notBlank province inputText validate[required]" data-value="${sysUser.nativePlaceProvId}" data-first-title="选择省"></select>
                                                        <select id="nativePlaceCityId" name="sysUser.nativePlaceCityId" style="width: 140px;" class="notBlank city inputText validate[required]" data-value="${sysUser.nativePlaceCityId}" data-first-title="选择市"></select>
                                                        <select id="nativePlaceAreaId" name="sysUser.nativePlaceAreaId" style="width: 140px;" class="notBlank area inputText validate[required]" data-value="${sysUser.nativePlaceAreaId}" data-first-title="选择地区"></select>
                                                    </div>
                                                    <input id="nativePlaceProvName" name="sysUser.nativePlaceProvName" type="hidden" value="${sysUser.nativePlaceProvName}">
                                                    <input id="nativePlaceCityName" name="sysUser.nativePlaceCityName" type="hidden" value="${sysUser.nativePlaceCityName}">
                                                    <input id="nativePlaceAreaName" name="sysUser.nativePlaceAreaName" type="hidden" value="${sysUser.nativePlaceAreaName}">
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
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    ${sysUser.nativePlaceProvName}${sysUser.nativePlaceCityName}${sysUser.nativePlaceAreaName}
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <%--<td></td>--%>
                                        <th><span style="color:red;">*</span>&#12288;<span >婚姻状况：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="sysUser.maritalId" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <c:forEach items="${marriageTypeEnumList}" var="marital">
                                                        <option value="${marital.id}" ${sysUser.maritalId eq marital.id?'selected':''}>${marital.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="sysUser.maritalId" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <c:forEach items="${marriageTypeEnumList}" var="marital">
                                                            <option value="${marital.id}" ${sysUser.maritalId eq marital.id?'selected':''}>${marital.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${sysUser.maritalName}</label>

                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <%--<td></td>--%>
                                        <th><span style="color: red;">*</span>&#12288;<span >生育状况：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" name="sysUser.bearName" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" value="${sysUser.bearName }">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" name="sysUser.bearName" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" value="${sysUser.bearName }">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${sysUser.bearName }</label>
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <%--<td></td>--%>
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
                                        <%--<td></td>--%>
                                        <th><span class="redStar" style="color: red;">*</span>&#12288;<span >入党（团）时间：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="notBlank inputText ctime validate[required] needFlag" name="eduUserExtInfoForm.joinOrgTime" value="${extInfoForm.joinOrgTime }">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 140px;text-align: left;" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="notBlank inputText ctime validate[required] needFlag" name="eduUserExtInfoForm.joinOrgTime" value="${extInfoForm.joinOrgTime }">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.joinOrgTime }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.joinOrgTime" value="${extInfoForm.joinOrgTime}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th style="width:20%"><span class="redStar" style="color: red;">*</span>&#12288;<span >党团关系是否转入：</span></th>
                                        <td style="width:30%">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" id="isRelationInto" name="eduUserExtInfoForm.isRelationInto" class="notBlank inputText validate[required] needFlag">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isRelationInto eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isRelationInto eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" id="isRelationInto" name="eduUserExtInfoForm.isRelationInto" class="notBlank inputText validate[required] needFlag">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isRelationInto eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isRelationInto eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isRelationInto==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isRelationInto==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" id="isRelationInto" name="eduUserExtInfoForm.isRelationInto" value="${extInfoForm.isRelationInto}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th style="width:20%;" class="joinTimeTd"><span class="redStar" style="color: red;">*</span>&#12288;<span >转入年月日：</span></th>
                                        <td style="width:30%" class="joinTimeTd">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="notBlank inputText ctime validate[required] needFlag" name="eduUserExtInfoForm.joinTime" value="${extInfoForm.joinTime }">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <label>${empty extInfoForm.joinTime?'等待老师录入':extInfoForm.joinTime}</label>
                                                <input type="hidden" name="eduUserExtInfoForm.joinTime" value="${extInfoForm.joinTime}">
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >入学前户口所在地：</span></th>
                                        <td colspan="3">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <div id="domicileName">
                                                    <select id="domicilePlaceId" name="sysUser.domicilePlaceId" style="width: 140px;" class="notBlank province inputText validate[required]" data-value="${domicileId[0]}" data-first-title="选择省"></select>
                                                    <select id="domicileCityId" name="sysUser.domicilePlaceId" style="width: 140px;" class="notBlank city inputText validate[required]" data-value="${domicileId[1]}" data-first-title="选择市"></select>
                                                    <select id="domicileAreaId" name="sysUser.domicilePlaceId" style="width: 140px;" class="notBlank area inputText validate[required]" data-value="${domicileId[2]}" data-first-title="选择地区"></select>
                                                </div>
                                                <input id="domicilePlaceName" name="sysUser.domicilePlaceName" type="hidden" value="${domicileName[0]}">
                                                <input id="domicileCityName" name="sysUser.domicilePlaceName" type="hidden" value="${domicileName[1]}">
                                                <input id="domicileAreaName" name="sysUser.domicilePlaceName" type="hidden" value="${domicileName[2]}">
                                                <script type="text/javascript">
                                                    // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                                                    $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                                                    $.cxSelect.defaults.nodata = "none";

                                                    $("#domicileName").cxSelect({
                                                        selects: ["province", "city", "area"],
                                                        nodata: "none",
                                                        firstValue: ""
                                                    });
                                                </script>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <div id="domicileName">
                                                        <select id="domicilePlaceId" name="sysUser.domicilePlaceId" style="width: 140px;" class="notBlank province inputText validate[required]" data-value="${domicileId[0]}" data-first-title="选择省"></select>
                                                        <select id="domicileCityId" name="sysUser.domicilePlaceId" style="width: 140px;" class="notBlank city inputText validate[required]" data-value="${domicileId[1]}" data-first-title="选择市"></select>
                                                        <select id="domicileAreaId" name="sysUser.domicilePlaceId" style="width: 140px;" class="notBlank area inputText validate[required]" data-value="${domicileId[2]}" data-first-title="选择地区"></select>
                                                    </div>
                                                    <input id="domicilePlaceName" name="sysUser.domicilePlaceName" type="hidden" value="${domicileName[0]}">
                                                    <input id="domicileCityName" name="sysUser.domicilePlaceName" type="hidden" value="${domicileName[1]}">
                                                    <input id="domicileAreaName" name="sysUser.domicilePlaceName" type="hidden" value="${domicileName[2]}">
                                                    <script type="text/javascript">
                                                        // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                                                        $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                                                        $.cxSelect.defaults.nodata = "none";

                                                        $("#domicileName").cxSelect({
                                                            selects: ["province", "city", "area"],
                                                            nodata: "none",
                                                            firstValue: ""
                                                        });
                                                    </script>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    ${domicileName[0]}${domicileName[1]}${domicileName[2]}
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th style="white-space:nowrap;"><span style="color: red;">*</span>&#12288;<span >入学前户口所在详细地址：</span></th>
                                        <td colspan="3">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 430px;text-align: left;" name="sysUser.domicilePlaceAddress" value="${sysUser.domicilePlaceAddress }"/>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" class="notBlank inputText validate[required]" style="width: 430px;text-align: left;" name="sysUser.domicilePlaceAddress" value="${sysUser.domicilePlaceAddress }"/>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${sysUser.domicilePlaceAddress }</label>
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >户口是否需要迁入我校：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.hkInSchool" class="notBlank inputText validate[required] hkInSchool">
                                                    <option/>
                                                    <option value="1" ${extInfoForm.hkInSchool eq '1'?'selected':''}>是</option>
                                                    <option value="2" ${extInfoForm.hkInSchool eq '2'?'selected':''}>否</option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.hkInSchool" class="notBlank inputText validate[required] hkInSchool">
                                                        <option/>
                                                        <option value="1" ${extInfoForm.hkInSchool eq '1'?'selected':''}>是</option>
                                                        <option value="2" ${extInfoForm.hkInSchool eq '2'?'selected':''}>否</option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>
                                                        <c:if test="${extInfoForm.hkInSchool eq '1'}">是</c:if>
                                                        <c:if test="${extInfoForm.hkInSchool eq '2'}">否</c:if>
                                                        <input type="hidden" class="hkInSchool" name="eduUserExtInfoForm.hkInSchool" value="${extInfoForm.hkInSchool}">
                                                    </label>
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th class="hkChangeNoTd"><span style="color: red;">*</span>&#12288;<span >户口迁移证编号：</span></th>
                                        <td class="hkChangeNoTd">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" class="notBlank inputText validate[required] hkChangeNo" style="width:140px;text-align: left;" placeholder="广州市填无" name="eduUserExtInfoForm.hkChangeNo" value="${extInfoForm.hkChangeNo }"/>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" class="notBlank inputText validate[required] hkChangeNo" style="width:140px;text-align: left;" placeholder="广州市填无" name="eduUserExtInfoForm.hkChangeNo" value="${extInfoForm.hkChangeNo }"/>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.hkChangeNo }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.hkChangeNo" value="${extInfoForm.hkChangeNo}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >原学习或工作单位：</span></th>
                                        <td colspan="3">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" class="notBlank inputText validate[required]" style="width:140px;text-align: left;" name="eduUserExtInfoForm.oldOrgName" value="${extInfoForm.oldOrgName }"/>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" class="notBlank inputText validate[required]" style="width:140px;text-align: left;" name="eduUserExtInfoForm.oldOrgName" value="${extInfoForm.oldOrgName }"/>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.oldOrgName }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.oldOrgName" value="${extInfoForm.oldOrgName}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >原档案所在单位：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.oldFileLocationOrg" value="${extInfoForm.oldFileLocationOrg }"/>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.oldFileLocationOrg" value="${extInfoForm.oldFileLocationOrg }"/>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.oldFileLocationOrg }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.oldFileLocationOrg" value="${extInfoForm.oldFileLocationOrg}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th style="width:20%;"><span style="color: red;">*</span>&#12288;<span >原档案所在单位邮编：</span></th>
                                        <td style="width:30%;">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.oldFileLocationOrgCode" value="${extInfoForm.oldFileLocationOrgCode }"/>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.oldFileLocationOrgCode" value="${extInfoForm.oldFileLocationOrgCode }"/>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.oldFileLocationOrgCode }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.oldFileLocationOrgCode" value="${extInfoForm.oldFileLocationOrgCode}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >档案所在地：</span></th>
                                        <td colspan="3">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <div id="recordLocationName">
                                                    <select id="recordPlaceId" name="eduUser.recordLocationId" style="width: 140px;" class="notBlank province inputText validate[required]" data-value="${recordId[0]}" data-first-title="选择省"></select>
                                                    <select id="recordCityId" name="eduUser.recordLocationId" style="width: 140px;" class="notBlank city inputText validate[required]" data-value="${recordId[1]}" data-first-title="选择市"></select>
                                                    <select id="recordAreaId" name="eduUser.recordLocationId" style="width: 140px;" class="notBlank area inputText validate[required]" data-value="${recordId[2]}" data-first-title="选择地区"></select>
                                                </div>
                                                <input id="recordPlaceName" name="eduUser.recordLocationName" type="hidden" value="${recordName[0]}">
                                                <input id="recordCityName" name="eduUser.recordLocationName" type="hidden" value="${recordName[1]}">
                                                <input id="recordAreaName" name="eduUser.recordLocationName" type="hidden" value="${recordName[2]}">
                                                <script type="text/javascript">
                                                    // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                                                    $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                                                    $.cxSelect.defaults.nodata = "none";

                                                    $("#recordLocationName").cxSelect({
                                                        selects: ["province", "city", "area"],
                                                        nodata: "none",
                                                        firstValue: ""
                                                    });
                                                </script>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <div id="recordLocationName">
                                                        <select id="recordPlaceId" name="eduUser.recordLocationId" style="width: 140px;" class="notBlank province inputText validate[required]" data-value="${recordId[0]}" data-first-title="选择省"></select>
                                                        <select id="recordCityId" name="eduUser.recordLocationId" style="width: 140px;" class="notBlank city inputText validate[required]" data-value="${recordId[1]}" data-first-title="选择市"></select>
                                                        <select id="recordAreaId" name="eduUser.recordLocationId" style="width: 140px;" class="notBlank area inputText validate[required]" data-value="${recordId[2]}" data-first-title="选择地区"></select>
                                                    </div>
                                                    <input id="recordPlaceName" name="eduUser.recordLocationName" type="hidden" value="${recordName[0]}">
                                                    <input id="recordCityName" name="eduUser.recordLocationName" type="hidden" value="${recordName[1]}">
                                                    <input id="recordAreaName" name="eduUser.recordLocationName" type="hidden" value="${recordName[2]}">
                                                    <script type="text/javascript">
                                                        // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                                                        $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                                                        $.cxSelect.defaults.nodata = "none";

                                                        $("#recordLocationName").cxSelect({
                                                            selects: ["province", "city", "area"],
                                                            nodata: "none",
                                                            firstValue: ""
                                                        });
                                                    </script>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    ${recordName[0]}${recordName[1]}${recordName[2]}
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >现家庭住址：</span></th>
                                        <td colspan="3">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 430px;text-align: left;" class="notBlank inputText validate[required]" name="eduUserExtInfoForm.nowResideAddress" value="${extInfoForm.nowResideAddress }">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 430px;text-align: left;" class="notBlank inputText validate[required]" name="eduUserExtInfoForm.nowResideAddress" value="${extInfoForm.nowResideAddress }">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.nowResideAddress }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.nowResideAddress" value="${extInfoForm.nowResideAddress}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >紧急联系人姓名：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" name="eduUserExtInfoForm.linkMan" value="${extInfoForm.linkMan }">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" name="eduUserExtInfoForm.linkMan" value="${extInfoForm.linkMan }">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    ${extInfoForm.linkMan }
                                                    <input type="hidden" name="eduUserExtInfoForm.linkMan" value="${extInfoForm.linkMan}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >紧急联系人电话：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" name="eduUserExtInfoForm.telephone" value="${extInfoForm.telephone }">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" name="eduUserExtInfoForm.telephone" value="${extInfoForm.telephone }">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.telephone }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.telephone" value="${extInfoForm.telephone}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否有医师资格证：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isDoctorQuaCert" class="notBlank inputText validate[required] quaCert">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isDoctorQuaCert eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isDoctorQuaCert eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isDoctorQuaCert" class="notBlank inputText validate[required] quaCert">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isDoctorQuaCert eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isDoctorQuaCert eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isDoctorQuaCert==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isDoctorQuaCert==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" class="quaCert" name="eduUserExtInfoForm.isDoctorQuaCert" value="${extInfoForm.isDoctorQuaCert}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th class="quaCertNoTd"><span style="color: red;">*</span>&#12288;<span >资格证编号：</span></th>
                                        <td class="quaCertNoTd">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required] quaCertNo" name="eduUserExtInfoForm.codeDoctorQuaCert" value="${extInfoForm.codeDoctorQuaCert}">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required] quaCertNo" name="eduUserExtInfoForm.codeDoctorQuaCert" value="${extInfoForm.codeDoctorQuaCert}">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.codeDoctorQuaCert}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.codeDoctorQuaCert" value="${extInfoForm.codeDoctorQuaCert}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th class="quaCertNoFillTd" hidden="hidden"></th>
                                        <td class="quaCertNoFillTd" hidden="hidden"></td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否有执业医师执照：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isMedicalPractitioner" class="notBlank inputText validate[required] zyCert">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isMedicalPractitioner eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isMedicalPractitioner eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isMedicalPractitioner" class="notBlank inputText validate[required] zyCert">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isMedicalPractitioner eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isMedicalPractitioner eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isMedicalPractitioner==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isMedicalPractitioner==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" class="zyCert" name="eduUserExtInfoForm.isMedicalPractitioner" value="${extInfoForm.isMedicalPractitioner}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th class="zyCertNoTd"><span style="color: red;">*</span>&#12288;<span >执照编号：</span></th>
                                        <td class="zyCertNoTd">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required] zyCertNo" name="eduUserExtInfoForm.codeMedicalPractitioner" value="${extInfoForm.codeMedicalPractitioner}">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required] zyCertNo" name="eduUserExtInfoForm.codeMedicalPractitioner" value="${extInfoForm.codeMedicalPractitioner}">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.codeMedicalPractitioner}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.codeMedicalPractitioner" value="${extInfoForm.codeMedicalPractitioner}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th class="zyCertNoFillTd" hidden="hidden"></th>
                                        <td class="zyCertNoFillTd" hidden="hidden"></td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否住宿：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isStay" class="notBlank inputText validate[required] isStay">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isStay eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isStay eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isStay" class="notBlank inputText validate[required] isStay">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isStay eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isStay eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isStay==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isStay==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" class="isStay" name="eduUserExtInfoForm.isStay" value="${extInfoForm.isStay}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th class="roomNumStayTd">&#12288;<span >宿舍号：</span></th>
                                        <td class="roomNumStayTd">
                                            <%--<c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">--%>
                                            <c:if test="${!isFeeMaster}">
                                                <input type="text" class="notBlank inputText roomNumStay" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.roomNumStay" value="${extInfoForm.roomNumStay }">
                                            </c:if>
                                            <%--<c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">--%>
                                                <%--<label id="roomNum">${extInfoForm.roomNumStay }</label>--%>
                                                <%--<input type="hidden" name="eduUserExtInfoForm.roomNumStay" value="${extInfoForm.roomNumStay}">--%>
                                            <%--</c:if>--%>
                                        </td>
                                        <th class="roomNumStayFillTd" hidden="hidden"></th>
                                        <td class="roomNumStayFillTd" hidden="hidden"></td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >本人手机号码：</span></th>
                                        <td>
                                            <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" name="sysUser.userPhone" value="${sysUser.userPhone }">
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >电子邮箱：</span></th>
                                        <td>
                                            <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" name="sysUser.userEmail" value="${sysUser.userEmail }">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >微信号：</span></th>
                                        <td>
                                            <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" name="sysUser.weiXinName" value="${sysUser.weiXinName }">
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >QQ号：</span></th>
                                        <td>
                                            <input type="text" name="sysUser.userQq" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" value="${sysUser.userQq }">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >身高：</span></th>
                                        <td>
                                            <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" name="eduUserExtInfoForm.height" value="${extInfoForm.height }">
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >血型：</span></th>
                                        <td>
                                            <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" name="eduUserExtInfoForm.bloodType" value="${extInfoForm.bloodType }">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>&#12288;<span >医保卡号：</span></th>
                                        <td>
                                            <%--<c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">--%>
                                            <c:if test="${!FeeMaster}">
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText" name="eduUserExtInfoForm.ybCardNo" value="${extInfoForm.ybCardNo }">
                                            </c:if>
                                            <%--<c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">--%>
                                                <%--<label ${empty extInfoForm.ybCardNo?"style='color:gray;'":""}>${empty extInfoForm.ybCardNo?"等待老师导入":extInfoForm.ybCardNo}</label>--%>
                                                <%--<input type="hidden" name="eduUserExtInfoForm.ybCardNo" value="${extInfoForm.ybCardNo}"/>--%>
                                            <%--</c:if>--%>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否农村学生：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isRuralStudents" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isRuralStudents eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isRuralStudents eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isRuralStudents" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isRuralStudents eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isRuralStudents eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isRuralStudents==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isRuralStudents==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isRuralStudents" value="${extInfoForm.isRuralStudents}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>

                                        <th><span style="color: red;">*</span>&#12288;<span >是否申请贫困生：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" id="isPoorStudents" name="eduUserExtInfoForm.isPoorStudents" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isPoorStudents eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isPoorStudents eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" id="isPoorStudents" name="eduUserExtInfoForm.isPoorStudents" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isPoorStudents eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isPoorStudents eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label id="isPoorStudents">
                                                        <c:if test="${extInfoForm.isPoorStudents==GlobalConstant.FLAG_Y}">是</c:if>
                                                        <c:if test="${extInfoForm.isPoorStudents==GlobalConstant.FLAG_N}">否</c:if>
                                                    </label>
                                                    <input type="hidden" name="eduUserExtInfoForm.isPoorStudents" value="${extInfoForm.isPoorStudents}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th></th>
                                        <td></td>
                                    </tr>
                                    <tbody id="poorStudentsTbody">
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否有固定收入：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isFixedIncome" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isFixedIncome eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isFixedIncome eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isFixedIncome" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isFixedIncome eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isFixedIncome eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isFixedIncome==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isFixedIncome==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isFixedIncome" value="${extInfoForm.isFixedIncome}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否五保户：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isFiveGuarantees" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isFiveGuarantees eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isFiveGuarantees eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isFiveGuarantees" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isFiveGuarantees eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isFiveGuarantees eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isFiveGuarantees==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isFiveGuarantees==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isFiveGuarantees" value="${extInfoForm.isFiveGuarantees}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否低保：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isLowGuarantees" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isLowGuarantees eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isLowGuarantees eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isLowGuarantees" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isLowGuarantees eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isLowGuarantees eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isLowGuarantees==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isLowGuarantees==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isLowGuarantees" value="${extInfoForm.isLowGuarantees}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否扶贫户：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isPoor" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isPoor eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isPoor eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isPoor" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isPoor eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isPoor eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isPoor==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isPoor==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isPoor" value="${extInfoForm.isPoor}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否孤儿：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isOrphan" class="notBlank inputText validate[required] isOrphan" onchange="orphanBindSingle('isOrphan')">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isOrphan eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isOrphan eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isOrphan" class="notBlank inputText validate[required] isOrphan" onchange="orphanBindSingle('isOrphan')">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isOrphan eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isOrphan eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isOrphan==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isOrphan==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isOrphan" value="${extInfoForm.isOrphan}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否单亲家庭子女：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isSingleParent" class="notBlank inputText validate[required] isSingleParent" onchange="orphanBindSingle('isSingleParent')">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isSingleParent eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isSingleParent eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isSingleParent" class="notBlank inputText validate[required] isSingleParent" onchange="orphanBindSingle('isSingleParent')">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isSingleParent eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isSingleParent eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isSingleParent==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isSingleParent==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isSingleParent" value="${extInfoForm.isSingleParent}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否残疾人子女：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isDisabledChildren" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isDisabledChildren eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isDisabledChildren eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isDisabledChildren" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isDisabledChildren eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isDisabledChildren eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isDisabledChildren==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isDisabledChildren==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isDisabledChildren" value="${extInfoForm.isDisabledChildren}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >本人是否残疾：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" id="userDisabled" name="eduUserExtInfoForm.isDisabled" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isDisabled eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isDisabled eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" id="userDisabled" name="eduUserExtInfoForm.isDisabled" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isDisabled eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isDisabled eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isDisabled==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isDisabled==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isDisabled" value="${extInfoForm.isDisabled}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th id="userDisabledLevelFileTh" hidden="hidden"></th>
                                        <td id="userDisabledLevelFileTd" hidden="hidden"></td>
                                        <th id="userDisabledLevelTh"><span style="color: red;">*</span>&#12288;<span >残疾类别：</span></th>
                                        <td id="userDisabledLevelTd">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isDisabledLevel" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="1" ${extInfoForm.isDisabledLevel eq '1'?'selected':''}>视力残疾</option>
                                                    <option value="2" ${extInfoForm.isDisabledLevel eq '2'?'selected':''}>听力残疾</option>
                                                    <option value="3" ${extInfoForm.isDisabledLevel eq '3'?'selected':''}>智力残疾</option>
                                                    <option value="4" ${extInfoForm.isDisabledLevel eq '4'?'selected':''}>其他残疾</option>
                                                    <%--<option value="5" ${extInfoForm.isDisabledLevel eq '5'?'selected':''}>否</option>--%>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isDisabledLevel" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="1" ${extInfoForm.isDisabledLevel eq '1'?'selected':''}>视力残疾</option>
                                                        <option value="2" ${extInfoForm.isDisabledLevel eq '2'?'selected':''}>听力残疾</option>
                                                        <option value="3" ${extInfoForm.isDisabledLevel eq '3'?'selected':''}>智力残疾</option>
                                                        <option value="4" ${extInfoForm.isDisabledLevel eq '4'?'selected':''}>其他残疾</option>
                                                        <%--<option value="5" ${extInfoForm.isDisabledLevel eq '5'?'selected':''}>否</option>--%>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                        <c:if test="${extInfoForm.isDisabledLevel eq '1'}">视力残疾</c:if>
                                                        <c:if test="${extInfoForm.isDisabledLevel eq '2'}">听力残疾</c:if>
                                                        <c:if test="${extInfoForm.isDisabledLevel eq '3'}">智力残疾</c:if>
                                                        <c:if test="${extInfoForm.isDisabledLevel eq '4'}">其他残疾</c:if>
                                                        <%--<c:if test="${extInfoForm.isDisabledLevel eq '5'}">否</c:if>--%>
                                                    <input type="hidden" name="eduUserExtInfoForm.isDisabledLevel" value="${extInfoForm.isDisabledLevel}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否父母丧失劳动能力：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isLostAbilityParent" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isLostAbilityParent eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isLostAbilityParent eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isLostAbilityParent" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isLostAbilityParent eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isLostAbilityParent eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isLostAbilityParent==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isLostAbilityParent==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isLostAbilityParent" value="${extInfoForm.isLostAbilityParent}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否家中有大病患者：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isSeriousIllness" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isSeriousIllness eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isSeriousIllness eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isSeriousIllness" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isSeriousIllness eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isSeriousIllness eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isSeriousIllness==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isSeriousIllness==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isSeriousIllness" value="${extInfoForm.isSeriousIllness}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否建档立卡的贫困户：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isFilingRiser" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isFilingRiser eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isFilingRiser eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isFilingRiser" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isFilingRiser eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isFilingRiser eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isFilingRiser==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isFilingRiser==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isFilingRiser" value="${extInfoForm.isFilingRiser}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否低收入家庭：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isLowIncomeFamilies" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isLowIncomeFamilies eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isLowIncomeFamilies eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isLowIncomeFamilies" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isLowIncomeFamilies eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isLowIncomeFamilies eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isLowIncomeFamilies==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isLowIncomeFamilies==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isLowIncomeFamilies" value="${extInfoForm.isLowIncomeFamilies}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否军烈属或优抚子女：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isSpecialCareChildren" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isSpecialCareChildren eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isSpecialCareChildren eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isSpecialCareChildren" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isSpecialCareChildren eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isSpecialCareChildren eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isSpecialCareChildren==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isSpecialCareChildren==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isSpecialCareChildren" value="${extInfoForm.isSpecialCareChildren}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >家庭人均年收入：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[999999.99]]" name="eduUserExtInfoForm.annualIncome" value="${extInfoForm.annualIncome}" onchange="toDecimal2(this)">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[999999.99]]" name="eduUserExtInfoForm.annualIncome" value="${extInfoForm.annualIncome}" onchange="toDecimal2(this)">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.annualIncome}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.annualIncome" value="${extInfoForm.annualIncome}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >家庭主要收入来源类型：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" id="incomeType" name="eduUserExtInfoForm.majorSourceIncome" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="1" ${extInfoForm.majorSourceIncome eq '1'?'selected':''}>工资</option>
                                                    <option value="2" ${extInfoForm.majorSourceIncome eq '2'?'selected':''}>奖金</option>
                                                    <option value="3" ${extInfoForm.majorSourceIncome eq '3'?'selected':''}>津贴</option>
                                                    <option value="4" ${extInfoForm.majorSourceIncome eq '4'?'selected':''}>补贴</option>
                                                    <option value="5" ${extInfoForm.majorSourceIncome eq '5'?'selected':''}>其他劳动收入</option>
                                                    <option value="6" ${extInfoForm.majorSourceIncome eq '6'?'selected':''}>自谋职业收入</option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" id="incomeType" name="eduUserExtInfoForm.majorSourceIncome" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="1" ${extInfoForm.majorSourceIncome eq '1'?'selected':''}>工资</option>
                                                        <option value="2" ${extInfoForm.majorSourceIncome eq '2'?'selected':''}>奖金</option>
                                                        <option value="3" ${extInfoForm.majorSourceIncome eq '3'?'selected':''}>津贴</option>
                                                        <option value="4" ${extInfoForm.majorSourceIncome eq '4'?'selected':''}>补贴</option>
                                                        <option value="5" ${extInfoForm.majorSourceIncome eq '5'?'selected':''}>其他劳动收入</option>
                                                        <option value="6" ${extInfoForm.majorSourceIncome eq '6'?'selected':''}>自谋职业收入</option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.majorSourceIncome eq '1'}">工资</c:if>
                                                    <c:if test="${extInfoForm.majorSourceIncome eq '2'}">奖金</c:if>
                                                    <c:if test="${extInfoForm.majorSourceIncome eq '3'}">津贴</c:if>
                                                    <c:if test="${extInfoForm.majorSourceIncome eq '4'}">补贴</c:if>
                                                    <c:if test="${extInfoForm.majorSourceIncome eq '5'}">其他劳动收入</c:if>
                                                    <c:if test="${extInfoForm.majorSourceIncome eq '6'}">自谋职业收入</c:if>
                                                    <c:if test="${extInfoForm.majorSourceIncome eq '5' || extInfoForm.majorSourceIncome eq '6'}">&#12288;${extInfoForm.majorSourceIncomeDesc}</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.majorSourceIncome" value="${extInfoForm.majorSourceIncome}">
                                                    <input type="hidden" name="eduUserExtInfoForm.majorSourceIncomeDesc" value="${extInfoForm.majorSourceIncomeDesc}">
                                                </c:if>
                                            </c:if>
                                            <span id="incomeSpan">
                                                &#12288;
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required]" name="eduUserExtInfoForm.majorSourceIncomeDesc" value="${extInfoForm.majorSourceIncomeDesc}">
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >家庭是否遭受自然灾害：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" id="naturalDisasters" name="eduUserExtInfoForm.isSufferNaturalDisasters" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isSufferNaturalDisasters eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isSufferNaturalDisasters eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" id="naturalDisasters" name="eduUserExtInfoForm.isSufferNaturalDisasters" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isSufferNaturalDisasters eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isSufferNaturalDisasters eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isSufferNaturalDisasters==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isSufferNaturalDisasters==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isSufferNaturalDisasters" value="${extInfoForm.isSufferNaturalDisasters}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th class="naturalDisastersTd"><span style="color: red;">*</span>&#12288;<span >自然灾害具体情况描述：</span></th>
                                        <td class="naturalDisastersTd">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <textarea placeholder="可填无" style="width:96%;height:30px;margin:4px;" id="naturalDisastersDesc" name="eduUserExtInfoForm.naturalDisastersDesc" class="validate[required]" maxlength="100">${extInfoForm.naturalDisastersDesc}</textarea>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <textarea placeholder="可填无" style="width:96%;height:30px;margin:4px;" id="naturalDisastersDesc" name="eduUserExtInfoForm.naturalDisastersDesc" class="validate[required]" maxlength="100">${extInfoForm.naturalDisastersDesc}</textarea>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.naturalDisastersDesc}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.naturalDisastersDesc" value="${extInfoForm.naturalDisastersDesc}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th class="naturalDisastersFillTd" hidden="hidden"></th>
                                        <td class="naturalDisastersFillTd" hidden="hidden"></td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >家庭遭受突发意外事件：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" id="emergency" name="eduUserExtInfoForm.isSufferEmergency" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isSufferEmergency eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isSufferEmergency eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" id="emergency" name="eduUserExtInfoForm.isSufferEmergency" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isSufferEmergency eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isSufferEmergency eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isSufferEmergency==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isSufferEmergency==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isSufferEmergency" value="${extInfoForm.isSufferEmergency}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th class="emergencyTd"><span style="color: red;">*</span>&#12288;<span >突发意外事件具体描述：</span></th>
                                        <td class="emergencyTd">
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <textarea placeholder="可填无" style="width:96%;height:30px;margin:4px;" name="eduUserExtInfoForm.emergencyDesc" class="validate[required]" maxlength="100">${extInfoForm.emergencyDesc}</textarea>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <textarea placeholder="可填无" style="width:96%;height:30px;margin:4px;" name="eduUserExtInfoForm.emergencyDesc" class="validate[required]" maxlength="100">${extInfoForm.emergencyDesc}</textarea>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.emergencyDesc}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.emergencyDesc" value="${extInfoForm.emergencyDesc}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th class="emergencyFillTd" hidden="hidden"></th>
                                        <td class="emergencyFillTd" hidden="hidden"></td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >家庭欠债金额：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[999999.99]]" name="eduUserExtInfoForm.debtMoney" value="${extInfoForm.debtMoney}" onchange="toDecimal2(this)">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[999999.99]]" name="eduUserExtInfoForm.debtMoney" value="${extInfoForm.debtMoney}" onchange="toDecimal2(this)">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.debtMoney}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.debtMoney" value="${extInfoForm.debtMoney}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >家庭欠债原因：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <textarea placeholder="可填无"  style="width:96%;height:30px;margin:4px;" name="eduUserExtInfoForm.debtReason" class="validate[required]" maxlength="60">${extInfoForm.debtReason}</textarea>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <textarea placeholder="可填无"  style="width:96%;height:30px;margin:4px;" name="eduUserExtInfoForm.debtReason" class="validate[required]" maxlength="60">${extInfoForm.debtReason}</textarea>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.debtReason}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.debtReason" value="${extInfoForm.debtReason}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >家庭人口数：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[99]]" name="eduUserExtInfoForm.familyPopulation" value="${extInfoForm.familyPopulation}" onchange="toDecimal(this)">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[99]]" name="eduUserExtInfoForm.familyPopulation" value="${extInfoForm.familyPopulation}" onchange="toDecimal(this)">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.familyPopulation}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.familyPopulation" value="${extInfoForm.familyPopulation}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >劳动力人口数：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[99]]" name="eduUserExtInfoForm.workingPopulation" value="${extInfoForm.workingPopulation}" onchange="toDecimal(this)">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[99]]" name="eduUserExtInfoForm.workingPopulation" value="${extInfoForm.workingPopulation}" onchange="toDecimal(this)">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.workingPopulation}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.workingPopulation" value="${extInfoForm.workingPopulation}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >家庭成员失业人数：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[99]]" name="eduUserExtInfoForm.unemployedPopulation" value="${extInfoForm.unemployedPopulation}" onchange="toDecimal(this)">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[99]]" name="eduUserExtInfoForm.unemployedPopulation" value="${extInfoForm.unemployedPopulation}" onchange="toDecimal(this)">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.unemployedPopulation}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.unemployedPopulation" value="${extInfoForm.unemployedPopulation}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >赡养人口数：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[99]]" name="eduUserExtInfoForm.supportPopulation" value="${extInfoForm.supportPopulation}" onchange="toDecimal(this)">
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <input type="text" style="width: 140px;text-align: left;" class="notBlank inputText validate[required,custom[number],min[0],max[99]]" name="eduUserExtInfoForm.supportPopulation" value="${extInfoForm.supportPopulation}" onchange="toDecimal(this)">
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.supportPopulation}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.supportPopulation" value="${extInfoForm.supportPopulation}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span style="color: red;">*</span>&#12288;<span >其他信息：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <textarea placeholder="可填无"  style="width:96%;height:30px;margin:4px;" name="eduUserExtInfoForm.otherDisastersDesc" class="validate[required]" maxlength="100">${extInfoForm.otherDisastersDesc}</textarea>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <textarea placeholder="可填无"  style="width:96%;height:30px;margin:4px;" name="eduUserExtInfoForm.otherDisastersDesc" class="validate[required]" maxlength="100">${extInfoForm.otherDisastersDesc}</textarea>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.otherDisastersDesc}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.otherDisastersDesc" value="${extInfoForm.otherDisastersDesc}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >是否农村低保户：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isRuralLowInsured" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isRuralLowInsured eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isRuralLowInsured eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isRuralLowInsured" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isRuralLowInsured eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isRuralLowInsured eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isRuralLowInsured==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isRuralLowInsured==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isRuralLowInsured" value="${extInfoForm.isRuralLowInsured}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    <tr>

                                        <th><span style="color: red;">*</span>&#12288;<span >是否农村特困供养：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.isRuralPoverty" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isRuralPoverty eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isRuralPoverty eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.isRuralPoverty" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="${GlobalConstant.FLAG_Y}" ${extInfoForm.isRuralPoverty eq GlobalConstant.FLAG_Y?'selected':''}>
                                                            是
                                                        </option>
                                                        <option value="${GlobalConstant.FLAG_N}" ${extInfoForm.isRuralPoverty eq GlobalConstant.FLAG_N?'selected':''}>
                                                            否
                                                        </option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <c:if test="${extInfoForm.isRuralPoverty==GlobalConstant.FLAG_Y}">是</c:if>
                                                    <c:if test="${extInfoForm.isRuralPoverty==GlobalConstant.FLAG_N}">否</c:if>
                                                    <input type="hidden" name="eduUserExtInfoForm.isRuralPoverty" value="${extInfoForm.isRuralPoverty}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span >其他：</span></th>
                                        <td>
                                            <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                                <textarea placeholder="可填无"  style="width:96%;height:30px;margin:4px;" name="eduUserExtInfoForm.otherDifficultyDesc" class="validate[required]" maxlength="40">${extInfoForm.otherDifficultyDesc}</textarea>
                                            </c:if>
                                            <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                                <c:if test="${requiredFlag}">
                                                    <textarea placeholder="可填无"  style="width:96%;height:30px;margin:4px;" name="eduUserExtInfoForm.otherDifficultyDesc" class="validate[required]" maxlength="40">${extInfoForm.otherDifficultyDesc}</textarea>
                                                </c:if>
                                                <c:if test="${!requiredFlag}">
                                                    <label>${extInfoForm.otherDifficultyDesc}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.otherDifficultyDesc" value="${extInfoForm.otherDifficultyDesc}">
                                                </c:if>
                                            </c:if>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                    <div class="spreadOne" hidden="hidden" style="margin-bottom:10px;" id="selectInfo">
                        <input type="hidden" id="selectStatus" name="selectStatus" value="${param.selectStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;选填信息--%>
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'}">
                                <span style="float:right;margin-right:100px;margin-top: 5px;margin-bottom: 5px;"><c:if test="${statusMap['SelectInfo'] ne 'Y'}"><a class="before">信息未确认，点击按钮确认</a><input type="button" class="search"  onclick="confirmStatus('SelectInfo',this)" value="确&#12288;认"/></c:if>
                                <c:if test="${statusMap['SelectInfo'] eq 'Y'}"><a class="after">信息已确认</a></c:if></span>
                            </c:if>
                            <%--<span style="float:right;margin-right:${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'?30:118}px;line-height:27px;color:blue;cursor:pointer;" onclick="selectCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadOneFour">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >普通话水平：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.mandarinLevel" value="${extInfoForm.mandarinLevel }">
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.mandarinLevel" value="${extInfoForm.mandarinLevel }">
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.mandarinLevel }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.mandarinLevel" value="${extInfoForm.mandarinLevel}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th style="width:20%;"><span >外语水平：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.foreignLanguageLevel" value="${extInfoForm.foreignLanguageLevel }">
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.foreignLanguageLevel" value="${extInfoForm.foreignLanguageLevel }">
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.foreignLanguageLevel }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.foreignLanguageLevel" value="${extInfoForm.foreignLanguageLevel}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >计算机水平：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.computerLevel" value="${extInfoForm.computerLevel }">
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.computerLevel" value="${extInfoForm.computerLevel }">
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.computerLevel }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.computerLevel" value="${extInfoForm.computerLevel}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th><span >学制：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.schoolSystem" value="${extInfoForm.schoolSystem }">
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.schoolSystem" value="${extInfoForm.schoolSystem }">
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.schoolSystem }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.schoolSystem" value="${extInfoForm.schoolSystem}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span >生源省市：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.studentSourceArea" value="${extInfoForm.studentSourceArea }">
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.studentSourceArea" value="${extInfoForm.studentSourceArea }">
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.studentSourceArea }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.studentSourceArea" value="${extInfoForm.studentSourceArea}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >宿舍地址：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.dormitoryAdd" value="${extInfoForm.dormitoryAdd }">
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.dormitoryAdd" value="${extInfoForm.dormitoryAdd }">
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.dormitoryAdd }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.dormitoryAdd" value="${extInfoForm.dormitoryAdd}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span >家庭电话：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.homePhone" value="${extInfoForm.homePhone }">
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.homePhone" value="${extInfoForm.homePhone }">
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.homePhone }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.homePhone" value="${extInfoForm.homePhone}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >配偶姓名：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.mateName" value="${extInfoForm.mateName }">
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.mateName" value="${extInfoForm.mateName }">
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.mateName }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.mateName" value="${extInfoForm.mateName}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span >配偶身份证：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.mateIdNo" value="${extInfoForm.mateIdNo }">
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" style="width: 140px;text-align: left;" class="inputText" name="eduUserExtInfoForm.mateIdNo" value="${extInfoForm.mateIdNo }">
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.mateIdNo }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.mateIdNo" value="${extInfoForm.mateIdNo}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >配偶工作单位：</span></th>
                                    <td colspan="3">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 300px;text-align: left;" name="eduUserExtInfoForm.spouseUnit" value="${extInfoForm.spouseUnit }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" class="inputText" style="width: 300px;text-align: left;" name="eduUserExtInfoForm.spouseUnit" value="${extInfoForm.spouseUnit }"/>
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.spouseUnit }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.spouseUnit" value="${extInfoForm.spouseUnit}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >特长：</span></th>
                                    <td colspan="3">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 300px;text-align: left;" name="eduUserExtInfoForm.speciality" value="${extInfoForm.speciality }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" class="inputText" style="width: 300px;text-align: left;" name="eduUserExtInfoForm.speciality" value="${extInfoForm.speciality }"/>
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.speciality }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.speciality" value="${extInfoForm.speciality}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >本人主要简历：</span></th>
                                    <td colspan="3">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 300px;text-align: left;" name="eduUserExtInfoForm.mainResume" value="${extInfoForm.mainResume }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <input type="text" class="inputText" style="width: 300px;text-align: left;" name="eduUserExtInfoForm.mainResume" value="${extInfoForm.mainResume }"/>
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.mainResume }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.mainResume" value="${extInfoForm.mainResume}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >入学前奖惩情况：</span></th>
                                    <td colspan="3">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <textarea class="xltxtarea" name="eduUserExtInfoForm.reAndPuStatusContent">${extInfoForm.reAndPuStatusContent }</textarea>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <textarea class="xltxtarea" name="eduUserExtInfoForm.reAndPuStatusContent">${extInfoForm.reAndPuStatusContent }</textarea>
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label>${extInfoForm.reAndPuStatusContent }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.reAndPuStatusContent" value="${extInfoForm.reAndPuStatusContent}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >曾担任过合作教学工作&#12288;<br>和进行何种科研工作：</span></th>
                                    <td colspan="3">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <textarea class="xltxtarea" name="eduUserExtInfoForm.scientificTogether">${extInfoForm.scientificTogether }</textarea>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <textarea class="xltxtarea" name="eduUserExtInfoForm.scientificTogether">${extInfoForm.scientificTogether }</textarea>
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label style="text-align: left;">${extInfoForm.scientificTogether }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.scientificTogether" value="${extInfoForm.scientificTogether}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >备注：</span></th>
                                    <td colspan="3">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <textarea class="xltxtarea" name="eduUserExtInfoForm.remark">${extInfoForm.remark }</textarea>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${optionalFlag}">
                                                <textarea class="xltxtarea" name="eduUserExtInfoForm.remark">${extInfoForm.remark }</textarea>
                                            </c:if>
                                            <c:if test="${!optionalFlag}">
                                                <label style="text-align: left;">${extInfoForm.remark }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.remark" value="${extInfoForm.remark}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" hidden="hidden" style="margin-bottom:10px;" id="feeInfo">
                        <input type="hidden" id="feeStatus" name="feeStatus" value="${param.feeStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;学费信息--%>
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'}">
                                <span style="float:right;margin-right:100px;margin-top: 5px;margin-bottom: 5px;"><c:if test="${statusMap['FeeInfo'] ne 'Y'}"><a class="before">信息未确认，点击按钮确认</a><input type="button" class="search"  onclick="confirmStatus('FeeInfo',this)" value="确&#12288;认"/></c:if>
                                <c:if test="${statusMap['FeeInfo'] eq 'Y'}"><a class="after">信息已确认</a></c:if></span>
                            </c:if>
                            <%--<span style="float:right;margin-right:${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'?30:118}px;line-height:27px;color:blue;cursor:pointer;" onclick="feeCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadOneFive">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span >缴费账号：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR || isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.accountNum" value="${extInfoForm.accountNum }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
                                            <label>${extInfoForm.accountNum }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.accountNum" value="${extInfoForm.accountNum}">
                                        </c:if>
                                    </td>
                                    <th style="width:20%;"><span >学费总额：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR||isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.tuitionFee" value="${extInfoForm.tuitionFee }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
                                            <label>${extInfoForm.tuitionFee }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.tuitionFee" value="${extInfoForm.tuitionFee}">
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >已缴纳学费：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR||isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.paytuitionFee" value="${extInfoForm.paytuitionFee }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
                                            <label>${extInfoForm.paytuitionFee }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.paytuitionFee" value="${extInfoForm.paytuitionFee}">
                                        </c:if>
                                    </td>
                                    <th><span >欠缴纳学费：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.arrearageFee" value="${extInfoForm.arrearageFee }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
                                            <label>${extInfoForm.arrearageFee }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.arrearageFee" value="${extInfoForm.arrearageFee}">
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >住宿费：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR||isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.dormitoryFee" value="${extInfoForm.dormitoryFee }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR }">
                                            <label>${extInfoForm.dormitoryFee }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.dormitoryFee" value="${extInfoForm.dormitoryFee}">
                                        </c:if>
                                    </td>
                                    <th><span >已缴纳住宿费：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.payDormitoryFee" value="${extInfoForm.payDormitoryFee }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR  }">
                                            <label>${extInfoForm.payDormitoryFee }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.payDormitoryFee" value="${extInfoForm.payDormitoryFee}">
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >欠缴纳住宿费：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR||isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.arrearageDormitoryFee" value="${extInfoForm.arrearageDormitoryFee }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR  }">
                                            <label>${extInfoForm.arrearageDormitoryFee }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.arrearageDormitoryFee" value="${extInfoForm.arrearageDormitoryFee}">
                                        </c:if>
                                    </td>
                                    <th><span >是否缴清费用：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR||isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.paidFee" class="inputText" onchange="sendNotice(this.value)">
                                                <option/>
                                                <option value="Y" ${extInfoForm.paidFee eq 'Y'?'selected':''}>是</option>
                                                <option value="N" ${extInfoForm.paidFee eq 'N'?'selected':''}>否</option>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR  }">
                                            <label>${extInfoForm.paidFee eq 'Y'?'是':''}${extInfoForm.paidFee eq 'N'?'否':''}</label>
                                            <input type="hidden" name="eduUserExtInfoForm.paidFee" value="${extInfoForm.paidFee}">
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span >缴费编号：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR||isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.paidFeeNo" value="${extInfoForm.paidFeeNo }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR  }">
                                            <label>${extInfoForm.paidFeeNo }</label>
                                            <input type="hidden" name="eduUserExtInfoForm.paidFeeNo" value="${extInfoForm.paidFeeNo}">
                                        </c:if>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" hidden="hidden" style="margin-bottom:10px;" id="gotCertInfo">
                        <input type="hidden" id="gotCertStatus" name="gotCertStatus" value="${param.gotCertStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;已获得学历或学位信息--%>
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'}">
                                <span style="float:right;margin-right:100px;margin-top: 5px;margin-bottom: 5px;"><c:if test="${statusMap['GotCertInfo'] ne 'Y'}"><a class="before">信息未确认，点击按钮确认</a><input type="button" class="search"  onclick="confirmStatus('GotCertInfo',this)" value="确&#12288;认"/></c:if>
                                <c:if test="${statusMap['GotCertInfo'] eq 'Y'}"><a class="after">信息已确认</a></c:if></span>
                            </c:if>
                            <%--<span style="float:right;margin-right:${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'?30:118}px;line-height:27px;color:blue;cursor:pointer;" onclick="gotCertCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadTwoOne">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%;"><span style="color: red;">*</span>&#12288;<span >第一学历：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.firstEducationContentId" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyFirstEducationList}" var="firstEducation">
                                                    <option value="${firstEducation.dictId}" ${extInfoForm.firstEducationContentId eq firstEducation.dictId?'selected':''}>${firstEducation.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.firstEducationContentId" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <c:forEach items="${dictTypeEnumGyFirstEducationList}" var="firstEducation">
                                                        <option value="${firstEducation.dictId}" ${extInfoForm.firstEducationContentId eq firstEducation.dictId?'selected':''}>${firstEducation.dictName}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>
                                                    <c:forEach items="${dictTypeEnumGyFirstEducationList}" var="firstEducation">
                                                        ${extInfoForm.firstEducationContentId eq firstEducation.dictId ? firstEducation.dictName:''}
                                                    </c:forEach>
                                                </label>
                                                <input type="hidden" name="eduUserExtInfoForm.firstEducationContentId" value="${extInfoForm.firstEducationContentId}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th style="width:20%;"><span style="color: red;">*</span>&#12288;<span >前置学历：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.preGraduation" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyPreGraduationList}" var="dict">
                                                    <option value="${dict.dictId}" ${extInfoForm.preGraduation eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.preGraduation" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <c:forEach items="${dictTypeEnumGyPreGraduationList}" var="dict">
                                                        <option value="${dict.dictId}" ${extInfoForm.preGraduation eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>
                                                    <c:forEach items="${dictTypeEnumGyPreGraduationList}" var="dict">
                                                        ${extInfoForm.preGraduation eq dict.dictId ? dict.dictName:''}
                                                    </c:forEach>
                                                </label>
                                                <input type="hidden" name="eduUserExtInfoForm.preGraduation" value="${extInfoForm.preGraduation}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <%--暂时隐藏--%>
                                    <%--<th style="width:20%;"><span style="color: red;">*</span>&#12288;<span style="color: blue;">定向培养单位</span>&#12288;</th>--%>
                                    <%--<td style="width:30%;">--%>
                                    <%--<c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">--%>
                                    <%--<input type="text" class="inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.directionalOrgName" value="${extInfoForm.directionalOrgName }"/>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">--%>
                                    <%--<c:if test="${degreeGrantFlag}">--%>
                                    <%--<input type="text" class="inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.directionalOrgName" value="${extInfoForm.directionalOrgName }"/>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${!degreeGrantFlag}">--%>
                                    <%--<label>${extInfoForm.directionalOrgName }</label>--%>
                                    <%--</c:if>--%>
                                    <%--</c:if>--%>
                                    <%--</td>--%>
                                </tr>
                                <tr>
                                    <th style="width:20%;"><span style="color: red;">*</span>&#12288;<span>是否获得本科学历：</span></th>
                                    <td style="width:30%;">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.bkgain" id="bkgain" class="inputText">
                                                <option/>
                                                <option value="1" ${extInfoForm.bkgain eq '1'?'selected':''}>是</option>
                                                <option value="2" ${extInfoForm.bkgain eq '2'?'selected':''}>否</option>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.bkgain" id="bkgain" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="1" ${extInfoForm.bkgain eq '1'?'selected':''}>是</option>
                                                    <option value="2" ${extInfoForm.bkgain eq '2'?'selected':''}>否</option>
                                                </select>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>
                                                    <c:if test="${extInfoForm.bkgain eq '1'}">是</c:if>
                                                    <c:if test="${extInfoForm.bkgain eq '2'}">否</c:if>
                                                </label>
                                                <input type="hidden" name="eduUserExtInfoForm.bkgain" value="${extInfoForm.bkgain}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th class="bkgainDisplayContent" style="width:20%;white-space:nowrap;"><span style="color: red;">*</span>&#12288;<span>获得本科学历的学习形式：</span></th>
                                    <td class="bkgainDisplayContent" style="width:30%;">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.underStudyForm" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyUnderStudyFormList}" var="dict">
                                                    <option value="${dict.dictId}" ${extInfoForm.underStudyForm eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.underStudyForm" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <c:forEach items="${dictTypeEnumGyUnderStudyFormList}" var="dict">
                                                        <option value="${dict.dictId}" ${extInfoForm.underStudyForm eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>
                                                    <c:forEach items="${dictTypeEnumGyUnderStudyFormList}" var="dict">
                                                        ${extInfoForm.underStudyForm eq dict.dictId?dict.dictName:''}
                                                    </c:forEach>
                                                </label>
                                                <input type="hidden" name="eduUserExtInfoForm.underStudyForm" value="${extInfoForm.underStudyForm}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th class="bkgainFillTD"></th>
                                    <td class="bkgainFillTD"></td>
                                </tr>
                                <tr class="bkgainDisplayContent">
                                    <th><span style="color: red;">*</span>&#12288;<span>本科毕业年月：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underGraduateTime" value="${extInfoForm.underGraduateTime }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underGraduateTime" value="${extInfoForm.underGraduateTime }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${extInfoForm.underGraduateTime }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.underGraduateTime" value="${extInfoForm.underGraduateTime}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span style="color: red;">*</span>&#12288;<span>本科毕业证书编号：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underDiplomaCode" value="${extInfoForm.underDiplomaCode }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underDiplomaCode" value="${extInfoForm.underDiplomaCode }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${extInfoForm.underDiplomaCode }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.underDiplomaCode" value="${extInfoForm.underDiplomaCode}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr class="bkgainDisplayContent">
                                    <th><span style="color: red;">*</span>&#12288;<span>本科毕业单位名称：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underGraduateOrgName" value="${extInfoForm.underGraduateOrgName }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underGraduateOrgName" value="${extInfoForm.underGraduateOrgName }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${extInfoForm.underGraduateOrgName }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.underGraduateOrgName" value="${extInfoForm.underGraduateOrgName}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span style="color: red;">*</span>&#12288;<span>本科毕业专业名称：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underGraduateMajor" value="${extInfoForm.underGraduateMajor }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underGraduateMajor" value="${extInfoForm.underGraduateMajor }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${extInfoForm.underGraduateMajor }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.underGraduateMajor" value="${extInfoForm.underGraduateMajor}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span style="color: red;">*</span>&#12288;<span>是否获得学士学位：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.xsgain" id="xsgain" class="inputText">
                                                <option/>
                                                <option value="1" ${extInfoForm.xsgain eq '1'?'selected':''}>是</option>
                                                <option value="2" ${extInfoForm.xsgain eq '2'?'selected':''}>否</option>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.xsgain" id="xsgain" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="1" ${extInfoForm.xsgain eq '1'?'selected':''}>是</option>
                                                    <option value="2" ${extInfoForm.xsgain eq '2'?'selected':''}>否</option>
                                                </select>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>
                                                    <c:if test="${extInfoForm.xsgain eq '1'}">是</c:if>
                                                    <c:if test="${extInfoForm.xsgain eq '2'}">否</c:if>
                                                </label>
                                                <input type="hidden" name="eduUserExtInfoForm.xsgain" value="${extInfoForm.xsgain}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                                <tr class="xsgainDisplayContent">
                                    <th><span style="color: red;">*</span>&#12288;<span>获得学士学位年月：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underAwardDegreeTime" value="${extInfoForm.underAwardDegreeTime }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underAwardDegreeTime" value="${extInfoForm.underAwardDegreeTime }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${extInfoForm.underAwardDegreeTime }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.underAwardDegreeTime" value="${extInfoForm.underAwardDegreeTime}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span style="color: red;">*</span>&#12288;<span>学士学位证书编号：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underDegreeCertCode" value="${extInfoForm.underDegreeCertCode }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underDegreeCertCode" value="${extInfoForm.underDegreeCertCode }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${extInfoForm.underDegreeCertCode }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.underDegreeCertCode" value="${extInfoForm.underDegreeCertCode}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr class="xsgainDisplayContent">
                                    <th><span style="color: red;">*</span>&#12288;<span>获学士学位单位名称：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underAwardDegreeOrg" value="${extInfoForm.underAwardDegreeOrg }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underAwardDegreeOrg" value="${extInfoForm.underAwardDegreeOrg }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${extInfoForm.underAwardDegreeOrg }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.underAwardDegreeOrg" value="${extInfoForm.underAwardDegreeOrg}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span style="color: red;">*</span>&#12288;<span>获学士学位专业名称：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underMajor" value="${extInfoForm.underMajor }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.underMajor" value="${extInfoForm.underMajor }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${ extInfoForm.underMajor}</label>
                                                <input type="hidden" name="eduUserExtInfoForm.underMajor" value="${extInfoForm.underMajor}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr class="xsgainDisplayContent">
                                    <th><span style="color: red;">*</span>&#12288;<span>获得学士学位学科门类：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.gotBachelorCertSubject" value="${extInfoForm.gotBachelorCertSubject }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.gotBachelorCertSubject" value="${extInfoForm.gotBachelorCertSubject }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${extInfoForm.gotBachelorCertSubject}</label>
                                                <input type="hidden" name="eduUserExtInfoForm.gotBachelorCertSubject" value="${extInfoForm.gotBachelorCertSubject}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                                <tr class="degreeCode">
                                    <th><span style="color: red;">*</span>&#12288;<span>最后学位证编号：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.code" value="${extInfoForm.code }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.code" value="${extInfoForm.code }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${extInfoForm.code }</label>
                                                <input type="hidden" name="eduUserExtInfoForm.code" value="${extInfoForm.code}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                                <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                    <tr class="linkRecruitType">
                                        <th style="white-space:nowrap;"><span style="color: red;">*</span>&#12288;<span>是否获得硕士研究生学历：</span></th>
                                        <td>
                                            <select style="width: 144px;" name="eduUserExtInfoForm.ssyjsgain" id="ssyjsgain" class="inputText">
                                                <option/>
                                                <option value="1" ${extInfoForm.ssyjsgain eq '1'?'selected':''}>是</option>
                                                <option value="2" ${extInfoForm.ssyjsgain eq '2'?'selected':''}>否</option>
                                            </select>
                                        </td>
                                        <th></th>
                                        <td></td>
                                    </tr>
                                    <tr class="linkRecruitType ssyjsgainDisplayContent">
                                        <th><span style="color: red;">*</span>&#12288;<span>硕士研究生毕业年月：</span></th>
                                        <td>
                                            <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterGraduateTime" value="${extInfoForm.masterGraduateTime }"/>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span>硕士研究生毕业证编号：</span></th>
                                        <td>
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterDiplomaCode" value="${extInfoForm.masterDiplomaCode }"/>
                                        </td>
                                    </tr>
                                    <tr class="linkRecruitType ssyjsgainDisplayContent">
                                        <th><span style="color: red;">*</span>&#12288;<span>硕士研究生毕业单位名称：</span></th>
                                        <td>
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterUnitName" value="${extInfoForm.masterUnitName }"/>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span>硕士研究生毕业专业名称：</span></th>
                                        <td>
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterGraduateMajor" value="${extInfoForm.masterGraduateMajor }"/>
                                        </td>
                                    </tr>
                                    <tr class="linkRecruitType">
                                        <th><span style="color: red;">*</span>&#12288;<span>是否获得硕士学位：</span></th>
                                        <td>
                                            <select style="width: 144px;" name="eduUserExtInfoForm.ssxwgain" id="ssxwgain" class="inputText">
                                                <option/>
                                                <option value="1" ${extInfoForm.ssxwgain eq '1'?'selected':''}>是</option>
                                                <option value="2" ${extInfoForm.ssxwgain eq '2'?'selected':''}>否</option>
                                            </select>
                                        </td>
                                        <th class="ssxwgainDisplayContent"><span style="color: red;">*</span>&#12288;<span>获得硕士学位的学习形式：</span></th>
                                        <td class="ssxwgainDisplayContent">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.masterStudyForm" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyMasterStudyFormList}" var="dict">
                                                    <option value="${dict.dictId}" ${extInfoForm.masterStudyForm eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <th class="ssxwgainFillTD"></th>
                                        <td class="ssxwgainFillTD"></td>
                                    </tr>
                                    <tr class="linkRecruitType ssxwgainDisplayContent">
                                        <th><span style="color: red;">*</span>&#12288;<span>获得硕士学位年月：</span></th>
                                        <td>
                                            <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterAwardDegreeTime" value="${extInfoForm.masterAwardDegreeTime }"/>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span>硕士学位证书编号：</span></th>
                                        <td>
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterDegreeCertCode" value="${extInfoForm.masterDegreeCertCode }"/>
                                        </td>
                                    </tr>
                                    <tr class="linkRecruitType ssxwgainDisplayContent">
                                        <th><span style="color: red;">*</span>&#12288;<span>获得硕士学位单位名称：</span></th>
                                        <td>
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterAwardDegreeOrg" value="${extInfoForm.masterAwardDegreeOrg }"/>
                                        </td>
                                        <th><span style="color: red;">*</span>&#12288;<span>获得硕士学位学科门类：</span></th>
                                        <td>
                                            <input type="text" class="inputText" style="text-align: left;" name="eduUserExtInfoForm.masterSubject" value="${extInfoForm.masterSubject}"/>
                                        </td>
                                    </tr>
                                    <tr class="gotMasterCertSpeTr ssxwgainDisplayContent">
                                        <th><span style="color: red;">*</span>&#12288;<span>获得硕士学位专业：</span></th>
                                        <td>
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.gotMasterCertSpe" value="${extInfoForm.gotMasterCertSpe }"/>
                                        </td>
                                        <th></th>
                                        <td></td>
                                    </tr>
                                </c:if>
                                <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                    <c:if test="${ not empty eduUser.trainTypeId and eduUser.trainTypeId >'1' }">
                                        <tr class="linkRecruitType">
                                            <th><span style="color: red;">*</span>&#12288;<span>是否获得硕士研究生学历：</span></th>
                                            <td>
                                                <c:if test="${degreeGrantFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.ssyjsgain" id="ssyjsgain" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="1" ${extInfoForm.ssyjsgain eq '1'?'selected':''}>是</option>
                                                        <option value="2" ${extInfoForm.ssyjsgain eq '2'?'selected':''}>否</option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    <label>
                                                        <c:if test="${extInfoForm.ssyjsgain eq '1'}">是</c:if>
                                                        <c:if test="${extInfoForm.ssyjsgain eq '2'}">否</c:if>
                                                    </label>
                                                    <input type="hidden" id="ssyjsgain" name="eduUserExtInfoForm.ssyjsgain" value="${extInfoForm.ssyjsgain}">
                                                </c:if>
                                            </td>
                                            <th></th>
                                            <td></td>
                                        </tr>
                                        <tr class="linkRecruitType ssyjsgainDisplayContent">
                                            <th><span style="color: red;">*</span>&#12288;<span>硕士研究生毕业年月：</span></th>
                                            <td>
                                                <c:if test="${degreeGrantFlag}">
                                                    <input type="text" class="inputText ctime validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterGraduateTime" value="${extInfoForm.masterGraduateTime }"/>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    <label>${extInfoForm.masterGraduateTime }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.masterGraduateTime" value="${extInfoForm.masterGraduateTime}">
                                                </c:if>
                                            </td>
                                            <th><span style="color: red;">*</span>&#12288;<span>硕士研究生毕业证编号：</span></th>
                                            <td>
                                                <c:if test="${degreeGrantFlag}">
                                                    <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterDiplomaCode" value="${extInfoForm.masterDiplomaCode }"/>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    <label>${extInfoForm.masterDiplomaCode }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.masterDiplomaCode" value="${extInfoForm.masterDiplomaCode}">
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr class="linkRecruitType ssyjsgainDisplayContent">
                                            <th><span style="color: red;">*</span>&#12288;<span>硕士研究生毕业单位名称：</span></th>
                                            <td>
                                                <c:if test="${degreeGrantFlag}">
                                                    <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterUnitName" value="${extInfoForm.masterUnitName }"/>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    <label>${extInfoForm.masterUnitName }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.masterUnitName" value="${extInfoForm.masterUnitName}">
                                                </c:if>
                                            </td>
                                            <th><span style="color: red;">*</span>&#12288;<span>硕士研究生毕业专业名称：</span></th>
                                            <td>
                                                <c:if test="${degreeGrantFlag}">
                                                    <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterGraduateMajor" value="${extInfoForm.masterGraduateMajor }"/>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    <label>${extInfoForm.masterGraduateMajor }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.masterGraduateMajor" value="${extInfoForm.masterGraduateMajor}">
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr class="linkRecruitType">
                                            <th><span style="color: red;">*</span>&#12288;<span>是否获得硕士学位：</span></th>
                                            <td>
                                                <c:if test="${degreeGrantFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.ssxwgain" id="ssxwgain" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <option value="1" ${extInfoForm.ssxwgain eq '1'?'selected':''}>是</option>
                                                        <option value="2" ${extInfoForm.ssxwgain eq '2'?'selected':''}>否</option>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    <label>
                                                        <c:if test="${extInfoForm.ssxwgain eq '1'}">是</c:if>
                                                        <c:if test="${extInfoForm.ssxwgain eq '2'}">否</c:if>
                                                    </label>
                                                    <input type="hidden" id="ssxwgain" name="eduUserExtInfoForm.ssxwgain" value="${extInfoForm.ssxwgain}">
                                                </c:if>
                                            </td>
                                            <th class="ssxwgainDisplayContent"><span style="color: red;">*</span>&#12288;<span>获得硕士学位的学习形式：</span></th>
                                            <td class="ssxwgainDisplayContent">
                                                <c:if test="${degreeGrantFlag}">
                                                    <select style="width: 144px;" name="eduUserExtInfoForm.masterStudyForm" class="notBlank inputText validate[required]">
                                                        <option/>
                                                        <c:forEach items="${dictTypeEnumGyMasterStudyFormList}" var="dict">
                                                            <option value="${dict.dictId}" ${extInfoForm.masterStudyForm eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    <label>
                                                        <c:forEach items="${dictTypeEnumGyMasterStudyFormList}" var="dict">
                                                            ${extInfoForm.masterStudyForm eq dict.dictId?dict.dictName:''}
                                                        </c:forEach>
                                                    </label>
                                                    <input type="hidden" name="eduUserExtInfoForm.masterStudyForm" value="${extInfoForm.masterStudyForm}">
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr class="linkRecruitType ssxwgainDisplayContent">
                                            <th><span style="color: red;">*</span>&#12288;<span>获得硕士学位年月：</span></th>
                                            <td>
                                                <c:if test="${degreeGrantFlag}">
                                                    <input type="text" class="notBlank inputText ctime validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterAwardDegreeTime" value="${extInfoForm.masterAwardDegreeTime }"/>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    <label>${extInfoForm.masterAwardDegreeTime }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.masterAwardDegreeTime" value="${extInfoForm.masterAwardDegreeTime}">
                                                </c:if>
                                            </td>
                                            <th><span style="color: red;">*</span>&#12288;<span>硕士学位证书编号：</span></th>
                                            <td>
                                                <c:if test="${degreeGrantFlag}">
                                                    <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterDegreeCertCode" value="${extInfoForm.masterDegreeCertCode }"/>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    <label>${extInfoForm.masterDegreeCertCode }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.masterDegreeCertCode" value="${extInfoForm.masterDegreeCertCode}">
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr class="linkRecruitType ssxwgainDisplayContent">
                                            <th><span style="color: red;">*</span>&#12288;<span>获得硕士学位单位名称：</span></th>
                                            <td>
                                                <c:if test="${degreeGrantFlag}">
                                                    <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.masterAwardDegreeOrg" value="${extInfoForm.masterAwardDegreeOrg }"/>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    <label>${extInfoForm.masterAwardDegreeOrg }</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.masterAwardDegreeOrg" value="${extInfoForm.masterAwardDegreeOrg}">
                                                </c:if>
                                            </td>
                                            <th><span style="color: red;">*</span>&#12288;<span>获得硕士学位学科门类：</span></th>
                                            <td>
                                                <c:if test="${degreeGrantFlag}">
                                                    <input type="text" class="notBlank inputText validate[required]" style="text-align: left;" name="eduUserExtInfoForm.masterSubject" value="${extInfoForm.masterSubject}"/>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    ${extInfoForm.masterSubject}
                                                    <input type="hidden" name="eduUserExtInfoForm.masterSubject" value="${extInfoForm.masterSubject}">
                                                </c:if>
                                            </td>
                                        </tr>
                                        <tr class="gotMasterCertSpeTr ssxwgainDisplayContent">
                                            <th><span style="color: red;">*</span>&#12288;<span>获得硕士学位专业：</span></th>
                                            <td>
                                                <c:if test="${degreeGrantFlag}">
                                                    <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.gotMasterCertSpe" value="${extInfoForm.gotMasterCertSpe }"/>
                                                </c:if>
                                                <c:if test="${!degreeGrantFlag}">
                                                    <label>${ extInfoForm.gotMasterCertSpe}</label>
                                                    <input type="hidden" name="eduUserExtInfoForm.gotMasterCertSpe" value="${extInfoForm.gotMasterCertSpe}">
                                                </c:if>
                                            </td>
                                            <th></th>
                                            <td></td>
                                        </tr>
                                    </c:if>
                                </c:if>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" hidden="hidden" style="margin-bottom:10px;" id="certReqInfo">
                        <input type="hidden" id="certReqStatus" name="certReqStatus" value="${param.certReqStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;攻读学历学位信息--%>
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'}">
                                <span style="float:right;margin-right:100px;margin-top: 5px;margin-bottom: 5px;"><c:if test="${statusMap['CertReqInfo'] ne 'Y'}"><a class="before">信息未确认，点击按钮确认</a><input type="button" class="search"  onclick="confirmStatus('CertReqInfo',this)" value="确&#12288;认"/></c:if>
                                <c:if test="${statusMap['CertReqInfo'] eq 'Y'}"><a class="after">信息已确认</a></c:if></span>
                            </c:if>
                            <%--<span style="float:right;margin-right:${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'?30:118}px;line-height:27px;color:blue;cursor:pointer;" onclick="certReqCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadTwoTwo">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width: 20%"><span style="color: red;">*</span>&#12288;<span >预毕业时间：</span></th>
                                    <td style="width: 30%">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.planGraduateTime" value="${extInfoForm.planGraduateTime }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText ctime validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.planGraduateTime" value="${extInfoForm.planGraduateTime }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                ${extInfoForm.planGraduateTime }
                                                <input type="hidden" name="eduUserExtInfoForm.planGraduateTime" value="${extInfoForm.planGraduateTime}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th style="width: 20%"><span style="color: red;">*</span>&#12288;<span >是否毕业：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.graduateFlag" class="inputText" id="isGraduateFlag">
                                                <option/>
                                                <option value="1" ${extInfoForm.graduateFlag eq '1'?'selected':''}>是</option>
                                                <option value="2" ${extInfoForm.graduateFlag eq '2'?'selected':''}>否</option>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.graduateFlag" class="notBlank inputText validate[required]" id="isGraduateFlag">
                                                    <option/>
                                                    <option value="1" ${extInfoForm.graduateFlag eq '1'?'selected':''}>是</option>
                                                    <option value="2" ${extInfoForm.graduateFlag eq '2'?'selected':''}>否</option>
                                                </select>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                ${extInfoForm.graduateFlag eq '1'?'是':''}
                                                ${extInfoForm.graduateFlag eq '2'?'否':''}
                                                <input type="hidden" name="eduUserExtInfoForm.graduateFlag" value="${extInfoForm.graduateFlag}" id="isGraduateFlag">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tbody id="graduateFlagTbody">
                                <tr>
                                    <th><span style="color: red;">*</span>&#12288;<span >毕业时间：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 140px;" class="inputText ctime" name="eduUser.graduateTime" value="${eduUser.graduateTime }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="text-align: left;width: 140px;" class="notBlank inputText ctime validate[required]" name="eduUser.graduateTime" value="${eduUser.graduateTime }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                ${eduUser.graduateTime }
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span style="color: red;">*</span>&#12288;<span >毕业证书编号：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="text-align: left;" name="eduUser.diplomaCode" value="${eduUser.diplomaCode}"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="text-align: left;" name="eduUser.diplomaCode" value="${eduUser.diplomaCode}"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                ${eduUser.diplomaCode}
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span style="color: red;">*</span>&#12288;<span >是否授予学位：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.awardDegreeFlag" class="inputText">
                                                <option/>
                                                <option value="${GlobalConstant.FLAG_Y}" ${eduUser.awardDegreeFlag eq GlobalConstant.FLAG_Y?'selected':''}>
                                                    是
                                                </option>
                                                <option value="${GlobalConstant.FLAG_N}" ${eduUser.awardDegreeFlag eq GlobalConstant.FLAG_N?'selected':''}>
                                                    否
                                                </option>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <select style="width: 144px;" name="eduUser.awardDegreeFlag" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <option value="${GlobalConstant.FLAG_Y}" ${eduUser.awardDegreeFlag eq GlobalConstant.FLAG_Y?'selected':''}>
                                                        是
                                                    </option>
                                                    <option value="${GlobalConstant.FLAG_N}" ${eduUser.awardDegreeFlag eq GlobalConstant.FLAG_N?'selected':''}>
                                                        否
                                                    </option>
                                                </select>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <c:if test="${eduUser.awardDegreeFlag==GlobalConstant.FLAG_Y}">是</c:if>
                                                <c:if test="${eduUser.awardDegreeFlag==GlobalConstant.FLAG_N}">否</c:if>
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span style="color: red;">*</span>&#12288;<span >授予学科门类：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.awardSubjectCategory" value="${extInfoForm.awardSubjectCategory}"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="width: 140px;text-align: left;" name="eduUserExtInfoForm.awardSubjectCategory" value="${extInfoForm.awardSubjectCategory}"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${extInfoForm.awardSubjectCategory}</label>
                                                <input type="hidden" name="eduUserExtInfoForm.awardSubjectCategory" value="${extInfoForm.awardSubjectCategory}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span style="color: red;">*</span>&#12288;<span >授予学位类别：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUser.awardDegreeCategoryId" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyDegreeCategoryList}" var="award">
                                                    <option value="${award.dictId}" ${eduUser.awardDegreeCategoryId eq award.dictId?'selected':''}>${award.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <select style="width: 144px;" name="eduUser.awardDegreeCategoryId" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <c:forEach items="${dictTypeEnumGyDegreeCategoryList}" var="award">
                                                        <option value="${award.dictId}" ${eduUser.awardDegreeCategoryId eq award.dictId?'selected':''}>${award.dictName}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                ${eduUser.awardDegreeCategoryName }
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span style="color: red;">*</span>&#12288;<span >授予学位时间：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText ctime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="eduUser.awardDegreeTime" style="text-align: left;width: 140px;" value="${eduUser.awardDegreeTime }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${degreeGrantFlag}">
                                                <input type="text" class="notBlank notBlank inputText ctime validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="eduUser.awardDegreeTime" style="text-align: left;width: 140px;" value="${eduUser.awardDegreeTime }"/>
                                            </c:if>
                                            <c:if test="${!degreeGrantFlag}">
                                                <label>${eduUser.awardDegreeTime }</label>
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span style="color: red;">*</span>&#12288;<span >授予学位证书编号：</span></th>
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
                                </tr></tbody>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" hidden="hidden" style="margin-bottom:10px;" id="paperInfo">
                        <input type="hidden" id="paperStatus" name="paperStatus" value="${param.paperStatus}">
                        <%--<table class="basic" style="width:100%;"><tr><th style="text-align:left;line-height:27px;">&#12288;学位论文信息--%>
                            <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'}">
                                <span style="float:right;margin-right:100px;margin-top: 5px;margin-bottom: 5px;"><c:if test="${statusMap['PaperInfo'] ne 'Y'}"><a class="before">信息未确认，点击按钮确认</a><input type="button" class="search"  onclick="confirmStatus('PaperInfo',this)" value="确&#12288;认"/></c:if>
                                <c:if test="${statusMap['PaperInfo'] eq 'Y'}"><a class="after">信息已确认</a></c:if></span>
                            </c:if>
                            <%--<span style="float:right;margin-right:${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'?30:118}px;line-height:27px;color:blue;cursor:pointer;" onclick="paperCheck()">查看</span>--%>
                        <%--</th></tr></table>--%>
                        <div class="spreadThree">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%"><span style="color: red;">*</span>&#12288;<span >论文题目：</span></th>
                                    <td style="width:30%">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.paperTitle" value="${extInfoForm.paperTitle }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${paperFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.paperTitle" value="${extInfoForm.paperTitle }"/>
                                            </c:if>
                                            <c:if test="${!paperFlag}">
                                                ${extInfoForm.paperTitle }
                                                <input type="hidden" name="eduUserExtInfoForm.paperTitle" value="${extInfoForm.paperTitle}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th style="width:20%"><span style="color: red;">*</span>&#12288;<span >论文选题来源：</span></th>
                                    <td style="width:30%">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.paperSource" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyPaperSourceList}" var="dict">
                                                    <option value="${dict.dictId}" ${extInfoForm.paperSource eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${paperFlag}">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.paperSource" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <c:forEach items="${dictTypeEnumGyPaperSourceList}" var="dict">
                                                        <option value="${dict.dictId}" ${extInfoForm.paperSource eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${!paperFlag}">
                                                <c:forEach items="${dictTypeEnumGyPaperSourceList}" var="dict">
                                                    ${extInfoForm.paperSource eq dict.dictId?dict.dictName:''}
                                                </c:forEach>
                                                <input type="hidden" name="eduUserExtInfoForm.paperSource" value="${extInfoForm.paperSource}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span style="color: red;">*</span>&#12288;<span >论文关键词：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="text-align: left;width: 140px;" placeholder="3-5个，用“，”隔开" name="eduUserExtInfoForm.paperKey" value="${extInfoForm.paperKey }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${paperFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="text-align: left;width: 140px;" placeholder="3-5个，用“，”隔开" name="eduUserExtInfoForm.paperKey" value="${extInfoForm.paperKey }"/>
                                            </c:if>
                                            <c:if test="${!paperFlag}">
                                                ${extInfoForm.paperKey }
                                                <input type="hidden" name="eduUserExtInfoForm.paperKey" value="${extInfoForm.paperKey}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span style="color: red;">*</span>&#12288;<span >论文类型：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.paperType" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyPaperTypeList}" var="dict">
                                                    <option value="${dict.dictId}" ${extInfoForm.paperType eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${paperFlag}">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.paperType" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <c:forEach items="${dictTypeEnumGyPaperTypeList}" var="dict">
                                                        <option value="${dict.dictId}" ${extInfoForm.paperType eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${!paperFlag}">
                                                <c:forEach items="${dictTypeEnumGyPaperTypeList}" var="dict">
                                                    ${extInfoForm.paperType eq dict.dictId?dict.dictName:''}
                                                </c:forEach>
                                                <input type="hidden" name="eduUserExtInfoForm.paperType" value="${extInfoForm.paperType}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="spreadOne" hidden="hidden" style="margin-bottom:10px;" id="workInfo">
                        <input type="hidden" id="workStatus" name="workStatus" value="${param.workStatus}">
                        <%--<table class="basic" style="width:100%;">--%>
                            <%--<tr>--%>
                                <%--<th style="text-align:left;line-height:27px;">&#12288;就业信息--%>
                                    <c:if test="${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'}">
                                        <span style="float:right;margin-right:100px;margin-top: 5px;margin-bottom: 5px;"><c:if test="${statusMap['WorkInfo'] ne 'Y'}"><a class="before">信息未确认，点击按钮确认</a><input type="button" class="search"  onclick="confirmStatus('WorkInfo',this)" value="确&#12288;认"/></c:if>
                                <c:if test="${statusMap['WorkInfo'] eq 'Y'}"><a class="after">信息已确认</a></c:if></span>
                                    </c:if>
                                    <%--<span style="float:right;margin-right:${roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR && param.qrztFlag ne 'N'?30:118}px;line-height:27px;color:blue;cursor:pointer;" onclick="workCheck()">查看</span>--%>
                                <%--</th>--%>
                            <%--</tr>--%>
                        <%--</table>--%>
                        <div class="spreadFour">
                            <table class="basic" style="width:100%; margin-top: 3px; margin-bottom: 30px;">
                                <tr>
                                    <th style="width:20%"><span style="color: red;">*</span>&#12288;<span >获学位后去向：</span></th>
                                    <td >
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.degreeDirection" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyGotDegreeDirectionList}" var="dict">
                                                    <option value="${dict.dictId}" ${extInfoForm.degreeDirection eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${employmentFlag}">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.degreeDirection" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <c:forEach items="${dictTypeEnumGyGotDegreeDirectionList}" var="dict">
                                                        <option value="${dict.dictId}" ${extInfoForm.degreeDirection eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${!employmentFlag}">
                                                <c:forEach items="${dictTypeEnumGyGotDegreeDirectionList}" var="dict">
                                                    ${extInfoForm.degreeDirection eq dict.dictId?dict.dictName:''}
                                                </c:forEach>
                                                <input type="hidden" name="eduUserExtInfoForm.degreeDirection" value="${extInfoForm.degreeDirection}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th><span style="color: red;">*</span>&#12288;<span >就业单位性质：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.unitNature" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyUnitNatureList}" var="dict">
                                                    <option value="${dict.dictId}" ${extInfoForm.unitNature eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${employmentFlag}">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.unitNature" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <c:forEach items="${dictTypeEnumGyUnitNatureList}" var="dict">
                                                        <option value="${dict.dictId}" ${extInfoForm.unitNature eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${!employmentFlag}">
                                                <c:forEach items="${dictTypeEnumGyUnitNatureList}" var="dict">
                                                    ${extInfoForm.unitNature eq dict.dictId?dict.dictName:''}
                                                </c:forEach>
                                                <input type="hidden" name="eduUserExtInfoForm.unitNature" value="${extInfoForm.unitNature}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th style="white-space:nowrap;"><span style="color: red;">*</span>&#12288;<span >就业单位所在省（直辖市）：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.unitAddress" value="${extInfoForm.unitAddress }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${employmentFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.unitAddress" value="${extInfoForm.unitAddress }"/>
                                            </c:if>
                                            <c:if test="${!employmentFlag}">
                                                ${extInfoForm.unitAddress }
                                                <input type="hidden" name="eduUserExtInfoForm.unitAddress" value="${extInfoForm.unitAddress}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th><span style="color: red;">*</span>&#12288;<span >工作性质：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <select style="width: 144px;" name="eduUserExtInfoForm.workNature" class="inputText">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumGyWorkNatureList}" var="dict">
                                                    <option value="${dict.dictId}" ${extInfoForm.workNature eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${employmentFlag}">
                                                <select style="width: 144px;" name="eduUserExtInfoForm.workNature" class="notBlank inputText validate[required]">
                                                    <option/>
                                                    <c:forEach items="${dictTypeEnumGyWorkNatureList}" var="dict">
                                                        <option value="${dict.dictId}" ${extInfoForm.workNature eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${!employmentFlag}">
                                                <c:forEach items="${dictTypeEnumGyWorkNatureList}" var="dict">
                                                    ${extInfoForm.workNature eq dict.dictId?dict.dictName:''}
                                                </c:forEach>
                                                <input type="hidden" name="eduUserExtInfoForm.workNature" value="${extInfoForm.workNature}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th></th>
                                    <td></td>
                                </tr>
                                <tr>
                                    <th style="width:20%"><span style="color: red;">*</span>&#12288;<span >就业单位名称：</span></th>
                                    <td style="width:30%">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.unitName" value="${extInfoForm.unitName }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${employmentFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.unitName" value="${extInfoForm.unitName }"/>
                                            </c:if>
                                            <c:if test="${!employmentFlag}">
                                                ${extInfoForm.unitName }
                                                <input type="hidden" name="eduUserExtInfoForm.unitName" value="${extInfoForm.unitName}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th style="width:20%"><span style="color: red;">*</span>&#12288;<span >就业单位联系人：</span></th>
                                    <td style="width:30%">
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.unitLinkMan" value="${extInfoForm.unitLinkMan }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${employmentFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.unitLinkMan" value="${extInfoForm.unitLinkMan }"/>
                                            </c:if>
                                            <c:if test="${!employmentFlag}">
                                                ${extInfoForm.unitLinkMan }
                                                <input type="hidden" name="eduUserExtInfoForm.unitLinkMan" value="${extInfoForm.unitLinkMan}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <th><span style="color: red;">*</span>&#12288;<span >就业单位联系人电话：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.unitLinkManPhone" value="${extInfoForm.unitLinkManPhone }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${employmentFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.unitLinkManPhone" value="${extInfoForm.unitLinkManPhone }"/>
                                            </c:if>
                                            <c:if test="${!employmentFlag}">
                                                ${extInfoForm.unitLinkManPhone }
                                                <input type="hidden" name="eduUserExtInfoForm.unitLinkManPhone" value="${extInfoForm.unitLinkManPhone}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                    <th><span style="color: red;">*</span>&#12288;<span >就业单位联系人邮箱：</span></th>
                                    <td>
                                        <c:if test="${roleFlag!=GlobalConstant.RES_ROLE_SCOPE_DOCTOR && !isFeeMaster }">
                                            <input type="text" class="inputText" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.unitLinkManEmail" value="${extInfoForm.unitLinkManEmail }"/>
                                        </c:if>
                                        <c:if test="${roleFlag==GlobalConstant.RES_ROLE_SCOPE_DOCTOR ||isFeeMaster }">
                                            <c:if test="${employmentFlag}">
                                                <input type="text" class="notBlank inputText validate[required]" style="text-align: left;width: 140px;" name="eduUserExtInfoForm.unitLinkManEmail" value="${extInfoForm.unitLinkManEmail }"/>
                                            </c:if>
                                            <c:if test="${!employmentFlag}">
                                                ${extInfoForm.unitLinkManEmail }
                                                <input type="hidden" name="eduUserExtInfoForm.unitLinkManEmail" value="${extInfoForm.unitLinkManEmail}">
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div><div style="display:none;overflow: auto;" id="printDivIframe" name="printDivIframe"></div>
</body>
</html>