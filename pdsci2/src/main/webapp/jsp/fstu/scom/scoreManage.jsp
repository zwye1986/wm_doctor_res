<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
    <script type="text/javascript">
        function toPage(page) {
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function pass(recordFlow) {
            jboxConfirm("是否确认审核?", function () {
                var json = {"recordLst": [recordFlow], "auditStatusId": "Passed"};
                var url = "<s:url value='/fstu/score/auditScore'/>";
                jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function batchAudit() {
            jboxConfirm("是否确认审核?", function () {
                var recordLst = [];
                $(":checkbox[name='recordFlow']:checked").each(function () {
                    recordLst.push(this.value);
                })
                if (recordLst.length > 0) {
                    var json = {"recordLst": recordLst, "auditStatusId": "Passed", "roleFlag": "${roleFlag}"};
                    var url = "<s:url value='/fstu/score/auditScore'/>";
                    jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
                        if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                            location.reload();
                        }
                    }, null, true);
                } else {
                    jboxTip("请勾选需要批量审核的数据！");
                    return;
                }
            });
        }
        function back(recordFlow) {
            jboxConfirm("是否确认退回?", function () {
                var json = {"recordLst": [recordFlow], "auditStatusId": "Backed"};
                var url = "<s:url value='/fstu/score/auditScore'/>";
                jboxPost(url, "jsonData="+JSON.stringify(json), function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function del(recordFlow) {
            jboxConfirm("是否确认删除?", function () {
                var url = "<s:url value='/fstu/score/delScore?recordFlow='/>" + recordFlow;
                jboxPost(url, null, function (resp) {
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        var height = (window.screen.height) * 0.6;
        var width = (window.screen.width) * 0.7;
        function audit(recordFlow) {
            var url = "<s:url value='/fstu/score/auditScoreInfo?roleFlag=${roleFlag}&recordFlow='/>" + recordFlow;
            jboxOpen(url, "编辑", 860, height);
        }
        function query(recordFlow) {
            var url = "<s:url value='/fstu/score/queryScore?recordFlow='/>" + recordFlow;
            jboxOpen(url, "查看", 860, height);
        }
        function importScore() {
            var courseFlow = '${courseFlow}';
            var url = "<s:url value='/fstu/score/importScore'/>";
            typeName = "导入学员成绩";
            jboxOpen(url, typeName, 380, 180);
        }


        function checkAllChange(){
            var isChecked = $("#checkall").prop("checked");
            $("input:checkbox[name='recordFlow']").each(function(i,n){
                $(n).prop("checked",isChecked);
            });
        }
        $(function(){
            $("input:checkbox[name='recordFlow']").change(function(){
                var isCheckAll=true;
                var checkedBox = $("input:checkbox[name='recordFlow']");
                $(checkedBox).each(function(index, element) {
                    if(!$(element).prop("checked")){
                        isCheckAll=false;
                    }
                });
                $("#checkall").prop("checked",isCheckAll);
            });
        });
        /*如果子复选框没有全选中，全选复选框也不选中，如果子复选框全选中那么全选复选框也跟随选中*/
        function likeChkChange(){
            /*如果子复选框没有全选中，全选复选框也不选中，如果子复选框全选中那么全选复选框也跟随选中*/
            /*标识符（true全选，false不全选）*/
            var isCheckAll = true;
            var likeItems=document.getElementsByName("like");
            for(var i=0; i<likeItems.length;i++){
                if(likeItems[i].checked==false){
                    isCheckAll=false;
                    break;
                }
            }
            /*将全选的标识符结果赋值给全选复选框的状态*/
            document.getElementById("ckeckAllChk").checked=isCheckAll;
        }
        function initDept() {
            var datas = [];
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    var d = {};
                    d.id = n.deptFlow;
                    d.text = n.deptName;
                    datas.push(d);
                });
            }, null, false);
            var itemSelectFuntion = function () {
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
        }

        $(document).ready(function () {
            <c:if test="${roleFlag ne 'header'}">initDept();</c:if>
            $('#firstProjTypeId option').hide();
            $('#firstProjTypeId option[value=""]').show();
            $('#firstProjTypeId'+' option.${dictTypeEnumAcaScoreType.id}\\.'+$("[name='firstScoreTypeId']").val()).show();
        });

        function linkage(dictId){
            $('#firstProjTypeId').val("");//清空上次展现数据
            $('#firstProjTypeId option').hide();
            $('#firstProjTypeId option[value=""]').show();
            $('#firstProjTypeId'+' option.${dictTypeEnumAcaScoreType.id}\\.'+dictId).show();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/fstu/score/scoreManage/${roleFlag}'/>" method="post">
                <table class="basic" style="width: 100%;margin: 10px 0px;">
                    <tr>
                        <td>
                            <div class="searchDiv">
                                年&#12288;&#12288;份：
                                <select name="sessionNumber" class="xlselect">
                                    <option/>
                                    <c:forEach items="${dictTypeEnumScoreYearList}" var="dict">
                                        <option value="${dict.dictName}"
                                                <c:if test="${dict.dictName eq param.sessionNumber}">selected</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="searchDiv">
                                学分类别：
                                <select name="firstScoreTypeId" class="xlselect" onchange="linkage(this.value)">
                                    <option/>
                                    <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                        <option value="${dict.dictId}"
                                                <c:if test="${dict.dictId eq param.firstScoreTypeId}">selected</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="searchDiv">
                                项目大类：
                                <select id="firstProjTypeId" name="firstProjTypeId" class="xlselect">
                                    <option/>
                                    <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                        <c:set var="dictKey" value="dictTypeEnumAcaScoreType.${dict.dictId}List"/>
                                        <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                            <c:set var="valueKey" value="${dict.dictId},${scope.dictId}"></c:set>
                                            <option class="${scope.dictTypeId}" value="${valueKey}" <c:if test="${valueKey eq param.firstProjTypeId}">selected</c:if>>${scope.dictName}</option>
                                        </c:forEach>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="searchDiv">
                                评分项&#12288;：
                                <select name="firstScoreItemId" class="xlselect">
                                    <option/>
                                    <c:forEach items="${dictTypeEnumAssessItemList}" var="dict">
                                        <option value="${dict.dictId}"
                                                <c:if test="${dict.dictId eq param.firstScoreItemId}">selected</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="searchDiv">
                                完成情况：
                                <select name="firstExecutionId" class="xlselect">
                                    <option/>
                                    <c:forEach items="${dictTypeEnumAccomplishResultList}" var="dict">
                                        <option value="${dict.dictId}"
                                                <c:if test="${dict.dictId eq param.firstExecutionId}">selected</c:if>>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="searchDiv">
                                分&#12288;&#12288;值：&nbsp;<input class="xltext" type="text" name="myScore"
                                                                value="${param.myScore}">
                            </div>
                            <div class="searchDiv">
                                学员工号：&nbsp;<input class="xltext" type="text" name="userCode" value="${param.userCode}">
                            </div>
                            <div class="searchDiv">
                                学员姓名：&nbsp;<input class="xltext" type="text" name="userName" value="${param.userName}">
                            </div>
                            <c:if test="${roleFlag ne 'header'}">
                                <div class="searchDiv">
                                    所属部门：&nbsp;<%--<input class="xltext" type="text" name="userDeptName"
                                                  value="${param.userDeptName}">--%>
                                    <input id="trainDept" class="xltext" name="userDeptName" type="text"
                                           value="${param.userDeptName}" autocomplete="off"/>
                                    <input id="deptFlow" name="userDeptFlow" class="input" value="${param.userDeptName}" type="text"
                                           hidden style="margin-left: 0px;"/>
                                </div>
                            </c:if>
                            <div class="searchDiv">
                                审核结果：&nbsp;<input type="radio" name="auditStatusId" value="ALL"
                                                  <c:if test="${param.auditStatusId eq 'ALL' || param.flag eq 'ALL'}">checked="checked"</c:if>>全部
                                <input type="radio" name="auditStatusId" value="Y"
                                       <c:if test="${param.auditStatusId eq 'Y'}">checked="checked"</c:if>>已审核
                                <input type="radio" name="auditStatusId" value="N"
                                       <c:if test="${param.auditStatusId eq 'N'}">checked="checked"</c:if>>未审核
                            </div>
                            <div class="searchDiv">
                                <input type="hidden" name="currentPage" id="currentPage">
                                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                                <input type="hidden" id="jsondata">
                                <c:if test="${roleFlag ne 'header'}">
                                    <input type="button" class="search" value="一键审核" onclick="batchAudit()"/>
                                </c:if>
                                <c:if test="${roleFlag eq 'admin'}">
                                    <input type="button" class="search" value="导入学分" onclick="importScore()"/>
                                </c:if>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>全选<input type="checkbox" id="checkall" onchange="checkAllChange()"/> </th>
                <th>年份</th>
                <th>学分类别</th>
                <th>项目大类</th>
                <th style="width:16%;">名称</th>
                <th style="width:16%;">编号</th>
                <th>姓名</th>
                <th>部门</th>
                <th>工号</th>
                <th>评分项</th>
                <th>完成情况</th>
                <th>分值</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${scoreList}" var="score">
                <tr>
                    <td>
                        <%--1、科主任审核条件(基于管理员未审核)[已去除]；2、医院管理员或科研管理员审核条件--%>
                        <c:if test="${(roleFlag eq 'admin' && score.firstMiurAuditFlag ne 'Y')||(roleFlag eq 'miur' && score.firstMiurAuditFlag eq 'Y')}">
                            <input type="checkbox" name="recordFlow" value="${score.recordFlow}"/>
                        </c:if>
                        <c:if test="${!((roleFlag eq 'admin' && score.firstMiurAuditFlag ne 'Y')||(roleFlag eq 'miur' && score.firstMiurAuditFlag eq 'Y'))}">
                            <input type="checkbox" disabled="disabled"/>
                        </c:if>
                    </td>
                    <td>${score.sessionNumber}</td>
                    <td>${score.firstScoreTypeName}</td>
                    <td>${score.firstProjTypeName}</td>
                    <td>${score.scoreName}</td>
                    <td>${score.scoreNumber}</td>
                    <td>${score.userName}</td>
                    <td>${score.userDeptName}</td>
                    <td>${score.userCode}</td>
                    <td>${score.firstScoreItemName}</td>
                    <td>${score.firstExecutionName}</td>
                    <td>${score.myScore}</td>
                    <td>
                        <c:if test="${score.auditStatusId eq 'Passing' && ((roleFlag eq 'admin' && score.firstMiurAuditFlag ne 'Y') ||(roleFlag eq 'miur' && score.firstMiurAuditFlag eq 'Y'))}"><%-- 待审核 --%>
                            <%--<a onclick="pass('${score.recordFlow}');" style="cursor: pointer;color: blue;">审核</a>--%>
                            <%--<a onclick="back('${score.recordFlow}');" style="cursor: pointer;color: blue;">退回</a>--%>
                            <a onclick="audit('${score.recordFlow}');" style="cursor: pointer;color: blue;">编辑</a>
                            <%--<a onclick="del('${score.recordFlow}');" style="cursor: pointer;color: blue;">删除</a>--%>
                        </c:if>
                        <c:if test="${score.auditStatusId eq 'Passed' && ((roleFlag eq 'admin' && score.firstMiurAuditFlag ne 'Y') ||(roleFlag eq 'miur' && score.firstMiurAuditFlag eq 'Y'))}">
                            <a onclick="audit('${score.recordFlow}');" style="cursor: pointer;color: blue;">编辑</a>
                        </c:if>
                        <%--<c:if test="${roleFlag eq 'header' && (empty score.auditStatusId||score.auditStatusId eq 'Passing')}">--%>
                            <%--<a onclick="audit('${score.recordFlow}');" style="cursor: pointer;color: blue;">编辑</a>--%>
                        <%--</c:if>--%>
                        <a onclick="query('${score.recordFlow}');" style="cursor: pointer;color: blue;">查看</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(scoreList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>