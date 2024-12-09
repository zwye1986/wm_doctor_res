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

            <c:forEach items="${resDocTypeEnumList}" var="type">
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
            afterAuditSearch2(form);
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
            $("#currentPage").val(1);
            var form = $("#searchForm").serialize();
            afterAuditSearch2(form);
        }

        function afterAuditSearch2(form) {
            var url = "<s:url value='/jsres/kzr/afterAuditSearch'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        function evaluation(doctorFlow, processFlow, deptFlow, recFlow, recordFlow) {
            var roleFlag = '${roleId}';
            var url = "<s:url value='/jsres/teacher/evaluationSun'/>?roleFlag=" + roleFlag + "&operUserFlow=" + doctorFlow + "&processFlow=" + processFlow + "&schDeptFlow=" + deptFlow + "&recFlow=" + recFlow + "&recordFlow=" + recordFlow;
            jboxOpen(url, "出科考核表", 900, 550, true);
        }
    </script>
</head>

<body>
<div class="main_hd">
    <h2 class="underline">出科成绩审核</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" action="<s:url value='/jsres/teacher/afterAuditSearch'/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <input type="hidden" name="roleId" value="${roleId}">
            姓名：<input type="text" name="doctorName" class="input" style="width: 100px;" value="${param.doctorName}">
            &#12288;
            轮转时间：
            <input type="text" id="startDate" name="schStartDate" value="${param.schStartDate}" class="input datepicker"
                   readonly="readonly" style="width: 100px;"/>
            ~
            <input type="text" id="endDate" name="schEndDate" value="${param.schEndDate}" class="input datepicker"
                   readonly="readonly" style="width: 100px;"/>
            &#12288;<input type="checkbox" value="Y" name="biaoJi" onclick="shenHe(this)"
                           <c:if test="${biaoJi==GlobalConstant.FLAG_Y}">checked</c:if>/>待审核</label>
            &#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
            &#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
            <font style="float: right;margin-right: 40px;"><img
                    src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>"
                    style="margin-top:-5px;"/>&nbsp;待审核</font>
            <br/>
            &#12288;人员类型：
            <c:forEach items="${resDocTypeEnumList}" var="type">
                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
            </c:forEach>
            <input style="margin-top: 10px;" class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th style="min-width: 80px;max-width: 80px;">姓名</th>
            <th style="min-width: 80px;max-width: 80px;">人员类型</th>
            <th style="min-width: 80px;max-width: 80px;">轮转科室</th>
            <th style="min-width: 170px;max-width: 170px;">轮转时间</th>
            <th style="min-width: 80px;max-width: 80px;">出科审核表</th>
        </tr>
        <c:forEach items="${schArrangeResult}" var="arrangeResult">
            <tr>
                <td>${arrangeResult.userName}</td>
                <td>${arrangeResult.doctorTypeName}</td>
                <td>${arrangeResult.schDeptName}</td>
                <c:if test="${not empty arrangeResult.schStartDate||not empty arrangeResult.schEndDate}">
                    <td>${arrangeResult.schStartDate}~${arrangeResult.schEndDate}</td>
                </c:if>
                <c:if test="${empty arrangeResult.schStartDate && empty arrangeResult.schEndDate}">
                    <td></td>
                </c:if>
                <td>
                    <a onclick="evaluation('${arrangeResult.userFlow}','${arrangeResult.processFlow}','${arrangeResult.deptFlow}','${resRecMap[arrangeResult.processFlow].recFlow}','${schRotationDeptMap[arrangeResult.processFlow].recordFlow}');">
                        <c:if test="${empty resRecMap[arrangeResult.processFlow].headAuditStatusId}">
                            <img src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>"/>
                        </c:if>
                        出科考核表
                    </a>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty schArrangeResult}">
            <tr>
                <td colspan="11">无记录</td>
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
 