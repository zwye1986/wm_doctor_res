<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script>
        function saveForm(canEditAppendix) {
            if ($("#editform").validationEngine("validate")) {
                autoValue($("#editform"), "autoValue");
                var url="<s:url value='/res/rec/saveRegistryForm?canEditAppendix='/>"+canEditAppendix;
                jboxSubmit($("#editform"),url, function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        parentRefresh();
                        jboxClose();
                    }
                }, function (resp) {
                    jboxTip("${GlobalConstant.SAVE_FAIL}");
                }, true);
            }
        }
        function parentRefresh() {
            window.parent.frames['mainIframe'].window.loadProcess();
        }
        function addMonitoring() {
            $("#monitoringTable").find("tr").find("td:last").each(function (i, e) {
                var afterAdd = $(e).after($(e).clone()).next("td");
                afterAdd.children().attr("checked", false);
                afterAdd.children().val("");
            });
        }
        function delMonitoring() {
            //取得已选择的CheckBox
            var checkedCheckboxs = $("input[name='monitoringIds']:checked");
            //所有的CheckBox
            var allCheckboxs = $("input[name='monitoringIds']");
            //必须有勾选才能删除
            if (checkedCheckboxs.length == 0) {
                jboxTip("请勾选要删除的！");
                return false;
            }
            //用一个数组存放已选择的CheckBox的序号
            var arrayObj = new Array();
            //外层循环对所有的CheckBox遍历
            allCheckboxs.each(function (i, e) {
                //内层循环对所有的已选择CheckBox遍历
                checkedCheckboxs.each(function (i2, e2) {
                    //当外层循环，循环到到的元素和内层循环，循环到到的元素相等时（即同一元素），
                    // 记录该已选择的CheckBox的序号
                    if (e == e2) {
                        arrayObj.push(i);
                    }
                });
            });
            var tds = $("#monitoringTable").find("tr").eq(1).find("td");
            if (checkedCheckboxs.length == allCheckboxs.length) {
                jboxTip("至少填写一组数据！");
                return false;
            }
            jboxConfirm("确认删除?", function () {
                // 取得monitoringTable下所有的tr
                var trs4deltd = $("#monitoringTable").find("tr");
                //每删除一位序号要相应减一
                var hasdeltd = 0;
                for (var i = 0; i < arrayObj.length; i++) {
                    //记录是否有删除的td
                    var temp = 0;
                    var index = arrayObj[i];
                    trs4deltd.each(function (i, e) {
                        var td4del = $(e).find("td");
                        td4del.each(function (i2, e2) {
                            if (i == 3 | i == 7 | i == 24 | i == 40 | i == 51 | i == 59) {
                                //如果是第3、7、24、40、51、59行，则从第2个td开始计算算起删除
                                if (i2 == index + 2 - hasdeltd) {
                                    $(e2).remove();
                                    temp += 1;
                                }
                            } else if (i == 0) {
                                //第0行不删除，啥也不做
                            } else {
                                //如果不是第0、3、7、24、40、51、59行，则从第1个td开始计算算起删除
                                if (i2 == index + 1 - hasdeltd) {
                                    $(e2).remove();
                                    temp += 1;
                                }
                            }
                        });
                    });
                    if (temp > 0) {
                        //如果temp大于0说明有td被删除，hasdeltd需要加1
                        hasdeltd += 1;
                    }
                }
            });
        }
        function addAccessories() {
            $("#accessoriesTb").append($("#accessoriesTemplate tr:eq(0)").clone());
        }
        function delAccessories() {
            var checkboxs = $("input[name='accessoriesIds']:checked");
            if (checkboxs.length == 0) {
                jboxTip("请勾选要删除的！");
                return false;
            }
            jboxConfirm("确认删除?", function () {
                var trs = $('#accessoriesTb').find(':checkbox:checked');
                $.each(trs, function (i, n) {
                    $(n).parent('td').parent("tr").remove();
                });
            });
        }
        function moveTr(obj) {
            jboxConfirm("确认删除？", function () {
                var flowinput = $(obj).parents("td").find("input[name='accessoriesFlow']");
                var nameinput = $(obj).parents("td").find("input[name='accessoriesName']");
                flowinput.remove();
                nameinput.remove()
                var tr = obj.parentNode;
                $("#accessoriesIsRe").val("Y");
                $(tr).after("<li><input type='file' class='validate[required]' name='accessories'/></li>");
                tr.remove();
                var trParent = obj.parentNode.parentNode;

            });
        }
        $(function(){
            <c:if test="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id || !(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR) || param.isRead}">
            $("#editform").find(":text,textarea").each(function(){
                $(this).attr("readonly","readonly");
            });
            $("#editform select").each(function(){
                var text = $(this).find(":selected").text().trim();
                var name =  $(this).attr("name");
                var value =  $(this).attr("value");
                $(this).replaceWith($('<input readonly  value="'+text+'"/><input readonly hidden name="'+name+'" value="'+text+'"/>'));
            });
            $("#editform").find(":checkbox,:radio").attr("disabled",true);
            $("#editform").find("input[name='monitoringTime']").attr("onclick","");
            </c:if>
        });
    </script>
