<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            var checkLen = $(".check:checked").length;
            if(checkLen == 0){
                jboxTip("请勾选投票人！");
                return;
            }
            var stationDivBody="" ;
            $(".check:checked").each(function(i){
                var userName=$(this).attr("name");
                var partyBranchName=$(this).attr("partyBranchName");
                stationDivBody+="<div><a userFlow='"+this.value+"'>"+userName+"&emsp;&emsp;"+partyBranchName+"</a></div>";
            });
            window.parent.frames['jbox-message-iframe'].$('#voterFlowsDiv').html(stationDivBody);
            jboxClose();
        }
        //模糊查询
        function likeSearch(name){
            if(name){
                $("[partyBranchNameTr]").hide();
                $("[partyBranchNameTr*='"+name+"']").show();
            }else{
                $("[partyBranchNameTr]").show();
            }
        }
        //复选框事件
        //全选、取消全选、反选的事件
        function selectAll() {
            if($("#checkAll").is(':checked')){
                $(".check:visible").prop("checked",true);
            }else{
                $(".check:visible").prop("checked",false);
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="" id="docInfoDiv1">
        <div>
            <div style="width: 100%;border: 0px solid #336544; overflow:auto;height: 500px" id="selectDiv">
                <form id="myForm" action="" method="post">
                    <table class="basic" style="width: 98%;">
                        <tr>
                            <td style="width: 50px;"><input type="checkbox" name="checkAll" class="checkAll" value="Y" id="checkAll" onclick="selectAll()"/>&nbsp;全选</td>
                            <td colspan="2">
                                <input type="text" name="partyBranchName" value="${param.partyBranchName}" placeholder="可通过党支部检索" onkeyup="likeSearch(this.value);">&#12288;
                            </td>
                        </tr>
                        <c:forEach items="${voterList}" var="user">
                            <tr partyBranchNameTr="${user.partyBranchName}" id="userTr">
                                <td><input type="checkbox" class="check" name="${user.userName}" value="${user.userFlow}" partyBranchName="${user.partyBranchName}"></td>
                                <td style="border-right: none;">&nbsp;${user.userName}</td>
                                <td style="border-left: none;">&nbsp;${user.partyBranchName}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </form>
            </div>
        </div>
        <div style="text-align: center;margin-top:20px;">
            <input type="button" class="search" onclick="save();" value="确&#12288;认"/>
            <input type="button" class="search" onclick="jboxClose();" value="取&#12288;消"/>
        </div>
    </div>
</div>
</body>
</html>