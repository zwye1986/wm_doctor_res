<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>

<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<style type="text/css">
    .indexNum + div{
        z-index: 99;
        position: relative!important;
        top:0!important;
        left:0!important;
    }
</style>
<script type="text/javascript">

    /**
     *模糊查询加载
     */
    (function($){
        $.fn.likeSearchInit = function(option){
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            searchInput.on("keyup focus",function(){
                $("#boxHome").show();
                if($.trim(this.value)){
                    $("#boxHome .item").hide();
                    var items = $("#boxHome .item[value*='"+this.value+"']").show();
                    if(!items){
                        $("#boxHome").hide();
                    }
                }else{
                    $("#boxHome .item").show();
                }
            });
            searchInput.on("blur",function(){
                if(!$("#boxHome.on").length){
                    $("#boxHome").hide();
                }
            });
            $("#boxHome").on("mouseenter mouseleave",function(){
                $(this).toggleClass("on");
            });
            $("#boxHome .item").click(function(){
                $("#boxHome").hide();
                var value = $(this).attr("value");
                $("#itemName").val(value);
                searchInput.val(value);
                if(option.clickActive){
                    option['clickActive']($(this).attr("flow"));
                }
            });
        };
    })(jQuery);

    //页面加载完成时调用
    $(function(){
        $("#orgSel").likeSearchInit({});
    });

    function toOrgFlow(orgFlow) {
        $("#orgFlow").val(orgFlow);
        searchDept();
    }

    function changeStatus() {
        if ($("#orgSel").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }

    $(document).ready(function(){
        if(${roleFlag eq GlobalConstant.USER_LIST_LOCAL}){
            $("#deptFlow").empty().append('<option/>');
            jboxPost("<s:url value='/jsres/statistic/searchDeptListByOrg'/>?orgFlow=${orgFlow}",null,function(resp){
                if(resp!=null&&resp.length){
                    for(var index in resp){
                        var option = $('<option/>');
                        option.attr({"value":resp[index].deptFlow}).text(resp[index].deptName);
                        $("#deptFlow").append(option);
                    }
                }
            },null,false);
        }
        toPage(1);
    });

    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("contentDiv","<s:url value='/jsres/statistic/teacherAuditList'/>",$("#searchForm").serialize(),false);
    }

    function searchDept() {
        $("#deptFlow").empty().append('<option/>');
        var orgFlow = $("#orgFlow").val();
        if(orgFlow != ''){
            jboxPost("<s:url value='/jsres/statistic/searchDeptListByOrg'/>?orgFlow="+orgFlow,null,function(resp){
                if(resp!=null&&resp.length){
                    for(var index in resp){
                        var option = $('<option/>');
                        option.attr({"value":resp[index].deptFlow}).text(resp[index].deptName);
                        $("#deptFlow").append(option);
                    }
                }
            },null,false);
        }
    }

</script>
<div class="main_hd">
    <h2 class="underline">
        带教审核量统计
    </h2>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="div_search">
        <form id="searchForm" method="post">
            <input id="currentPage" type="hidden" name="currentPage"/>
            <input id="roleFlag" type="hidden" name="roleFlag" value ="${roleFlag}"/>
            <table class="searchTable">
                <c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
                    <tr>
                        <td class="td_left">科&#12288;&#12288;室：</td>
                        <td>
                            <select id="deptFlow" name="deptFlow" class="select"></select>
                        </td>
                        <td class="td_left">带教姓名：</td>
                        <td>
                            <input type="text" name="teacherName" value="${param.teacherName}" class="input"/>
                        </td>
                        <td colspan="4">
                           <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL}">
                    <tr>
                        <td class="td_left">基&#12288;&#12288;地：</td>
                        <td>
<%--                            <select name="orgFlow" id="orgFlow" class="select" onchange="searchDept()">--%>
<%--                                <option value="">请选择</option>--%>
<%--                                <c:forEach items="${orgs}" var="org">--%>
<%--                                    <option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
                            <input type="hidden" id="orgFlow" name="orgFlow" value="">
                            <input id="orgSel" class="toggleView input" type="text"  placeholder="请选择基地"
                                   style="width: 122px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 140px -4px;"
                                   name="orgName" value="" autocomplete="off" title="${param.orgName}" onkeydown="changeStatus();" onkeyup="changeStatus();" onmouseover="this.title = this.value"/>
                            <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:5px;">
                                <div id="boxHome" style="max-height: 200px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 300px;border-top: none;position: relative;display: none;">
                                    <p class="item" flow="" value="全部" onclick="toOrgFlow('');" style="height: 30px;padding-left: 10px;text-align: left;">全部</p>
                                    <c:forEach items="${orgs}" var="org">
                                        <p class="item" flow="${org.orgFlow}" value="${org.orgName}" onclick="toOrgFlow('${org.orgFlow}');" style="height: 30px;padding-left: 10px;text-align: left;">${org.orgName}</p>
                                    </c:forEach>
                                </div>
                            </div>
                        </td>
                        <td class="td_left">科&#12288;&#12288;室：</td>
                        <td>
                            <select id="deptFlow" name="deptFlow" class="select"></select>
                        </td>
                        <td class="td_left">带教姓名：</td>
                        <td>
                            <input type="text" name="teacherName" value="${param.teacherName}" class="input"/>
                        </td>
                        <td colspan="2">
                            <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                        </td>
                    </tr>
                </c:if>
            </table>
        </form>
    </div>
    <div id="contentDiv" style="max-width: 950px">

    </div>
</div>