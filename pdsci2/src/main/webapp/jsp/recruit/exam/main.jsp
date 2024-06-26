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


    <script type="text/javascript">

        function toPage(page)
        {
            jboxStartLoading();
            page=page||1;
            $("#currentPage").val(page);
            $("#examMainForm").submit();
        }

        function addExamMain(mainFlow) {
            jboxOpen("<s:url value='/recruit/examMain/addExamMain'/>?mainFlow="+mainFlow, "考试信息维护", 500,200);
        }
        function showExamMain(mainFlow) {
            jboxOpen("<s:url value='/recruit/examMain/showExamMain'/>?mainFlow="+mainFlow, "考试信息", 500,200);
        }
        function editMainNote(mainFlow) {
            jboxOpen("<s:url value='/recruit/examMain/editMainNote'/>?mainFlow="+mainFlow, "维护考生须知", 900,400);
        }
        function delExam(mainFlow) {
            var url = '<s:url value="/recruit/examMain/delExam"/>?mainFlow='+mainFlow;
            jboxConfirm("确认删除？" , function(){
                jboxPost(url , null , function(resp){
                    jboxTip(resp);
                    if(resp=="${ GlobalConstant.DELETE_SUCCESSED}")
                    {
                        toPage(1);
                    }
                } , null , true);
            });
        }
        function fenPei(mainFlow,isRe) {
            var url = '<s:url value="/recruit/examMain/fenPei"/>?mainFlow='+mainFlow;
            var msg=isRe=='Y'?"确认重新分配？已分配考场的学员也将重新分配！":"确认一键分配考试信息？";
            jboxConfirm(msg , function(){
                jboxPost(url , null , function(resp){
                    jboxTip(resp);
                    if(resp=="${ GlobalConstant.OPERATE_SUCCESSED}")
                    {
                        toPage(1);
                    }
                } , null , true);
            });
        }
        function publishExam(mainFlow) {
            var url = '<s:url value="/recruit/examMain/publishExam"/>?mainFlow='+mainFlow;
            jboxConfirm("确认发布？发布之后无法修改考试相关内容" , function(){
                jboxPost(url , null , function(resp){
                    jboxTip(resp);
                    if(resp=="${ GlobalConstant.OPERATE_SUCCESSED}")
                    {
                        toPage(1);
                    }
                } , null , true);
            });
        }

        function addExamInfoForm(mainFlow) {
            jboxStartLoading();
            window.location.href = "<s:url value='/recruit/examInfo/examInfos'/>?mainFlow="+mainFlow;
        }

    </script>

</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 ">
            <form id="examMainForm" name="examMainForm" action="<s:url value='/recruit/examMain/main'/>" method="post">
                <input type="hidden" id="currentPage" name="currentPage"/>
                <div class="queryDiv">
                    <div class="inputDiv_" style="margin-left: 50px">
                        <label class="qlable">招录年份：</label>
                        <input class="qtext" id="recruitYear" name="recruitYear" type="text" class="qtext" value="${param.recruitYear}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
                    </div>
                    <div class="inputDiv_" style="margin-left: 50px">
                        <label class="qlable">是否发布：</label>
                        <select class="xlselect" name="isPublish"  >
                            <option></option>
                            <option value="Y" ${param.isPublish eq 'Y' ? 'selected':''}>是</option>
                            <option value="N" ${param.isPublish eq 'N' ? 'selected':''}>否</option>
                        </select>
                    </div>
                    <div class="lastDiv" style="max-width: 180px;min-width: 180px;">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
                        <input type="button" value="新&#12288;增" class="searchInput" onclick="addExamMain('');"/>
                    </div>
                </div>
            </form>
        </div>
        <table class="xllist" style="width: 100%;">
            <tr>
                <th width="10%">年份</th>
                <th width="15%" style="text-align: center">开始时间</th>
                <th width="15%" style="text-align: center">结束时间</th>
                <th width="10%" style="text-align: center">操作</th>
            </tr>
            <tbody>
            <c:forEach items="${examMains}" var="examMain" varStatus="seq">
                <c:set var="cfgInfo" value="${cfgMap[examMain.cfgFlow]}"></c:set>
                <tr>
                    <td>${examMain.recruitYear} <c:if test="${cfgInfo.isRecruit eq 'Y'}">当前招录</c:if></td>
                    <td>${examMain.startTime}</td>
                    <td>${examMain.endTime}</td>
                    <td>

                        <c:if test="${examMain.isPublish eq 'Y'}">
                            <a href="javascript:showExamMain('${examMain.mainFlow}')" style="color: blue">查看</a>
                            |
                            <a href="javascript:addExamInfoForm('${examMain.mainFlow}')" style="color: blue">考核信息配置</a>
                            <c:if test="${cfgInfo.isRecruit eq 'Y' }">
                                |
                                <c:if test="${fenPeiMap[examMain.mainFlow] eq 'Y'}">
                                    <a href="javascript:fenPei('${examMain.mainFlow}','Y')" style="color: blue">重新分配</a>
                                </c:if>
                                <c:if test="${fenPeiMap[examMain.mainFlow] ne 'Y'}">
                                    <a href="javascript:fenPei('${examMain.mainFlow}','N')" style="color: blue">一键分配</a>
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${examMain.isPublish eq 'N'}">
                            <a href="javascript:addExamMain('${examMain.mainFlow}')" style="color: blue">编辑</a>
                            |
                            <c:if test="${cfgInfo.isRecruit eq 'Y'}">
                                <a href="javascript:addExamInfoForm('${examMain.mainFlow}')" style="color: blue">考核信息配置</a>
                                |
                            </c:if>
                            <c:if test="${cfgInfo.isRecruit eq 'Y' and cfgInfo.recruitEndDate < pdfn:getCurrDate()}">
                                <a href="javascript:publishExam('${examMain.mainFlow}')" style="color: blue">发布</a>
                                |
                            </c:if>
                            <a href="javascript:delExam('${examMain.mainFlow}')" style="color: blue">删除</a>
                        </c:if>
                        |
                        <a href="javascript:editMainNote('${examMain.mainFlow}')" style="color: blue">考试须知</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty examMains}">
                <tr><td align="center" colspan="8">无记录</td></tr>
            </c:if>
        </table>
    </div>
    <div class="resultDiv">
        <c:set var="pageView" value="${pdfn:getPageView(examMains)}" scope="request"/>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>