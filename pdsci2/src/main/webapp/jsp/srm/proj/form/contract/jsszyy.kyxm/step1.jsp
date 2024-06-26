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
        function doBack() {
            <c:if test="${sessionScope.projListScope ne GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            window.location.href = "<s:url value='/srm/proj/mine/process?projFlow='/>" + $("#projFlow").val();
            </c:if>
            <c:if test="${sessionScope.projListScope eq GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">
            window.location.href = "<s:url value='/srm/proj/search/recList?projFlow='/>" + $("#projFlow").val();
            </c:if>
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

                    <font style="font-size: 14px; font-weight:bold;color: #333; ">江苏省中医院科技项目合同</font>
                    <table class="basic" style="width: 100%">
                        <tr>
                            <th colspan="2" class="theader"></th>
                        </tr>
                        <tr>
                            <th>项目编号：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" class="validate[required] inputText" name="projNo"
                                       value="<c:if test='${empty resultMap.projNo and param.view!=GlobalConstant.FLAG_Y}'>${proj.projNo}</c:if><c:if test='${! empty resultMap.projNo}'>${resultMap.projNo}</c:if>" style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <th>项目名称：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" class="validate[required] inputText" name="projName"
                                       value="<c:if test='${empty resultMap.projName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${! empty resultMap.projName}'>${resultMap.projName}</c:if>" style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <th>项目负责人：</td>
                            <td style="text-align: left;padding-left: 10px;" colspan="3">
                                <input type="text" class="validate[required] inputText" name="userName"
                                       style="width: 46%"
                                       value="<c:if test='${empty resultMap.userName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.userName}'>${resultMap.userName}</c:if>"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <th width="15%" >手&#12288;&#12288;机：</th>
                            <td width="35%" style="text-align: left;padding-left: 10px;">
                                <input type="text" class="validate[required] inputText" name="userPhone"
                                       value="<c:if test='${empty resultMap.userPhone and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserPhone}</c:if><c:if test='${!empty resultMap.userPhone}'>${resultMap.userPhone}</c:if>"
                                       style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>

                        <tr>
                            <th>起止年限：</td>
                            <td style="text-align: left;padding-left: 10px;">
                                <input id="projStartTime" name="beginYear"  type="text" value="<c:if test='${empty resultMap.beginYear}'>${pdfn:transDateTimeForPattern(proj.projStartTime,'yyyy-MM-dd','yyyy年MM月')}</c:if><c:if test='${! empty resultMap.beginYear}'>${resultMap.beginYear}</c:if>" class="inputText ctime" style="width: 160px;"  readonly="readonly" <%--<c:if test="${empty applicationScope.sysCfgMap['srm_contract_start_time']}">--%>onClick="WdatePicker({dateFmt:'yyyy年MM月'})"<%--</c:if>--%> onchange="checkBDDate()" />
                                ~<input id="projEndTime" name="endYear"  type="text" value="<c:if test='${empty resultMap.endYear}'>${pdfn:transDateTimeForPattern(proj.projEndTime,'yyyy-MM-dd','yyyy年MM月')}</c:if><c:if test='${! empty resultMap.endYear}'>${resultMap.endYear}</c:if>" class="inputText ctime" style="width: 160px;"  readonly="readonly" <%--<c:if test="${empty applicationScope.sysCfgMap['srm_contract_end_time']}">--%>onClick="WdatePicker({dateFmt:'yyyy年MM月'})"<%--</c:if>--%> onchange="checkBDDate()" />
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                        <tr>
                            <th width="15%" >承担科室：</th>
                            <td width="35%" style="text-align: left;padding-left: 10px;">
                                <input type="text" class="validate[required] inputText" name="applyDept"
                                       value="<c:if test='${empty resultMap.applyDept and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyDeptName}</c:if><c:if test='${!empty resultMap.applyDept}'>${resultMap.applyDept}</c:if>"
                                       style="width: 46%"/>
                                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                            </td>
                        </tr>
                    </table>
                </form>
                <div class="button"
                     style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
                    <input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
                    <input id="nxt" type="button" onclick="nextOpt('step2')" class="search" value="保&#12288;存"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		