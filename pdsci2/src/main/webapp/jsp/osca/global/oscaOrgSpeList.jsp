
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
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function search(){
            jboxStartLoading();
            $("#searchForm").submit();
            toPage(1);
        }
        var width=(window.screen.width)*0.3;
        var height=(window.screen.height)*0.3;
        function showEdit(orgFlow,orgName){
            jboxStartLoading();
            jboxOpen("<s:url value='/osca/orgSpeGlobal/editInfo2'/>?orgFlow="+orgFlow+"&orgName="+orgName, "专业设置", 700, 500);
        }

        /**
         * 控制考点管理员角色 学员信息管理、考官信息管理菜单的按钮显示
         */
        function setShowFlg(role,orgFlow,obj){
            var isShow='N';
            var checked=$(obj).is(':checked');
            if(checked){
                isShow='Y';
            }
            var page=$("#currentPage").val();
            var url = "<s:url value='/osca/orgSpeGlobal/setShowFlg'/>?orgFlow="+orgFlow+"&isShow="+isShow+"&role="+role;
            jboxPost(url,null, function(resp) {
                if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                    top.jboxTip(resp);
                    toPage(page);
                }else{
                    top.jboxTip(resp);
                    toPage(page);
                }
            },null,false);
        }

        function setOrgSpe(orgFlow,obj){
            var checked=$(obj).is(':checked');
            var page=$("#currentPage").val();
            var url = "<s:url value='/osca/orgSpeGlobal/saveOrgSpe'/>?orgFlow="+orgFlow+"&checked="+checked;
            jboxPost(url,null, function(resp) {
                if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                    top.jboxTip(resp);
                    toPage(page);
                }else{
                    top.jboxTip(resp);
                    toPage(page);
                }
            },null,false);
        }

        function addOrg(){
            jboxOpen("<s:url value='/osca/orgSpeGlobal/goAddOrg'/>?orgProvId=${orgProvId}", "详情", 650, 210);
        }
        function editOrg(orgFlow){
            var page=$("#currentPage").val();
            jboxOpen("<s:url value='/osca/orgSpeGlobal/addOrg'/>?orgFlow="+orgFlow+"&page="+page, "详情", 650, 210);
        }

        //停用 启用
        function setOrgStatus(orgFlow,status){
            var optName = status == 'N'?"停用":"启用";
            jboxConfirm("是否确认"+optName+"？", function(){
                var url = "<s:url value='/osca/orgSpeGlobal/oprOrg?orgFlow='/>"+orgFlow+"&recordStatus="+status;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/osca/orgSpeGlobal/list'/>"
                  method="post">
                <div class="queryDiv">
                    <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
                    <div class="inputDiv">
                        <label class="qlable">地&#12288;&#12288;市：</label>
                        <select name="sysOrg.orgCityId" class="qselect">
                            <option value="All">全省</option>
                            <c:forEach var="orgCity" items="${orgCityList}">
                                <c:if test="${not empty orgCity.orgCityId}">
                                    <option value="${orgCity.orgCityId}"
                                            <c:if test="${orgCity.orgCityId == orgCityId}">selected</c:if>>${orgCity.orgCityName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">基地名称：</label>
                        <input class="qtext" name="sysOrg.orgName" value="${orgName}" type="text" />
                    </div>
                    <div style="border: 0px;float: left;max-width: 250px;min-width: 85px;line-height: 35px;height: 35px;text-align: right;">
                    <input id="searchSite" name="searchSite" <c:if test="${param.searchSite == 'on'}">checked="checked"</c:if> type="checkbox" />
                    查看考点
                    </div>

                    <div class="lastDiv" style="max-width: 180px;min-width: 180px;">
                    <input type="button" value="查&#12288;询" class="search" onclick="search();"/>
                    <input type="button" value="新&#12288;增" class="search" onclick="addOrg();"/>
                    </div>
                </div>
            </form>
            <div id="base">
                <table  class="xllist">
                    <colgroup>
                        <col width="10%"/>
                        <col width="30%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                    </colgroup>
                    <tr>
                        <th  style="text-align: center;">序号</th>
                        <th  style="text-align: center;">基地名称</th>
                        <th  style="text-align: center;">所属地市</th>
                        <th  style="text-align: center;">设为考点</th>
                        <th  style="text-align: center;">专业设置</th>
                        <th  style="text-align: center;">学员信息管理控制</th>
                        <th  style="text-align: center;">考官信息管理控制</th>
                        <th  style="text-align: center;">操作</th>
                    </tr>
                    <c:if test="${not empty oscaOrgSpeExtList}">
                        <c:forEach items="${oscaOrgSpeExtList}" var="orgSpeExt" varStatus="num">
                            <tr>
                                <td>${num.count}</td>
                                <td>${orgSpeExt.orgName}</td>
                                <td>${orgSpeExt.sysOrg.orgCityName}</td>
                                <td><input name="toSite" value="" <c:if test="${orgSpeExt.sysOrg.isExamOrg eq GlobalConstant.IS_EXAM_TEA_Y}">checked="checked"</c:if>  type="checkbox" onchange="setOrgSpe('${orgSpeExt.orgFlow}',this)"/></td>
                                <c:choose>
                                    <c:when test="${orgSpeExt.sysOrg.isExamOrg eq GlobalConstant.IS_EXAM_TEA_Y}">
                                        <c:if test="${orgSpeExt.toSite eq GlobalConstant.FLAG_Y}">
                                            <td><a style="cursor:pointer;color:#4195c5;" onclick="showEdit('${orgSpeExt.orgFlow}','${orgSpeExt.orgName}')">详情</a></td>
                                        </c:if>
                                        <c:if test="${orgSpeExt.toSite eq GlobalConstant.FLAG_N}">
                                            <td><a style="cursor:pointer;color:#4195c5;" onclick="showEdit('${orgSpeExt.orgFlow}','${orgSpeExt.orgName}')">设置</a></td>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a>待设置</a></td>
                                    </c:otherwise>
                                </c:choose>
                                <td><input name="osceDoctorShow"   <c:if test="${orgSpeExt.sysOrg.osceDoctorShow eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>  type="checkbox" onchange="setShowFlg('doctor','${orgSpeExt.orgFlow}',this)"/></td>
                                <td><input name="osceTeacherShow"  <c:if test="${orgSpeExt.sysOrg.osceTeacherShow eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>  type="checkbox" onchange="setShowFlg('teacher','${orgSpeExt.orgFlow}',this)"/></td>
                                <td>
                                    <c:if test="${orgSpeExt.sysOrg.recordStatus eq 'Y'}">
                                        <a onclick="editOrg('${orgSpeExt.orgFlow}')" style="color: #4195c5;cursor: pointer">编辑</a>
                                        |
                                        <a onclick="setOrgStatus('${orgSpeExt.orgFlow}','N')" style="color: #4195c5;cursor: pointer">停用</a>
                                    </c:if>
                                    <c:if test="${orgSpeExt.sysOrg.recordStatus eq 'N'}">
                                    <a onclick="setOrgStatus('${orgSpeExt.orgFlow}','Y')" style="color: #4195c5;cursor: pointer">启用</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty oscaOrgSpeExtList}">
                        <tr><td colspan="99">暂无记录</td></tr>
                    </c:if>
                </table>
                <div>
                    <c:set var="pageView" value="${pdfn:getPageView(oscaOrgSpeExtList)}" scope="request"></c:set>
                    <pd:pagination toPage="toPage"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>