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
        $(function(){
            $("#detail").slideInit({
                width:1000,
                speed:500,
                outClose:true,
                haveZZ:true
            });
        });
        function toPage(page){
            jboxStartLoading();
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }

        function editInfo(userFlow){
            jboxOpen("<s:url value='/xjgl/partyBranch/editPartyMember'/>?userFlow="+userFlow,"编辑",500,300);
        }

        function exportData(){
            var url = "<s:url value='/xjgl/partyBranch/exportPartyMembers'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }

        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function electorInfo(recordFlow){
            jboxOpen("<s:url value='/xjgl/partyBranch/electorInfoList'/>?recordFlow="+recordFlow+"&userFlag='Y'","被投票人详情",650,430);
        }
        function toVote(recordFlow){
            jboxOpen("<s:url value='/xjgl/partyBranch/toVoteInfo'/>?voteFlow="+recordFlow,"投票",650,500);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/xjgl/partyBranch/toVoteList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                投票名称：<input type="text" name="voteName" value="${param.voteName}" style="width: 137px;"/>&#12288;
                时&#12288;&#12288;间：
                <span>
                    <input type="text" style="width:100px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="beginTime" value="${param.beginTime}" onchange="checkTime(this)"/>
                    -- <input type="text" style="width:100px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="endTime" value="${param.endTime}" onchange="checkTime(this)"/>
                </span>&#12288;
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:140px;">投票名称</td>
                <td style="width:120px;">投票时间</td>
                <td style="width:80px;">被投票人</td>
                <td style="width:100px;">操作</td>
            </tr>
            <c:forEach items="${dataList}" var="info" varStatus="i">
                <tr>
                    <td>${info.voteName}</td>
                    <td>${info.beginTime}~${info.endTime}</td>
                    <td>
                        <a onclick="electorInfo('${info.recordFlow}');" style="cursor:pointer;color:blue;">详情</a>
                    </td>
                    <td>
                        <c:if test="${(pdfn:getCurrDate() gt info.endTime)||(pdfn:getCurrDate() lt info.beginTime)}">
                            投票
                        </c:if>
                        <c:if test="${(pdfn:getCurrDate() le info.endTime)&&(pdfn:getCurrDate() ge info.beginTime)}">
                            <a onclick="toVote('${info.recordFlow}');" style="cursor:pointer;color:blue;">投票</a>
                        </c:if>
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
<div id="detail" style="background: white;">
</div>
</body>
</html>