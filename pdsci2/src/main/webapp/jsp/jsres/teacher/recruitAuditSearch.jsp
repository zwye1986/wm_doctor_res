<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style type="text/css">
        table {
            /*     width:30em; */
            table-layout: fixed; /* 只有定义了表格的布局算法为fixed，下面td的定义才能起作用。 */
        }

        .over {
            width: 100%;
            word-break: keep-all; /* 不换行 */
            white-space: nowrap; /* 不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#startDate').datepicker();
            $('#endDate').datepicker();

            $(".viewStandard").on("mouseenter mouseleave", function () {
// 		$(".el",this).toggle();
                $(".al", this).toggle();
            }).on("mousemove", function (e) {
                $(".al", this).css({
                    top: e.pageY,
                    left: e.pageX
                });
            });
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
        function details(doctorFlow, typeId, processFlow) {
            var url = "<s:url value='/jsres/teacher/details'/>?doctorFlow=" + doctorFlow + "&typeId=" + typeId + "&processFlow=" + processFlow;
            jboxOpen(url, "审核列表", 900, 550, true);
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
        function evaluation(doctorFlow, processFlow, deptFlow, recFlow, recordFlow) {
            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_process_eval" var="key"/>
            var roleFlag = '${GlobalConstant.RES_ROLE_SCOPE_TEACHER}';
            var url = "<s:url value='/jsres/teacher/evaluationSun'/>?roleFlag=" + roleFlag + "&operUserFlow=" + doctorFlow + "&processFlow=" + processFlow + "&schDeptFlow=" + deptFlow + "&recFlow=" + recFlow + "&recordFlow=" + recordFlow;
            if(${pdfn:jsresPowerCfgMap(key) eq 'Y'})
            {
                jboxPost("<s:url value='/jsres/teacher/checkProcessEval'/>?processFlow="+processFlow,null,function(resp){
                    if(resp>0)
                    {
                        jboxTip("请先完成此科室的月度考评！");
                        return false;
                    }else{
                        jboxOpen(url, "出科考核表", 900, 550, true);
                    }
                },null,false);
            }else{
                jboxOpen(url, "出科考核表", 900, 550, true);
            }
        }
    </script>
</head>

<body>
<div class="main_hd">
    <h2 class="underline">培训数据审核</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm" action="<s:url value='/jsres/teacher/recruitAuditSearch'/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <input type="hidden" name="roleFlag" value="${param.roleFlag}">
            <table>
                <tr>
                    <td><label>姓&#12288;&#12288;名：</label>
                        <input type="text" name="userName" class="input" style="width: 100px;" value="${param.userName}">
                    </td>
                    <td><label>&#12288;轮转时间：</label>
                        <input type="text" id="startDate" name="schStartDate" value="${param.schStartDate}" class="input datepicker"
                               readonly="readonly" style="width: 100px;"/>
                        ~
                        <input type="text" id="endDate" name="schEndDate" value="${param.schEndDate}" class="input datepicker"
                               readonly="readonly" style="width: 100px;"/>
                    </td>
                    <td>
                        <label>&#12288;<input type="checkbox" value="${biaoJi}" name="biaoJi" onclick="shenHe(this)"
                                      <c:if test="${biaoJi==GlobalConstant.FLAG_Y}">checked</c:if>/>待审核</label>
                    </td>
                    <td>
                        &#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
                        &#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
                        <font style="float: right;margin-right: 40px;"><img
                                src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>"
                                style="margin-top:-5px;"/>&nbsp;待审核</font>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                       人员类型：
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                        </c:forEach>
                        &#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>

    <c:set var="preKey" value="res_${preRecTypeEnumPreTrainForm.id}_form_flag"/>
    <div style="overflow-x:auto;max-width:940px;overflow-y:auto;min-height:500px; margin-left: 30px;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="width: 80px;">姓名</th>
                <th style="width: 80px;">人员类型</th>
                <th style="width: 80px;">标准科室</th>
                <th style="width: 80px;">轮转科室</th>
                <th style="width: 170px;">轮转时间</th>
                <c:forEach items="${registryTypeEnumList}" var="registryType">
                    <c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
                    <c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
                        <th style="width: 80px;">${registryType.name}</th>
                    </c:if>
                </c:forEach>
                <th style="width: 100px;">出科审核表</th>
            </tr>
            <c:forEach items="${resDoctorSchProcess}" var="process">
                <tr>
                    <td>${process.userName}</td>
                    <td>${process.doctorTypeName}</td>
                    <td style="width: 80px;">
                        [${readSchRotationGroupMap[process.processFlow].schStageName}${readSchRotationGroupMap[process.processFlow].groupName}]${schRotationDeptMap[process.processFlow].standardDeptName}

                    </td>
                    <td>${process.schDeptName}</td>
                    <c:if test="${!empty process.schStartDate || !empty process.schEndDate}">
                        <td>${process.schStartDate}~${process.schEndDate}</td>
                    </c:if>
                    <c:if test="${!(!empty process.schStartDate || !empty process.schEndDate)}">
                        <td></td>
                    </c:if>
                    <c:forEach items="${registryTypeEnumList}" var="registryType">
                        <c:set value="res_registry_type_${registryType.id}" var="viewCfgKey"/>
                        <c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
                            <c:set value="${process.processFlow}${registryType.id}" var="preTrainMapKey"/>
                            <c:set value="${preTrainMapKey}notAudit" var="notAuditKey"/>
                            <c:if test="${not empty resRecCountMap[preTrainMapKey]}">
                                <td>
                                    <a onclick="details('${process.userFlow}','${registryType.id}','${process.processFlow}');">
                                        <c:if test="${resRecCountMap[notAuditKey]>0}">
                                            <img src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>"/>
                                        </c:if>
                                            ${resRecCountMap[preTrainMapKey]}
                                    </a>
                                </td>
                            </c:if>
                            <c:if test="${empty resRecCountMap[preTrainMapKey]}">
                                <td>-</td>
                            </c:if>
                        </c:if>
                    </c:forEach>
                    <td>
                        <a onclick="evaluation('${process.userFlow}','${process.processFlow}','${process.deptFlow}','${resRecMap[process.processFlow].recFlow}','${schRotationDeptMap[process.processFlow].recordFlow}');">
                            <c:if test="${empty resRecMap[process.processFlow]}">
                                <img src="<s:url value='/css/skin/${skinPath}/images/unchecked.png'/>"/>
                            </c:if>
                            出科考核表
                        </a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty resDoctorSchProcess}">
                <tr>
                    <td colspan="99">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(resDoctorSchProcess)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>

</body>
</html>
 