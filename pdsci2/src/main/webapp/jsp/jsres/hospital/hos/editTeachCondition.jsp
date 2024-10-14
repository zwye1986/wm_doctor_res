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
<script type="text/javascript">
    $(document).ready(function () {
        if ($("#teachingTb tr").length <= 0) {
            add('teaching');
        }
        if ($("#centerTb tr").length <= 0) {
            add('center');
        }

    });

    function saveEducationInfo() {
        if (false == $("#baseInfoForm").validationEngine("validate")) {
            return false;
        }

        jboxSubmit($("#baseInfoForm"), "<s:url value='/jsres/base/saveBase'/>?type=${GlobalConstant.TEACH_CONDITION}", function (resp) {
            if ("${GlobalConstant.SAVE_SUCCESSED}" == resp) {
                setTimeout(function () {
                    $("#submitBtn").show();
                    loadInfo('${GlobalConstant.BASIC_INFO}', '${sessionScope.currUser.orgFlow}','${sessionNumber}');
                }, 1000);
            }
        }, null, true);
    }

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
    });

    function calculate(tableName, className) {
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

    function add(tb) {
        var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
        var index = $("#" + tb + "Tb tr").length;
        cloneTr.html(cloneTr.html().htmlFormartById({"index": index}));
        $("#" + tb + "Tb").append(cloneTr);
        var a = $("#" + tb + "Tb").children(":last-child").children().next();
        $("#" + tb + "Tb").children(":last-child").children().slice(0, 1).text(index + 1);
    }

    String.prototype.htmlFormartById = function (data) {
        var html = this;
        for (var key in data) {
            var reg = new RegExp('\\{' + key + '\\}', 'g');
            html = html.replace(reg, data[key]);
        }
        return html;
    };

    function del(obj, tb) {
        $(obj).parent().parent().remove();
        var index = $("#" + tb + "Tb tr").length;
        for (let i = 0; i <= index; i++) {
            $("#" + tb + "Tb").children().slice(i, i + 1).children().slice(1, 2).text(i + 1);
        }
    }

    function uploadPdfFile(type,typeName,fileType,fileSuffix) {
        var url = "<s:url value='/jsres/doctor/uploadFile'/>?operType="+type+"&fileType="+fileType+"&fileSuffix="+fileSuffix;
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

    function uploadFile(obj) {

        var fileName = $(obj).val();
        if (fileName == "") {
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
        if ($.trim(fileName) != "" && !regStr.test(fileName)) {
            $(obj).val("");
            jboxTip("请上传&nbsp;.doc,.docx,.xls,.xlsx,.zip,.ppt,.pptx格式的文件");
            return;
        }
    }
</script>
<input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
<form id='baseInfoForm' style="position:relative;">
    <input type="hidden" name="sysOrg.orgFlow"
           value="${empty sysOrg.orgFlow?sessionScope.currUser.orgFlow:sysOrg.orgFlow}"/>
    <input type="hidden" name="flag" value="${GlobalConstant.TEACH_CONDITION}"/>
    <input type="hidden" name="resBase.sessionNumber" value="${sessionNumber}"/>
    <div class="main_bd">
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
                        <td><input type="text" class="input1 validate[required,custom[number],min[0],max[99999999],maxSize[10]]" style="width:100px;"
                                   name="baseExtInfoEducationInfo.clinicalSkillsTrainingCenterM2" value="${baseExtInfoEducationInfo.clinicalSkillsTrainingCenterM2}"/>
                        </td>
                        <th><span class="red">*</span>培训管理制度：</th>
                        <td>
                            <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.trainManageSystem" onclick="changeUrlSpanShow(this)" value="Y" <c:if test="${baseExtInfoEducationInfo.trainManageSystem eq 'Y' }">checked="checked"</c:if>/>有&nbsp;
                            <input type="radio" class="validate[required]" name="baseExtInfoEducationInfo.trainManageSystem" onclick="changeUrlSpanShow(this)" value="N" <c:if test="${baseExtInfoEducationInfo.trainManageSystem eq 'N' }">checked="checked"</c:if>/>无&nbsp;
                            <span id="trainManageSystemUrlShowSpan" style="display: ${baseExtInfoEducationInfo.trainManageSystem eq 'Y' ? '' : 'none'}">
                                <span id="trainManageSystemUrlSpan" style="display:${!empty baseExtInfoEducationInfo.trainManageSystemUrl?'':'none'} ">
                                    &nbsp; <a href="${sysCfgMap['upload_base_url']}/${baseExtInfoEducationInfo.trainManageSystemUrl}" target="_blank">查看</a>&nbsp;
                                </span>
                                <a id="trainManageSystemUrl" href="javascript:uploadPdfFile('trainManageSystemUrl','培训管理制度','', '.pdf');" style="margin-left: 2px">${empty baseExtInfoEducationInfo.trainManageSystemUrl?'':'重新'}上传</a>&nbsp;
                                <span id="trainManageSystemUrlDel" style="${empty baseExtInfoEducationInfo.trainManageSystemUrl?'display:none':''}">
                                    <img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
                                         style="cursor: pointer;" onclick="javascript:delFile('trainManageSystemUrl');" />
                                </span>
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
                                    &nbsp; <a href="${sysCfgMap['upload_base_url']}/${baseExtInfoEducationInfo.clinicalBaseTrainContractUrl}" target="_blank">查看</a>&nbsp;
                                </span>
                                <a id="clinicalBaseTrainContractUrl" href="javascript:uploadPdfFile('clinicalBaseTrainContractUrl','培训管理制度','', '.pdf');" style="margin-left: 2px">${empty baseExtInfoEducationInfo.clinicalBaseTrainContractUrl?'':'重新'}上传</a>&nbsp;
                                <span id="clinicalBaseTrainContractUrlDel" style="${empty baseExtInfoEducationInfo.clinicalBaseTrainContractUrl?'display:none':''}">
                                    <img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
                                         style="cursor: pointer;" onclick="javascript:delFile('clinicalBaseTrainContractUrl');" />
                                </span>
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
            <%--<h4>培训设施设备</h4>
            <table border="2px" cellpadding="1px" cellspacing="1px" class="grid" id="table4"
                   style="border-top-style: none;">
                <colgroup>
                    <col width="22%"/>
                    <col width="39%"/>
                    <col width="39%"/>
                </colgroup>
                <tr>
                    <th style="border-top: 1px solid #e7e7eb;">教室</th>
                    <td style="border-top: 1px solid #e7e7eb;">总面积：<input type="text" class="input1 validate[custom[number],min[0]]"
                                                       style="width:100px;" name="educationInfo.totalClassroomArea"
                                                       value="${educationInfo.totalClassroomArea}"/>&nbsp;平方米
                    </td>
                    <td style="border-top: 1px solid #e7e7eb;">间数：<input type="text" class="input1 validate[custom[integer],min[0]]"
                                          style="width:100px;" name="educationInfo.numberOfClassroom"
                                          value="${educationInfo.numberOfClassroom}"/>&nbsp;间
                    </td>
                </tr>
            </table>
            <table border="2px" cellpadding="1px" cellspacing="1px" class="grid" id="table5"
                   style="border-top-style: none;">
                <colgroup>
                    <col width="22%"/>
                    <col width="40%"/>
                    <col width="20%"/>
                    <col width="10%"/>
                    <col width="8%"/>
                </colgroup>
                <tr>
                    <td colspan="5" style="text-align: left;">电化教学设备（设备名称、数量）</td>
                </tr>
                <tr>
                    <th>序号</th>
                    <th colspan="2">设备名称&nbsp;<span style="color:red;">*</span></th>
                    <th>数量&nbsp;<span style="color:red;">*</span></th>
                    <th>
                        操作
                        <span style="float: right;padding-right: 20px">
                            <img class="opBtn" title="新增"
                                 src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="javascript:add('teaching')" />
                        </span>
                    </th>
                </tr>
                <tbody id="teachingTb">
                    <c:forEach var="teachingEquipment" items="${educationInfo.teachingEquipmentList}" varStatus="status">
                        <tr>
                            <td>${status.index+1}</td>
                            <td colspan="2" style="position: relative">
                                <input type="text" class="input validate[required]" style="width:90%;"
                                       name="educationInfo.teachingEquipmentList[${status.index}].equipmentName"
                                       value="${teachingEquipment.equipmentName}"/>
                            </td>
                            <td style="position: relative">
                                <input type="text" class="input validate[required]" style="width:90%;"
                                       name="educationInfo.teachingEquipmentList[${status.index}].equipmentNumber"
                                       value="${teachingEquipment.equipmentNumber}"/>
                            </td>
                            <td>
                                <a onclick="del(this, 'teaching');">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <tr>
                    <th style="text-align: right;">临床模拟技能训练中心总面积：</th>
                    <td colspan="4" style="text-align: left;"><input type="text" class="input1 validate[custom[number],min[0]]" style="width:100px;"
                               name="educationInfo.centerArea" value="${educationInfo.centerArea}"/>&nbsp;平方米
                    </td>
                </tr>
                <tr>
                    <td colspan="5" style="text-align: left">模拟设备种类（列举名称、型号、数量）</td>
                </tr>
                <tr>
                    <th>序号</th>
                    <th>设备名称&nbsp;<span style="color: red;">*</span></th>
                    <th>型号</th>
                    <th>数量&nbsp;<span style="color: red;">*</span></th>
                    <th>
                        操作
                        <span style="float: right;padding-right: 20px">
                            <img class="opBtn" title="新增"
                                 src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="javascript:add('center')" />
                        </span>
                    </th>
                </tr>
                <tbody id="centerTb">
                    <c:forEach var="centerEquipment" items="${educationInfo.centerEquipmentList}" varStatus="status">
                        <tr>
                            <td>${status.index+1}</td>
                            <td style="position: relative">
                                <input type="text" class="input validate[required]" style="width:90%;"
                                       name="educationInfo.centerEquipmentList[${status.index}].equipmentName"
                                       value="${centerEquipment.equipmentName}"/>
                            </td>
                            <td style="position: relative">
                                <input type="text" class="input" style="width:90%;"
                                       name="educationInfo.centerEquipmentList[${status.index}].equipmentModel"
                                       value="${centerEquipment.equipmentModel}"/>
                            </td>
                            <td style="position: relative">
                                <input type="text" class="input validate[required]" style="width:90%;"
                                       name="educationInfo.centerEquipmentList[${status.index}].equipmentNumber"
                                       value="${centerEquipment.equipmentNumber}"/>
                            </td>
                            <td>
                                <a onclick="del(this, 'center');">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <table border="2px" cellpadding="1px" cellspacing="1px" class="grid" id="table6"
                   style="border-top-style: none;">
                <colgroup>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                    <col width="25%"/>
                </colgroup>
                <tr>
                    <th style="text-align: right;">计算机数量：</th>
                    <td style="text-align: left;"><input type="text" class="input1 validate[custom[integer],min[0]]" style="width:100px;"
                               name="educationInfo.numberOfComputer" value="${educationInfo.numberOfComputer}"/>&nbsp;台
                    </td>
                    <th style="text-align: right;">
                        计算机信息检索系统与网络平台：
                    </th>
                    <td style="text-align: left;">
                        <select class='select validate[required]' style="width:100px;"
                                name="educationInfo.hasComputerSystem">
                            <option value="">请选择</option>
                            <option value="有"
                                    <c:if test="${educationInfo.hasComputerSystem eq '有' }">selected="selected"</c:if>>有
                            </option>
                            <option value="无"
                                    <c:if test="${educationInfo.hasComputerSystem eq '无' }">selected="selected"</c:if>>无
                            </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="5" style="text-align: left;">图书馆藏书</td>
                </tr>
                <tr>
                    <th style="text-align: right;">种类：</th>
                    <td style="text-align: left;">
                        <input type="text" class="input1 validate[custom[integer],min[0]]"
                            style="width:100px;" name="educationInfo.kindOfBooks" value="${educationInfo.kindOfBooks}"/>&nbsp;种
                    </td>
                    <th style="text-align: right;">
                        数量：
                    </th>
                    <td style="text-align: left">
                        <input type="text" class="input1 validate[custom[integer],min[0]]"
                            style="width:100px;" name="educationInfo.numberOfBooks" value="${educationInfo.numberOfBooks}"/>&nbsp;万册
                    </td>
                </tr>
            </table>--%>
        </div>
    </div>
    <c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id  or resBase.baseStatusId eq baseStatusEnumNotPassed.id or empty resBase.baseStatusId  }">
        <div class="btn_info">
            <input class="btn_green" style="width:100px;" onclick="saveEducationInfo();" type="button"
                   value="保&#12288;存"/>
            <input class="btn_green" style="width:100px;" onclick="hosMain('${sessionScope.currUser.orgFlow}', '')" type="button"
                   value="关&#12288;闭"/>
        </div>
    </c:if>
</form>
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
            <td><span>
                <img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
                     style="cursor: pointer;" onclick="javascript:del(this, 'teaching');" />
            </span></td>
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
            <td><span>
                <img class="opBtn" title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />"
                     style="cursor: pointer;" onclick="javascript:del(this, 'center');" />
            </span></td>
        </tr>
    </table>
</div>