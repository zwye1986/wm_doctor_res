<%@include file="/jsp/common/doctype.jsp" %>
<html>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true" />
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
</style>
<script type="text/javascript">
    $(document).ready(function(){
        toPage(1);

    });
    function toPage(page) {
        $("#currentPage").val(page);
        jboxLoad("doctorListZi","<s:url value='/res/activityTarget/list'/>?roleFlag=${roleFlag}&"+$("#searchForm").serialize(),true);
    }
    function add(targetFlow)
    {

        var url="<s:url value='/res/activityTarget/add'/>?targetFlow="+targetFlow;
        if(targetFlow==""){
            jboxOpen(url, "新增评价指标", 500, 200,true);
        }else{
            jboxOpen(url, "编辑评价指标", 500, 200,true);
        }
    }
</script>
<div class="mainright" style="min-height: 300px;">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm">
                <input type="hidden" id="currentPage" name="currentPage"/>
                <div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">评价指标：</label>
                        <input type="text" name="targetName" value="" class="qtext" />
                    </div>
                    <div class="lastDiv" style="max-width: 200px;min-width: 200px;">
                        <input class="searchInput" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                        <input class="searchInput" type="button" value="新&#12288;增" onclick="add('');"/>
                    </div>
                </div>
            </form>
        </div>
        <div id="doctorListZi">
        </div>
    </div>
</div>
</html>