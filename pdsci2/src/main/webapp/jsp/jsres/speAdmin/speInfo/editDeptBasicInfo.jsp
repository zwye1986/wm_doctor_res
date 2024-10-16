<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
    .div_table h4 {
        color: #000000;
        font: 15px 'Microsoft Yahei';
        font-weight: 500;
    }
    #BaseInfoForm table th {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 500;
    }
    #BaseInfoForm table td,#BaseInfoForm table td input,#BaseInfoForm table td label {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }
</style>
<script type="text/javascript"
        src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        toRead('drxw','drxwEdit');
        toRead('masterxw','masterEdit');
    });

    function toZero(obj) {
        $("#"+obj).val("0");
    }

    function toRead(obj,cls) {
        if ($("#"+obj).prop('checked')){
            $("."+cls).attr("readonly",false);
        }else {
            $("."+cls).attr("readonly","readonly");
            $("."+cls).val("");
        }
    }

    function saveBaseInfo() {
        if (!$("#BaseInfoForm").validationEngine("validate") || !checkGroupMember()) {
            $("#indexBody").scrollTop("0px");
            return false;
        }

        if ($("#masterxw").prop('checked')){
            $("#masterxw").val("Y");
        }else {
            $("#masterxw").val("N");
        }
        if ($("#drxw").prop('checked')){
            $("#drxw").val("Y");
        }else {
            $("#drxw").val("N");
        }

        jboxPost("<s:url value='/jsres/speAdmin/saveBase'/>", $("#BaseInfoForm").serialize(), function (resp) {
            if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
                setTimeout(function(){
                    $("#submitBtn").show();
                    loadInfo('${GlobalConstant.DEPT_BASIC_INFO}','${speFlow}');
                },1000);
            }
        }, null, true);
    }

    function checkGroupMember() {
        if(!$("#groupMemberTb tr").length) {
            jboxTip("请添加教学小组成员！");
            return false;
        }

        return true;
    }

    function addGroupMember(tb) {
        var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
        var index = $("#" + tb + "Tb tr").length;
        cloneTr.html(cloneTr.html().htmlFormartById({"index": index}));
        $("#" + tb + "Tb").append(cloneTr);
    }

    function delGroupMember(obj, type, pre) {
        $(obj).parent().parent().remove();
        if(type) {
            var index = $("#" + type + "Tb tr").length;
            for(var i = 0; i < index; i++) {
                var cur = $($("#" + type + "Tb tr")[i]);
                cur.html(cur.html().htmlFormatByIndex(i + "", pre));
            }
        }
    }

    function valueUpdate(cur) {
        $(cur).attr("value", $(cur).val());
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
</script>
<input type="hidden" id="resBase" name="resBase" value="${baseSpeDept}"/>
<form id='BaseInfoForm' style="position:relative;">
    <input type="hidden" name="resBaseSpeDept.orgFlow"
           value="${empty baseSpeDept?sessionScope.currUser.orgFlow:baseSpeDept.orgFlow}"/>
    <input type="hidden" name="resBaseSpeDept.speFlow" value="${speFlow}"/>
    <input type="hidden" name="resBaseSpeDept.sessionNumber" value="${sessionNumber}"/>
    <input type="hidden" name="flag" value="${GlobalConstant.DEPT_BASIC_INFO}"/>
    <div class="main_bd">
        <div class="div_table">
            <h4><span class="red">*</span>基本信息</h4>
            <table border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="17%"/>
                    <col width="13%"/>
                    <col width="17%"/>
                    <col width="15%"/>
                    <col width="15%"/>
                    <col width="13%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th><span style="color: red">*</span>&nbsp;专业基地名称：</th>
                    <td>${speName}</td>
                    <th><span style="color: red">*</span>&nbsp;专业基地编码：</th>
                    <td colspan="3">${speFlow}</td>
                </tr>
                <%--<tr>
                    <th><span style="color: red">*</span>&nbsp;专业基地负责人姓名：</th>
                    <td><input type="text" class="input validate[required]" name="deptBasicInfoForm.speRespName" style="width:90%;"
                               value="${deptBasicInfoForm.speRespName}"/></td>
                    <th><span style="color: red">*</span>&nbsp;负责人联系电话：</th>
                    <td><input type="text" class=" input validate[required,custom[phone]]" style="width:90%;" name="deptBasicInfoForm.speRespPhone"
                               value="${deptBasicInfoForm.speRespPhone}"/></td>
                    <th><span style="color: red">*</span>&nbsp;负责人邮箱：</th>
                    <td><input type="text" class=" input validate[required,custom[email]]"style="width:90%;" name="deptBasicInfoForm.speRespEmail"
                               value="${deptBasicInfoForm.speRespEmail}"/></td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>&nbsp;教学主任姓名：</th>
                    <td><input type="text" class="input validate[required]" name="deptBasicInfoForm.speDirName" style="width:90%;"
                               value="${deptBasicInfoForm.speDirName}"/></td>
                    <th><span style="color: red">*</span>&nbsp;教学主任联系电话：</th>
                    <td><input type="text" class=" input validate[required,custom[phone]]" style="width:90%;" name="deptBasicInfoForm.speDirPhone"
                               value="${deptBasicInfoForm.speDirPhone}"/></td>
                    <th><span style="color: red">*</span>&nbsp;教学主任邮箱：</th>
                    <td><input type="text" class=" input validate[required,custom[email]]" style="width:90%;" name="deptBasicInfoForm.speDirEmail"
                               value="${deptBasicInfoForm.speDirEmail}"/></td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>&nbsp;教学秘书姓名：</th>
                    <td><input type="text" class="input validate[required]" name="deptBasicInfoForm.speSceName" style="width:90%;"
                               value="${deptBasicInfoForm.speSceName}"/></td>
                    <th><span style="color: red">*</span>&nbsp;教学秘书联系电话：</th>
                    <td><input type="text" class=" input validate[required,custom[phone]]" style="width:90%;"name="deptBasicInfoForm.speScePhone"
                               value="${deptBasicInfoForm.speScePhone}"/></td>
                    <th><span style="color: red">*</span>&nbsp;教学秘书邮箱：</th>
                    <td><input type="text" class=" input validate[required,custom[email]]"style="width:90%;" name="deptBasicInfoForm.speSceEmail"
                               value="${deptBasicInfoForm.speSceEmail}"/></td>
                </tr>--%>
                </tbody>
            </table>

            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="speRespTable" style="table-layout: fixed">
                <tr>
                    <th style="width: 30%">专业基地负责人<span class="red">*</span></th>
                    <th style="width: 30%">手机号码<span class="red">*</span></th>
                    <th style="width: 30%">邮箱地址<span class="red">*</span></th>
                    <th style="width: 10%"></th>
                    <%--<th>操作&nbsp;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                     style="cursor: pointer;position: absolute;margin-top: 2px;" onclick="javascript:add('zpglbmfzr')" /></th>--%>
                </tr>
                <tbody id="speRespTb">
                <tr>
                    <td>
                        <input type="text" style="width: 200px" name="deptBasicInfoForm.speRespName" class='input validate[required]' value="${deptBasicInfoForm.speRespName}"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[phone]]' style="width: 200px"
                               name="deptBasicInfoForm.speRespPhone" oninput="valueUpdate(this);"
                               value="${deptBasicInfoForm.speRespPhone }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[email]]' style="width: 200px"
                               name="deptBasicInfoForm.speRespEmail" oninput="valueUpdate(this);"
                               value="${deptBasicInfoForm.speRespEmail }"/>
                    </td>
                </tr>
                </tbody>
            </table>

            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="speDirTable" style="table-layout: fixed">
                <tr>
                    <th style="width: 30%">教学主任<span class="red">*</span></th>
                    <th style="width: 30%">手机号码<span class="red">*</span></th>
                    <th style="width: 30%">邮箱地址<span class="red">*</span></th>
                    <th style="width: 10%"></th>
                    <%--<th>操作&nbsp;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                     style="cursor: pointer;position: absolute;margin-top: 2px;" onclick="javascript:add('zpglbmfzr')" /></th>--%>
                </tr>
                <tbody id="speDirTb">
                <tr>
                    <td>
                        <input type="text" style="width: 200px" name="deptBasicInfoForm.speDirName" class='input validate[required]' value="${deptBasicInfoForm.speDirName}"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[phone]]' style="width: 200px"
                               name="deptBasicInfoForm.speDirPhone" oninput="valueUpdate(this);"
                               value="${deptBasicInfoForm.speDirPhone }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[email]]' style="width: 200px"
                               name="deptBasicInfoForm.speDirEmail" oninput="valueUpdate(this);"
                               value="${deptBasicInfoForm.speDirEmail }"/>
                    </td>
                </tr>
                </tbody>
            </table>

            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="speSceTable" style="table-layout: fixed">
                <tr>
                    <th style="width: 30%">教学秘书<span class="red">*</span></th>
                    <th style="width: 30%">手机号码<span class="red">*</span></th>
                    <th style="width: 30%">邮箱地址<span class="red">*</span></th>
                    <th style="width: 10%"></th>
                    <%--<th>操作&nbsp;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                     style="cursor: pointer;position: absolute;margin-top: 2px;" onclick="javascript:add('zpglbmfzr')" /></th>--%>
                </tr>
                <tbody id="speSceTb">
                <tr>
                    <td>
                        <input type="text" style="width: 200px" name="deptBasicInfoForm.speSceName" class='input validate[required]' value="${deptBasicInfoForm.speSceName}"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[phone]]' style="width: 200px"
                               name="deptBasicInfoForm.speScePhone" oninput="valueUpdate(this);"
                               value="${deptBasicInfoForm.speScePhone }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[email]]' style="width: 200px"
                               name="deptBasicInfoForm.speSceEmail" oninput="valueUpdate(this);"
                               value="${deptBasicInfoForm.speSceEmail }"/>
                    </td>
                </tr>
                </tbody>
            </table>

            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="groupLeaderTable" style="table-layout: fixed">
                <tr>
                    <th style="width: 30%">教学小组组长<span class="red">*</span></th>
                    <th style="width: 30%">手机号码<span class="red">*</span></th>
                    <th style="width: 30%">邮箱地址<span class="red">*</span></th>
                    <th style="width: 10%"></th>
                    <%--<th>操作&nbsp;<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                     style="cursor: pointer;position: absolute;margin-top: 2px;" onclick="javascript:add('zpglbmfzr')" /></th>--%>
                </tr>
                <tbody id="groupLeaderTb">
                <tr>
                    <td>
                        <input type="text" style="width: 200px" name="deptBasicInfoForm.teachingGroupLeaderName" class='input validate[required]' value="${deptBasicInfoForm.teachingGroupLeaderName}"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[phone]]' style="width: 200px"
                               name="deptBasicInfoForm.teachingGroupLeaderPhone" oninput="valueUpdate(this);"
                               value="${deptBasicInfoForm.teachingGroupLeaderPhone }"/>
                    </td>
                    <td>
                        <input type="text" class='input validate[required,custom[email]]' style="width: 200px"
                               name="deptBasicInfoForm.teachingGroupLeaderEmail" oninput="valueUpdate(this);"
                               value="${deptBasicInfoForm.teachingGroupLeaderEmail }"/>
                    </td>
                </tr>
                </tbody>
            </table>

            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="groupMemberTable" style="table-layout: fixed">
                <tr>
                    <th style="width: 30%">教学小组组员<span class="red">*</span></th>
                    <th style="width: 30%">手机号码<span class="red">*</span></th>
                    <th style="width: 30%">邮箱地址<span class="red">*</span></th>
                    <th style="width: 10%"><img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                                style="cursor: pointer;" onclick="javascript:addGroupMember('groupMember')" /></th>
                </tr>
                <tbody id="groupMemberTb">
                <c:forEach var="groupMember" items="${deptBasicInfoForm.teachingGroupMemberList}" varStatus="status">
                    <tr>
                        <td>
                            <input type="text" class="input validate[required]" style="width: 200px"
                                   name="deptBasicInfoForm.teachingGroupMemberList[${status.index}].teachingGroupMemberName" oninput="valueUpdate(this);"
                                   value="${groupMember.teachingGroupMemberName }"/>
                        </td>
                        <td>
                            <input type="text" class='input validate[required,custom[phone]]' style="width: 200px"
                                   name="deptBasicInfoForm.teachingGroupMemberList[${status.index}].teachingGroupMemberPhone" oninput="valueUpdate(this);"
                                   value="${groupMember.teachingGroupMemberPhone }"/>
                        </td>
                        <td>
                            <input type="text" class='input validate[required,custom[email]]' style="width: 200px"
                                   name="deptBasicInfoForm.teachingGroupMemberList[${status.index}].teachingGroupMemberEmail" oninput="valueUpdate(this);"
                                   value="${groupMember.teachingGroupMemberEmail }"/>
                        </td>
                        <td><img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
                                 style="cursor: pointer;" onclick="javascript:delGroupMember(this, 'groupMember', 'teachingGroupMemberList');" /></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="div_table">
            <h4>基本条件</h4>
            <table border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                </colgroup>
                <tbody>
                <%--<tr>
                    <th><span style="color: red">*</span>&nbsp;医院类别：</th>
                    <td colspan="3">
                        <label><input name="deptBasicInfoForm.hostType" type="radio" value="${GlobalConstant.FLAG_Y}" class='validate[required]'
                                      <c:if test="${deptBasicInfoForm.hostType ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;综合医院&#12288;</label>
                        <label><input name="deptBasicInfoForm.hostType" type="radio" value="${GlobalConstant.FLAG_N}" class='validate[required]'
                                      <c:if test="${deptBasicInfoForm.hostType ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;专科医院</label>
                    </td>
                </tr>--%>
                <tr>
                    <th><span style="color: red">*</span>&nbsp;本年编制总床位数（张）：</th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]'
                               name="deptBasicInfoForm.bzzcws" style="width:200px;" value="${deptBasicInfoForm.bzzcws}"/>
                    </td>
                    <th><span style="color: red">*</span>&nbsp;本年实有总床位数（张）：</th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.syzcws" value="${deptBasicInfoForm.syzcws}"/>
                    </td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>&nbsp;本年收治住院病人数（人次）：</th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.nszzybrs" style="width:200px;"
                               value="${deptBasicInfoForm.nszzybrs}"/>
                    </td>
                    <th><span style="color: red">*</span>&nbsp;本年病床使用率（%）：</th>
                    <td>
                        <input type="text" class='input validate[required,custom[number],min[0],max[100],maxSize[10]]' style="width:200px;"
                               name="deptBasicInfoForm.bcsyl" value="${deptBasicInfoForm.bcsyl}"/>
                    </td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>&nbsp;本年门诊量（人次）：</th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]'
                               name="deptBasicInfoForm.nmzl" style="width:200px;" value="${deptBasicInfoForm.nmzl}"/>
                    </td>
                    <th><span style="color: red">*</span>&nbsp;本年急诊量（人次）：</th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.njzl" value="${deptBasicInfoForm.njzl}"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <span style="color: red">*</span>
                        本年病床周转次数（次）：
                    </th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]'
                               name="deptBasicInfoForm.bczzcs" style="width:200px;" value="${deptBasicInfoForm.bczzcs}"/>
                    </td>
                    <th>
                        <span style="color: red">*</span>
                        本年平均住院日（天）：
                    </th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.pjzyr" value="${deptBasicInfoForm.pjzyr}"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <span style="color: red">*</span>
                        本年出院病人数（人次）：
                    </th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]'
                               name="deptBasicInfoForm.ncybrs" style="width:200px;" value="${deptBasicInfoForm.ncybrs}"/>
                    </td>
                    <th>
                        <span style="color: red">*</span>
                        本年急诊手术例数（例次）：
                    </th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]'
                               style="width:200px;" name="deptBasicInfoForm.njzscls"
                               value="${deptBasicInfoForm.njzscls}"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <span style="color: red">*</span>
                        近三年培训总容量（人）：
                    </th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.npxzrl"
                               style="width:200px;" value="${deptBasicInfoForm.npxzrl}"/>
                    </td>
                    <th>
                        <span style="color: red">*</span>
                        本年剩余培训容量（人）：
                    </th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.sypxrl" value="${deptBasicInfoForm.sypxrl}"/>
                    </td>
                </tr>
                <c:if test="${speFlow eq '1600'}">
                <tr>
                    <th>
                        <span style="color: red">*</span>
                        产科本年分娩量（人次）：
                    </th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.cknfml"
                               style="width:200px;" value="${empty deptBasicInfoForm.cknfml?'':deptBasicInfoForm.cknfml}"/>
                    </td>
                    <th>
                        <span style="color: red">*</span>
                        妇科本年收治病人数（人次）：
                    </th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.fknszbrs" value="${empty deptBasicInfoForm.fknszbrs?'':deptBasicInfoForm.fknszbrs}"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <span style="color: red">*</span>
                        产科本年收治病人数（人次）：
                    </th>
                    <td colspan="3">
                        <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.cknszbrs"
                               style="width:200px;" value="${empty deptBasicInfoForm.cknszbrs?'':deptBasicInfoForm.cknszbrs}"/>
                    </td>
                </tr>
                </c:if>
                <c:if test="${speFlow eq '1900'}">
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年麻醉总数（人次）：
                        </th>
                        <td>
                            <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.nmzzszmk"
                                   style="width:200px;" value="${empty deptBasicInfoForm.nmzzszmk?'':deptBasicInfoForm.nmzzszmk}"/>
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年麻醉恢复室病人数（人次）：
                        </th>
                        <td>
                            <input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;"
                                   name="deptBasicInfoForm.mzhfsbrs" value="${empty deptBasicInfoForm.mzhfsbrs?'':deptBasicInfoForm.mzhfsbrs}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年疼痛门诊病人数（人次）：
                        </th>
                        <td>
                            <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.ttmzbrs"
                                   style="width:200px;" value="${empty deptBasicInfoForm.ttmzbrs?'':deptBasicInfoForm.ttmzbrs}"/>
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年重症监护室收治病人数（人次）：
                        </th>
                        <td>
                            <input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;"
                                   name="deptBasicInfoForm.zzjhsszbrs" value="${empty deptBasicInfoForm.zzjhsszbrs?'':deptBasicInfoForm.zzjhsszbrs}"/>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${speFlow eq '2000'}">
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年活检标本病例数（例次）：
                        </th>
                        <td>
                            <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.nhjbbbls"
                                   style="width:200px;" value="${empty deptBasicInfoForm.nhjbbbls?'':deptBasicInfoForm.nhjbbbls}"/>
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年尸体解剖病例数（例次）：
                        </th>
                        <td>
                            <input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;"
                                   name="deptBasicInfoForm.nstjpbls" value="${empty deptBasicInfoForm.nstjpbls?'':deptBasicInfoForm.nstjpbls}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年冰冻快速诊断量（例次）：
                        </th>
                        <td>
                            <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.nbdkszdl"
                                   style="width:200px;" value="${empty deptBasicInfoForm.nbdkszdl?'':deptBasicInfoForm.nbdkszdl}"/>
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年细胞学检查病例数（例次）：
                        </th>
                        <td>
                            <input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;"
                                   name="deptBasicInfoForm.nxbxjcbls" value="${empty deptBasicInfoForm.nxbxjcbls?'':deptBasicInfoForm.nxbxjcbls}"/>
                        </td>
                    </tr>
                </c:if>

                <tr>
                    <th><span class="red">*</span>本年总病例病种数（个）：</th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.annualDiseases"
                              style="width:200px;" value="${empty deptBasicInfoForm.annualDiseases?'':deptBasicInfoForm.annualDiseases}"/>
                    </td>
                    <th>
                        <span class="red">*</span>本年收治总疾病（种）：
                    </th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.annualDiseaseCategory"
                               style="width:200px;" value="${empty deptBasicInfoForm.annualDiseaseCategory?'':deptBasicInfoForm.annualDiseaseCategory}"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>本年收治总疾病（个）：</th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.annualDiseaseNumber"
                               style="width:200px;" value="${empty deptBasicInfoForm.annualDiseaseNumber?'':deptBasicInfoForm.annualDiseaseNumber}"/>
                    </td>
                    <th>
                        <span class="red">*</span>教学门诊：
                    </th>
                    <td>
                        <input type="radio" name="deptBasicInfoForm.teachingClinic" value="Y" <c:if test="${deptBasicInfoForm.teachingClinic eq 'Y' }">checked="checked"</c:if>/>有&nbsp;
                        <input type="radio" name="deptBasicInfoForm.teachingClinic" value="N" <c:if test="${deptBasicInfoForm.teachingClinic eq 'N' }">checked="checked"</c:if>/>无&nbsp;
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>近三年培训人数总计（人）：</th>
                    <td>
                        <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.threeYearTrainingCount"
                               style="width:200px;" value="${empty deptBasicInfoForm.threeYearTrainingCount?'':deptBasicInfoForm.threeYearTrainingCount}"/>
                    </td>
                    <th>
                        <span class="red">*</span>近三年理论首考平均通过率（%）：
                    </th>
                    <td>
                        <input type="text" class='input validate[required,custom[number],min[0],max[100],maxSize[10]]' name="deptBasicInfoForm.threeYearExamPassPer"
                               style="width:200px;" value="${empty deptBasicInfoForm.threeYearExamPassPer?'':deptBasicInfoForm.threeYearExamPassPer}"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <%--<div class="div_table">
            <h4>承担教学任务（近三年总数）</h4>
            <table border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th>本科生：</th>
                    <td>
                        <label><input name="deptBasicInfoForm.bksyw" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${deptBasicInfoForm.bksyw == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>

                        <label><input name="deptBasicInfoForm.bksyw" type="radio" value="${GlobalConstant.FLAG_N}" onclick="toZero('bksrs');"
                                      <c:if test="${deptBasicInfoForm.bksyw == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                        <input type="text" class='input validate[custom[integer],min[0]]' style="width:100px;" id="bksrs" name="deptBasicInfoForm.bksrs" value="${deptBasicInfoForm.bksrs}"/>人次
                    </td>
                    <th>研究生（硕、博）：</th>
                    <td>
                        <label><input name="deptBasicInfoForm.yjsyw" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${deptBasicInfoForm.yjsyw == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>

                        <label><input name="deptBasicInfoForm.yjsyw" type="radio" value="${GlobalConstant.FLAG_N}" onclick="toZero('yjsrs');"
                                      <c:if test="${deptBasicInfoForm.yjsyw == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                        <input type="text" class='input validate[custom[integer],min[0]]' style="width:100px;" id="yjsrs" name="deptBasicInfoForm.yjsrs" value="${deptBasicInfoForm.yjsrs}"/>人次
                    </td>
                </tr>
                <tr>
                    <th>住院医师：</th>
                    <td>
                        <label><input name="deptBasicInfoForm.zyysyw" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${deptBasicInfoForm.zyysyw ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="deptBasicInfoForm.zyysyw" type="radio" value="${GlobalConstant.FLAG_N}" onclick="toZero('zyysrs');"
                        <c:if test="${deptBasicInfoForm.zyysyw ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                        <input type="text" class='input validate[custom[integer],min[0]]' style="width:100px;" name="deptBasicInfoForm.zyysrs" id="zyysrs" value="${deptBasicInfoForm.zyysrs}"/>人次
                    </td>
                    <th>进修医师：</th>
                    <td>
                        <label><input name="deptBasicInfoForm.jxysyw" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${deptBasicInfoForm.jxysyw ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="deptBasicInfoForm.jxysyw" type="radio" value="${GlobalConstant.FLAG_N}" onclick="toZero('jxysrs');"
                        <c:if test="${deptBasicInfoForm.jxysyw ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                        <input type="text" class='input validate[custom[integer],min[0]]' style="width:100px;" name="deptBasicInfoForm.jxysrs" id="jxysrs" value="${deptBasicInfoForm.jxysrs}"/>人次
                    </td>
                </tr>
                <tr>
                    <th>承担继续医学教育情况：</th>
                    <td colspan="3"><textarea name="deptBasicInfoForm.cdjjyx">${deptBasicInfoForm.cdjjyx}</textarea></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="div_table">
            <h4>承担科研项目（省部级以上的国家发明奖和科技进步奖）</h4>
            <table border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th>特等：</th>
                    <td><input type="text" class='input validate[custom[integer],min[0]]'
                                           name="deptBasicInfoForm.td" style="width:200px;" value="${deptBasicInfoForm.td}"/>项
                    </td>
                    <th>一等：</th>
                    <td><input type="text" class='input validate[custom[integer],min[0]]'
                                           style="width:200px;" name="deptBasicInfoForm.yd" value="${deptBasicInfoForm.yd}"/>项
                    </td>
                </tr>
                <tr>
                    <th>二等：</th>
                    <td><input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.ed"
                               style="width:200px;" value="${deptBasicInfoForm.ed}"/>项
                    </td>
                    <th>三等：</th>
                    <td><input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.sd" value="${deptBasicInfoForm.sd}"/>项
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="div_table">
            <h4>其他</h4>
            <table border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th>国家临床重点专科：</th>
                    <td>
                        <label><input name="deptBasicInfoForm.gjlczdzk" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${deptBasicInfoForm.gjlczdzk ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;是&#12288;</label>
                        <label><input name="deptBasicInfoForm.gjlczdzk" type="radio" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${deptBasicInfoForm.gjlczdzk ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;否</label>
                    </td>
                    <th>省重点建设学科：</th>
                    <td>
                        <label><input name="deptBasicInfoForm.sszdjsxk" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${deptBasicInfoForm.sszdjsxk == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;是&#12288;</label>
                        <label><input name="deptBasicInfoForm.sszdjsxk" type="radio" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${deptBasicInfoForm.sszdjsxk == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;否</label>
                    </td>
                </tr>
                <tr>
                    <th>市重点建设学科：</th>
                    <td>
                        <label><input name="deptBasicInfoForm.cityZdjsxk" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${deptBasicInfoForm.cityZdjsxk == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;是&#12288;</label>
                        <label><input name="deptBasicInfoForm.cityZdjsxk" type="radio" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${deptBasicInfoForm.cityZdjsxk == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;否</label>
                    </td>
                    <th>学位培养点：</th>
                    <td>
                        <label><input name="deptBasicInfoForm.xwpyd" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${deptBasicInfoForm.xwpyd == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;是&#12288;</label>
                        <label><input name="deptBasicInfoForm.xwpyd" type="radio" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${deptBasicInfoForm.xwpyd == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;否</label>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right">
                        <input type="checkbox" id="masterxw" onclick="toRead('masterxw','masterEdit');"
                               <c:if test="${deptBasicInfoForm.masterxw eq 'Y'}">checked="checked"</c:if> name="deptBasicInfoForm.masterxw"/>&#12288;硕士
                    </td>
                    <td>学校：<input type="text" class='input masterEdit' name="deptBasicInfoForm.masterSchlAndSpe[0].school"
                                             style="width:200px;" value="${deptBasicInfoForm.masterSchlAndSpe[0].school}"/></td>
                    <td colspan="2">专业：<input type="text" class='input masterEdit' name="deptBasicInfoForm.masterSchlAndSpe[0].spe"
                                             style="width:200px;" value="${deptBasicInfoForm.masterSchlAndSpe[0].spe}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>学校：<input type="text" class='input masterEdit' name="deptBasicInfoForm.masterSchlAndSpe[1].school"
                                             style="width:200px;" value="${deptBasicInfoForm.masterSchlAndSpe[1].school}"/></td>
                    <td colspan="2">专业：<input type="text" class='input masterEdit' name="deptBasicInfoForm.masterSchlAndSpe[1].spe"
                                             style="width:200px;" value="${deptBasicInfoForm.masterSchlAndSpe[1].spe}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>学校：<input type="text" class='input masterEdit' name="deptBasicInfoForm.masterSchlAndSpe[2].school"
                                             style="width:200px;" value="${deptBasicInfoForm.masterSchlAndSpe[2].school}"/></td>
                    <td colspan="2">专业：<input type="text" class='input masterEdit' name="deptBasicInfoForm.masterSchlAndSpe[2].spe"
                                             style="width:200px;" value="${deptBasicInfoForm.masterSchlAndSpe[2].spe}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>学校：<input type="text" class='input masterEdit' name="deptBasicInfoForm.masterSchlAndSpe[3].school"
                                             style="width:200px;" value="${deptBasicInfoForm.masterSchlAndSpe[3].school}"/></td>
                    <td colspan="2">专业：<input type="text" class='input masterEdit' name="deptBasicInfoForm.masterSchlAndSpe[3].spe"
                                             style="width:200px;" value="${deptBasicInfoForm.masterSchlAndSpe[3].spe}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>学校：<input type="text" class='input masterEdit' name="deptBasicInfoForm.masterSchlAndSpe[4].school"
                                             style="width:200px;" value="${deptBasicInfoForm.masterSchlAndSpe[4].school}"/></td>
                    <td colspan="2">专业：<input type="text" class='input masterEdit' name="deptBasicInfoForm.masterSchlAndSpe[4].spe"
                                             style="width:200px;" value="${deptBasicInfoForm.masterSchlAndSpe[4].spe}"/></td>
                </tr>
                <tr>
                    <td style="text-align: right">
                        <input type="checkbox"  onclick="toRead('drxw','drxwEdit');" id="drxw"
                               <c:if test="${deptBasicInfoForm.drxw eq 'Y'}">checked="checked"</c:if> name="deptBasicInfoForm.drxw"/>&#12288;博士
                    </td>
                    <td>学校：<input type="text" class='input drxwEdit' name="deptBasicInfoForm.docSchlAndSpe[0].school"
                                             style="width:200px;" value="${deptBasicInfoForm.docSchlAndSpe[0].school}"/></td>
                    <td colspan="2">专业：<input type="text" class='input drxwEdit' name="deptBasicInfoForm.docSchlAndSpe[0].spe"
                                             style="width:200px;" value="${deptBasicInfoForm.docSchlAndSpe[0].spe}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>学校：<input type="text" class='input drxwEdit' name="deptBasicInfoForm.docSchlAndSpe[1].school"
                                             style="width:200px;" value="${deptBasicInfoForm.docSchlAndSpe[1].school}"/></td>
                    <td colspan="2">专业：<input type="text" class='input drxwEdit' name="deptBasicInfoForm.docSchlAndSpe[1].spe"
                                             style="width:200px;" value="${deptBasicInfoForm.docSchlAndSpe[1].spe}"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>学校：<input type="text" class='input drxwEdit' name="deptBasicInfoForm.docSchlAndSpe[2].school"
                                             style="width:200px;" value="${deptBasicInfoForm.docSchlAndSpe[2].school}"/></td>
                    <td colspan="2">专业：<input type="text" class='input drxwEdit' name="deptBasicInfoForm.docSchlAndSpe[2].spe"
                                             style="width:200px;" value="${deptBasicInfoForm.docSchlAndSpe[2].spe}"/></td>
                </tr>
                <tr>
                    <th>开展住院医师规范化培训工作年限：</th>
                    <td><input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.kzzyys" style="width:100px;" value="${deptBasicInfoForm.kzzyys}"/>年
                    <th>近三年累计结业人数：</th>
                    <td><input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.ljjyrs" style="width:100px;" value="${deptBasicInfoForm.ljjyrs}"/>人
                </tr>
                </tbody>
            </table>
        </div>--%>
    </div>
    <div class="btn_info">
        <input class="btn_green" onclick="saveBaseInfo()" type="button" value="保&#12288;存"/>
    </div>
</form>
<div style="display: none">
    <table id="groupMemberTemplate">
        <tr>
            <td>
                <input type="text" class="input validate[required]" style="width:200px"
                       name="deptBasicInfoForm.teachingGroupMemberList[{index}].teachingGroupMemberName" oninput="valueUpdate(this);" />
            </td>
            <td>
                <input type="text" class='input validate[required,custom[phone]]' style="width: 200px"
                       name="deptBasicInfoForm.teachingGroupMemberList[{index}].teachingGroupMemberPhone" oninput="valueUpdate(this);" />
            </td>
            <td>
                <input type="text" class='input validate[required,custom[email]]' style="width: 200px"
                       name="deptBasicInfoForm.teachingGroupMemberList[{index}].teachingGroupMemberEmail" oninput="valueUpdate(this);" />
            </td>
            <td>
                <img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
                     style="cursor: pointer;" onclick="javascript:delGroupMember(this, 'groupMember', 'groupMemberList');" />
            </td>
        </tr>
    </table>
</div>