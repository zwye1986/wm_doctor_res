<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
    </jsp:include>
</head>

<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
<script type="text/javascript">
    function addItem() {
        var w = $('.mainright').width();
        var h = $('.mainright').height();
        var url = "<s:url value='/srm/proj/add/addProjInfo'/>";
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='550px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, '立项信息编辑', w, 550);
    }
    function searchProj() {
        jboxStartLoading();
        $("#searchForm").submit();
    }
    function resProj() {
        jboxStartLoading();
        $("#paramForm").submit();
    }
    function auditList(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/mine/auditList?projFlow='/>" + projFlow, "审核信息", 900, 600);
    }
    function editRecFiles(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/add/editRecFiles?projFlow='/>" + projFlow, "项目附件信息", 700, 500);
    }
    function audit(projFlow) {
        $("#recForm").attr("action", "<s:url value='/srm/proj/add/recList?projFlow='/>" + projFlow);
        jboxStartLoading();
        $("#recForm").submit();
    }

    //加载申请单位
    function loadApplyOrg() {
        //清空
        var org = $('#org');
        org.html('<option value="">请选择</option>');
        var chargeOrgFlow = $('#chargeOrg').val();
        if (!chargeOrgFlow) {
            return;
        }
        var url = "<s:url value='/sys/org/loadApplyOrg'/>?orgFlow=" + chargeOrgFlow;
        jboxStartLoading();
        jboxGet(url, null, function (data) {
            $.each(data, function (i, n) {
                org.append('<option value="' + n.orgFlow + '">' + n.orgName + '</option>');
            });
        }, null, false);
    }

    //加载项目状态枚举
    function loadProjStatusId() {
        var paramStatusId = '${param.projStatusId}';
        var status = $("#projStatusId");
        status.html('<option value="">请选择</option>');
        var stage = $("#projStageId").val();
        if (stage) {
            var url = "<s:url value='/srm/proj/add/loadProjStatus'/>?projStageId=" + stage;
            jboxStartLoading();
            jboxGet(url, null, function (data) {
                for (key in data) {
                    var sel = "";
                    if (key == paramStatusId) {
                        sel = "selected"
                    }
                    status.append('<option value="' + key + '" ' + sel + '>' + data[key] + '</option>');
                }
            }, null, false);
        }
    }

    $(document).ready(function () {
        loadProjStatusId();
        searchProjCategory();
    });
    function searchProjCategory(){
        var dictFlow = $("select[name='projDeclarerFlow'] option:selected").attr("dictFlow");
        var dictTypeId = $("#projDeclarerFlow").val();
        var data = {
            dictFlow : dictFlow,
            dictTypeId : dictTypeId
        };
        var url = "<s:url value='/sys/dict/getCategoryDictByDeclarer'/>";
        jboxPost(url , data , function(data){
            //清空原类别！
            $("select[name=projSecondSourceId] option[value != '']").remove();
            var dataObj = data;
            for(var i =0;i<dataObj.length;i++){
                var cId =dataObj[i].dictId;
                var cName =dataObj[i].dictName;
                $option =$("<option></option>");
                $option.attr("value",cId);
                $option.text(cName);
                if("${param.projSecondSourceId}" == cId){
                    $option.attr("selected",true);
                }
                $("select[name='projSecondSourceId']").append($option);
            }
        } , null , false);
    }
    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchProj();
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
        initDept();
    });

    function exportExcel() {
        var url = "<s:url value='/srm/proj/add/exportExcel/${sessionScope.projListScope}/${sessionScope.projCateScope}'/>";
        jboxSubmit($('#searchForm'), url, null, null);
        jboxEndLoading();
    }

