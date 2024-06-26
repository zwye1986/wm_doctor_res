
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="false"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>

    <style type="text/css">
        caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}
    </style>

    <script type="text/javascript">
        function search(){
            $("#recruitInfoForm").submit();
        }

        function toPage(page)
        {
            jboxStartLoading();
            page=page||1;
            $("#currentPage").val(page);
            search()
        }


        function changeExamScore(recruitFlow) {
            jboxOpen("<s:url value='/recruit/writeExamManage/changeExamScore'/>?recruitFlow="+recruitFlow, "修改考试成绩", 500,160);
        }
        function importScore() {
            jboxOpen("<s:url value='/recruit/writeExamManage/importScore'/>", "导入考试成绩", 500,210);
        }
        function auditWriteExam(recruitFlow) {
            var url = "<s:url value='/recruit/writeExamManage/auditWriteExam'/>?recordStatus=Y&recruitFlow="+recruitFlow;
            jboxConfirm("确认考试通过？" , function(){
                jboxPost(url,null,function(resp){
                    if(resp == '${GlobalConstant.OPRE_SUCCESSED}'){
                        window.location.reload();
                    }else{
                        window.location.reload();
                    }
                },null,true);
            });
        }

        function notAuditWriteExam(recruitFlow) {
            var url = "<s:url value='/recruit/writeExamManage/auditWriteExam'/>?recordStatus=N&recruitFlow="+recruitFlow;
            jboxConfirm("确认考试不通过？" , function(){
                jboxPost(url,null,function(resp){
                    if(resp == '${GlobalConstant.OPRE_SUCCESSED}'){
                        window.location.reload();
                    }else{
                        window.location.reload();
                    }
                },null,true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 ">
            <form id="recruitInfoForm" action="<s:url value='/recruit/writeExamManage/main'/>" method="post">
                <input type="hidden" id="currentPage" name="currentPage"/>
                    <div class="div_search" style="padding: 10px">
                        <div style="display:inline;margin-left: 50px">
                            <p1 class="qlable">姓&#12288;&#12288;名：</p1>
                            <input id="userName" value="${param.userName}" name="userName" type="text" class="qtext"/>
                        </div>
                        <div style="display:inline;margin-left: 50px">
                            <p1 class="qlable">证件号码：</p1>
                            <input id="idNo" value="${param.idNo}" name="idNo" type="text" class="qtext"/>
                        </div>
                        <div style="display:inline;margin-left: 50px">
                            <p1 class="qlable">年&#12288;&#12288;份：</p1>
                            <input id="recruitYear" value="${param.recruitYear}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" name="recruitYear" type="text" class="qtext"/>
                        </div>
                        <br>
                        <br>
                        <div style="display:inline;margin-left: 50px">
                            <p1 class="qlable">考试时间：</p1>
                            <input type="text" value="${param.beginDate}" class="xltext" id="beginDate" name="beginDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate: '#F{$dp.$D(\'endDate\')}'})" readonly="readonly">
                            ~&#12288;
                            <input type="text" value="${param.endDate}" class="xltext" id="endDate"   name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate: '#F{$dp.$D(\'beginDate\')}'})" readonly="readonly">
                            <input style="margin-left: 40px" type="button" class="search" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>&#12288;
                            <input style="margin-left: 40px" type="button" class="search" value="导&#12288;入" class="searchInput" onclick="importScore();"/>
                        </div>
                </div>
            </form>
        </div>
        <table class="xllist" style="width: 100%;">
            <tr>
                <th width="8%" >年份</th>
                <th width="9%">姓名</th>
                <th width="10%" style="text-align: center">性别</th>
                <th width="10%" style="text-align: center">证件类型</th>
                <th width="10%" style="text-align: center">证件号码</th>
                <th width="10%" style="text-align: center">准考证号</th>
                <th width="15%" style="text-align: center">考试时间</th>
                <th width="8%" style="text-align: center">考试分数</th>
                <th width="10%" style="text-align: center">考试结果</th>
                <th width="10%" style="text-align: center">操作</th>
            </tr>
            <tbody>
            <c:forEach items="${recruitInfoExts}" var="recruitInfoExt" varStatus="seq">
                <tr>
                    <td>${recruitInfoExt.recruitYear}</td>
                    <td>${recruitInfoExt.sysUser.userName}</td>
                    <td>${recruitInfoExt.sysUser.sexName}</td>
                    <td>${recruitInfoExt.sysUser.cretTypeName}</td>
                    <td>${recruitInfoExt.sysUser.idNo}</td>
                    <td>${recruitInfoExt.ticketNumber}</td>
                    <td>${recruitInfoExt.recruitExamInfo.examStartTime}~${recruitInfoExt.recruitExamInfo.examStartTime}</td>
                    <td>${recruitInfoExt.examScore}</td>
                    <td>
                        <c:if test="${recruitInfoExt.examIsPass eq 'Y'}">
                            <font color="black">考试通过</font>
                        </c:if>
                        <c:if test="${recruitInfoExt.examIsPass eq 'N'}">
                            <font color="black">考试未通过</font>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${recruitInfoExt.interviewFlag ne 'Y'}">

                            <a href="javascript:changeExamScore('${recruitInfoExt.recruitFlow}')" style="color: blue">修改分数</a>
                            <c:if test="${empty recruitInfoExt.examIsPass}">
                                |
                                <a href="javascript:auditWriteExam('${recruitInfoExt.recruitFlow}')" style="color: blue">通过</a>
                                |
                                <a href="javascript:notAuditWriteExam('${recruitInfoExt.recruitFlow}')" style="color: blue">不通过</a>
                            </c:if>
                            <c:if test="${recruitInfoExt.examIsPass eq 'Y'}">
                                |
                                <a href="javascript:notAuditWriteExam('${recruitInfoExt.recruitFlow}')" style="color: blue">不通过</a>
                            </c:if>
                            <c:if test="${recruitInfoExt.examIsPass eq 'N'}">
                                |
                                <a href="javascript:auditWriteExam('${recruitInfoExt.recruitFlow}')" style="color: blue">通过</a>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty recruitInfoExts}">
                <tr><td align="center" colspan="99">无记录</td></tr>
            </c:if>
        </table>
    </div>
    <div class="resultDiv">
        <c:set var="pageView" value="${pdfn:getPageView(recruitInfoExts)}" scope="request"/>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>