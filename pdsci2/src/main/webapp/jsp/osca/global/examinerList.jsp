
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
        function toPage(page){
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }

        function setRecordStatus(msg,userFlow,recordStatus){
            jboxConfirm("确认"+msg+"该用户吗？",function () {
                var url = "<s:url value='/osca/oscaExaminerManage/modifyRecordStatus?userFlow='/>" + userFlow+"&recordStatus="+recordStatus;
                jboxPost(url,null,function(resp){
                    jboxTip(resp);
                    var page=$("#currentPage").val();
                    toPage(page);
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
            $("#searchForm").submit();
        }
        var width=(window.screen.width)*0.6;
        var height=(window.screen.height)*0.4;
        function addNewExam(){
            jboxStartLoading();
            jboxOpen("<s:url value='/osca/oscaExaminerManage/showEditInfo'/>?manage=global", "添加考官", width, height);
        }

        function editOldExam(userFlow){
            jboxStartLoading();
            jboxOpen("<s:url value='/osca/oscaExaminerManage/showEditInfoBeforAdd'/>?userFlow="+userFlow+"&manage=global", "编辑考官", width, height);
        }

        var width1=(window.screen.width)*0.3;
        var height1=(window.screen.height)*0.3;
        function showAssignsExam(userFlow){
            jboxStartLoading();
            jboxOpen("<s:url value='/osca/oscaExaminerManage/showAssignsExam'/>?userFlow="+userFlow, "分配考点", width1, height1);
        }

        function exportExamList(){
            var orgFlow=$("select[name='orgFlow']").val();
            var titleName=$("input[name='titleName']").val();
            var userCode = $("input[name='userCode']").val();
            var userName=$("input[name='userName']").val();
            var recordStatus=$("select[name='recordStatus']").val();
            var data=orgFlow+"/pdnsp/"+titleName+"/pdnsp/"+userCode+"/pdnsp/"+userName+"/pdnsp/"+recordStatus;
            var url = "<s:url value='/osca/oscaExaminerManage/exportExamList'/>?orgFlow="+orgFlow+"&titleName="+titleName+"&userCode="+userCode+"&userName="+userName+"&recordStatus"+recordStatus;
            jboxTip("导出中....");
            $("#exportA").attr("href",url);
            $("#outToExcelSpan").click();
        }

        function showImport(){
            var url = "<s:url value='/osca/oscaExaminerManage/showImport'/>";
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
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <input id="roleId" type="hidden" value="${roleId}"/>
            <input id="teacherCurrentPage" type="hidden" value="${teacherCurrentPage}"/>
            <input id="doctorFlow" type="hidden" value="${doctorFlow}"/>
            <form id="searchForm" name="mySearchForm" action="<s:url value='/osca/oscaExaminerManage/list'/>"
                  method="post">
                <div class="queryDiv">
                    <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
                    <a class="btn" id="exportA" type="hidden"><span id="outToExcelSpan"> </span></a>
                    <div class="inputDiv">
                        <label class="qlable">考点名称：</label>
                        <select name="orgFlow" class="qselect">
                            <option value="All">全部</option>
                            <c:forEach var="org" items="${orgSpeList}">
                                <option value="${org.orgFlow}"
                                        <c:if test="${org.orgFlow == param.orgFlow}">selected</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </select>
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
                        <label class="qlable">用户名：</label>
                        <input class="qtext" name="userCode" value="${param.userCode}" type="text" />
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">姓&#12288;&#12288;名：</label>
                        <input class="qtext" name="userName" value="${param.userName}" type="text" />
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">职&#12288;&#12288;称：</label>
                        <input class="qtext" name="titleName" value="${param.titleName}" type="text" />
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">考官状态：</label>
                        <select name="recordStatus" class="qselect">
                            <option value="">全部</option>
                            <option value="Y" <c:if test="${param.recordStatus == 'Y'}">selected</c:if>>启用</option>
                            <option value="N" <c:if test="${param.recordStatus == 'N'}">selected</c:if>>停用</option>
                        </select>
                    </div>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
                    </div>
                </div>
                <div class="funcDiv">
                    <input type="button" value="新&#12288;增" class="search" onclick="addNewExam()"/>
                    <input type="button" value="导&#12288;入" class="search" onclick="showImport()"/>
                    <input type="button" value="导&#12288;出" class="search" onclick="exportExamList()"/>
                    <input type="button" value="一键停用" class="search" onclick="setAllRecordStatus()"/>
                </div>
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
                        <th  style="text-align: center;" colspan="2">专业（类型）</th>
                        <th  style="text-align: center;">联系方式</th>
                        <th  style="text-align: center;">所在单位</th>
                        <th  style="text-align: center;">所在考点</th>
                        <th  style="text-align: center;">操作</th>
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
                                <td>
                                    <c:choose>
                                        <c:when test="${examiner.orgName ==null }">
                                            <a style="cursor:pointer;color:#4195c5;" onclick="showAssignsExam('${examiner.userFlow}')">待分配</a>
                                        </c:when>
                                        <c:otherwise>
                                            ${examiner.orgName}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
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