</script>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="paramForm"
                  action="<s:url value="/srm/proj/add/toExcel/${sessionScope.projListScope}/${sessionScope.projCateScope}?flag=y" />"
                  method="post">
                <input type="hidden" name="projYear" value="${param.projYear}"/>
                <input type="hidden" name="projTypeId" value="${param.projTypeId}"/>
                <input type="hidden" name="projNo" value="${param.projNo}"/>
                <input type="hidden" name="projStageId" value="${param.projStageId}"/>
                <input type="hidden" name="projStatusId" value="${param.projStatusId}"/>
                <input type="hidden" name="applyOrgFlow" value="${param.applyOrgFlow}"/>
                <input type="hidden" name="chargeOrgFlow" value="${param.chargeOrgFlow}"/>
                <input type="hidden" name="orgFlow" value="${param.orgFlow}"/>
                <input type="hidden" name="projName" value="${param.projName}"/>
            </form>
            <form id="searchForm"
                  action="<s:url value="/srm/proj/add/list/${sessionScope.projListScope}/${sessionScope.projCateScope}?flag=y" />"
                  method="post">
                <%--院外项目--%>
                <input type="hidden" name="projTypeId" value="jsszyy.ywxm" />

                <%--<div class="searchDiv">
                    年&#12288;&#12288;份：
                    <input type="text" class="xltext ctime" name="projYear" readonly="readonly"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.projYear }"/>
                </div>--%>
                    <div class="searchDiv">
                        年份区间：
                        <input type="text" class="xltext ctime" name="startYear" readonly="readonly" style="width: 69px"
                               onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.startYear }<c:if test="${empty param.startYear and defaultTerm eq 'Y'}">${pdfn:getCurrYear()}</c:if>"/> -
                        <input type="text" class="xltext ctime" name="endYear" readonly="readonly" style="width: 69px"
                               onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.endYear }<c:if test="${empty param.endYear and defaultTerm eq 'Y'}">${pdfn:getCurrYear()}</c:if>"/>
                    </div>
                <div class="searchDiv">
                        科&#12288;&#12288;室：
                        <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                               value="${param.applyDeptName}" autocomplete="off"/>
                        <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}" type="text"
                               hidden style="margin-left: 0px;"/>
                </div>
                <%--<div class="searchDiv">
                    项目类型：
                    <select class="xlselect" name="projTypeId">
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumProjTypeList}">
                            <option value="${dict.dictId}"
                                    <c:if test='${param.projTypeId==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>--%>
                <div class="searchDiv">
                    一级来源：
                    <select id="projDeclarerFlow" name="projDeclarerFlow" class="xlselect"  onchange="searchProjCategory();">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                            <option dictFlow="${dict.dictFlow}" value="${dict.dictId}" <c:if test="${param.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    二级来源：
                    <select name="projSecondSourceId" class="xlselect">
                        <option value="">请选择</option>
                    </select>
                </div>
                <c:if test='${sessionScope.projListScope=="charge"}'>
                    <div class="searchDiv">
                        申报单位：
                        <select name="applyOrgFlow" class="xlselect">
                            <option value="">请选择</option>
                            <c:forEach var="org" items="${orgList}">
                                <option value="${org.orgFlow}"
                                        <c:if test="${param.applyOrgFlow==org.orgFlow}">selected</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>

                <c:if test='${sessionScope.projListScope=="global" }'>
                    <div class="searchDiv">
                        主管部门：
                        <select id="chargeOrg" name="chargeOrgFlow" onchange="loadApplyOrg();" class="xlselect">
                            <option value="">请选择</option>
                            <c:forEach var="chargeOrg" items="${chargeOrgList}">
                                <option value="${chargeOrg.orgFlow}"
                                        <c:if test="${param.chargeOrgFlow==chargeOrg.orgFlow}">selected</c:if>>${chargeOrg.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="searchDiv">
                        申报单位：
                        <select id="org" name="applyOrgFlow" class="xlselect">
                            <option value="">请选择</option>
                            <c:forEach var="org" items="${orgList}">
                                <option value="${org.orgFlow}"
                                        <c:if test="${param.applyOrgFlow==org.orgFlow}">selected</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>
                <div class="searchDiv">
                    项目名称：&nbsp;<input type="text" name="projName" value="${param.projName}" class="xltext"/>
                </div>
                <div class="searchDiv">
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    <input type="button" class="search" onclick="searchProj();" value="查&#12288;询">
                    <input type="button" class="search" onclick="addItem();" value="添&#12288;加">
                    <c:if test="${pdfn:contains(param.projTypeId, 'yhwsj')  and  sessionScope.projListScope eq 'global'}">
                        <input type="button" class="search" onclick="exportExcel();" value="导出Excel">
                    </c:if>
                    <!-- <input type="button" class="search" onclick="resProj();" value="导出Excel"> -->
                </div>
            </form>
        </div>
        <form id="recForm" method="post">
            <table class="xllist">
                <thead>
                <tr>
                    <th width="5%">年份</th>
                    <th width="10%">项目类别</th>
                    <th>项目名称</th>
                    <%--<th >立项编号</th>--%>
                    <th width="10%">起止时间</th>
                    <th width="7%">项目负责人</th>
                    <th width="10%">当前阶段</th>
                    <th width="10%">当前状态</th>
                    <c:if test="${sessionScope.projListScope eq 'charge' or sessionScope.projListScope eq 'global'}">
                        <th width="15%">申报单位</th>
                    </c:if>
                    <th width="15%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty projList}">
                    <tr>
                        <td colspan="9">无记录</td>
                    </tr>
                </c:if>
                <c:forEach items="${projList}" var="proj">
                    <c:choose>
                        <c:when test="${sessionScope.projListScope == GlobalConstant.PROJ_STATUS_SCOPE_LOCAL}">

                            <tr>
                                <td><span>${proj.projYear }</span></td>
                                <td>${proj.projTypeName }</td>
                                <td><a style="color:blue"
                                       href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>"
                                       target="_blank">${proj.projName}</a></td>
                                    <%--<td>${proj.projNo}</td>--%>
                                <td>${proj.projStartTime }~<br/>${proj.projEndTime }</td>
                                <td>${proj.applyUserName}</td>
                                <td>${proj.projStageName }</td>
                                <td>${proj.projStatusName }</td>
                                <c:if test="${sessionScope.projListScope eq 'charge' or sessionScope.projListScope eq 'global'}">
                                    <td>${proj.applyOrgName }</td>
                                </c:if>
                                <td>[<a href="javascript:void(0)" onclick="auditList('${proj.projFlow}');">查看过程</a>]&nbsp;
                                         <%--[<a href="javascript:void(0)" onclick="editRecFiles('${proj.projFlow}');">附件管理</a>]--%>
                                     </td>
                            </tr>

                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${proj.projStageId == projStageEnumApply.id and  proj.projStatusId == projApplyStatusEnumApply.id }"></c:when>
                                <c:otherwise>
                                    <tr>
                                        <td><span>${proj.projYear }</span></td>
                                        <td>${proj.projTypeName }</td>
                                        <td><a style="color:blue"
                                               href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>"
                                               target="_blank">${proj.projName}</a></td>
                                        <td>${proj.projNo}</td>
                                        <td>${proj.projStartTime }~<br/>${proj.projEndTime }</td>
                                        <td>${proj.applyUserName}</td>
                                        <td>${proj.projStageName }</td>
                                        <td>${proj.projStatusName }</td>
                                        <c:if test="${sessionScope.projListScope eq 'charge' or sessionScope.projListScope eq 'global'}">
                                            <td>${proj.applyOrgName }</td>
                                        </c:if>
                                        <td>[<a href="javascript:void(0)"
                                                onclick="auditList('${proj.projFlow}');">查看</a>]
                                        </td>
                                        <td>
                                            [<a href="javascript:void(0)" onclick="audit('${proj.projFlow}');">进入</a>]
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </tbody>
            </table>
        </form>
        <c:set var="pageView" value="${pdfn:getPageView(projList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>


</html>