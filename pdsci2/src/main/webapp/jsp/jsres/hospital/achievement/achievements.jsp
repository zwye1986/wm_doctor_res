<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
    $(document).ready(function(){
        // 页面加载成功 年级选中当前年级
        $.checkYear("sessionNumber","${sysCfgMap['jsres_local_sessionNumber']}",null);
        <c:if test="${sessionScope.userListScope != GlobalConstant.USER_LIST_LOCAL}">
        //getCityArea();
        </c:if>
        <c:forEach items="${jsResDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if("${data}"=="${type.id}"){
            $("#"+"${data}").attr("checked","checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#"+"${type.id}").attr("checked","checked");
        </c:if>
        </c:forEach>

        toPage(1);
    });
    function checkAll(obj){
        var f=false;
        if($(obj).attr("checked")=="checked")
        {
            f=true;
        }
        $(".docType").each(function(){
            if(f)
            {
                $(this).attr("checked","checked");
            }else {
                $(this).removeAttr("checked");
            }

        });
    }
    function changeAllBox(){
        if($(".docType:checked").length==$(".docType").length)
        {
            $("#all").attr("checked","checked");
        }else{
            $("#all").removeAttr("checked");
        }
    }
    function toPage(page) {
        if($(".docType:checked").length==0) {
            jboxTip("请选择人员类型！");
            return false;
        }
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("contentDiv","<s:url value='/jsres/hospital/getAchievementList'/>",$("#searchForm").serialize(),false);
    }

    function auditRecruit(recruitFlow, doctorFlow, exType){
        var url = "<s:url value='/jsres/manage/province/doctor/auditRecruit'/>?openType=open&recruitFlow="+recruitFlow+"&doctorFlow="+doctorFlow + "&exType=" + exType;
        jboxOpen(url,"医师信息查看",1050,550);
    }

    function modifyResult(recruitFlow, userName){
        var url = "<s:url value='/jsres/hospital/modifyResultInfo'/>?recruitFlow="+recruitFlow;
        jboxOpen(url,"医师"+userName+"成绩修改",400,300);
    }

    function changeTrainSpes(){
        var trainCategoryid=$("#catSpeId").val();
        if(trainCategoryid =="${dictTypeEnumDoctorTrainingSpe.id}"){
            $("#derateFlagLabel").show();
        }else{
            $("#derateFlag").attr("checked",false);
            $("#derateFlagLabel").hide();
        }
        if(trainCategoryid==""){
            $("select[name=speId] option[value != '']").remove();
            return false;
        }
        $("select[name=speId] option[value != '']").remove();
        $("#"+trainCategoryid+"_select").find("option").each(function(){
            $(this).clone().appendTo($("#speId"));
        });
        return false;
    }

    function getCityArea(){
        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        var provIds = "320000";
        jboxGet(url,null, function(json) {
            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
            var newJsonData=new Array();
            var j=0;
            for(var i=0;i<json.length;i++){
                if(provIds==json[i].v){
                    var citys=json[i].s;
                    for(var k=0;k<citys.length;k++){
                        newJsonData[j++]=citys[k];
                    }
                }
            }
            $.cxSelect.defaults.url = newJsonData;
            $.cxSelect.defaults.nodata = "none";
            $("#provCityAreaId").cxSelect({
                selects : ["city"/* ,"area" */],
                nodata : "none",
                firstValue : ""
            });
        },null,false);
    }

    function exportForDetail(){
        if($(".docType:checked").length==0) {
            jboxTip("请选择人员类型！");
            return false;
        }
        var url = "<s:url value='/jsres/manage/exportForDetailByOrg'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }

    function confireRecruitY(recruitFlow,doctorFlow,userName) {
        jboxConfirm("确认录取学员"+ userName +"？",function(){
            jboxPost("<s:url value='/jsres/hospital/confireRecruit'/>",{"recruitFlow":recruitFlow,"doctorFlow":doctorFlow,"recruitFlag":"Y"}, function(resp){
                setTimeout(function(){
                    toPage(1);
                },300);
            } , null , true);
        });
    }

    function confireRecruitN(recruitFlow,doctorFlow,userName) {
        var url = "<s:url value ='/jsres/hospital/toTconfireRecruit'/>?recruitFlow="+recruitFlow+"&doctorFlow="+doctorFlow+"&userName="+userName+"&recruitFlag=N";
        jboxOpen(url, "确认不录取学员"+ userName+"？", 400, 240);

  /*      jboxConfirm("确认不录取学员"+ userName +"？",function(){
            jboxPost("<s:url value='/jsres/hospital/confireRecruit'/>",{"recruitFlow":recruitFlow,"doctorFlow":doctorFlow,"recruitFlag":"N"}, function(resp){
                setTimeout(function(){
                    toPage(1);
                },300);
            } , null , true);
        });*/
    }

    function checkSingel(obj){
        if(!$(obj).is(':checked')){
            $("#checkAll").prop("checked",false);
        }else{
            var checkAllLen = $("input[type='checkbox'][class='check']").length;
            var checkLen = $("input[type='checkbox'][class='check']:checked").length;
            if(checkAllLen == checkLen){
                $("#checkAll").prop("checked",true);
            }
        }
    }
    function allCheck(){
        if($("#checkAll").is(':checked')){
            $(".check").prop("checked",true);
        }else{
            $(".check").prop("checked",false);
        }
    }
    function batchAuditOpt(){
        var checkLen = $(":checkbox[class='check']:checked").length;
        if(checkLen == 0){
            jboxTip("请勾选待审核信息！");
            return;
        }
        var recruitFlowList = [];
        $(":checkbox[class='check']:checked").each(function(){
            recruitFlowList.push(this.value);
        })

        jboxButtonConfirm("确认一键录取审核勾选学员？","一键录取","一键不录取",function(){

            jboxPost("<s:url value='/jsres/hospital/confireRecruitAll?recruitFlowList='/>"+recruitFlowList, {"recruitFlag":"Y"}, function(resp){
                setTimeout(function(){
                    toPage(1);
                },300);
            } , null , true);

        },function(){
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/hospital/confireRecruitAll?recruitFlowList='/>"+recruitFlowList,{"recruitFlag":"N"}, function(resp){
                setTimeout(function(){
                    toPage(1);
                },300);
            } , null , true);

        },300);

    }

    function exportForDetail(){

        if($(".docType:checked").length==0) {
            jboxTip("请选择人员类型！");
            return false;
        }
        var url = "<s:url value='/jsres/hospital/expertAchievementList?scoreType=all'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }

    $(document).ready(function(){
        $(":radio[name='sortType']").click(function () {
            toPage(1)
        });
    });
</script>
<div class="main_hd">
    <h2 class="underline">
        招录录取管理
    </h2>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="div_search">
        <form id="searchForm" action="<s:url value='/jsres/hospital/getAchievementList'/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage"/>
            <input id="auditStatusId" type="hidden" name="auditStatusId" value ="Passed"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">培训类别：</td>
                    <td>
                        <select name="catSpeId" id="catSpeId" class="select" onchange="changeTrainSpes(this.value)">
                            <option value="DoctorTrainingSpe">住院医师</option>
                            <%--<option value="">请选择</option>
                            <c:forEach items="${trainCategoryEnumList}" var="trainCategory">
                                <c:if test="${trainCategory.id ne 'WMFirst' and trainCategory.id ne 'WMSecond'}">
                                    <option name="${trainCategory.id}"  value="${trainCategory.id}" <c:if test="${param.catSpeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                                </c:if>
                            </c:forEach>--%>
                        </select>
                    </td>
                    <td class="td_left">培训专业：</td>
                    <td>
                        <select name="speId" id="speId" class="select">
                            <option value="">全部</option>
                        </select>
                    </td>
                    <td class="td_left">年&#12288;&#12288;级：</td>
                    <td><input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input indexNum" readonly="readonly"/></td>
                    <td class="td_left">姓&#12288;&#12288;名：</td>
                    <td><input type="text" name="userName" value="${param.userName}" class="input"/></td>
               </tr>
               <tr>
                    <td class="td_left">证&ensp;件&ensp;号：</td>
                    <td><input type="text" name="idNo" style="width: 122px" value="${param.idNo}" class="input"/></td>
                   <td class="td_left">录取状态：</td>
                   <td >
                       <select name="recruitFlag" class="select" >
                           <option value="">全部</option>
                           <option value="F" ${param.recruitFlag eq 'F'?'selected':''}>待审核</option>
                           <option value="Y" ${param.recruitFlag eq 'Y'?'selected':''}>已录取</option>
                           <option value="N" ${param.recruitFlag eq 'N'?'selected':''}>未录取</option>
                       </select>
                   </td>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>

                </tr>
                <tr>
                    <td class="td_left">排序方式：</td>
                    <td colspan="6">
                        <input type="radio" name="sortType" value="Exam"  id="radioa"  <c:if test="${param.sortType eq 'Exam'}"> checked</c:if> /><label for="radioa">按笔试成绩</label>&#12288;
                        <input type="radio" name="sortType" value="Interview"  id="radio0"  <c:if test="${param.sortType eq 'Interview'}"> checked</c:if> /><label for="radio0">按面试成绩</label>&#12288;
                        <input type="radio" name="sortType" value="Skill" id="radio1" <c:if test="${param.sortType eq 'Skill'}">checked</c:if> /><label for="radio1">按操作成绩</label>&#12288;
                        <input type="radio" name="sortType" value="Total"  id="radioAll" <c:if test="${param.sortType eq 'Total'}">checked</c:if> /><label for="radioAll">按总成绩</label>&#12288;
                    </td>
                </tr>
                <tr>
                    <td colspan="8">
                        <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                        <input class="btn_green" type="button" value="一键审核" onclick="batchAuditOpt()"/>
                        <input class="btn_green" type="button" value="导&#12288;出" onclick="exportForDetail();"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="contentDiv">

    </div>
    <div style="display: none;">
        <select id="WMFirst_select">
            <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
                <option value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="WMSecond_select">
            <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
                <option value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="AssiGeneral_select">
            <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
                <option value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="DoctorTrainingSpe_select">
            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                <option value="${dict.dictId}">${dict.dictName}</option>
            </c:forEach>
        </select>

    </div>
</div>