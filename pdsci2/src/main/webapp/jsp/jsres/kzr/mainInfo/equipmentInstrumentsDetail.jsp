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
<script type="text/javascript"
        src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<input type="hidden" id="resBase" name="resBase" value="${resBase}"/>
<form id='BaseInfoForm' style="position:relative;">
    <input type="hidden" name="resBaseSpeDept.orgFlow"
           value="${empty baseSpeDept?sessionScope.currUser.orgFlow:baseSpeDept.orgFlow}"/>
    <input type="hidden" name="resBaseSpeDept.deptFlow" value="${deptFlow}"/>
    <input type="hidden" name="flag" value="${GlobalConstant.TRAINING}"/>
    <div class="main_bd"
            <c:if test="${isglobal eq 'Y'}"> style="position: relative;overflow-y: auto;height: 600px" </c:if>
            <c:if test="${isJoin eq 'Y'}"> style="position: relative;overflow-y: auto;height: 625px" </c:if>  >
        <div class="div_table">
            <h4>医疗设备仪器</h4>
            <c:if test="${viewFlag ne 'Y'}">
                <img src="<s:url value='/jsp/res/images/test.png'/>" onclick="editInfo('EquipmentInstruments','${deptFlow}');"
                     style="cursor: pointer;height: 20px;width: 20px;float: right;margin-top: -35px"/>
            </c:if>

            如某项的数量为0，可省略不填。
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
                                <th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}"
                                    style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>
                                <th colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}"
                                    style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrTwo}</th>
                            </c:if>

                            <c:if test="${t.isTd eq 'Y'}">
                                <td style="text-align: left">${t.arrOne}</td>
                                <td>${t.info}</td>
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
                                            <td>   ${t.info}  </td>
                                        </c:if>
                                    </c:if>

                                    <c:if test="${not empty t.arrTwoClo}">
                                        <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo eq '0'}">
                                            <td>   ${t.info} </td>
                                        </c:if>
                                    </c:if>

                                </c:if>

                                <c:if test="${empty t.arrOneClo}">
                                    <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
                                    <c:if test="${not empty t.arrTwoClo}">
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo eq '0'}">
                                            <td>   ${t.info} </td>
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
                                                <td>    ${t.info} </td>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty t.arrThreeClo }">
                                            <td>    ${t.info}  </td>
                                        </c:if>
                                    </c:if>
                                </c:if>

                                <c:if test="${empty t.arrOneClo}">
                                    <c:if test="${empty t.arrTwoClo}">
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                            <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                <td>  ${t.info} </td>
                                            </c:if>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${not empty t.arrTwoClo}">
                                        <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                            <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                <td>  ${t.info} </td>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${ empty t.arrThreeClo }">
                                            <td>  ${t.info}  </td>
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
                                <c:if test="${not empty t.arrTwoClo}">
                                    <th colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}"
                                        style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrTwo}</th>
                                </c:if>
                                <c:if test="${not empty t.arrThreeClo}">
                                    <th colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}"
                                        style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrThree}</th>
                                </c:if>
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
                                                <td>  ${t.info}</td>
                                            </c:if>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                <td>  ${t.info}</td>
                                                <td>  ${t.infoTwo}</td>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty t.arrThreeClo}">
                                            <td>${t.arrFour}</td>
                                            <td>${t.info}</td>
                                        </c:if>
                                    </c:if>
                                </c:if>

                                <c:if test="${empty t.arrOneClo}">
                                    <c:if test="${empty t.arrTwoClo}">
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                            <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                                <td>${t.info}</td>
                                            </c:if>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                <td>  ${t.info}</td>
                                                <td>  ${t.infoTwo}</td>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${ empty t.arrThreeClo}">
                                            <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                            <td>${t.info}</td>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${not empty t.arrTwoClo}">
                                        <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                            <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                                <td>${t.info}</td>
                                            </c:if>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                <td>  ${t.info}</td>
                                                <td>  ${t.infoTwo}</td>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${ empty t.arrThreeClo }">
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                                <td>${t.info}</td>
                                            </c:if>
                                            <c:if test="${ empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td>${t.info}</td>
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
            <c:if test="${tableAllArrNum2 eq '6'}">
                <table cellspacing="0" cellpadding="0" class="base_info" style="margin-top: 10px">
                    <colgroup>
                        <col width="15%"/>
                        <col width="15%"/>
                        <col width="5%"/>
                        <col width="25%"/>
                        <col width="20%"/>
                        <col width="20%"/>
                    </colgroup>
                    <tbody>
                    <c:forEach items="${infoList2}" var="t" varStatus="status">
                        <tr>
                            <c:if test="${t.isTh eq 'Y'}">
                                <th colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}" style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrOne}</th>
                                <th colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}" style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrTwo}</th>
                                <th style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">序号</th>
                                <th colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}" style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrThree}</th>
                                <th colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}" style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrFour}</th>
                                <th colspan="${t.arrFiveClo}" rowspan="${t.arrFiveRow}" style="<c:if test="${status.index==0}">background-color: #f4f5f9;</c:if> text-align: center;border-right: 1px solid #e7e7eb">${t.arrFive}</th>
                            </c:if>


                            <c:if test="${t.isTd eq 'Y'}">
                                <c:if test="${not empty t.arrOneClo}">
                                    <td colspan="${t.arrOneClo}" rowspan="${t.arrOneRow}" style="text-align: center">${t.arrOne}</td>
                                    <c:if test="${not empty t.arrTwoClo}">
                                        <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
                                        <td>${status.index}</td>
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                            <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                                <td>  ${t.info}</td>
                                            </c:if>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                <td>  ${t.info}</td>
                                                <c:if test="${not empty t.arrFiveClo }">
                                                    <td colspan="${t.arrFiveClo}" rowspan="${t.arrFiveRow}">${t.arrFive}</td>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty t.arrThreeClo}">
                                            <td>${t.arrFour}</td>
                                            <td>${t.info}</td>
                                        </c:if>
                                    </c:if>
                                </c:if>

                                <c:if test="${empty t.arrOneClo}">
                                    <c:if test="${empty t.arrTwoClo}">
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                            <td>${status.index}</td>
                                            <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                                <td>${t.info}</td>
                                            </c:if>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                <td>  ${t.info}</td>
                                                <c:if test="${not empty t.arrFiveClo }">
                                                    <td colspan="${t.arrFiveClo}" rowspan="${t.arrFiveRow}">${t.arrFive}</td>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${ empty t.arrThreeClo}">
                                            <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                            <td>${t.info}</td>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${not empty t.arrTwoClo}">
                                        <td colspan="${t.arrTwoClo}" rowspan="${t.arrTwoRow}">${t.arrTwo}</td>
                                        <td>${status.index}</td>
                                        <c:if test="${not empty t.arrThreeClo and t.arrThreeClo ne '0'}">
                                            <td colspan="${t.arrThreeClo}" rowspan="${t.arrThreeRow}">${t.arrThree}</td>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                                <td>${t.info}</td>
                                            </c:if>
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo eq '0'}">
                                                <td>  ${t.info}</td>
                                                <c:if test="${not empty t.arrFiveClo }">
                                                    <td colspan="${t.arrFiveClo}" rowspan="${t.arrFiveRow}">${t.arrFive}</td>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${ empty t.arrThreeClo }">
                                            <c:if test="${not empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td colspan="${t.arrFourClo}" rowspan="${t.arrFourRow}">${t.arrFour}</td>
                                                <td>${t.info}</td>
                                            </c:if>
                                            <c:if test="${ empty t.arrFourClo and t.arrFourClo ne '0'}">
                                                <td>${t.info}</td>
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
    </div>
    <%--  <c:if test="${viewFlag ne 'Y'}">
          <div class="btn_info">
              <input type="button" class="btn_green" value="编&#12288;辑"
                     onclick="editInfo('EquipmentInstruments','${deptFlow}');"></input>
          </div>
      </c:if>--%>
</form>

