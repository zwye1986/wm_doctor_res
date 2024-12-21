<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    function saveBaseInfo() {
        jboxTip("保存成功！");
        loadInfo('${GlobalConstant.DIAG_DISEASE}','${deptFlow}');
    }

    function saveNum(infoFlow,p) {
        setTimeout(function() {
            if (null == p || p==''){
                p=0;
            }
            var num = $("#"+infoFlow).val();

            var orgFlow = $("#orgFlow").val();
            var deptFlow = $("input[name='resBaseSpeDept.deptFlow']").val();
            jboxPost("<s:url value='/jsres/kzr/saveNum'/>",{infoType:"DiagDisease",sessionNumber:"${sessionNumber}",infoFlow:infoFlow,info:num,orgFlow:orgFlow,deptFlow:deptFlow,type:"dept",speFlow:"${speFlow}"},function(resp){
                // jboxTip(resp);
            },null,false);
        }, 1);
    }
    function saveNum2(infoFlow) {
        setTimeout(function() {
            if (null == p || p==''){
                p=0;
            }
            var num = $("#"+infoFlow+"2").val();

            var orgFlow = $("#orgFlow").val();
            var deptFlow = $("#deptFlow").val();
            jboxPost("<s:url value='/jsres/kzr/saveNum'/>",{infoType:"DiagDisease",sessionNumber:"${sessionNumber}",infoFlow:infoFlow,infoTwo:num,orgFlow:orgFlow,deptFlow:deptFlow,type:"dept",speFlow:"${speFlow}"},function(resp){
                // jboxTip(resp);
            },null,false);
        }, 1);
    }
