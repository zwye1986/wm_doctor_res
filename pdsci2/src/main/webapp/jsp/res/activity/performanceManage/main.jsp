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
</style>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<script type="text/javascript">
    $(document).ready(function(){
        var datas=[];
        <c:forEach items="${depts}" var="dept">
        var d={};
        d.id="${dept.deptFlow}";
        d.text="${dept.deptName}";
        datas.push(d);
        </c:forEach>
        initDepts(datas);
        toPage(1);
    });
    function initOrgs()
    {
        var datas=[];
        <c:forEach items="${orgs}" var="dept">
        var d={};
        d.id="${dept.orgFlow}";
        d.text="${dept.orgName}";
        datas.push(d);
        </c:forEach>
        var itemSelectFuntion = function(){
            $("#orgFlow").val(this.id);
            searchDept(this.id);
        };
        $.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
    }
    function initDepts(datas)
    {
        var itemSelectFuntion = function(){
            $("#deptFlow").val(this.id);
        };
        $.selectSuggest('trainDept',datas,itemSelectFuntion,"deptFlow",true);
    }
function toPage(page) {
	$("#currentPage").val(page);
	jboxLoad("doctorListZi","<s:url value='/res/performanceManage/list'/>?roleFlag=${param.roleFlag}&"+$("#searchForm").serialize(),true);
}
function exportExcel(){
	var url = "<s:url value='/res/performanceManage/exportList'/>?roleFlag=${param.roleFlag}";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}

</script>

<div class="mainright" style="min-height: 300px;" >
	<div class="content">
        <div class="title1 clearfix">
	        <form id="searchForm">
		        <input type="hidden" id="currentPage" name="currentPage"/>
                <div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">开始月份：</label>
                        <input type="text" id="startDate" name="startTime" value="" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">结束月份：</label>
                        <input type="text" id="endDate" name="endTime" value="" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">科&#12288;&#12288;室：</label>
                        <input id="trainDept" name="deptName" value="${param.deptName}"  class="toggleView qtext" type="text"  autocomplete="off"  />
                        <input type="hidden" name="deptFlow" value="${param.deptFlow}" id="deptFlow">
                    </div>
                    <div class="lastDiv" style="max-width: 180px;min-width: 180px;">
                        <input class="search" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                        <input class="search" type="button" value="导&#12288;出" onclick="exportExcel();"/>
                    </div>
                </div>
            </form>
        </div>
        <div id="doctorListZi">
        </div>
    </div>
</div>
</html>