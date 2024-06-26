
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER{background-color: #eee;}
    .cur{color:red;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
        toPage(1);
    });
    function toPage(page) {
        $("#currentPage").val(page);
        jboxLoad("doctorListZi","<s:url value='/res/activityQuery/list'/>?roleFlag=${param.roleFlag}&isNew=${param.isNew}&isEval=${param.isEval}&"+$("#searchForm").serialize(),true);
    }
    function upload(activityFlow,isUpload){
        var url = "<s:url value='/res/activityQuery/upload'/>?activityFlow="+activityFlow+"&isUpload="+isUpload;
        if(isUpload=="Y")
        {
            jboxOpen(url, "上传活动图片",700,550);
        }else{
            jboxOpen(url, "查看活动图片",700,550);
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
                    <label class="qlable">活动名称：</label>
                    <input type="text" name="activityName" value="" class="qtext" />
                </div>
                <div class="inputDiv">
                    <label class="qlable">主&nbsp;讲&nbsp;人：</label>
                    <input type="text" name="userName" value="" class="qtext" />
                </div>
                <div class="inputDiv">
                    <label class="qlable">活动形式：</label>
                    <select id="activityTypeId" name="activityTypeId" class="qselect">
                        <option value="">全部</option>
                        <c:forEach items="${dictTypeEnumActivityTypeList}" var="a">
                            <option value="${a.dictId}" >${a.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="inputDiv">
                    <label class="qlable">科&#12288;&#12288;室：</label>
                    <select name="deptFlow" class="qselect">
                        <option value="">全部</option>
                        <c:forEach items="${depts}" var="dept">
                            <option value="${dept.deptFlow}" >${dept.deptName}<c:if test="${dept.orgFlow ne doctor.orgFlow}">(${dept.orgName})</c:if>
                            </option>
                        </c:forEach>
                    </select>
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
                    &nbsp;<input type="checkbox" name="isCurrent" checked value="Y">当前轮转科室
                    &nbsp;<input class="searchInput"  type="button" value="查&#12288;询" onclick="toPage(1);"/>
                </div>
            </div>
        </form>
        </div>
        <div id="doctorListZi">
        </div>
    </div>
</div>