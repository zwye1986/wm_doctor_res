<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

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
    <script type="text/javascript">
        function doAudit(fundFormFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value="/srm/paymentXZ/audit/finance?fundFormFlow="/>" + fundFormFlow, "审核", 750, 600);
        }
        function doAuditLocal(fundFormFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value="/srm/paymentXZ/audit/local?fundFormFlow="/>" + fundFormFlow, "审核", 750, 600);
        }
        function doView(fundFormFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value="/srm/payment/showPaymentAudit?fundDetailFlow="/>" + fundFormFlow, "操作信息", 800, 600);
        }
        function doPrint(fundFormFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value="/srm/paymentXZ/print?fundFormFlow="/>" + fundFormFlow, "打印", 750, 600);
        }
        function search() {
            jboxStartLoading();
            $('#searchForm').submit();
        }

    </script>
</head>
<body>
<div class="mainright" id="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/paymentXZ/auditList/${scope}"/>" method="post">
                <p>
                    &#12288;项目名称：
                    <input class="xltext" style="width: 120px" name="proj.projName" type="text"
                           value="${fundFormExt.proj.projName}"/>
                    &#12288;项目类型：
                    <select name="proj.projTypeId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumProjTypeList }" var="dict">
                            <option value="${dict.dictId }"
                                    <c:if test="${dict.dictId==fundFormExt.proj.projTypeId}">selected="selected"</c:if> >${dict.dictName }</option>
                        </c:forEach>
                    </select>
                </p>
                <p>
                    &#12288;&#12288;负责人：
                    <input class="xltext" style="width: 120px" name="proj.applyUserName" type="text"
                           value="${fundFormExt.proj.applyUserName}"/>
                    <!--
                    &#12288;&#12288;科室：
                    <input class="xltext" style="width: 120px" name="" type="text" value=""/> -->
                    &#12288;报销时间：
                    <input onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 160px;" class="ctime"
                           name="provideDateTime" type="text" value="${param.provideDateTime}"/>
                    &#12288;审核状态：
                    <select name="operStatusId" class="xlselect" style="width:100px;">
                        <option value="">全部</option>
                        <c:if test="${scope eq 'local'}">
                            <option value="${achStatusEnumSubmit.id}"
                                    <c:if test="${achStatusEnumSubmit.id==param.operStatusId}">selected="selected"</c:if> >${achStatusEnumSubmit.name }</option>
                                <option value="${achStatusEnumThirdAudit.id}"
                                        <c:if test="${achStatusEnumThirdAudit.id==param.operStatusId}">selected="selected"</c:if> >${achStatusEnumThirdAudit.name}</option>
                            <option value="${achStatusEnumFourthAudit.id}"
                                    <c:if test="${achStatusEnumFourthAudit.id==param.operStatusId}">selected="selected"</c:if> >${achStatusEnumFourthAudit.name}</option>
                            <option value="${achStatusEnumFifthAudit.id}"
                                    <c:if test="${achStatusEnumFifthAudit.id==param.operStatusId}">selected="selected"</c:if> >${achStatusEnumFifthAudit.name}</option>
                        </c:if>
                        <c:if test="${scope eq 'finance'}">
                            <c:if test="${isChief eq 'Y'}">
                                <option value="${achStatusEnumThirdAudit.id}"
                                        <c:if test="${achStatusEnumThirdAudit.id==param.operStatusId}">selected="selected"</c:if> >${achStatusEnumThirdAudit.name }</option>
                                <option value="${achStatusEnumFourthAudit.id}"
                                        <c:if test="${achStatusEnumFourthAudit.id==param.operStatusId}">selected="selected"</c:if> >${achStatusEnumFourthAudit.name}</option>
                                <option value="${achStatusEnumFifthAudit.id}"
                                        <c:if test="${achStatusEnumFifthAudit.id==param.operStatusId}">selected="selected"</c:if> >${achStatusEnumFifthAudit.name }</option>
                            </c:if>
                            <c:if test="${(isDean eq 'Y') and (isChief ne 'Y')}">
                                <option value="${achStatusEnumFourthAudit.id}"
                                        <c:if test="${achStatusEnumFourthAudit.id==param.operStatusId}">selected="selected"</c:if> >${achStatusEnumFourthAudit.name }</option>
                                <option value="${achStatusEnumFifthAudit.id}"
                                        <c:if test="${achStatusEnumFifthAudit.id==param.operStatusId}">selected="selected"</c:if> >${achStatusEnumFifthAudit.name }</option>
                            </c:if>
                        </c:if>
                    </select>
                    &#12288;
                    <input class="search" type="button" value="查&#12288;询" onclick="search()"/>
                </p>
            </form>
        </div>

        <table class="xllist">
            <tr>
                <th width="300px;">项目名称</th>
                <th width="100px;">项目类型</th>
                <th width="80px;">负责人</th>
                <th width="60px;">预算金额(元)</th>
                <th width="60px;">报销金额(元)</th>
                <th width="60px;">到账总额(元)</th>
                <th width="60px;">到账余额(元)</th>
                <th width="100px;">报销时间</th>
                <th width="80px;">审核状态</th>
                <th width="60px;">操作</th>
            </tr>
                <c:forEach items="${fundFormExtList}" var="fundFormExt">
                    <tr>
                        <td>${fundFormExt.proj.projName}</td>
                        <td>${fundFormExt.proj.projTypeName}</td>
                        <td>${fundFormExt.proj.applyUserName}</td>
                        <td>${pdfn:transMultiply(fundFormExt.projFundInfo.budgetAmount,10000)}</td>
                        <td class="money">${pdfn:transMultiply(fundFormExt.realmoney,10000)}</td>
                        <td>${pdfn:transMultiply(fundFormExt.projFundInfo.realityAmount,10000)}</td>
                        <td>${pdfn:transMultiply(fundFormExt.projFundInfo.realityBalance,10000)}</td>
                        <td>${pdfn:transDateTime(fundFormExt.provideDateTime)}</td>
                        <td>${fundFormExt.operStatusName}</td>
                        <td>
                            <c:set var="approveFlag" value="false"/>
                                <c:if test="${fundFormExt.operStatusId==achStatusEnumThirdAudit.id && isChief eq 'Y'}">
                                    <c:set var="approveFlag" value="true"/>
                                </c:if>
                                <c:if test="${fundFormExt.operStatusId==achStatusEnumFourthAudit.id && isDean eq 'Y'}">
                                    <c:set var="approveFlag" value="true"/>
                                </c:if>
                            <c:choose>
                                <c:when test="${scope == GlobalConstant.USER_LIST_FINANCE}">
                                    <c:choose>
                                        <c:when test="${approveFlag}"><a
                                                href="javascript:void(0)" onclick="doView('${fundFormExt.fundFormFlow}');">查看</a>|<a href="javascript:void(0)" onclick="doAudit('${fundFormExt.fundFormFlow}');" >审核</a></c:when>
                                        <c:otherwise><a href="javascript:void(0)"
                                                        onclick="doView('${fundFormExt.fundFormFlow}');">查看</a><c:if
                                                test="${fundFormExt.operStatusId==achStatusEnumFifthAudit.id}">|<a href="javascript:void(0)" onclick="doPrint('${fundFormExt.fundFormFlow}');" >打印</a></c:if></c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${fundFormExt.operStatusId==achStatusEnumSubmit.id}"><a
                                                href="javascript:void(0)"
                                                onclick="doAuditLocal('${fundFormExt.fundFormFlow}');">审核</a>|<a href="javascript:void(0)" onclick="doView('${fundFormExt.fundFormFlow}');">查看</a></c:when>
                                        <c:otherwise><a href="javascript:void(0)"
                                                        onclick="doView('${fundFormExt.fundFormFlow}');">查看</a><c:if
                                                test="${fundFormExt.operStatusId==achStatusEnumFifthAudit.id}">|<a href="javascript:void(0)" onclick="doPrint('${fundFormExt.fundFormFlow}');" >打印</a></c:if></c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
        </table>
    </div>
</div>
</body>
</html>