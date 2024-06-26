<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="true"/>
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
    </jsp:include>
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }
        function checkBDDate(){
            if($('#projStartTime').val() && $('#projEndTime').val() && $('#projStartTime').val() > $('#projEndTime').val()){
                jboxTip("开始时间不能计划结束时间！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step1"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333; ">无锡市医学重点学科（发展学科）合同书</font>
                    <table class="basic" style="width: 100%">
                        <tr>
                            <th colspan="2" class="theader"></th>
                        </tr>
                        <tr>
                            <th>合同书名称：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <select class="validate[required] inputText" name="contractName" style="width: 46%;padding-left: 10%" >
                                    <option value="">请选择</option>
                                    <option value="printTemeplete1"
                                            <c:if test='${resultMap.contractName eq "printTemeplete1"}'>selected</c:if>
                                    >无锡市医学发展学科合同书</option>
                                    <option value="printTemeplete2"
                                            <c:if test='${resultMap.contractName eq "printTemeplete2"}'>selected</c:if>
                                    >无锡市医学发展学科建设对象合同书</option>
                                    <option value="printTemeplete3"
                                            <c:if test='${resultMap.contractName eq "printTemeplete3"}'>selected</c:if>
                                    >无锡市医学重点学科建设合同书</option>
                                    <option value="printTemeplete4"
                                            <c:if test='${resultMap.contractName eq "printTemeplete4"}'>selected</c:if>
                                    >无锡市医学重点学科建设对象合同书</option>
                                </select>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <th>学科名称：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" class="validate[required] inputText" name="subjectName"
                                       value="<c:if test='${empty resultMap.subjectName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${! empty resultMap.subjectName}'>${resultMap.subjectName}</c:if>" style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <th>学科带头人：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" class="validate[required] inputText" name="name"
                                       style="width: 46%"
                                       value="<c:if test='${empty resultMap.name and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.name}'>${resultMap.name}</c:if>"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <th>建设单位（甲方）：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" class="validate[required] inputText"
                                       value="无锡市卫生计生委" style="width: 46%" readonly/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <th width="15%" >承担单位（乙方）：</th>
                            <td width="35%" style="text-align: left;padding-left: 10px;">
                                <input type="text" class="validate[required] inputText" name="orgName"
                                       value="<c:if test='${empty resultMap.orgName and param.view!=GlobalConstant.FLAG_Y}'>${org.orgName}</c:if><c:if test='${!empty resultMap.orgName}'>${resultMap.orgName}</c:if>"
                                       style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <th width="15%" >承担单位主管部门（丙方）：</th>
                            <td width="35%" style="text-align: left;padding-left: 10px;">
                                <input type="text" class="validate[required] inputText" name="chargeOrgName"
                                       value="<c:if test='${empty resultMap.chargeOrgName and param.view!=GlobalConstant.FLAG_Y}'>${org.chargeOrgName}</c:if><c:if test='${!empty resultMap.chargeOrgName}'>${resultMap.chargeOrgName}</c:if>"
                                       style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <th>合同起止年限：</td>
                            <td style="text-align: left;padding-left: 10px;">
                                <input type="text" class="inputText" value="2016~2020" readonly="readonly" style="width: 46%">
                                <%--<input id="projStartTime" name="beginYear"  type="text" value="<c:if test='${empty resultMap.beginYear}'>${applicationScope.sysCfgMap['srm_contract_start_time']}</c:if><c:if test='${! empty resultMap.beginYear}'>${resultMap.beginYear}</c:if>" class="inputText ctime" style="width: 160px;"  readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_contract_start_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if> onchange="checkBDDate()" />
                                ~<input id="projEndTime" name="endYear"  type="text" value="<c:if test='${empty resultMap.endYear}'>${applicationScope.sysCfgMap['srm_contract_end_time']}</c:if><c:if test='${! empty resultMap.endYear}'>${resultMap.endYear}</c:if>" class="inputText ctime" style="width: 160px;"  readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_contract_end_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if> onchange="checkBDDate()" />
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>--%>
                            </td>
                        </tr>
                        <tr>
                            <th>无锡市卫生计生委编号：</td>
                            <td style="text-align: left;padding-left: 10px;">
                                <input type="text" class="inputText" name="commissionNo" value="${proj.projNo}" readonly="readonly" style="width: 46%">
                                <%--<input id="projStartTime" name="beginYear"  type="text" value="<c:if test='${empty resultMap.beginYear}'>${applicationScope.sysCfgMap['srm_contract_start_time']}</c:if><c:if test='${! empty resultMap.beginYear}'>${resultMap.beginYear}</c:if>" class="inputText ctime" style="width: 160px;"  readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_contract_start_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if> onchange="checkBDDate()" />
                                ~<input id="projEndTime" name="endYear"  type="text" value="<c:if test='${empty resultMap.endYear}'>${applicationScope.sysCfgMap['srm_contract_end_time']}</c:if><c:if test='${! empty resultMap.endYear}'>${resultMap.endYear}</c:if>" class="inputText ctime" style="width: 160px;"  readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_contract_end_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if> onchange="checkBDDate()" />
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>--%>
                            </td>
                        </tr>
                    </table>
                </form>
                <div class="button"
                     style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
                    <%--<a href="javascript:void(0)" target="_self" class="search" onclick="nextOpt('step2')">下一步</a>--%>
                    <input id="prev" type="button" onclick="nextOpt('step2')" class="search" value="下一步"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		