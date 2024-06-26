    <script type="text/javascript">
        function search(){
            jboxStartLoading();
            var url = "<s:url value='/jsres/training/getGuidesForUni'/>";
            jboxPost(url,$("#searchForm").serialize(),function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }
        function edit(recordFlow,roleFlag){
            jboxOpen("<s:url value='/jsres/training/openDirector'/>?recordFlow="+recordFlow+"&roleFlag="+roleFlag,"详细信息",1000,500);
        }
        function del(recordFlow){
            jboxConfirm("确认删除?",function(){
             jboxStartLoading();
            jboxPost("<s:url value='/jsres/training/delDirector'/>?recordFlow="+recordFlow,null,function(resp){
                if('${GlobalConstant.DELETE_SUCCESSED}'==resp){
                    jboxTip("删除成功!");
                    jboxEndLoading();
                    search();
                }
            },null,false);
            });
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
    </script>
    <div class="main_hd">
        <h2 class="underline">住培指南管理</h2>
    </div>
    <div class="main_bd" id="div_table_0">
        <div class="div_search">
        <form id="searchForm" >
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
                <input class="input" name="resNoticeTitle" value="${param.resNoticeTitle}" type="text"/>&nbsp;指南标题(关键字)&#12288;&#12288;
                     <input type="button" class="btn_green" value="查&#12288;询" onclick="search();"/>
                     &#12288;<input type="button" class="btn_green" value="新&#12288;增" onclick="edit('','manager')">
        </form>
        <div class="basic" style="margin-top: 5px">
        <table class="grid">
            <tr>
                <th colspan="2" style="text-align: center;">
                    住培指南
                </th>
            </tr>
            <c:forEach items="${tarinNotices}" var="tarinNotice" varStatus="status">
                <tr>
                    <td style="text-align: left;width:700px;word-wrap:break-word;word-break:break-all;">&nbsp;
                        <a onclick="search(edit('${tarinNotice.recordFlow}','${roleFlag}'))"style="color: #0a0a0a">
                        <u><b>${status.count}.</b></u>${tarinNotice.resNoticeTitle}</a></td>
                    <td style="width: 100px">
                        <a onclick="edit('${tarinNotice.recordFlow}','manager')"style="cursor: pointer">[编辑]</a>&nbsp;
                        <a onclick="del('${tarinNotice.recordFlow}')"style="cursor: pointer">[删除]</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty tarinNotices}">
                <tr>
                    <td colspan="2">无记录!</td>
                </tr>
            </c:if>
        </table>
        </div>
    </div>
        <div class="page" style="padding-right: 40px">
            <c:set var="pageView" value="${pdfn:getPageView(tarinNotices)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>