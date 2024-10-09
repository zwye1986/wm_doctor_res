<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
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
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style>
    .div_table h4 {
        color: #000000;
        font: 15px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info th, .grid th {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info td, .grid td, .input {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }
    .base_info td a {
        color: #ffffff;
        background-color: #409eff;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
        padding: 3px 10px;
        border-radius: 2px;
    }
    <style>
     .div_table h4 {
         color: #000000;
         font: 15px 'Microsoft Yahei';
         font-weight: 500;
     }

    .base_info th, .grid th {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 500;
    }

    .base_info td, .grid td, .input {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }

    input {
        width: 68px;
    }
</style>
</style>
<script type="text/javascript">
    function saveBaseInfo(){
        if(!$("#BaseInfoForm").validationEngine("validate") || !checkSetJointOrg() || !checkOthers()){
            $("#indexBody").scrollTop("0px");
            return false;
        }
        var orgProvNameText = $("#orgProvId option:selected").text();
        if(orgProvNameText == "选择省"){
            orgProvNameText = "";
        }
        var orgCityNameText = $("#orgCityId option:selected").text();
        if(orgCityNameText == "选择市"){
            orgCityNameText = "";
        }
        var orgAreaNameText = $("#orgAreaId option:selected").text();
        if(orgAreaNameText == "选择地区"){
            orgAreaNameText = "";
        }
        $("#orgProvName").val(orgProvNameText);
        $("#orgCityName").val(orgCityNameText);
        $("#orgAreaName").val(orgAreaNameText);
        jboxSubmit($("#BaseInfoForm"), "<s:url value='/jsres/base/saveBase'/>", function(resp){
            if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
                $("#submitBtn").show();
                setTimeout(function(){
                    <%--hosMain('${sessionScope.currUser.orgFlow}');--%>
                    loadInfo('${GlobalConstant.BASIC_INFO}','${sessionScope.currUser.orgFlow}','${pdfn:getCurrYear()}');
                },1000);
            }
        } , null , true);
    }

    function checkOthers() {
        if(!$("#hospitalLevelLicenceUrlValue").val()) {
            jboxTip("请添加医院等级证书！");
            return false;
        }

        if(!$("#professionLicenceUrlValue").val()) {
            jboxTip("请添加执业许可证！");
            return false;
        }

        return true;
    }

    function checkSetJointOrg() {
        var joints = $("#jointContractBody tr");
        // 校验必填项是不是都填了
        for(var i = 0; i < joints.length; i++) {
            var joint = joints[i];
            var jointOrg = $(joint).find('select[name="jointOrgFlows"]');
            if(!$(jointOrg).val()) {
                jboxTip("请选择协同单位！");
                return false;
            }

            var speId = $(joint).find('select[name="speIds"]');
            if(!$(speId).val()) {
                jboxTip("请选择专业基地！");
                return false;
            }

            var uploadFiles = $(joint).find("input[name='files']");
            var uploadNum = 0;
            for(var j = 0; j < uploadFiles.length; j++) {
                var uploadFile = uploadFiles[j];
                if($(uploadFile).val()) {
                    uploadNum++;
                }
            }

            // 记录一下该基地上传了几个文件
            var fileUploadNum = $(joint).find("input[name='fileUploadNum']");
            $(fileUploadNum).val(uploadNum);

            var fileFlows = $(joint).find("input[name='jointContractFileFlows']");

            if(!fileFlows.length) {
                jboxTip("请上传协同关系协议！");
                return false;
            }

            fileFlows = Array.from(fileFlows).filter(function(item) {
                return $(item).val();
            });
            // 记录下原来的文件还剩几个
            var fileRemainNum = $(joint).find("input[name='fileRemainNum']");
            $(fileRemainNum).val(fileFlows.length);
        }

        return true;
    }

    function uploadPdfFile(type,typeName,fileType,fileSuffix) {
        var url = "<s:url value='/jsres/doctor/uploadFile'/>?operType="+type+"&fileType="+fileType+"&fileSuffix="+fileSuffix;
        jboxOpen(url, "上传"+typeName, 500, 185);
    }

    function changeUrlSpanShow(obj) {
        var isShow = $(obj).val() == 'Y';
        var varName = $(obj).attr("name");
        varName = varName.split('.')[1];
        varName += 'UrlShowSpan';
        if(isShow) {
            $("#" + varName).show();
        }else {
            $("#" + varName).hide();
        }

    }

    String.prototype.htmlFormartById = function(data){
        var html = this;
        for(var key in data){
            var reg = new RegExp('\\{'+key+'\\}','g');
            html = html.replace(reg,data[key]);
        }
        return html;
    };

    String.prototype.htmlFormatByIndex = function(data, pre){
        var html = this;
        var reg = new RegExp(pre + "\\[\\d+\\]", "g");
        html = html.replace(reg, pre + "[" + data + "]");
        return html;

    };

    $(document).ready(function(){
        if ($("#ywfgfzrTb tr").length <= 0) {
            add('ywfgfzr');
        }
        if ($("#zpywkslxrTb tr").length <= 0) {
            add('zpywkslxr');
        }
        if ($("#zpglbmfzrTb tr").length <= 0) {
            add('zpglbmfzr');
        }
    });

    function add(tb) {
        var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
        var index = $("#" + tb + "Tb tr").length;
        cloneTr.html(cloneTr.html().htmlFormartById({"index": index}));
        $("#" + tb + "Tb").append(cloneTr);
    }

    function del(obj, type, pre) {
        $(obj).parent().parent().remove();
        if(type) {
            var index = $("#" + type + "Tb tr").length;
            for(var i = 0; i < index; i++) {
                var cur = $($("#" + type + "Tb tr")[i]);
                cur.html(cur.html().htmlFormatByIndex(i + "", pre));
            }
        }
    }

    function uploadFile(type,typeName,fileType) {
        var url = "<s:url value='/jsres/doctor/uploadFile'/>?operType="+type+"&fileType="+fileType;
        jboxOpen(url, "上传"+typeName, 500, 185);
    }

    function delFile(type) {
        jboxConfirm("确认删除？" , function(){
            $("#"+type+"Del").hide();
            $("#"+type+"Span").hide();
            $("#"+type).text("上传");
            $("#"+type+"Value").val("");
            $("#"+type+"Se").show();
        });
    }

    function valueUpdate(cur) {
        $(cur).attr("value", $(cur).val());
        if(cur.tagName == 'SELECT') {
            $(cur).find("option[value='" + $(cur).val() + "']").attr("selected", true);
        }
    }

    function saveBaseMainInfo() {
        saveBaseInfo();
        // saveEducationInfo();
    }

    $(document).ready(function () {
        if ($("#teachingTb tr").length <= 0) {
            add('teaching');
        }
        if ($("#centerTb tr").length <= 0) {
            add('center');
        }
    });

    $(document).ready(function () {
        for (var i = 1; i < 3; i++) {
            for (var j = 1; j < 5; j++) {
                calculate(i, "td" + j);
            }
        }
        for (var i = 5; i < 7; i++) {
            calculate(2, "td" + i);
        }
        $("#cw").text(parseInt($("#td11").text()) + parseInt($("#td12").text()));
        $("#mz").text(parseInt($("#td21").text()) + parseInt($("#td22").text()));
        $("#cy").text(parseInt($("#td31").text()) + parseInt($("#td32").text()));
        $("#dj").text(parseInt($("#td41").text()) + parseInt($("#td42").text()) + parseInt($("#td62").text()));
    })
    ;

    function calculate(tableName, className
    ) {
        var sum = 0;
        $("#table" + tableName + " ." + className).each(function () {
            var val = $(this).val();
            if (val == null || val == undefined || val == '' || isNaN(val)) {
                val = 0;
            }
            sum += parseFloat(val);
        });
        $("#" + className + tableName).text(parseFloat(sum.toFixed(3)));
        $("#cw").text(parseInt($("#td11").text()) + parseInt($("#td12").text()));
        $("#mz").text(parseInt($("#td21").text()) + parseInt($("#td22").text()));
        $("#cy").text(parseInt($("#td31").text()) + parseInt($("#td32").text()));
        $("#dj").text(parseInt($("#td41").text()) + parseInt($("#td42").text()) + parseInt($("#td62").text()))
    }

    function addFile() {
        $('#filesTable').append($("#fileTableFormat tr:eq(0)").clone());
    }

    function moveTr(obj) {
        jboxConfirm("确认删除？", function () {
            var tr = obj.parentNode.parentNode;
            tr.remove();
        });
    }

    function addJointFile(obj) {
        var td = obj.parentNode;
        /*if($(td).find("div[class='jointContractFile']").length >= 3) {
            jboxTip("一个协同单位最多只能上传3个协同关系协议！");
            return;
        }*/

        $(td).append($("#jointContractFileTemplate div").clone());
    }

    function delJointFile(obj) {
        jboxConfirm("确认删除？" , function(){
            var div = obj.parentNode;
            div.remove();
        });
    }

    function moveJointTr(obj) {
        jboxConfirm("确认删除？", function () {
            var tr = obj.parentNode.parentNode;
            tr.remove();
        });
    }

    function addJointContract() {
        if($("#jointContractBody").find("tr").length >= 3) {
            jboxTip("最多只能有三个协同单位！");
            return;
        }

        $('#jointContractBody').append($("#jointContractTemplate tr:eq(0)").clone());
    }

    function uploadFileCheck(obj) {

        var fileName = $(obj).val();
        if (!$.trim(fileName)) {
            jboxTip("请选择文件！");
            return;
        }
        fileName = $.trim(fileName);
        var types = ".pdf";
        var regStr = /\.pdf$/i;
        if (!regStr.test(fileName)) {
            $(obj).val("");
            jboxTip("请上传 pdf 格式的文件");
            return;
        }
    }
</script>
<input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
<form id='BaseInfoForm' style="position:relative;">
    <input type="hidden" name="resBase.orgFlow" value="${resBase.orgFlow}"/>
    <input type="hidden" name="sysOrg.orgFlow" value="${sessionScope.currUser.orgFlow}"/>
    <input type="hidden" name="baseFlow" value="${sessionScope.currUser.orgFlow}"/>
    <input type="hidden" name="flag" value="${GlobalConstant.BASIC_MAIN_ALL}"/>
    <input type="hidden" name="resBase.sessionNumber" value="${pdfn:getCurrYear()}"/>
    <div class="main_bd">
        <div class="div_table">
            <h4><span class="red">*</span>基本信息</h4>
            <table border="0" cellspacing="0" cellpadding="0" class="base_info" id="table1">
                <colgroup>
                    <col width="20%"/>
                    <col width="15%"/>
                    <col width="15%"/>
                    <col width="15%"/>
                    <col width="15%"/>
                    <col width="15%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th><span class="red">*</span>培训基地名称：</th>
                    <td colspan="2"><%--<input type="text"  style="width: 115px"  class='input' id="orgName" name="sysOrg.orgName" value="${sysOrg.orgName}" readonly="readonly"/>--%>
                        <span>${sysOrg.orgName}</span>
                    </td>
                    <th>所属地区：</th>
                    <td colspan="2">
                        <div id="provCityAreaId">
                            <select id="orgProvId" name="sysOrg.orgProvId" class="province select" data-value="${sysOrg.orgProvId}" style="width: 127px;" data-first-title="选择省"></select>
                            <select id="orgCityId" name="sysOrg.orgCityId" class="city select" data-value="${sysOrg.orgCityId}" style="width: 127px;" data-first-title="选择市"></select>
                            <select id="orgAreaId" name="sysOrg.orgAreaId" class="area select" data-value="${sysOrg.orgAreaId}" style="width: 127px;" data-first-title="选择地区"></select>
                        </div>
                        <input id="orgProvName" name="sysOrg.orgProvName" type="hidden" value="${sysOrg.orgProvName}">
                        <input id="orgCityName" name="sysOrg.orgCityName" type="hidden" value="${sysOrg.orgCityName}">
                        <input id="orgAreaName" name="sysOrg.orgAreaName" type="hidden" value="${sysOrg.orgAreaName}">
                        <script type="text/javascript">
                            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
                            $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
                            $.cxSelect.defaults.nodata = "none";
                            $("#provCityAreaId").cxSelect({
                                selects : ["province", "city", "area"],
                                nodata : "none",
                                firstValue : "",
                                disabled: true
                            });
                        </script>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>培训基地地址：</th>
                    <td colspan="2"><input type="text" style="width: 90%"  name="sysOrg.orgAddress"  class='input validate[required]' value="${sysOrg.orgAddress}"/></td>
                    <th><span class="red">*</span>邮政编码：</th>
                    <td colspan="2"><input type="text"  style="width: 115px" name="sysOrg.orgZip" class="input validate[required,custom[chinaZip]]"  value="${sysOrg.orgZip}"/></td>
                </tr>
                <%--<tr>
                    <th><span class="red">*</span>培训基地负责人：</th>
                    <td>
                        <input type="text" style="width: 115px" name="sysOrg.orgPersonInCharge" class='input validate[required]' value="${sysOrg.orgPersonInCharge}"/>
                    </td>
                    <th>职称：</th>
                    <td>
                        <select name="basicInfo.jdfzrTitleId" class="select" style="margin-left: 4px; width: 121px;">
                            <option></option>
                            <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                <option value="${title.dictId}"
                                        <c:if test='${basicInfo.jdfzrTitleId == title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>职务：</th>
                    <td>
                        <select name="basicInfo.jdfzrPostId" class="select" style="margin-left: 4px; width: 121px;">
                            <option></option>
                            <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                <option value="${post.dictId}"
                                        <c:if test='${basicInfo.jdfzrPostId == post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><span class="red"></span>手机号码：</th>
                    <td>
                        <input type="text" class='input validate[custom[mobile]]' style="width: 115px"
                               name="basicInfo.jdfzrMobilephone" oninput="valueUpdate(this);"
                               value="${basicInfo.jdfzrMobilephone }"/>
                    </td>
                    <th><span class="red"></span>固定电话：</th>
                    <td>
                        <input type="text" class='input validate[custom[phone]]' style="width: 115px"
                               name="basicInfo.jdfzrFixedPhone" oninput="valueUpdate(this);"
                               value="${basicInfo.jdfzrFixedPhone }"/>
                    </td>
                    <th><span class="red"></span>邮箱地址：</th>
                    <td>
                        <input type="text" class='input validate[custom[email]]' style="width: 115px"
                               name="basicInfo.jdfzrMailAddress" oninput="valueUpdate(this);"
                               value="${basicInfo.jdfzrMailAddress }"/>
                    </td>
                </tr>--%>
                </tbody>
            </table>

            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="jdfzrTable" style="table-layout: fixed">
                <tr>
                    <th style="width: 15%">培训基地负责人<span class="red">*</span></th>
                    <th style="width: 15%">手机号码<span class="red">*</span></th>
                    <th style="width: 15%">固定电话<span class="red">*</span></th>
                    <th style="width: 15%">邮箱地址<span class="red">*</span></th>
                    <th style="width: 15%">职称</th>
                    <th style="width: 15%">职务<span class="red">*</span></th>
                    <th style="width: 10%"></th>
                    <%--<th>操作&nbsp;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                     style="cursor: pointer;position: absolute;margin-top: 2px;" onclick="javascript:add('zpglbmfzr')" /></th>--%>
                </tr>
                <tbody id="jdfzrTb">
                <tr>
                    <td style="position: relative">
                        <input type="text" style="width: 115px" name="sysOrg.orgPersonInCharge" class='input validate[required]' value="${sysOrg.orgPersonInCharge}"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[mobile]]' style="width: 115px"
                               name="basicInfo.jdfzrMobilephone" oninput="valueUpdate(this);"
                               value="${basicInfo.jdfzrMobilephone }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[phone]]' style="width: 115px"
                               name="basicInfo.jdfzrFixedPhone" oninput="valueUpdate(this);"
                               value="${basicInfo.jdfzrFixedPhone }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[email]]' style="width: 115px"
                               name="basicInfo.jdfzrMailAddress" oninput="valueUpdate(this);"
                               value="${basicInfo.jdfzrMailAddress }"/>
                    </td>
                    <td>
                        <select name="basicInfo.jdfzrTitleId" class="select">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                <option value="${title.dictId}"
                                        <c:if test='${basicInfo.jdfzrTitleId == title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name="basicInfo.jdfzrPostId" class="select validate[required]">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                <option value="${post.dictId}"
                                        <c:if test='${basicInfo.jdfzrPostId == post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <%--<td><a onclick="del(this, 'zpglbmfzr', 'zpglbmfzrList');">删除</a></td>--%>
                </tr>
                </tbody>
            </table>

            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="zpglbmfzrTable" style="table-layout: fixed">
                <tr>
                    <th style="width: 15%">住培管理部门负责人<span class="red">*</span></th>
                    <th style="width: 15%">手机号码<span class="red">*</span></th>
                    <th style="width: 15%">固定电话<span class="red">*</span></th>
                    <th style="width: 15%">邮箱地址<span class="red">*</span></th>
                    <th style="width: 15%">职称</th>
                    <th style="width: 15%">职务<span class="red">*</span></th>
                    <th style="width: 10%"></th>
                    <%--<th>操作&nbsp;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                     style="cursor: pointer;position: absolute;margin-top: 2px;" onclick="javascript:add('zpglbmfzr')" /></th>--%>
                </tr>
                <tbody id="zpglbmfzrTb">
                <tr>
                    <td style="position: relative">
                        <input type="text" class="input validate[required]" style="width: 115px"
                               name="basicInfo.zpglbmfzrList[0].contactorName" oninput="valueUpdate(this);"
                               value="${basicInfo.zpglbmfzrList[0].contactorName }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[mobile]]' style="width: 115px"
                               name="basicInfo.zpglbmfzrList[0].mobilephone" oninput="valueUpdate(this);"
                               value="${basicInfo.zpglbmfzrList[0].mobilephone }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[phone]]' style="width: 115px"
                               name="basicInfo.zpglbmfzrList[0].fixedPhone" oninput="valueUpdate(this);"
                               value="${basicInfo.zpglbmfzrList[0].fixedPhone }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[email]]' style="width: 115px"
                               name="basicInfo.zpglbmfzrList[0].mailAddress" oninput="valueUpdate(this);"
                               value="${basicInfo.zpglbmfzrList[0].mailAddress }"/>
                    </td>
                    <td>
                        <select name="basicInfo.zpglbmfzrList[0].titleId" class="select" onchange="valueUpdate(this);">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                <option value="${title.dictId}"
                                        <c:if test='${basicInfo.zpglbmfzrList[0].titleId == title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name="basicInfo.zpglbmfzrList[0].postId" class="select validate[required]" onchange="valueUpdate(this);">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                <option value="${post.dictId}"
                                        <c:if test='${basicInfo.zpglbmfzrList[0].postId == post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <%--<td><a onclick="del(this, 'zpglbmfzr', 'zpglbmfzrList');">删除</a></td>--%>
                </tr>
                </tbody>
            </table>

            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="contactManTable" style="table-layout: fixed">
                <tr>
                    <th style="width: 15%">联络员<span class="red">*</span></th>
                    <th style="width: 15%">手机号码<span class="red">*</span></th>
                    <th style="width: 15%">固定电话<span class="red">*</span></th>
                    <th style="width: 15%">邮箱地址<span class="red">*</span></th>
                    <th style="width: 15%">职称</th>
                    <th style="width: 15%">职务<span class="red">*</span></th>
                    <th style="width: 10%">操作&nbsp;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                                          style="cursor: pointer;position: absolute;margin-top: 2px;" onclick="javascript:add('contactMan')" /></th>
                </tr>
                <tbody id="contactManTb">
                <c:forEach var="contactMan" items="${basicInfo.contactManList}" varStatus="status">
                    <tr>
                        <td style="position: relative">
                            <input type="text" class="input validate[required]" style="width: 115px"
                                   name="basicInfo.contactManList[${status.index}].contactorName" oninput="valueUpdate(this);"
                                   value="${contactMan.contactorName }"/>
                        </td>
                        <td>
                            <input type="text" class='input validate[required,custom[mobile]]' style="width: 115px"
                                   name="basicInfo.contactManList[${status.index}].mobilephone" oninput="valueUpdate(this);"
                                   value="${contactMan.mobilephone }"/>
                        </td>
                        <td>
                            <input type="text" class='input validate[required,custom[phone]]' style="width: 115px"
                                   name="basicInfo.contactManList[${status.index}].fixedPhone" oninput="valueUpdate(this);"
                                   value="${contactMan.fixedPhone }"/>
                        </td>
                        <td>
                            <input type="text" class='input validate[required,custom[email]]' style="width: 115px"
                                   name="basicInfo.contactManList[${status.index}].mailAddress" oninput="valueUpdate(this);"
                                   value="${contactMan.mailAddress }"/>
                        </td>
                        <td>
                            <select name="basicInfo.contactManList[${status.index}].titleId" class="select" onchange="valueUpdate(this);">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                    <option value="${title.dictId}"
                                            <c:if test='${contactMan.titleId == title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="basicInfo.contactManList[${status.index}].postId" class="select validate[required]" onchange="valueUpdate(this);">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                    <option value="${post.dictId}"
                                            <c:if test='${contactMan.postId == post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
                                 style="cursor: pointer;" onclick="javascript:del(this, 'contactMan', 'contactManList');" /></td>
                        <%--<td><a onclick="del(this, 'contactMan', 'contactManList');">删除</a></td>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <%--<table border="2px" cellpadding="0" cellspacing="0" class="grid" id="zpglbmfzrTable">
                <colgroup>
                    <col width="20%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="20%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    &lt;%&ndash;<col width="10%"/>&ndash;%&gt;
                </colgroup>
                <tr>
                    <th>住培管理部门负责人<span class="red">*</span></th>
                    <th>手机号码</th>
                    <th>固定电话</th>
                    <th>邮箱地址</th>
                    <th>职称</th>
                    <th>职务</th>
                    &lt;%&ndash;<th>操作&nbsp;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                     style="cursor: pointer;position: absolute;margin-top: 2px;" onclick="javascript:add('zpglbmfzr')" /></th>&ndash;%&gt;
                </tr>
                <tbody id="zpglbmfzrTb">
                <tr>
                    <td style="position: relative">
                        <input type="text" class="input validate[required]" style="width: 115px"
                               name="basicInfo.zpglbmfzrList[0].contactorName" oninput="valueUpdate(this);"
                               value="${basicInfo.zpglbmfzrList[0].contactorName }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[custom[mobile]]' style="width: 115px"
                               name="basicInfo.zpglbmfzrList[0].mobilephone" oninput="valueUpdate(this);"
                               value="${basicInfo.zpglbmfzrList[0].mobilephone }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[custom[phone]]' style="width: 115px"
                               name="basicInfo.zpglbmfzrList[0].fixedPhone" oninput="valueUpdate(this);"
                               value="${basicInfo.zpglbmfzrList[0].fixedPhone }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[custom[email]]' style="width: 115px"
                               name="basicInfo.zpglbmfzrList[0].mailAddress" oninput="valueUpdate(this);"
                               value="${basicInfo.zpglbmfzrList[0].mailAddress }"/>
                    </td>
                    <td>
                        <select name="basicInfo.zpglbmfzrList[0].titleId" class="select" onchange="valueUpdate(this);">
                            <option></option>
                            <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                <option value="${title.dictId}"
                                        <c:if test='${basicInfo.zpglbmfzrList[0].titleId == title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <select name="basicInfo.zpglbmfzrList[0].postId" class="select" onchange="valueUpdate(this);">
                            <option></option>
                            <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                <option value="${post.dictId}"
                                        <c:if test='${basicInfo.zpglbmfzrList[0].postId == post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    &lt;%&ndash;<td><a onclick="del(this, 'zpglbmfzr', 'zpglbmfzrList');">删除</a></td>&ndash;%&gt;
                </tr>
                </tbody>
            </table>

            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="contactManTable">
                <colgroup>
                    <col width="20%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="20%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                </colgroup>
                <tr>
                    <th>联络员<span class="red">*</span></th>
                    <th>手机号码</th>
                    <th>固定电话</th>
                    <th>邮箱地址</th>
                    <th>职称</th>
                    <th>职务</th>
                    <th>操作&nbsp;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                       style="cursor: pointer;position: absolute;margin-top: 2px;" onclick="javascript:add('contactMan')" /></th>
                </tr>
                <tbody id="contactManTb">
                <c:forEach var="contactMan" items="${basicInfo.contactManList}" varStatus="status">
                    <tr>
                        <td style="position: relative">
                            <input type="text" class="input validate[required]" style="width: 115px"
                                   name="basicInfo.contactManList[${status.index}].contactorName" oninput="valueUpdate(this);"
                                   value="${contactMan.contactorName }"/>
                        </td>
                        <td>
                            <input type="text" class='input validate[custom[mobile]]' style="width: 115px"
                                   name="basicInfo.contactManList[${status.index}].mobilephone" oninput="valueUpdate(this);"
                                   value="${contactMan.mobilephone }"/>
                        </td>
                        <td>
                            <input type="text" class='input validate[custom[phone]]' style="width: 115px"
                                   name="basicInfo.contactManList[${status.index}].fixedPhone" oninput="valueUpdate(this);"
                                   value="${contactMan.fixedPhone }"/>
                        </td>
                        <td>
                            <input type="text" class='input validate[custom[email]]' style="width: 115px"
                                   name="basicInfo.contactManList[${status.index}].mailAddress" oninput="valueUpdate(this);"
                                   value="${contactMan.mailAddress }"/>
                        </td>
                        <td>
                            <select name="basicInfo.contactManList[${status.index}].titleId" class="select" onchange="valueUpdate(this);">
                                <option></option>
                                <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                    <option value="${title.dictId}"
                                            <c:if test='${contactMan.titleId == title.dictId}'>selected="selected"</c:if>>${title.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <select name="basicInfo.contactManList[${status.index}].postId" class="select" onchange="valueUpdate(this);">
                                <option></option>
                                <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                                    <option value="${post.dictId}"
                                            <c:if test='${contactMan.postId == post.dictId}'>selected="selected"</c:if>>${post.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><a onclick="del(this, 'contactMan', 'contactManList');">删除</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>--%>
        </div>

        <div class="div_table">
            <h4><span class="red">*</span>培训基地资质</h4>
            <table border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="30%"/>
                    <col width="70%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th><span class="red">*</span>类别：</th>
                    <td>
                        <input type="checkbox" class="validate[required]" name="basicInfo.lx" value="综合医院" <c:if test="${pdfn:contains(basicInfo.lx, '综合医院') }">checked="checked"</c:if>/>综合医院&nbsp;
                        <input type="checkbox" class="validate[required]" name="basicInfo.lx" value="专科医院" <c:if test="${pdfn:contains(basicInfo.lx, '专科医院') }">checked="checked"</c:if>/>专科医院&nbsp;
                        <input type="checkbox" class="validate[required]" name="basicInfo.lx" value="附属医院" <c:if test="${pdfn:contains(basicInfo.lx, '附属医院') }">checked="checked"</c:if>/>附属医院&nbsp;
                        <input type="checkbox" class="validate[required]" name="basicInfo.lx" value="教学医院" <c:if test="${pdfn:contains(basicInfo.lx, '教学医院') }">checked="checked"</c:if>/>教学医院
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>级别：</th>
                    <td>
                        <select style="width:207px;" class='select validate[required]' name="basicInfo.levelRank">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumOrgLevelRankList}" var="orgLevelRank">
                                <option value="${orgLevelRank.dictId}" <c:if test="${basicInfo.levelRank eq orgLevelRank.dictId }">selected="selected"</c:if>>${orgLevelRank.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
               <%-- <tr>
                    <th>等级：</th>
                    <td>
                        <input type="radio" name="basicInfo.dj" value="一级" <c:if test="${basicInfo.dj eq '一级' }">checked="checked"</c:if>/>一级&nbsp;
                        <input type="radio" name="basicInfo.dj" value="二级" <c:if test="${basicInfo.dj eq '二级' }">checked="checked"</c:if>/>二级&nbsp;
                        <input type="radio" name="basicInfo.dj" value="三级" <c:if test="${basicInfo.dj eq '三级' }">checked="checked"</c:if>/>三级
                    </td>
                </tr>
                <tr>
                    <th>等次：</th>
                    <td>
                        <input type="radio" name="basicInfo.dc" value="甲等" <c:if test="${basicInfo.dc eq '甲等' }">checked="checked"</c:if>/>甲等&nbsp;
                        <input type="radio" name="basicInfo.dc" value="乙等" <c:if test="${basicInfo.dc eq '乙等' }">checked="checked"</c:if>/>乙等&nbsp;
                        <input type="radio" name="basicInfo.dc" value="丙等" <c:if test="${basicInfo.dc eq '丙等' }">checked="checked"</c:if>/>丙等&nbsp;
                        <input type="radio" name="basicInfo.dc" value="未定等次" <c:if test="${basicInfo.dc eq '未定等次' }">checked="checked"</c:if>/>未定等次
                    </td>
                </tr>--%>
                <%--<tr>
                    <th>其它：</th>
                    <td>
                        <input type="text" name="basicInfo.zzOtherInfo" class='input' style="width: 197px;margin-left: 2px;" value="${basicInfo.zzOtherInfo}"
                               maxlength="10" />
                    </td>
                </tr>--%>
                <tr>
                    <th><span class="red">*</span>培训基地性质：</th>
                    <td>
                        <input type="radio" class="validate[required]" name="basicInfo.zcdjlx" value="公立医院" <c:if test="${basicInfo.zcdjlx eq '公立医院' }">checked="checked"</c:if>/>公立医院&nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp; 民营：
                        <input type="radio" class="validate[required]" name="basicInfo.zcdjlx" value="私营医院" <c:if test="${basicInfo.zcdjlx eq '私营医院' }">checked="checked"</c:if>/>私营医院&nbsp;
                        <input type="radio" class="validate[required]" name="basicInfo.zcdjlx" value="联营医院" <c:if test="${basicInfo.zcdjlx eq '联营医院' }">checked="checked"</c:if>/>联营医院&nbsp;
                        <input type="radio" class="validate[required]" name="basicInfo.zcdjlx" value="外资医院" <c:if test="${basicInfo.zcdjlx eq '外资医院' }">checked="checked"</c:if>/>外资医院
                    </td>
                </tr>
                <%--<tr>
                    <th>分类管理方式：</th>
                    <td>
                        <input type="radio" name="basicInfo.classificationManagement" value="营利" <c:if test="${basicInfo.classificationManagement eq '营利' }">checked="checked"</c:if>/>营利&nbsp;
                        <input type="radio" name="basicInfo.classificationManagement" value="非营利" <c:if test="${basicInfo.classificationManagement eq '非营利' }">checked="checked"</c:if>/>非营利
                    </td>
                </tr>--%>
                <tr>
                    <th><span class="red">*</span>住院医师基地获批文号：</th>
                    <td>
                        <select style="width:207px;" class='select validate[required]' name="resBase.resApprovalNumberId">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumResidentBaseApproveNumList}" var="tra">
                                <option value="${tra.dictId}" <c:if test="${resBase.resApprovalNumberId eq tra.dictId }">selected="selected"</c:if>>${tra.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>培训基地统一社会信用代码：</th>
                    <td><input type="text" name="sysOrg.creditCode"  class='input validate[required]' style="width: 197px;margin-left: 2px;"  value="${sysOrg.creditCode}"/></td>
                </tr>
                <tr>
                    <th><span class="red">*</span>执业许可证：</th>
                    <td>
							<span id="professionLicenceUrlSpan" style="display:${!empty basicInfo.professionLicenceUrl?'':'none'} ">
								&nbsp; <a href="${sysCfgMap['upload_base_url']}/${basicInfo.professionLicenceUrl}" target="_blank">查看图片</a>&nbsp;
							</span>
                        <c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id or resBase.baseStatusId eq baseStatusEnumNotPassed.id or empty resBase.baseStatusId  }">
                            <a id="professionLicenceUrl" href="javascript:uploadFile('professionLicenceUrl','执业许可证图片');" style="margin-left: 2px">${empty basicInfo.professionLicenceUrl?'':'重新'}上传</a>&nbsp;
                            <a id="professionLicenceUrlDel" href="javascript:delFile('professionLicenceUrl');" style="${empty basicInfo.professionLicenceUrl?'display:none':''}">删除</a>
                            <input type="hidden" id="professionLicenceUrlValue"  name="basicInfo.professionLicenceUrl" value="${basicInfo.professionLicenceUrl}" />
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>医院等级证书：</th>
                    <td>
							<span id="hospitalLevelLicenceUrlSpan" style="display:${!empty basicInfo.hospitalLevelLicenceUrl?'':'none'} ">
								&nbsp; <a href="${sysCfgMap['upload_base_url']}/${basicInfo.hospitalLevelLicenceUrl}" target="_blank">查看图片</a>&nbsp;
							</span>
                        <c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id or resBase.baseStatusId eq baseStatusEnumNotPassed.id or empty resBase.baseStatusId}">
                            <a id="hospitalLevelLicenceUrl" href="javascript:uploadFile('hospitalLevelLicenceUrl','医院等级证书图片');" style="margin-left: 2px">${empty basicInfo.hospitalLevelLicenceUrl?'':'重新'}上传</a>&nbsp;
                            <a id="hospitalLevelLicenceUrlDel" href="javascript:delFile('hospitalLevelLicenceUrl');" style="${empty basicInfo.hospitalLevelLicenceUrl?'display:none':''}">删除</a>
                            <input type="hidden" id="hospitalLevelLicenceUrlValue"  name="basicInfo.hospitalLevelLicenceUrl" value="${basicInfo.hospitalLevelLicenceUrl}" />
                        </c:if>
                    </td>
                </tr>
                <%--<c:if test="${not empty jointOrgFlag and jointOrgFlag eq 'Y'}">
                <tr>
                    <th>协同关系协议：</th>
                    <td>
							<span id="collaborativeRelationshipAgreementUrlSpan" style="display:${!empty basicInfo.collaborativeRelationshipAgreementUrl?'':'none'} ">
								&nbsp; <a href="${sysCfgMap['upload_base_url']}/${basicInfo.collaborativeRelationshipAgreementUrl}" target="_blank">查看图片</a>&nbsp;
							</span>
                        <c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id or resBase.baseStatusId eq baseStatusEnumNotPassed.id or empty resBase.baseStatusId}">
                            <a id="collaborativeRelationshipAgreementUrl" href="javascript:uploadFile('collaborativeRelationshipAgreementUrl','协同关系协议图片');" style="margin-left: 2px">${empty basicInfo.collaborativeRelationshipAgreementUrl?'':'重新'}上传</a>&nbsp;
                            <a id="collaborativeRelationshipAgreementUrlDel" href="javascript:delFile('collaborativeRelationshipAgreementUrl');" style="${empty basicInfo.collaborativeRelationshipAgreementUrl?'display:none':''}">删除</a>&nbsp
                            <input type="hidden" id="collaborativeRelationshipAgreementUrlValue"  name="basicInfo.collaborativeRelationshipAgreementUrl" value="${basicInfo.collaborativeRelationshipAgreementUrl}" />
                        </c:if>
                    </td>
                </tr>
                </c:if>--%>
                </tbody>
            </table>

            <table class="base_info">
                <thead>
                <colgroup>
                    <col width="30%"/>
                    <col width="30%"/>
                    <col width="30%"/>
                    <col width="10%"/>
                </colgroup>
                <tr>
                    <th style="text-align: center"><span class="red">*</span>协同单位名称</th>
                    <th style="text-align: center"><span class="red">*</span>专业基地</th>
                    <th style="text-align: center"><span class="red">*</span>协同关系协议</th>
                    <th style="text-align: center">
                        操作
                        <span style="float: right;padding-right: 20px">
							<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addJointContract();" />
						</span>
                    </th>
                </tr>
                </thead>
                <tbody id="jointContractBody">
                <c:forEach var="jointContract" items="${jointContractList}" varStatus="status">
                    <tr>
                        <td style="text-align: center">
                            <select name="jointOrgFlows" class="select" οnclick="return false;" οnmοusedοwn="return false;">
                                <option value="${jointContract.orgFlow}">${jointContract.orgName}</option>
                            </select>
                        </td>
                        <td style="text-align: center">
                            <select name="speIds" class="select">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${jointContract.speId eq dict.dictId}">selected</c:if> >${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: center">
                            （请选择pdf格式的文件）<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addJointFile(this);" />
                            <c:if test="${not empty jointContract.fileList}">
                                <c:forEach items="${jointContract.fileList}" var="contractFile">
                                    <div style="text-align: center;padding: 2px;margin: 2px" class="jointContractFile">
                                        <a href="${sysCfgMap['upload_base_url']}/${contractFile.filePath}"
                                           target="_blank" style="font: 14px 'Microsoft Yahei';font-weight: 400;border-radius: 2px;width: 60%;background-color: white;color: #409eff">${contractFile.fileName}</a>
                                        <a onclick="delJointFile(this);" style="background-color: white;color: #409eff">删除</a>
                                        <input type="hidden" name="jointContractFileFlows" value="${contractFile.fileFlow}" />
                                    </div>
                                </c:forEach>
                            </c:if>
                            <input type="hidden" name="fileUploadNum" value="0" />
                            <input type="hidden" name="fileRemainNum" value="0" />
                        </td>
                        <td>
                            <a onclick="moveJointTr(this);">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="div_table">
        <h4>基本条件（门诊科室设置情况，截止上年度）</h4>
        <table border="0" cellspacing="0" cellpadding="0" class="base_info">
            <colgroup>
                <col width="22%"/>
                <col width="28%"/>
                <col width="22%"/>
                <col width="28%"/>
            </colgroup>
            <tbody>
            <tr>
                <th><span class="red">*</span>编制总床位数（张）：</th>
                <td><input type="text" class="input1 validate[required,custom[integer],min[0],max[99999999]]" style="width:100px;"
                           name="educationInfo.bzBedCount" value="${educationInfo.bzBedCount}"/>
                </td>
                <th><span class="red">*</span>实有总床位数（张）：</th>
                <td><input type="text" class="input1 validate[required,custom[integer],min[0],max[99999999]]" style="width:100px;"
                           name="educationInfo.sjBedCount" value="${educationInfo.sjBedCount }"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>年收治住院病人数（人次）：</th>
                <td><input type="text" class="input1 validate[required,custom[integer],min[0],max[99999999]]" style="width:100px;"
                           name="educationInfo.yearlyNumberOfClinicalPatients"
                           value="${educationInfo.yearlyNumberOfClinicalPatients}"/>
                </td>
                <th><span class="red">*</span>病床使用率（%）：</th>
                <td><input type="text" class="input1 validate[required,custom[number],min[0],max[100],maxSize[9]]" style="width:100px;"
                           name="educationInfo.bedOccupancy" value="${educationInfo.bedOccupancy}"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>本年门诊量（万人次）：</th>
                <td><input type="text" class="input1 validate[required,custom[number],min[0],max[9999],maxSize[9]]" style="width:100px;"
                           name="educationInfo.yearMzCount" value="${educationInfo.yearMzCount}"/>
                </td>
                <th><span class="red">*</span>本年急诊量（万人次）：</th>
                <td><input type="text" class="input1 validate[required,custom[number],min[0],max[9999],maxSize[9]]" style="width:100px;"
                           name="educationInfo.yearJzCount" value="${educationInfo.yearJzCount}"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>本年手术量（台次）：</th>
                <td><input type="text" class="input1 validate[required,custom[integer],min[0],max[99999999]]" style="width:100px;"
                           name="educationInfo.yearSjCount" value="${ educationInfo.yearSjCount}"/>
                </td>
                <th><span class="red">*</span>本年出院病人数（万人次）：</th>
                <td><input type="text" class="input1 validate[required,custom[number],min[0],max[9999],maxSize[9]]" style="width:100px;"
                           name="educationInfo.yearCybrCount" value="${ educationInfo.yearCybrCount}"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>本年专业基地数（个）：</th>
                <td><input type="text" class="input1 validate[required,custom[integer],min[0],max[99999999]]" style="width:100px;"
                           name="educationInfo.numberOfExistingProfessionalBases"
                           value="${educationInfo.numberOfExistingProfessionalBases}"/>
                </td>
                <th><span class="red">*</span>3年培训容量总和（人）：</th>
                <td><input type="text" class="input1 validate[required,custom[integer],min[0],max[99999999]]" style="width:100px;"
                           name="educationInfo.total3YearTrainingCapacity"
                           value="${educationInfo.total3YearTrainingCapacity}"/>
                </td>
            </tr>


            <tr>
                <th><span class="red">*</span>本年入出院病人诊断符合率（%）：</th>
                <td><input type="text" class="input1 validate[required,custom[number],min[0]],max[100],maxSize[9]]" style="width:100px;"
                           name="educationInfo.rcybrzdfhl" value="${educationInfo.rcybrzdfhl}"/>
                </td>
                <th><span class="red">*</span>本年住院病人治愈好转率（%）：</th>
                <td><input type="text" class="input1 validate[required,custom[number],min[0],max[100],maxSize[9]]" style="width:100px;"
                           name="educationInfo.zybrzyhzl" value="${educationInfo.zybrzyhzl}"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>本年住院总死亡率（%）：</th>
                <td><input type="text" class="input1 validate[required,custom[number],min[0],max[100],maxSize[9]]" style="width:100px;"
                           name="educationInfo.zyzswl" value="${educationInfo.zyzswl}"/>
                </td>
                <th><span class="red">*</span>本年感染总发生率（%）：</th>
                <td><input type="text" class="input1 validate[required,custom[number],min[0],max[100],maxSize[9]]" style="width:100px;"
                           name="educationInfo.grzfsl" value="${educationInfo.grzfsl}"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>本年手术患者并发症发生率（%）：</th>
                <td><input type="text" class="input1 validate[required,custom[number],min[0],max[100],maxSize[9]]" style="width:100px;"
                           name="educationInfo.sshzbfzfsl" value="${educationInfo.sshzbfzfsl}"/>
                </td>
                <th><span class="red">*</span>按本年总病例病种数（个）：</th>
                <td><input type="text" class="input1 validate[required,custom[integer],min[0],max[99999999]]" style="width:100px;"
                           name="baseExtInfoEducationInfo.annualDiseases" value="${baseExtInfoEducationInfo.annualDiseases}"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>本年收治总疾病（种）：</th>
                <td><input type="text" class="input1 validate[required,custom[integer],min[0],max[99999999]]" style="width:100px;"
                           name="baseExtInfoEducationInfo.annualDiseaseCategory" value="${baseExtInfoEducationInfo.annualDiseaseCategory}"/>
                </td>
                <th><span class="red">*</span>按本年总病例病种数（个）：</th>
                <td><input type="text" class="input1 validate[required,custom[integer],min[0],max[99999999]]" style="width:100px;"
                           name="baseExtInfoEducationInfo.annualDiseaseNumber" value="${baseExtInfoEducationInfo.annualDiseaseNumber}"/>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>演示教室：</th>
                <td>
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.hospitalDemoClass" value="Y" <c:if test="${baseExtInfoEducationInfo.hospitalDemoClass eq 'Y' }">checked="checked"</c:if>/>有&nbsp;
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.hospitalDemoClass" value="N" <c:if test="${baseExtInfoEducationInfo.hospitalDemoClass eq 'N' }">checked="checked"</c:if>/>无&nbsp;
                </td>
                <th><span class="red">*</span>图书馆：</th>
                <td>
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.hospitalLibrary" value="Y" <c:if test="${baseExtInfoEducationInfo.hospitalLibrary eq 'Y' }">checked="checked"</c:if>/>有&nbsp;
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.hospitalLibrary" value="N" <c:if test="${baseExtInfoEducationInfo.hospitalLibrary eq 'N' }">checked="checked"</c:if>/>无&nbsp;
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>文献检索系统：</th>
                <td>
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.literatureRetrievalSystem" value="Y" <c:if test="${baseExtInfoEducationInfo.literatureRetrievalSystem eq 'Y' }">checked="checked"</c:if>/>有&nbsp;
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.literatureRetrievalSystem" value="N" <c:if test="${baseExtInfoEducationInfo.literatureRetrievalSystem eq 'N' }">checked="checked"</c:if>/>无&nbsp;
                </td>
                <th><span class="red">*</span>网络信息管理平台：</th>
                <td>
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.netInfoManagePlatform" value="Y" <c:if test="${baseExtInfoEducationInfo.netInfoManagePlatform eq 'Y' }">checked="checked"</c:if>/>有&nbsp;
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.netInfoManagePlatform" value="N" <c:if test="${baseExtInfoEducationInfo.netInfoManagePlatform eq 'N' }">checked="checked"</c:if>/>无&nbsp;
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>临床技能培训中心建筑面积（平米）：</th>
                <td><input type="text" class="input1 validate[required,custom[number],min[0],max[100],maxSize[9]]" style="width:100px;"
                           name="baseExtInfoEducationInfo.clinicalSkillsTrainingCenterM2" value="${baseExtInfoEducationInfo.clinicalSkillsTrainingCenterM2}"/>
                </td>
                <th><span class="red">*</span>培训管理制度：</th>
                <td>
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.trainManageSystem" onclick="changeUrlSpanShow(this)" value="Y" <c:if test="${baseExtInfoEducationInfo.trainManageSystem eq 'Y' }">checked="checked"</c:if>/>有&nbsp;
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.trainManageSystem" onclick="changeUrlSpanShow(this)" value="N" <c:if test="${baseExtInfoEducationInfo.trainManageSystem eq 'N' }">checked="checked"</c:if>/>无&nbsp;
                    <span id="trainManageSystemUrlShowSpan" style="display: ${baseExtInfoEducationInfo.trainManageSystem eq 'Y' ? '' : 'none'}">
                                <span id="trainManageSystemUrlSpan" style="display:${!empty baseExtInfoEducationInfo.trainManageSystemUrl?'':'none'} ">
                                    &nbsp; <a href="${sysCfgMap['upload_base_url']}/${baseExtInfoEducationInfo.trainManageSystemUrl}" target="_blank">查看文件</a>&nbsp;
                                </span>
                                <a id="trainManageSystemUrl" href="javascript:uploadPdfFile('trainManageSystemUrl','培训管理制度','', '.pdf');" style="margin-left: 2px">${empty baseExtInfoEducationInfo.trainManageSystemUrl?'':'重新'}上传</a>&nbsp;
                                <a id="trainManageSystemUrlDel" href="javascript:delFile('trainManageSystemUrl');" style="${empty baseExtInfoEducationInfo.trainManageSystemUrl?'display:none':''}">删除</a>
                                <input type="hidden" id="trainManageSystemUrlValue"  name="baseExtInfoEducationInfo.trainManageSystemUrl" value="${baseExtInfoEducationInfo.trainManageSystemUrl}" />
                            </span>
                </td>
            </tr>
            <tr>
                <th><span class="red">*</span>临床基地培训协议：</th>
                <td>
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.clinicalBaseTrainContract" onclick="changeUrlSpanShow(this)" value="Y" <c:if test="${baseExtInfoEducationInfo.clinicalBaseTrainContract eq 'Y' }">checked="checked"</c:if>/>有&nbsp;
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.clinicalBaseTrainContract" onclick="changeUrlSpanShow(this)" value="N" <c:if test="${baseExtInfoEducationInfo.clinicalBaseTrainContract eq 'N' }">checked="checked"</c:if>/>无&nbsp;
                    <span id="clinicalBaseTrainContractUrlShowSpan" style="display: ${baseExtInfoEducationInfo.clinicalBaseTrainContract eq 'Y' ? '' : 'none'}">
                                <span id="clinicalBaseTrainContractUrlSpan" style="display:${!empty baseExtInfoEducationInfo.clinicalBaseTrainContractUrl?'':'none'} ">
                                    &nbsp; <a href="${sysCfgMap['upload_base_url']}/${baseExtInfoEducationInfo.clinicalBaseTrainContractUrl}" target="_blank">查看文件</a>&nbsp;
                                </span>
                                <a id="clinicalBaseTrainContractUrl" href="javascript:uploadPdfFile('clinicalBaseTrainContractUrl','培训管理制度','', '.pdf');" style="margin-left: 2px">${empty baseExtInfoEducationInfo.clinicalBaseTrainContractUrl?'':'重新'}上传</a>&nbsp;
                                <a id="clinicalBaseTrainContractUrlDel" href="javascript:delFile('clinicalBaseTrainContractUrl');" style="${empty baseExtInfoEducationInfo.clinicalBaseTrainContractUrl?'display:none':''}">删除</a>
                                <input type="hidden" id="clinicalBaseTrainContractUrlValue"  name="baseExtInfoEducationInfo.clinicalBaseTrainContractUrl" value="${baseExtInfoEducationInfo.clinicalBaseTrainContractUrl}" />
                            </span>
                </td>
                <th><span class="red">*</span>教学门诊：</th>
                <td>
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.teachingClinic" value="Y" <c:if test="${baseExtInfoEducationInfo.teachingClinic eq 'Y' }">checked="checked"</c:if>/>有&nbsp;
                    <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.teachingClinic" value="N" <c:if test="${baseExtInfoEducationInfo.teachingClinic eq 'N' }">checked="checked"</c:if>/>无&nbsp;
                </td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="btn_info">
        <input class="btn_green" onclick="saveBaseMainInfo()" type="button" style="width: 88px" value="保&#12288;存"/>
    </div>
