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
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
.queryDiv{
    min-width: 600px;
}
.lastDiv{
    margin-top: 10px;
    margin-bottom: 10px;
}
#doctorListZi{
    padding: 0 15px 30px;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	toPage(1);
});
function toPage(page) {
	$("#currentPage").val(page);
	jboxLoad("doctorListZi","<s:url value='/res/performanceManage/ActivityPerformanceSearch'/>?roleFlag=${param.roleFlag}&typeId=${typeId}&"+$("#searchForm").serialize(),true);
}
function addFormula(mainFlow,typeId,flag){
    jboxOpen("<s:url value='/res/performanceManage/searchByMainFlow'/>?mainFlow="+mainFlow+"&typeId="+typeId+"&flag="+flag,"编辑信息", 600, 550);

}

</script>

<div class="mainright" style="min-height: 300px;" >
	<div class="content">
        <div class="title1 clearfix">
	        <form id="searchForm">
		        <input type="hidden" id="currentPage" name="currentPage"/>
                <div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">开始时间：</label>
                        <input type="text" id="startDate" name="startDate" value="" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">结束时间：</label>
                        <input type="text" id="endDate" name="endDate" value="" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </div>

                    </div>
                    <div class="lastDiv" style="max-width: 180px;min-width: 180px;">
                        <input class="search" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                        <input class="search" type="button" value="新&#12288;增" onclick="addFormula('','${typeId}','add');"/>
                    </div>
                </div>
            </form>
        </div>
        <div id="doctorListZi">
        </div>
    </div>
</div>
</html>