</script>
<input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
<form id='BaseInfoForm' style="position:relative;">
    <input type="hidden" name="resBaseSpeDept.orgFlow" id="orgFlow"
           value="${empty baseSpeDept?sessionScope.currUser.orgFlow:baseSpeDept.orgFlow}"/>
    <input type="hidden" id="deptFlow" name="resBaseSpeDept.deptFlow" value="${deptFlow}"/>
    <input type="hidden" id="flag" name="flag" value="${GlobalConstant.DIAG_DISEASE}"/>
    <div class="main_bd"
            <c:if test="${isglobal eq 'Y'}"> style="position: relative;overflow-y: auto;height: 600px" </c:if>
            <c:if test="${isJoin eq 'Y'}"> style="position: relative;overflow-y: auto;height: 625px" </c:if> >
        <div class="div_table">
            <h4>诊疗疾病范围</h4>
            如某项的年诊治/完成例数为0，则可省略不填。
            <p>${deptInfo}</p>
            <c:if test="${tableAllArrNum eq '2'}">
                <table cellspacing="0" cellpadding="0" class="base_info">
                    <colgroup>
                        <col width="60%"/>
                        <col width="40%"/>
                    </colgroup>
                    <tbody>
                    <c:forEach items="${infoList}" var="t" varStatus="status">
                        <tr>
                            <c:if test="${t.isTh eq 'Y'}">
                                <th style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>
                                <th style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrTwo}</th>
                            </c:if>

                            <c:if test="${t.isTd eq 'Y'}">

                                <td style="text-align: left">${t.arrOne}</td>
                                <td>
                                    <input type="text" class='input validate[custom[integer]]'style="width:200px;" id="${t.recordFlow}"
                                                       value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" />
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${tableAllArrNum eq '3'}">
                <table cellspacing="0" cellpadding="0" class="base_info">
                    <colgroup>
                        <col width="30%"/>
                        <col width="40%"/>
                        <col width="30%"/>
                    </colgroup>
                    <tbody>
                    <c:forEach items="${infoList}" var="t" varStatus="status">
                        <tr>
                            <c:if test="${t.isTh eq 'Y'}">
                                <c:if test="${t.arrOneClo eq t.arrNum}">
                                    <th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
                                        style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>
                                </c:if>

                                <c:if test="${t.arrOneClo ne t.arrNum}">
                                    <th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
                                        style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>

                                    <c:if test="${empty t.arrTwoClo}">
                                        <th colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}"
                                            style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrThree}</th>
                                    </c:if>

                                </c:if>
                            </c:if>


                            <c:if test="${t.isTd eq 'Y'}">
                                <c:if test="${not empty t.arrOneClo}">
                                    <td colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
                                            <c:if test="${empty t.arrTwoClo}"> style="text-align: right" </c:if>
                                            <c:if test="${not empty t.arrTwoClo}"> style="text-align: center" </c:if>>${t.arrOne}</td>
                                    <c:if test="${empty t.arrTwoClo}">
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo eq '0'}">
                                            <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                       value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                        </c:if>
                                    </c:if>

                                    <c:if test="${not empty t.arrTwoClo}">
                                        <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo eq '0'}">
                                            <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                       value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                        </c:if>
                                    </c:if>

                                </c:if>

                                <c:if test="${empty t.arrOneClo}">
                                    <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
                                    <c:if test="${not empty t.arrTwoClo}">
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo eq '0'}">
                                            <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                       value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                        </c:if>
                                    </c:if>
                                </c:if>

                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${tableAllArrNum eq '4'}">
                <table cellspacing="0" cellpadding="0" class="base_info">
                    <colgroup>
                        <col width="20%"/>
                        <col width="30%"/>
                        <col width="30%"/>
                        <col width="20%"/>
                    </colgroup>
                    <tbody>
                        <c:forEach items="${infoList}" var="t" varStatus="status">
                            <tr>
                                <c:if test="${t.isTh eq 'Y'}">
                                    <c:if test="${t.arrOneClo eq t.arrNum}">
                                        <th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
                                            style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>
                                    </c:if>

                                    <c:if test="${t.arrOneClo ne t.arrNum}">
                                        <th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
                                            style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>

                                        <c:if test="${empty t.arrTwoClo}">
                                            <c:if test="${empty t.arrThreeClo}">
                                                <th colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}"
                                                    style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrFour}</th>
                                            </c:if>
                                            <c:if test="${not empty t.arrThreeClo}">
                                                <th colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}"
                                                    style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrThree}</th>
                                            </c:if>
                                        </c:if>

                                    </c:if>
                                </c:if>


                                <c:if test="${t.isTd eq 'Y'}">
                                    <c:if test="${not empty t.arrOneClo}">
                                        <td colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}" style="text-align: center">${t.arrOne}</td>
                                        <c:if test="${not empty t.arrTwoClo}">
                                            <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
                                            <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                                <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                                <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                    <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                               value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${empty t.arrThreeClo }">
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                           value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                            </c:if>
                                        </c:if>
                                    </c:if>

                                    <c:if test="${empty t.arrOneClo}">
                                        <c:if test="${empty t.arrTwoClo}">
                                            <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                                <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                                <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                    <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                               value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${not empty t.arrTwoClo}">
                                            <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
                                            <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                                <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                                <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                    <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                               value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${ empty t.arrThreeClo }">
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                           value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                            </c:if>
                                        </c:if>
                                    </c:if>

                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${tableAllArrNum eq '5'}">
                <table cellspacing="0" cellpadding="0" class="base_info">
                    <colgroup>
                        <col width="15%"/>
                        <col width="15%"/>
                        <col width="20%"/>
                        <col width="30%"/>
                        <col width="20%"/>
                    </colgroup>
                    <tbody>
                    <c:forEach items="${infoList}" var="t" varStatus="status">
                        <tr>
                            <c:if test="${t.isTh eq 'Y'}">
                                <th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
                                    style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>
                                <c:if test="${not empty t.arrFourClo}">
                                    <th colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}"
                                        style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrFour}</th>
                                </c:if>
                                <th colspan="${t.arrFiveClo}" rowspan="${t.arrFiveRow}"
                                    style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrFive}</th>
                            </c:if>


                            <c:if test="${t.isTd eq 'Y'}">
                                <c:if test="${not empty t.arrOneClo}">
                                    <td colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}" style="text-align: center">${t.arrOne}</td>
                                    <c:if test="${not empty t.arrTwoClo}">
                                        <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>

                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                            <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                           value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                            </c:if>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                           value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>

                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}2"
                                                           value="${t.infoTwo}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum2('${t.recordFlow}','${t.plaInfo}');" /></td>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty t.arrThreeClo}">
                                            <td>${t.arrFour}</td>
                                            <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                       value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                        </c:if>
                                    </c:if>
                                </c:if>

                                <c:if test="${empty t.arrOneClo}">
                                    <c:if test="${empty t.arrTwoClo}">
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                            <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                           value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                            </c:if>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                           value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}2"
                                                           value="${t.infoTwo}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum2('${t.recordFlow}','${t.plaInfo}');" /></td>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${ empty t.arrThreeClo}">
                                            <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                            <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                       value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${not empty t.arrTwoClo}">
                                        <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                            <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                           value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                            </c:if>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                           value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}2"
                                                           value="${t.infoTwo}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum2('${t.recordFlow}','${t.plaInfo}');" /></td>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${ empty t.arrThreeClo }">
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                           value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                            </c:if>
                                            <c:if test="${ empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td><input type="text" class='input validate[custom[integer]]' style="width:200px;" id="${t.recordFlow}"
                                                           value="${t.info}" name="diagDiseaseForm.${t.arrName}" onchange="saveNum('${t.recordFlow}','${t.plaInfo}');" /></td>
                                            </c:if>
                                        </c:if>
                                    </c:if>
                                </c:if>

                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

        </div>
        <div class="btn_info">
            <input class="btn_green" onclick="saveBaseInfo()" type="button" value="保&#12288;存"/>
        </div>
    </div>

</form>
