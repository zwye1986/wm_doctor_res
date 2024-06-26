<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        function toPage(page){
            jboxStartLoading();
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }

        function editInfo(userFlow){
            jboxOpen("<s:url value='/xjgl/partyBranch/editPartyMember'/>?userFlow="+userFlow,"编辑",500,300);
        }

        function addInfo(recordFlow){
            var url ="<s:url value='/xjgl/questionnaire/showEditQuestion?recordFlow='/>"+recordFlow;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"编辑问卷调查",650,500,true);
        }

        function showInfo(recordFlow){
            jboxOpen("<s:url value='/xjgl/questionnaire/showQuestionInfo'/>?recordFlow="+recordFlow,"问卷详情",800,600);
        }

        function delInfo(recordFlow){
            var url = "<s:url value='/xjgl/questionnaire/delCourseQuestion'/>?recordFlow="+recordFlow;
            jboxConfirm("确定删除该问卷吗？",function(){
                jboxPost(url,null,function(resp){
                    if(resp=='${GlobalConstant.OPERATE_SUCCESSED}'){
                        toPage(1);
                    }
                },null,true);
            },null);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/xjgl/questionnaire/courseQuestionList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                问卷名称：<input type="text" name="questionName" value="${param.questionName}" style="width: 137px;"/>&#12288;
                创建时间：<input type="text" style="width:137px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime" value="${param.createTime}"/>&#12288;
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <input type="button" class="search" onclick="addInfo('');" value="新&#12288;增"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:160px;">问卷名称</td>
                <td style="width:100px;">使用课程类型</td>
                <td style="width:120px;">创建时间</td>
                <td style="width:90px;">操作</td>
            </tr>
            <c:forEach items="${dataList}" var="info" varStatus="i">
                <tr>
                    <td>${info.questionName}</td>
                    <td>${info.courseTypeName}</td>
                    <td>${pdfn:transDate(info.createTime)}</td>
                    <td>
                        <a onclick="addInfo('${info.recordFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                        <a onclick="showInfo('${info.recordFlow}');" style="cursor:pointer;color:blue;">查看</a>
                        <a onclick="delInfo('${info.recordFlow}');" style="cursor:pointer;color:blue;">删除</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty dataList}">
                <tr>
                    <td colspan="99" style="text-align: center;">无记录！</td>
                </tr>
            </c:if>
        </table>
        <div style="margin-top:65px;">
            <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>