
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
    <script type="text/javascript">
        function toPage(page){
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function search(){
            jboxStartLoading();
            $("#searchForm").submit();
            toPage();
        }
        function editManager(userFlow,newFlag){
            jboxOpen("<s:url value='/osca/orgSpeGlobal/editManager'/>?userFlow="+userFlow+"&newFlag="+newFlag, "详情", 650, 210);
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
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/osca/orgSpeGlobal/managerlist'/>"
                  method="post">
                <div class="queryDiv">
                    <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
                    <div class="inputDiv">
                        <label class="qlable">地&#12288;&#12288;市：</label>
                        <select name="orgCityId" class="qselect">
                            <option value="">全部</option>
                            <c:forEach var="orgCity" items="${orgCityList}">
                                <c:if test="${not empty orgCity.orgCityId}">
                                    <option value="${orgCity.orgCityId}"
                                            <c:if test="${orgCity.orgCityId eq param.orgCityId}">selected</c:if>>${orgCity.orgCityName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">基地名称：</label>
                        <input class="qtext" name="orgName" value="${param.orgName}" type="text" />
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">用&ensp;户&ensp;名：</label>
                        <input class="qtext" name="userCode" value="${param.userCode}" type="text"/>
                    </div>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="search();"/>
                    </div>
                    <div class="lastDiv">
                        <input type="button" value="新&#12288;增" class="searchInput" onclick="editManager('','Y');"/>
                    </div>
                </div>
            </form>
            <div id="base">
                <table  class="xllist">
                    <colgroup>
                        <col width="5%"/>
                        <col width="15%"/>
                        <col width="30%"/>
                        <col width="15%"/>
                        <col width="15%"/>
                    </colgroup>
                    <tr>
                        <th  style="text-align: center;">序号</th>
                        <th  style="text-align: center;">用户名</th>
                        <th  style="text-align: center;">基地名称</th>
                        <th  style="text-align: center;">所属地市</th>
                        <th  style="text-align: center;">操作</th>
                    </tr>
                    <c:if test="${not empty resultMapList}">
                        <c:forEach items="${resultMapList}" var="result" varStatus="s">
                            <tr>
                                <td>${s.count}</td>
                                <td>${result['USER_CODE']}</td>
                                <td>${result['ORG_NAME']}</td>
                                <td>${result['ORG_CITY_NAME']}</td>
                                <td>
                                    <a onclick="editManager('${result["USER_FLOW"]}','N')" style="color: #4195c5;cursor: pointer">编辑</a> |
                                    <c:if test="${result['STATUS_ID'] eq userStatusEnumActivated.id}">
                                        <a style="color: #4195c5;cursor: pointer;" onclick="lock('${result["USER_FLOW"]}')">停用</a>
                                    </c:if>
                                    <c:if test="${result['STATUS_ID'] eq userStatusEnumLocked.id}">
                                        <a style="color: #4195c5;cursor: pointer;" onclick="activate('${result["USER_FLOW"]}')">启用</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty resultMapList}">
                        <tr><td colspan="99">暂无记录</td></tr>
                    </c:if>
                </table>
                <div>
                    <c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"></c:set>
                    <pd:pagination toPage="toPage"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>