<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function auditInfo(tutorFlow,viewFlag){
            var url = "<s:url value='/gyxjgl/tutor/detailInfo?role=${param.role}&tutorFlow='/>"+tutorFlow+"&viewFlag="+viewFlag;
            jboxOpen(url, viewFlag=='view'?'查看':'审核',800,600);
        }
        function backInfo(tutorFlow){
            jboxConfirm("确认退回重审？", function(){
                var url = "<s:url value='/gyxjgl/tutor/backAuditOpt?tutorFlow='/>"+tutorFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gyxjgl/tutor/tutorAudit?role=${param.role}"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>姓&#12288;&#12288;&#12288;&#12288;名：
                <input type="text" name="doctorName" value="${param.doctorName}">
                <span style="padding-left:20px;"></span>导&ensp;&#8197;师&ensp;类&ensp;&#8197;型：
                <select class="validate[required] select" name="doctorTypeId" style="width:137px;">
                    <option value="">请选择</option>
                    <option value="xsxbd" <c:if test="${param.doctorTypeId eq 'xsxbd'}">selected</c:if>>学术型博导</option>
                    <option value="xsxsd" <c:if test="${param.doctorTypeId eq 'xsxsd'}">selected</c:if>>学术型硕导</option>
                    <option value="zyxbd" <c:if test="${param.doctorTypeId eq 'zyxbd'}">selected</c:if>>专业型博导</option>
                    <option value="zyxsd" <c:if test="${param.doctorTypeId eq 'zyxsd'}">selected</c:if>>专业型硕导</option>
                </select>
                <span style="padding-left:20px;"></span>申&ensp;&#8197;请&ensp;时&ensp;&#8197;间：
                <span>
                    <input type="text" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime" value="${param.createTime}" onchange="checkTime(this)"/>
                    -- <input type="text" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="modifyTime" value="${param.modifyTime}" onchange="checkTime(this)"/>
                </span>
                <br/>
                <c:if test="${param.role eq 'pydw'}">
                    <span style=""></span>审&ensp;&#8197;核&ensp;状&ensp;&#8197;态：
                    <select name="pydwAuditStatusId" style="width:137px;" class="select">
                        <option value="">请选择</option>
                        <option value="Passing" <c:if test="${param.pydwAuditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                        <option value="Passed" <c:if test="${param.pydwAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                        <option value="UnPassed" <c:if test="${param.pydwAuditStatusId eq 'UnPassed'}">selected</c:if>>审核不通过</option>
                    </select>
                </c:if>
                <c:if test="${param.role eq 'xwk'}">
                    <span style=""></span>审&ensp;&#8197;核&ensp;状&ensp;&#8197;态：
                    <select name="xwkAuditStatusId" style="width:137px;" class="select">
                        <option value="">请选择</option>
                        <option value="Passing" <c:if test="${param.xwkAuditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                        <option value="Passed" <c:if test="${param.xwkAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                        <option value="UnPassed" <c:if test="${param.xwkAuditStatusId eq 'UnPassed'}">selected</c:if>>审核不通过</option>
                    </select>
                    <span style="padding-left:20px;"></span>二&ensp;&#8197;级&ensp;机&ensp;&#8197;构：
                    <select name="pydwOrgFlow" style="width:137px;" class="select">
                        <option value="">请选择</option>
                        <c:forEach items="${orgList}" var="org">
                            <option value="${org.orgFlow}" <c:if test="${param.pydwOrgFlow eq org.orgFlow }">selected</c:if>>${org.orgName}</option>
                        </c:forEach>
                    </select>
                    <span style="padding-left:20px;"></span>二级机构审核：
                    <select name="pydwAuditStatusId" style="width:137px;" class="select">
                        <option value="">请选择</option>
                        <option value="Passing" <c:if test="${param.pydwAuditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                        <option value="Passed" <c:if test="${param.pydwAuditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                        <option value="UnPassed" <c:if test="${param.pydwAuditStatusId eq 'UnPassed'}">selected</c:if>>审核不通过</option>
                    </select>
                </c:if>
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>导师姓名</th>
                <th>导师类型</th>
                <th>培养单位</th>
                <th>申请时间</th>
                <th>二级机构审核</th>
                <th>研究生院审核</th>
                <th style="min-width:140px;">操作</th>
            </tr>
            <c:forEach items="${tutorList}" var="tutor">
                <tr>
                <td>${tutor.doctorName}</td>
                <td>${tutor.doctorTypeName}</td>
                <td>${tutor.pydwOrgName}</td>
                <td>${tutor.applyTime}</td>
                <td>${empty tutor.pydwAuditStatusName?'--':tutor.pydwAuditStatusName}</td>
                <td>${empty tutor.xwkAuditStatusName?'--':tutor.xwkAuditStatusName}</td>
                <td>
                    <%--培养单位重审标识--%>
                    <c:set var="pydwReAuditFlag" value="${tutor.pydwAuditStatusId eq 'Passed' || tutor.pydwAuditStatusId eq 'UnPassed'}"/>
                    <%--学位科重审标识--%>
                    <c:set var="xwkReAuditFlag" value="${tutor.xwkAuditStatusId eq 'Passed' || tutor.xwkAuditStatusId eq 'UnPassed'}"/>
                    <c:if test="${param.role eq 'pydw'}">
                        <c:if test="${tutor.pydwAuditStatusId eq 'Passing'}">
                            <a onclick="auditInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">审核</a>
                        </c:if>
                        <c:if test="${pydwReAuditFlag && !xwkReAuditFlag}">
                            <a onclick="auditInfo('${tutor.doctorFlow}','reAudit');" style="cursor:pointer;color:blue;">重审</a>
                        </c:if>
                        <c:if test="${tutor.pydwAuditStatusId ne 'Passing'}">
                            <a onclick="auditInfo('${tutor.doctorFlow}','view');" style="cursor:pointer;color:blue;">查看</a>
                        </c:if>
                    </c:if>
                    <c:if test="${param.role eq 'xwk'}">
                        <%--学位科审核标识--%>
                        <c:set var="xwkFlag" value="${tutor.pydwAuditStatusId eq 'Passed' && tutor.xwkAuditStatusId eq 'Passing'}"/>
                        <c:if test="${xwkFlag}">
                            <a onclick="auditInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">审核</a>
                        </c:if>
                        <c:if test="${xwkReAuditFlag}">
                            <a onclick="auditInfo('${tutor.doctorFlow}','reAudit');" style="cursor:pointer;color:blue;">重审</a>
                            <%--退回，导师可重新完善提交资料并审核--%>
                            <a onclick="backInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">退回</a>
                        </c:if>
                        <c:if test="${!xwkFlag}">
                            <a onclick="auditInfo('${tutor.doctorFlow}','view');" style="cursor:pointer;color:blue;">查看</a>
                        </c:if>
                    </c:if>
                </td>
            </tr>
            </c:forEach>
        </table>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(tutorList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>