<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>

    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <script type="text/javascript">
            function checkBDDate() {
                if ($('#projStartTime').val() && $('#projEndTime').val() && $('#projStartTime').val() > $('#projEndTime').val()) {
                    jboxTip("计划开始时间不能大于计划结束时间！");
                    return false;
                }
                return true;
            }
            function back() {
                history.back();
            }
            function save() {
                if (false == $("#projForm").validationEngine("validate")) {
                    return false;
                }
                if(checkBDDate()){
                    $("#saveBtn").attr("disabled", true);
                    $("#projForm").submit();
                }
            }
        </script>
    </c:if>

    <c:if test="${param.view == GlobalConstant.FLAG_Y}">
        <script type="text/javascript">
            $(function () {
                $('input').attr("readonly", "readonly");
                $("select").attr("disabled", "disabled");
                $(".ctime").removeAttr("onclick");
            });
        </script>
    </c:if>
    <style type="text/css">
        .line {
            border: none;
        }

        #table1 th {
            background: #fff;
        }

        #table2 th {
            background: #fff;
            text-align: center;
            padding: 0px;
            margin: 0px;
        }

        #table2 td {
            text-align: center;
            padding: 0px;
            margin: 0px;
        }
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <form action="<s:url value='/srm/proj/add/saveZKProjInfo'/>" method="post" id="projForm"
                  enctype="multipart/form-data" style="position: relative;">
                <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                <input type="hidden" id="pageName" name="pageName" value="step1"/>
                <div class="title1 clearfix">
                    <table width="100%" cellspacing="0" cellpadding="0">
                        <!-- 学科概况开始 -->
                        <tr>
                            <td>
                                <table width="100%" cellspacing="0" cellpadding="0" class="basic">
                                    <tr>
                                        <th colspan="4" style="text-align: left;padding-left: 15px">基本信息</th>
                                    </tr>
                                    <tbody>
                                    <tr>
                                        <td width="20%" style="text-align: right;">重点专科名称：</td>
                                        <td colspan="3" width="80%">
                                            <input name="projName" type="text" value="${proj.projName}"
                                                   class="validate[required]  inputText"
                                                   style="width: 450px; text-align: left;"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="20%" style="text-align: right;">重点专科类型：</td>
                                        <td colspan="3" width="80%">
                                            <select name="projTypeId" class="validate[required] inputText">
                                                <option value="">请选择</option>
                                                <c:choose>
                                                    <c:when test="${sessionScope.projCateScope==projCategroyEnumZk.id}">
                                                        <c:forEach var="dictEnuProfeType"
                                                                   items="${dictTypeEnumProfeTypeList}">
                                                            <option value="${dictEnuProfeType.dictId}"
                                                                    <c:if test='${proj.projTypeId==dictEnuProfeType.dictId}'>selected="selected"</c:if>>${dictEnuProfeType.dictName}</option>
                                                        </c:forEach>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="">错误了！！！</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr id="planTimeTr">
                                        <td style="text-align: right;">起止时间：</td>
                                        <td width="30%" style="text-align: left; padding-left: 10px;">
                                            <input id="projStartTime" name="projStartTime" type="text" value="${proj.projStartTime}" class="inputText ctime"
                                                   style="width: 160px;" readonly="readonly"
                                                   <c:if test="${empty proj.projStartTime}">onClick="WdatePicker({dateFmt:'yyyy-MM'})"</c:if>
                                                   onchange="checkBDDate()"/>
                                            <span class="redspan" style="padding: 0px;margin-left: 10px; margin-right: 10px;">~</span>
                                            <input id="projEndTime" name="projEndTime" type="text" value="${proj.projEndTime}" class="inputText ctime"
                                                   style="width: 160px;" readonly="readonly"
                                                   <c:if test="${empty proj.projEndTime}">onClick="WdatePicker({dateFmt:'yyyy-MM'})"</c:if>
                                                   onchange="checkBDDate()"/>
                                            <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
                                        </td>
                                    </tr>
                                    <%--<tr>
                                        <td width="20%" style="text-align: right;">专科负责人：</td>
                                        <td colspan="3" width="80%">
                                            <input name="projName" type="text" value="${projInfoMap.projName}"
                                                   class="validate[required]  inputText"
                                                   style="width: 450px; text-align: left;"/>
                                        </td>
                                    </tr>--%>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <!-- 学科概况结束 -->
                    </table>
                    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
                        <div class="button" style="width:1000px;">
                            <input class="search" type="button" id="saveBtn" onclick="save();" value="保&#12288;存"/>
                            <input class="close" type="button" value="返&#12288;回" onclick="back();"/>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>