<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/jsp/gzzyjxres/htmlhead-gzzyjxres.jsp">
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<style>

</style>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>

    $(function(){
        if('${stuBatId}' == "Y"){
            $(".stepA4").click();
            $(".stepA3").prop("onclick",null).off("click");
            $("input").attr("disabled","true");
            $(".btn_green").attr("disabled",false);
            $("textarea").attr("disabled","true");
            $("input[type=file]").attr("disabled","true");
            $(".opBtn").attr("disabled","true");
            $("select").attr("disabled","true");
            $("#batchFlow").attr("disabled",false);
            $(".btn").hide();
            $(".save_btn").hide();
            $(".opBtn").hide();
            $("font").hide();
            $("#imageFileSpan").hide();
            return;
        }
        var formHaveSubmit1 = $("#formHaveSubmit1").val();
        var formHaveSubmit2 = $("#formHaveSubmit2").val();
        var formHaveSubmit3 = $("#formHaveSubmit3").val();
        if(formHaveSubmit1 != '${GlobalConstant.FLAG_Y}'){
            $(".stepA2").prop("onclick",null).off("click");
            $(".stepA3").prop("onclick",null).off("click");
            $(".stepA4").prop("onclick",null).off("click");
        }
        if(formHaveSubmit1 == '${GlobalConstant.FLAG_Y}' && formHaveSubmit2 != '${GlobalConstant.FLAG_Y}'){
            $("#infoPage2").click();
            $(".stepA2").prop("onclick",null).off("click");
            $(".stepA3").prop("onclick",null).off("click");
            $(".stepA4").prop("onclick",null).off("click");
        }
        if(formHaveSubmit2 == '${GlobalConstant.FLAG_Y}' && formHaveSubmit3 != '${GlobalConstant.FLAG_Y}'){
            $("#infoPage3").click();
            $(".stepA2").prop("onclick",null).off("click");
            $(".stepA3").prop("onclick",null).off("click");
            $(".stepA4").prop("onclick",null).off("click");
        }
        if(formHaveSubmit3 == '${GlobalConstant.FLAG_Y}' && '${registerFormUri eq GlobalConstant.FLAG_Y}' != "true"){
            $(".stepA2").click();
            $(".stepA4").prop("onclick",null).off("click");
        }
        if('${registerFormUri eq GlobalConstant.FLAG_Y}' == "true"){
            $(".stepA3").click();
            $(".stepA4").prop("onclick",null).off("click");
        }
    });


    function showPage(page,resp)
    {
       if(page == 'infoDiv1'){
            $("#infoDiv1").show();
            $("#infoDiv2").hide();
            $("#infoDiv3").hide();
            $("#nextSpan").show();
        }else if(page == 'infoDiv2'){
           if($("#formHaveSubmit1").val() == "N"){
               jboxTip("请保存个人基本信息！");
               return false;
           }
            $("#infoDiv2").show();
            $("#infoDiv1").hide();
            $("#infoDiv3").hide();
            $("#nextSpan").show();
        }else if(page == 'infoDiv3'){
           if($("#formHaveSubmit1").val() == "N"){
               jboxTip("请保存个人基本信息！");
               return false;
           }
           if($("#formHaveSubmit2").val() == "N"){
               jboxTip("请保存个人社会信息！");
               return false;
           }
            $("#infoDiv3").show();
            $("#infoDiv1").hide();
            $("#infoDiv2").hide();
            $("#nextSpan").hide();
        }else {
           if($("#infoDiv1").is(":hidden")){
               if($("#formHaveSubmit2").val() == "N"){
                   jboxTip("请保存个人社会信息！");
                   return false;
               }else {
                   $("#infoDiv3").show();
                   $("#infoDiv1").hide();
                   $("#infoDiv2").hide();
                   $("#nextSpan").hide();
                   $("#infoPage2").removeClass("spanClick");
                   $("#infoPage2").addClass("spanNotClick");
                   $("#infoPage3").removeClass("spanNotClick");
                   $("#infoPage3").addClass("spanClick");
                   return;
               }
           }else {
               if($("#formHaveSubmit1").val() == "N"){
                   jboxTip("请保存个人基本信息！");
                   return false;
               }else {
                   $("#infoDiv2").show();
                   $("#infoDiv1").hide();
                   $("#infoDiv3").hide();
                   $("#nextSpan").show();
                   $("#infoPage1").removeClass("spanClick");
                   $("#infoPage1").addClass("spanNotClick");
                   $("#infoPage2").removeClass("spanNotClick");
                   $("#infoPage2").addClass("spanClick");
                   return;
               }
           }
        }
        $(".spanInfo").removeClass("spanClick");
        $(".spanInfo").addClass("spanNotClick");
        $(resp).removeClass("spanNotClick");
        $(resp).addClass("spanClick");
    }

    $(document).ready(function(){
        $('.datepicker').datepicker();
        <c:if test="${user.cretTypeId eq 'Shenfenzheng'}">
        changeIdNo();
        </c:if>
//            var idNo = $('#idNo').val();
//            var birthDay = idNo.substr(6,4)+"-"+idNo.substr(10,2)+"-"+idNo.substr(12,2);
//            $('#userBirthday').val(birthDay);
//            var r = $('#userBirthday').val().match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
//            if(r==null) return false;
//            var d= new Date(r[1],r[3]-1,r[4]);
//            if(d.getFullYear()==r[1] && (d.getMonth()+1)==r[3] && d.getDate()==r[4]){
//                var Y = new Date().getFullYear();
//                $('#userAge').val(Y-r[1]);
//            }
    });
    /**
     * 上传证书及文件
     */
    function uploadFile(type, typeName) {
        jboxOpen("<s:url value='/inx/gzzyjxres/uploadFile'/>?operType=" + type, "上传" + typeName, 450, 150);
    }
    /**
     * 删除证书及文件
     */
    function delFile(type) {
        jboxConfirm("确认删除？", function () {
            $("#" + type + "Del").hide();
            $("#" + type + "Span").hide();
            $("#" + type).html("上&#12288;传");
            $("#" + type + "Value").val("");
            $("#" + type).addClass("validate[required]");
        });
    }
    var len;
    $(document).ready(function(){
         len=$("#speTb tr").length==null || ''?0:$("#speTb tr").length;
    });


    String.prototype.htmlFormartById = function (data) {
        var html = this;
        for (var key in data) {
            var reg = new RegExp('\\{' + key + '\\}', 'g');
            html = html.replace(reg, data[key]);
        }
        return html;
    }

    /**
     * 增加工作经历
     */
    function add(tb) {
        if(tb=='spe'){
            if(len>='3'){
                jboxTip("最多只能添加3个！") ;
                return;
            }
        }

        $("#add_a").remove();
        var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
        var index = $("#" + tb + "Tb tr").length;
        cloneTr.html(cloneTr.html().htmlFormartById({"index": index}));
        $("#" + tb + "Tb").append(cloneTr);
        $('.datepicker').datepicker();
        if(tb=='spe'){
            len+=1;
        }
    }


    /**
     * 删除工作经历
     */
    function delTr(tb) {
        var checkboxs = $("input[name='" + tb + "Ids']:checked");
        if (checkboxs.length == 0) {
            jboxTip("请勾选要删除的！");
            return false;
        }

        jboxConfirm("确认删除?", function () {
            var trs = $('#' + tb + 'Tb').find(':checkbox:checked');
            $.each(trs, function (i, n) {
                $(n).parent('td').parent("tr").remove();
            });

            var reg = new RegExp('\\[\\d+\\]', 'g');
            $("#" + tb + "Tb tr").each(function (i) {
                $("[name]", this).each(function () {
                    this.name = this.name.replace(reg, "[" + i + "]");
                });
            });
            len-=checkboxs.length;
        });


    }
    /**
     * 删除其他资质证书
     */
    function delRegTr(obj) {
        jboxConfirm("确认删除?", function () {
            $(obj).parent('td').parent("tr").remove();
            var reg = new RegExp('\\[\\d+\\]', 'g');
            $("#credentialsTb tr").each(function (i) {
                $("[name]", this).each(function () {
                    this.name = this.name.replace(reg, "[" + i + "]");
                });
            });
        });
    }
    /**
     * 上传头像
     */
    function uploadImage() {
        $.ajaxFileUpload({
            url: "<s:url value='/sys/user/userHeadImgUpload'/>?userFlow=${user.userFlow}",
            secureuri: false,
            fileElementId: 'imageFile',
            dataType: 'json',
            success: function (data, status) {
                if (data.indexOf("success") == -1) {
                    jboxTip(data);
                } else {
                    jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
                    var arr = new Array();
                    arr = data.split(":");
                    $("#userImg").val(arr[1]);
                    var url = "${sysCfgMap['upload_base_url']}/" + arr[1];
                    $("#showImg").attr("src", url);
                    $("#headimgurl").val(arr[1]);
                    $('#imageFile').prev('span').find('a').text("重新上传");
                    $('#imageFile').prev('span').removeClass("validate[required]");
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
    //网上报名
    function search(stuBatId){
        if(!stuBatId){
            stuBatId = $('#batchFlow').val();
        }
        var url = "<s:url value='/gzzyjxres/doctor/singup?batchFlow='/>"+stuBatId;
        jboxLoad("content", url, true);
    }
    //下载申请表
    function printApplForm(batchFlow,templeteName){
//        $('#registerFormUri').removeClass("validate[required]");
        if(false == $("#doctorForm").validationEngine("validate")){
            return false;
        }
        if('${batchFlow}' == ""){
            jboxInfo("请等待管理员设置系统默认批次！");
            return false;
        }
        if(templeteName=='cd'){
            templeteName = "成都中医药大学附属医院/四川省中医院";
        }else if(templeteName=='xz'){
            templeteName = "徐州中心医院";
        }
        jboxPost("<s:url value='/gzzyjxres/doctor/valideExist'/>",{"batchFlow":batchFlow},function(resp){
            if(null != resp && resp != ""){
                jboxTip("下载中,请稍等...");
                var url = '<s:url value="/gzzyjxres/hospital/printApplForm?resumeFlow="/>'+resp+"&printFlag=doctor"+"&templeteName="+encodeURI(encodeURI(templeteName));
                window.location.href = url;
            }
        },null,false);
    }

    function changeAge(obj){
        if("${pdfn:getCurrDate()}" < $(obj).val()){
            jboxTip("出生日期必须小于当前日期");
            $(obj).val("");
        }else{
            var r = $(obj).val().match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
            if(r==null) return false;
            var d= new Date(r[1],r[3]-1,r[4]);
            if(d.getFullYear()==r[1] && (d.getMonth()+1)==r[3] && d.getDate()==r[4]){
                var Y = new Date().getFullYear();
                $('#userAge').val(Y-r[1]);
            }
        }
    }

    function changeIdNo(){
        var idNo = $('#idNo').val();
        var birthDay = idNo.substr(6,4)+"-"+idNo.substr(10,2)+"-"+idNo.substr(12,2);
        $('#userBirthday').val(birthDay);
        var r = $('#userBirthday').val().match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
        if(r==null) return false;
        var d= new Date(r[1],r[3]-1,r[4]);
        if(d.getFullYear()==r[1] && (d.getMonth()+1)==r[3] && d.getDate()==r[4]){
            var Y = new Date().getFullYear();
            $('#userAge').val(Y-r[1]);
        }

    }

    function saveInfo(formId){
        if(false == $("#"+formId).validationEngine("validate")){
            return;
        }

        var titleName = $("#titleId").find("option:selected").text();
       $("#titleName").val(titleName);

        if(formId == 'studyForm'){
            if($("#speTb").children().length<=0)
            {
                jboxTip("请至少添加一条专业信息！");
                return;
            }
        }
        if(formId == "socialForm"){
            var beT = $("#maxEduBdate").val();
            var enT = $("#maxEduEdate").val();
            if(beT > enT){
                jboxTip("最高学历结束时间不能小于开始时间！");
                return;
            }
            if($("#workTb").children().length<=0)
            {
                jboxTip("请至少添加一条工作经验！");
                return;
            }
            if($("#projectTb").children().length<=0&&$("#thesisTb").children().length<=0)
            {
                jboxTip("请至少添加一条科研信息！");
                return;
            }
            if($("#educationTb").children().length<=0)
            {
                jboxTip("请至少添加一条学历信息！");
                return;
            }

        }
        var stuBatId = $("#batchFlow").find("option:selected").val();
        jboxEndLoading();
        jboxPost("<s:url value='/gzzyjxres/doctor/saveSingupInfo?stuBatId='/>"+stuBatId,$("#"+formId).serialize(),function(resp){
            var data = eval("("+resp+")");
            var operResult = data['operResult'];
            if(operResult == '${GlobalConstant.OPRE_SUCCESSED}'){
                if(data['formHaveSubmit1'] == '${GlobalConstant.FLAG_Y}'){
                    $("#formHaveSubmit1").val("Y");
                    search(stuBatId);
                    jboxEndLoading();
                    $("#infoPage2").click();
                }
                if(data['formHaveSubmit2'] == '${GlobalConstant.FLAG_Y}'){
                    $("#formHaveSubmit2").val("Y");
                    search(stuBatId);
                    jboxEndLoading();
                    $("#infoPage3").click();
                }
                if(data['formHaveSubmit3'] == '${GlobalConstant.FLAG_Y}'){
                    jboxEndLoading();
                    search(stuBatId);
                }
            }
            jboxEndLoading();
            jboxTip(operResult);
        },null,false);
    }

    function showBigStep(resp){
        if(resp == "stepA1"){
            $(".stepA2").removeClass("active");
            $(".stepA3").removeClass("active");
            $(".stepA4").removeClass("active");
            $("#div_table_1").show();
            $("#div_table_2").hide();
            $("#div_table_3").hide();
            $("#div_table_4").hide();
        }else if(resp == "stepA2"){
            $(".stepA2").addClass("active");
            $(".stepA3").removeClass("active");
            $(".stepA4").removeClass("active");
            $("#div_table_1").hide();
            $("#div_table_2").show();
            $("#div_table_3").hide();
            $("#div_table_4").hide();
        }else if(resp == "stepA3"){
            $(".stepA2").addClass("active");
            $(".stepA3").addClass("active");
            $(".stepA4").removeClass("active");
            $("#div_table_1").hide();
            $("#div_table_2").hide();
            $("#div_table_3").show();
            $("#div_table_4").hide();
        }else{
            $(".stepA2").addClass("active");
            $(".stepA3").addClass("active");
            $(".stepA4").addClass("active");
            $("#div_table_1").hide();
            $("#div_table_2").hide();
            $("#div_table_3").hide();
            $("#div_table_4").show();
        }
    }

    function changeStep(resp){
        $("."+resp).click();
    }

    function submiteApply(){
        var stuBatId = $("#batchFlow").find("option:selected").val();
        var registerFormUriValue = $("#registerFormUriValue").val();
        if(!registerFormUriValue){
            jboxTip("你还未上传申请表！请上传！");
            return;
        }
        jboxConfirm("确认提交吗？提交后申请信息不可修改！" , function(){
            jboxStartLoading();
            jboxPost("<s:url value='/gzzyjxres/doctor/saveSingupInfo?flag=true&stuBatId='/>"+stuBatId+"&registerFormUriValue="+registerFormUriValue,null,function(resp){
                if(resp=="1"){
                    jboxTip("保存成功");
                    search(stuBatId);
                    //自动跳转到主页（仅第一次点击提交）
                    location.href="/pdsci/gzzyjxres/doctor/index";

                }else{
                    jboxTip(resp);
                }
            },null,false);
        })

    }
    //加载职称
    function loadTitle(){
        var titleTypeId = $("#titleTypeId").val();
        if(titleTypeId=='' || titleTypeId==null){
                return;
        }
        //首先要刷新一下职称下拉框
        $("#titleId").empty();

        var paramTitleId = '${stuUser.titleId}';

        var title = $("#titleId");


        var titleType = 'GzzyjxUserTitle.'+$("#titleTypeId").val();
        if (titleType) {
            var url = "<s:url value='/gzzyjxres/doctor/loadTitle'/>?titleTypeId=" + titleType;
//            jboxStartLoading();
            jboxGet(url, null, function (data) {
                for (var i=0;i<data.length;i++) {
                    var sel = "";
                    if (data[i].dictId == paramTitleId) {
                        sel = "selected";
                    }
                    title.append('<option value="' + data[i].dictId + '" ' + sel + '>' + data[i].dictName + '</option>');

                }
            }, null, false);

        }
    }

    $(document).ready(function(){
        loadTitle();
    });


    function reApply(){
        var stuBatId = $("#batchFlow").find("option:selected").val()
        jboxPost("<s:url value='/gzzyjxres/doctor/saveSingupInfo?flag=reApply&stuBatId='/>"+stuBatId,null,function(resp){
            if(resp=="1"){
                jboxTip("操作成功");
                search(stuBatId);
            }else{
                jboxTip(resp);
            }
        },null,false);
    }
</script>
<div id="singupContent">
    <div id='docTypeForm'>
        <p id="errorMsg" style="color: red;"></p>
        <div class="main_hd"><h2 class="underline">网上报名</h2></div>
        <div class="main_bd">
            <div class="div_table">
                <div class="score_frame" style="width: 960px">
                    <div class="div_table">
                        <h4>进修批次&#12288;&#12288;&#12288;&#12288;&#12288;
                            <select autocomplete="off" class="select" id="batchFlow" style="width: 160px;margin-left: 5px;" onchange="search()">
                                <c:forEach items="${batchLst}" var="dict">
                                    <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                                </c:forEach>
                            </select>
                        </h4>
                    </div>
                    <div class="div_table" style="padding:20px 0px 0px;">
                        <div class="stepDiv">
                            <a href="#" class="stepA1 active" onclick="showBigStep('stepA1');" style="cursor:default">申请信息填写</a>
                            <a href="#" class="stepA2" onclick="showBigStep('stepA2');" style="cursor:default">下载申请表</a>
                            <a href="#" class="stepA3" onclick="showBigStep('stepA3');" style="cursor:default">提交申请表</a>
                            <a href="#" class="stepA4" onclick="showBigStep('stepA4');" style="cursor:default">审核结果</a>
                        </div>
                    </div>
                    <div class="div_table parentDiv" id="div_table_1" >
                        <form id="fileForm" method="post" enctype="multipart/form-data">
                            <input type="file" id="file" name="file" class="validate[required]" style="display: none;"/>
                        </form>
                        <div id="infoDiv1" class="infoDiv search_table doctorInfo" style="margin-top:20px;">
                            <input class="havaSubmit" id="formHaveSubmit1" hidden value="${empty formHaveSubmit1 ? 'N' : formHaveSubmit1}">
                            <form id="basicForm" style="position:relative;">
                                <h4 style="width: 100%">个人基本信息</h4>
                                <table style="width: 100%;" class="searchTable" >
                                    <colgroup>
                                        <col width="15%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="25%"/>
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>姓名：
                                        </td>
                                        <td>
                                            <input class='input validate[required]' type="text" name="user.userName"  value="${extInfo.userName}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                        <td rowspan="6" style="text-align: center;padding-top:5px;">
                                            <img src="${sysCfgMap['upload_base_url']}/${extInfo.userHeadImg}" id="showImg" width="110px" height="130px" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
                                            <span id="imageFileSpan" style="cursor: pointer; display:block;" class="${empty extInfo.userHeadImg?'validate[required]':''}">
                                                [<a onclick="$('#imageFile').click();">${empty extInfo.userHeadImg?'上传照片':'重新上传'}</a>]
                                            </span>
                                            <input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
                                            <input type="hidden" id="headimgurl" name="user.userHeadImg" value="${extInfo.userHeadImg}"/>
                                            <span style="color:red">请上传1寸大小的照片</span>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>性别：
                                        </td>
                                        <td>

                                            <label>
                                                <input type="radio" class='validate[required]' name="user.sexId" <c:if test="${extInfo.sexId eq 'Man' || extInfo.sexId ne 'Woman'}">checked="checked"</c:if> value="Man"/>男
                                            </label>&nbsp;
                                            <label>
                                                <input type="radio" class='validate[required]' name="user.sexId" <c:if test="${extInfo.sexId eq 'Woman'}">checked="checked"</c:if> value="Woman"/>女
                                            </label>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>证件号码：
                                        </td>
                                        <td>
                                            <input id="idNo" readonly="readonly" class='input validate[required]' onchange="changeIdNo(this);" type="text" name="user.idNo"  value="${extInfo.idNo}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>出生日期：
                                        </td>
                                        <td>
                                            <input type="text" id="userBirthday" name="user.userBirthday" value="${extInfo.userBirthday}"  class='input validate[required]' onchange="changeAge(this)" <%--readonly="readonly"--%> style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>年龄：
                                        </td>
                                        <td>
                                            <input class='input validate[required]' id="userAge" type="text" name="stuUser.userAge" value="${stuUser.userAge}" <%--readonly="readonly"--%> style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>民族：
                                        </td>
                                        <td>
                                            <select id="nationId" name="user.nationId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <c:forEach items="${userNationEnumList}" var="nation">
                                                    <option value="${nation.id}" ${extInfo.nationId eq nation.id?'selected':''}>${nation.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>政治面貌：
                                        </td>
                                        <td>
                                            <select id="politicsStatusId" name="user.politicsStatusId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <c:forEach items="${politicsAppearanceEnumList}" var="statu">
                                                    <option value="${statu.id}" ${user.politicsStatusId eq statu.id?'selected':''}>${statu.name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>籍贯：
                                        </td>
                                        <td colspan="4">
                                            <input type="hidden" id="nativePlaceProvName" name="user.nativePlaceProvName" value="${user.nativePlaceProvName}"/>
                                            <input type="hidden" id="nativePlaceCityName" name="user.nativePlaceCityName" value="${user.nativePlaceCityName}"/>
                                            <div id="native">
                                                <select id="nativePlaceProvId" name="user.nativePlaceProvId" style="width: 157px;" class="select notBlank province inputText validate[required]" data-value="${user.nativePlaceProvId}" data-first-title="选择省" onchange="changeSourceProvincesName('nativePlaceProv');"></select>
                                                <select id="nativePlaceCityId" name="user.nativePlaceCityId" style="width: 157px;" class="select notBlank city inputText validate[required]" data-value="${user.nativePlaceCityId}" data-first-title="选择市" onchange="changeSourceProvincesName('nativePlaceCity');"></select>
                                            </div>
                                            <script type="text/javascript">
                                                function changeSourceProvincesName(obj){
                                                    var sname = $("#"+obj+"Id").find("option:selected").text();
                                                    $("#"+obj+"Name").val(sname);
                                                }
                                                // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                                                $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                                                $.cxSelect.defaults.nodata = "none";

                                                $("#native").cxSelect({
                                                    selects : ["province", "city"],
                                                    //selects : ["province"],
                                                    nodata : "none",
                                                    firstValue : ""
                                                });
                                            </script>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>手机号码：
                                        </td>
                                        <td>
                                            <input class='input validate[required]' type="text" name="user.userPhone"  value="${extInfo.userPhone}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>个人专业：
                                        </td>
                                        <td colspan="4">
                                            <input class="input validate[required]" type="text" name="user.userMajor"  value="${extInfo.userMajor}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                       <%-- <td></td>
                                        <td></td>
                                        <td></td>--%>
                                    </tr>
                                  <%--  <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>电子邮箱：
                                        </td>
                                        <td>
                                            <input readonly="readonly" class='input validate[required]' type="text" name="user.userEmail"  value="${extInfo.userEmail}" style="width: 150px;margin-left: 0px"/>
                                        </td>

                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>--%>
                                    <tr>
                                        <td style="text-align: center" colspan="5">
                                            <input type="button" class="btn_green save_btn" onclick="saveInfo('basicForm');" value="保存此页信息"/>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                        <div id="infoDiv2" class="infoDiv search_table doctorInfo" style="margin-top:20px;display: none;" >
                            <input class="havaSubmit" id="formHaveSubmit2" hidden value="${empty formHaveSubmit2 ? 'N' : formHaveSubmit2}">
                            <form id="socialForm"  style="position:relative;" >
                                <h4 style="width: 100%">个人社会信息</h4>
                                <table style="width: 100%;" class="searchTable" >
                                    <colgroup>
                                        <col width="16%"/>
                                        <col width="24%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>参加工作时间：
                                        </td>
                                        <td>
                                            <input class='input validate[required] datepicker' type="text" name="stuUser.jobYear"  value="${stuUser.jobYear}" style="width: 150px;margin-left: 0px"/>
                                        <td class="td_left">
                                            <font color="red">*</font>选送单位：
                                        </td>
                                        <td>
                                            <input class="input validate[required]" type="text" name="stuUser.sendComName"  value="${stuUser.sendComName}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>医院等级：
                                        </td>
                                        <td>
                                            <select name="stuUser.hospitalLevelId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <c:forEach items="${dictTypeEnumHospitalRankList}" var="dict">
                                                    <option value="${dict.dictId}" ${stuUser.hospitalLevelId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>邮政编码：
                                        </td>
                                        <td>
                                            <input type='text'style="width: 150px;margin-left: 0px" class="input validate[required]" name="extInfo.postCodes" value="${extInfo.postCodes}"/>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>选送单位详细地址：
                                        </td>
                                        <td  colspan="4">
                                            <input type='text'style="width: 524px;margin-left: 0px" class="input validate[required]" name="stuUser.sendComAddress" value="${stuUser.sendComAddress}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <%--<td class="td_left">
                                            <font color="red">*</font>所在科室：
                                        </td>
                                        <td>
                                            <input class="input validate[required]" type="text" name="stuUser.deptName"  value="${stuUser.deptName}" style="width: 150px;margin-left: 0px"/>
                                        </td>--%>
                                       <td class="td_left">
                                           <font color="red">*</font>学位：
                                       </td>
                                       <td>
                                          <select id="degree" name="extInfo.degreeId" class="validate[required] select" style="width: 157px;margin-left: 0px;">
                                            <option value="">请选择</option>
                                            <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
                                            <option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.degreeId }">selected</c:if>>${dict.dictName}</option>
                                            </c:forEach>
                                          </select>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>职务：
                                        </td>
                                        <td>
                                            <input class="input validate[required]" type="text" name="stuUser.postName"  value="${stuUser.postName}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>职称类别：
                                        </td>
                                        <td>
                                            <select id="titleTypeId" name="extInfo.titleTypeId" onchange="loadTitle()" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <c:forEach items="${dictTypeEnumGzzyjxUserTitleList}" var="dict">
                                                    <c:set var="dictKey" value="${dict.dictId}"></c:set>
                                                    <option value="${dict.dictId}" ${extInfo.titleTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>职称：
                                        </td>
                                        <td>
                                            <select id="titleId" name="stuUser.titleId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <c:forEach items="${applicationScope[dictKey]}" var="sDict">
                                                    <option value="${sDict.dictId}" ${stuUser.titleId eq sDict.dictId?'selected':''}>${sDict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="hidden" name="stuUser.titleName" id="titleName">
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>执业资格：
                                        </td>
                                        <td>
                                            <select name="stuUser.certifiedTypeId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <c:forEach items="${dictTypeEnumPracticeGenreList}" var="dict">
                                                    <option value="${dict.dictId}" ${stuUser.certifiedTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>最高学历：
                                        </td>
                                        <td>
                                            <select name="stuUser.maxEduId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
                                                    <option value="${dict.dictId}" ${stuUser.maxEduId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <%--<tr>--%>
                                        <%--<td class="td_left">--%>
                                            <%--<font color="red">*</font>是否熟练电脑：--%>
                                        <%--</td>--%>
                                        <%--<td>--%>
                                            <%--<select name="stuUser.isComputer" class="select validate[required]" style="width: 157px;margin-left: 0px;">--%>
                                                <%--<option value="">请选择</option>--%>
                                                <%--<option value="Y" ${stuUser.isComputer eq 'Y'?'selected':''}>是</option>--%>
                                                <%--<option value="N" ${stuUser.isComputer eq 'N'?'selected':''}>否</option>--%>
                                            <%--</select>--%>
                                        <%--</td>--%>
                                        <%--<td></td>--%>
                                        <%--<td></td>--%>
                                        <%--<td></td>--%>
                                    <%--</tr>--%>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>毕业学校：
                                        </td>
                                        <td>
                                            <input class="input validate[required]" type="text" name="stuUser.schoolName"  value="${stuUser.schoolName}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>毕业专业：
                                        </td>
                                        <td>
                                            <input class="input validate[required]" type="text" name="stuUser.schoolSpeName"  value="${stuUser.schoolSpeName}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>最高学历开始时间：
                                        </td>
                                        <td>
                                            <input id="maxEduBdate" type='text' class='input validate[required] datepicker' name="stuUser.maxEduBdate" value="${stuUser.maxEduBdate}" readonly="readonly" style="width: 146px;margin-left: 0px" />
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>最高学历结束时间：
                                        </td>
                                        <td>
                                            <input id="maxEduEdate" type='text' class='input validate[required] datepicker' name="stuUser.maxEduEdate" value="${stuUser.maxEduEdate}" readonly="readonly" style="width: 146px;margin-left: 0px" />
                                        </td>
                                        <td></td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div>
                                    <h4 style="width: 100%;float: left">工作经历（含外单位进修经历）<span style="float: right;padding-right: 20px">
                                        <img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('work')"/>&#12288;
                                        <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('work');"/>
                                    </span>
                                    </h4>

                                </div>
                                <table style="width: 100%;" class="grid" >
                                    <colgroup>
                                        <col width="16%"/>
                                        <col width="24%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                    </colgroup>

                                    <tr style="font-weight: bold;">
                                        <th style="text-align: center;">选择</th>
                                        <th style="text-align: center;">起止时间</th>
                                        <th style="text-align: center;">工作单位</th>
                                        <th style="text-align: center;">从事工作</th>
                                        <th style="text-align: center;">职务</th>
                                    </tr>
                                    <tbody id="workTb">
                                    <c:forEach items="${workDateList}" var="workDate">
                                        <c:forEach var="resume" items="${extInfo.workResumeList}" varStatus="status">
                                            <c:if test="${workDate eq resume.clinicalRoundDate}">
                                                <tr>
                                                    <td><input type="checkbox" name="workIds"/></td>
                                                    <td>
                                                        <input type='text' class='validate[required] input' name="extInfo.workResumeList[${status.index}].clinicalRoundDate" placeholder="例:2017.01-2017.09" value="${resume.clinicalRoundDate}" style="width: 140px;" />
                                                    </td>
                                                    <td>
                                                        <input type='text' class='validate[required] input' name="extInfo.workResumeList[${status.index}].hospitalName" value="${resume.hospitalName}" style="width: 140px;"/>
                                                    </td>
                                                    <td>
                                                        <input type='text' class='validate[required] input' name="extInfo.workResumeList[${status.index}].workDescription" value="${resume.workDescription}" style="width: 140px;"/>
                                                    </td>
                                                    <td>
                                                        <input type='text' class='validate[required] input' name="extInfo.workResumeList[${status.index}].postName" value="${resume.postName}" style="width: 140px;"/>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div>
                                    <h4 style="width: 100%;float: left">学历信息（从高中学历写起）<span style="float: right;padding-right: 20px">
                                        <img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('education')"/>&#12288;
                                        <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('education');"/>
                                    </span></h4>

                                </div>
                                <table style="width: 100%;" class="grid" >
                                    <colgroup>
                                        <col width="7%"/>
                                        <col width="23%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                    </colgroup>

                                    <tr style="font-weight: bold;">
                                        <th style="text-align: center;">选择</th>
                                        <th style="text-align: center;">起止时间</th>
                                        <th style="text-align: center;">学校名称</th>
                                        <th style="text-align: center;">专业名称</th>
                                        <th style="text-align: center;">学制</th>
                                        <th style="text-align: center;">学历</th>
                                        <th style="text-align: center;">学位</th>
                                    </tr>
                                    <tbody id="educationTb">
                                    <%--按时间降序展示--%>
                                    <c:forEach items="${eduDateList}" var="eduDate">
                                        <c:forEach var="resume" items="${extInfo.educationList}" varStatus="status">
                                            <c:if test="${eduDate eq resume.eduRoundDate}">
                                                <tr>
                                                    <td><input type="checkbox" name="educationIds"/></td>
                                                    <td>
                                                        <input type='text' class='validate[required] input' name="extInfo.educationList[${status.index}].eduRoundDate" placeholder="例:2017.01-2017.09" value="${resume.eduRoundDate}" style="width: 95%;" />
                                                    </td>
                                                    <td>
                                                        <input type='text' class='validate[required] input' name="extInfo.educationList[${status.index}].schoolName" value="${resume.schoolName}" style="width: 95%;"/>
                                                    </td>
                                                    <td>
                                                        <input type='text' class='validate[required] input' name="extInfo.educationList[${status.index}].speName" value="${resume.speName}" style="width: 95%;"/>
                                                    </td>
                                                    <td>
                                                        <input type='text' class='validate[required] input' name="extInfo.educationList[${status.index}].length" value="${resume.length}" style="width: 95%;"/>
                                                    </td>
                                                    <td>
                                                        <input type='text' class='validate[required] input' name="extInfo.educationList[${status.index}].education" value="${resume.education}" style="width: 95%;"/>
                                                    </td>
                                                    <td>
                                                        <input type='text' class='validate[required] input' name="extInfo.educationList[${status.index}].degree" value="${resume.degree}" style="width: 95%;"/>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <div>
                                    <h4 style="width: 100%;float: left">科研信息</h4>

                                </div>
                                <table style="width: 100%;" class="grid" >
                                    <colgroup>
                                        <col width="10%"/>
                                        <col width="20%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="20%"/>
                                    </colgroup>
                                    <tr style="font-weight: bold;">
                                        <th style="text-align: left;" colspan="7">一、课题<span style="float: right;padding-right: 20px">
                                        <img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('project')"/>&#12288;
                                        <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('project');"/>
                                    </span></th>
                                    </tr>
                                    <tr style="font-weight: bold;">
                                        <th style="text-align: center;">选择</th>
                                        <th style="text-align: center;">标题内容</th>
                                        <th style="text-align: center;">作者名次</th>
                                        <th style="text-align: center;">课题级别</th>
                                        <th style="text-align: center;">课题获批时间</th>
                                        <th style="text-align: center;">合同书号</th>
                                        <th style="text-align: center;">获奖情况（何部门批准<br/>及奖励名称、等级）</th>
                                    </tr>
                                    <tbody id="projectTb">
                                    <c:forEach var="project" items="${extInfo.projectList}" varStatus="status">
                                        <tr>
                                            <td><input type="checkbox" name="projectIds"/></td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.projectList[${status.index}].titleName" value="${project.titleName}" style="width: 140px;" />
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.projectList[${status.index}].authorRank" value="${project.authorRank}" style="width: 60px;"/>
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.projectList[${status.index}].projectLevel" value="${project.projectLevel}" style="width: 80px;"/>
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.projectList[${status.index}].projectDate" value="${project.projectDate}" style="width: 140px;"/>
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.projectList[${status.index}].pubNumber" value="${project.pubNumber}" style="width: 90px;"/>
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.projectList[${status.index}].awardSituation" value="${project.awardSituation}" style="width: 140px;"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                                <table style="width: 100%;" class="grid" >
                                    <colgroup>
                                        <col width="8%"/>
                                        <col width="20%"/>
                                        <col width="8%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="8%"/>
                                        <col width="20%"/>
                                        <col width="8%"/>
                                    </colgroup>
                                    <tr style="font-weight: bold;">
                                        <th style="text-align: left;" colspan="8">二、论文<span style="float: right;padding-right: 20px">
                                        <img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('thesis')"/>&#12288;
                                        <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('thesis');"/>
                                    </span></th>
                                    </tr>
                                    <tr style="font-weight: bold;">
                                        <th style="text-align: center;">选择</th>
                                        <th style="text-align: center;">论文题目</th>
                                        <th style="text-align: center;">作者名次</th>
                                        <th style="text-align: center;">杂志名称</th>
                                        <th style="text-align: center;">杂志名称发表时间</th>
                                        <th style="text-align: center;">刊号</th>
                                        <th style="text-align: center;">获奖情况（何部门批准<br/>及奖励名称、等级）</th>
                                        <th style="text-align: center;">是否SCI或核心</th>
                                    </tr>
                                    <tbody id="thesisTb">
                                    <c:forEach var="project" items="${extInfo.thesisList}" varStatus="status">
                                        <tr>
                                            <td><input type="checkbox" name="thesisIds"/></td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.thesisList[${status.index}].titleName" value="${project.titleName}" style="width: 120px;" />
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.thesisList[${status.index}].authorRank" value="${project.authorRank}" style="width: 50px;"/>
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.thesisList[${status.index}].pubName" value="${project.pubName}" style="width: 110px;"/>
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.thesisList[${status.index}].pubDate" value="${project.pubDate}" style="width: 60px;"/>
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.thesisList[${status.index}].pubNumber" value="${project.pubNumber}" style="width: 60px;"/>
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.thesisList[${status.index}].awardSituation" value="${project.awardSituation}" style="width: 120px;"/>
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.thesisList[${status.index}].isSCI" value="${project.isSCI}" style="width: 50px;"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                                <h4 style="width: 100%">证件及文件</h4>
                                <table style="width: 100%;" class="searchTable" >
                                    <colgroup>
                                        <col width="16%"/>
                                        <col width="24%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>身份证正面：</td>
                                        <td colspan="2">
                                            <span id="idNoUriSpan" style="display:${not empty extInfo.idNoUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.idNoUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="idNoUri" href="javascript:uploadFile('idNoUri','身份证正面');" class="btn ${empty extInfo.idNoUri?'validate[required]':''}">${empty extInfo.idNoUri?'':'重新'}上传</a>&#12288;
                                            <a id="idNoUriDel" href="javascript:delFile('idNoUri');" class="btn" style="${empty extInfo.idNoUri?'display:none':''}">删除</a>&#12288;
                                            <input type="hidden" id="idNoUriValue" name="extInfo.idNoUri" value="${extInfo.idNoUri}">
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>身份证反面：</td>
                                        <td colspan="2">
                                            <span id="idNoUri2Span" style="display:${not empty extInfo.idNoUri2?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.idNoUri2}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="idNoUri2" href="javascript:uploadFile('idNoUri2','身份证反面');" class="btn ${empty extInfo.idNoUri2?'validate[required]':''}">${empty extInfo.idNoUri2?'':'重新'}上传</a>&#12288;
                                            <a id="idNoUri2Del" href="javascript:delFile('idNoUri2');" class="btn" style="${empty extInfo.idNoUri2?'display:none':''}">删除</a>&#12288;
                                            <input type="hidden" id="idNoUri2Value" name="extInfo.idNoUri2" value="${extInfo.idNoUri2}">
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>手持身份证：</td>
                                        <td colspan="2">
                                            <span id="idNoUri3Span" style="display:${not empty extInfo.idNoUri3?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.idNoUri3}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="idNoUri3" href="javascript:uploadFile('idNoUri3','手持身份证');" class="btn ${empty extInfo.idNoUri3?'validate[required]':''}">${empty extInfo.idNoUri3?'':'重新'}上传</a>&#12288;
                                            <a id="idNoUri3Del" href="javascript:delFile('idNoUri3');" class="btn" style="${empty extInfo.idNoUri3?'display:none':''}">删除</a>&#12288;
                                            <input type="hidden" id="idNoUri3Value" name="extInfo.idNoUri3" value="${extInfo.idNoUri3}">
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td colspan="5" style="height: 10px"><font style="color:red;font-size: 10px">请上传身份证正、反面，以及本人正面免冠、露出肘关节手持身份证的正面拍照，共三张照片 </font></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>最高学历证书图片：</td>
                                        <td colspan="4">
                                           <%-- <input type="text" class="input validate[required]" placeholder="请填写毕业证编号" style="width: 300px;margin-left: 0px" name="extInfo.graduatedNo" value='${extInfo.graduatedNo}'/>--%>
                                            <span id="graduatedUriSpan" style="display:${!empty extInfo.graduatedUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.graduatedUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="graduatedUri" href="javascript:uploadFile('graduatedUri','最高学历证书');" class="btn ${empty extInfo.graduatedUri?'validate[required]':''}">${empty extInfo.graduatedUri?'':'重新'}上传</a>&#12288;
                                            <a id="graduatedUriDel" href="javascript:delFile('graduatedUri');" class="btn" style="${empty extInfo.graduatedUri?'display:none':''}">删除</a>&#12288;
                                               <font style="color:red;font-size: 10px">（请上传原件）</font>
                                            <input type="hidden" id="graduatedUriValue" name="extInfo.graduatedUri" value="${extInfo.graduatedUri}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>上传单位介绍信：</td>
                                        <td colspan="4">
                                            <span id="introductionUriSpan" style="display:${!empty extInfo.introductionUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.introductionUri}" target="_blank">查看附件</a>]&#12288;
                                            </span>
                                            <a id="introductionUri" href="javascript:uploadFile('introductionUri','单位介绍信');" class="btn ${empty extInfo.introductionUri?'validate[required]':''}">${empty extInfo.introductionUri?'':'重新'}上传</a>&#12288;
                                            <a id="introductionUriDel" href="javascript:delFile('introductionUri');" class="btn" style="${empty extInfo.introductionUri?'display:none':''}">删除</a>&#12288;
                                            <font style="color:red;font-size: 10px" > （请上传扫描件，请以PDF格式上传。）</font>
                                            <input type="hidden" id="introductionUriValue" name="extInfo.introductionUri" value="${extInfo.introductionUri}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>感染四项检查结果<br/>(一周内有效)：</td>
                                        <td colspan="4">
                                            <span id="testResultUriSpan" style="display:${!empty extInfo.testResultUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.testResultUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="testResultUri" href="javascript:uploadFile('testResultUri','感染四项检查结果');" class="btn ${empty extInfo.testResultUri?'validate[required]':''}">${empty extInfo.testResultUri?'':'重新'}上传</a>&#12288;
                                            <a id="testResultUriDel" href="javascript:delFile('testResultUri');" class="btn" style="${empty extInfo.testResultUri?'display:none':''}">删除</a>&#12288;
                                            <font style="color:red;font-size: 10px">（请上传原件）</font>
                                            <input type="hidden" id="testResultUriValue" name="extInfo.testResultUri" value="${extInfo.testResultUri}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>遵守纪律协议书：</td>
                                        <td colspan="4">
                                            <span id="agreementUriSpan" style="display:${!empty extInfo.agreementUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.agreementUri}" target="_blank">查看附件</a>]&#12288;
                                            </span>
                                            <a id="agreementUri" href="javascript:uploadFile('agreementUri','遵守纪律协议书');" class="btn ${empty extInfo.agreementUri?'validate[required]':''}">${empty extInfo.agreementUri?'':'重新'}上传</a>&#12288;
                                            <a id="agreementUriDel" href="javascript:delFile('agreementUri');" class="btn" style="${empty extInfo.agreementUri?'display:none':''}">删除</a>&#12288;
                                            <input type="hidden" id="agreementUriValue" name="extInfo.agreementUri" value="${extInfo.agreementUri}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="5" style="height: 10px"><font style="color: red;font-size: 10px">（请下载<a href="<s:url value='/jsp/gzzyjxres/print/agreementTemeplete.doc'/>">遵守纪律协议书模板</a>，再上传审核盖章的协议书扫描件并以PDF格式上传。）</font></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left"><font color="red" id="zgzs">*</font>医师（护士、护师）资格证书编号：</td>
                                        <td colspan="4">
                                            <input type='text' class="input validate[required]" placeholder="请填写医师（护士、护师）资格证书编号" style="width:300px;margin-left: 0px" name="extInfo.qualifiedNo" value="${extInfo.qualifiedNo}"/>
                                            <span id="qualifiedUriSpan" style="display:${!empty extInfo.qualifiedUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.qualifiedUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="qualifiedUri" href="javascript:uploadFile('qualifiedUri','医师（护士、护师）资格证');" class="btn ${empty extInfo.qualifiedUri?'validate[required]':''}">${empty extInfo.qualifiedUri?'':'重新'}上传</a>&#12288;
                                            <a id="qualifiedUriDel" href="javascript:delFile('qualifiedUri');" class="btn" style="${empty extInfo.qualifiedUri?'display:none':''}">删除</a>&#12288;
                                            <font style="color:red;font-size: 10px">（请上传原件）</font>
                                            <input type="hidden" id="qualifiedUriValue" name="extInfo.qualifiedUri" value="${extInfo.qualifiedUri}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>医师（护士）执业证书编号：</td>
                                        <td colspan="4">
                                            <input type='text' placeholder="请填写医师（护士）执业证书编号" style="width: 300px;margin-left: 0px" class="input validate[required]" name="extInfo.certifiedNo" value='${extInfo.certifiedNo}'/>
                                            <span id="certifiedUriSpan" style="display:${!empty extInfo.certifiedUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.certifiedUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="certifiedUri" href="javascript:uploadFile('certifiedUri','医师（护士）执业证');" class="btn ${empty extInfo.certifiedUri?'validate[required]':''}">${empty extInfo.certifiedUri?'':'重新'}上传</a>&#12288;
                                            <a id="certifiedUriDel" href="javascript:delFile('certifiedUri');" class="btn" style="${empty extInfo.certifiedUri?'display:none':''}">删除</a>&#12288;
                                            <font style="color:red;font-size: 10px">（请上传原件）</font>
                                            <input type="hidden" id="certifiedUriValue" name="extInfo.certifiedUri" value="${extInfo.certifiedUri}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="td_left">其它资质证书：</td>
                                        <td colspan="4">添加资质证书&#12288;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('credentials')"/></td>
                                    </tr>
                                    </tbody>

                                    <tbody id="credentialsTb">
                                    <c:forEach var="reg" items="${extInfo.regList}" varStatus="status">
                                        <tr class="yszz">
                                            <td class="td_left"><font color="red">*</font>资质证书名称：</td>
                                            <td colspan="4">
                                                <input type='text' placeholder="请填写证书名称" style="width: 300px;" class="input validate[required]" name="extInfo.regList[${status.index}].regNo" value='${reg.regNo}'/>
                                                <span id="regUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">查看图片</a>]&#12288;
                                                </span>
                                                <a href="javascript:uploadFile('regUri${status.index}','资质证书');" class="btn">${empty reg.regUri?'':'重新'}上传</a>&#12288;
                                                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                                                <input type="hidden" id="regUri${status.index}Value" name="extInfo.regList[${status.index}].regUri" value="${reg.regUri}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tbody>
                                    <tr>
                                        <td style="text-align: center" colspan="2">
                                            <input type="button" onclick="saveInfo('socialForm');" class="btn_green save_btn" value="保存此页信息"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                        <div id="infoDiv3" class="infoDiv search_table doctorInfo" style="margin-top:20px;display: none;">
                            <input class="havaSubmit" id="formHaveSubmit3" hidden value="${empty formHaveSubmit3 ? 'N' : formHaveSubmit3}">
                            <form id="studyForm" style="position:relative;">
                                <h4 style="width: 100%">进修相关信息</h4>

                                <div>
                                    <h4 style="width: 100%;float: left">进修专业（最多只能添加3个）
                                                <span style="float: right;padding-right: 20px">
                                                    <img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('spe')"/>&#12288;
                                                    <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('spe');"/>
                                                </span>
                                    </h4>
                                </div>

                                <table style="width: 100%;" class="grid" >
                                    <colgroup>
                                        <col width="20%"/>
                                        <col width="40%"/>
                                        <col width="40%"/>
                                    </colgroup>

                                    <tr style="font-weight: bold;">
                                        <th style="text-align: center;">选择</th>
                                        <th style="text-align: center;">进修专业</th>
                                        <th style="text-align: center;">进修时间</th>
                                    </tr>
                                    <tbody id="speTb">
                                    <c:forEach var="speForm" items="${extInfo.speFormList}" varStatus="status">
                                        <tr>
                                            <td><input type="checkbox" name="speIds"/></td>
                                            <td>
                                                <select name="extInfo.speFormList[${status.index}].speId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                    <option value="">请选择</option>
                                                    <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                                                        <option value="${SysDept.deptFlow}" ${speForm.speId eq SysDept.deptFlow?'selected':''}>${SysDept.deptName}</option>
                                                    </c:forEach>
                                                </select>
                                               <%-- <select name="extInfo.speFormList[${status.index}].speName" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                    <option value="">请选择</option>
                                                    <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                                        <option value="${dict.dictName}" ${speForm.speName eq dict.dictName?'selected':''}>${dict.dictName}</option>
                                                    </c:forEach>
                                                </select>--%>
                                            </td>
                                            <td>
                                                <select name="extInfo.speFormList[${status.index}].stuTimeId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                    <option value="">请选择</option>
                                                    <option value="1" <c:if test="${speForm.stuTimeId eq '1'}">selected="selected"</c:if>>1个月</option>
                                                    <option value="2" <c:if test="${speForm.stuTimeId eq '2'}">selected="selected"</c:if>>2个月</option>
                                                    <option value="3" <c:if test="${speForm.stuTimeId eq '3'}">selected="selected"</c:if>>3个月</option>
                                                    <option value="6" <c:if test="${speForm.stuTimeId eq '6'}">selected="selected"</c:if>>6个月</option>
                                                    <option value="12" <c:if test="${speForm.stuTimeId eq '12'}">selected="selected"</c:if>>12个月</option>
                                                </select>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                                <table style="width: 100%;" class="searchTable">
                                    <colgroup>
                                        <col width="18%"/>
                                        <col width="32%"/>
                                        <col width="18%"/>
                                        <col width="32%"/>
                                    </colgroup>
                                    <tbody>
                                  <%--  <tr>
                                        <td class="td_left">
                                            进修批次：
                                        </td>
                                        <td>
                                            ${batchNo}<input type="hidden" name="stuUser.stuBatId" value="${batchFlow}" />
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>--%>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>工作服：
                                        </td>
                                        <td>
                                            <select class="select validate[required]" name="extInfo.workClother" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <option value="白大衣" <c:if test="${extInfo.workClother eq '白大衣'}">selected="selected"</c:if>>白大衣</option>
                                                <option value="护士服" <c:if test="${extInfo.workClother eq '护士服'}">selected="selected"</c:if>>护士服</option>
                                            </select>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>工作服尺寸：
                                        </td>
                                        <td>
                                            <select class="select validate[required]" name="stuUser.clotherSizeId" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <option value="S" <c:if test="${stuUser.clotherSizeId eq 'S'}">selected="selected"</c:if>>S号</option>
                                                <option value="M" <c:if test="${stuUser.clotherSizeId eq 'M'}">selected="selected"</c:if>>M号</option>
                                                <option value="L" <c:if test="${stuUser.clotherSizeId eq 'L'}">selected="selected"</c:if>>L号</option>
                                                <option value="XL" <c:if test="${stuUser.clotherSizeId eq 'XL'}">selected="selected"</c:if>>XL号</option>
                                                <option value="XXL" <c:if test="${stuUser.clotherSizeId eq 'XXL'}">selected="selected"</c:if>>XXL号</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>是否住宿：
                                        </td>
                                        <td>
                                            <select class="select validate[required]" name="stuUser.isPutup" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <option value="不住宿" <c:if test="${stuUser.isPutup eq '不住宿'}">selected="selected"</c:if>>不住宿</option>
                                                <option value="住宿" <c:if test="${stuUser.isPutup eq '住宿'}">selected="selected"</c:if>>住宿</option>
                                                <%--<option value="4人间" <c:if test="${stuUser.isPutup eq '4人间'}">selected="selected"</c:if>>4人间</option>--%>
                                            </select>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>是否我院教&#12288;<br/>学基地或帮&#12288;<br/>扶协议单位：
                                        </td>
                                        <td>
                                            <select id="isOwnOrg" name="extInfo.isOwnOrg" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="" ${empty extInfo.isOwnOrg ?'selected':''}/>
                                                <option value="own" ${extInfo.isOwnOrg eq 'own'?'selected':''}>我院教学基地</option>
                                                <option value="other" ${extInfo.isOwnOrg eq 'other'?'selected':''}>帮扶协议单位</option>
                                                <option value="N" ${extInfo.isOwnOrg eq 'N'?'selected':''}>否</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>进修目的：
                                        </td>
                                        <td colspan="3" style="height: 120px;">
                                            <textarea name="extInfo.studyAim" style="width: 98%; height: 90%;text-align: left;"
                                                      class="validate[required] [maxSize[150]]" placeholder="请在进修目的填写三级科室名称及每个科室学习时长，个人志愿申报仅供医院管理部门参考，不代表必然按照进修生意愿安排轮科,描述不超过150字">${extInfo.studyAim}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>本人从事专业&#12288;<br/>现有业务水平：
                                        </td>
                                        <td colspan="3" style="height: 120px;">
                                            <textarea style="width: 98%; height: 90%;text-align: left;" name="extInfo.vocationalLevel"
                                                      class="validate[required] [maxSize[150]]" placeholder="（请注明现工作形式主要是门诊还是住院部且描述不超过150字）">${extInfo.vocationalLevel}</textarea>
                                        </td>
                                    </tr>
                                    <%--<tr>--%>
                                        <%--<td class="td_left">--%>
                                            <%--<font color="red">*</font>本人学历及&#12288;<br/>社会经历：--%>
                                        <%--</td>--%>
                                        <%--<td colspan="3" style="height: 120px;">--%>
                                            <%--<textarea style="width: 98%; height: 90%;text-align: left;" name="extInfo.socialExperience"--%>
                                                      <%--class="validate[required] [maxSize[150]]" placeholder="描述不超过150字">${extInfo.socialExperience}</textarea>--%>
                                        <%--</td>--%>
                                        <%--<td></td>--%>
                                    <%--</tr>--%>
                                    <tr>
                                        <td style="text-align: center" colspan="2">
                                            <input type="button"  onclick="saveInfo('studyForm');" class="btn_green save_btn" value="保存此页信息"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                        <div class="search_table doctorInfo" style="margin-top:20px;">
                           <div style="text-align: center;">
                               <span id="infoPage1" class="spanInfo spanClick" onclick="showPage('infoDiv1',this);" style="font-size: 16px;">1</span>&#12288;
                               <span id="infoPage2" class="spanInfo spanNotClick" onclick="showPage('infoDiv2',this);" style="font-size: 16px;">2</span>&#12288;
                               <span id="infoPage3" class="spanInfo spanNotClick" onclick="showPage('infoDiv3',this);" style="font-size: 16px;">3</span>&#12288;
                               <input class="btn_green" onclick="showPage('next',this);" id="nextSpan" type="button" value="下一页" />
                           </div>
                        </div>
                    </div>
                    <div  class="div_table parentDiv" id="div_table_2" style="display: none">
                        <table style="width: 60%;margin-left: 50px;" class="searchTable" cellpadding="10">
                            <tr style="height: 70px;font-size: 110%;">
                                <c:if test="${sysCfgMap['jx_templete_name'] eq 'xz'}">
                                    <td style="background-color: #cadeee;" colspan="4">&nbsp;申请信息已填写完毕，请下载并打印申请表至<span class="red">选送单位审核并盖相应公章。</span></td>
                                </c:if>
                                <c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">
                                    <td style="background-color: rgb(255, 224, 168);" colspan="4">&nbsp;申请信息已填写完毕，请下载并打印申请表至<span class="red">选送单位审核并盖相应公章。</span></td>
                                </c:if>
                            </tr>
                            <tr style="height: 70px;font-size: 110%;">
                                <td colspan="3">&nbsp;请及时上传审核盖章后的进修申请表图片，上传完<br/>&nbsp;成后，申请流程方才全部结束。</td>
                                <td style="text-align: right;">
                                    <input class="btn_green save_btn" onclick="printApplForm('${stuUser.stuBatId}','${sysCfgMap['jx_templete_name']}');" type="button" value="下载进修生申请表" />
                                </td>
                            </tr>
                            <tr style="height: 80px;">
                                <td colspan="2"></td>
                                <td colspan="2">
                                    <input class="btn_green save_btn" onclick="changeStep('stepA1');" type="button" value="上一步" />&#12288;
                                    <input class="btn_green save_btn" onclick="changeStep('stepA3');" type="button" value="下一步" />
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div  class="div_table parentDiv" id="div_table_3" style="display: none">
                        <table id="applyInfo" style="width: 75%;margin-left: 50px;" class="searchTable" cellpadding="10">
                            <tr style="height: 70px;font-size: 110%;">
                                <c:if test="${sysCfgMap['jx_templete_name'] eq 'xz'}">
                                <td style="background-color: #cadeee;" colspan="4">&nbsp;上传审核盖章后的进修申请表扫描件，完成申请流程。<font style="color: red;font-size: 10px;">（请以PDF文件格式上传）</font></td>
                                </c:if>
                                <c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">
                                    <td style="background-color: rgb(255, 224, 168);" colspan="4">&nbsp;上传审核盖章后的进修申请表扫描件，完成申请流程。<font style="color: red;font-size: 10px;">（请以PDF文件格式上传）</font></td>
                                </c:if>
                            </tr>
                            <tr style="height: 70px;font-size: 110%;">
                                <td colspan="3">&nbsp;需上传审核盖章后的进修申请表扫描件，请注意核实。</td>
                                <td style="text-align: right;">
                                    <span id="registerFormUriSpan" style="display:${!empty extInfo.registerFormUri?'':'none'} ">
                                        [<a href="${sysCfgMap['upload_base_url']}/${extInfo.registerFormUri}" target="_blank">查看附件</a>]&#12288;
                                    </span>
                                    <a id="registerFormUri" href="javascript:uploadFile('registerFormUri','申请登记扫描件');" class="btn_green <c:if test="${empty extInfo.registerFormUri}">validate[required]</c:if>">${empty extInfo.registerFormUri?'上&#12288;传':'重新上传'}</a>&#12288;
                                    <a id="registerFormUriDel" href="javascript:delFile('registerFormUri');" class="btn_green" style="${empty extInfo.registerFormUri?'display:none':''}">删&#12288;除</a>&#12288;
                                    <input type="hidden" id="registerFormUriValue" name="extInfo.registerFormUri" value="${extInfo.registerFormUri}">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4" style="text-align: center;">
                                    <input class="btn_green" onclick="submiteApply();" type="button" value="提&#12288;交"/>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div  class="div_table parentDiv" id="div_table_4" style="display: none">
                        <c:if test="${stuUser.stuStatusId eq stuStatusEnumPassing.id}">
                            <table style="width: 60%;margin-left: 50px;" class="searchTable" cellpadding="10">
                                <tr style="height: 70px;font-size: 110%;">
                                    <c:if test="${sysCfgMap['jx_templete_name'] eq 'xz'}">
                                    <td style="background-color: #cadeee;">&nbsp;提交资料审核中。</td>
                                    </c:if>
                                    <c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">
                                        <td style="background-color: rgb(255, 224, 168);">&nbsp;提交资料审核中。</td>
                                    </c:if>
                                </tr>
                            </table>
                        </c:if>
                        <c:if test="${stuUser.stuStatusId eq stuStatusEnumUnPassed.id and stuUser.isBack ne 'Y'}">
                            <table id="" style="width: 60%;margin-left: 50px;" class="searchTable" cellpadding="10">
                                <tr style="height: 70px;font-size: 110%;">
                                    <td style="background-color: rgb(255, 224, 168);">&nbsp;非常抱歉，您的进修申请未通过审核。</td>
                                </tr>
                                <tr style="height: 80px;">
                                    <td>原因：${auditAgree}</td>
                                </tr>
                            </table>
                        </c:if>
                        <c:if test="${stuUser.isBack eq 'Y'}">
                            <table id="" style="width: 60%;margin-left: 50px;" class="searchTable" cellpadding="10">
                                <tr style="height: 70px;font-size: 110%;">
                                    <c:if test="${sysCfgMap['jx_templete_name'] eq 'xz'}">
                                    <td style="background-color: #cadeee;" colspan="4">&nbsp;非常抱歉，您的进修申请未通过审核。</td>
                                    </c:if>
                                    <c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">
                                        <td style="background-color: rgb(255, 224, 168);" colspan="4">&nbsp;非常抱歉，您的进修申请未通过审核。</td>
                                    </c:if>
                                </tr>
                                <tr style="height: 80px;">
                                    <td colspan="3">原因：${auditAgree}</td>
                                    <td style="text-align: center">
                                        <input class="btn_green" onclick="reApply();" type="button" value="重新申请"/>
                                    </td>
                                </tr>
                            </table>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div style="display: none">
        <!-- 进修专业模板 -->
        <table id="speTemplate">
            <tr>
                <td><input type="checkbox" name="speIds"/></td>
                <td>
                    <select name="extInfo.speFormList[{index}].speId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                        <option value="">请选择</option>
                        <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                            <option value="${SysDept.deptFlow}" ${speForm.speId eq SysDept.deptFlow?'selected':''}>${SysDept.deptName}</option>
                        </c:forEach>
                    </select>
                   <%-- <select name="extInfo.speFormList[{index}].speName" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                            <option value="${dict.dictName}" &lt;%&ndash;${stuUser.speId eq dict.dictId?'selected':''}&ndash;%&gt;>${dict.dictName}</option>
                        </c:forEach>
                    </select>--%>
                </td>
                <td>
                    <select name="extInfo.speFormList[{index}].stuTimeId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                        <option value="">请选择</option>
                        <option value="1" <%--<c:if test="${stuUser.stuTimeId eq '1'}">selected="selected"</c:if>--%>>1个月</option>
                        <option value="2" <%--<c:if test="${stuUser.stuTimeId eq '2'}">selected="selected"</c:if>--%>>2个月</option>
                        <option value="3" <%--<c:if test="${stuUser.stuTimeId eq '3'}">selected="selected"</c:if>--%>>3个月</option>
                        <option value="6" <%--<c:if test="${stuUser.stuTimeId eq '6'}">selected="selected"</c:if>--%>>6个月</option>
                        <option value="12" <%--<c:if test="${stuUser.stuTimeId eq '12'}">selected="selected"</c:if>--%>>12个月</option>
                    </select>
                </td>
            </tr>
        </table>
        <!-- 工作经历模板 -->
        <table id="workTemplate">
            <tr>
                <td><input type="checkbox" name="workIds"/></td>
                <td>
                    <input type='text' class='validate[required] input '  name="extInfo.workResumeList[{index}].clinicalRoundDate" placeholder="例:2017.01-2017.09" style="width: 140px;" />
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.workResumeList[{index}].hospitalName" style="width: 140px;" />
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.workResumeList[{index}].workDescription" style="width: 140px;" />
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.workResumeList[{index}].postName" style="width: 140px;" />
                </td>
            </tr>
        </table>
        <!--资质证书模板-->
        <table id="credentialsTemplate">
            <tr class="yszz">
                <td class="td_left"><font color="red">*</font>资质证书名称：</td>
                <td colspan="4">
                    <input type='text' placeholder="请填写证书名称" style="width: 300px;" class="input validate[required]" name="extInfo.regList[{index}].regNo" />
                    <span id="regUri${'{index}'}Span" style="display:${!empty reg.regUri?'':'none'} ">
                        [<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">查看图片</a>]&#12288;
                    </span>
                    <a href="javascript:uploadFile('regUri${'{index}'}','资质证书');" class="btn">${empty reg.regUri?'':'重新'}上传</a>&#12288;
                    <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                    <font style="color:red;font-size: 10px">（请上传原件）</font>
                    <input type="hidden" id="regUri${'{index}'}Value" name="extInfo.regList[{index}].regUri">
                </td>
            </tr>
        </table>
        <!--科研信息模板-->
        <table id="projectTemplate">
            <tr>
                <td><input type="checkbox" name="projectIds"/></td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.projectList[{index}].titleName" style="width: 140px;" />
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.projectList[{index}].authorRank" style="width: 60px;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.projectList[{index}].projectLevel" style="width: 80px;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.projectList[{index}].projectDate" style="width: 140px;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.projectList[{index}].pubNumber" style="width: 90px;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.projectList[{index}].awardSituation" style="width: 140px;"/>
                </td>
            </tr>
        </table>
        <table id="thesisTemplate">
            <tr>
                <td><input type="checkbox" name="thesisIds"/></td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.thesisList[{index}].titleName" style="width: 120px;" />
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.thesisList[{index}].authorRank" style="width: 50px;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.thesisList[{index}].pubName"  style="width: 110px;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.thesisList[{index}].pubDate"  style="width: 60px;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.thesisList[{index}].pubNumber" style="width: 60px;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.thesisList[{index}].awardSituation" style="width: 120px;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.thesisList[{index}].isSCI" style="width: 50px;"/>
                </td>
            </tr>
        </table>
        <!--学历信息模板-->
        <table id="educationTemplate">
            <tr>
                <td><input type="checkbox" name="educationIds"/></td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.educationList[{index}].eduRoundDate" placeholder="例:2017.01-2017.09"  style="width: 95%;" />
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.educationList[{index}].schoolName"  style="width: 95%;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.educationList[{index}].speName"  style="width: 95%;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.educationList[{index}].length"  style="width: 95%;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.educationList[{index}].education"  style="width: 95%;"/>
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.educationList[{index}].degree"  style="width: 95%;"/>
                </td>
            </tr>
        </table>
    </div>
</div>