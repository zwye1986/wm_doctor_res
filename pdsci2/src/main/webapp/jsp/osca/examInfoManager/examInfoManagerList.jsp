
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>

    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <style type="text/css">
        .table {
            border: 1px solid #e3e3e3;
        }
        .table tr:nth-child(2n) {
            background-color: #fcfcfc;
            transition: all 0.125s ease-in-out 0s;
        }
        .table tr:hover {
            background: #fbf8e9 none repeat scroll 0 0;
        }
        .table th, .table td {
            border-bottom: 1px solid #e3e3e3;
            border-right: 1px solid #e3e3e3;
            text-align: center;
        }
        .table th {
            background: rgba(0, 0, 0, 0) url("<s:url value='/jsp/res/disciple/images/table.png'/>") repeat-x scroll 0 0;
            color: #585858;
            height: 30px;
        }
        .table td {
            height: 30px;
            line-height: 25px;
            text-align: center;
            word-break: break-all;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function(){
        });
        function toPage(page){
            $("[name='orgFlow']").attr("disabled",false);
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }

        function lock(userFlow){
            jboxConfirm("确认停用该用户吗？停用后该用户将不能登录系统！",function () {
                var url = "<s:url value='/sys/user/lock?userFlow='/>"+userFlow;
                jboxGet(url,null,function(){
                    search();
                });
            });
        }
        function activate(userFlow){
            jboxConfirm("确认启用该用户吗？",function () {
                var url = "<s:url value='/sys/user/activate?userFlow='/>"+userFlow;
                jboxGet(url,null,function(){
                    search();
                });
            });
        }

        function setAllRecordStatus(){
            var data="";
            $("td input[name='userFlowValue']").each(function(){
                var chVal=$(this).attr("value");
                data=data+"/pdnsp/"+chVal;
            });
            jboxConfirm("确认停用该页面所有用户吗？",function () {
                var url = "<s:url value='/osca/oscaExaminerManage/modifyAllRecordStatus?data='/>" + data;
                jboxPost(url,null,function(resp){
                    jboxTip(resp);
                    var page=$("#currentPage").val();
                    toPage(page);
                });
            });
        }

        function search(){
            jboxStartLoading();
            $("[name='orgFlow']").attr("disabled",false);
            $("#searchForm").submit();
        }
        var width=(window.screen.width)*0.6;
        var height=(window.screen.height)*0.4;
        function addNewExamManage(){
            jboxStartLoading();
            jboxOpen("<s:url value='/osca/oscaExaminerManage/showEditInfo'/>?manage=manage", "添加考官", width, height);
        }

        function editOldExam(userFlow){
            jboxStartLoading();
            jboxOpen("<s:url value='/osca/oscaExaminerManage/showEditInfoBeforAdd'/>?userFlow="+userFlow+"&manage=manage", "编辑考官", width, height);
        }

        function exportExamList(){
            <c:if test="${empty examinerList}">
                jboxTip("无数据");
            </c:if>
            var url = "<s:url value='/osca/oscaExaminerManage/exportManageExamList'/>";
            jboxTip("导出中....");
            $("[name='orgFlow']").attr("disabled",false);
            jboxExp($("#searchForm"),url);
        }

        function showImport(){
            var url = "<s:url value='/osca/oscaExaminerManage/showManageImport'/>";
            typeName="导入考官信息";
            jboxOpen(url, typeName, 380, 180);
        }

        function linkageSubject(dictId){
            $('#trainingSpeId').val("");//清空上次展现数据
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
        }

        $(document).ready(function(){
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <input id="roleId" type="hidden" value="${roleId}"/>
            <input id="teacherCurrentPage" type="hidden" value="${teacherCurrentPage}"/>
            <input id="doctorFlow" type="hidden" value="${doctorFlow}"/>
            <form id="searchForm" name="mySearchForm" action="<s:url value='/osca/oscaExaminerManage/managerList'/>"
                  method="post">
                <div class="queryDiv">
                    <input type="hidden" id="currentPage" name="currentPage" value="">
                    <div class="inputDiv">
                        <label class="qlable">所在考点：</label>
                        <%--<c:choose>--%>
                            <%--<c:when test="${manage eq 'manage'}">--%>
                                <select name="orgFlow" class="qselect" style="margin-left: 8px;" disabled="disabled">
                                    <option value="${orgFlow}">${orgName}</option>
                                </select>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--<select name="orgFlow"  class="qselect" style="border: 1;margin-left: 8px;">--%>
                                    <%--<option value="">全部</option>--%>
                                    <%--<c:forEach var="org" items="${orgSpeList}">--%>
                                        <%--<option value="${org.orgFlow}"--%>
                                                <%--<c:if test="${org.orgFlow eq sysUser.orgFlow}">selected</c:if>>${org.orgName}--%>
                                        <%--</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">用户名：</label>
                        <input class="qtext" name="userCode" value="${param.userCode}" type="text" />
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">姓名：</label>
                        <input class="qtext" name="userName" value="${param.userName}" type="text" />
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">所在单位：</label>
                        <input class="qtext" name="workOrgName" value="${param.workOrgName}" type="text" />
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">考核人员类型：</label>
                        <select name="trainingTypeId" class="qselect" onchange="linkageSubject(this.value)">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                                <option value="${dict.dictId}" ${param.trainingTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">考核专业：</label>
                        <c:set value="OscaTrainingType.${param.trainingTypeId}" var="trainingTypeClass"></c:set>
                        <select id="trainingSpeId" name="trainingSpeId" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                                <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
                                <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                    <option class="${scope.dictTypeId}" value="${scope.dictId}" ${(param.trainingSpeId eq scope.dictId && trainingTypeClass eq scope.dictTypeId)?'selected':''}>${scope.dictName}</option>
                                </c:forEach>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">考官状态：</label>
                        <select name="statusId" class="qselect">
                            <option value="">全部</option>
                            <option value="${userStatusEnumActivated.id}" ${param.statusId eq userStatusEnumActivated.id?'selected':''}>已启用</option>
                            <option value="${userStatusEnumLocked.id}" ${param.statusId eq userStatusEnumLocked.id?'selected':''}>已停用</option>
                        </select>
                    </div>
                    <div class="lastDiv" style="min-width: 180px;max-width: 180px;">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
                        <input type="button" value="导&#12288;出" class="searchInput" onclick="exportExamList()"/>
                    </div>
                </div>
                <c:if test="${isShow eq 'Y'}">
                <div class="funcDiv">
                	<input type="button" value="新&#12288;增" class="search" onclick="addNewExamManage()"/>
                    <input type="button" value="导&#12288;入" class="search" onclick="showImport()"/>
                    <input type="button" value="一键停用" class="search" onclick="setAllRecordStatus()"/>
                </div>
                </c:if>
            </form>


            <div id="base">
                <table  class="xllist">
                    <colgroup>
                        <col width="5%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="5%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                    </colgroup>
                    <tr>
                        <th  style="text-align: center;">序号</th>
                        <th  style="text-align: center;">用户名</th>
                        <th  style="text-align: center;">姓名</th>
                        <th  style="text-align: center;">性别</th>
                        <th  style="text-align: center;">职称</th>
                        <th  style="text-align: center;" colspan="2">类型（专业）</th>
                        <th  style="text-align: center;">联系方式</th>
                        <th  style="text-align: center;">所在单位</th>
                        <th  style="text-align: center;">所在考点</th>
                        <c:if test="${isShow eq 'Y'}">
                        <th  style="text-align: center;">操作</th>
                        </c:if>
                    </tr>

                    <c:if test="${not empty examinerList}">
                        <c:forEach items="${examinerList}" var="examiner" varStatus="num">
                            <tr>
                                <td>${num.count}<input name="userFlowValue" type="hidden" value="${examiner.userFlow}"/></td>
                                <td>${examiner.userCode}</td>
                                <td>${examiner.userName}</td>
                                <td>${examiner.sexName}</td>
                                <td>${examiner.titleName}</td>
                                <td colspan="2">${typeSpeMap[examiner.userFlow]}</td>
                                <td>${examiner.userPhone}</td>
                                <td>${examiner.workOrgName}</td>
                                <td>${examiner.orgName}</td>
                                <c:if test="${isShow eq 'Y'}">
                                <td>
                                    <a style="cursor:pointer;color:#4195c5;" onclick="editOldExam('${examiner.userFlow}')" >编辑</a>
                                    |
                                    <c:if test="${examiner.statusId eq userStatusEnumActivated.id}">
                                        <a style="cursor:pointer;color:#4195c5;" onclick="lock('${examiner.userFlow}')">停用</a>
                                    </c:if>
                                    <c:if test="${examiner.statusId eq userStatusEnumLocked.id}">
                                        <a style="cursor:pointer;color:#4195c5;" onclick="activate('${examiner.userFlow}')">启用</a>
                                    </c:if>
                                </td>
                                </c:if>
                            </tr>

                        </c:forEach>
                    </c:if>

                    <c:if test="${empty examinerList}">
                        <tr><td colspan="99">暂无记录</td></tr>
                    </c:if>
                </table>
                <div>
                    <c:set var="pageView" value="${pdfn:getPageView(examinerList)}" scope="request"></c:set>
                    <pd:pagination toPage="toPage"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>