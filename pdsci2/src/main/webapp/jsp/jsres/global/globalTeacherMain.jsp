<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="bootstrapSelect" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css"/>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    /*.input{*/
    /*    margin: 0 12px 0 4px;*/
    /*    width: 161px;*/
    /*}*/

    .btn-group.bootstrap-select {
        margin: 0 12px 0 4px !important;
        width: 182px !important;
        height: 30px;
    }

    .text{
        margin-left: 0;
        width: auto;
        height: auto;
        line-height: inherit;
        color: black;
    }
    .selected a{
        padding: 0;
        background: #459ae9;
    }
    .btn{
        height: 30px !important;
        border: 1px solid #e7e7eb
    }
    .body{
        width: 90%;
        margin-left: auto;
        margin-right: auto;
        padding: 0 0 88px;
    }
    .container{
        padding-left: 0;
        padding-right: 0;
    }
    .btn-default{
        background-color: #fff;
    }
    .div_search span{
        font-weight: inherit;
        float: none;
    }
    .div_search{
        line-height: 35px;
    }
    .clearfix {
        clear: both;
        height: 0;
    }
    .bootstrap-select>.dropdown-toggle {
        width: 161px;
        padding-right: 25px;
        /*border: 1px;*/
        border: 1px solid #e7e7eb;
    }
    .btn-group.bootstrap-select {
        margin: 0 !IMPORTANT;
        width: 182px !important;
        height: 30px;
    }


</style>
<script type="text/javascript">

    $(document).ready(function () {
        search();
    });

    function toPage(page) {
        $("#currentPage").val(page);
        search();
    }

    function search() {
        var url = "<s:url value='/jsres/statistic/globalTeacher'/>";
        jboxStartLoading();
        jboxPostLoad("searchContent", url, $("#searchForm").serialize(), true);
        jboxEndLoading();
    }

    function exportUser() {
        var url = "<s:url value='/jsres/statistic/exportGlobalTeacher'/>";
        jboxExp($("#searchForm"), url);
    }

</script>


<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value="${param.currentPage}">

             <div class="form_search">
                 <div class="form_item">
                     <div class="form_label">基地名称：</div>
                     <div class="form_content">
                         <select name="orgFlow"  id="orgFlow" class="select">
                             <option value="" >请选择</option>
                             <c:forEach items="${orgList}" var="org">
                                 <option <c:if test="${param.orgFlow eq org.orgFlow}">selected="selected"</c:if> value="${org.orgFlow}">${org.orgName}</option>
                             </c:forEach>
                         </select>
                     </div>
                 </div>
                 <div class="form_item">
                     <div class="form_label">培训专业：</div>
                     <div class="form_content">
                         <select name="speId"  id="speId" class="select">
                             <option value="" >请选择</option>
                             <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                 <option <c:if test="${param.speId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
                             </c:forEach>
                         </select>
                     </div>
                 </div>
             </div>
            <div style="margin-top: 15px;">
                <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                <input type="button" class="btn_green" onclick="exportUser()" value="导&#12288;出">
            </div>

        </form>
    </div>

    <div id="searchContent">
    </div>
</div>