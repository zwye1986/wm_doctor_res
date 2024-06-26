<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function(){
        toPage();
        $('#cfgYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode:2,
            format:'yyyy'
        });
    });

    function toPage(page){
        if(page != undefined){
            $("#currentPage").val(page);
        }
        searchCfgInfo();
    }

    function searchCfgInfo(){
        <%--var url ="<s:url value='/jsres/doctor/accountList'/>";--%>
        var url =" <s:url value='/jsres/base/loadScoreConf'/>";
        jboxPostLoad("contentDiv", url, $("#searchForm").serialize(), true);
    }
    var width  = 400;
    var height =200;
    //添加
    function setScoreConf(){
        var url="<s:url value='/jsres/base/setScoreConf'/>";
        jboxOpen(url,"添加配置信息",width,height);
    }
    //编辑
    function editCfgInfo(cfgYear){
        var url="<s:url value='/jsres/base/setScoreConf?cfgYear='/>"+cfgYear;
        jboxOpen(url,"编辑配置信息",width,height);
    }
    //删除
    function delCfgInfo(cfgYear){
        jboxConfirm("确认删除此条记录?", function(){
            var url="<s:url value='/jsres/base/delScoreConf?cfgYear='/>"+cfgYear;
            jboxPost(url, null, function(resp){
                if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                    searchCfgInfo();
                }
            }, null, false);
        });
    }
</script>


<div class="main_hd">
    <h2 class="underline">成绩合格线配置</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            年份：<input type="text" class="input" id="cfgYear" name="cfgYear" value="${param.cfgYear}" style="width:100px;"/>&#12288;
            <input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/>&#12288;
            <input class="btn_green" type="button" value="添&#12288;加" onclick="setScoreConf()"/>
        </form>
    </div>

    <div class="search_table" id="contentDiv">

    </div>
</div>
