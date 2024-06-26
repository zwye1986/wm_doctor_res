<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
    <script>
        $(document).ready(function(){
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
        });
        function toPage(page){
            $("#currentPage").val(page);
            jboxPostLoad("contentDiv","<s:url value="/osca/examineInfo/checkCityInfoBySpeList/city"/>",$("#searchForm").serialize(),true);
        }

        function exportInfo(){
            var url = "<s:url value='/osca/examineInfo/exportCityInfo/speInfo/city'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
        function linkageSubject(dictId){
            $('#trainingSpeId').val("");//清空上次展现数据
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content" id="contentDiv">
        <div class="queryDiv">
        <form id="searchForm" action="<s:url value="/osca/examineInfo/checkCityInfoBySpeList/city"/>" method="post">
            <%--<div class="choseDivNewStyle">--%>
                <input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>
                <%--<table class="basic" style="width:100%;border:0px;margin:10px 0px;">--%>
                    <%--<tr>--%>
                        <%--<td style="border:0px;">--%>
                            <div class="inputDiv">
                                <label class="qlable">年&#12288;&#12288;份：</label>
                                <input type="text"  class="qtext" value="${(empty param.clinicalYear)?thisYear:param.clinicalYear}" name="clinicalYear" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" >
                            </div>
                            <div class="inputDiv">
                                <label class="qlable">培训基地：</label>
                                <select name="orgFlow" class="qselect">
                                    <option value="">全部</option>
                                    <c:forEach items="${cityOrgList}" var="dict">
                                        <option value="${dict.ORG_FLOW}"
                                                <c:if test="${dict.ORG_FLOW eq param.orgFlow}">selected</c:if>>${dict.ORG_NAME}</option>
                                    </c:forEach>
                                </select>
                            </div>
                <div class="inputDiv">
                    培训类型：
                    <select name="trainingTypeId" class="qselect" onchange="linkageSubject(this.value)">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                            <option value="${dict.dictId}" ${param.trainingTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="inputDiv">
                    培训专业：
                    <c:set value="OscaTrainingType.${param.trainingTypeId}" var="trainingTypeClass"></c:set>
                    <select id="trainingSpeId" name="speId" class="qselect" onchange="selectSpe1()">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                            <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
                            <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                <option class="${scope.dictTypeId}" value="${scope.dictId}" ${(param.speId eq scope.dictId && trainingTypeClass eq scope.dictTypeId)?'selected':''}>${scope.dictName}</option>
                            </c:forEach>
                        </c:forEach>
                    </select>
                </div>
                            <div class="lastDiv">
                                <input type="button" class="searchInput" value="查&#12288;询" onclick="toPage(1)"/>
                            </div>
                            <div class="lastDiv">
                                <input type="button" class="searchInput" value="导&#12288;出" onclick="exportInfo()"/>
                            </div>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</div>--%>
        </form>
        </div>
        <table class="xllist" style="width:100%;">
            <tr style="background-color:#F5F5F5;">
                <td>序号</td>
                <td>专业</td>
                <td>应考人数</td>
                <td>缺考人数</td>
                <td>通过人数</td>
                <td>不通过人数</td>
                <td>合格率</td>
            </tr>
            <c:if test="${not empty checkInfoList}">
                <c:forEach items="${checkInfoList}" var="info" varStatus="i">
                    <tr>
                        <td>${i.index+1}</td>
                        <td>${info.SPE_NAME}</td>
                        <td>${info.TOTLENUM}</td>
                        <td>${info.NOSIGNNUM}</td>
                        <td>${info.PASSNUM}</td>
                        <td>${info.UNPASSNUM}</td>
                        <td>${info.percent}%</td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty checkInfoList}">
                <tr><td colspan="99">暂无记录</td></tr>
            </c:if>
        </table>
        <div style="margin-top:100px;">
            <c:set var="pageView" value="${pdfn:getPageView(checkInfoList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>

</body>
</html>
