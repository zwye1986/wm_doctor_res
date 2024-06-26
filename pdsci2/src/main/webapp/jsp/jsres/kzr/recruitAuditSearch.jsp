<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style type="text/css">
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#startDate').datepicker();
            $('#endDate').datepicker();

            <c:forEach items="${jsResDocTypeEnumList}" var="type">
            <c:forEach items="${datas}" var="data">
            if("${data}"=="${type.id}"){
                $("#"+"${data}").attr("checked","checked");
            }
            </c:forEach>
            <c:if test="${empty datas}">
            $("#"+"${type.id}").attr("checked","checked");
            </c:if>
            </c:forEach>
        });
        function checkAll(obj){
            var f=false;
            if($(obj).attr("checked")=="checked")
            {
                f=true;
            }
            $(".docType").each(function(){
                if(f)
                {
                    $(this).attr("checked","checked");
                }else {
                    $(this).removeAttr("checked");
                }

            });
        }
        function changeAllBox(){
            if($(".docType:checked").length==$(".docType").length)
            {
                $("#all").attr("checked","checked");
            }else{
                $("#all").removeAttr("checked");
            }
        }

        function toPage(page) {
            page = page || "${param.currentPage}";
            $("#currentPage").val(page);
            var form = $("#searchForm").serialize();
            recruitAuditSearch(form);
        }
        function shenHe(obj) {
            var s = $(obj).val();
            if (s == "Y") {
                $(obj).val("");
                b = $(obj).val();
            }
            if (s == "") {
                $(obj).val("Y");
                b = $(obj).val();
            }
            var form = $("#searchForm").serialize();
            recruitAuditSearch(form);
        }
        function details(doctorFlow, processFlow) {
            var url = "<s:url value='/jsres/kzr/details'/>?doctorFlow=" + doctorFlow + "&processFlow=" + processFlow;
            jboxOpen(url, "登记数据", 900, 550, true);
        }
    </script>
</head>

<body>
<div class="main_hd">
    <h2 class="underline">培训数据查询</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" action="<s:url value='/jsres/teacher/recruitAuditSearch'/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <input type="hidden" name="roleFlag" value="${param.roleFlag}">
            姓名：<input type="text" name="doctorName" class="input" style="width: 100px;" value="${param.doctorName}">
            &#12288;
            轮转时间：
            <input type="text" id="startDate" name="schStartDate" value="${param.schStartDate}" class="input datepicker"
                   readonly="readonly" style="width: 100px;"/>
            ~
            <input type="text" id="endDate" name="schEndDate" value="${param.schEndDate}" class="input datepicker"
                   readonly="readonly" style="width: 100px;"/>
            &#12288;人员类型：
            <c:forEach items="${jsResDocTypeEnumList}" var="type">
                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
            </c:forEach>
            <br/>
            <input style="margin-top: 10px;" class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
            <%-- <label><input type="checkbox" value="${biaoJi}"  name="biaoJi" onclick="shenHe(this)" <c:if test="${biaoJi==GlobalConstant.FLAG_Y}">checked</c:if>/>&nbsp;待审核</label> --%>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid" >
        <tr>
            <th style="min-width: 80px;max-width: 80px;">姓名</th>
            <th style="min-width: 80px;max-width: 80px;">人员类型</th>
            <th style="min-width: 80px;max-width: 80px;">性别</th>
            <th style="min-width: 80px;max-width: 80px;">电话</th>
            <th style="min-width: 80px;max-width: 80px;">轮转科室</th>
            <th style="min-width: 170px;max-width: 170px;">轮转时间</th>
            <th style="min-width: 80px;max-width: 80px;">登记记录</th>
        </tr>
        <c:forEach items="${schArrangeResult}" var="arrangeResult">
            <tr>
                <td>${arrangeResult.userName}</td>
                <td>${arrangeResult.doctorTypeName}</td>
                <td>${arrangeResult.sexName}</td>
                <td>${arrangeResult.userPhone}</td>
                <td>${arrangeResult.schDeptName}</td>
                <c:if test="${not empty arrangeResult.schStartDate||not empty arrangeResult.schEndDate}">
                    <td>${arrangeResult.schStartDate}~${arrangeResult.schEndDate}</td>
                </c:if>
                <c:if test="${empty arrangeResult.schStartDate && empty arrangeResult.schEndDate}">
                    <td></td>
                </c:if>
                <td>
                    <a onclick="details('${arrangeResult.userFlow}','${arrangeResult.processFlow}');">查看</a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty schArrangeResult}">
            <tr>
                <td colspan="99">无记录</td>
            </tr>
        </c:if>
        </table>
    </div>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(schArrangeResult)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
</body>
</html>
 