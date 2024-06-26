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
        function editInfo(tutorFlow){
            var url = "<s:url value='/gyxjgl/tutor/editTutor?tutorFlow='/>"+tutorFlow+"&role=xwk";
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"编辑",860,600);
        }
        function delInfo(tutorFlow){
            jboxConfirm("删除后账号无法恢复，是否确认？", function(){
                var url = "<s:url value='/gyxjgl/tutor/delTutor?tutorFlow='/>"+tutorFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function impExcel(){
            jboxOpen("<s:url value='/jsp/gyxjgl/tutor/impTutorTemp.jsp'/>", "导入",600,200);
        }
        function impOaDate(){
            jboxConfirm("确认对接人事系统导师数据？", function(){
                var url = "<s:url value='/gyxjgl/tutor/impOaDate'/>";
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
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
        <form id="searchForm" action="<s:url value="/gyxjgl/tutor/passedTutorList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <span style=""></span>姓&#12288;&#12288;名：
                <input type="text" name="doctorName" value="${param.doctorName}">
                <span style="padding-left:20px;"></span>导&#12288;&nbsp;师&#12288;&ensp;类&#12288;&nbsp;型：
                <select class="validate[required] select" name="doctorTypeId" style="width:137px;">
                    <option value="">请选择</option>
                    <option value="xsxbd" <c:if test="${param.doctorTypeId eq 'xsxbd'}">selected</c:if>>学术型博导</option>
                    <option value="xsxsd" <c:if test="${param.doctorTypeId eq 'xsxsd'}">selected</c:if>>学术型硕导</option>
                    <option value="zyxbd" <c:if test="${param.doctorTypeId eq 'zyxbd'}">selected</c:if>>专业型博导</option>
                    <option value="zyxsd" <c:if test="${param.doctorTypeId eq 'zyxsd'}">selected</c:if>>专业型硕导</option>
                </select>
                <span style="padding-left:20px;"></span>导师通过时间：
                <span>
                    <input type="text" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime" value="${param.createTime}" onchange="checkTime(this)"/>
                    -- <input type="text" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="modifyTime" value="${param.modifyTime}" onchange="checkTime(this)"/>
                </span>
                <br/>
                <span style=""></span>二级机构：
                <select name="pydwOrgFlow" style="width:137px;" class="select pydwOrgFlow">
                    <option value="">请选择</option>
                    <c:forEach items="${orgList}" var="org">
                        <option value="${org.orgFlow}" <c:if test="${param.pydwOrgFlow eq org.orgFlow }">selected</c:if>>${org.orgName}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:20px;">导师资格失效年份：</span>
                <input type="text" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" name="createUserFlow" value="${param.createUserFlow}"/>
                &ensp;<input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <input type="button" class="search" value="导&#12288;入" onclick="impExcel()"/>
                <input type="button" class="search" value="人事系统对接" onclick="impOaDate()"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>导师姓名</th>
                <th>导师类型</th>
                <th>培养单位</th>
                <th>培养单位审核</th>
                <th>研究生院审核</th>
                <th style="min-width:140px;">操作</th>
            </tr>
            <c:forEach items="${tutorList}" var="tutor">
                <tr>
                <td>${tutor.doctorName}</td>
                <td>${tutor.doctorTypeName}</td>
                <td>${tutor.pydwOrgName}</td>
                <td>${empty tutor.pydwAuditStatusName?'--':tutor.pydwAuditStatusName}</td>
                <td>${empty tutor.xwkAuditStatusName?'--':tutor.xwkAuditStatusName}</td>
                <td>
                    <c:if test="${tutor.applyFlag eq 'Y'}">
                        <a onclick="editInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                        <a onclick="delInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">删除</a>
                    </c:if>
                    <c:if test="${tutor.applyFlag ne 'Y'}">待导师申请</c:if>
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