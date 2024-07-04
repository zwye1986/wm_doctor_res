<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <c:if test="${!param.noHead}">
        <jsp:include page="/jsp/common/htmlhead.jsp">
            <jsp:param name="basic" value="true"/>
            <jsp:param name="jbox" value="true"/>
            <jsp:param name="jquery_form" value="true"/>
            <jsp:param name="jquery_ui_tooltip" value="true"/>
            <jsp:param name="jquery_ui_combobox" value="true"/>
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
    </c:if>

    <script type="text/javascript">
        function saveForm(){
            if($("#lectureForm").validationEngine("validate")){

                jboxSubmit($("#lectureForm"),"<s:url value='/res/rec/saveRegistryForm'/>",function(resp){
                    parentRefresh();
                    jboxClose();
                },function(resp){
                    jboxTip("${GlobalConstant.SAVE_FAIL}");
                },true);
            }
        }

        function parentRefresh(){
            window.parent.frames['mainIframe'].window.loadProcess();
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="lectureForm" enctype="multipart/form-data" method="post">
                <c:set var="doctor" value="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}"/>
                <input type="hidden" name="formFileName" value="${formFileName}"/>
                <input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
                <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
                <input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
                <input type="hidden" name="operUserFlow" value="${param.operUserFlow}">

                <table class="basic" width="100%">
                    <tr>
                        <td style="width: 100px;"><font color="red">*</font>时间：</td>
                        <td >
                            <c:if test="${doctor}">
                            <input style="width: 160px;" class="validate[required]" name="lectureDate"
                                   type="text" value="${formDataMap['lectureDate']}" style="margin-right: 0px"
                                   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
                            />
                            </c:if>
                            <c:if test="${!doctor}">
                                ${formDataMap['lectureDate']}
                            </c:if>
                        </td>
                        <td style="width: 100px;"><font color="red">*</font>题目：</td>
                        <td >
                            <c:if test="${doctor}">
                            <input style="width: 160px;" class="validate[required]" name="lectureName" type="text" value="${formDataMap['lectureName']}" style="margin-right: 0px"/>
                            </c:if>
                            <c:if test="${!doctor}">
                                ${formDataMap['lectureName']}
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 100px;"><font color="red">*</font>讲者：</td>
                        <td >
                            <c:if test="${doctor}">
                            <input style="width: 160px;" class="validate[required]" name="speaker" type="text" value="${formDataMap['speaker']}" style="margin-right: 0px"/>
                            </c:if>
                            <c:if test="${!doctor}">
                                ${formDataMap['speaker']}
                            </c:if>
                        </td>
                        <td colspan="2"></td>
                    </tr>
                </table>
                <p align="center">
                    <c:if test="${doctor}">
                    <c:if test="${!(rec.auditStatusId eq recStatusEnumTeacherAuditY.id) && !param.isRead}">
                        <input class="search" type="button" value="保&#12288;存"  onclick="saveForm();"/>
                    </c:if>
                    </c:if>
                    <input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();"/>
                </p>
            </form>
        </div>
    </div>
</div>
</body>
</html>