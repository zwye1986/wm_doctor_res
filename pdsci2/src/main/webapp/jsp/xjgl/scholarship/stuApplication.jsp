<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function applyInfo(recordFlow){
            var applyTypeId = $("select[name='applyTypeId'] option:selected").val();
            if(recordFlow == "" && applyTypeId==""){
                jboxTip("请选择奖助类型后申请");
                return;
            }else if(applyTypeId=="Gjzxj" || applyTypeId=="Xyzxj"){
                jboxTip("国家助学金和学业助学金申请将自动生成");
                return;
            }
            var url = "<s:url value='/xjgl/scholarship/editApplyInfo?recordFlow='/>"+recordFlow+"&applyTypeId="+applyTypeId;
            jboxOpen(url,recordFlow==''?"奖助申请":"重新申请",800,600);
        }
        function detailInfo(recordFlow){
            var url = "<s:url value='/xjgl/scholarship/detailInfo?recordFlow='/>"+recordFlow;
            jboxOpen(url, "查看",800,600);
        }
        function delInfo(recordFlow){
            jboxConfirm("确认取消申请？", function(){
                var url = "<s:url value='/xjgl/scholarship/delApplyInfo?recordFlow='/>"+recordFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function expInfo(recordFlow){
            var url = "<s:url value='/xjgl/scholarship/exportWord?recordFlow='/>"+recordFlow;
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
        function printInfo(recordFlow){
            var url = '<s:url value="/xjgl/scholarship/printAward"/>?recordFlow='+recordFlow;
            jboxStartLoading();
            jboxPost(url, null, function(data) {
                $("#printDiv").html(data);
                setTimeout(function(){
                    var newstr = $("#printDiv").html();
                    var oldstr = document.body.innerHTML;
                    document.body.innerHTML =newstr;
                    window.print();
                    document.body.innerHTML = oldstr;
                    jboxEndLoading();
                    return false;
                },3000);
            },null,false);
        }
    </script>
</head>
<body style="overflow:visible;">
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/xjgl/scholarship/stuApplication"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>奖助类型：
                <select class="validate[required] select" name="applyTypeId" style="width:137px;">
                    <option value="">请选择</option>
                    <c:forEach items="${scholarshipTypeEnumList}" var="ship">
                        <option value="${ship.id}" ${param.applyTypeId eq ship.id?'selected':''}>${ship.name}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <input type="button" class="search" value="申&#12288;请" onclick="applyInfo('')"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>申请时间</th>
                <th>申请奖助类型</th>
                <th>申请内容</th>
                <th>导师</th>
                <th>培养单位</th>
                <th>分委会</th>
                <th>学校</th>
                <th style="min-width:130px;width:1%;">操作</th>
            </tr>
            <c:forEach items="${mainList}" var="main">
                <tr>
                    <td>${main.studentId}</td>
                    <td>${main.userName}</td>
                    <td>${main.applyTime}</td>
                    <td>${main.applyTypeName}</td>
                    <td><c:if test="${!(main.applyTypeId eq 'Gjzxj' || main.applyTypeId eq 'Xyzxj')}"><a onclick="detailInfo('${main.recordFlow}')" style="cursor:pointer;color:blue;">查看</a></c:if>
                        <c:if test="${main.applyTypeId eq 'Gjzxj' || main.applyTypeId eq 'Xyzxj'}">--</c:if>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${main.applyTypeId eq 'Gjzxj' || main.applyTypeId eq 'Xyzxj' || main.applyTypeId eq 'Zggw'}">--</c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${!empty main.doctorFlow && !empty main.secondDoctorFlow}">
                                        <span title="${main.doctorAuditAdvice}">导师一：${main.doctorAuditStatusName}</span>&ensp;<span title="${main.secondAuditAdvice}">导师二：${main.secondAuditStatusName}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${!empty main.doctorFlow}"><span title="${main.doctorAuditAdvice}">${main.doctorAuditStatusName}</span></c:if>
                                        <c:if test="${!empty main.secondDoctorFlow}"><span title="${main.secondAuditAdvice}">${main.secondAuditStatusName}</span></c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td title='${main.pydwAuditAdvice}'>${empty main.pydwAuditStatusId?'--':main.pydwAuditStatusName}</td>
                    <td title='${main.fwhAuditAdvice}'>${empty main.fwhAuditStatusId?'--':main.fwhAuditStatusName}</td>
                    <td title='${main.szkAuditAdvice}'>${empty main.szkAuditStatusId?'--':main.szkAuditStatusName}</td>
                    <td>
                        <c:if test="${!(main.applyTypeId eq 'Gjzxj' || main.applyTypeId eq 'Xyzxj')}">
                            <c:choose>
                                <c:when test="${main.applyTypeId eq 'Zggw' && !(main.szkAuditStatusId eq 'UnPassed' || main.szkAuditStatusId eq 'Passed')}"><%--申请后未被审核--%>
                                    <a onclick="delInfo('${main.recordFlow}');" style="cursor:pointer;color:blue;">取消申请</a>
                                </c:when>
                                <c:when test="${main.applyTypeId ne 'Zggw' && !(main.doctorAuditStatusId eq 'UnPassed' || main.doctorAuditStatusId eq 'Passed' || main.secondAuditStatusId eq 'UnPassed' || main.secondAuditStatusId eq 'Passed')}"><%--申请后未被审核--%>
                                    <a onclick="delInfo('${main.recordFlow}');" style="cursor:pointer;color:blue;">取消申请</a>
                                </c:when>
                                <c:when test="${main.doctorAuditStatusId eq 'UnPassed' || main.secondAuditStatusId eq 'UnPassed' || main.pydwAuditStatusId eq 'UnPassed' || main.fwhAuditStatusId eq 'UnPassed' || main.szkAuditStatusId eq 'UnPassed'}"><%--管理层有任何一方不通过--%>
                                    <a onclick="applyInfo('${main.recordFlow}');" style="cursor:pointer;color:blue;">重新申请</a>
                                    <a onclick="delInfo('${main.recordFlow}');" style="cursor:pointer;color:blue;">取消申请</a>
                                </c:when>
                                <c:when test="${main.szkAuditStatusId eq 'Passed'}"><%--思政科最终审核通过--%>
                                    <a onclick="expInfo('${main.recordFlow}');" style="cursor:pointer;color:blue;">导出</a>
                                    <c:if test="${main.applyTypeId eq 'Gjjxj' || main.applyTypeId eq 'Xyjxj'}">
                                        <a onclick="printInfo('${main.recordFlow}');" style="cursor:pointer;color:blue;">打印</a>
                                    </c:if>
                                </c:when>
                                <c:otherwise>审核中</c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:if test="${main.applyTypeId eq 'Gjzxj' || main.applyTypeId eq 'Xyzxj'}">--</c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(mainList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
<div id="printDiv" style="display: none;"></div>
</body>
</html>