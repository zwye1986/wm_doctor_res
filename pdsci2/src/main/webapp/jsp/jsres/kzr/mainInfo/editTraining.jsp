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
<script type="text/javascript"
        src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    .div_table h4 {
        color: #000000;
        font: 15px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info th,.grid th {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info td,.grid td,.base_info td label, .base_info td input {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        if ($("#workTb tr").length <= 0) {
            add('work');
        }
    });

    function add(tb) {
        var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
        var index = $("#" + tb + "Tb tr").length;
        cloneTr.html(cloneTr.html().htmlFormartById({"index": index}));
        $("#" + tb + "Tb").append(cloneTr);
    }

    function uploadFile(obj) {
        var activityName = $(obj).val();
        if (activityName == "") {
            jboxTip("请选择文件！");
            return;
        }

        var types = ".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx".split(",");
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
        if ($.trim(activityName) != "" && !regStr.test(activityName)) {
            $(obj).val("")
            jboxTip("请上传&nbsp;.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx格式的文件");
            return;
        }
    }
    
    function del(obj){
        jboxConfirm("确认删除？", function () {
            var tr = obj.parentNode.parentNode;
            tr.remove();
        });
    }
    
    String.prototype.htmlFormartById = function (data) {
        var html = this;
        for (var key in data) {
            var reg = new RegExp('\\{' + key + '\\}', 'g');
            html = html.replace(reg, data[key]);
        }
        return html;
    };

    function saveBaseInfo(){
        if (!$("#BaseInfoForm").validationEngine("validate")) {
            $("#indexBody").scrollTop("0px");
            return false;
        }
        jboxSubmit($("#BaseInfoForm"), "<s:url value='/jsres/kzr/saveBase'/>?type=${GlobalConstant.TRAINING}", function (resp) {
            if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
                setTimeout(function(){
                    $("#submitBtn").show();
                    loadInfo('${GlobalConstant.TRAINING}','${deptFlow}');
                },1000);
            }
        }, null, true);
    }
