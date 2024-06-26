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

<style type="text/css">
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

    .base_info td, .grid td, .input, .grid td label {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }
</style>

<script type="text/javascript">
    $(document).ready(function () {
        if ($("#personTb tr").length <= 0) {
            add('person');
        }
        if ($("#ruleTb tr").length <= 0) {
            add('rule');
        }
        if ($("#workTb tr").length <= 0) {
            add('work');
        }

    });

    String.prototype.htmlFormartById = function (data) {
        var html = this;
        for (var key in data) {
            var reg = new RegExp('\\{' + key + '\\}', 'g');
            html = html.replace(reg, data[key]);
        }
        return html;
    };

    function add(tb) {
        var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
        var index = $("#" + tb + "Tb tr").length;
        cloneTr.html(cloneTr.html().htmlFormartById({"index": index}));
        $("#" + tb + "Tb").append(cloneTr);
        if(tb != 'person'){
            $("#" + tb + "Tb").children(":last-child").children().slice(0, 1).text(index + 1);
        }
    }

    function saveOrganizationInfo() {
        if (!$("#BaseInfoForm").validationEngine("validate")) {
            return false;
        }
        jboxSubmit($("#BaseInfoForm"), "<s:url value='/jsres/base/saveBase'/>?type=${GlobalConstant.ORG_MANAGE}", function (resp) {
            if ("${GlobalConstant.SAVE_SUCCESSED}" == resp) {
                setTimeout(function () {
                    $("#submitBtn").show();
                    loadInfo('${GlobalConstant.BASIC_INFO}', '${sessionScope.currUser.orgFlow}', '${sessionNumber}');
                }, 1000);
            }
        }, null, true);
    }

    function del(obj, tb) {
        $(obj).parent().parent().remove();
        var index = $("#" + tb + "Tb tr").length;
        for (let i = 0; i <= index; i++) {
            $("#" + tb + "Tb").children().slice(i, i + 1).children().slice(0, 1).text(i + 1);
        }
    }

    function checkTeltphone(obj) {
        var s = obj.value;
        var reg = /^[1][34578]\w*$/; //正则表达式模式。
        var r = reg.test(s);
        if (r) {
            $(obj).addClass("validate[custom[mobile]]");
            $(obj).removeClass("validate[custom[phone2]]");
        } else {
            $(obj).addClass("validate[custom[phone2]]");
            $(obj).removeClass("validate[custom[mobile]]");
        }
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

    function changeStatus(type, showStatus) {
        if (!showStatus) {
            $("#" + type + "input").hide();
            $("#" + type + "input").addClass("noVal");
        } else {
            $("#" + type + "input").show();
            $("#" + type + "input").removeClass("noVal");
        }
    }

    function uploadFile(obj) {

        var fileName = $(obj).val();
        if (fileName == "") {
            jboxTip("请选择文件！");
            return;
        }
        var types = ".rar,.jpg,.png,.pdf,.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx".split(",");
        var regStr = "/";
        for (var i = 0; i < types.length; i++) {
            if (types[i]) {
                if (i == (types.length - 1)) {
                    regStr = regStr + "\\" + types[i] + '$';
                } else {
                    regStr = regStr + "\\" + types[i] + '$|';
                }
            }
        }
        regStr = regStr + '/i';
        regStr = eval(regStr);
        if ($.trim(fileName) != "" && !regStr.test(fileName)) {
            $(obj).val("");
            jboxTip("请上传&nbsp;.rar,.jpg,.png,.pdf,.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx格式的文件");
            return;
        }
    }

    function reupload(id, obj) {
        jboxConfirm("确认重新选择？", function () {
            $("#" + id).show();
            $("#" + id + "Flow").remove();
            $("#" + id + "A").hide();
            $(obj).hide();
        });
    }
</script>
<input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
<form id='BaseInfoForm' style="position:relative;">
    <input type="hidden" name="sysOrg.orgFlow"
           value="${empty sysOrg.orgFlow?sessionScope.currUser.orgFlow:sysOrg.orgFlow}"/>
    <input type="hidden" name="flag" value="${GlobalConstant.ORG_MANAGE}"/>
    <input type="hidden" name="resBase.sessionNumber" value="${sessionNumber}"/>
    <div class="main_bd">
        <div class="div_table" id="work">
            <%--<h4>组织管理（住院医师培训组织管理机构成员及职责）</h4>
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <colgroup>
                    <col width="10%"/>
                    <col width="6%"/>
                    <col width="6%"/>
                    <col width="15%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="8%"/>
                </colgroup>
                <tr>
                    <th>姓名&nbsp;<span style="color: red;">*</span></th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>专业</th>
                    <th>最高学历</th>
                    <th>科室</th>
                    <th>职务</th>
                    <th>联系电话</th>
                    <th>专职/兼职</th>
                    <th>
                        操作
                        <span style="float: right;padding-right: 20px">
							<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="javascript:add('person')" />
						</span>
                    </th>
                </tr>
                <tbody id="personTb">
                    <c:forEach var="person" items="${organizationManage.organizationPersonList}" varStatus="status">
                        <tr>
                            <td style="position: relative">
                                <input type="text" class="input validate[required]" style="width:80px;"
                                    name="organizationManage.organizationPersonList[${status.index}].name" value="${person.name }"/>
                            </td>
                            <td>
                                <select name="organizationManage.organizationPersonList[${status.index}].sex" class="select"
                                        style="width:80px;">
                                    <option value="">请选择</option>
                                    <c:forEach var="dict" items="${userSexEnumList }">
                                        <c:if test="${dict.id != userSexEnumUnknown.id}">
                                            <option value="${dict.name}"
                                                    <c:if test="${person.sex eq dict.name}">selected="selected"</c:if>>${dict.name}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="text" class="input1 validate[custom[integer],max[150]]" style="width:50px;"
                                       name="organizationManage.organizationPersonList[${status.index}].age"
                                       value="${person.age }"/></td>
                            <td><input type="text" class="input1" style="width:120px;"
                                       name="organizationManage.organizationPersonList[${status.index}].profession"
                                       value="${person.profession }"/></td>
                            <td>
                                <select class="select"
                                        name="organizationManage.organizationPersonList[${status.index}].tallStudy" style="width: 80px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumUserEducationList }" var="dict">
                                        <option value="${dict.dictName}"
                                                <c:if test="${person.tallStudy==dict.dictName }">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="text" class="input1" style="width:100px;"
                                       name="organizationManage.organizationPersonList[${status.index}].dept"
                                       value="${person.dept }"/></td>
                            <td>
                                <select class="select"
                                        name="organizationManage.organizationPersonList[${status.index}].job" style="width: 80px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumUserPostList }" var="dict">
                                        <option value="${dict.dictName}"
                                                <c:if test="${person.job==dict.dictName }">selected="selected"</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="text" class="input1" style="width:100px;"
                                       name="organizationManage.organizationPersonList[${status.index}].telephone"
                                       value="${person.telephone }" onchange="checkTeltphone(this);"/></td>
                            <td><input type="text" class="input1" style="width:80px;"
                                       name="organizationManage.organizationPersonList[${status.index}].partOrAllJob"
                                       value="${person.partOrAllJob }"/></td>
                            <td><a onclick="moveTr(this);">删除</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="div_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th colspan="10" style="text-align:left; padding-left:5px;">
                        现有住院医师培训相关规章制度、培训实施计划、考试考核等（请列出具体名称）
                    </th>
                </tr>
                <tr>
                    <td colspan="10" style="padding-left: 0px;padding-right: 1px;"><textarea name="organizationManage.info">${organizationManage.info}</textarea></td>
                </tr>
            </table>
--%>
            <h4>附件列表</h4>
            <table id="filesTable" border="0" cellpadding="0" cellspacing="0" class="grid" id="table3">
                <tr>
                    <td style="text-align: left;padding-left: 10px;">
                        附件名（请选择rar,jpg,png,pdf,doc,docx,xls,xlsx,zip,ppt,pptx格式的文件）&nbsp;<span style="color: red;">*</span>
                    </td>
                    <td width="75px">
                        操作
                        <span style="float: right;padding-right: 20px">
							<img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addFile();" />
						</span>
                    </td>
                </tr>
                <c:forEach items="${files}" var="file">
                    <c:if test="${file.fileUpType == 'files'}">
                        <tr>
                            <td style="text-align: left;padding-left: 10px;">
                                <input hidden name="fileFlows" value="${file.fileFlow}">
                                <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                                   target="_blank">${file.fileName}</a>
                            </td>
                            <td>
                                <a onclick="moveTr(this);">删除</a>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
            <%--附件模板--%>
            <table class="filesTable" id="fileTableFormat" style="display: none;">
                <tr>
                    <td style="text-align: left;padding-left: 10px;">
                        <input type='file' name='files' class='' onchange="uploadFile(this);"
                               accept=".rar,.jpg,.png,.pdf,.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
                    </td>
                    <td>
                        <a onclick="moveTr(this);">删除</a>
                    </td>
                </tr>
            </table>

           <%-- <h4>培训机构</h4>
            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table4">
                <colgroup>
                    <col width="30%"/>
                    <col width="20%"/>
                    <col width="30%"/>
                    <col width="20%"/>
                </colgroup>
                <tr>
                    <th style="text-align: right;">培训领导小组：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.leaderGroup" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.leaderGroup ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.leaderGroup" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.leaderGroup ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                    <th style="text-align: right;">专家委员会：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.expertCommittee" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.expertCommittee ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.expertCommittee" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.expertCommittee ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">培训管理职能部门：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.managementDepartment" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.managementDepartment ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.managementDepartment" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.managementDepartment ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                    <th style="text-align: right;">专职管理人员：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.managementPersonnel" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.managementPersonnel ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.managementPersonnel" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.managementPersonnel ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">专业基地管理主任负责制：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.directorResponsibility" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.directorResponsibility ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.directorResponsibility" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.directorResponsibility ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                    <th style="text-align: right;">专业基地管理专/兼职秘书：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.secretary" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.secretary ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.secretary" type="radio" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.secretary ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">住院医师规范化培训组织管理机构及职责：</th>
                    <td colspan="3" style="text-align: left;">
                        <label><input name="organizationManage.orgManagement" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.orgManagement == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.orgManagement" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.orgManagement == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
            </table>

            <h4>培训制度</h4>
            <table id="filesTable" border="2px" cellpadding="0" cellspacing="0" class="grid" id="table5">
                <colgroup>
                    <col width="10%"/>
                    <col width="50%"/>
                    <col width="30%"/>
                    <col width="8%" />
                </colgroup>
                <tr>
                    <td colspan="4" style="text-align: left">
                        请提供现有住院医师规范化培训相关规章制度，包括培训管理、培训考核、奖惩制度、人事管理制度、经费管理制度、培训工作会议记录、培训管理职能部门工作记录、培训方案、考核资料、档案、住培工作纳入培训基地(医院)绩效考核体系
                    </td>
                </tr>
                <tr>
                    <th>序号</th>
                    <th>规章制度名称&nbsp;<span style="color: red;">*</span></th>
                    <th>上传附件</th>
                    <th>
                        操作
                        <span style="float: right;padding-right: 20px">
                            <img class="opBtn" title="新增"
                                 src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="javascript:add('rule')" />
                        </span>
                    </th>
                </tr>
                <tbody id="ruleTb">
                    <c:forEach var="trainingSystem" items="${organizationManage.organizationTrainingSystemList}"
                               varStatus="status">
                        <tr>
                            <td>${status.index+1}</td>
                            <td style="position: relative">
                                <input type="text" class="input validate[required]" style="width:300px;"
                                       name="organizationManage.organizationTrainingSystemList[${status.index}].rulesAndRegulations"
                                       value="${trainingSystem.rulesAndRegulations }"/>
                                <input hidden type="text" style="width:80px;"
                                       name="organizationManage.organizationTrainingSystemList[${status.index}].appendix"
                                       value="${trainingSystem.appendix}"/>
                            </td>
                            <td>
                                <c:set var="file" value="${fileMap[trainingSystem.appendix]}"></c:set>
                                <c:if test="${empty file}">
                                    <input type="file" name="${trainingSystem.appendix}" onchange="uploadFile(this);"
                                           accept=".rar,.jpg,.png,.pdf,.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx"
                                           style="width: 100%;"/>
                                </c:if>
                                <c:if test="${not empty file}">
                                    <input type='file' name='${trainingSystem.appendix}' onchange="uploadFile(this);"
                                           accept=".rar,.jpg,.png,.pdf,.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx"
                                           style="width: 100%;display: none;"/>
                                    <input hidden name="fileFlows" value="${file.fileFlow}"/>
                                    <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                                       target="_blank">${file.fileName}</a>
                                </c:if>
                            </td>
                            <td><a onclick="del(this, 'rule');">删除</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <h4>工作情况</h4>
            <table id="filesTable" border="2px" cellpadding="0" cellspacing="0" class="grid" id="table6">
                <colgroup>
                    <col width="10%"/>
                    <col width="50%"/>
                    <col width="30%"/>
                    <col width="8%" />
                </colgroup>
                <tr>
                    <td colspan="4" style="text-align: left">近三年住院医师培训工作计划、总结、培训名单、人数及考核成绩，本院住院医师培训率，接收外单位培训任务。</td>
                </tr>
                <tr>
                    <th style="background-color: #f4f5f9;">序号</th>
                    <th style="background-color: #f4f5f9;">文件名称&nbsp;<span style="color: red;">*</span></th>
                    <th style="background-color: #f4f5f9;">上传附件</th>
                    <th>
                        操作
                        <span style="float: right;padding-right: 20px">
                            <img class="opBtn" title="新增"
                                 src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="javascript:add('work')" />
                        </span>
                    </th>
                </tr>
                <tbody id="workTb">
                    <c:forEach var="workCondition" items="${organizationManage.organizationWorkConditionList}"
                               varStatus="status">
                        <tr>
                            <td>${status.index+1}</td>
                            <td style="position: relative">
                                <input type="text" class="input validate[required]" style="width:300px;"
                                       name="organizationManage.organizationWorkConditionList[${status.index}].fileName"
                                       value="${workCondition.fileName }"/>
                                <input hidden type="text" style="width:80px;"
                                       name="organizationManage.organizationWorkConditionList[${status.index}].appendix"
                                       value="${workCondition.appendix}"/>
                            </td>
                            <td>
                                <c:set var="file" value="${fileMap[workCondition.appendix]}"></c:set>
                                <c:if test="${empty file}">
                                    <input type="file" name="${workCondition.appendix}" onchange="uploadFile(this);"
                                           accept=".rar,.jpg,.png,.pdf,.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx"
                                           style="width: 100%;"/>
                                </c:if>
                                <c:if test="${not empty file}">
                                    <input type='file' name='${workCondition.appendix}' onchange="uploadFile(this);"
                                           accept=".rar,.jpg,.png,.pdf,.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx"
                                           style="width: 100%;display: none;"/>
                                    <input hidden name="fileFlows" value="${file.fileFlow}"/>
                                    <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                                       target="_blank">${file.fileName}</a>
                                </c:if>
                            </td>
                            <td><a onclick="del(this, 'work');">删除</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <h4>住院医师规范化培训工作经验</h4>
            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table7">
                <colgroup>
                    <col width="30%"/>
                    <col width="20%"/>
                    <col width="30%"/>
                    <col width="20%"/>
                </colgroup>
                <tr>
                    <td style="text-align: left" colspan="4">不同人员对住院医师培训工作效果的评价与反馈意见</td>
                </tr>
                <tr>
                    <th style="text-align: right;">培训对象：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.trainees" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.trainees == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.trainees" type="radio" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.trainees == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                    <th style="text-align: right;">指导医师：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.instructor" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.instructor == GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.instructor" type="radio" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.instructor == GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">专业基地：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.professionalBase" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.professionalBase ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.professionalBase" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.professionalBase ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                    <th style="text-align: right;">用人单位：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.employer" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.employer ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.employer" type="radio" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.employer ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: left" colspan="4">不同人员对指导医师带教质量的评价与反馈意见</td>
                </tr>
                <tr>
                    <th style="text-align: right;">培训对象：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.trainees1" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.trainees1 ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.trainees1" type="radio" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.trainees1 ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                    <th style="text-align: right;">上级部门：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.superiorDepartment" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.superiorDepartment ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.superiorDepartment" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.superiorDepartment ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">同行：</th>
                    <td colspan="3" style="text-align: left;">
                        <label><input name="organizationManage.peer" type="radio" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.peer ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.peer" type="radio" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.peer ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
            </table>

            <h4>其他相关措施</h4>
            <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table8">
                <colgroup>
                    <col width="30%"/>
                    <col width="70%"/>
                </colgroup>
                <tr>
                    <th style="text-align: right;">培训基地能够提供给培训对象的生活补助：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.livingAllowance" type="radio"
                                      onclick="changeStatus('allowance',true);" value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.livingAllowance ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.livingAllowance" type="radio"
                                      onclick="changeStatus('allowance',false);" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.livingAllowance ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                        &#12288;（金额：<input id="allowanceinput" type="text"
                                           class="input1 validate[custom[integer],min[0]]" style="width:100px;"
                                           name="organizationManage.allowanceMoney"
                                           value="${organizationManage.allowanceMoney}"
                                           <c:if test="${organizationManage.allowanceMoney==GlobalConstant.FLAG_N or empty organizationManage.allowanceMoney  }">style="display: none;"</c:if>/>元/人/月）
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">与培训对象签订培训协议：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.trainingAgreement" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.trainingAgreement ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.trainingAgreement" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.trainingAgreement ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">协助解决招收社会学员的档案和工龄：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.archivesAndAge" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.archivesAndAge ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.archivesAndAge" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.archivesAndAge ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">协助解决招收社会学员的社会保障：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.socialSecurity" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.socialSecurity ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.socialSecurity" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.socialSecurity ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">解决培训对象住宿：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.stay" type="radio" value="${ GlobalConstant.SOLVE_ALL}"
                                      <c:if test="${organizationManage.stay ==GlobalConstant.SOLVE_ALL }">checked="checked"</c:if> />&#12288;全部解决&#12288;</label>
                        <label><input name="organizationManage.stay" type="radio" value="${ GlobalConstant.SOLVE_PART}"
                                      <c:if test="${organizationManage.stay ==GlobalConstant.SOLVE_PART }">checked="checked"</c:if> />&#12288;部分解决&#12288;</label>
                        <label><input name="organizationManage.stay" type="radio" value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.stay ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">协助解决培训对象的医师资格考试和执业注册：</th>
                    <td style="text-align: left;">
                        <label><input name="organizationManage.registrationForPractitioners" type="radio"
                                      value="${GlobalConstant.FLAG_Y}"
                                      <c:if test="${organizationManage.registrationForPractitioners ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                        <label><input name="organizationManage.registrationForPractitioners" type="radio"
                                      value="${GlobalConstant.FLAG_N}"
                                      <c:if test="${organizationManage.registrationForPractitioners ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                    </td>
                </tr>
            </table>--%>
        </div>

        <c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id  or resBase.baseStatusId eq baseStatusEnumNotPassed.id or empty resBase.baseStatusId  }">
            <div class="btn_info">
                <input class="btn_green" onclick="saveOrganizationInfo();" type="button" value="保&#12288;存"/>
            </div>
        </c:if>
    </div>

</form>
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
