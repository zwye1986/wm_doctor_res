<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>
</head>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
<script type="text/javascript">
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
    function audit(projFlow) {
        $("#recForm").attr("action", "<s:url value='/srm/proj/search/recList?projFlow='/>" + projFlow);
        jboxStartLoading();
        $("#recForm").submit();
    }

    //加载项目状态枚举
    function loadProjStatusId() {
        var paramStatusId = '${param.projStatusId}';
        var status = $("#projStatusId");
        status.html('<option value="">请选择</option>');
        var stage = $("#projStageId").val();
        if (stage) {
            var url = "<s:url value='/srm/proj/search/loadProjStatus'/>?projStageId=" + stage;
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
    });

    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchProj();
    }

    function exportExcel() {
        var url = "<s:url value='/srm/proj/search/exportExcel/${sessionScope.projListScope}/${sessionScope.projCateScope}'/>";
        jboxSubmit($('#searchForm'), url, null, null);
        jboxEndLoading();
    }
    function exportExcelJs() {
        var url = "<s:url value='/srm/proj/search/exportExcelJs/${sessionScope.projListScope}/${sessionScope.projCateScope}'/>";
        jboxSubmit($('#searchForm'), url, null, null);
        jboxEndLoading();
    }

    var setting = {
        view: {
            dblClickExpand: false,
            showIcon: false,
            showTitle:false,
            selectedMulti:false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick,
            onClick: onClick
        }
    };

    function beforeClick(treeId, treeNode) {
        var check = (treeNode.id!=0);
        if (!check) jboxTip('不能选择根节点');
        return check;
    }

    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                nodes = zTree.getSelectedNodes(),
                v = "";
        id = "";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name + ",";
            id += nodes[i].id + ",";
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        if (id.length > 0 ) id = id.substring(0, id.length-1);
        var cityObj = $("#proSelect");
        $("#selectProjId").val(id);
        cityObj.attr("value", v);
    }

    function showMenu() {
        var cityObj = $("#proSelect");
        var cityOffset = $("#proSelect").offset();
        $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }

    $(document).ready(function(){
        /*var url = "<s:url value='/sys/subject/getAllDataJson'/>";
        jboxPostJson(url,null,function(data){
            if(data){
                zNodes = $.parseJSON(data);
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            }
        },null,false);*/
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
</script>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="paramForm"
                  action="<s:url value="/srm/proj/search/toExcel/${sessionScope.projListScope}/${sessionScope.projCateScope}?flag=y" />"
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
                  action="<s:url value="/srm/proj/search/list/${sessionScope.projListScope}/${sessionScope.projCateScope}?flag=y" />"
                  method="post">
                <input id="currentPage" type="hidden" name="currentPage" value=""/>
                <p>
                <div class="searchDiv">
                年份区间：
                    <input type="text" class="xltext ctime" name="startYear" readonly="readonly" style="width: 69px"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.startYear }<c:if test="${empty param.startYear and defaultTerm eq 'Y'}">${pdfn:getCurrYear()}</c:if>"/> -
                <input type="text" class="xltext ctime" name="endYear" readonly="readonly" style="width: 69px"
                       onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.endYear }<c:if test="${empty param.endYear and defaultTerm eq 'Y'}">${pdfn:getCurrYear()}</c:if>"/>
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
                    项目阶段：
                    <select id="projStageId" name="projStageId" onchange="loadProjStatusId();"
                                       class="xlselect">
                    <option value="">请选择</option>
                    <c:forEach var="stage" items="${projStageEnumList}">
                        <c:if test="${stage.id ne 'Award'}">
                        <option value="${stage.id}"
                                <c:if test="${param.projStageId==stage.id}">selected</c:if>>${stage.name}</option>
                        </c:if>
                    </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    项目状态：
                    <select id="projStatusId" name="projStatusId" class="xlselect">
                        <option value="">请选择项目阶段</option>
                    </select>
                </div>
                <c:if test="${sessionScope.projListScope=='local'}">
                <div class="searchDiv">
                    科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                           value="${param.applyDeptName}" autocomplete="off"/>
                    <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                </c:if>
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
                <div class="searchDiv">
                    课题编号：
                    <input class="xltext" name="projNo" type="text"
                           value="${param.projNo}"/>
                </div>
                <div class="searchDiv">
                    课题账号：
                    <input class="xltext" name="accountNo" type="text"
                           value="${param.accountNo}"/>
                </div>
                <div class="searchDiv">
                    项目名称： <input type="text" name="projName" value="${param.projName}" class="xltext"/>
                </div>
                <div class="searchDiv">
                    负 责 人 ： <input type="text" name="applyUserName" value="${param.applyUserName}" class="xltext"/>
                </div>


                <div class="searchDiv">
                    <input type="button" class="search" onclick="searchProj();" value="查&#12288;询">
                    <%--<c:if test="${pdfn:contains(param.projTypeId, 'yhwsj')  and  sessionScope.projListScope eq 'global'}">
                        <input type="button" class="search" onclick="exportExcel();" value="导出Excel">
                    </c:if>--%>
                    <c:choose>
                        <c:when test="${pdfn:contains(param.projTypeId, 'yhwsj')  and  sessionScope.projListScope eq 'global'}">
                            <input type="button" class="search" onclick="exportExcel();" value="导出Excel">
                        </c:when>
                        <c:otherwise>
                            <input type="button" class="search" onclick="exportExcelJs();" value="导出Excel">
                        </c:otherwise>
                    </c:choose>
                </div>
                <!-- <input type="button" class="search" onclick="resProj();" value="导出Excel"> -->
                </p>
            </form>
        </div>
        <form id="recForm" method="post">
            <table class="xllist">
                <thead>
                <tr>
                    <th width="5%">年份</th>
                    <th width="7%">课题编号</th>
                    <th width="7%">课题账号</th>
                    <th width="10%">一级来源</th>
                    <th width="10%">二级来源</th>
                    <th width="12%">项目名称</th>
                    <th width="10%">起止时间</th>
                    <th width="7%">项目负责人</th>
                    <th width="10%">当前阶段</th>
                    <th width="10%">当前状态</th>
                    <th width="7%">审核意见</th>
                    <th width="5%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${projList}" var="proj">

                            <tr>
                                <td><span>${proj.projYear }</span></td>
                                <td>${proj.projNo }</td>
                                <td>${proj.accountNo }</td>
                                <td>${proj.projDeclarer }</td>
                                <td>${proj.projSecondSourceName }</td>
                                <td><a style="color:blue"
                                       href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>"
                                       target="_blank">${proj.projName}</a></td>
                                <td>${proj.projStartTime }~<br/>${proj.projEndTime }</td>
                                <td>${proj.applyUserName}</td>
                                <td>${proj.projStageName }</td>
                                <td>${proj.projStatusName }</td>
                                <td>[<a href="javascript:void(0)" onclick="auditList('${proj.projFlow}');">查看</a>]</td>
                                <td>
                                    [<a href="javascript:void(0)" onclick="audit('${proj.projFlow}');">进入</a>]
                                </td>
                </c:forEach>
                </tbody>
                <c:if test="${projList == null || projList.size() == 0 }">
                    <tr>
                        <td align="center" colspan="11">无记录</td>
                    </tr>
                </c:if>
            </table>
        </form>
        <c:set var="pageView" value="${pdfn:getPageView(projList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:190px;"></ul>
</div>
</body>


</html>