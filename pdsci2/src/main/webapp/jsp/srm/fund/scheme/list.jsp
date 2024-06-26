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
        $(function () {
            searchProjCategory();
        });
        function search() {
            jboxStartLoading();
            $('#schemeForm').submit();
        }

        function cancel() {
            jboxStartLoading();
            window.location.reload(true);
        }
        function edit(schemeFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/fund/scheme/edit'/>?schemeFlow=" + schemeFlow, "修改预算方案", 500, 200);
        }
        function editProjSource(schemeFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/fund/scheme/editProjSource'/>?schemeFlow=" + schemeFlow, "修改预算方案", 500, 300);
        }
        function setBudgetItem(schemeFlow) {
            var w = $('#mainright').width();
            var url = '<s:url value="/srm/fund/scheme/itemList?schemeFlow="/>' + schemeFlow;
            var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='550px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
            jboxMessager(iframe, '预算项设置', w, 550);
        }
        function startScheme(schemeFlow) {
            var url = '<s:url value="/srm/fund/scheme/startScheme"/>?schemeFlow=' + schemeFlow;
            jboxConfirm("确认启用？", function () {
                jboxStartLoading();
                jboxGet(url, null, function () {
                    window.parent.frames['mainIframe'].location.reload(true);
                }, null, true);
            });
        }
        function add() {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/fund/scheme/edit'/>", "添加预算方案", 500, 200);
        }
        function stopScheme(schemeFlow) {
            var url = '<s:url value="/srm/fund/scheme/stopScheme"/>?schemeFlow=' + schemeFlow;
            jboxConfirm("确认停用？", function () {
                jboxStartLoading();
                jboxGet(url, null, function () {
                    window.parent.frames['mainIframe'].location.reload(true);
                }, null, true);
            });
        }
        function updateScheme(schemeFlow, obj) {
            var tr = $(obj).parent("td").parent('tr');
            var tdInput = tr.find('td').eq(0);
            var tdSelect = tr.find('td').eq(1);
            var schemeName = tdInput.find("input").val();
            var projTypeId = tdSelect.find('select[name="projTypeId"]').val();
            var scheme = {
                "schemeFlow": schemeFlow,
                "schemeName": schemeName,
                "projTypeId": projTypeId,
            };
            var url = '<s:url value="/srm/fund/scheme/updateScheme"/>';
            jboxStartLoading();
            jboxPost(url, scheme, function () {
                $("#schemeForm").submit();
            }, null, true);

        }
        function searchProjCategory() {
            var dictFlow = $("select[name='projFirstSourceId'] option:selected").attr("dictFlow");
            var dictTypeId = $("#projFirstSourceId").val();
            var data = {
                dictFlow: dictFlow,
                dictTypeId: dictTypeId
            };
            var url = "<s:url value='/sys/dict/getCategoryDictByDeclarer'/>";
            jboxPost(url, data, function (data) {
                //清空原类别！
                $("select[name=projSecondSourceId] option[value != '']").remove();
                var dataObj = data;
                for (var i = 0; i < dataObj.length; i++) {
                    var cId = dataObj[i].dictId;
                    var cName = dataObj[i].dictName;
                    $option = $("<option></option>");
                    $option.attr("value", cId);
                    $option.text(cName);
                    if ("${param.projSecondSourceId}" == cId) {
                        $option.attr("selected", true);
                    }
                    $("select[name='projSecondSourceId']").append($option);
                }
            }, null, false);
        }
        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            search();
        }
    </script>
    <style type="text/css">
        .xllist a {
            color: #00a0ff;
        }

        .xllist a:hover {
            color: #ff821a;
        }
    </style>