</form>
<div style="display: none">
    <div id="jointContractFileTemplate">
        <div style="text-align: center" class="jointContractFile">
            <input type='file' name='files' class='' onchange="uploadFileCheck(this);"
                   accept=".pdf" style="width: 60%;padding: 2px;margin: 2px"/>
            <a onclick="delJointFile(this);" style="background-color: white;color: #409eff">删除</a>
            <input type="hidden" name="jointContractFileFlows" value="${contractFile.fileFlow}" />
        </div>
    </div>

    <table id="jointContractTemplate">
        <tr>
            <td style="text-align: center">
                <select name="jointOrgFlows" class="select">
                    <option value="">请选择</option>
                    <c:forEach items="${unjointOrgList}" var="jointOrg">
                        <option value="${jointOrg.orgFlow}">${jointOrg.orgName}</option>
                    </c:forEach>
                    <c:forEach items="${jointContractList}" var="jointOrg">
                        <option value="${jointOrg.orgFlow}">${jointOrg.orgName}</option>
                    </c:forEach>
                </select>
            </td>
            <td style="text-align: center">
                <select name="speIds" class="select">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                        <option value="${dict.dictId}">${dict.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td style="text-align: center">
                （请选择pdf格式的文件）<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addJointFile(this);" />
                <input type="hidden" name="fileUploadNum" value="0" />
                <input type="hidden" name="fileRemainNum" value="0" />
            </td>
            <td>
                <a onclick="moveJointTr(this);">删除</a>
            </td>
        </tr>
    </table>

    <table id="ywfgfzrTemplate">
        <tr>
            <td style="position: relative">
                <input type="text" class="input" style="width: 115px"
                       name="basicInfo.ywfgfzrList[{index}].contactorName"  oninput="valueUpdate(this);"/>
            </td>
            <td>
                <input type="text" class='input validate[custom[mobile]]' style="width: 115px"
                       name="basicInfo.ywfgfzrList[{index}].mobilephone"  oninput="valueUpdate(this);"/>
            </td>
            <td>
                <input type="text" class='input validate[custom[email]]' style="width: 115px"
                       name="basicInfo.ywfgfzrList[{index}].mailAddress"  oninput="valueUpdate(this);"/>
            </td>
            <td>
                <select name="basicInfo.ywfgfzrList[{index}].titleId" class="select" onchange="valueUpdate(this);">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                        <option value="${title.dictId}">${title.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="basicInfo.ywfgfzrList[{index}].postId" class="select" onchange="valueUpdate(this);">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                        <option value="${post.dictId}">${post.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td><a onclick="del(this, 'ywfgfzr', 'ywfgfzrList');">删除</a></td>
        </tr>
    </table>
</div>
<div style="display: none">
    <table id="zpywkslxrTemplate">
        <tr>
            <td style="position: relative">
                <input type="text" class="input" style="width: 115px"
                       name="basicInfo.zpywkslxrList[{index}].contactorName" oninput="valueUpdate(this);" />
            </td>
            <td>
                <input type="text" class='input validate[custom[mobile]]' style="width: 115px"
                       name="basicInfo.zpywkslxrList[{index}].mobilephone" oninput="valueUpdate(this);" />
            </td>
            <td>
                <input type="text" class='input validate[custom[email]]' style="width: 115px"
                       name="basicInfo.zpywkslxrList[{index}].mailAddress" oninput="valueUpdate(this);" />
            </td>
            <td>
                <select name="basicInfo.zpywkslxrList[{index}].titleId" class="select" onchange="valueUpdate(this);">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                        <option value="${title.dictId}">${title.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="basicInfo.zpywkslxrList[{index}].postId" class="select" onchange="valueUpdate(this);">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                        <option value="${post.dictId}">${post.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td><a onclick="del(this, 'zpywkslxr', 'zpywkslxrList');">删除</a></td>
        </tr>
    </table>
</div>
<div style="display: none">
    <table id="zpglbmfzrTemplate">
        <tr>
            <td style="position: relative">
                <input type="text" class="input validate[required]" style="width: 115px"
                       name="basicInfo.zpglbmfzrList[{index}].contactorName"  oninput="valueUpdate(this);"/>
            </td>
            <td>
                <input type="text" class='input validate[custom[mobile]]' style="width: 115px"
                       name="basicInfo.zpglbmfzrList[{index}].mobilephone" oninput="valueUpdate(this);"/>
            </td>
            <td>
                <input type="text" class='input validate[custom[email]]' style="width: 115px"
                       name="basicInfo.zpglbmfzrList[{index}].mailAddress" oninput="valueUpdate(this);" />
            </td>
            <td>
                <select name="basicInfo.zpglbmfzrList[{index}].titleId" class="select" onchange="valueUpdate(this);">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                        <option value="${title.dictId}">${title.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="basicInfo.zpglbmfzrList[{index}].postId" class="select" onchange="valueUpdate(this);">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                        <option value="${post.dictId}">${post.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td><a onclick="del(this, 'zpglbmfzr', 'zpglbmfzrList');">删除</a></td>
        </tr>
    </table>
</div>
<div style="display: none">
    <table id="personTemplate">
        <tr>
            <td style="position: relative">
                <input type="text" class="input validate[required]" style="width:80px;" name="organizationManage.organizationPersonList[{index}].name"/>
            </td>
            <td>
                <select name="organizationManage.organizationPersonList[{index}].sex" class="select"
                        style="width:80px;">
                    <option value="">请选择</option>
                    <c:forEach var="dict" items="${userSexEnumList }">
                        <c:if test="${dict.id != userSexEnumUnknown.id}">
                            <option value="${dict.name}">${dict.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" class="input1 validate[custom[integer],max[150]]" style="width:50px;"
                       name="organizationManage.organizationPersonList[{index}].age"/></td>
            <td><input type="text" class="input1" style="width:120px;"
                       name="organizationManage.organizationPersonList[{index}].profession"/></td>
            <td>
                <select class="select" name="organizationManage.organizationPersonList[{index}].tallStudy" style="width: 80px;">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserEducationList }" var="dict">
                        <option value="${dict.dictName}">${dict.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" class="input1" style="width:100px;"
                       name="organizationManage.organizationPersonList[{index}].dept"/></td>
            <td>
                <select class="select" name="organizationManage.organizationPersonList[{index}].job" style="width: 80px;">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserPostList }" var="dict">
                        <option value="${dict.dictName}">${dict.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" class="input1" style="width:100px;"
                       name="organizationManage.organizationPersonList[{index}].telephone"
                       onchange="checkTeltphone(this);"/></td>
            <td><input type="text" class="input1" style="width:80px;"
                       name="organizationManage.organizationPersonList[{index}].partOrAllJob"/></td>
            <td><a onclick="moveTr(this);">删除</a></td>
        </tr>
    </table>
    <table id="ruleTemplate">
        <tr>
            <td></td>
            <td style="position: relative">
                <input type="text" class="input validate[required]" style="width:300px;"
                       name="organizationManage.organizationTrainingSystemList[{index}].rulesAndRegulations"/>
                <input hidden type="text" style="width:80px;"
                       name="organizationManage.organizationTrainingSystemList[{index}].appendix" value=""/>
            </td>
            <td>
                <input type='file' name="" onchange="uploadFile(this);"
                       accept=".rar,.jpg,.png,.pdf,.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
            </td>
            <td><a onclick="del(this, 'rule');">删除</a></td>
        </tr>
    </table>
    <table id="workTemplate">
        <tr>
            <td></td>
            <td style="position: relative">
                <input type="text" class="input validate[required]" style="width:300px;"
                       name="organizationManage.organizationWorkConditionList[{index}].fileName"/>
                <input hidden type="text" style="width:80px;"
                       name="organizationManage.organizationWorkConditionList[{index}].appendix" value=""/>
            </td>
            <td>
                <input type='file' name="" onchange="uploadFile(this);"
                       accept=".rar,.jpg,.png,.pdf,.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
            </td>
            <td><a onclick="del(this, 'work');">删除</a></td>
        </tr>
    </table>
</div>
<div style="display: none">
    <table id="teachingTemplate">
        <tr>
            <td></td>
            <td colspan="2" style="position: relative">
                <input type="text" class="input validate[required]" style="width:90%;"
                       name="educationInfo.teachingEquipmentList[{index}].equipmentName"/>
            </td>
            <td style="position: relative">
                <input type="text" class="input validate[required]" style="width:90%;"
                       name="educationInfo.teachingEquipmentList[{index}].equipmentNumber"/>
            </td>
            <td><a onclick="del(this, 'teaching');">删除</a></td>
        </tr>
    </table>
    <table id="centerTemplate">
        <tr>
            <td></td>
            <td style="position: relative">
                <input type="text" class="input validate[required]" style="width:90%;"
                       name="educationInfo.centerEquipmentList[{index}].equipmentName"/>
            </td>
            <td style="position: relative">
                <input type="text" class="input" style="width:90%;"
                       name="educationInfo.centerEquipmentList[{index}].equipmentModel"/>
            </td>
            <td style="position: relative">
                <input type="text" class="input validate[required]" style="width:90%;"
                       name="educationInfo.centerEquipmentList[{index}].equipmentNumber"/>
            </td>
            <td><a onclick="del(this, 'center');">删除</a></td>
        </tr>
    </table>
</div>
<div style="display: none">
    <table id="contactManTemplate">
        <tr>
            <td style="position: relative">
                <input type="text" class="input validate[required]" style="width: 115px"
                       name="basicInfo.contactManList[{index}].contactorName" oninput="valueUpdate(this);" />
            </td>
            <td>
                <input type="text" class='input validate[required,custom[mobile]]' style="width: 115px"
                       name="basicInfo.contactManList[{index}].mobilephone" oninput="valueUpdate(this);"
                       value="${contactMan.mobilephone }"/>
            </td>
            <td>
                <input type="text" class='input validate[required,custom[phone]]' style="width: 115px"
                       name="basicInfo.contactManList[{index}].fixedPhone" oninput="valueUpdate(this);"
                       value="${contactMan.fixedPhone }"/>
            </td>
            <td>
                <input type="text" class='input validate[required,custom[email]]' style="width: 115px"
                       name="basicInfo.contactManList[{index}].mailAddress" oninput="valueUpdate(this);"
                       value="${contactMan.mailAddress }"/>
            </td>
            <td>
                <select name="basicInfo.contactManList[{index}].titleId" class="select" onchange="valueUpdate(this);">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                        <option value="${title.dictId}">${title.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <select name="basicInfo.contactManList[{index}].postId" class="select validate[required]" onchange="valueUpdate(this);">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                        <option value="${post.dictId}">${post.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td><img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
                     style="cursor: pointer;" onclick="javascript:del(this, 'contactMan', 'contactManList');" /></td>
        </tr>
    </table>
</div>