    <script type="text/javascript">
        function save(){
            if($("#opinionUserContent").val()==""){
                jboxTip("请输入反馈意见!");
                return ;
            }
            jboxConfirm("确认提交?",function(){
                jboxPost("<s:url value='/jsres/training/saveOpinions'/>",$("#saveForm").serialize(),function(resp){
                    if(resp=='${GlobalConstant.OPRE_SUCCESSED_FLAG}'){
                        jboxTip("保存成功!");
                        opinions();
                        jboxClose();
                    }else{
                        jboxTip("保存失败!");
                    }
                },null,false);
            });
        }
        function edit(trainingOpinionFlow){
            jboxOpen("<s:url value='/jsres/training/editOpinions'/>?trainingOpinionFlow="+trainingOpinionFlow,"编辑意见",500,200);
        }
        function del(trainingOpinionFlow){
            jboxConfirm("确认删除?",function(){
                jboxPost("<s:url value='/jsres/training/delOpinions'/>?trainingOpinionFlow="+trainingOpinionFlow,null,function(resp){
                    if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
                        jboxTip("删除成功!");
                        opinions();
                    }
                },null,false);
            });
        }
    </script>
    <div class="main_hd">
        <h2 class="underline">意见反馈</h2>
    </div>

<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="saveForm">
            <table class="basic" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="text-align: center">
                        <font size="4">住培意见反馈</font>
                    </th>
                </tr>
                <tr>
                    <td style="padding: 0">
                        <textarea placeholder="输入你的反馈意见!" id="opinionUserContent" name="opinionUserContent" style="width: 100%;height: 100px;"></textarea>
                    </td>
                </tr>
            </table>
            <p style="text-align: right"><input type="button" onclick="save();" class="btn_green" value="提&#12288;交"/></p>
        </form>
            <div class="basic" style="margin-top:5px ">
                <table class="grid">
            <tr>
                <th colspan="3">
                    历史意见
                </th>
            </tr>
            <tr>
                <th style="width: 5%">序号</th>
                <th style="width: 70%">内容</th>
                <%--<th style="width: 10%">操作</th>--%>
            </tr>
            <c:forEach items="${trainingOpinions}" var="trainingOpinion" varStatus="status">
                <tr>
                    <th
                    <c:if test="${!empty trainingOpinion.opinionReplyContent}">
                        rowspan="2"
                    </c:if>
                    >${status.index+1}</th>
                        <td style="text-align: center;word-wrap:break-word;word-break:break-all;">
                            <p>${trainingOpinion.opinionUserContent}</p></td>
                    <%--<td>--%>
                        <%--<c:if test="${empty trainingOpinion.opinionReplyContent}">--%>
                            <%--<a style="cursor: pointer;" onclick="edit('${trainingOpinion.trainingOpinionFlow}')">[编辑]</a>&nbsp;--%>
                            <%--<a style="cursor: pointer;" onclick="del('${trainingOpinion.trainingOpinionFlow}')">[删除]</a>--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${!empty trainingOpinion.opinionReplyContent}">--%>
                            <%--管理员已回复--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                </tr>
                <c:if test="${!empty trainingOpinion.opinionReplyContent}">
                <tr>
                    <td colspan="2" style="text-align: left;word-wrap:break-word;word-break:break-all;">
                        管理员回复:${trainingOpinion.opinionReplyContent}
                    </td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
        </div>
    </div>
</div>

