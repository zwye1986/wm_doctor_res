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

        jboxPost("<s:url value='/jsres/speAdmin/saveBase'/>", $("#BaseInfoForm").serialize(), function (resp) {
            if("${GlobalConstant.SAVE_SUCCESSED}" == resp){
                setTimeout(function(){
                    $("#submitBtn").show();
                    loadInfo('${GlobalConstant.DEPT_BASIC_INFO}','${speFlow}');
                },1000);
            }
        }, null, true);
    }
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
            <h4>基本信息</h4>
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
                <tr>
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
                </tr>
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
                    <th><span style="color: red">*</span>&nbsp;本年编制总床位数：</th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]'
                               name="deptBasicInfoForm.bzzcws" style="width:200px;" value="${dataIsNull?promptMap.bzzcws:deptBasicInfoForm.bzzcws}"/>张
                    </td>
                    <th><span style="color: red">*</span>&nbsp;本年实有总床位数：</th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.syzcws" value="${dataIsNull?promptMap.syzcws:deptBasicInfoForm.syzcws}"/>张
                    </td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>&nbsp;本年收治住院病人数：</th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.nszzybrs" style="width:200px;"
                               value="${dataIsNull?promptMap.nszzybrs:deptBasicInfoForm.nszzybrs}"/>人次
                    </td>
                    <th><span style="color: red">*</span>&nbsp;本年病床使用率：</th>
                    <td>
                        <input type="text" class='input validate[custom[number],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.bcsyl" value="${dataIsNull?promptMap.bcsyl:deptBasicInfoForm.bcsyl}"/>%
                    </td>
                </tr>
                <tr>
                    <th><span style="color: red">*</span>&nbsp;本年门诊量：</th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]'
                               name="deptBasicInfoForm.nmzl" style="width:200px;" value="${dataIsNull?promptMap.nmzl:deptBasicInfoForm.nmzl}"/>人次
                    </td>
                    <th><span style="color: red">*</span>&nbsp;本年急诊量：</th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.njzl" value="${dataIsNull?promptMap.njzl:deptBasicInfoForm.njzl}"/>人次
                    </td>
                </tr>
                <tr>
                    <th>
                        <span style="color: red">*</span>
                        本年病床周转次数：
                    </th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]'
                               name="deptBasicInfoForm.bczzcs" style="width:200px;" value="${dataIsNull?promptMap.bczzcs:deptBasicInfoForm.bczzcs}"/>次
                    </td>
                    <th>
                        <span style="color: red">*</span>
                        本年平均住院日：
                    </th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.pjzyr" value="${dataIsNull?promptMap.pjzyr:deptBasicInfoForm.pjzyr}"/>天
                    </td>
                </tr>
                <tr>
                    <th>
                        <span style="color: red">*</span>
                        本年出院病人数：
                    </th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]'
                               name="deptBasicInfoForm.ncybrs" style="width:200px;" value="${dataIsNull?promptMap.ncybrs:deptBasicInfoForm.ncybrs}"/>人次
                    </td>
                    <th>
                        <span style="color: red">*</span>
                        本年急诊手术例数：
                    </th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]'
                               style="width:200px;" name="deptBasicInfoForm.njzscls"
                               value="${dataIsNull?promptMap.njzscls:deptBasicInfoForm.njzscls}"/>例次
                    </td>
                </tr>
                <tr>
                    <th>
                        <span style="color: red">*</span>
                        近三年培训总容量：
                    </th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.npxzrl"
                               style="width:200px;" value="${dataIsNull?promptMap.npxzrl:deptBasicInfoForm.npxzrl}"/>人
                    </td>
                    <th>
                        <span style="color: red">*</span>
                        本年剩余培训容量：
                    </th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.sypxrl" value="${dataIsNull?promptMap.sypxrl:deptBasicInfoForm.sypxrl}"/>人
                    </td>
                </tr>
                <c:if test="${speFlow eq '1600'}">
                <tr>
                    <th>
                        <span style="color: red">*</span>
                        产科本年分娩量：
                    </th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.cknfml"
                               style="width:200px;" value="${empty deptBasicInfoForm.cknfml?0:deptBasicInfoForm.cknfml}"/>人次
                    </td>
                    <th>
                        <span style="color: red">*</span>
                        妇科本年收治病人数：
                    </th>
                    <td>
                        <input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;"
                               name="deptBasicInfoForm.fknszbrs" value="${empty deptBasicInfoForm.fknszbrs?0:deptBasicInfoForm.fknszbrs}"/>人次
                    </td>
                </tr>
                <tr>
                    <th>
                        <span style="color: red">*</span>
                        产科本年收治病人数：
                    </th>
                    <td colspan="3">
                        <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.cknszbrs"
                               style="width:200px;" value="${empty deptBasicInfoForm.cknszbrs?0:deptBasicInfoForm.cknszbrs}"/>人次
                    </td>
                </tr>
                </c:if>
                <c:if test="${speFlow eq '1900'}">
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年麻醉总数：
                        </th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.nmzzszmk"
                                   style="width:200px;" value="${empty deptBasicInfoForm.nmzzszmk?0:deptBasicInfoForm.nmzzszmk}"/>人次
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年麻醉恢复室病人数：
                        </th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;"
                                   name="deptBasicInfoForm.mzhfsbrs" value="${empty deptBasicInfoForm.mzhfsbrs?0:deptBasicInfoForm.mzhfsbrs}"/>人次
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年疼痛门诊病人数：
                        </th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.ttmzbrs"
                                   style="width:200px;" value="${empty deptBasicInfoForm.ttmzbrs?0:deptBasicInfoForm.ttmzbrs}"/>人次
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年重症监护室收治病人数：
                        </th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;"
                                   name="deptBasicInfoForm.zzjhsszbrs" value="${empty deptBasicInfoForm.zzjhsszbrs?0:deptBasicInfoForm.zzjhsszbrs}"/>人次
                        </td>
                    </tr>
                </c:if>
                <c:if test="${speFlow eq '2000'}">
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年活检标本病例数：
                        </th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.nhjbbbls"
                                   style="width:200px;" value="${empty deptBasicInfoForm.nhjbbbls?0:deptBasicInfoForm.nhjbbbls}"/>例次
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年尸体解剖病例数：
                        </th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;"
                                   name="deptBasicInfoForm.nstjpbls" value="${empty deptBasicInfoForm.nstjpbls?0:deptBasicInfoForm.nstjpbls}"/>例次
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span style="color: red">*</span>
                            本年冰冻快速诊断量：
                        </th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' name="deptBasicInfoForm.nbdkszdl"
                                   style="width:200px;" value="${empty deptBasicInfoForm.nbdkszdl?0:deptBasicInfoForm.nbdkszdl}"/>例次
                        </td>
                        <th>
                            <span style="color: red">*</span>
                            本年细胞学检查病例数：
                        </th>
                        <td>
                            <input type="text" class='input validate[custom[integer],min[0]]' style="width:200px;"
                                   name="deptBasicInfoForm.nxbxjcbls" value="${empty deptBasicInfoForm.nxbxjcbls?0:deptBasicInfoForm.nxbxjcbls}"/>例次
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>

        <div class="div_table">
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
        </div>
    </div>
    <div class="btn_info">
        <input class="btn_green" onclick="saveBaseInfo()" type="button" value="保&#12288;存"/>
    </div>
</form>
