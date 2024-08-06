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
            afterAuditSearch2(form);
        }

        function afterAuditSearch2(form) {
            var url = "<s:url value='/res/manager/temporaryOutSearch'/>";
            jboxStartLoading();
            jboxPost(url, form, function (resp) {
                $("#content").html(resp);
                jboxEndLoading();
            }, null, false);
        }
        function loadDetail(doctorFlow){
            var url="<s:url value='/jsres/doctorRecruit/doctorRecruitResultDetail'/>?roleId=${param.roleId}&doctorFlow="+doctorFlow;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='550px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"详细信息",1000,550);
        }
        //导出
        function exportExpert(){
            var url="<s:url value='/res/manager/exportTemporaryOut'/>";
            jboxTip("导出中…………");
            jboxExp($("#searchForm"), url);
            jboxEndLoading();
        }
    </script>
</head>

<body>
<div class="main_hd">
    <h2 class="underline">临时出科查询</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" action="<s:url value='/res/manager/temporaryOutSearch'/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <input type="hidden" name="roleId" value="${roleId}">
            <div>
                <c:if test="${roleId ne 'user'}">
                <label class="form_label" >姓名：</label>
                <input type="text" name="doctorName" class="input"  value="${param.doctorName}">
                <label style="margin-left: 15px" class="form_label" >人员类型：</label>
                <c:forEach items="${jsResDocTypeEnumList}" var="type">
                    <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                </c:forEach>
                </c:if>

                <label style="margin-left: 15px" class="form_label">审核状态：</label>
                <select name="temporaryAuditStatusId" class="select">
                    <option value="">请选择</option>
                    <option value="Auditing" <c:if test="${param.temporaryAuditStatusId eq 'Auditing'}">selected</c:if>>待审核</option>
                    <option value="Passed" <c:if test="${param.temporaryAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                    <option value="NotPassed" <c:if test="${param.temporaryAuditStatusId eq 'NotPassed'}">selected</c:if>>审核不通过</option>
                </select>
            </div>


            <div style="margin-bottom: 15px;margin-top: 15px">
                <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                <input class="btn_green" type="button" value="导&#12288;出" onclick="exportExpert();"/>
            </div>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th style="min-width: 80px;max-width: 80px;">姓名</th>
            <th style="min-width: 80px;max-width: 80px;">人员类型</th>
            <th style="min-width: 80px;max-width: 80px;">轮转科室</th>
            <th style="min-width: 170px;max-width: 170px;">轮转时间</th>
            <th style="min-width: 170px;max-width: 170px;">审核状态</th>
            <c:if test="${roleId ne 'user'}">
                 <th style="min-width: 80px;max-width: 80px;">操作</th>
            </c:if>
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
                <td>${arrangeResult.temporaryAuditStatusName}</td>
                <c:if test="${roleId ne 'user'}">
                    <td>
                        <a style="cursor: pointer"
                           onclick="loadDetail('${arrangeResult.userFlow}')"
                        >出科信息</a>
                    </td>
                </c:if>

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
 