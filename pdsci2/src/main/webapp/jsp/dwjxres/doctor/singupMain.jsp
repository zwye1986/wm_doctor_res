<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:include page="/jsp/dwjxres/htmlhead-dwjxres.jsp">
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
            $(".showImg").show();
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
        searchTitleGenre();
    });
    /**
     * 上传证书及文件
     */
    function uploadFile(type, typeName) {
        jboxOpen("<s:url value='/inx/dwjxres/uploadFile'/>?operType=" + type, "上传" + typeName, 450, 150);
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
    /**
     * 增加工作经历
     */
    var indexMap={};
    function add(tb) {
        $("#add_a").remove();
        var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
        var index=indexMap[tb];
        if(!index)
        {
            index = $("#" + tb + "Tb tr").length-1;
        }
        index++;
        indexMap[tb]=index;
        cloneTr.html(cloneTr.html().htmlFormartById({"index": index}));
        $("#" + tb + "Tb").append(cloneTr);
        $('.datepicker').datepicker();
    }
    String.prototype.htmlFormartById = function (data) {
        var html = this;
        for (var key in data) {
            var reg = new RegExp('\\{' + key + '\\}', 'g');
            html = html.replace(reg, data[key]);
        }
        return html;
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
        var url = "<s:url value='/dwjxres/doctor/singup?batchFlow='/>"+stuBatId;
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
        jboxPost("<s:url value='/dwjxres/doctor/valideExist'/>",{"batchFlow":batchFlow},function(resp){
            if(null != resp && resp != ""){
                jboxTip("下载中,请稍等...");
                var url = '<s:url value="/dwjxres/hospital/printApplForm?resumeFlow="/>'+resp+"&printFlag=doctor"+"&templeteName="+encodeURI(encodeURI(templeteName));
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
            if($("#idNoUriTb").children().length<=1)
            {
                jboxTip("请至少添加两张身份证图片，一张为正面，一张为反面！");
                return;
            }
            if($("#graduatedUriTb").children().length<=0)
            {
                jboxTip("请至少添加一张毕业证图片！");
                return;
            }
            if($("#qualifiedUriTb").children().length<=0)
            {
                jboxTip("请至少添加一张医师（护士、护师）资格证书！");
                return;
            }
            if($("#certifiedUriTb").children().length<=0)
            {
                jboxTip("请至少添加一张医师（护士）执业证证书！");
                return;
            }
        }
        var stuBatId = $("#batchFlow").find("option:selected").val();
        jboxPost("<s:url value='/dwjxres/doctor/saveSingupInfo?stuBatId='/>"+stuBatId,$("#"+formId).serialize(),function(resp){
            var data = eval("("+resp+")");
            var operResult = data['operResult'];
            if(operResult == '${GlobalConstant.OPRE_SUCCESSED}'){
                if(data['formHaveSubmit1'] == '${GlobalConstant.FLAG_Y}'){
                    $("#formHaveSubmit1").val("Y");
                    $("#infoPage2").click();
                }
                if(data['formHaveSubmit2'] == '${GlobalConstant.FLAG_Y}'){
                    $("#formHaveSubmit2").val("Y");
                    $("#infoPage3").click();
                }
                if(data['formHaveSubmit3'] == '${GlobalConstant.FLAG_Y}'){
                    search(stuBatId)
                }
            }
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
            jboxPost("<s:url value='/dwjxres/doctor/saveSingupInfo?flag=true&stuBatId='/>"+stuBatId+"&registerFormUriValue="+registerFormUriValue,null,function(resp){
                if(resp=="1"){
                    jboxTip("保存成功");
                    search(stuBatId);
                    //自动跳转到主页（仅第一次点击提交）
                    location.href="/pdsci/dwjxres/doctor/index";

                }else{
                    jboxTip(resp);
                }
            },null,false);
        })

    }
    function reApply(){
        var stuBatId = $("#batchFlow").find("option:selected").val()
        jboxPost("<s:url value='/dwjxres/doctor/saveSingupInfo?flag=reApply&stuBatId='/>"+stuBatId,null,function(resp){
            if(resp=="1"){
                jboxTip("操作成功");
                search(stuBatId);
            }else{
                jboxTip(resp);
            }
        },null,false);
    }
    function showImage(imgUrl)
    {
        var height=(window.screen.height)*0.9;
        var width=(window.screen.width)*0.9;
        var url="<s:url value='/jsp/dwjxres/doctor/showImage.jsp'/>?imgUrl="+imgUrl;
//        jboxOpen(url, "查看",1000,650);
        window.open(url);
    }

    function searchTitleGenre() {
        var options = $(".titleTypeId option:selected");
        var titleGenreName = options.text();
        //console.log(titleGenreName);
        if (titleGenreName == '护士') {
            $(".optionClass").show();
            $(".optionClassType").hide();
        } else {
            $(".optionClassType").show();
            $(".optionClass").hide();
        }

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
                                        <col width="10%"/>
                                        <col width="20%"/>
                                        <col width="25%"/>
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
                                            <font color="red">*</font>身份证号：
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
                                            <input type="text" id="userBirthday" name="user.userBirthday" value="${extInfo.userBirthday}"  class='input validate[required]' onchange="changeAge(this)" readonly="readonly" style="width: 146px;margin-left: 0px"/>
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
                                            <input class='input validate[required]' id="userAge" type="text" name="stuUser.userAge" readonly="readonly" style="width: 150px;margin-left: 0px"/>
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
                                            <font color="red">*</font>手机号码：
                                        </td>
                                        <td>
                                            <input class='input validate[required]' type="text" name="user.userPhone"  value="${extInfo.userPhone}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
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
                            <form id="fileForm2" method="post" enctype="multipart/form-data">
                                <input type="text" name="productFlow" value="${recruitFlow}" style="display: none;"/>
                                <input type="file" id="file2" name="file" class="validate[required]" style="display: none;"/>
                            </form>
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
                                            <font color="red">*</font>工作年限：
                                        </td>
                                        <td>
                                            <input class="input validate[required]" type="text" name="stuUser.jobYear"  value="${stuUser.jobYear}" style="width: 150px;margin-left: 0px"/>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>选送单位：
                                        </td>
                                        <td>
                                            <input class="input validate[required]" type="text" name="stuUser.sendComName"  value="${stuUser.sendComName}" style="width: 150px;margin-left: 0px"/>
                                        <td></td>
                                        <td></td>
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
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>医院等级评定证明：
                                        </td>
                                        <td colspan="2">
                                            <span id="hospitalLevelProveUriSpan" style="display:${!empty extInfo.hospitalLevelProveUri?'':'none'} ">
                                                <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.hospitalLevelProveUri}');"class="btn showImg"  >查看图片</a>&#12288;
                                            </span>
                                            <a id="hospitalLevelProveUri" href="javascript:uploadFile('hospitalLevelProveUri','医院等级评定证明图片');" class="btn">${empty extInfo.hospitalLevelProveUri?'上&#12288;传':'重新上传'}</a>&#12288;
                                            <%--<a id="hospitalLevelProveUriDel" href="javascript:delFile('hospitalLevelProveUri');" class="btn_green" style="${empty extInfo.hospitalLevelProveUri?'display:none':''}">删&#12288;除</a>&#12288;--%>
                                            <input type="hidden" id="hospitalLevelProveUriValue" name="extInfo.hospitalLevelProveUri" value="${extInfo.hospitalLevelProveUri}">
                                        </td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>选送单位详细地址：
                                        </td>
                                        <td  colspan="3">
                                            <input type='text'style="width: 460px;margin-left: 0px" class="input validate[required]" name="stuUser.sendComAddress" value="${stuUser.sendComAddress}"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>选送单位纳税人识别号：
                                        </td>
                                        <td  colspan="3">
                                            <input type='text'style="width: 460px;margin-left: 0px" class="input validate[required,maxSize[150],custom[onlyLetterNumber]]" name="stuUser.identificationNumber" value="${stuUser.identificationNumber}"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>所在科室：
                                        </td>
                                        <td>
                                            <input class="input validate[required]" type="text" name="stuUser.deptName"  value="${stuUser.deptName}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>职务：
                                        </td>
                                        <td>
                                            <input class="input validate[required]" type="text" name="stuUser.postName"  value="${stuUser.postName}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>职称类别：
                                        </td>
                                        <td>
                                            <select name="extInfo.titleTypeId" class="select validate[required] titleTypeId" style="width: 157px;margin-left: 0px;" onclick="searchTitleGenre()">
                                                <option value="">请选择</option>
                                                <c:forEach items="${dictTypeEnumTitleGenreList}" var="dict">
                                                    <option value="${dict.dictId}" ${extInfo.titleTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>职称：
                                        </td>
                                        <td>
                                            <select name="stuUser.titleId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <c:forEach items="${dictTypeEnumUserTitleList}" var="dict">
                                                    <option value="${dict.dictId}" ${stuUser.titleId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td></td>
                                        <td></td>
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
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>是否熟练电脑：
                                        </td>
                                        <td>
                                            <select name="stuUser.isComputer" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <option value="Y" ${stuUser.isComputer eq 'Y'?'selected':''}>是</option>
                                                <option value="N" ${stuUser.isComputer eq 'N'?'selected':''}>否</option>
                                            </select>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
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
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>毕业学校：
                                        </td>
                                        <td>
                                            <input class="input validate[required]" type="text" name="stuUser.schoolName"  value="${stuUser.schoolName}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>毕业专业：
                                        </td>
                                        <td>
                                            <input class="input validate[required]" type="text" name="stuUser.schoolSpeName"  value="${stuUser.schoolSpeName}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>最高学历开始时间：
                                        </td>
                                        <td>
                                            <input id="maxEduBdate" type='text' class='input validate[required] datepicker' name="stuUser.maxEduBdate" value="${stuUser.maxEduBdate}" readonly="readonly" style="width: 146px;margin-left: 0px" />
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>最高学历结束时间：
                                        </td>
                                        <td>
                                            <input id="maxEduEdate" type='text' class='input validate[required] datepicker' name="stuUser.maxEduEdate" value="${stuUser.maxEduEdate}" readonly="readonly" style="width: 146px;margin-left: 0px" />
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    </tbody>
                                </table>
                                <div>
                                    <h4 style="width: 100%;float: left">工作经历<span style="float: right;padding-right: 20px">
                                        <img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('work')"/>&#12288;
                                        <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('work');"/>
                                    </span></h4>

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
                                    <c:forEach var="resume" items="${extInfo.workResumeList}" varStatus="status">
                                        <tr>
                                            <td><input type="checkbox" name="workIds"/></td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.workResumeList[${status.index}].clinicalRoundDate" placeholder="开始-结束时间" value="${resume.clinicalRoundDate}" style="width: 140px;" />
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
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <table style="width: 100%;" class="searchTable" >
                                    <colgroup>
                                        <col width="30%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="10%"/>
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>身份证图片：</td>
                                        <td colspan="2">&#12288;添加身份证图片&#12288;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('idNoUri')"/></td>
                                        <td colspan="2"><font color="red">请上传身份证照片正反面</font></td>
                                    </tr>
                                    </tbody>
                                    <tbody id="idNoUriTb">
                                    <c:forEach var="reg" items="${extInfo.idNoUriList}" varStatus="status">
                                        <tr class="yszz">
                                            <td class="td_left"></td>
                                            <td colspan="4">&#12288;
                                                <span id="idNoUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                    <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  >查看图片</a>&#12288;
                                                </span>
                                                <a href="javascript:uploadFile('idNoUri${status.index}','身份证图片');" class="btn">${empty reg.regUri?'':'重新'}上传</a>&#12288;
                                                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                                                <input type="hidden" id="idNoUri${status.index}Value" name="extInfo.idNoUriList[${status.index}].regUri" value="${reg.regUri}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty extInfo.idNoUriList and not empty extInfo.idNoUri}">
                                        <tr class="yszz">
                                            <td class="td_left"></td>
                                            <td colspan="4">&#12288;
                                                <span id="idNoUri0Span" style="display:${!empty extInfo.idNoUri?'':'none'} ">
                                                    <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.idNoUri}');" class="btn showImg"  >查看图片</a>&#12288;
                                                </span>
                                                <a href="javascript:uploadFile('idNoUri0','身份证图片');" class="btn">${empty extInfo.idNoUri?'':'重新'}上传</a>&#12288;
                                                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                                                <input type="hidden" id="idNoUri0Value" name="extInfo.idNoUriList[0].regUri" value="${extInfo.idNoUri}">
                                            </td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                    <tbody>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>毕业证图片：</td>
                                        <td colspan="4">&#12288;添加毕业证图片&#12288;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('graduatedUri')"/></td>
                                    </tr>
                                    </tbody>
                                    <tbody id="graduatedUriTb">
                                    <c:forEach var="reg" items="${extInfo.graduatedUriList}" varStatus="status">
                                        <tr class="yszz">
                                            <td class="td_left"></td>
                                            <td colspan="4">&#12288;
                                                <span id="graduatedUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                    <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  >查看图片</a>&#12288;
                                                </span>
                                                <a href="javascript:uploadFile('graduatedUri${status.index}','毕业证图片');" class="btn">${empty reg.regUri?'':'重新'}上传</a>&#12288;
                                                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                                                <input type="hidden" id="graduatedUri${status.index}Value" name="extInfo.graduatedUriList[${status.index}].regUri" value="${reg.regUri}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty extInfo.graduatedUriList and not empty extInfo.graduatedUri}">
                                        <tr class="yszz">
                                            <td class="td_left"></td>
                                            <td colspan="4">&#12288;
                                                <span id="graduatedUri0Span" style="display:${!empty extInfo.graduatedUri?'':'none'} ">
                                                    <a  href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.graduatedUri}');"class="btn showImg"  >查看图片</a>&#12288;
                                                </span>
                                                <a href="javascript:uploadFile('graduatedUri0','毕业证图片');" class="btn">${empty extInfo.graduatedUri?'':'重新'}上传</a>&#12288;
                                                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                                                <input type="hidden" id="graduatedUri0Value" name="extInfo.graduatedUriList[0].regUri" value="${extInfo.graduatedUri}">
                                            </td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                    <tbody>
                                    <tr>
                                        <td class="td_left"><font color="red" id="zgzs">*</font>医师（护士、护师）资格证书编号：</td>
                                        <td colspan="4">
                                            &#12288;<input type='text' class="input validate[required]" placeholder="请填写医师（护士、护师）资格证书编号" style="width:300px;margin-left: 0px" name="extInfo.qualifiedNo" value="${extInfo.qualifiedNo}"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="td_left"><font color="red">*</font>医师（护士、护师）资格证书：</td>
                                        <td colspan="4">&#12288;添加图片&#12288;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('qualifiedUri')"/></td>
                                    </tr>
                                    </tbody>
                                    <tbody id="qualifiedUriTb">
                                    <c:forEach var="reg" items="${extInfo.qualifiedUriList}" varStatus="status">
                                        <tr class="yszz">
                                            <td class="td_left"></td>
                                            <td colspan="4">&#12288;
                                                <span id="qualifiedUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                    <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  >查看图片</a>&#12288;
                                                </span>
                                                <a href="javascript:uploadFile('qualifiedUri${status.index}','医师（护士）执业证图片');" class="btn">${empty reg.regUri?'':'重新'}上传</a>&#12288;
                                                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                                                <input type="hidden" id="qualifiedUri${status.index}Value" name="extInfo.qualifiedUriList[${status.index}].regUri" value="${reg.regUri}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty extInfo.qualifiedUriList and not empty extInfo.qualifiedUri}">
                                        <tr class="yszz">
                                            <td class="td_left"></td>
                                            <td colspan="4">&#12288;
                                                <span id="qualifiedUri0Span" style="display:${!empty extInfo.qualifiedUri?'':'none'} ">
                                                    <a  href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.qualifiedUri}');"class="btn showImg"  >查看图片</a>&#12288;
                                                </span>
                                                <a href="javascript:uploadFile('qualifiedUri0','医师（护士）执业证图片');" class="btn">${empty extInfo.qualifiedUri?'':'重新'}上传</a>&#12288;
                                                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                                                <input type="hidden" id="qualifiedUri0Value" name="extInfo.qualifiedUriList[0].regUri" value="${extInfo.qualifiedUri}">
                                            </td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                    <tbody>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>医师（护士）执业证书编号：</td>
                                        <td colspan="4">
                                            &#12288;<input type='text' placeholder="请填写医师（护士）执业证书编号" style="width: 300px;margin-left: 0px" class="input validate[required]" name="extInfo.certifiedNo" value='${extInfo.certifiedNo}'/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="td_left"><font color="red">*</font>医师（护士）执业证证书：</td>
                                        <td colspan="4">&#12288;添加图片&#12288;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('certifiedUri')"/></td>
                                    </tr>
                                    </tbody>
                                    <tbody id="certifiedUriTb">
                                    <c:forEach var="reg" items="${extInfo.certifiedUriList}" varStatus="status">
                                        <tr class="yszz">
                                            <td class="td_left"></td>
                                            <td colspan="4">&#12288;
                                                <span id="certifiedUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                    <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  >查看图片</a>&#12288;
                                                </span>
                                                <a href="javascript:uploadFile('certifiedUri${status.index}','医师（护士）执业证图片');" class="btn">${empty reg.regUri?'':'重新'}上传</a>&#12288;
                                                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                                                <input type="hidden" id="certifiedUri${status.index}Value" name="extInfo.certifiedUriList[${status.index}].regUri" value="${reg.regUri}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <c:if test="${empty extInfo.certifiedUriList and not empty extInfo.certifiedUri}">
                                        <tr class="yszz">
                                            <td class="td_left"></td>
                                            <td colspan="4">&#12288;
                                                <span id="certifiedUri0Span" style="display:${!empty extInfo.certifiedUri?'':'none'} ">
                                                    <a  href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.certifiedUri}');"class="btn showImg"  >查看图片</a>&#12288;
                                                </span>
                                                <a href="javascript:uploadFile('certifiedUri0','医师（护士）执业证图片');" class="btn">${empty extInfo.certifiedUri?'':'重新'}上传</a>&#12288;
                                                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                                                <input type="hidden" id="certifiedUri0Value" name="extInfo.certifiedUriList[0].regUri" value="${extInfo.certifiedUri}">
                                            </td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                    <tbody>
                                    <tr>
                                        <td  class="td_left">其它资质证书：</td>
                                        <td colspan="4">&#12288;添加资质证书&#12288;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('credentials')"/></td>
                                    </tr>
                                    </tbody>
                                    <tbody id="credentialsTb">
                                    <c:forEach var="reg" items="${extInfo.regList}" varStatus="status">
                                        <tr class="yszz">
                                            <td class="td_left"><font color="red">*</font>资质证书名称：</td>
                                            <td colspan="4">&#12288;
                                                <input type='text' placeholder="请填写证书名称" style="width: 300px;" class="input validate[required]" name="extInfo.regList[${status.index}].regNo" value='${reg.regNo}'/>
                                                <span id="regUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                    <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  >查看图片</a>&#12288;
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
                                <table style="width: 100%;" class="searchTable">
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
                                            <font color="red">*</font>进修专业：
                                        </td>
                                        <td>
                                            <select name="stuUser.speId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                                    <option value="${dict.dictId}" ${stuUser.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            进修批次：
                                        </td>
                                        <td>
                                            ${batchNo}<input type="hidden" name="stuUser.stuBatId" value="${batchFlow}" />
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>进修时间：
                                        </td>
                                        <td>
                                            <select name="stuUser.stuTimeId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <option value="短期培训" <c:if test="${stuUser.stuTimeId eq '短期培训'}">selected="selected"</c:if> class="optionClassType">短期培训(3个月)</option>
                                                <%--<option value="1" <c:if test="${stuUser.stuTimeId eq '1'}">selected="selected"</c:if> class="optionClass">1个月</option>--%>
                                                <%--<option value="2" <c:if test="${stuUser.stuTimeId eq '2'}">selected="selected"</c:if> class="optionClass">2个月</option>--%>
                                                <option value="3" <c:if test="${stuUser.stuTimeId eq '3'}">selected="selected"</c:if> class="optionClass">3个月</option>
                                                <option value="6" <c:if test="${stuUser.stuTimeId eq '6'}">selected="selected"</c:if>>6个月</option>
                                                <option value="12" <c:if test="${stuUser.stuTimeId eq '12'}">selected="selected"</c:if>>12个月</option>
                                            </select>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
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
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>是否住宿：
                                        </td>
                                        <td>
                                            <select class="select validate[required]" name="stuUser.isPutup" style="width: 157px;margin-left: 0px;">
                                                <option value="">请选择</option>
                                                <option value="不住宿" <c:if test="${stuUser.isPutup eq '不住宿'}">selected="selected"</c:if>>不住宿</option>
                                                <option value="2人间" <c:if test="${stuUser.isPutup eq '2人间'}">selected="selected"</c:if>>2人间</option>
                                                <option value="4人间" <c:if test="${stuUser.isPutup eq '4人间'}">selected="selected"</c:if>>4人间</option>
                                            </select>
                                        </td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>进修目的：
                                        </td>
                                        <td colspan="3" style="height: 120px;">
                                            <textarea name="extInfo.studyAim" style="width: 98%; height: 90%;text-align: left;"
                                                      class="validate[required] [maxSize[150]]" placeholder="描述不超过150字">${extInfo.studyAim}</textarea>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>本人从事专业&#12288;<br/>现有业务水平：
                                        </td>
                                        <td colspan="3" style="height: 120px;">
                                            <textarea style="width: 98%; height: 90%;text-align: left;" name="extInfo.vocationalLevel"
                                                      class="validate[required] [maxSize[150]]" placeholder="（请注明现工作形式主要是门诊还是住院部且描述不超过150字）">${extInfo.vocationalLevel}</textarea>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: center" colspan="2">
                                            <input type="button"  onclick="saveInfo('studyForm');" class="btn_green save_btn" value="保存此页信息"/>
                                        </td>
                                        <td></td>
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
                                <td style="background-color: #cadeee;" colspan="4">&nbsp;上传审核盖章后的进修申请表图片，完成申请流程。</td>
                                </c:if>
                                <c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">
                                    <td style="background-color: rgb(255, 224, 168);" colspan="4">&nbsp;上传审核盖章后的进修申请表图片，完成申请流程。</td>
                                </c:if>
                            </tr>
                            <tr style="height: 70px;font-size: 110%;">
                                <td colspan="3">&nbsp;需上传审核盖章后的进修申请表图片，请注意核实。</td>
                                <td style="text-align: right;">
                                    <span id="registerFormUriSpan" style="display:${!empty extInfo.registerFormUri?'':'none'} ">
                                        <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.registerFormUri}');"class="btn showImg"  >查看图片</a>&#12288;
                                    </span>
                                    <a id="registerFormUri" href="javascript:uploadFile('registerFormUri','申请登记图片');" class="btn_green <c:if test="${empty extInfo.registerFormUri}">validate[required]</c:if>">${empty extInfo.registerFormUri?'上&#12288;传':'重新上传'}</a>&#12288;
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
        <!-- 工作经历模板 -->
        <table id="workTemplate">
            <tr>
                <td><input type="checkbox" name="workIds"/></td>
                <td>
                    <input type='text' class='validate[required] input ' placeholder="开始-结束时间" name="extInfo.workResumeList[{index}].clinicalRoundDate" style="width: 140px;" />
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
                <td colspan="4">&#12288;
                    <input type='text' placeholder="请填写证书名称" style="width: 300px;" class="input validate[required]" name="extInfo.regList[{index}].regNo" />
                    <span id="regUri${'{index}'}Span" style="display:${!empty reg.regUri?'':'none'} ">
                        <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  >查看图片</a>&#12288;
                    </span>
                    <a id="regUri${'{index}'}" href="javascript:uploadFile('regUri${'{index}'}','资质证书');" class="btn">${empty reg.regUri?'':'重新'}上传</a>&#12288;
                    <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                    <input type="hidden" id="regUri${'{index}'}Value" name="extInfo.regList[{index}].regUri">
                </td>
            </tr>
        </table>
        <!--资质证书模板-->
        <table id="idNoUriTemplate">
            <tr class="yszz">
                <td class="td_left"></td>
                <td colspan="4">&#12288;
                    <span id="idNoUri${'{index}'}Span" style="display:${!empty reg.regUri?'':'none'} ">
                        <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  >查看图片</a>&#12288;
                    </span>
                    <a id="idNoUri${'{index}'}" href="javascript:uploadFile('idNoUri${'{index}'}','身份证图片');" class="btn">${empty reg.regUri?'':'重新'}上传</a>&#12288;
                    <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                    <input type="hidden" id="idNoUri${'{index}'}Value" name="extInfo.idNoUriList[{index}].regUri">
                </td>
            </tr>
        </table>
        <!--资质证书模板-->
        <table id="graduatedUriTemplate">
            <tr class="yszz">
                <td class="td_left"></td>
                <td colspan="4">&#12288;
                    <span id="graduatedUri${'{index}'}Span" style="display:${!empty reg.regUri?'':'none'} ">
                        <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  >查看图片</a>&#12288;
                    </span>
                    <a id="graduatedUri${'{index}'}" href="javascript:uploadFile('graduatedUri${'{index}'}','毕业证图片');" class="btn">${empty reg.regUri?'':'重新'}上传</a>&#12288;
                    <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                    <input type="hidden" id="graduatedUri${'{index}'}Value" name="extInfo.graduatedUriList[{index}].regUri">
                </td>
            </tr>
        </table>
        <!--资质证书模板-->
        <table id="qualifiedUriTemplate">
            <tr class="yszz">
                <td class="td_left"></td>
                <td colspan="4">&#12288;
                    <span id="qualifiedUri${'{index}'}Span" style="display:${!empty reg.regUri?'':'none'} ">
                        <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  >查看图片</a>&#12288;
                    </span>
                    <a id="qualifiedUri${'{index}'}" href="javascript:uploadFile('qualifiedUri${'{index}'}','医师（护士、护师）资格证书');" class="btn">${empty reg.regUri?'':'重新'}上传</a>&#12288;
                    <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                    <input type="hidden" id="qualifiedUri${'{index}'}Value" name="extInfo.qualifiedUriList[{index}].regUri">
                </td>
            </tr>
        </table>
        <!--资质证书模板-->
        <table id="certifiedUriTemplate">
            <tr class="yszz">
                <td class="td_left"></td>
                <td colspan="4">&#12288;
                    <span id="certifiedUri${'{index}'}Span" style="display:${!empty reg.regUri?'':'none'} ">
                        <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  >查看图片</a>&#12288;
                    </span>
                    <a id="certifiedUri${'{index}'}" href="javascript:uploadFile('certifiedUri${'{index}'}','医师（护士）执业证书');" class="btn">${empty reg.regUri?'':'重新'}上传</a>&#12288;
                    <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                    <input type="hidden" id="certifiedUri${'{index}'}Value" name="extInfo.certifiedUriList[{index}].regUri">
                </td>
            </tr>
        </table>
    </div>
</div>