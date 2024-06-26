<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        //查询
        function search(){
            jboxStartLoading();
            $("#searchForm").submit();
        }
        //分页
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        function editInfo(userFlow){
            var url =  "<s:url value='/xjgl/user/editSupple'/>?userFlow="+userFlow;
            jboxOpen(url ,"编辑",660,760);
        }
        //复选框事件
        //全选、取消全选、反选的事件
        function selectAll() {
            if($("#checkAll").attr("checked")){
                $(".check").attr("checked",true);
            }else{
                $(".check").attr("checked",false);
            }
        }
        //子复选框的事件
        function setSelectAll(obj){
            if(!$(obj).attr("checked")){
                $("#checkAll").attr("checked",false);
            }else{
                var checkAllLen = $("input[type='checkbox'][class='check']").length;
                var checkLen = $("input[type='checkbox'][class='check']:checked").length;
                if(checkAllLen == checkLen){
                    $("#checkAll").attr("checked",true);
                }
            }
        }
        function backOpt(){
            if($(".check:checked").size()==0){
                jboxTip("至少勾选一条补录信息");
                return;
            }
            var recordLst = [];
            $(".check:checked").each(function(){
                recordLst.push(this.value);
            })
            jboxConfirm("确认一键退回已选信息？", function(){
                var url = "<s:url value='/xjgl/user/backBatchOpt?recordLst='/>"+recordLst;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        location.reload();
                    }
                });
            });

        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/xjgl/user/supplementList?role=xx"/>" method="post">
            <table class="basic" style="width: 100%;margin: 10px 0px 5px -20px;border: none;">
                <tr>
                    <td style="border: none;">
                        <input id="currentPage" type="hidden" name="currentPage" value=""/>
                        <div>&#12288;
                            学&#12288;&#12288;号：<input type="text" style="width: 137px;" name="sid" value="${param.sid}" >
                            &#12288;姓&#12288;&#12288;名：<input type="text" style="width: 137px;" name="userName" value="${param.userName }">
                            &#12288;培养层次：<select style="width: 144px;" name="trainTypeId" class="inputText">
                                <option/>
                                <c:forEach items="${dictTypeEnumTrainTypeList}" var="trainType">
                                    <c:if test="${trainType.dictId ne '3'}">
                                        <option value="${trainType.dictId}" ${param.trainTypeId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                            &#12288;培养类型：<select style="width: 144px;" name="trainCategoryId" class="inputText">
                                <option/>
                                <c:forEach items="${dictTypeEnumTrainCategoryList}" var="train">
                                    <option value="${train.dictId}" ${param.trainCategoryId eq train.dictId?'selected':''}>${train.dictName}</option>
                                </c:forEach>
                            </select>
                            &#12288;补录确认：<select style="width: 144px;" name="submitFlag" class="inputText">
                                <option/>
                                <option value="Y" ${param.submitFlag eq 'Y'?'selected':''}>已补</option>
                                <option value="N" ${param.submitFlag eq 'N'?'selected':''}>未补</option>
                            </select>
                            <input type="button" class="search" onclick="search();" value="查&#12288;询" />
                            <input type="button" name="" class="search" onclick="backOpt();" value="一键退回"/>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        <div class="resultDiv">
            <table class="basic" width="100%">
                <tr style="font-weight: bold;">
                    <td style="min-width:80px;max-width:80px;text-align: center;padding:0px;">
                        <input type="checkbox" name="checkAll" class="checkAll" value="Y" id="checkAll" onclick="selectAll()"/>&nbsp;正/反选
                    </td>
                    <td style="min-width:80px;max-width:80px;text-align: center;padding:0px;">学号</td>
                    <td style="min-width:80px;max-width:80px;text-align: center;padding:0px;">学生姓名</td>
                    <td style="min-width:120px;max-width:120px;text-align: center;padding:0px;">学生身份证件类型</td>
                    <td style="min-width:120px;max-width:120px;text-align: center;padding:0px;">学生身份证件号码</td>
                    <td style="min-width:120px;max-width:120px;text-align: center;padding:0px;">学生是否在职</td>
                    <td style="min-width:100px;max-width:100px;text-align: center;padding:0px;">入学日期</td>
                    <td style="min-width:100px;max-width:100px;text-align: center;padding:0px;">学籍状态</td>
                    <td style="min-width:120px;max-width:120px;text-align: center;padding:0px;line-height:25px;">父母或监护人1<br/>姓名</td>
                    <td style="min-width:120px;max-width:120px;text-align: center;padding:0px;line-height:25px;">父母或监护人1<br/>身份证件类型</td>
                    <td style="min-width:120px;max-width:120px;text-align: center;padding:0px;line-height:25px;">父母或监护人1<br/>身份证件号码</td>
                    <td style="min-width:120px;max-width:120px;text-align: center;padding:0px;line-height:25px;">父母或监护人2<br/>姓名</td>
                    <td style="min-width:120px;max-width:120px;text-align: center;padding:0px;line-height:25px;">父母或监护人2<br/>身份证件类型</td>
                    <td style="min-width:120px;max-width:120px;text-align: center;padding:0px;line-height:25px;">父母或监护人2<br/>身份证件号码</td>
                    <td style="min-width:80px;max-width:80px;text-align: center;padding:0px;">操作</td>
                </tr>
                <c:forEach items="${userList}" var="su">
                    <tr>
                        <td style="text-align: center;padding:0px;">
                            <input type="checkbox" class="check" value="${su.USER_FLOW}" onclick="setSelectAll(this)" />
                        </>
                        <td style="text-align: center;padding:0px;">${su.SID }</td>
                        <td style="text-align: center;padding:0px;">${su.USER_NAME}</td>
                        <td style="text-align: center;padding:0px;line-height:25px;">${su.ZJLX}</td>
                        <td style="text-align: center;padding:0px;">${su.ZJHM}</td>
                        <td style="text-align: center;padding:0px;">${su.SFZZ}</td>
                        <td style="text-align: center;padding:0px;">${su.RXRQ}</td>
                        <td style="text-align: center;padding:0px;">${su.XJZT}</td>
                        <td style="text-align: center;padding:0px;">${su.FMXM1}</td>
                        <td style="text-align: center;padding:0px;line-height:25px;">${su.FMZJLX1}</td>
                        <td style="text-align: center;padding:0px;">${su.FMZJHM1}</td>
                        <td style="text-align: center;padding:0px;">${su.FMXM2}</td>
                        <td style="text-align: center;padding:0px;line-height:25px;">${su.FMZJLX2}</td>
                        <td style="text-align: center;padding:0px;">${su.FMZJHM2}</td>
                        <td style="text-align: center;padding:0px;">
                            <a onclick="editInfo('${su.USER_FLOW}');" style="cursor: pointer">编辑</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty userList}">
                    <td colspan="99" style="text-align: center;">无记录！</td>
                </c:if>
            </table>
            <c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>