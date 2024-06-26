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
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script>
        $(document).ready(function(){
            var lastValue="";
            var value="";
            var pos=1;
            var tds = $(".resultTd");
            for(var i=0;i<tds.length;i++)
            {
                value = $(tds[i]).attr("orgFlow");
//                console.log(value)
                if(lastValue == value)
                {
                    $(tds[i]).hide();
                    $(tds[i]).siblings().last().hide();
                    var x = i-pos;
                    var rowspan = parseInt($(tds[x]).attr("rowspan"))+1;
                    $(tds[x]).attr("rowspan",rowspan);
                    $(tds[x]).siblings().last().attr("rowspan",rowspan);
                    pos++;
                }else{
                    lastValue = value;
                    pos=1;
                }
            }
        });
        function toPage(page){
            $("#currentPage").val(page);
            jboxPostLoad("contentDiv","<s:url value="/osca/examineInfo/orgInfoList"/>",$("#searchForm").serialize(),true);
        }

        function exportInfo(){
            var url = "<s:url value='/osca/examineInfo/exportOrgInfoList'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content" id="contentDiv">
        <div class="queryDiv">
        <form id="searchForm" action="<s:url value="/osca/examineInfo/orgInfoList"/>" method="post">
            <%--<div class="choseDivNewStyle" style="min-width: 1100px;">--%>
                <input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>
                <%--<table class="basic" style="width:100%;border:0px;margin:10px 0px;">--%>
                    <%--<tr>--%>
                        <%--<td style="border:0px;">--%>
                <div class="inputDiv">
                    <label class="qlable">年份：</label>
                    <input type="text" value="${(empty param.clinicalYear)?thisYear:param.clinicalYear}" name="clinicalYear" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="qtext">
                </div>
                <%--<div class="inputDiv">--%>
                    <%--<label class="qlable">考核类型：</label>--%>
                    <%--<select id="isLocal" name="isLocal" class="qselect">--%>
                        <%--<OPTION value="Y" <c:if test="${param.isLocal ne 'N'}">selected="selected"</c:if>>院内考核</option>--%>
                        <%--<option value="N" <c:if test="${param.isLocal eq 'N'}">selected="selected"</c:if>>结业考核</option>--%>
                    <%--</select>--%>
                <%--</div>--%>
                <div class="inputDiv">
                    <label class="qlable">培训基地：</label>
                    <input type="text" value="${param.orgName}" name="orgName" <%--placeholder="模糊查询"--%> class="qtext">
                </div>
                <input type="hidden" name="isLocal" value="${param.isLocal}">
                <div class="lastDiv" style="min-width: 180px;max-width: 180px;">
                       <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                    <input type="button" class="search" value="导&#12288;出" onclick="exportInfo()"/>
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
                <td>培训基地</td>
                <td>年份</td>
                <td>考核类型</td>
                <td>专业</td>
                <td>应考人数</td>
                <td>缺考人数</td>
                <td>通过人数</td>
                <td>不通过人数</td>
                <td>合格率</td>
                <td>总合格率</td>
            </tr>
            <c:if test="${not empty resultMapList}">
                <c:forEach items="${resultMapList}" var="result" varStatus="s">
                    <tr>
                        <td>${s.index+1}</td>
                        <td class="resultTd" orgFlow="${result.ORG_FLOW}" rowspan="1">${result.ORG_NAME}</td>
                        <td>${result.CLINICAL_YEAR}</td>
                        <td>
                            <c:if test="${result.IS_LOCAL eq 'Y'}">院内考核</c:if>
                            <c:if test="${result.IS_LOCAL eq 'N'}">结业考核</c:if>
                        </td>
                        <td>${result.SPE_NAME}</td>
                        <td>${result.TOTLENUM}</td>
                        <td>${result.NOSIGNNUM}</td>
                        <td>${result.PASSNUM}</td>
                        <td>${result.UNPASSNUM}</td>
                        <td>${result.percent}</td>
                        <td>${result.percentAll}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty resultMapList}">
                <tr><td colspan="99">暂无记录</td></tr>
            </c:if>
        </table>
        <div style="margin-top:100px;">
            <c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>

</body>
</html>
