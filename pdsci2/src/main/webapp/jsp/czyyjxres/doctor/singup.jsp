<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/czyyjxres/htmlhead-czyyjxres.jsp">
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style>
    .base_info th, .base_info td {
        height: 45px;
    }
</style>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
    $(document).ready(function(){
        $('.datepicker').datepicker();
        var r = $('#userBirthday').val().match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
        if(r==null) return false;
        var d= new Date(r[1],r[3]-1,r[4]);
        if(d.getFullYear()==r[1] && (d.getMonth()+1)==r[3] && d.getDate()==r[4]){
            var Y = new Date().getFullYear();
            $('#userAge').val(Y-r[1]);
        }
    });
    /**
     * 上传证书及文件
     */
    function uploadFile(type, typeName) {
        jboxOpen("<s:url value='/inx/czyyjxres/uploadFile'/>?operType=" + type, "上传" + typeName, 450, 150);
    }
    /**
     * 删除证书及文件
     */
    function delFile(type) {
        jboxConfirm("确认删除？", function () {
            $("#" + type + "Del").hide();
            $("#" + type + "Span").hide();
            $("#" + type).text("上传");
            $("#" + type + "Value").val("");
            $("#" + type).addClass("validate[required]");
        });
    }
    /**
     * 增加工作经历
     */
    function add(tb) {
        $("#add_a").remove();
        var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
        var index = $("#" + tb + "Tb tr").length;
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
    /**
     * 保存（状态flag：flase未提交 true待审核）
     */
    function saveSingup(flag){
        if('${batchFlow}' == ""){
            jboxInfo("请等待管理员设置系统默认批次！");
            return false;
        }
        $("#flag").val(flag);
        if(flag){
            if($('#registerFormUriValue').val()=="") {
                $('#registerFormUri').addClass("validate[required]");
            }
            if(false == $("#doctorForm").validationEngine("validate")){
                return false;
            }
            jboxConfirm("是否确认提交，提交后不能再修改？" , function(){
                jboxPost("<s:url value='/czyyjxres/doctor/submitSingup'/>" , $("#doctorForm").serialize() , function(resp){
                    if(resp=="1"){
                        jboxTip("保存成功");
                        search();
                    }else{
                        jboxTip(resp);
                    }
                } , null , false);
            });
        }else{
            $('#registerFormUri').removeClass("validate[required]");
            if(false == $("#doctorForm").validationEngine("validate")){
                return false;
            }
            jboxPost("<s:url value='/czyyjxres/doctor/submitSingup'/>" , $("#doctorForm").serialize() , function(resp){
                if(resp=="1"){
                    jboxTip("保存成功");
                    search();
                }else{
                    jboxTip(resp);
                }
            } , null , false);
        }
    }
    //网上报名
    function search(){
        var url = "<s:url value='/czyyjxres/doctor/singup?batchFlow='/>"+$('#batchFlow').val();
        jboxLoad("content", url, true);
    }
    //打印申请表
    function printApplForm(batchFlow){
		$('#registerFormUri').removeClass("validate[required]");
        if(false == $("#doctorForm").validationEngine("validate")){
            return false;
        }
        if('${batchFlow}' == ""){
            jboxInfo("请等待管理员设置系统默认批次！");
            return false;
        }
        jboxPost("<s:url value='/czyyjxres/doctor/valideExist'/>",{"batchFlow":batchFlow},function(resp){
            if(null != resp && resp != ""){
                jboxTip("打印中,请稍等...");
                var url = '<s:url value="/czyyjxres/hospital/printApplForm?resumeFlow="/>'+resp+"&printFlag=doctor";
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
</script>
<div id="singupContent">
    <div id='docTypeForm'>
        <p id="errorMsg" style="color: red;"></p>
        <div class="main_hd"><h2 class="underline">网上报名</h2></div>
        <form id='doctorForm' style="position:relative;">
            <div class="main_bd">
                <div class="div_table">
                    <div class="score_frame">
                        <div class="div_table">
                            <h4>进修批次&#12288;&#12288;&#12288;&#12288;&#12288;
                                <select autocomplete="off" class="select" id="batchFlow" style="width: 160px;margin-left: 5px;" onchange="search()">
                                    <c:forEach items="${batchLst}" var="dict">
                                        <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                                    </c:forEach>
                                </select></h4>
                        </div>
                        <div class="div_table">
                            <h4>基本信息</h4>
                            <div>
                                <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                                    <colgroup>
                                        <col width="12%"/>
                                        <col width="22%"/>
                                        <col width="12%"/>
                                        <col width="22%"/>
                                        <col width="12%"/>
                                        <col width="20%"/>
                                    </colgroup>
                                    <tr>
                                        <th><font color="red">*</font>姓名：</th>
                                        <td><input type='text' class='input validate[required]' name="user.userName" value="${user.userName}"/></td>
                                        <th><font color="red">*</font>出生日期：</th>
                                        <td colspan="2"><input type='text' class='input validate[required] datepicker' id="userBirthday" name="user.userBirthday" value="${user.userBirthday}" onchange="changeAge(this)" readonly="readonly"/></td>
                                        <td rowspan="4" style="text-align: center;padding-top:5px;">
                                            <img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" style="max-width: 170px;max-height: 150px;" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
                                            <span style="cursor: pointer; display:block;" class="${empty user.userHeadImg?'validate[required]':''}">
                                                [<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传照片':'重新上传'}</a>]
                                            </span>
                                            <input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
                                            <input type="hidden" id="headimgurl" value=""/>
                                            <span style="color:red">请上传1寸大小的照片</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><font color="red">*</font>性别：</th>
                                        <td style="padding-left: 10px;">
                                            <label>
                                                <input type="radio" class='validate[required]' name="user.sexId" <c:if test="${param.sexId eq user.sexId || user.sexId eq 'Man'}">checked="checked"</c:if> value="Man"/>男
                                            </label>&nbsp;
                                            <label>
                                                <input type="radio" class='validate[required]' name="user.sexId" <c:if test="${param.sexId eq user.sexId || user.sexId eq 'Woman'}">checked="checked"</c:if> value="Woman"/>女
                                            </label>
                                        </td>
                                        <th><font color="red">*</font>年龄：</th>
                                        <td colspan="2"><input type='text' class='input validate[required]' id="userAge" name="stuUser.userAge" readonly="readonly"/></td>
                                    </tr>
                                    <tr>
                                        <th><font color="red">*</font>身份证号：</th>
                                        <td><input type='text' class='input validate[required]' name="user.idNo" value="${user.idNo}"/></td>
                                        <th><font color="red">*</font>民族：</th>
                                        <td colspan="2"><select name="user.nationId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
                                            <option value="">请选择</option>
                                            <c:forEach items="${userNationEnumList}" var="nation">
                                                <option value="${nation.id}" ${user.nationId eq nation.id?'selected':''}>${nation.name}</option>
                                            </c:forEach>
                                        </select></td>
                                    </tr>
                                    <tr>
                                        <th><font color="red">*</font>职称类别：</th>
                                        <td><select name="extInfo.titleTypeId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
                                            <option value="">请选择</option>
                                            <c:forEach items="${dictTypeEnumTitleGenreList}" var="dict">
                                            <option value="${dict.dictId}" ${extInfo.titleTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                            </c:forEach>
                                        </select></td>
                                        <th><font color="red">*</font>职称：</th>
                                        <td colspan="2"><select name="stuUser.titleId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
                                            <option value="">请选择</option>
                                            <c:forEach items="${dictTypeEnumUserTitleList}" var="dict">
                                            <option value="${dict.dictId}" ${stuUser.titleId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                            </c:forEach>
                                        </select></td>
                                    </tr>
                                    <tr>
                                        <th>所在科室：</th>
                                        <td><input type='text' class='input' name="stuUser.deptName" value="${stuUser.deptName}"/></td>
                                        <th><font color="red">*</font>工作年限：</th>
                                        <td><input type='text' class='input validate[required]' name="stuUser.jobYear" value="${stuUser.jobYear}"/></td>
                                        <th><font color="red">*</font>职务：</th>
                                        <td colspan="2"><input type='text' class='input validate[required]' name="stuUser.postName" value="${stuUser.postName}"/></td>
                                    </tr>
                                    <tr>
                                        <th><font color="red">*</font>执业资格：</th>
                                        <td><select name="stuUser.certifiedTypeId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
                                            <option value="">请选择</option>
                                            <c:forEach items="${dictTypeEnumPracticeGenreList}" var="dict">
                                            <option value="${dict.dictId}" ${stuUser.certifiedTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                            </c:forEach>
                                        </select></td>
                                        <th><font color="red">*</font>进修专业：</th>
                                        <td><select name="stuUser.speId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
                                            <option value="">请选择</option>
                                            <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                                <option value="${dict.dictId}" ${stuUser.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                            </c:forEach>
                                        </select></td>
                                        <th><font color="red">*</font>进修时间：</th>
                                        <td><select name="stuUser.stuTimeId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
                                            <option value="">请选择</option>
                                            <option value="1" <c:if test="${stuUser.stuTimeId eq '1'}">selected="selected"</c:if>>1个月</option>
                                            <option value="2" <c:if test="${stuUser.stuTimeId eq '2'}">selected="selected"</c:if>>2个月</option>
                                            <option value="3" <c:if test="${stuUser.stuTimeId eq '3'}">selected="selected"</c:if>>3个月</option>
                                            <option value="6" <c:if test="${stuUser.stuTimeId eq '6'}">selected="selected"</c:if>>6个月</option>
                                            <option value="12" <c:if test="${stuUser.stuTimeId eq '12'}">selected="selected"</c:if>>12个月</option>
                                        </select></td>
                                    </tr>
                                    <tr>
                                        <th>进修批次：</th>
                                        <td>${batchNo}<input type="hidden" name="stuUser.stuBatId" value="${batchFlow}" /></td>
                                        <th><font color="red">*</font>工作服尺寸：</th>
                                        <td><select class="select validate[required]" name="stuUser.clotherSizeId" style="width: 160px;margin-left: 5px;">
                                            <option value="">请选择</option>
                                            <option value="S" <c:if test="${stuUser.clotherSizeId eq 'S'}">selected="selected"</c:if>>S号</option>
                                            <option value="M" <c:if test="${stuUser.clotherSizeId eq 'M'}">selected="selected"</c:if>>M号</option>
                                            <option value="L" <c:if test="${stuUser.clotherSizeId eq 'L'}">selected="selected"</c:if>>L号</option>
                                            <option value="XL" <c:if test="${stuUser.clotherSizeId eq 'XL'}">selected="selected"</c:if>>XL号</option>
                                            <option value="XXL" <c:if test="${stuUser.clotherSizeId eq 'XXL'}">selected="selected"</c:if>>XXL号</option>
                                        </select></td>
                                        <th><font color="red">*</font>是否住宿：</th>
                                        <td>
                                            <select class="select validate[required]" name="stuUser.isPutup" style="width: 160px;margin-left: 5px;">
                                                <option value="">请选择</option>
                                                <option value="不住宿" <c:if test="${stuUser.isPutup eq '不住宿'}">selected="selected"</c:if>>不住宿</option>
                                                <option value="2人间" <c:if test="${stuUser.isPutup eq '2人间'}">selected="selected"</c:if>>2人间</option>
                                                <option value="4人间" <c:if test="${stuUser.isPutup eq '4人间'}">selected="selected"</c:if>>4人间</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><font color="red">*</font>最高学历：</th>
                                        <td>
                                            <select name="stuUser.maxEduId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
                                                <option/>
                                                <c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
                                                    <option value="${dict.dictId}" ${stuUser.maxEduId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <th><font color="red">*</font>毕业学校：</th>
                                        <td><input type='text' class='input validate[required]' name="stuUser.schoolName" value="${stuUser.schoolName}"/></td>
                                        <th><font color="red">*</font>毕业专业：</th>
                                        <td><input type='text' class='input validate[required]' name="stuUser.schoolSpeName" value="${stuUser.schoolSpeName}"/></td>
                                    </tr>
                                    <tr>
                                        <th><font color="red">*</font>最高学历开始时间：</th>
                                        <td><input type='text' class='input validate[required] datepicker' name="stuUser.maxEduBdate" value="${stuUser.maxEduBdate}" /></td>
                                        <th><font color="red">*</font>最高学历结束时间：</th>
                                        <td><input type='text' class='input validate[required] datepicker' name="stuUser.maxEduEdate" value="${stuUser.maxEduEdate}" /></td>
                                        <th>是否熟练电脑：</th>
                                        <td><select name="stuUser.isComputer" class="select" style="width: 160px;margin-left: 5px;">
                                            <option value="">请选择</option>
                                            <option value="Y" ${stuUser.isComputer eq 'Y'?'selected':''}>是</option>
                                            <option value="N" ${stuUser.isComputer eq 'N'?'selected':''}>否</option>
                                        </select></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="div_table">
                            <h4>本人联系方式</h4>
                            <div>
                                <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                                    <colgroup>
                                        <col width="12%"/>
                                        <col width="22%"/>
                                        <col width="12%"/>
                                        <col width="22%"/>
                                        <col width="12%"/>
                                        <col width="20%"/>
                                    </colgroup>
                                    <tr>
                                        <th><font color="red">*</font>手机号码：</th>
                                        <td><input type='text' class='input  validate[required]' name="user.userPhone" value="${user.userPhone}"/></td>
                                        <th><font color="red">*</font>电子邮箱：</th>
                                        <td><input type='text' class='input  validate[required]' name="user.userEmail" value="${user.userEmail}"/></td>
                                        <th><font color="red">*</font>选送单位：</th>
                                        <td><input type='text' class='input validate[required]' name="stuUser.sendComName" value="${stuUser.sendComName}"/></td>
                                    </tr>
                                    <tr>
                                        <th><font color="red">*</font>医院等级：</th>
                                        <td><select name="stuUser.hospitalLevelId" class="select validate[required]" style="width: 160px;margin-left: 5px;">
                                            <option value="">请选择</option>
                                            <c:forEach items="${dictTypeEnumHospitalRankList}" var="dict">
                                            <option value="${dict.dictId}" ${stuUser.hospitalLevelId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                            </c:forEach>
                                        </select></td>
                                        <th><font color="red">*</font>选送单位详细地址：</th>
                                        <td colspan="3"><input type='text'style="width:460px;" class='input validate[required]' name="stuUser.sendComAddress" value="${stuUser.sendComAddress}"/></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="div_table">
                            <h4>工作经历</h4>
                            <div>
                                <table border="0" cellpadding="0" cellspacing="0" class="grid">
                                    <colgroup>
                                        <col width="12%">
                                        <col width="22%"/>
                                        <col width="22%"/>
                                        <col width="22%"/>
                                        <col width="22%"/>
                                    </colgroup>
                                    <tr>
                                        <th colspan="5" style="text-align: left;">
                                            <span style="float: right;padding-right: 20px">
                                                <img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('work')"/>&#12288;
                                                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('work');"/>
                                            </span>
                                        </th>
                                    </tr>
                                    <tr style="font-weight: bold;">
                                        <td></td>
                                        <td>起止时间</td>
                                        <td>工作单位</td>
                                        <td>从事工作</td>
                                        <td>职务</td>
                                    </tr>
                                    <tbody id="workTb">
                                    <c:forEach var="resume" items="${extInfo.workResumeList}" varStatus="status">
                                        <tr>
                                            <td><input type="checkbox" name="workIds"/></td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.workResumeList[${status.index}].clinicalRoundDate" placeholder="开始-结束时间" value="${resume.clinicalRoundDate}" style="width: 160px;" />
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.workResumeList[${status.index}].hospitalName" value="${resume.hospitalName}" style="width: 160px;"/>
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.workResumeList[${status.index}].workDescription" value="${resume.workDescription}" style="width: 160px;"/>
                                            </td>
                                            <td>
                                                <input type='text' class='validate[required] input' name="extInfo.workResumeList[${status.index}].postName" value="${resume.postName}" style="width: 160px;"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <div class="div_table">
                            <h4>说明</h4>
                            <div>
                                <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                                    <colgroup>
                                        <col width="12%"/>
                                        <col width="22%"/>
                                        <col width="12%"/>
                                        <col width="22%"/>
                                        <col width="12%"/>
                                        <col width="20%"/>
                                    </colgroup>
                                    <tr>
                                        <th><font color="red">*</font>进修目的：</th>
                                        <td colspan="5"><textarea name="extInfo.studyAim" style="width: 98%; height: 100px" class="validate[required] [maxSize[150]]" placeholder="描述不超过150字">${extInfo.studyAim}</textarea></td>
                                    </tr>
                                    <tr>
                                        <th><font color="red">*</font>本人从事专业现有业务水平：</th>
                                        <td colspan="5"><textarea style="width: 98%; height: 100px" name="extInfo.vocationalLevel" class="validate[required] [maxSize[150]]" placeholder="（请注明现工作形式主要是门诊还是住院部）">${extInfo.vocationalLevel}</textarea></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="div_table">
                            <h4>证书及文件</h4>
                            <div>
                                <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                                    <tr>
                                        <th width="20%"><font color="red">*</font>身份证图片：</th>
                                        <td colspan="3">
                                            <span id="idNoUriSpan" style="display:${!empty extInfo.idNoUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.idNoUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="idNoUri" href="javascript:uploadFile('idNoUri','身份证图片');" class="btn ${empty extInfo.idNoUri?'validate[required]':''}">${empty extInfo.idNoUri?'':'重新'}上传</a>&#12288;
                                            <a id="idNoUriDel" href="javascript:delFile('idNoUri');" class="btn" style="${empty extInfo.idNoUri?'display:none':''}">删除</a>&#12288;
                                            <input type="hidden" id="idNoUriValue" name="extInfo.idNoUri" value="${extInfo.idNoUri}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><font color="red">*</font>毕业证图片：</th>
                                        <td colspan="3">
                                            <%--<input type="text" class="input validate[required]" placeholder="请填写毕业证编号" style="width: 300px;" name="extInfo.graduatedNo" value='${extInfo.graduatedNo}'/>--%>
                                            <span id="graduatedUriSpan" style="display:${!empty extInfo.graduatedUri?'':'none'} ">
		              	                        [<a href="${sysCfgMap['upload_base_url']}/${extInfo.graduatedUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="graduatedUri" href="javascript:uploadFile('graduatedUri','毕业证书');" class="btn ${empty extInfo.graduatedUri?'validate[required]':''}">${empty extInfo.graduatedUri?'':'重新'}上传扫描件</a>&#12288;
                                            <a id="graduatedUriDel" href="javascript:delFile('graduatedUri');" class="btn" style="${empty extInfo.graduatedUri?'display:none':''}">删除</a>&#12288;
                                            <input type="hidden" id="graduatedUriValue" name="extInfo.graduatedUri" value="${extInfo.graduatedUri}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><font color="red" id="zgzs">*</font>医师（护士、护师）资格证书编号：</th>
                                        <td colspan="3">
                                            <input type='text' class="input validate[required]" placeholder="请填写医师（护士、护师）资格证书编号" style="width:300px;" name="extInfo.qualifiedNo" value="${extInfo.qualifiedNo}"/>
                                            <span id="qualifiedUriSpan" style="display:${!empty extInfo.qualifiedUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.qualifiedUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="qualifiedUri" href="javascript:uploadFile('qualifiedUri','医师（护士、护师）资格证');" class="btn ${empty extInfo.qualifiedUri?'validate[required]':''}">${empty extInfo.qualifiedUri?'':'重新'}上传扫描件</a>&#12288;
                                            <a id="qualifiedUriDel" href="javascript:delFile('qualifiedUri');" class="btn" style="${empty extInfo.qualifiedUri?'display:none':''}">删除</a>&#12288;
                                            <input type="hidden" id="qualifiedUriValue" name="extInfo.qualifiedUri" value="${extInfo.qualifiedUri}">
                                        </td>
                                    </tr>
                                    <tr class="yszz">
                                        <th><font color="red">*</font>医师（护士）执业证书编号：</th>
                                        <td colspan="3">
                                            <input type='text' placeholder="请填写医师（护士）执业证书编号" style="width: 300px;" class="input validate[required]" name="extInfo.certifiedNo" value='${extInfo.certifiedNo}'/>
                                            <span id="certifiedUriSpan" style="display:${!empty extInfo.certifiedUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.certifiedUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="certifiedUri" href="javascript:uploadFile('certifiedUri','医师（护士）执业证');" class="btn ${empty extInfo.certifiedUri?'validate[required]':''}">${empty extInfo.certifiedUri?'':'重新'}上传扫描件</a>&#12288;
                                            <a id="certifiedUriDel" href="javascript:delFile('certifiedUri');" class="btn" style="${empty extInfo.certifiedUri?'display:none':''}">删除</a>&#12288;
                                            <input type="hidden" id="certifiedUriValue" name="extInfo.certifiedUri" value="${extInfo.certifiedUri}">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>其它资质证书：</th>
                                        <td colspan="3">添加资质证书&#12288;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('credentials')"/></td>
                                    </tr>
                                    <tbody id="credentialsTb">
                                    <c:forEach var="reg" items="${extInfo.regList}" varStatus="status">
                                        <tr class="yszz">
                                            <th><font color="red">*</font>资质证书名称：</th>
                                            <td colspan="3">
                                                <input type='text' placeholder="请填写证书名称" style="width: 300px;" class="input validate[required]" name="extInfo.regList[${status.index}].regNo" value='${reg.regNo}'/>
                                                    <span id="regUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                        [<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">查看图片</a>]&#12288;
                                                    </span>
                                                <a href="javascript:uploadFile('regUri${status.index}','资质证书');" class="btn">${empty reg.regUri?'':'重新'}上传扫描件</a>&#12288;
                                                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                                                <input type="hidden" id="regUri${status.index}Value" name="extInfo.regList[${status.index}].regUri" value="${reg.regUri}">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tr>
                                        <th width="20%"><font color="red">*</font>进修生申请登记表：</th>
                                        <td colspan="2" style="width:33%;">
                                            <span id="registerFormUriSpan" style="display:${!empty extInfo.registerFormUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.registerFormUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
                                            <a id="registerFormUri" href="javascript:uploadFile('registerFormUri','申请登记图片');" class="btn <c:if test="${empty extInfo.registerFormUri}">validate[required]</c:if>">${empty extInfo.registerFormUri?'':'重新'}上传</a>&#12288;
                                            <a id="registerFormUriDel" href="javascript:delFile('registerFormUri');" class="btn" style="${empty extInfo.registerFormUri?'display:none':''}">删除</a>&#12288;
                                            <input type="hidden" id="registerFormUriValue" name="extInfo.registerFormUri" value="${extInfo.registerFormUri}">
                                        </td>
                                        <td style="line-height:14px;"><font color="red">先点‘暂存’按钮，再点‘打印申请表’盖章后上传扫描件或照片，最后点‘提交’报名</font></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="nextPage" class="button" style="margin: 30px;">
                <input type="hidden" id="flag" name="flag" />
                <input class="btn_green" type="button" onclick="saveSingup(false);" value="暂&#12288;存"/>
                <input class="btn_green" type="button" onclick="saveSingup(true);" value="提&#12288;交"/>
                <input class="btn_green" type="button" onclick="printApplForm('${batchFlow}');" value="打印申请表"/>
            </div>
        </form>
    </div>
    <div style="display: none">
        <!-- 工作经历模板 -->
        <table id="workTemplate">
            <tr>
                <td><input type="checkbox" name="workIds"/></td>
                <td>
                    <input type='text' class='validate[required] input ' placeholder="开始-结束时间" name="extInfo.workResumeList[{index}].clinicalRoundDate" />
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.workResumeList[{index}].hospitalName" />
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.workResumeList[{index}].workDescription" />
                </td>
                <td>
                    <input type='text' class='validate[required] input' name="extInfo.workResumeList[{index}].postName" />
                </td>
            </tr>
        </table>
        <!--资质证书模板-->
        <table id="credentialsTemplate">
            <tr class="yszz">
                <th><font color="red">*</font>资质证书名称：</th>
                <td colspan="3">
                    <input type='text' placeholder="请填写证书名称" style="width: 300px;" class="input validate[required]" name="extInfo.regList[{index}].regNo" />
                    <span id="regUri${'{index}'}Span" style="display:${!empty reg.regUri?'':'none'} ">
                        [<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">查看图片</a>]&#12288;
                    </span>
                    <a href="javascript:uploadFile('regUri${'{index}'}','资质证书');" class="btn">${empty reg.regUri?'':'重新'}上传扫描件</a>&#12288;
                    <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delRegTr(this);"/>
                    <input type="hidden" id="regUri${'{index}'}Value" name="extInfo.regList[{index}].regUri">
                </td>
            </tr>
        </table>
    </div>
</div>