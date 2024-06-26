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
                        <th>编制总床位数：</th>
                        <td><input type="text" class="input1 validate[custom[integer],min[0],max[99999999]]" style="width:100px;"
                                   name="educationInfo.bzBedCount" value="${educationInfo.bzBedCount}"/>&nbsp;张
                        </td>
                        <th>实有总床位数：</th>
                        <td><input type="text" class="input1 validate[custom[integer],min[0],max[99999999]]" style="width:100px;"
                                   name="educationInfo.sjBedCount" value="${educationInfo.sjBedCount }"/>&nbsp;张
                        </td>
                    </tr>
                    <tr>
                        <th>年收治住院病人数：</th>
                        <td><input type="text" class="input1 validate[custom[integer],min[0],max[99999999]]" style="width:100px;"
                                   name="educationInfo.yearlyNumberOfClinicalPatients"
                                   value="${educationInfo.yearlyNumberOfClinicalPatients}"/>&nbsp;人次
                        </td>
                        <th>病床使用率：</th>
                        <td><input type="text" class="input1 validate[custom[number],min[0],max[100],maxSize[9]]" style="width:100px;"
                                   name="educationInfo.bedOccupancy" value="${educationInfo.bedOccupancy}"/>&nbsp;%
                        </td>
                    </tr>
                    <tr>
                        <th>本年门诊量：</th>
                        <td><input type="text" class="input1 validate[custom[number],min[0],max[9999],maxSize[9]]" style="width:100px;"
                                   name="educationInfo.yearMzCount" value="${educationInfo.yearMzCount}"/>&nbsp;万人次
                        </td>
                        <th>本年急诊量：</th>
                        <td><input type="text" class="input1 validate[custom[number],min[0],max[9999],maxSize[9]]" style="width:100px;"
                                   name="educationInfo.yearJzCount" value="${educationInfo.yearJzCount}"/>&nbsp;万人次
                        </td>
                    </tr>
                    <tr>
                        <th>本年手术量：</th>
                        <td><input type="text" class="input1 validate[custom[integer],min[0],max[99999999]]" style="width:100px;"
                                   name="educationInfo.yearSjCount" value="${ educationInfo.yearSjCount}"/>&nbsp;台次
                        </td>
                        <th>本年出院病人数：</th>
                        <td><input type="text" class="input1 validate[custom[number],min[0],max[9999],maxSize[9]]" style="width:100px;"
                                   name="educationInfo.yearCybrCount" value="${ educationInfo.yearCybrCount}"/>&nbsp;万人次
                        </td>
                    </tr>
                    <tr>
                        <th>本年专业基地数：</th>
                        <td><input type="text" class="input1 validate[custom[integer],min[0],max[99999999]]" style="width:100px;"
                                   name="educationInfo.numberOfExistingProfessionalBases"
                                   value="${educationInfo.numberOfExistingProfessionalBases}"/>&nbsp;个
                        </td>
                        <th>3年培训容量总和：</th>
                        <td><input type="text" class="input1 validate[custom[integer],min[0],max[99999999]]" style="width:100px;"
                                   name="educationInfo.total3YearTrainingCapacity"
                                   value="${educationInfo.total3YearTrainingCapacity}"/>&nbsp;人
                        </td>
                    </tr>


                    <tr>
                        <th>本年入出院病人诊断符合率：</th>
                        <td><input type="text" class="input1 validate[custom[number],min[0]],max[100],maxSize[9]]" style="width:100px;"
                                   name="educationInfo.rcybrzdfhl" value="${educationInfo.rcybrzdfhl}"/>&nbsp;%
                        </td>
                        <th>本年住院病人治愈好转率：</th>
                        <td><input type="text" class="input1 validate[custom[number],min[0],max[100],maxSize[9]]" style="width:100px;"
                                   name="educationInfo.zybrzyhzl" value="${educationInfo.zybrzyhzl}"/>&nbsp;%
                        </td>
                    </tr>
                    <tr>
                        <th>本年住院总死亡率：</th>
                        <td><input type="text" class="input1 validate[custom[number],min[0],max[100],maxSize[9]]" style="width:100px;"
                                   name="educationInfo.zyzswl" value="${educationInfo.zyzswl}"/>&nbsp;%
                        </td>
                        <th>本年感染总发生率：</th>
                        <td><input type="text" class="input1 validate[custom[number],min[0],max[100],maxSize[9]]" style="width:100px;"
                                   name="educationInfo.grzfsl" value="${educationInfo.grzfsl}"/>&nbsp;%
                        </td>
                    </tr>
                    <tr>
                        <th>本年手术患者并发症发生率：</th>
                        <td><input type="text" class="input1 validate[custom[number],min[0],max[100],maxSize[9]]" style="width:100px;"
                                   name="educationInfo.sshzbfzfsl" value="${educationInfo.sshzbfzfsl}"/>&nbsp;%
                        </td>
                        <th>按省级卫生健康行政部门有关规定核定的培训容量总和：</th>
                        <td><input type="text" class="input1 validate[custom[integer],min[0],max[99999999]]" style="width:100px;"
                                   name="educationInfo.hdpxrlzh" value="${educationInfo.hdpxrlzh}"/>&nbsp;人
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