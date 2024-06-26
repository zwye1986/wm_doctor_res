<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
            $('#nxt').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }
    </script>
</c:if>
<div style="width: 100%;margin-top: 20px">
    <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
        <input type="hidden" id="pageName" name="pageName" value="step1"/>
        <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
        <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
        <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
        <table width="60%" class="basic" style="margin:auto;">
            <c:choose>
                <c:when test="${param.view == GlobalConstant.FLAG_Y}">
                    <tbody>
                    <tr>
                        <td width="20%" style="text-align:right">项目编号：</td>
                        <td colspan="3" width="80%">
                                ${proj.projNo}
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">项目名称：</td>
                        <td colspan="3" width="80%">
                                ${resultMap.projName}
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">项目负责人：</td>
                        <td width="30%">
                                ${resultMap.projApplyUser}
                        </td>
                        <td width="20%" style="text-align:right">联系电话：</td>
                        <td width="30%">
                                ${resultMap.applyUserPhone}
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">起止年限：</td>
                        <td colspan="3" width="80%">
                                ${resultMap.startYear}~${resultMap.endYear}
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">承担单位：</td>
                        <td colspan="3" width="80%">
                                ${resultMap.supportOrg}
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">单位地址：</td>
                        <td colspan="3" width="80%">
                                ${resultMap.orgAddress}
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">组织机构代码：</td>
                        <td width="30%">
                                ${resultMap.orgCode}
                        </td>
                        <td width="20%" style="text-align:right">邮 政 编 码：</td>
                        <td width="30%">
                                ${resultMap.postalcode}
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">开户银行、帐号：</td>
                        <td colspan="3" width="80%">
                                ${resultMap.bankAccount}
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">保证单位（丙方）：</td>
                        <td colspan="3" width="80%">
                                ${resultMap.Guarantee_unit}
                        </td>
                    </tr>
                    </tbody>
                </c:when>
                <c:otherwise>
                    <tbody>
                    <tr>
                        <td width="20%" style="text-align:right">项目编号：</td>
                        <td colspan="3" width="80%">
                            <input name="projNo" type="text" value="${proj.projNo}" readonly="readonly"
                                   class="validate[required] inputText" style="width: 84%; "/>
                            <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">项目名称：</td>
                        <td colspan="3" width="80%">
                            <input name="projName" type="text"
                                   value="<c:if test='${empty resultMap.projName and param.view != GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${!empty resultMap.projName}'>${resultMap.projName}</c:if>"
                                   class="validate[required] inputText" style="width: 84%; "/>
                            <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">项目负责人：</td>
                        <td width="30%">
                            <input name="projApplyUser" type="text"
                                   value="<c:if test='${empty resultMap.projApplyUser and param.view != GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${!empty resultMap.projApplyUser}'>${resultMap.projApplyUser}</c:if>"
                                   class="validate[required] inputText" style="width: 84%; "/>
                            <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                        </td>
                        <td width="20%" style="text-align:right">联系电话：</td>
                        <td width="30%">
                            <input name="applyUserPhone" type="text"
                                   value="<c:if test='${empty resultMap.applyUserPhone and param.view != GlobalConstant.FLAG_Y}'>${user.userPhone}</c:if><c:if test='${!empty resultMap.applyUserPhone}'>${resultMap.applyUserPhone}</c:if>"
                                   class="validate[required] inputText" style="width: 84%; "/>
                            <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">起止年限：</td>
                        <td colspan="3" width="80%">
                            <input name="startYear" type="text"
                                   value="<c:if test='${empty resultMap.startYear}'>${applicationScope.sysCfgMap['srm_contract_start_time']}</c:if><c:if test='${! empty resultMap.startYear}'>${resultMap.startYear}</c:if>"
                                   class="validate[required] inputText" readonly="readonly" style="width: 40%;"
                                   <c:if test="${empty applicationScope.sysCfgMap['srm_contract_start_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if>/>~
                            <input name="endYear" type="text"
                                   value="<c:if test='${empty resultMap.endYear}'>${applicationScope.sysCfgMap['srm_contract_end_time']}</c:if><c:if test='${! empty resultMap.endYear}'>${resultMap.endYear}</c:if>"
                                   class="validate[required] inputText" readonly="readonly" style="width: 40%; "
                                   <c:if test="${empty applicationScope.sysCfgMap['srm_contract_end_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if>/>
                            <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">承担单位：</td>
                        <td colspan="3" width="80%">
                            <input name="supportOrg" type="text"
                                   value="<c:if test='${empty resultMap.supportOrg and param.view != GlobalConstant.FLAG_Y}'>${proj.applyOrgName}</c:if><c:if test='${!empty resultMap.supportOrg}'>${resultMap.supportOrg}</c:if>"
                                   class="inputText" style="width: 84%; "/>
                            <%--<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>--%>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">单位地址：</td>
                        <td colspan="3" width="80%">
                            <input name="orgAddress" type="text"
                                   value="<c:if test='${empty resultMap.orgAddress and param.view != GlobalConstant.FLAG_Y}'>${org.orgAddress}</c:if><c:if test='${!empty resultMap.orgAddress}'>${resultMap.orgAddress}</c:if>"
                                   class="validate[required] inputText" style="width: 84%; "/>
                            <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">组织机构代码：</td>
                        <td width="30%">
                            <input name="orgCode" type="text" value="${resultMap.orgCode}"
                                   class="validate[required] inputText" style="width: 84%; "/>
                            <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                        </td>
                        <td width="20%" style="text-align:right">邮 政 编 码：</td>
                        <td width="30%">
                            <input name="postalcode" type="text" value="${resultMap.postalcode}"
                                   class="validate[required] inputText" style="width: 84%; "/>
                            <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">开户银行、帐号：</td>
                        <td colspan="3" width="80%">
                            <input name="bankAccount" type="text" value="${resultMap.bankAccount}"
                                   class="validate[required] inputText" style="width: 84%; "/>
                            <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%" style="text-align:right">保证单位（丙方）：</td>
                        <td colspan="3" width="80%">
                            <input name="Guarantee_unit" type="text" value="${resultMap.Guarantee_unit}"
                                   class="inputText" style="width: 84%; "/>
                            <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                        </td>
                    </tr>
                    </tbody>
                </c:otherwise>
            </c:choose>
        </table>
        <c:if test="${param.view != GlobalConstant.FLAG_Y}">
            <div align="center" style="margin-top: 10px">
                <input id="nxt" type="button" onclick="nextOpt('step2')" class="search" value="下一步"/>
            </div>
        </c:if>
    </form>
</div>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function selectTag(showContent, selfObj) {
            // 操作标签
            //var tag = document.getElementById("tags").getElementsByTagName("li");
            //var taglength = tag.length;
            //for (i = 0; i < taglength; i++) {
            //    tag[i].className = "";
            //}
            //$('#'+selfObj).addClass("selectTag");
            // 操作内容
            for (var i = 0; j = document.getElementById("tagContent" + i); i++) {
                j.style.display = "none";
            }
            document.getElementById(showContent).style.display = "block";
        }
    </script>
</c:if>
