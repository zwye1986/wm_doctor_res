<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer2.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/viewer/viewer.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        initOrg();
        toPage(1);
    });

    function initOrg() {
        var datas=[];
        <c:forEach items="${orgs}" var="org">
            var d={};
            d.id="${org.orgFlow}";
            d.text="${org.orgName}";
            datas.push(d);
        </c:forEach>
        var itemSelectFuntion = function(){
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
    }

    function signAudit(signFlow) {
        jboxButtonConfirm("是否审核基地签名图片？","通过","不通过",function(){
            jboxPost("<s:url value='/jsres/hospital/confireSignInfo'/>",{"signFlow":signFlow,"auditStatusId":"Passed"}, function(resp){
                setTimeout(function(){
                    toPage(1);
                },300);
            } , null , true);

        },function(){
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/hospital/confireSignInfo'/>",{"signFlow":signFlow,"auditStatusId":"UnPassed"}, function(resp){
                setTimeout(function(){
                    toPage(1);
                },300);
            } , null , true);

        },300);
    }

    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchIn();
    }
    function searchIn() {
        var url = "<s:url value='/jsres/hospital/signManage'/>";
        jboxPostLoad("doctorContent", url, $("#inForm").serialize(), true);
    }
</script>
<div class="main_bd">
    <div class="div_search">
        <form id="inForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            <input type="hidden" name="tabTag" id="tabTag" value="${tabTag}"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">培训基地：</td>
                    <td>
                        <input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 134px"  />
                        <input type="hidden" name="orgFlow" id="orgFlow">
                    </td>
                    <td class="td_left">审核状态：</td>
                    <td>
                        <select name="auditStatusId" id="auditStatusId" class="select" style="width: 134px;">
                            <option value="">请选择</option>
                            <option value="Auditing" selected="selected">待审核</option>
                            <option value="Passed" <c:if test="${param.auditStatusId=='Passed'}">selected="selected"</c:if>>审核通过</option>
                            <option value="UnPassed" <c:if test="${param.auditStatusId=='UnPassed'}">selected="selected"</c:if>>审核不通过</option>
                        </select>
                    </td>
                    <td class="td_left">
                        <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询" style="margin-left: 8px">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table" id="doctorContent" style="padding: 0 15px;">

    </div>

    <div>
        <c:forEach items="${orgFlowList}" var="flow">
            <input type="hidden" id="${flow}" value="${flow}"/>
        </c:forEach>
    </div>
</div>

