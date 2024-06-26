<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<style type="text/css">
</style>
<script type="text/javascript">
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
            eval($(this).attr("cusTrigger"));
        });
        if ("${param.liId}" != "") {
            $("#${param.liId}").addClass("tab_select");
        } else {
            $('li').first().addClass("tab_select");
        }
        $(".tab_select a").click();
        <c:if test="${!empty resBase}">
        <c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id or resBase.baseStatusId eq baseStatusEnumNotPassed.id }">
        $("#submitBtn").show();
        </c:if>
        </c:if>

        trainSpeInfo();
    });
    function submitInfo(){
        jboxConfirm("提交后不可修改！请确认修改的信息是否已保存，否则提交的仍是保存前的信息，确认提交?",function(){
            jboxPost("<s:url value='/jsres/base/submitBaseInfo'/>" , $("#baseForm").serialize() , function(resp){
                if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
                    setTimeout(function(){
                        $("#submitBtn").hide();
                        $(".tab_select").children().click();
                    },1000);
                }
            } , null , true);
        });
    }

    function getInfo(baseInfoName,baseFlow){
        var resBase = $("#baseStatusId").val();
        if(!resBase){
            loadInfo(baseInfoName,baseFlow);
        }else{
            editInfo(baseInfoName,baseFlow);
        }
    }

    function editInfo(baseInfoName,orgFlow,sessionNumber){
        var url="";
        if(baseInfoName=='${trainCategoryTypeEnumAfter2014.id}'||baseInfoName=='${trainCategoryTypeEnumBefore2014.id}'){
            url="<s:url value='/jsres/base/findTrainSpe'/>?editFlag=${GlobalConstant.FLAG_Y}&trainCategoryType="+baseInfoName+"&orgFlow="+orgFlow;
            jboxLoad("trainSpeContent",url,false);
        }else{
            url="<s:url value='/jsres/base/findAllBaseInfo'/>?viewFlag=${param.viewFlag}&editFlag=${GlobalConstant.FLAG_Y}&baseInfoName="+baseInfoName+"&baseFlow="+orgFlow+"&sessionNumber="+sessionNumber;
            jboxLoad("hosContent", url, false);
        }
    }

    function loadInfo(baseInfoName,baseFlow, sessionNumber){
        var r = $("#resBase").val();
        if(baseInfoName!="${GlobalConstant.BASIC_INFO}"&& (r=="" || r==null || r == "undefineded")){
            $(".tab_select").toggleClass("tab_select tab");
            $("#toptab li:first").toggleClass("tab_select tab");
            jboxTip("请先完善基本信息");
            return false;
        }
        if(sessionNumber) {
            var url="<s:url value='/jsres/base/findAllBaseInfo'/>?viewFlag=${param.viewFlag}&baseInfoName="+baseInfoName+"&baseFlow="+baseFlow+"&sessionNumber="+sessionNumber+"&ishos=${ishos}";
        }else if(${not empty ishos}) {
            var url="<s:url value='/jsres/base/findAllBaseInfo'/>?viewFlag=${param.viewFlag}&baseInfoName="+baseInfoName+"&baseFlow="+baseFlow+"&sessionNumber=${sessionNumber}"+"&ishos=${ishos}";
        } else {
            var url="<s:url value='/jsres/base/findAllBaseInfo'/>?viewFlag=${param.viewFlag}&baseInfoName="+baseInfoName+"&baseFlow="+baseFlow+"&sessionNumber=${pdfn:getCurrYear()}";
        }
        jboxLoad("hosContent", url, false);
    }

    function trainSpeInfo(){
        var url="<s:url value='/jsres/base/trainSpeMain'/>?orgFlow=${baseFlow}&isJoin=${isJoin}"+"&ishos=${ishos}&sessionNumber=${sessionNumber}";
        jboxLoad("hosContent", url, false);
    }

    function coopBaseInfo(){
        var url="<s:url value='/jsres/base/findCoopBase'/>";
        jboxLoad("hosContent", url, false);
    }

    function commuHospital(){
        jboxLoad("hosContent","<s:url value='/jsp/jsres/hospital/hos/commuHospital.jsp'/>",false);
    }
</script>
<div class="main_hd" id="speBaseManage" style="display: none">
    <input type="hidden" id="baseStatusId" name="baseStatusId" value="${resBase.baseStatusId}"/>
    <form id="baseForm" style="position:relative;">
        <input type="hidden" name="baseFlow" value="${sessionScope.currUser.orgFlow}"/>
        <input type="hidden" id="resBase" value="${resBase.orgFlow}"/>
    </form>
    <c:if test="${GlobalConstant.FLAG_Y != param.viewFlag}">
        <h2>
            专业基地管理
            <!-- 			<input id="submitBtn" class="btn_green" type="button" onclick="submitInfo();" value="提交"  style="display: none; float: right; margin-top: 30px; margin-right: 10px;" /> -->
        </h2>
    </c:if>
    <%--<div class="title_tab" id="toptab" style="margin-top: 5px;">
        <ul>
            <li id="tab"class="tab_select" cusTrigger="loadInfo('${GlobalConstant.BASIC_INFO}','${param.baseFlow}');" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">基地信息</a>
            </li>
            &lt;%&ndash;  <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y and !empty resBase.orgFlow }">
                   <li class="tab" cusTrigger="loadInfo('${GlobalConstant.TEACH_CONDITION}','${param.baseFlow}');" style="cursor: pointer;"><a>教学条件</a></li>
                   <li class="tab" cusTrigger="loadInfo('${GlobalConstant.ORG_MANAGE}','${param.baseFlow}');" style="cursor: pointer;"><a>组织管理</a></li>
                   <li class="tab" cusTrigger="loadInfo('${GlobalConstant.SUPPORT_CONDITION}','${param.baseFlow}');" style="cursor: pointer;"><a>支撑条件</a></li>
               </c:if>
                <c:if test="${param.viewFlag != GlobalConstant.FLAG_Y}">
                   <li class="tab" cusTrigger="loadInfo('${GlobalConstant.TEACH_CONDITION}','${param.baseFlow}');" style="cursor: pointer;"><a>教学条件</a></li>
                   <li class="tab" cusTrigger="loadInfo('${GlobalConstant.ORG_MANAGE}','${param.baseFlow}');" style="cursor: pointer;"><a>组织管理</a></li>
                   <li class="tab" cusTrigger="loadInfo('${GlobalConstant.SUPPORT_CONDITION}','${param.baseFlow}');" style="cursor: pointer;"><a>支撑条件</a></li>
               </c:if>&ndash;%&gt;
            <li class="tab" cusTrigger="trainSpeInfo();" style="cursor: pointer;">
                <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">专业基地</a>
            </li>
            <c:if test="${! empty jointOrgs and param.viewFlag != GlobalConstant.FLAG_Y  }">
                <li class="tab" cusTrigger="coopBaseInfo();" style="cursor: pointer;">
                    <a style="color: #000000;font: 15px 'Microsoft Yahei';font-weight: 400;">协同单位</a>
                </li>
            </c:if>
            <!--             <li class="tab" cusTrigger="commuHospital();" style="cursor: pointer;"><a href="javascript:void(0);" >社区培训基地</a></li> -->
        </ul>
    </div>--%>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="hosContent" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="height: 700px;overflow: auto;"</c:if>>
    </div>
</div>
