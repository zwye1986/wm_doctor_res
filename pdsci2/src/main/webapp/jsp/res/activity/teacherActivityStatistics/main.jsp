<%@include file="/jsp/common/doctype.jsp" %>
<html>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
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
        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq roleFlag || GlobalConstant.USER_LIST_UNIVERSITY eq roleFlag}">
        initOrgs();
        </c:if>
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
	jboxLoad("doctorListZi","<s:url value='/res/teacherActivityStatistics/list'/>?roleFlag=${roleFlag}&"+$("#searchForm").serialize(),true);
}
function exportExcel(){
	var url = "<s:url value='/res/teacherActivityStatistics/exportList'/>?roleFlag=${roleFlag}";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
function searchDept(orgFlow) {
    if(orgFlow==""){
        initDepts([]);
        return;
    }
    jboxStartLoading();
    var url = "<s:url value='/res/deptActivityStatistics/searchDeptList'/>?orgFlow="+orgFlow;
    jboxGet(url,null,function(resp){
        jboxEndLoading();
        var datas=[];
        if(""!= resp){
            var dataObj = resp;
            for(var i = 0; i<dataObj.length; i++){
                var deptFlow = dataObj[i].deptFlow;
                var deptName = dataObj[i].deptName;
                var d={};
                d.id=deptFlow;
                d.text=deptName;
                datas.push(d);
            }

            if(""!="${param.deptFlow}"){
                $("#deptFlow").val("${param.deptFlow}");
            }
            if(""!="${param.deptName}"){
                $("#trainDept").val("${param.deptName}");
            }
        }
        initDepts(datas);
    },null,false);
}
</script>

<div class="mainright" style="min-height: 300px;" >
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm">
                <input type="hidden" id="currentPage" name="currentPage"/>
                <div class="queryDiv">
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq roleFlag || GlobalConstant.USER_LIST_UNIVERSITY eq roleFlag}">
                        <div class="inputDiv">
                            <label class="qlable">培训基地：</label>
                            <input id="trainOrg" name="orgName" value="${orgName}"  class="toggleView qtext" type="text"  autocomplete="off"  />
                            <input type="hidden" name="orgFlow" value="${orgFlow}" id="orgFlow">
                        </div>
                    </c:if>
                    <div class="inputDiv">
                        <label class="qlable">科&#12288;&#12288;室：</label>
                        <input id="trainDept" name="deptName" value="${param.deptName}"  class="toggleView qtext" type="text"  autocomplete="off"  />
                        <input type="hidden" name="deptFlow" value="${param.deptFlow}" id="deptFlow">
                    </div>
                <div class="inputDiv">
                    <label class="qlable">开始时间：</label>
                    <input type="text" id="startDate" name="startTime" value="" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                </div>
                <div class="inputDiv">
                    <label class="qlable">结束时间：</label>
                    <input type="text" id="endDate" name="endTime" value="" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                </div>
                <div class="inputDiv">
                    <label class="qlable">主&ensp;讲&ensp;人：</label>
                    <input type="text" name="userName" value="" class="qtext" />
                </div>
                <div class="inputDiv" style="max-width: 180px;min-width: 180px;">
                    <input class="searchInput" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                    <input class="searchInput" type="button" value="导&#12288;出" onclick="exportExcel();"/>
                </div>
                </div>
	        </form>
        </div>
        <div id="doctorListZi">
        </div>
    </div>
</div>
</html>