</script>
<input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
<div
style="position: relative;overflow-y: auto;height: 600px"  >
    <form id='BaseInfoForm' style="position:relative;">
        <input type="hidden" name="resBaseSpeDept.orgFlow" id="orgFlow"
               value="${empty baseSpeDept?sessionScope.currUser.orgFlow:baseSpeDept.orgFlow}"/>
        <input type="hidden" id="deptFlow"  name="resBaseSpeDept.deptFlow" value="${deptFlow}"/>
        <input type="hidden" id="flag" name="flag" value="${GlobalConstant.TRAINING}"/>
        <input type="hidden" name="resBaseSpeDept.sessionNumber" value="${sessionNumber}"/>
        <div class="main_bd">
            <div class="div_table">
                <h4>培训对象医疗工作量</h4>
                <table cellspacing="0" cellpadding="0" class="base_info">
                    <colgroup>
                        <col width="20%"/>
                        <col width="30%"/>
                        <col width="20%"/>
                        <col width="30%"/>
                    </colgroup>
                    <tbody>
                        <tr>
                            <th>轮转管床数：</th>
                            <td><input type="text" class='input validate[custom[integer],min[0]]' style="width:100px;"
                                       name="trainingForm.lzcws" value="${trainingForm.lzcws}"/>张
                            </td>
                            <th>日门诊量：</th>
                            <td>
                                <input type="text" class='input validate[custom[integer],min[0]]' style="width:100px;"
                                       name="trainingForm.rmzl" value="${trainingForm.rmzl}"/>人次
                            </td>
                        </tr>
                        <tr>
                            <th>日急诊量：</th>
                            <td>
                                <input type="text" class='input validate[custom[integer],min[0]]' style="width:100px;"
                                       name="trainingForm.rjzl" value="${trainingForm.rjzl}"/>人次
                            </td>
                            <th>轮转必选科室手写系统病历数：</th>
                            <td>
                                <input type="text" class='input validate[custom[integer],min[0]]' style="width:100px;"
                                       name="trainingForm.lzbx" value="${trainingForm.lzbx}"/>份/科
                            </td>
                        </tr>
                        <tr>
                            <th>近三年招收人数：</th>
                            <td>
                                <input type="text" class='input validate[custom[integer],min[0]]' style="width:100px;"
                                       name="trainingForm.zrs" value="${trainingForm.zrs}"/>人
                            </td>
                            <th>当前在培人数：</th>
                            <td>
                                <input type="text" class='input validate[custom[integer],min[0]]' style="width:100px;"
                                       name="trainingForm.zprs" value="${trainingForm.zprs}"/>人
                            </td>
                        </tr>
                        <tr>
                            <th>住院医师规范化培训登记手册：</th>
                            <td>
                                <label><input name="trainingForm.dj" type="radio" value="${GlobalConstant.FLAG_Y}"
                                              <c:if test="${trainingForm.dj ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                                <label><input name="trainingForm.dj" type="radio" value="${GlobalConstant.FLAG_N}"
                                              <c:if test="${trainingForm.dj ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                            </td>
                            <th>住院医师规范化培训考核手册：</th>
                            <td>
                                <label><input name="trainingForm.kh" type="radio" value="${GlobalConstant.FLAG_Y}"
                                              <c:if test="${trainingForm.kh ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
                                <label><input name="trainingForm.kh" type="radio" value="${GlobalConstant.FLAG_N}"
                                              <c:if test="${trainingForm.kh ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="div_table">
                <h4>科室各种培训活动记录（可另附表）</h4>
                <table border="2px" cellpadding="0" cellspacing="0" class="grid" id="table6">
                    <colgroup>
                        <col width="50%"/>
                        <col width="30%"/>
                        <col width="20%"/>
                    </colgroup>
                    <tr>
                        <td colspan="3" style="text-align: left">近三年入科教育、轮转计划表、教学查房、疑难死亡病例讨论、小讲课、出科考核。</td>
                    </tr>
                    <tr>
                        <th>培训活动记录名称&nbsp;<span style="color: red;">*</span></th>
                        <th>上传附件</th>
                        <th>操作
                            <img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;position: relative;top: 3px;" onclick="javascript:add('work')"></img>&#12288;
                        </th>
                    </tr>
                    <tbody id="workTb">
                    <c:forEach var="equ" items="${trainingForm.trainingActivityForms}" varStatus="status">
                        <tr>
                            <td style="position: relative">
                                <input type="text" class="input validate[required]" style="width:80%;"
                                       name="trainingForm.trainingActivityForms[${status.index}].activityName"
                                       value="${equ.activityName }"/>
                                <input type="hidden" name="trainingForm.trainingActivityForms[${status.index}].appendix"
                                       value="${equ.appendix}"/>

                            </td>
                            <td style="text-align: center">
                                <c:set var="file" value="${fileMap[equ.appendix]}"></c:set>
                                <c:if test="${empty file}">
                                    <input type="file" name="${equ.appendix}" onchange="uploadFile(this);"
                                           accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
                                </c:if>

                                <c:if test="${not empty file}">
                                    <input type='file' name='${equ.appendix}' onchange="uploadFile(this);"
                                           accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;display: none;"/>
                                    <input hidden name="fileFlows" value="${file.fileFlow}"/>
                                    <a href="<s:url value='/jsres/base/downFile'/>?fileFlow=${file.fileFlow}"
                                       target="_blank">${file.fileName}</a>
                                </c:if>
                            </td>
                            <td>
                                <a style="margin-left: -18px" onclick="del(this);">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="btn_info">
            <input class="btn_green" onclick="saveBaseInfo()" type="button" value="保&#12288;存"/>
        </div>
    </form>

</div>


<div style="display: none">
    <table id="workTemplate">
        <tr>
            <td style="position: relative">
                <input type="text" class="input validate[required]" style="width:80%;"name="trainingForm.trainingActivityForms[{index}].activityName" />
                <input type="hidden" name="trainingForm.trainingActivityForms[{index}].appendix" value="{index}"/>
            </td>
            <td>
                <input type='file' name="{index}" onchange="uploadFile(this);"
                       accept=".doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx" style="width: 100%;"/>
            </td>
            <td>
                <a style="margin-left: -18px" onclick="del(this);">删除</a>
            </td>
        </tr>
    </table>
</div>
