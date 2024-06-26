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
        function addInfo(){
            jboxOpen("<s:url value='/xjgl/partyBranch/editCaucusNotice'/>","编辑",900,500);
        }
        function noticeView(topicFlow){
            jboxOpen("<s:url value='/xjgl/partyBranch/noticeView'/>?recordFlow=${info.recordFlow}","专题讨论",650,430);
        }
        function delInfo(topicFlow){
            var url = "<s:url value='/xjgl/partyBranch/delSpecialTopic'/>?topicFlow="+topicFlow;
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
        <form id="searchForm" action="<s:url value="/xjgl/partyBranch/caucusNotices"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <input type="hidden" name="role" value="${role}"/>
                标&#12288;&#12288;题：<input type="text" name="noticeName" value="${param.noticeName}" style="width: 137px;"/>&#12288;
                时&#12288;&#12288;间：
                <span>
                    <input type="text" style="width:100px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime" value="${param.createTime}" onchange="checkTime(this)"/>
                    -- <input type="text" style="width:100px" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="modifyTime" value="${param.modifyTime}" onchange="checkTime(this)"/>
                </span>&#12288;
                通知类型：<select class="select" style="width: 141px;" name="noticeTypeId">
                    <option value=""/>
                    <option value="1" <c:if test="${param.noticeTypeId eq '1'}">selected="selected"</c:if>>普通通知</option>
                    <option value="2" <c:if test="${param.noticeTypeId eq '2'}">selected="selected"</c:if>>投票通知</option>
                </select>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <c:if test="${role ne 'doctor'}">
                    <input type="button" class="search" onclick="addInfo();" value="新&#12288;增"/>
                </c:if>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr style="font-weight: bold;">
                <td style="width:50px;">序号</td>
                <td style="width:140px;">标题</td>
                <td style="width:120px;">时间</td>
                <c:if test="${role ne 'doctor'}">
                    <td style="width:80px;">点击量</td>
                </c:if>
                <td style="width:100px;">操作</td>
            </tr>
            <c:forEach items="${dataList}" var="info" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${info.noticeName}</td>
                    <td>${info.publishTime}</td>
                    <c:if test="${role ne 'doctor'}">
                        <td>${info.clicks}</td>
                    </c:if>
                    <td>
                        <%--<a onclick="noticeView('${info.recordFlow}');" style="cursor:pointer;color:blue;">查看</a>--%>
                        <a href="<s:url value='/xjgl/partyBranch/noticeView'/>?recordFlow=${info.recordFlow}" style="cursor:pointer;color:blue;" target="_blank">查看</a>
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