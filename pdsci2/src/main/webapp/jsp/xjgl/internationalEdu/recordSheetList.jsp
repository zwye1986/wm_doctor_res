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
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        <%--function addInfo(){--%>
            <%--jboxOpen("<s:url value='/xjgl/abroadApply/editAbroadApply'/>?roleFlag=${param.roleFlag}","编辑",960,600);--%>
        <%--}--%>
        function editInfo(recordFlow){
            jboxOpen("<s:url value='/xjgl/abroadApply/showEditSheet'/>?recordFlow="+recordFlow+"&roleFlag=${param.roleFlag}&formType=${param.formType}","编辑",960,600);
        }

        function delSheetInfo(recordFlow,sheetType){
            var url = "<s:url value='/xjgl/abroadApply/delSheetInfo'/>?recordFlow="+recordFlow+"&sheetType="+sheetType;
            jboxConfirm("确定删除该登记表信息？",function(){
                jboxPost(url,null,function(resp){
                    if(resp=='${GlobalConstant.OPERATE_SUCCESSED}'){
                        toPage(1);
                    }
                },null,true);
            },null);
        }


        function exportData(){
            var url = "<s:url value='/xjgl/partyBranch/exportPartyMembers'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
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
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/xjgl/abroadApply/abroadApplyList"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
                <input type="hidden" name="formType" value="${param.formType}"/>
                起止时间：<span><input type="text" style="width:100px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="beginDate" value="${param.beginDate}" onchange="checkTime(this)"/>
                 -- <input type="text" style="width:100px;" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="endDate" value="${param.endDate}" onchange="checkTime(this)"/></span>&#12288;
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <%--<input type="button" class="search" onclick="addInfo();" value="新&#12288;增"/>--%>
                <%--<input type="button" class="search" onclick="addInfo();" value="导&#12288;出"/>--%>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:100px;">姓名</td>
                <td style="width:50px;">性别</td>
                <td style="width:100px;">出生年月</td>
                <td style="width:100px;">政治面貌</td>
                <td style="width:120px;">出国起止时间</td>
                <td style="width:100px;">操作</td>
            </tr>
            <c:forEach items="${dataList}" var="info" varStatus="i">
                <tr>
                    <td>${info.userName}</td>
                    <td>${info.sexName}</td>
                    <td>${info.birthDate}</td>
                    <td>${info.politicalStatus}</td>
                    <td>${info.beginDate}~${info.endDate}</td>
                    <td>
                        <a onclick="editInfo('${info.recordFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                        <a onclick="delSheetInfo('${info.recordFlow}','recordSheet');" style="cursor:pointer;color:blue;">删除</a>
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