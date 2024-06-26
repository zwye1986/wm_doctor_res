<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script>
        function search(){
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        function edit(userFlow){
            jboxOpen("<s:url value='/osca/personnel/editStudent'/>?userFlow="+userFlow+"&editFlag=Y","详细信息",900,500,true);
        }
        <%--function add(){--%>
            <%--jboxOpen("<s:url value='/osca/personnel/editStudent'/>&addFlag=Y","详细信息",900,500,true);--%>
        <%--}--%>
        function linkageSubject(dictId){
            $('#trainingSpeId').val("");//清空上次展现数据
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
        }
        $(function(){
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
        })
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
        function importExl(){
            var url = "<s:url value='/jsp/osca/base/importUsers.jsp'/>";
            jboxOpen(url, "人员导入", 700, 250);
        }
        function exportExl(){
            var url = "<s:url value='/osca/personnel/exportStudents'/>";
            jboxTip("导出中…………");
            jboxExp($("#searchForm"),url);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/osca/personnel/studentList'/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <div class="queryDiv">
                <div class="inputDiv">
                    姓&#12288;&#12288;名：
                    <input name="userName" class="xltext" value="${param.userName}">
                </div>
                <div class="inputDiv">
                    培训类型：
                    <select name="trainingTypeId" class="xlselect" onchange="linkageSubject(this.value)">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                            <option value="${dict.dictId}" ${param.trainingTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="inputDiv">
                    培训专业：
                    <c:set value="OscaTrainingType.${param.trainingTypeId}" var="trainingTypeClass"></c:set>
                    <select id="trainingSpeId" name="trainingSpeId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                            <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
                            <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                <option class="${scope.dictTypeId}" value="${scope.dictId}" ${(param.trainingSpeId eq scope.dictId && trainingTypeClass eq scope.dictTypeId)?'selected':''}>${scope.dictName}</option>
                            </c:forEach>
                        </c:forEach>
                    </select>
                </div>
                <div class="inputDiv">
                     培训年级：
                     <input name="sessionNumber" class="xltext" value="${param.sessionNumber}" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})">
                </div>
                <div class="inputDiv">
                     证件号码：
                     <input name="idNo" class="xltext" value="${param.idNo}">
                </div>
                <div class="inputDiv">
                    学员状态：
                    <select name="statusId" class="xlselect">
                        <option value="">全部</option>
                        <option value="${userStatusEnumActivated.id}" ${param.statusId eq userStatusEnumActivated.id?'selected':''}>已启用</option>
                        <option value="${userStatusEnumLocked.id}" ${param.statusId eq userStatusEnumLocked.id?'selected':''}>已停用</option>
                    </select>
                </div>
                <div class="inputDiv" style="min-width: 400px;max-width: 400px;text-align: left">
                    <input type="button" value="查&#12288;询" onclick="search()" class="search">
                    <c:if test="${isShow eq 'Y'}">
                        <input type="button" value="新&#12288;增" onclick="edit('')" class="search">
                    </c:if>
                    <input type="button" value="导&#12288;出" onclick="exportExl('')" class="search">
                    <c:if test="${isShow eq 'Y'}">
                    <input type="button" value="导&#12288;入" onclick="importExl('')" class="search">
                    </c:if>
                </div>
            </div>
        </form>

        <table class="xllist" style="margin-top: 10px;">
            <colgroup>
                <col width="8%"/>
                <col width="12%"/>
                <col width="8%"/>
                <col width="12%"/>
                <col width="5%"/>
                <col width="15%"/>
                <col width="10%"/>
                <col width="8%"/>
                <col width="12%"/>
                <c:if test="${isShow eq 'Y'}">
                <col width="10%"/>
                </c:if>
            </colgroup>
            <tr>
                <th>姓名</th>
                <th>培训基地</th>
                <th>培训类型</th>
                <th>培训专业</th>
                <th>培训年级</th>
                <th>证件号码</th>
                <th>邮箱</th>
                <th>手机号码</th>
                <th>所在单位</th>
                <c:if test="${isShow eq 'Y'}">
                <th>操作</th>
                </c:if>
            </tr>
            <c:forEach items="${resultMapList}" var="item" varStatus="s">
                <tr>
                    <td>${item["USER_NAME"]}</td>
                    <td>${item["ORG_NAME"]}</td>
                    <td>${item["TRAINING_TYPE_NAME"]}</td>
                    <td>${item["TRAINING_SPE_NAME"]}</td>
                    <td>${item["SESSION_NUMBER"]}</td>
                    <td>${item["ID_NO"]}</td>
                    <td>${item["USER_EMAIL"]}</td>
                    <td>${item["USER_PHONE"]}</td>
                    <td>${item["WORK_ORG_NAME"]}</td>
                    <c:if test="${isShow eq 'Y'}">
                    <td>
                        <c:if test="${item['OSCA_STUDENT_SUBMIT'] eq 'Y'}">
                            <a style="color: #4195c5;cursor: pointer;" onclick="edit('${item["USER_FLOW"]}')">编辑 </a>
                            |
                            <c:if test="${item['STATUS_ID'] eq userStatusEnumActivated.id}">
                                <a style="color: #4195c5;cursor: pointer;" onclick="lock('${item["USER_FLOW"]}')">停用</a>
                            </c:if>
                            <c:if test="${item['STATUS_ID'] eq userStatusEnumLocked.id}">
                                <a style="color: #4195c5;cursor: pointer;" onclick="activate('${item["USER_FLOW"]}')">启用</a>
                            </c:if>
                        </c:if>
                        <c:if test="${item['OSCA_STUDENT_SUBMIT'] ne 'Y'}">
                            -
                        </c:if>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty resultMapList}">
                <tr>
                    <td colspan="20">无记录</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
