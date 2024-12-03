<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/common-art.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<script type="text/javascript">
    $(document).ready(function(){
        // 页面加载成功 年级选中当前年级
        if(${empty sessionNumber}){
            $.checkYear("sessionNumber","${sysCfgMap['jsres_local_sessionNumber']}",null);
        }else{
            $.checkYear("sessionNumber","${sessionNumber}",null);
        }
        <c:if test="${sessionScope.userListScope != GlobalConstant.USER_LIST_LOCAL}">
        //getCityArea();
        </c:if>
        <c:forEach items="${resDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if("${data}"=="${type.id}"){
            $("#"+"${data}").attr("checked","checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#"+"${type.id}").attr("checked","checked");
        </c:if>
        </c:forEach>

        var trainCategoryid="${param.catSpeId}";
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
        jboxPostLoad("achievementContent","<s:url value='/jsres/hospital/getAchievementDetailsAcc?scoreType=exam'/>",$("#searchForm").serialize(),false);
    }

    function auditRecruit(recruitFlow, doctorFlow, exType){
        var url = "<s:url value='/jsres/manage/province/doctor/auditRecruit'/>?openType=open&recruitFlow="+recruitFlow+"&doctorFlow="+doctorFlow + "&exType=" + exType;
        jboxOpen(url,"医师信息查看",1050,550);
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

    function impExamResult(){
        jboxOpen("<s:url value='/jsres/hospital/achievement/importExamResult'/>", "笔试成绩单导入", '600', '300');
    }

    function confireResult(recruitFlow,userName){
        jboxButtonConfirm("审核学员"+ userName +"笔试成绩？","通过","不通过",function(){
            jboxPost("<s:url value='/jsres/hospital/confireResult'/>",{"recruitFlow":recruitFlow,"examStatusId":"Passed"}, function(resp){
                setTimeout(function(){
                    toPage(1);
                },300);
            } , null , true);

        },function(){
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/hospital/confireResult'/>",{"recruitFlow":recruitFlow,"examStatusId":"NotPassed"}, function(resp){
                setTimeout(function(){
                    toPage(1);
                },300);
            } , null , true);

        },300);
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

        jboxButtonConfirm("确认一键审核勾选学员笔试成绩？","一键通过","一键不通过",function(){

            jboxPost("<s:url value='/jsres/hospital/confireResultAll?recruitFlowList='/>"+recruitFlowList, {"scoreType":"exam","resultFlag":"Passed"}, function(resp){
                setTimeout(function(){
                    toPage(1);
                },300);
            } , null , true);

        },function(){
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/hospital/confireResultAll?recruitFlowList='/>"+recruitFlowList,{"scoreType":"exam","resultFlag":"NotPassed"}, function(resp){
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
        var url = "<s:url value='/jsres/hospital/expertAchievementList?scoreType=exam'/>";
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
                            <option value="AssiGeneral">助理全科</option>
                          <%--  <option value="">请选择</option>
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
                    <td><input type="text" name="idNo" value="${param.idNo}" class="input"/></td>
                    <td class="td_left">审核状态：</td>
                    <td >
                        <select name="examStatusId" class="select">
                            <option value="">全部</option>
                            <option value="Auditing" ${param.examStatusId eq 'Auditing'?'selected':''}>待审核</option>
                            <option value="Passed" ${param.examStatusId eq 'Passed'?'selected':''}>审核通过</option>
                            <option value="NotPassed" ${param.examStatusId eq 'NotPassed'?'selected':''}>审核不通过</option>
                        </select>
                    </td>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td colspan="8">
                        <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                        <input class="btn_green" type="button" value="一键审核" onclick="batchAuditOpt()"/>
                        <input class="btn_green" type="button" value="成绩导入" onclick="impExamResult()"/>
                        <input class="btn_green" type="button" value="成绩导出" onclick="exportForDetail();"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="contentDiv">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th width="5%"><input type="checkbox" id="checkAll" onclick="allCheck()"/></th>
                    <th width="5%">序号</th>
                    <th width="8%">姓名</th>
                    <th width="10%">人员类型</th>
                    <th width="12%">证件号</th>
                    <th width="5%">年级</th>
                    <th width="10%">培训专业</th>
                    <th width="8%">笔试成绩</th>
                    <th width="10%">审核状态</th>
                    <th width="10%">操作</th>
                </tr>
                <c:forEach items="${recruitList}" var="recruit" varStatus="a">
                    <tr>
                        <td><input type="checkbox"  class="check" value="${recruit.recruitFlow}" onclick="checkSingel(this)"></td>
                        <td>${a.count}</td>
                        <td>${recruit.sysUser.userName}</td>
                        <td>${recruit.resDoctor.doctorTypeName}</td>
                        <td>${recruit.sysUser.idNo}</td>
                        <td>${recruit.sessionNumber}</td>
                        <td>${recruit.speName}</td>
                        <td>${empty recruit.examResult?"-":recruit.examResult}</td>
                        <td>
                           ${empty recruit.examStatusName?"待审核":recruit.examStatusName}
                        </td>
                        <c:choose>
                            <c:when test="${recruit.examStatusId eq 'Auditing' or empty recruit.examStatusId}">
                                <td><a class="btn" onclick="confireResult('${recruit.recruitFlow}','${recruit.sysUser.userName}');">审核</a>
                                    <%--<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>--%>
                                </td>
                             </c:when>
                            <c:otherwise>
                                <td>
                                    <c:if test="${recruit.recruitFlag ne 'Y'}">
                                        <a class="btn" onclick="confireResult('${recruit.recruitFlow}','${recruit.sysUser.userName}');">重审</a>
                                    </c:if>
                                   <%--<a class="btn" onclick="auditRecruit('${recruit.recruitFlow}','${recruit.doctorFlow}','view');">查看</a>--%>
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
                <c:if test="${empty recruitList}">
                    <tr>
                        <td colspan="20" >无记录！</td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(recruitList)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
    <div style="display: none;">
        <select id="WMFirst_select">
            <c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="WMSecond_select">
            <c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="AssiGeneral_select">
            <c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
            </c:forEach>
        </select>
        <select id="DoctorTrainingSpe_select">
            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
            </c:forEach>
        </select>

    </div>
</div>