</head>
<body>
<c:set var="canEditAppendix" value="${rec.auditStatusId eq recStatusEnumTeacherAuditY.id &&(param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR)}"/>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="editform" enctype="multipart/form-data" method="post">
                <input type="hidden" name="formFileName" value="${formFileName}"/>
                <input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
                <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
                <input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
                <input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
                <table class="basic" width="100%">
                    <tr>
                        <th colspan="4" style="text-align: left;">&#12288;登记信息</th>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>姓名：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input class="validate[required]" name="diseasePersonName" type="text"
                                       value="${formDataMap['diseasePersonName']}" style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['diseasePersonName']}</label>
                            </c:if>
                        </td>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>性别：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <select name="gender" style="width: 153px;"
                                        class="autoValue validate[required]">
                                    <option value="">请选择</option>
                                    <option value="Man"
                                            <c:if test="${'Man' eq formDataMap['gender_id']}">selected</c:if>>男
                                    </option>
                                    <option value="Woman"
                                            <c:if test="${'Woman' eq formDataMap['gender_id']}">selected</c:if>>女
                                    </option>
                                </select>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['gender']}</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>年龄：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input class="validate[required]" name="age" type="text" value="${formDataMap['age']}"
                                       style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['age']}</label>
                            </c:if>
                        </td>
                        <td style="width: 120px;text-align: right;"><font color="red">*</font>个人档案号：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <input name="personDocumentNum" type="text"
                                       value="${formDataMap['personDocumentNum']}" style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['personDocumentNum']}</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;">身份证号：</td>
                        <td colspan="3">
                            <c:if test="${!empty doctor}">
                                <input name="idNo" type="text"
                                       value="${formDataMap['idNo']}" style="width: 150px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['idNo']}</label>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 120px;text-align: right;">家庭住址：</td>
                        <td colspan="3">
                            <c:if test="${!empty doctor}">
                                <textarea style="width: 93%;height: 90%;"
                                          name="homeAddress">${formDataMap['homeAddress']}</textarea>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['homeAddress']}</label>
                            </c:if>

                        </td>
                    </tr>
                </table>
                <table id="monitoringTable" class="basic" width="100%" style="margin-top: 20px;">
                    <tr>
                        <th colspan="99" style="text-align: left;"><span style="float: left;margin-left: 5px;">&#12288;监测信息</span>
                            <c:if test="${!empty doctor}">
                                <span style="float: left;margin-left: 20px;margin-bottom: 5px;">
                                    <img class='opBtn' title='删除' style='cursor: pointer;'
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick='delMonitoring();'/>
                                    <img class="opBtn" title="新增"
                                         src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="addMonitoring();"/>
                                </span>
                            </c:if>
                        </th>
                    </tr>
                    <c:if test="${!empty doctor}">
                        <tr>
                            <td colspan="2" style="min-width: 160px;">勾选删除</td>
                            <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}"
                                       varStatus="status">
                                <td style="min-width: 100px;text-align: center;padding-left: 0px;"><input
                                        type="checkbox"
                                        name="monitoringIds"/>
                                </td>
                            </c:forEach>
                            <c:if test="${empty formDataMap['monitoringDetail']}">
                                <c:forEach begin="1" end="3" step="1">
                                    <td style="min-width: 100px;text-align: center;padding-left: 0px;"><input
                                            type="checkbox"
                                            name="monitoringIds"/>
                                    </td>
                                </c:forEach>
                            </c:if>
                        </tr>
                    </c:if>
                    <tr>
                        <td colspan="2">时间</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="monitoringTime" type="text"
                                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                           value="${monitoringMap['monitoringTime']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['monitoringTime']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="monitoringTime" type="text"
                                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td rowspan="4" style="min-width: 35px;">症状</td>
                        <td style="min-width: 140px;">多饮多食多尿</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="polydipsiaAndPolyuria" type="text"
                                           value="${monitoringMap['polydipsiaAndPolyuria']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['polydipsiaAndPolyuria']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="polydipsiaAndPolyuria" type="text" style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>头晕</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="dizzy" type="text"
                                           value="${monitoringMap['dizzy']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['dizzy']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="dizzy" type="text" style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>心悸</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="palpitation" type="text"
                                           value="${monitoringMap['palpitation']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['palpitation']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="palpitation" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>其他：
                            <c:if test="${!empty doctor}">
                                <input name="symptom_other_name" type="text"
                                       value="${formDataMap['symptom_other_name']}" style="width: 80px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${ formDataMap['symptom_other_name']}</label>
                            </c:if>
                        </td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="symptom_other" type="text"
                                           value="${monitoringMap['symptom_other']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['symptom_other']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="symptom_other" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td rowspan="17">体征</td>
                        <td>呼吸（次/分）</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="breathing" type="text"
                                           value="${monitoringMap['breathing']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['breathing']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="breathing" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>血压（mmHg）</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="bloodPressure" type="text"
                                           value="${monitoringMap['bloodPressure']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['bloodPressure']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="bloodPressure" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>心率（次/分）</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="heartRate" type="text"
                                           value="${monitoringMap['heartRate']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['heartRate']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="heartRate" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>体重（Kg）</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="weight" type="text"
                                           value="${monitoringMap['weight']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['weight']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="weight" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>身高（cm）</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="height" type="text"
                                           value="${monitoringMap['height']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['height']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="height" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>体重指数BMI</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="bodyMassIndex" type="text"
                                           value="${monitoringMap['bodyMassIndex']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['bodyMassIndex']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="bodyMassIndex" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>浮肿</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="edema" type="text"
                                           value="${monitoringMap['edema']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['edema']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="edema" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>肢体皮肤颜色</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="skinColor" type="text"
                                           value="${monitoringMap['skinColor']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['skinColor']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="skinColor" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>肢体皮肤温度</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="skinTemperature" type="text"
                                           value="${monitoringMap['skinTemperature']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['skinTemperature']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="skinTemperature" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>肢体皮肤痛觉</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="skinPain" type="text"
                                           value="${monitoringMap['skinPain']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['skinPain']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="skinPain" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>肢体皮肤异常感觉</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="paresthesia" type="text"
                                           value="${monitoringMap['paresthesia']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['paresthesia']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="paresthesia" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>肢体皮肤感染</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="skinInfected" type="text"
                                           value="${monitoringMap['skinInfected']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['skinInfected']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="skinInfected" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>足背动脉搏动</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="pulsationOfDorsumOfFoot" type="text"
                                           value="${monitoringMap['pulsationOfDorsumOfFoot']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['pulsationOfDorsumOfFoot']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="pulsationOfDorsumOfFoot" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>跟腱反射</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="achillesTendonReflex" type="text"
                                           value="${monitoringMap['achillesTendonReflex']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['achillesTendonReflex']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="achillesTendonReflex" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>ED</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="ED" type="text"
                                           value="${monitoringMap['ED']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['ED']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="ED" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>眼底</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="eyeground" type="text"
                                           value="${monitoringMap['eyeground']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['eyeground']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="eyeground" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>其他：
                            <c:if test="${!empty doctor}">
                                <input name="sign_other_name" type="text"
                                       value="${formDataMap['sign_other_name']}" style="width: 80px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${ formDataMap['sign_other_name']}</label>
                            </c:if>
                        </td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="sign_other" type="text"
                                           value="${monitoringMap['sign_other']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['sign_other']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="sign_other" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td rowspan="16">血液</td>
                        <td>血红蛋白</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="hemoglobin" type="text"
                                           value="${monitoringMap['hemoglobin']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['hemoglobin']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="hemoglobin" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>白细胞</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="blood_leucocyte" type="text"
                                           value="${monitoringMap['blood_leucocyte']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['blood_leucocyte']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="blood_leucocyte" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>空腹静脉血糖</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="FPG" type="text"
                                           value="${monitoringMap['FPG']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['FPG']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="FPG" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>餐后2H静脉血糖</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="a2hPBG" type="text"
                                           value="${monitoringMap['a2hPBG']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['a2hPBG']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="a2hPBG" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>空腹指血血糖</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="FBBG" type="text"
                                           value="${monitoringMap['FBBG']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['FBBG']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="FBBG" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>餐后2H指血血糖</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="P2HBG" type="text"
                                           value="${monitoringMap['P2HBG']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['P2HBG']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="P2HBG" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>糖化血红蛋白HbA1c</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="HbA1c" type="text"
                                           value="${monitoringMap['HbA1c']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['HbA1c']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="HbA1c" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>总胆固醇TC</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="totalCholesterol" type="text"
                                           value="${monitoringMap['totalCholesterol']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['totalCholesterol']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="totalCholesterol" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>高密度脂蛋白HDL</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="HDL" type="text"
                                           value="${monitoringMap['HDL']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['HDL']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="HDL" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>低密度脂蛋白LDL</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="LDL" type="text"
                                           value="${monitoringMap['LDL']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['LDL']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="LDL" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>甘油三酯TG</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="TG" type="text"
                                           value="${monitoringMap['TG']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['TG']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="TG" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>肌酐</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="creatinine" type="text"
                                           value="${monitoringMap['creatinine']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['creatinine']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="creatinine" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>血尿素氮BUN</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="BUN" type="text"
                                           value="${monitoringMap['BUN']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['BUN']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="BUN" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>钾</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="potassium" type="text"
                                           value="${monitoringMap['potassium']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['potassium']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="potassium" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>钠</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="sodium" type="text"
                                           value="${monitoringMap['sodium']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['sodium']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="sodium" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>其他：
                            <c:if test="${!empty doctor}">
                                <input name="blood_other_name" type="text"
                                       value="${formDataMap['blood_other_name']}" style="width: 80px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${ formDataMap['blood_other_name']}</label>
                            </c:if>
                        </td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="blood_other" type="text"
                                           value="${monitoringMap['blood_other']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['blood_other']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="blood_other" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td rowspan="8">尿液</td>
                        <td>白细胞</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="urine_leucocyte" type="text"
                                           value="${monitoringMap['urine_leucocyte']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['urine_leucocyte']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="urine_leucocyte" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>红细胞</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="urine_erythrocyte" type="text"
                                           value="${monitoringMap['urine_erythrocyte']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['urine_erythrocyte']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="urine_erythrocyte" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>尿蛋白</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="urineProtein" type="text"
                                           value="${monitoringMap['urineProtein']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['urineProtein']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="urineProtein" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>管型</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="tubular" type="text"
                                           value="${monitoringMap['tubular']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['tubular']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="tubular" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>糖</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="sugar" type="text"
                                           value="${monitoringMap['sugar']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['sugar']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="sugar" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>酮体</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="ketone" type="text"
                                           value="${monitoringMap['ketone']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['ketone']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="ketone" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>24小时糖定量</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="A24Hsugar" type="text"
                                           value="${monitoringMap['A24Hsugar']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['A24Hsugar']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="A24Hsugar" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>其他：
                            <c:if test="${!empty doctor}">
                                <input name="urine_other_name" type="text"
                                       value="${formDataMap['urine_other_name']}" style="width: 80px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${ formDataMap['urine_other_name']}</label>
                            </c:if>
                        </td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="urine_other" type="text"
                                           value="${monitoringMap['urine_other']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['urine_other']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="urine_other" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td colspan="2">心电图</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="electrocardiogram" type="text"
                                           value="${monitoringMap['electrocardiogram']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['electrocardiogram']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="electrocardiogram" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td colspan="2">合并症</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="complication" type="text"
                                           value="${monitoringMap['complication']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['complication']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="complication" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td colspan="2">营养师</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="dietitian" type="text"
                                           value="${monitoringMap['dietitian']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['dietitian']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="dietitian" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td rowspan="8">转诊</td>
                        <td>眼科</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="Eye" type="text"
                                           value="${monitoringMap['Eye']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['Eye']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="Eye" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>心内科</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="heartDepartment" type="text"
                                           value="${monitoringMap['heartDepartment']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['heartDepartment']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="heartDepartment" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>外科</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="surgery" type="text"
                                           value="${monitoringMap['surgery']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['surgery']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="surgery" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>肾内科</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="nephrology" type="text"
                                           value="${monitoringMap['nephrology']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['nephrology']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="nephrology" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>神经内科</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="neurology" type="text"
                                           value="${monitoringMap['neurology']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['neurology']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="neurology" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>皮肤科</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="skinDepartment" type="text"
                                           value="${monitoringMap['skinDepartment']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['skinDepartment']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="skinDepartment" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>糖尿病专科</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="diabetesSpecialty" type="text"
                                           value="${monitoringMap['diabetesSpecialty']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['diabetesSpecialty']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="diabetesSpecialty" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>其他：
                            <c:if test="${!empty doctor}">
                                <input name="referral_other_name" type="text"
                                       value="${formDataMap['referral_other_name']}" style="width: 80px;"/>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${ formDataMap['referral_other_name']}</label>
                            </c:if>
                        </td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="referral_other" type="text"
                                           value="${monitoringMap['referral_other']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['referral_other']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="referral_other" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td rowspan="5">指导</td>
                        <td>戒烟</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="quitSmoking" type="text"
                                           value="${monitoringMap['quitSmoking']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['quitSmoking']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="quitSmoking" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>饮食</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="diet" type="text"
                                           value="${monitoringMap['diet']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['diet']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="diet" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>运动</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="motion" type="text"
                                           value="${monitoringMap['motion']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['motion']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="motion" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>遵医行为</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="complianceBehavior" type="text"
                                           value="${monitoringMap['complianceBehavior']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['complianceBehavior']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="complianceBehavior" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td>心理调整</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="psychologicalAdjustment" type="text"
                                           value="${monitoringMap['psychologicalAdjustment']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['psychologicalAdjustment']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="psychologicalAdjustment" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <tr>
                        <td colspan="2">用药</td>
                        <c:forEach var="monitoringMap" items="${formDataMap['monitoringDetail']}" varStatus="status">
                            <td>
                                <c:if test="${!empty doctor}">
                                    <input name="medication" type="text"
                                           value="${monitoringMap['medication']}" style="width: 80px;"/>
                                </c:if>
                                <c:if test="${empty doctor}">
                                    <label>${ monitoringMap['medication']}</label>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${empty formDataMap['monitoringDetail']}">
                            <c:forEach begin="1" end="3" step="1">
                                <td>
                                    <input name="medication" type="text"
                                           style="width: 80px;"/>
                                </td>
                            </c:forEach>
                        </c:if>
                    </tr>
                </table>
                <table class="basic" width="100%" style="margin-top: 20px;">
                    <tr>
                        <th colspan="2" style="text-align: left;">&#12288;其他</th>
                    </tr>

                    <tr>
                        <td style="width: 120px;text-align: right;">其他：</td>
                        <td>
                            <c:if test="${!empty doctor}">
                                <textarea style="width: 93%;height: 90%;"
                                          name="monitoringOther">${formDataMap['monitoringOther']}</textarea>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['monitoringOther']}</label>
                            </c:if>

                        </td>
                    </tr>
                </table>
                <table id="accessoriesTable" class="basic" width="100%" style="margin-top: 20px;">
                    <tr>
                        <th colspan="6" style="text-align: left;">&#12288;附件信息
                            <c:if test="${!empty doctor}">
                                <span style="float: right;margin-right: 10px;margin-bottom: 5px;">
                                    <img class='opBtn' title='删除' style='cursor: pointer;'
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick='delAccessories();'/>
                                    <img class="opBtn" title="新增"
                                         src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="addAccessories();"/>
                                </span>
                            </c:if>

                        </th>
                    </tr>
                    <tbody id="accessoriesTb">
                    <c:set var="fileFlows" value="${pdfn:split(formDataMap['accessories_Flow'],',')}"/>
                    <c:set var="fileNames" value="${pdfn:split(formDataMap['accessories'],',')}"/>
                    <c:if test="${not empty formDataMap['accessories_Flow']}">
                        <c:forEach var="fileFlow" items="${fileFlows}" varStatus="status">
                            <tr>
                                <td width="4%">
                                    <input type="checkbox" name="accessoriesIds"/>
                                </td>
                                <td>
                                    <input type="text" id="accessoriesIsRe" value="Y" name="accessoriesIsRe"
                                           style="display: none;"/>
                                    <input type="text" value="${fileFlows[status.index]}" name="accessoriesFlow"
                                           style="display: none;"/>
                                    <input type="text" value="${fileNames[status.index]}" name="accessoriesName"
                                           style="display: none;"/>
                                    <ul>
                                        <li>
                                            <a href="<s:url value='/res/rec/fileDown'/>?fileFlow=${fileFlows[status.index]}">【${fileNames[status.index]}】</a><img
                                                class='opBtn' title='删除' style='cursor: pointer;'
                                                src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                                onclick='moveTr(this);'></img>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
                <p align="center">
                    <c:if test="${canEditAppendix}">
                        <input class="search" type="button" value="保&#12288;存"  onclick="saveForm('Y');"/>
                    </c:if>
                    <c:if test="${!canEditAppendix}">
                        <input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
                    </c:if>
                    <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
                </p>
            </form>
        </div>
        <div style="display: none">
            <!-- 模板 -->
            <table id="accessoriesTemplate">
                <tr>
                    <td width="4%">
                        <input type="checkbox" name="accessoriesIds"/>
                    </td>
                    <td>
                        <input type="file" class="validate[required]" name="accessories"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>