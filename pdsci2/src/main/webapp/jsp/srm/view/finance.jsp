<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
    </jsp:include>
    <%--<script type="text/javascript" defer="defer">
        function showProj(stageId){
            jboxStartLoading();
            window.location.href="<s:url value="/srm/proj/mine/projList/ky" />?projStageId="+stageId;
        }
    </script>--%>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div style="width: 100%;">
                <table  class="xllist nofix" style="width: 100%">
                    <tr>
                        <th  colspan="3" style="text-align: left;">&#12288;常用操作</th>
                    </tr>
                    <tbody>
                    <tr>
                        <td class="bs_mod viewTd" align="left" >
                            <div style="float: left;padding-top:10px;padding-bottom: 10px;">
                                <div align="left" style="padding-left: 10px;float: left;cursor: pointer;">
                                    <img  src="<s:url value="/css/skin/${skinPath}/images/index_proj_city.png" />" onclick="window.location.href='<s:url value='/srm/proj/mine/projList/ky'/>'">
                                </div>
                                <div align="left" style=";margin-top: 5px;width: 220px">
                                    <b style="font-size: 16px;"><a href="<s:url value='/srm/fund/accountsList/finance'/>">经费到账</a></b><br/>
                                    经费到账编辑
                                </div>
                            </div>
                            <div style="float: left;padding-top:10px;padding-bottom: 10px;">
                                <div align="left" style="padding-left: 10px;float: left;cursor: pointer;">
                                    <img  src="<s:url value="/css/skin/${skinPath}/images/index_proj_province.png" />" onclick="window.location.href='<s:url value="/srm/aid/proj/list"/>?isCountry=${aidProjCategoryEnumProvince.id}'">
                                </div>
                                <div align="left" style=";margin-top: 5px;width: 220px">
                                    <b style="font-size: 16px;"><a href="<s:url value="/srm/payment/auditList/finance"/>">报销添加</a></b><br/>
                                    报销添加
                                </div>
                            </div>
                            <div style="float: left;padding-top:10px;padding-bottom: 10px;">
                                <div align="left" style="padding-left: 10px;float: left;cursor: pointer;">
                                    <img  src="<s:url value="/css/skin/${skinPath}/images/index_zdxk.png" />" onclick="window.location.href='<s:url value='/srm/proj/mine/projList/xk'/>'">
                                </div>
                                <div align="left" style=";margin-top: 5px;width: 220px">
                                    <b style="font-size: 16px;"><a href="<s:url value='/srm/fund/list/local'/>">财务统计</a></b><br/>
                                    查询经费信息
                                </div>
                            </div>
                            <div style="float: left;padding-top:10px;padding-bottom: 10px;">
                                <div align="left" style="padding-left: 10px;float: left;cursor: pointer;">
                                    <img  src="<s:url value="/css/skin/${skinPath}/images/index_talent.png" />" onclick="window.location.href='<s:url value='/srm/aid/talents/list/personal'/>'">
                                </div>
                                <div align="left" style=";margin-top: 5px;width: 220px">
                                    <b style="font-size: 16px;"><a href="<s:url value='/srm/payment/reimburseAuditList'/>">报销审核</a></b><br/>
                                </div>
                            </div>
                            <div style="float: left;padding-top:10px;padding-bottom: 10px;">
                                <div align="left" style="padding-left: 10px;float: left;cursor: pointer;">
                                    <img  src="<s:url value="/css/skin/${skinPath}/images/index_user.png" />" onclick="window.location.href='<s:url value='/sys/user/view'/>'">
                                </div>
                                <div align="left" style=";margin-top: 5px;width: 220px">
                                    <b style="font-size: 16px;"><a href="<s:url value='/sys/user/view?editFlag=${GlobalConstant.FLAG_Y}'/>">个人信息</a></b><br/>
                                    基本信息、教育、工作经历
                                </div>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div style="width:100%; margin-top: 10px;">
                <table  class="xllist" width="100%">
                    <tr>
                        <th  colspan="3" style="text-align: left;">&#12288;待办事项</th>
                    </tr>
                    <c:if test="${empty resultMap_ky}">
                        <tr>
                            <td style="text-align: left;padding-left: 30px;color: red;" >
                                目前没有待办理的业务!
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty resultMap_ky.manageFeeAuditCount}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px;">
                                您有<font color="red">${resultMap_ky.manageFeeAuditCount }</font>个管理费审核待处理，请点击 <a href="<s:url value='/srm/fund/feeList/finance?isAudit=true'/>" style="color: red;">这里</a>&#12288;进入 !
                            </td>
                        </tr>
                    </c:if>

                    <c:if test="${not empty resultMap_ky.incomeAuditCount}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">
                                您有<font color="red">${resultMap_ky.incomeAuditCount }</font>个经费到账审核处理，请点击 <a href="<s:url value='/srm/fund/accountsList/finance?isAudit=true'/>" style="color: red;">这里</a>&#12288;进入！
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty resultMap_ky.dealPaymentAuditCount}">
                        <tr>
                            <td style="text-align: left;padding-left: 20px">
                                您有<font color="red">${resultMap_ky.dealPaymentAuditCount}</font>个报销审核待处理，请点击 <a href="<s:url value='/srm/payment/auditList/finance?isAudit=true'/>" style="color: red;">这里</a>&#12288;进入！
                            </td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>