<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/common-art.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css"/>
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
        jboxPostLoad("achievementContent","<s:url value='/jsres/hospital/getAchievementDetails?scoreType=oper'/>",$("#searchForm").serialize(),false);
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

    function impSkillExam(){
    jboxOpen("<s:url value='/jsp/jsres/hospital/achievement/importSkillExam.jsp'/>", "操作成绩单导入", '600', '300');
    }

    function confireResult(recruitFlow,userName){
        jboxButtonConfirm("审核学员"+ userName +"操作成绩？","通过","不通过",function(){
            jboxPost("<s:url value='/jsres/hospital/confireResult'/>",{"recruitFlow":recruitFlow,"operStatusId":"Passed"}, function(resp){
                setTimeout(function(){
                    toPage(1);
                },300);
            } , null , true);

        },function(){
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/hospital/confireResult'/>",{"recruitFlow":recruitFlow,"operStatusId":"NotPassed"}, function(resp){
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

        jboxButtonConfirm("确认一键审核勾选学员操作成绩？","一键通过","一键不通过",function(){

            jboxPost("<s:url value='/jsres/hospital/confireResultAll?recruitFlowList='/>"+recruitFlowList, {"scoreType":"oper","resultFlag":"Passed"}, function(resp){
                setTimeout(function(){
                    toPage(1);
                },300);
            } , null , true);

        },function(){
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/hospital/confireResultAll?recruitFlowList='/>"+recruitFlowList,{"scoreType":"oper","resultFlag":"NotPassed"}, function(resp){
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
        var url = "<s:url value='/jsres/hospital/expertAchievementList?scoreType=oper'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }


   //显示隐藏
let flag = false;
function showOrHide(){

	if(flag){
		$(`.form_item_hide`).hide();
		// document.getElementById("hideForm").style.display='none';
		$("#open").text("展开")
		flag = false;
	}else {
		$(`.form_item_hide`).css('display','flex');
		// document.getElementById("hideForm").style.display='flex';
		$("#open").text("收起")
		flag = true;
	}

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


            <div class="form_search">
                <div class="form_item">
                    <div class="form_label">培训类别：</div>
                    <div class="form_content">
                        <select style="width: 161px" name="catSpeId" id="catSpeId" class="select" onchange="changeTrainSpes(this.value)">
                            <option value="DoctorTrainingSpe">住院医师</option>
                            <%--   <option value="">请选择</option>
                            <c:forEach items="${trainCategoryEnumList}" var="trainCategory">
                                <c:if test="${trainCategory.id ne 'WMFirst' and trainCategory.id ne 'WMSecond'}">
                                    <option name="${trainCategory.id}"  value="${trainCategory.id}" <c:if test="${param.catSpeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                                </c:if>
                            </c:forEach>--%>
                        </select>
                    </div>
                </div>
                <div class="form_item">
                    <div class="form_label">培训专业：</div>
                    <div class="form_content">
                        <select style="width: 161px" name="speId" id="speId" class="select">
                            <option value="">全部</option>
                        </select>
                    </div>
                </div>
                <div class="form_item">
                    <div class="form_label">年级：</div>
                    <div class="form_content">
                        <input style="width: 161px" type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input indexNum" readonly="readonly"/>
                    </div>
                </div>

                <div class="form_item">
                    <div class="form_label">姓名：</div>
                    <div class="form_content">
                        <input style="width: 161px" type="text" name="userName" value="${param.userName}" class="input"/>
                    </div>
                </div>

                <div class="form_item form_item_hide">
                    <div class="form_label">证件号：</div>
                    <div class="form_content" >
                        <input style="width: 161px" type="text" name="idNo" value="${param.idNo}" class="input"/>
                    </div>
                </div>

                <div class="form_item form_item_hide">
                    <div class="form_label">审核状态：</div>
                    <div class="form_content" >
                        <select style="width: 161px" name="operStatusId" class="select" >
                            <option value="">全部</option>
                            <option value="Auditing" ${param.operStatusId eq 'Auditing'?'selected':''}>待审核</option>
                            <option value="Passed" ${param.operStatusId eq 'Passed'?'selected':''}>审核通过</option>
                            <option value="NotPassed" ${param.operStatusId eq 'NotPassed'?'selected':''}>审核不通过</option>
                        </select>
                    </div>

                </div>
                <div class="form_item form_item_hide" style="width: 400px;">
                    <div class="form_label">人员类型：</div>
                    <div class="form_content" style="margin: auto 0" >
                         <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </div>
                </div>

            </div>
            
            <div style="margin-top: 15px;margin-bottom: 15px">
                <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                <input class="btn_green" type="button" value="一键审核" onclick="batchAuditOpt()"/>
                <input class="btn_green" type="button" value="成绩导入" onclick="impInterviewExam()"/>
                <input class="btn_green" type="button" value="成绩导出" onclick="exportForDetail();"/>
                <a style="color: #54B2E5;margin: auto 0 auto 15px;" onclick="showOrHide()" id="open">展开</a>
            </div>



<%--            <table class="searchTable">--%>
<%--                <tr>--%>
<%--                    <td class="td_left">培训类别：</td>--%>
<%--                    <td>--%>
<%--                        <select name="catSpeId" id="catSpeId" class="select" onchange="changeTrainSpes(this.value)">--%>
<%--                            <option value="DoctorTrainingSpe">住院医师</option>--%>
<%--                         &lt;%&ndash;   <option value="">请选择</option>--%>
<%--                            <c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
<%--                                <c:if test="${trainCategory.id ne 'WMFirst' and trainCategory.id ne 'WMSecond'}">--%>
<%--                                    <option name="${trainCategory.id}"  value="${trainCategory.id}" <c:if test="${param.catSpeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
<%--                                </c:if>--%>
<%--                             </c:forEach>&ndash;%&gt;--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                    <td class="td_left">培训专业：</td>--%>
<%--                    <td>--%>
<%--                        <select name="speId" id="speId" class="select">--%>
<%--                            <option value="">全部</option>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                    <td class="td_left">年&#12288;&#12288;级：</td>--%>
<%--                    <td><input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input indexNum" readonly="readonly"/></td>--%>
<%--                    <td class="td_left">姓&#12288;&#12288;名：</td>--%>
<%--                    <td><input type="text" name="userName" value="${param.userName}" class="input"/></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td class="td_left">证&ensp;件&ensp;号：</td>--%>
<%--                    <td><input type="text" name="idNo" value="${param.idNo}" class="input"/></td>--%>
<%--                    <td class="td_left">审核状态：</td>--%>
<%--                    <td >--%>
<%--                        <select name="operStatusId" class="select">--%>
<%--                            <option value="">全部</option>--%>
<%--                            <option value="Auditing" ${param.operStatusId eq 'Auditing'?'selected':''}>待审核</option>--%>
<%--                            <option value="Passed" ${param.operStatusId eq 'Passed'?'selected':''}>审核通过</option>--%>
<%--                            <option value="NotPassed" ${param.operStatusId eq 'NotPassed'?'selected':''}>审核不通过</option>--%>
<%--                        </select>--%>
<%--                    </td>--%>
<%--                    <td class="td_left">人员类型：</td>--%>
<%--                    <td colspan="3">--%>
<%--                        <c:forEach items="${jsResDocTypeEnumList}" var="type">--%>
<%--                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>--%>
<%--                        </c:forEach>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td colspan="8">--%>
<%--                        <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>--%>
<%--                        <input class="btn_green" type="button" value="一键审核" onclick="batchAuditOpt()"/>--%>
<%--                        <input class="btn_green" type="button" value="成绩导入" onclick="impSkillExam()"/>--%>
<%--                        <input class="btn_green" type="button" value="成绩导出" onclick="exportForDetail();"/>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--            </table>--%>
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
                    <th width="8%">操作成绩</th>
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
                        <td>${empty recruit.operResult ?"-":recruit.operResult}</td>
                        <td>
                                ${empty recruit.operStatusName?"待审核":recruit.operStatusName}
                        </td>
                        <c:choose>
                            <c:when test="${recruit.operStatusId eq 'Auditing' or empty recruit.operStatusId}">
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
