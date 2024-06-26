<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>

<jsp:include page="/jsp/zseyjxres/htmlhead-gzzyjxres_en.jsp">
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

    $(document).ready(function(){

    });

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
//            $(".submit_btn").hide();
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



    $(document).ready(function(){
        $('.datepicker').datepicker({format:'yyyy-mm-dd'});
    });
    /**
     * 上传证书及文件
     */
    function uploadFile(type, typeName) {
        jboxOpen("<s:url value='/inx/gzzyjxrecruit/uploadFile'/>?operType=" + type, "upload "+ typeName, 450, 150);
    }
    /**
     * 删除证书及文件
     */
    function delFile(type) {
        jboxConfirm("confirm the deletion?", function () {
            $("#" + type + "Del").hide();
            $("#" + type + "Span").hide();
            $("#" + type).html("upload");
            $("#" + type + "Value").val("");
            $("#" + type).addClass("validate[required]");
        });
    }
    var len;
    $(document).ready(function(){
        len=$("#speTb tr").length==null || ''?0:$("#speTb tr").length;
    });

    /**
     * 增加工作经历
     */
    function add(tb) {
        if(tb=='spe'){
            if(len>='3'){
                jboxTip("You can add only 3 at most!") ;
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
            jboxTip("Please check to delete!");
            return false;
        }

        jboxConfirm("confirm the deletion?", function () {
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
        jboxConfirm("confirm the deletion?", function () {
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
                    jboxTip('Upload successfully!');
                    var arr = new Array();
                    arr = data.split(":");
                    $("#userImg").val(arr[1]);
                    var url = "${sysCfgMap['upload_base_url']}/" + arr[1];
                    $("#showImg").attr("src", url);
                    $("#headimgurl").val(arr[1]);
                    $('#imageFile').prev('span').find('a').text("reupload");
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
    function search(stuBatId,flag){
        if(!stuBatId){
            stuBatId = $('#batchFlow').val();
        }
        var url = "<s:url value='/zseyjxres/doctor/singup_en?batchFlow='/>"+stuBatId+"&flag="+flag;
        jboxLoad("content", url, true);
    }


    function saveInfo(formId){
        if(false == $("#"+formId).validationEngine("validate")){
            return;
        }

//        var beT = $("#maxEduBdate").val();
//        var enT = $("#maxEduEdate").val();
//        if(beT > enT){
//            jboxTip("The end time must not be less than the start time!");
//            return;
//        }
        if($("#workTb").children().length<=0)
        {
            jboxTip("Please add at least one working experience!");
            return;
        }

        if($("#educationTb").children().length<=0)
        {
            jboxTip("Please add at least one academic information!");
            return;
        }
        var stuBatId = $("#batchFlow").find("option:selected").val();
        jboxEndLoading();
        jboxPost("<s:url value='/zseyjxres/doctor/saveSingupInfo?stuBatId='/>"+stuBatId,$("#"+formId).serialize(),function(resp){
            var data = eval("("+resp+")");
            var operResult = data['operResult'];
            if(operResult == '${GlobalConstant.OPRE_SUCCESSED}'){
                if(data['formHaveSubmit1'] == '${GlobalConstant.FLAG_Y}'){
                    $("#formHaveSubmit1").val("Y");
                    search(stuBatId,"Y");
                    jboxEndLoading();
                }
            }
            jboxEndLoading();
            jboxTip('Successful operation!');
        },null,false);
    }

    function showBigStep(resp){
        if(resp == "stepA1"){
            $(".stepA3").addClass("active");
            $(".stepA4").addClass("active");
            $("#div_table_1").show();
            $("#div_table_3").show();
            $("#div_table_4").show();
        }else{
            $(".stepA3").addClass("active");
            $(".stepA4").addClass("active");
            $("#div_table_1").show();
            $("#div_table_3").show();
            $("#div_table_4").show();
        }
    }



    function submiteApply(){
//        saveInfo();
        var stuBatId = $("#batchFlow").find("option:selected").val();

        jboxConfirm("Confirm the submission? Application information cannot be modified after submission!" , function(){
            jboxStartLoading();
            jboxPost("<s:url value='/zseyjxres/doctor/saveSingupInfo?flag=true&stuBatId='/>"+stuBatId,null,function(resp){
                if(resp=="1"){
                    jboxTip("Save successfully!");
                    search(stuBatId);
                    //自动跳转到主页（仅第一次点击提交）
                    location.href="/zseyjxres/doctor/index_en";
                }else{
                    jboxTip(resp);
                }
            },null,false);
        })

    }



    function reApply(){
        var stuBatId = $("#batchFlow").find("option:selected").val()
        jboxPost("<s:url value='/zseyjxres/doctor/saveSingupInfo?flag=reApply&stuBatId='/>"+stuBatId,null,function(resp){
            if(resp=="1"){
                jboxTip("Successful operation!");
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
        <div class="main_hd"><h2 class="underline">on-line registration</h2></div>
        <div class="main_bd">
            <%--<div class="div_table">--%>
                <div class="score_frame" <%--style="width: 960px"--%>>
                    <div class="div_table">
                        <h4>Study batches&#12288;&#12288;&#12288;&#12288;&#12288;
                            <select autocomplete="off" class="select" id="batchFlow" style="width: 160px;margin-left: 5px;" onchange="search()">
                                <c:forEach items="${batchLst}" var="dict">
                                    <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchDate}</option>
                                </c:forEach>
                            </select>
                        </h4>
                    </div>
                    <%--<div class="div_table" style="padding:20px 0px 0px;">--%>
                        <%--<div class="stepDiv">--%>
                            <%--<a href="#" class="stepA1 active" onclick="showBigStep('stepA1');" style="cursor:default">申请信息填写</a>--%>
                            <%--<a href="#" class="stepA2" onclick="showBigStep('stepA2');" style="cursor:default">下载申请表</a>--%>
                            <%--<a href="#" class="stepA3" onclick="showBigStep('stepA3');" style="cursor:default">提交申请表</a>--%>
                            <%--<a href="#" class="stepA4" onclick="showBigStep('stepA4');" style="cursor:default">审核结果</a>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="div_table parentDiv" id="div_table_1" >
                        <form id="fileForm" method="post" enctype="multipart/form-data">
                            <input type="file" id="file" name="file" class="validate[required]" style="display: none;"/>
                        </form>
                        <div id="infoDiv1" class="infoDiv search_table doctorInfo" style="margin-top:20px;">
                            <input class="havaSubmit" id="formHaveSubmit1" hidden value="${empty formHaveSubmit1 ? 'N' : formHaveSubmit1}">
                            <form id="basicForm" style="position:relative;">
                                <h4 style="width: 100%">Essential information</h4>
                                <table style="width: 100%;" class="searchTable" >
                                    <colgroup>
                                        <col width="15%"/>
                                        <col width="10%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="35%"/>
                                    </colgroup>
                                    <tbody>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>Name(first name，family name):
                                        </td>
                                        <td>
                                            <input class='input validate[required]' type="text" name="user.userName"  value="${extInfo.userName}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>Age:
                                        </td>
                                        <td>
                                            <input class='input validate[required]' id="userAge" type="text" name="stuUser.userAge" value="${stuUser.userAge}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td rowspan="4" style="text-align: center;padding-top:5px;">
                                            <img src="${sysCfgMap['upload_base_url']}/${extInfo.userHeadImg}" id="showImg" width="110px" height="130px" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
                                            <span id="imageFileSpan" style="cursor: pointer; display:block;" class="${empty extInfo.userHeadImg?'validate[required]':''}">
                                                [<a onclick="$('#imageFile').click();">${empty extInfo.userHeadImg?'upload':'reupload'}</a>]
                                            </span>
                                            <input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
                                            <input type="hidden" id="headimgurl" name="user.userHeadImg" value="${extInfo.userHeadImg}"/>
                                            <span style="color:red">please upload one inch in size picture</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>Gender:
                                        </td>
                                        <td>
                                            <label>
                                                <input type="radio" class='validate[required]' name="user.sexId" <c:if test="${extInfo.sexId eq 'Man' || extInfo.sexId ne 'Woman'}">checked="checked"</c:if> value="Man"/>Man
                                            </label>&nbsp;
                                            <label>
                                                <input type="radio" class='validate[required]' name="user.sexId" <c:if test="${extInfo.sexId eq 'Woman'}">checked="checked"</c:if> value="Woman"/>Woman
                                            </label>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>Date of birth:
                                        </td>
                                        <td>
                                            <input type="text" id="userBirthday" name="user.userBirthday" value="${extInfo.userBirthday}"  class='input validate[required] datepicker'  <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"--%> readonly="readonly" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>Phone Number:
                                        </td>
                                        <td>
                                            <input class='input validate[required]' type="text" name="user.userPhone"  value="${extInfo.userPhone}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>E-mail:
                                        </td>
                                        <td>
                                            <input class='input validate[required]' type="text" name="user.userEmail"  value="${extInfo.userEmail}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>Nationality:
                                        </td>
                                        <td>
                                            <select name="user.nationalityId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                <option value=""></option>
                                                <c:forEach items="${dictTypeEnumGzzyjxNationalList}" var="dict">
                                                    <option value="${dict.dictId}" ${user.nationalityId eq dict.dictId?'selected':''}>${dict.dictDesc}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>Passport No:
                                        </td>
                                        <td>
                                            <input class='input validate[required]' type="text" name="user.passportNo"  value="${user.passportNo}" style="width: 150px;margin-left: 0px"/>
                                        </td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td class="td_left">
                                            <font color="red">*</font>Work uniform:
                                        </td>
                                        <td>
                                            <select class="select validate[required]" name="extInfo.workClother" style="width: 157px;margin-left: 0px;">
                                                <option value=""></option>
                                                <option value="white coat" <c:if test="${extInfo.workClother eq 'white coat'}">selected="selected"</c:if>>white coat</option>
                                                <option value="nurse uniform" <c:if test="${extInfo.workClother eq 'nurse uniform'}">selected="selected"</c:if>>nurse uniform</option>
                                            </select>
                                        </td>
                                        <td class="td_left">
                                            <font color="red">*</font>The uniform size:
                                        </td>
                                        <td>
                                            <select class="select validate[required]" name="stuUser.clotherSizeId" style="width: 157px;margin-left: 0px;">
                                                <option value=""></option>
                                                <option value="S" <c:if test="${stuUser.clotherSizeId eq 'S'}">selected="selected"</c:if>>S</option>
                                                <option value="M" <c:if test="${stuUser.clotherSizeId eq 'M'}">selected="selected"</c:if>>M</option>
                                                <option value="L" <c:if test="${stuUser.clotherSizeId eq 'L'}">selected="selected"</c:if>>L</option>
                                                <option value="XL" <c:if test="${stuUser.clotherSizeId eq 'XL'}">selected="selected"</c:if>>XL</option>
                                                <option value="XXL" <c:if test="${stuUser.clotherSizeId eq 'XXL'}">selected="selected"</c:if>>XXL</option>
                                            </select>
                                        </td>
                                        <td></td>
                                    </tr>

                                    </tbody>
                                </table>
                                <div>
                                    <h4 style="width: 100%;float: left">medical related social(work) experience<span style="float: right;padding-right: 20px">
                                        <img class="opBtn" title="add" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('work')"/>&#12288;
                                        <img class="opBtn" title="delete" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('work');"/>
                                    </span>
                                    </h4>

                                </div>
                                <table style="width: 100%;" class="grid" >
                                    <colgroup>
                                        <col width="6%"/>
                                        <col width="24%"/>
                                        <col width="20%"/>
                                        <col width="20%"/>
                                        <col width="30%"/>
                                    </colgroup>

                                    <tr style="font-weight: bold;">
                                        <th style="text-align: center;">choose</th>
                                        <th style="text-align: center;">beginning and ending time</th>
                                        <th style="text-align: center;">name of the company</th>
                                        <th style="text-align: center;">major in work</th>
                                        <th style="text-align: center;">position</th>
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
                                    <h4 style="width: 100%;float: left">Department of study purpose(You can add only 3 at most)
                                                <span style="float: right;padding-right: 20px">
                                                    <img class="opBtn" title="add" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('spe')"/>&#12288;
                                                    <img class="opBtn" title="delete" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('spe');"/>
                                                </span>
                                    </h4>
                                </div>

                                <table style="width: 100%;" class="grid" >
                                    <colgroup>
                                        <col width="5%"/>
                                        <col width="40%"/>
                                        <col width="55%"/>
                                    </colgroup>

                                    <tr style="font-weight: bold;">
                                        <th style="text-align: center;">choose</th>
                                        <th style="text-align: center;">department of study purpose</th>
                                        <th style="text-align: center;">the length(calculate by weekdays)</th>
                                    </tr>
                                    <tbody id="speTb">
                                    <c:forEach var="speForm" items="${extInfo.speFormList}" varStatus="status">
                                        <tr>
                                            <td><input type="checkbox" name="speIds"/></td>
                                            <td>
                                                <select name="extInfo.speFormList[${status.index}].speId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                                                    <option value=""></option>
                                                    <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                                        <option value="${dict.dictDesc}" ${speForm.speId eq dict.dictDesc?'selected':''}>${dict.dictDesc}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td>
                                                <input  class="validate[required] input datepicker" name="extInfo.speFormList[${status.index}].beginDate" type="text" value="${speForm.beginDate}" readonly="readonly" style="width: 40%"/>-
                                                <input  class="validate[required] input datepicker" name="extInfo.speFormList[${status.index}].endDate" type="text"  value="${speForm.endDate}" readonly="readonly" style="width: 40%"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                                <div>
                                    <h4 style="width: 100%;float: left">Experience in medical education<span style="float: right;padding-right: 20px">
                                        <img class="opBtn" title="add" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('education')"/>&#12288;
                                        <img class="opBtn" title="delete" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('education');"/>
                                    </span>
                                    </h4>
                                </div>
                                <table style="width: 100%;" class="grid" >
                                    <colgroup>
                                        <col width="3%"/>
                                        <col width="17%"/>
                                        <col width="25%"/>
                                        <col width="15%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                        <col width="20%"/>
                                    </colgroup>

                                    <tr style="font-weight: bold;">
                                        <th style="text-align: center;">choose</th>
                                        <th style="text-align: center;">beginning and ending time</th>
                                        <th style="text-align: center;">name of school</th>
                                        <th style="text-align: center;">name of major</th>
                                        <th style="text-align: center;">length of schooling</th>
                                        <th style="text-align: center;">record of formal schooling</th>
                                        <th style="text-align: center;">degree</th>
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

                                <h4 style="width: 100%">Documents and files</h4>
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
                                        <td class="td_left"><font color="red">*</font>passport number:</td>
                                        <td colspan="4">
                                            <input type='text' placeholder="<%--please enter passport number--%>"  style="width: 300px;margin-left: 0px" class="input validate[required]" name="extInfo.passportNo" value='${extInfo.passportNo}'/>
                                            <span id="passportUriSpan" style="display:${!empty extInfo.passportUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.passportUri}" target="_blank">view picture</a>]&#12288;
                                            </span>
                                            <a id="passportUri" href="javascript:uploadFile('passportUri','passport number');" class="btn ${empty extInfo.passportUri?'validate[required]':''}">${empty extInfo.passportUri?'':'re'}upload</a>&#12288;

                                            <a id="passportUriDel" href="javascript:delFile('passportUri');" class="btn" style="${empty extInfo.passportUri?'display:none':''}">delete</a>&#12288;
                                            <input type="hidden" id="passportUriValue" name="extInfo.passportUri" value="${extInfo.passportUri}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="td_left"><font color="red">*</font>medical certificate or medical license number：</td>
                                        <td colspan="4">
                                            <input type='text' placeholder="<%--please enter medical certificate or medical license number--%>" style="width: 300px;margin-left: 0px" class="input validate[required]" name="extInfo.certifiedNo" value='${extInfo.certifiedNo}'/>
                                            <span id="certifiedUriSpan" style="display:${!empty extInfo.certifiedUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.certifiedUri}" target="_blank">view picture</a>]&#12288;
                                            </span>
                                            <a id="certifiedUri" href="javascript:uploadFile('certifiedUri','medical certificate or medical license');" class="btn ${empty extInfo.certifiedUri?'validate[required]':''}">${empty extInfo.certifiedUri?'':'re'}upload</a>&#12288;
                                            <a id="certifiedUriDel" href="javascript:delFile('certifiedUri');" class="btn" style="${empty extInfo.certifiedUri?'display:none':''}">delete</a>&#12288;
                                            <input type="hidden" id="certifiedUriValue" name="extInfo.certifiedUri" value="${extInfo.certifiedUri}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  class="td_left">Other qualification certificat:</td>
                                        <td colspan="4">add qualification certificat&#12288;<img class="opBtn" title="add" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('credentials')"/></td>
                                    </tr>
                                    </tbody>
                                    <tbody id="credentialsTb">
                                    <c:forEach var="reg" items="${extInfo.regList}" varStatus="status">
                                        <tr class="yszz">
                                            <td class="td_left"><font color="red">*</font>certificat number:</td>
                                            <td colspan="4">
                                                <input type='text' placeholder="please enter certificat number" style="width: 300px;" class="input validate[required]" name="extInfo.regList[${status.index}].regNo" value='${reg.regNo}'/>
                                                <span id="regUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                    [<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">view picture</a>]&#12288;
                                                </span>
                                                <a href="javascript:uploadFile('regUri${status.index}','qualification certificat');" class="btn">${empty reg.regUri?'':'re'}upload</a>&#12288;
                                                <img class="opBtn" title="delete" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                                                <input type="hidden" id="regUri${status.index}Value" name="extInfo.regList[${status.index}].regUri" value="${reg.regUri}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tbody>
                                    <c:if test="${stuUser.stuStatusId eq stuStatusEnumUnPassed.id and stuUser.isBack ne 'Y'}">
                                        <tr style="height: 70px;font-size: 110%;">
                                            <td style="background-color: rgb(255, 224, 168);" colspan="99">&nbsp;I'm sorry to say that your application for further education has not been approved.</td>
                                        </tr>
                                        <tr style="height: 80px;">
                                            <td colspan="99">Reason:${auditAgree}</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${stuUser.isBack eq 'Y'}">
                                        <tr style="height: 70px;font-size: 110%;">
                                            <c:if test="${sysCfgMap['jx_templete_name'] eq 'xz'}">
                                                <td style="background-color: #cadeee;" colspan="99">&nbsp;I'm sorry to say that your application for further education has not been approved.</td>
                                            </c:if>
                                            <c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">
                                                <td style="background-color: rgb(255, 224, 168);" colspan="99">&nbsp;I'm sorry to say that your application for further education has not been approved.</td>
                                            </c:if>
                                        </tr>
                                        <tr style="height: 80px;">
                                            <td colspan="4">Reason:${auditAgree}</td>
                                            <td style="text-align: center">
                                                <input class="btn_green" onclick="reApply();" type="button" value="Reapply"/>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <tr>
                                        <td style="text-align: center" colspan="99">
                                            <input type="button" class="btn_green save_btn" onclick="saveInfo('basicForm');" value="Save"/>
                                            <c:if test="${param.flag eq 'Y'}">
                                                <input type="button" id="submit_btn" class="btn_green submit_btn" onclick="submiteApply();" value="Submit" />
                                            </c:if>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>

                    </div>

                    <%--<div  class="div_table parentDiv" id="div_table_3" style="display: none">--%>
                        <%--<table id="applyInfo" style="width: 75%;margin-left: 50px;" class="searchTable" cellpadding="10">--%>
                            <%--<tr>--%>
                                <%--<td colspan="4" style="text-align: center;">--%>
                                    <%--<input class="btn_green" onclick="submiteApply();" type="button" value="Submit"/>--%>
                                <%--</td>--%>
                            <%--</tr>--%>
                        <%--</table>--%>
                    <%--</div>--%>
                    <%--<div  class="div_table parentDiv" id="div_table_4" style="display: none">--%>
                        <%--<c:if test="${stuUser.stuStatusId eq stuStatusEnumPassing.id}">--%>
                            <%--<table style="width: 60%;margin-left: 50px;" class="searchTable" cellpadding="10">--%>
                                <%--<tr style="height: 70px;font-size: 110%;">--%>
                                    <%--<c:if test="${sysCfgMap['jx_templete_name'] eq 'xz'}">--%>
                                        <%--<td style="background-color: #cadeee;">&nbsp;Submit data for audit.</td>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">--%>
                                        <%--<td style="background-color: rgb(255, 224, 168);">&nbsp;Submit data for audit.</td>--%>
                                    <%--</c:if>--%>
                                <%--</tr>--%>
                            <%--</table>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${stuUser.stuStatusId eq stuStatusEnumUnPassed.id and stuUser.isBack ne 'Y'}">--%>
                            <%--<table id="" style="width: 60%;margin-left: 50px;" class="searchTable" cellpadding="10">--%>
                                <%--<tr style="height: 70px;font-size: 110%;">--%>
                                    <%--<td style="background-color: rgb(255, 224, 168);">&nbsp;I'm sorry to say that your application for further education has not been approved.</td>--%>
                                <%--</tr>--%>
                                <%--<tr style="height: 80px;">--%>
                                    <%--<td>Reason:${auditAgree}</td>--%>
                                <%--</tr>--%>
                            <%--</table>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${stuUser.isBack eq 'Y'}">--%>
                            <%--<table id="" style="width: 60%;margin-left: 50px;" class="searchTable" cellpadding="10">--%>
                                <%--<tr style="height: 70px;font-size: 110%;">--%>
                                    <%--<c:if test="${sysCfgMap['jx_templete_name'] eq 'xz'}">--%>
                                        <%--<td style="background-color: #cadeee;" colspan="4">&nbsp;I'm sorry to say that your application for further education has not been approved.</td>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${sysCfgMap['jx_templete_name'] eq 'cd'}">--%>
                                        <%--<td style="background-color: rgb(255, 224, 168);" colspan="4">&nbsp;I'm sorry to say that your application for further education has not been approved.</td>--%>
                                    <%--</c:if>--%>
                                <%--</tr>--%>
                                <%--<tr style="height: 80px;">--%>
                                    <%--<td colspan="3">Reason:${auditAgree}</td>--%>
                                    <%--<td style="text-align: center">--%>
                                        <%--<input class="btn_green" onclick="reApply();" type="button" value="Reapply"/>--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
                            <%--</table>--%>
                        <%--</c:if>--%>
                    <%--</div>--%>
                </div>
            <%--</div>--%>
        </div>
    </div>
    <div style="display: none">
        <!-- 进修专业模板 -->
        <table id="speTemplate">
            <tr>
                <td><input type="checkbox" name="speIds"/></td>
                <td>
                    <select name="extInfo.speFormList[{index}].speId" class="select validate[required]" style="width: 157px;margin-left: 0px;">
                        <option value=""></option>
                        <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                            <option value="${dict.dictDesc}" <%--${stuUser.speId eq dict.dictId?'selected':''}--%>>${dict.dictDesc}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input  class="validate[required] input datepicker" name="extInfo.speFormList[{index}].beginDate" type="text"  readonly="readonly" style="width: 40%"/>-
                    <input  class="validate[required] input datepicker" name="extInfo.speFormList[{index}].endDate" type="text"  readonly="readonly" style="width: 40%"/>
                </td>
            </tr>
        </table>
        <!-- 工作经历模板 -->
        <table id="workTemplate">
            <tr>
                <td><input type="checkbox" name="workIds"/></td>
                <td>
                    <input type='text' class='validate[required] input '  name="extInfo.workResumeList[{index}].clinicalRoundDate" style="width: 140px;" />
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
                <td class="td_left"><font color="red">*</font>qualification certificat number：</td>
                <td>
                    <input type='text' placeholder="please enter qualification certificat number" style="width: 300px;" class="input validate[required]" name="extInfo.regList[{index}].regNo" />
                </td>
                <td colspan="4">
                    <span id="regUri${'{index}'}Span" style="display:${!empty reg.regUri?'':'none'} ">
                        [<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">view picture</a>]&#12288;
                    </span>
                    <a href="javascript:uploadFile('regUri${'{index}'}','qualification certificat');" class="btn">${empty reg.regUri?'':'re'}upload</a>&#12288;
                    <img class="opBtn" title="delete" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                    <input type="hidden" id="regUri${'{index}'}Value" name="extInfo.regList[{index}].regUri">
                </td>

            </tr>
        </table>

        <!--学历信息模板-->
        <table id="educationTemplate">
            <tr>
                <td><input type="checkbox" name="educationIds"/></td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.educationList[{index}].eduRoundDate" placeholder="e.g.:2017.01-2017.09"  style="width: 95%;" />
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