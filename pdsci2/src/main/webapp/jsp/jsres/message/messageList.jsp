<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">

    function toPage(page){
        if(page != undefined){
            $("#currentPage").val(page);
        }
        search();
    }

    function search(){
        var url ="<s:url value='/jsres/message/messageList'/>";
        jboxPostLoad("content", url, $("#searchForm").serialize(), true);
    }

    function delMessage(messageFlow){
        jboxConfirm("确认删除？" , function(){
            var url = "<s:url value='/jsres/message/delMessage'/>?messageFlow="+messageFlow;
            jboxGet(url , null , function(resp){
                toPage(1);
            } , null , true);
        });
    }

    function edit(messageFlow){
        var url = "<s:url value='/jsres/message/edit'/>?messageFlow="+messageFlow;
        if(messageFlow)
        {
            jboxOpen(url,"编辑招录信息",900,500,true);
        }else{
            jboxOpen(url,"新增招录信息",900,500,true);
        }
    }

</script>


<div class="main_hd">
    <h2 class="underline">招录信息发布</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form style="margin-top: 15px" id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            <label class="from_label">标题：</label>
            <input type="text" class="input" name="messageTitle" value="${param.messageTitle}" style="width:161px;"/>
<%--            &#12288;--%>
<%--            <input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/>--%>
<%--            &#12288;--%>
<%--            <input class="btn_green" type="button" onclick="edit('')" value="新&#12288;增"/>--%>


            <div style="margin-top: 15px;margin-bottom: 15px">
                <input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/>
                &#12288;
                <input class="btn_green" type="button" onclick="edit('')" value="新&#12288;增"/>
            </div>

        </form>
    </div>
    <div  class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="75%"/>
                <col width="10%"/>
                <col width="15%"/>
            </colgroup>
            <tbody id="tbody">
            <tr>
                <th>标题</th>
                <th>发布日期</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${messageList}" var="list">
                <tr>
                    <td style="text-align: left;">${list.messageTitle}</td>
                    <td>${pdfn:transDate(list.modifyTime)}</td>
                    <td>
                        <a href="javascript:edit('${list.messageFlow}');" >编辑</a>
                        <a style="margin-left: 5px" href="<s:url value='/inx/jsres/messageView'/>?messageFlow=${list.messageFlow}" target="_blank" >查看</a>
                        <a style="margin-left: 5px" onclick="delMessage('${list.messageFlow}');" >删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty messageList}">
                <tr>
                    <td colspan="4">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>

    <div class="page" style="text-align: right">
        <c:set var="pageView" value="${pdfn:getPageView(messageList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>

