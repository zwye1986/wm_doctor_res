<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript"
        src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    .div_table h4 {
        color: #000000;
        font: 15px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info th {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info td {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }
</style>
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
        if (!$("#BaseInfoForm").validationEngine("validate")) {
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

        jboxPost("<s:url value='/jsres/kzr/saveBase'/>", $("#BaseInfoForm").serialize(), function (resp) {
            if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
                setTimeout(function(){
                    $("#submitBtn").show();
                    loadInfo('${GlobalConstant.DEPT_BASIC_INFO}','${deptFlow}');
                },1000);
            }
        }, null, true);
    }
</script>
<input type="hidden" id="resBase" name="resBase" value="${baseSpeDept}"/>
<form id='BaseInfoForm' style="position:relative;">
    <input type="hidden" name="resBaseSpeDept.orgFlow"
           value="${empty baseSpeDept?sessionScope.currUser.orgFlow:baseSpeDept.orgFlow}"/>
    <input type="hidden" name="resBaseSpeDept.deptFlow" value="${deptFlow}"/>
    <input type="hidden" name="flag" value="${GlobalConstant.DEPT_BASIC_INFO}"/>
    <input type="hidden" name="resBaseSpeDept.sessionNumber" value="${sessionNumber}"/>
    <div class="main_bd" <c:if test="${isJoin eq 'Y'  or isglobal eq 'Y'}"> style="position: relative;overflow-y: auto;height: 600px" </c:if> >
        <div class="div_table">
            <h4><span style="color: red">*</span>基本信息</h4>
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
                        <th>
                            <span style="color: red">*</span>
                            科室名称：
                        </th>
                        <td><input type="text"  class="input validate[required]" name="deptBasicInfoForm.deptName" style="width:90%;"
                                   value="${deptBasicInfoForm.deptName}"/></td>

                        <th>
                            <span style="color: red">*</span>
                            科室代码：
                        </th>
                        <td colspan="5"><input type="text" class="input validate[required]"  style="width:31%;"
                                               name="deptBasicInfoForm.deptCode" value="${deptBasicInfoForm.deptCode}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            科室负责人：
                        </th>
                        <td><input type="text" class="input validate[required]" name="deptBasicInfoForm.deptRespName" style="width:90%;"
                                   value="${deptBasicInfoForm.deptRespName}"/></td>
                        <th>
                            <span style="color: red">*</span>
                            手机号码：
                        </th>
                        <td><input type="text" class=" input validate[required,custom[phone]]" style="width:90%;"
                                   name="deptBasicInfoForm.deptRespPhone"
                                   value="${deptBasicInfoForm.deptRespPhone}"/></td>
                        <th>
                            <span style="color: red">*</span>
                            邮箱地址：
                        </th>
                        <td><input type="text" class=" input validate[required,custom[email]]" style="width:90%;"
                                   name="deptBasicInfoForm.deptRespEmail"
                                   value="${deptBasicInfoForm.deptRespEmail}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            科主任：
                        </th>
                        <td><input type="text" class="input validate[required]" name="deptBasicInfoForm.deptDirName" style="width:90%;"
                                   value="${deptBasicInfoForm.deptDirName}"/></td>
                        <th>
                            <span style="color: red">*</span>
                            手机号码：
                        </th>
                        <td><input type="text" class=" input validate[required,custom[phone]]" style="width:90%;"
                                   name="deptBasicInfoForm.deptDirPhone"
                                   value="${deptBasicInfoForm.deptDirPhone}"/></td>
                        <th>
                            <span style="color: red">*</span>
                            邮箱地址：
                        </th>
                        <td><input type="text" class=" input validate[required,custom[email]]" style="width:90%;"
                                   name="deptBasicInfoForm.deptDirEmail"
                                   value="${deptBasicInfoForm.deptDirEmail}"/></td>
                    </tr>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            科室秘书：
                        </th>
                        <td><input type="text" class="input validate[required]" name="deptBasicInfoForm.deptSceName" style="width:90%;"
                                   value="${deptBasicInfoForm.deptSceName}"/></td>
                        <th>
                            <span style="color: red">*</span>
                            手机号码：
                        </th>
                        <td><input type="text" class=" input validate[required,custom[phone]]" style="width:90%;"
                                   name="deptBasicInfoForm.deptScePhone"
                                   value="${deptBasicInfoForm.deptScePhone}"/></td>
                        <th>
                            <span style="color: red">*</span>
                            邮箱地址：
                        </th>
                        <td><input type="text" class=" input validate[required,custom[email]]" style="width:90%;"
                                   name="deptBasicInfoForm.deptSceEmail"
                                   value="${deptBasicInfoForm.deptSceEmail}"/></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="div_table">
            <h4><span style="color: red">*</span>基本条件</h4>
            <table border="0" cellspacing="0" cellpadding="0" class="base_info">
                <colgroup>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                </colgroup>
                <tbody>
                    <%--<tr>
                        <th>
                            <span style="color: red">*</span>
                            医院类别：
                        </th>
                        <td colspan="3">
                            <label><input name="deptBasicInfoForm.hostType" type="radio" value="${GlobalConstant.FLAG_Y}" class='validate[required]'
                                          <c:if test="${deptBasicInfoForm.hostType ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;综合医院&#12288;</label>
                            <label><input name="deptBasicInfoForm.hostType" type="radio" value="${GlobalConstant.FLAG_N}" class='validate[required]'
                                          <c:if test="${deptBasicInfoForm.hostType ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;专科医院</label>
                        </td>
                    </tr>--%>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年编制总床位数（张）：
                        </th>
                        <td><input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.bzzcws"
                                   style="width:200px;" value="${deptBasicInfoForm.bzzcws}" />
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年实有总床位数（张）：
                        </th>
                        <td><input type="text" class='input validate[required,custom[integer],min[0]]'  name="deptBasicInfoForm.syzcws"
                                   style="width:200px;" value="${deptBasicInfoForm.syzcws}" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年收治住院病人数（人次）：
                        </th>
                        <td><input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.nszzybrs"
                                   style="width:200px;" value="${deptBasicInfoForm.nszzybrs}" />
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年病床使用率（%）：
                        </th>
                        <td><input type="text" class='input validate[required,custom[number],min[0]]' name="deptBasicInfoForm.bcsyl"
                                   style="width:200px;" value="${deptBasicInfoForm.bcsyl}" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年门诊量（人次）：
                        </th>
                        <td><input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.nmzl"
                                   style="width:200px;" value="${deptBasicInfoForm.nmzl}" />
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年急诊量（人次）：
                        </th>
                        <td><input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.njzl"
                                   style="width:200px;" value="${deptBasicInfoForm.njzl}" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年病床周转次数（次）：
                        </th>
                        <td><input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;"
                                               name="deptBasicInfoForm.bczzcs" value="${deptBasicInfoForm.bczzcs}"/>
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年平均住院日（天）：
                        </th>
                        <td><input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;"
                                               name="deptBasicInfoForm.pjzyr" value="${deptBasicInfoForm.pjzyr}"/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年出院病人数（人次）：
                        </th>
                        <td>
                            <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.ncybrs" style="width:200px;" value="${deptBasicInfoForm.ncybrs}"/>
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年急诊手术例数（例次）：
                        </th>
                        <td><input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;" name="deptBasicInfoForm.njzscls"
                                   value="${deptBasicInfoForm.njzscls}"/>
                        </td>
                    </tr>
                    <%--<tr>
                        <th>
                            <span style="color: red">*</span>
                            近三年培训总容量（人）：
                        </th>
                        <td>
                            <input type="text" class='input validate[required,custom[integer],min[0]]' name="deptBasicInfoForm.npxzrl" style="width:200px;" value="${deptBasicInfoForm.npxzrl}"/>
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年剩余培训容量（人）：
                        </th>
                        <td>
                            <input type="text" class='input validate[required,custom[integer],min[0]]' style="width:200px;" name="deptBasicInfoForm.sypxrl" value="${deptBasicInfoForm.sypxrl}"/>
                        </td>
                    </tr>--%>
                    <tr>
                        <th><span class="red">*</span>本年总病例病种数（个）：</th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.annualDiseases"
                                   style="width:200px;" value="${empty deptBasicInfoForm.annualDiseases?0:deptBasicInfoForm.annualDiseases}"/>
                        </td>
                        <th>
                            <span class="red">*</span>本年收治总疾病（种）：
                        </th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.annualDiseaseCategory"
                                   style="width:200px;" value="${empty deptBasicInfoForm.annualDiseaseCategory?0:deptBasicInfoForm.annualDiseaseCategory}"/>
                        </td>
                    </tr>
                    <tr>
                        <th><span class="red">*</span>本年收治总疾病（个）：</th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.annualDiseaseNumber"
                                   style="width:200px;" value="${empty deptBasicInfoForm.annualDiseaseNumber?0:deptBasicInfoForm.annualDiseaseNumber}"/>
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
                            <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.threeYearTrainingCount"
                                   style="width:200px;" value="${empty deptBasicInfoForm.threeYearTrainingCount?0:deptBasicInfoForm.threeYearTrainingCount}"/>
                        </td>
                        <th>
                            <span class="red">*</span>近三年理论首考平均通过率（%）：
                        </th>
                        <td>
                            <input type="text" class='input validate[custom[number],min[0]]' name="deptBasicInfoForm.threeYearExamPassPer"
                                   style="width:200px;" value="${empty deptBasicInfoForm.threeYearExamPassPer?0:deptBasicInfoForm.threeYearExamPassPer}"/>
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
                                          <c:if test="${deptBasicInfoForm.bksyw ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>

                            <label><input name="deptBasicInfoForm.bksyw" type="radio" value="${GlobalConstant.FLAG_N}" onclick="toZero('bksrs');"
                                          <c:if test="${deptBasicInfoForm.bksyw ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
                            <input type="text" class='input validate[custom[integer],min[0]]' style="width:100px;" id="bksrs" name="deptBasicInfoForm.bksrs" value="${deptBasicInfoForm.bksrs}"/>人次
                        </td>
                        <th>研究生（硕、博）：</th>
                        <td>
                            <label><input name="deptBasicInfoForm.yjsyw" type="radio" value="${GlobalConstant.FLAG_Y}"
                                          <c:if test="${deptBasicInfoForm.yjsyw ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>

                            <label><input name="deptBasicInfoForm.yjsyw" type="radio" value="${GlobalConstant.FLAG_N}" onclick="toZero('yjsrs');"
                                          <c:if test="${deptBasicInfoForm.yjsyw ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
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
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.td" style="width:200px;" value="${deptBasicInfoForm.td}"/>项
                        </td>
                        <th>一等：</th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;" name="deptBasicInfoForm.yd" value="${deptBasicInfoForm.yd}"/>项
                        </td>
                    </tr>
                    <tr>
                        <th>二等：</th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.ed" style="width:200px;" value="${deptBasicInfoForm.ed}"/>项
                        </td>
                        <th>三等：</th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;" name="deptBasicInfoForm.sd" value="${deptBasicInfoForm.sd}"/>项
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
                                          <c:if test="${deptBasicInfoForm.sszdjsxk ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;是&#12288;</label>
                            <label><input name="deptBasicInfoForm.sszdjsxk" type="radio" value="${GlobalConstant.FLAG_N}"
                                          <c:if test="${deptBasicInfoForm.sszdjsxk ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;否</label>
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
                                          <c:if test="${deptBasicInfoForm.xwpyd ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;是&#12288;</label>
                            <label><input name="deptBasicInfoForm.xwpyd" type="radio" value="${GlobalConstant.FLAG_N}"
                                          <c:if test="${deptBasicInfoForm.xwpyd ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;否</label>
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
                        <th>累计结业人本科生数：</th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.ljjyrs" style="width:100px;" value="${deptBasicInfoForm.ljjyrs}"/>人
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>--%>
        <div class="btn_info">
            <input class="btn_green" onclick="saveBaseInfo()" type="button" value="保&#12288;存"/>
        </div>
    </div>
</form>
