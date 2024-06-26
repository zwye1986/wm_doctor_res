<%@ taglib prefix="cP" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // $('#sessionNumber').datepicker({
        //     startView: 2,
        //     maxViewMode: 2,
        //     minViewMode: 2,
        //     format: 'yyyy'
        // });
        $.checkYear("sessionNumber","",null);

        getCityArea();
        initOrg();
        toPage(1);
    });

    function getCityArea(){
        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        var provIds = "320000";
        jboxGet(url,null, function(json) {
            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
            var newJsonData=new Array();
            var j=0;
            var html ="<option value=''></option>";
            for(var i=0;i<json.length;i++){
                if(provIds==json[i].v){
                    var citys=json[i].s;
                    for(var k=0;k<citys.length;k++){
                        var city=citys[k];
                        html+="<option value='"+city.v+"'>"+city.n+"</option>";
                    }
                }
            }
            $("#cityId2").html(html);
        },null,false);
    }

    var allOrgs=[];
    function initOrg() {
        var datas=[];
        <c:forEach items="${orgs}" var="org">
            <c:if test="${sessionScope.currUser.orgFlow!=org.orgFlow }">
                var d={};
                d.id="${org.orgFlow}";
                d.text="${org.orgName}";
                d.cityId="${org.orgCityId}";
                datas.push(d);
                allOrgs.push(d);
            </c:if>
        </c:forEach>
        var itemSelectFuntion = function(){
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);

    }

    function changeOrg(obj) {
        var cityId=$(obj).val();
        var datas=[];
        for(var i=0;i<allOrgs.length;i++) {
            var org=allOrgs[i];
            if(org.cityId==cityId||cityId=="") {
                datas.push(org);
            }
        }
        $("#orgFlow").val("");
        var itemSelectFuntion = function(){
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
    }

    function toPage(page) {
        var currentPage = "";
        if (!page || page != undefined) {
            currentPage = page;
        }
        if (page == undefined || page == "") {
            currentPage = 1;
        }
        $("#currentPage").val(currentPage);
        search();
    }

    //黑名单信息查询
    function search() {
        // if ($("#orgFlow").val() == "") {
        //     $("#trainOrg").val("");
        // }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jsres/blackList/blackListInfoList'/>";
        jboxPostLoad("div_table2", url, $("#searchForm").serialize(), true);
    }
    //黑名单移除
    function removeBlack(userFlow, recordStatus, recordflow) {
        jboxConfirm("确认移除该记录吗？", function () {
            var url = "<s:url value='/jsres/blackList/removeBlack?userFlow='/>" + userFlow + "&recordStatus=" + recordStatus + "&recordFlow=" + recordflow;
            jboxGet(url, null, function () {
                search();
            });
        });
    }
    //展示原因详情
    function showDetail(resp1, resp2) {
//	jboxConfirm(resp1);
        jboxOpenContent(resp1, "原因", 300, 100, true);
    }
    //添加黑名单
    function addBlackList1(roleFlag) {
        debugger;
        var url = "<s:url value='/jsres/blackList/addBlackList'/>?roleFlag="+roleFlag;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='500px' height='450px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,'黑名单添加',500,450,null,false);
        /*jboxOpen("<s:url value='/jsres/blackList/addBlackList'/>?roleFlag="+roleFlag, "黑名单添加", 500, 400);*/
    }

    function change() {
        $("#trainOrg").val("");
    }
    
    function exportBlackList() {
        var url = "<s:url value='/jsres/blackList/exportBlackList'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
<body>
<div class="main_hd">
    <h2 class="underline">黑名单管理</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value=""/>
            <div>
                <table>
                    <tr>
                        <td class="td_left">地&#12288;&#12288;市：</td>
                        <td>
                            <select id="cityId2" name="cityId" class="select" onchange="changeOrg(this)" style="width: 130px"></select>
                        </td>
                        <td class="td_left">&#12288;培训基地：</td>
                        <td>
                            <input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off" style="width: 124px;margin: 0px 0px;" />
                            <input type="hidden" name="orgFlow" id="orgFlow" value="${param.orgFlow}">
                        </td>
                        <td class="td_left">&#12288;学员姓名：</td>
                        <td>
                            <input type="text" value="${param.userName}" class="input" name="userName" style="width: 130px;"/>
                        </td>
                        <td class="td_left">&#12288;原培训届别：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                                   class="input" readonly="readonly" style="width: 130px;margin-left: 0px"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">证件号码：</td>
                        <td>
                            <input type="text" value="${param.idNo}" class="input" name="idNo"
                                   style="width: 124px;margin: 0 0px"/>
                        </td>
                        <td class="td_left">&#12288;审核状态：</td>
                        <td>
                            <select name="auditStatusId" class="select" style="margin-left: 0px;width: 131px">
                                <option value="">请选择</option>
                                <option name="Passing" value="Passing" selected="selected">待审核</option>
                                <option name="Passed" value="Passed" <c:if test="${param.auditStatusId eq 'Passed'}">selected="selected"</c:if>>审核通过</option>
                            </select>
                        </td>
                        <td id='jointOrg' colspan="2">
                            &nbsp;<label style="cursor: pointer;margin-left: 9px" ><input type="checkbox" id="jointOrgFlag"
                                                                         <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                                         name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                        </td>
                                <%--<c:if test="${JointOrgCount ne '0'}">培训基地：--%>
                                    <%--<select class="select" name="orgFlow0" style="width: 120px;" onchange="searchDeptList(this.value)">--%>
                                        <%--<option value="all" <c:if test="${orgFlow eq 'all'}">selected="selected"</c:if>>全部</option>--%>
                                        <%--<c:forEach items="${orgList}" var="org">--%>
                                            <%--<option value="${org.orgFlow}" <c:if test="${orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</c:if>--%>
                        <%--<td colspan="2">--%>
                            <%--<input class="btn_green" type="button" onclick="search()" value="查&#12288;询"/>&#12288;--%>
                            <%--<input class="btn_green" type="button" onclick="addBlackList('charge')" value="新&#12288;增"/>&#12288;--%>
                            <%--<input class="btn_green" type="button" onclick="exportBlackList()" value="导&#12288;出"/>--%>
                        <%--</td>--%>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <input class="btn_green" type="button" onclick="search()" value="查&#12288;询"/>&#12288;
                            <input class="btn_green" type="button" onclick="exportBlackList()" value="导&#12288;出"/>&#12288;
                            <c:if test="${maintenance ne 'Y'}"> <%--客服（运维角色）只能查看——--%>
                                <input class="btn_green" type="button" onclick="addBlackList1('charge')" value="新&#12288;增"/>
                            </c:if>
                        </td>
                    </tr>
                <%--<tr>--%>
                    <%--<td>--%>
                        <%--<input class="btn_green" type="button" onclick="search()" value="查&#12288;询"/>&#12288;--%>
                        <%--<input class="btn_green" type="button" onclick="addBlackList('charge')" value="新&#12288;增"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                </table>
            </div>
        </form>
    </div>
    <div class="search_table" id="div_table2">

    </div>
</div>

</body>
