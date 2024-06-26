<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<c:set var="expert_notice" value="false" scope="session"></c:set>
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
    <style type="text/css">
        a:link {color: #0000FF} /* 未访问的链接 */
        a:visited {color: #7f64b5} /* 已访问的链接 */
        a:hover {color: hotpink} /* 鼠标移动到链接上 */
        /*a:active {color: #0000FF} // 选定的链接 *!*/
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <table class="xllist" width="500px;">
                <tr>
                    <th align="center" style="font-size: 16px;"><font color="red">★★★系统通知★★★</font></th>
                </tr>
                <tr>
                    <td style="font-size: 15px;text-align: left;">请点击下面链接下载项目评审须知:</td>
                </tr>
                <tr>
                    <td style="font-size: 15px;text-align: left;">&#12288;&#12288;<a  href='<s:url value="/pub/file/downFileByUrl?fileCode=srm_expert_proj_notes_file_1"/>'>${sysCfgMap["srm_expert_proj_notes_file_1"] }</a></td>
                </tr>
                <tr>
                    <td style="font-size: 15px;text-align: left;">&#12288;&#12288;<a  href='<s:url value="/pub/file/downFileByUrl?fileCode=srm_expert_proj_notes_file_2"/>'>${sysCfgMap["srm_expert_proj_notes_file_2"] }</a></td>
                </tr>
                <c:if test="${not empty sysCfgMap['srm_expert_proj_notes_file_3'] }" >
                <tr>
                    <td style="font-size: 15px;text-align: left;">&#12288;&#12288;<a  href='<s:url value="/pub/file/downFileByUrl?fileCode=srm_expert_proj_notes_file_3"/>'>${sysCfgMap["srm_expert_proj_notes_file_3"] }</a></td>
                </tr>
                </c:if>
                <c:if test="${not empty sysCfgMap['srm_expert_proj_notes_file_4'] }" >
                <tr>
                    <td style="font-size: 15px;text-align: left;">&#12288;&#12288;<a  href='<s:url value="/pub/file/downFileByUrl?fileCode=srm_expert_proj_notes_file_4"/>'>${sysCfgMap["srm_expert_proj_notes_file_4"] }</a></td>
                </tr>
                </c:if>
            </table>
        </div>
    </div>
</div>
</body>
</html>