</head>
<body>
<div class="mainright" id="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="schemeForm" action="<s:url value="/srm/fund/scheme/list"/>" method="post">
                <input id="currentPage" type="hidden" name="currentPage" value=""/>
                <p>
                    方案名称
                    <input type="text" name="schemeName" class="xltext" value="${param.schemeName}"/>
                    <c:choose>
                        <c:when test="${(sysCfgMap['srm_for_use'] eq 'local') and (sysCfgMap['srm_local_type'] eq 'Y')}">
                            项目类型:
                            <select name="projTypeId">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumProjTypeList }" var="dict">
                                    <option value="${dict.dictId}"
                                            <c:if test="${dict.dictId eq param.projTypeId}">selected="selected"</c:if> >${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </c:when><%--sysCfgMap['srm_local_type']=='common'--%>
                        <c:otherwise>
                            一级来源：
                            <select id="projFirstSourceId" name="projFirstSourceId" class="xlselect"
                                    onchange="searchProjCategory();">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                                    <option dictFlow="${dict.dictFlow}" value="${dict.dictId}"
                                            <c:if test="${param.projFirstSourceId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                            二级来源：
                            <select name="projSecondSourceId" class="xlselect">
                                <option value="">请选择</option>
                            </select>
                        </c:otherwise>
                    </c:choose>

                    <input type="button" class="search" onclick="search();" value="查&#12288;询">
                    <input type="button" class="search" onclick="add();" value="添&#12288;加">
                </p>
            </form>
        </div>
        <table class="xllist" id="mubiao">
            <tr style="height: 20px">
                <th width="20%">方案名称</th>
                <c:choose>
                    <c:when test="${(sysCfgMap['srm_for_use'] eq 'local') and (sysCfgMap['srm_local_type'] eq 'Y')}">
                        <th width="40%">项目类型</th>
                    </c:when>
                    <c:otherwise>
                        <th width="40%">项目来源</th>
                        <%--<th>二级来源</th>--%>
                    </c:otherwise>
                </c:choose>
                <th width="10%">方案状态</th>
                <th width="280px">操作</th>
            </tr>
            <c:forEach var="scheme" items="${schemeList }">
                <tr>
                    <td>${scheme.schemeName}</td>
                    <c:choose>
                        <c:when test="${(sysCfgMap['srm_for_use'] eq 'local') and (sysCfgMap['srm_local_type'] eq 'Y')}">
                            <td id="${scheme.projTypeId }">${scheme.projTypeName}</td>
                        </c:when>
                        <c:otherwise>
                            <%--<td>${scheme.projFirstSourceName}</td>--%>
                            <%--<td>${scheme.projSecondSourceName}</td>--%>
                            <td style="text-align: left">
                                <div style="max-height: 150px;overflow: auto">
                                    <c:forEach items="${map[scheme.schemeFlow]}" var="projSource">
                                        <p>
                                            一级来源：${projSource.projFirstSourceName}&#12288;&#12288;二级来源：${projSource.projSecondSourceName}</p>
                                    </c:forEach>
                                </div>
                            </td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <c:choose>
                            <c:when test="${scheme.recordStatus eq 'Y'}">
                                启用
                            </c:when>
                            <c:otherwise>
                                停用
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="javascript:void(0)" onclick="updateScheme('${scheme.schemeFlow }',this);"
                           style="display: none;">保存</a>
                        <a href="javascript:void(0)" onclick="cancel();" style="display: none;">取消</a>
                        <%--徐州中心医院经费方案直接关联到项目类型--%>
                        <c:if test="${(sysCfgMap['srm_for_use'] eq 'local') and (sysCfgMap['srm_local_type'] ne 'Y')}">
                        <a href="javascript:void(0)" onclick="editProjSource('${scheme.schemeFlow}');">关联项目来源</a>
                        </c:if>
                        <a href="javascript:void(0)" onclick="edit('${scheme.schemeFlow}');">编辑</a>

                        <c:choose>
                            <c:when test="${scheme.recordStatus eq 'Y'}">
                                <a href="javascript:stopScheme('${scheme.schemeFlow }');">停用</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0)" onclick="startScheme('${scheme.schemeFlow }');">启用</a>
                            </c:otherwise>
                        </c:choose>
                        <a href="javascript:void(0);" onclick="setBudgetItem('${scheme.schemeFlow}')">预算项设置</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(schemeList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>