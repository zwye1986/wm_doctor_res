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
    <style type="text/css">
        .bs_tb tbody th {
            background: #fff;
        }

        .bs_tb tbody td {
            text-align: left;
        }

        .bs_tb tbody td input {
            text-align: left;
            margin-left: 10px;
        }
    </style>
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

                    <font style="font-size: 14px; font-weight:bold;color: #333; ">临床医学中心（创新平台）建设合同书</font>
                    <table class="basic" style="width: 100%">
                        <tr>
                            <th colspan="2" class="theader"></th>
                        </tr>
                        <tr>
                            <td width="200px;" style="text-align: center">中心名称：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" class="validate[required] inputText" name="centerName"
                                       style="width: 46%"
                                       value="<c:if test='${empty resultMap.centerName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${! empty resultMap.centerName}'>${resultMap.centerName}</c:if>"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">中心主任：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" class="validate[required] inputText" name="centerDirector"
                                       value="<c:if test='${empty resultMap.centerDirector and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.centerDirector}'>${resultMap.centerDirector}</c:if>" style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">培养单位（甲方）：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" class="validate[required] inputText"
                                       value="江苏省卫生计生委" style="width: 46%" readonly/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td width="15%" style="text-align: center">所在单位（乙方）：</td>
                            <td width="35%" style="text-align: left;padding-left: 10px;">
                                <input type="text" class="validate[required] inputText" name="orgName"
                                       value="<c:if test='${empty resultMap.orgName and param.view!=GlobalConstant.FLAG_Y}'>${org.orgName}</c:if><c:if test='${!empty resultMap.orgName}'>${resultMap.orgName}</c:if>"
                                       style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td width="15%" style="text-align: center">所在单位主管部门（丙方）：</td>
                            <td width="35%" style="text-align: left;padding-left: 10px;">
                                <input type="text" class="validate[required] inputText" name="chargeOrgName"
                                       value="<c:if test='${empty resultMap.chargeOrgName and param.view!=GlobalConstant.FLAG_Y}'>${org.chargeOrgName}</c:if><c:if test='${!empty resultMap.chargeOrgName}'>${resultMap.chargeOrgName}</c:if>"
                                       style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center">起止年限：</td>
                            <td style="text-align: left;padding-left: 10px;">
                                <input type="text" class="inputText" value="2016~2020" readonly="readonly" style="width: 46%">
                                <%--<input id="projStartTime" name="beginYear"  type="text" value="<c:if test='${empty resultMap.beginYear}'>${applicationScope.sysCfgMap['srm_contract_start_time']}</c:if><c:if test='${! empty resultMap.beginYear}'>${resultMap.beginYear}</c:if>" class="inputText ctime" style="width: 160px;"  readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_contract_start_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if> onchange="checkBDDate()" />
                                ~<input id="projEndTime" name="endYear"  type="text" value="<c:if test='${empty resultMap.endYear}'>${applicationScope.sysCfgMap['srm_contract_end_time']}</c:if><c:if test='${! empty resultMap.endYear}'>${resultMap.endYear}</c:if>" class="inputText ctime" style="width: 160px;"  readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_contract_end_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if> onchange="checkBDDate()" />
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>--%>
                            </td>
                        </tr>

                        <tr>
                            <td style="text-align: center">江苏省卫生计生委编号：</td>
                            <td style="text-align: left;padding-left: 10px;">
                                <input type="text" class="inputText" name="commissionNo" readonly="readonly" value="${proj.projNo}" style="width: 46%">
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
		