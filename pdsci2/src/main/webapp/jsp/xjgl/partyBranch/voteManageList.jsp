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
        function addInfo(){
            var url="<s:url value='/xjgl/partyBranch/addVoteInfo'/>";
//            jboxOpen(url,"编辑",650,500);
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"编辑",650,500,false);
        }
        function electorInfo(recordFlow){
            jboxOpen("<s:url value='/xjgl/partyBranch/electorInfoList'/>?recordFlow="+recordFlow,"被投票人详情",650,430);
        }
        function voterInfo(recordFlow){
            jboxOpen("<s:url value='/xjgl/partyBranch/voterInfoList'/>?recordFlow="+recordFlow,"投票人详情",650,430);
        }
        function showInfo(recordFlow){
            jboxOpen("<s:url value='/xjgl/partyBranch/showVoteInfo'/>?recordFlow="+recordFlow,"详情",650,400);
        }
        function delInfo(recordFlow){
            var url = "<s:url value='/xjgl/partyBranch/delVoteInfo'/>?recordFlow="+recordFlow;
            jboxConfirm("确定删除该记录？",function(){
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
        <form id="searchForm" action="<s:url value="/xjgl/partyBranch/voteManageList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                投票名称：<input type="text" name="voteName" value="${param.voteName}" style="width: 137px;"/>&#12288;
                时&#12288;&#12288;间：
                <span>
                    <input type="text" style="width:100px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="beginTime" value="${param.beginTime}" onchange="checkTime(this)"/>
                    -- <input type="text" style="width:100px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="endTime" value="${param.endTime}" onchange="checkTime(this)"/>
                </span>&#12288;
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <input type="button" class="search" onclick="addInfo();" value="新&#12288;增"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:140px;">投票名称</td>
                <td style="width:120px;">投票时间</td>
                <td style="width:80px;">被投票人</td>
                <td style="width:80px;">投票人</td>
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
                        <a onclick="voterInfo('${info.recordFlow}');" style="cursor:pointer;color:blue;">详情</a>
                    </td>
                    <td>
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
<div id="detail" style="background: white;">
</div>
</body>
</html>