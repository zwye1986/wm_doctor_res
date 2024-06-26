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
    <style>
        a{color:#4195c5}
    </style>
    <script>
        $(function(){
           $(".unedit").css({color:"grey",cursor:"auto"}).removeAttr("onclick");
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
        });
        function search(){
            $("#searchForm").submit();
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        
        function readFrom(flow) {
            $.ajax({
                url: "<s:url value='/osca/provincial/readFromList'/>",
                type: "get",
                data: {"subjectFlow": flow},
                dataType: "json",
                success: function (res) {
                    if (res=='${GlobalConstant.OPRE_FAIL}'){
                        jboxTip("该方案暂无表单！");
                    }else{
                        var url='<s:url value="/osca/provincial/showFrom?subjectFlow="/>'+flow;
                        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
                        jboxMessager(iframe, "查看表单详情", 1200, 600, false);
                    }
                }
            });
        }
        
        function add(flow){
            var url='<s:url value="/osca/provincial/editSubject/${roleFlag}?subjectFlow="/>'+flow;
            jboxOpen(url,"详情",1100,550);
        }
        function showPassedInfo(flow,isEdit){
            var url='<s:url value="/osca/provincial/showPassedInfo?subjectFlow="/>'+flow+"&isEdit="+isEdit;
            var msg="合格线查看";
            if(isEdit=="")
            {
                msg="合格线配置";
            }
            jboxOpen(url,"详情",1100,550);
        }
        function releases(flow,flag){
            var tip = {"release":"确认发布？","unrelease":"确认撤销发布？","delete":"确认删除？"};
            jboxConfirm(tip[flag],function(){
                var url = '<s:url value="/osca/provincial/saveSubject?editFlag="/>'+flag+"&subjectFlow="+flow;
                jboxPost(url,null,function(resp){
                    if(resp==1) jboxTip("发布成功！");
                    else jboxTip("发布失败！");
                    search();
                },null,false)
            })
        }
        function linkageSubject(dictId){
            $('#trainingSpeId').val("");//清空上次展现数据
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/osca/provincial/subjectList/${roleFlag}'/>" method="post">

            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <div class="queryDiv">
            <%--<table class="basic" style="width:100%; margin-bottom: 10px; margin-top: 10px;">--%>
                <%--<tr style="height: 54px;">--%>
                    <%--<td>--%>
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
                        <c:if test="${roleFlag eq 'province'}">
                        <div class="inputDiv">
                            <label class="qlable">发布状态：</label>
                                <select name="isReleased" class="qselect">
                                    <option value="">全部</option>
                                    <option value="Y"<c:if test="${'Y' eq param.isReleased}">selected</c:if>>已发布</option>
                                    <option value="N"<c:if test="${'N' eq param.isReleased}">selected</c:if>>未发布</option>
                                </select>
                        </div>
                        </c:if>
                        <c:if test="${roleFlag eq 'hospital'}">
                            <div class="inputDiv">
                                <label class="qlable">考核方案类型：</label>
                                    <select name="actionTypeId" class="qselect">
                                        <option value="">全部</option>
                                        <option value="ProvincialPlan"<c:if test="${'ProvincialPlan' eq param.actionTypeId}">selected</c:if>>省级方案</option>
                                        <option value="HospitalPlan"<c:if test="${'HospitalPlan' eq param.actionTypeId}">selected</c:if>>院级方案</option>
                                    </select>
                            </div>
                        </c:if>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" onclick="search()" class="searchInput">
                    </div>
                    <div class="lastDiv">
                        <input type="button" value="新&#12288;增" onclick="add('')" class="searchInput">
                    </div>
            </div>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
        </form>

        <table class="xllist" style="margin-top: 10px;">
            <tr>
                <th>序号</th>
                <th>考核方案名称</th>
                <c:if test="${roleFlag eq 'hospital'}">
                <th>考核方案类型</th>
                </c:if>
                <th>培训专业</th>
                <th>考核站数</th>
                <c:if test="${roleFlag eq 'province'}">
                <th>发布状态</th>
                </c:if>
                <th>合格线配置</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${subjectMains}" var="item" varStatus="s">
                <tr>
                    <td>${s.index+1}</td>
                    <td>${item.subjectName}</td>
                    <c:if test="${roleFlag eq 'hospital'}">
                    <td>${item.actionTypeName}</td>
                    </c:if>
                    <td>${item.trainingSpeName}</td>
                    <td>${item.stationNum}</td>
                    <c:if test="${roleFlag eq 'province'}">
                    <td>${(item.isReleased eq 'N')?"未发布":"已发布"}</td>
                    </c:if>
                    <td>
                        <c:if test="${roleFlag eq 'province'}">
                            <a style="cursor: pointer;"  onclick="showPassedInfo('${item.subjectFlow}','Y')">配置</a>
                            <c:if test="${item.isResave eq 'Y'}"><img title="请重新配置" class="icon-head" src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/></c:if>
                        </c:if>
                        <c:if test="${roleFlag eq 'hospital'}">
                            <c:if test="${item.actionTypeId eq 'ProvincialPlan'}">
                                <a style="cursor: pointer;" onclick="showPassedInfo('${item.subjectFlow}','N')">查看</a>
                            </c:if>
                            <c:if test="${item.actionTypeId eq 'HospitalPlan'}">
                                <a style="cursor: pointer;"  onclick="showPassedInfo('${item.subjectFlow}','Y')">配置</a>
                                <c:if test="${item.isResave eq 'Y'}"><img title="请重新配置" class="icon-head" src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/></c:if>
                            </c:if>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${roleFlag eq 'province'}">
                        <c:if test="${item.isReleased eq 'N'}">
                            <a style="cursor: pointer;" onclick="releases('${item.subjectFlow}','release')">发布</a>
                        </c:if>
                        <c:if test="${item.isReleased eq 'Y'}">
                            <a style="cursor: pointer;" onclick="releases('${item.subjectFlow}','unrelease')">取消发布</a>
                        </c:if>
                        &#12288;
                            <a style="cursor: pointer;" onclick="readFrom('${item.subjectFlow}')">查看</a> |
                            <a style="cursor: pointer;" onclick="add('${item.subjectFlow}')"<c:if test="${item.isReleased eq 'Y'}">class="unedit" </c:if>>编辑</a> |
                            <a style="cursor: pointer;" onclick="releases('${item.subjectFlow}','delete')"<c:if test="${item.isReleased eq 'Y'}">class="unedit" </c:if>>删除</a>
                        </c:if>
                        <c:if test="${roleFlag eq 'hospital'}">
                            <c:if test="${item.actionTypeId eq 'ProvincialPlan'}">
                                <a style="cursor: pointer;" onclick="readFrom('${item.subjectFlow}')">查看</a> |
                                <a style="cursor: pointer;" onclick="add('${item.subjectFlow}')">编辑</a>
                            </c:if>
                            <c:if test="${item.actionTypeId eq 'HospitalPlan'}">
                                <a style="cursor: pointer;" onclick="readFrom('${item.subjectFlow}')">查看</a> |
                                <a style="cursor: pointer;" onclick="add('${item.subjectFlow}')">编辑</a> |
                                <a style="cursor: pointer;" onclick="releases('${item.subjectFlow}','delete')">删除</a>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty subjectMains}">
                <tr>
                    <td colspan="20">无记录</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(subjectMains)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
