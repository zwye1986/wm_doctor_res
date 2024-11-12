<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
    function gradeSearch(page) {
        jboxStartLoading();
        var endScore = $("#endScore").val();
        var startScore = $("#startScore").val();
        if(endScore){
            var ex = /^[0-9]+\d*$/;
            if (!ex.test(endScore)) {
                jboxInfo("得分区间内应填入为整数！");
                return;
            }
            if (endScore < 0 || endScore > 100){
                jboxInfo("得分区间应在0~100范围内！");
                return;
            }
        }
        if(startScore){
            var ex = /^[0-9]+\d*$/;
            if (!ex.test(startScore)) {
                jboxInfo("得分区间内应填入为整数！");
                return;
            }
            if (startScore < 0 || startScore > 100){
                jboxInfo("得分区间应在0~100范围内！");
                return;
            }
            if (endScore){
                if (parseInt(endScore) < parseInt(startScore)){
                    jboxInfo("得分区间填写有误！");
                    return;
                }
            }
        }
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        // $('#gradeSearchForm').submit();
        jboxPostLoad("doctorContent","<s:url value='/res/evaluateHospitalResult/main'/>", $("#gradeSearchForm").serialize(),false)
    }

    // function toPageByOrg(page) {
    //     $("select[name='deptFlow']").val("");
    //     if (page != undefined) {
    //         $("#currentPage").val(page);
    //     }
    //     $('#gradeSearchForm').submit();
    // }

    function detailShow(span,clazz){
//		$("font",span).toggle();
        $("."+clazz+"Show").fadeToggle(100);
    }

    function operDetail(name,keyCode,date,processFlow){
        var url = "<s:url value='/res/evaluateHospitalResult/gradeSearchDoc'/>?gradeRole=${param.gradeRole}&keyCode="+keyCode+"&date="+date+"&processFlow="+processFlow+"&role=${role}";
        jboxOpen(url,name+"评分详情",800,500);
    }

    function operDetailByCfgFlow(name,keyCode,date,cfgFlow){
        var url = "<s:url value='/res/evaluateHospitalResult/gradeSearchDoc'/>?gradeRole=${param.gradeRole}&keyCode="+keyCode+"&date="+date+"&cfgFlow="+cfgFlow+"&role=${role}";
        jboxOpen(url,name+"评分详情",800,500);
    }

    function operDetail2(name,recFlow,date,processFlow){
        var url = "<s:url value='/res/evaluateHospitalResult/gradeSearchDoc'/>?gradeRole=${param.gradeRole}&recFlow="+recFlow+"&date="+date+"&processFlow="+processFlow+"&role=${role}";
        jboxOpen(url,name+"评分详情",800,500);
    }

    function operNurseDetail(recTypeName, recTypeId, recFlow, processFlow, schResultFlow, schDeptFlow, rotationFlow, doctorFlow) {
        jboxOpen("<s:url value='/res/nurse/operNurseDetail'/>?roleFlag=${roleFlag}&processFlow=" + processFlow + "&resultFlow=" + schResultFlow + "&schDeptFlow=" + schDeptFlow + "&rotationFlow=" +
            rotationFlow + "&recTypeId=" + recTypeId + "&recFlow=" + recFlow + "&doctorFlow=" + doctorFlow,
            recTypeName+"得分详情", 1200, 700);
    }

    function exportEval() {
        debugger;
        var endScore = $("#endScore").val();
        var startScore = $("#startScore").val();
        if(endScore){
            var ex = /^[0-9]+\d*$/;
            if (!ex.test(endScore)) {
                jboxInfo("得分区间内应填入为整数！");
                return;
            }
            if (endScore < 0 || endScore > 100){
                jboxInfo("得分区间应在0~100范围内！");
                return;
            }
        }
        if(startScore){
            var ex = /^[0-9]+\d*$/;
            if (!ex.test(startScore)) {
                jboxInfo("得分区间内应填入为整数！");
                return;
            }
            if (startScore < 0 || startScore > 100){
                jboxInfo("得分区间应在0~100范围内！");
                return;
            }
            if (endScore){
                if (endScore < startScore){
                    jboxInfo("得分区间填写有误！");
                    return;
                }
            }
        }
        if (${empty datas}) {
            jboxTip("当前无记录!");
            return;
        }
        var gradeRole = $("#gradeRole").val();
        var role = $("#role").val();
        <%--var url = "<s:url value='/res/evaluateHospitalResult/exportEval?gradeRole='/>" + gradeRole + "&role=" + role;--%>
        var url = "<s:url value='/res/evaluateHospitalResult/exportEval'/>";
        jboxTip("导出中...");
        // jboxSubmit($("#gradeSearchForm"), url, null, null, false);

        // jboxPost(url,$("#gradeSearchForm").serialize(),function(resp){
        // },null,false);


        <%--var url = "<s:url value='/jsres/manage/exportSzList?orgFlow=${sessionScope.currUser.orgFlow}'/>";--%>
        jboxExp($("#gradeSearchForm"), url);



        jboxEndLoading();
    }

    function checkStart() {
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();
        if (''!=endTime && ''!= startTime  && startTime>endTime){
            jboxTip("开始时间不能大于结束时间！");
            return;
        }
    }

    $(function () {
        if ('${gradeRole}'=='patient'){
            debugger
            <c:forEach items="${jsResDocTypeEnumList}" var="type">
                <c:forEach items="${docType}" var="data">
                if("${data}"=="${type.id}"){
                    $("#"+"${data}").attr("checked","checked");
                }
                </c:forEach>
                <c:if test="${empty docType}">
                    $("#"+"${type.id}").attr("checked","checked");
                </c:if>
            </c:forEach>
        }
    });

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


    function grade(recFlow) {
        jboxOpen("<s:url value='/res/evaDoctorResult/patientMainInfo'/>?recFlow=" + recFlow,
            '病人评价详情', 1200, 660);
    }
</script>

<div class="mainright">
    <div class="top-tab">
        <div class="clearfix" style="padding-top: 10px;">
                <div class="div_search">
                    <form id="gradeSearchForm" action="<s:url value="/res/evaluateHospitalResult/main"/>" method="post">
                        <input type="hidden" id="currentPage" name="currentPage">
                        <input type="hidden" id="gradeRole" name="gradeRole" value="${param.gradeRole}">
                        <input type="hidden" id="trainingTypeId" name="trainingTypeId" value="${param.trainingTypeId}">
                        <input type="hidden" id="role" name="role" value="${role}">
                        <c:if test="${param.gradeRole eq 'doctor' or empty param.gradeRole}">

                            <div class="form_search">
                                <div class="form_item">
                                    <div class="form_label">姓&#12288;&#12288;名：</div>
                                    <div class="form_content" >
                                        <input type="text" name="userName" value="${param.userName}" class="input" />
                                    </div>
                                </div>
                                <div class="form_item">
                                    <div class="form_label">培训专业：</div>
                                    <div class="form_content" >
                                        <select name="trainingSpeId" class="select" style="width: 161px;">
                                            <option  value="">全部</option>
                                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                                <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form_item">
                                    <div class="form_label">开始时间：</div>
                                    <div class="form_content" >
                                        <input name="startTime" id="startTime" style="width: 161px;" placeholder="请选择开始时间"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                               onchange="checkStart()" value="${param.startTime}" class="input">
                                    </div>
                                </div>
                                <div class="form_item">
                                    <div class="form_label">结束时间：</div>
                                    <div class="form_content">
                                        <input name="endTime" id="endTime" style="width: 161px;" placeholder="请选择结束时间"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                               onchange="checkStart()" value="${param.endTime}" class="input">
                                    </div>
                                </div>

                                <div class="form_item form_item_hide">
                                    <div class="form_label">年&#12288;&#12288;级：</div>
                                    <div class="form_content">
                                        <input style="width: 161px;" type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="input"/>
                                    </div>
                                </div>

                                <div class="form_item form_item_hide">
                                    <div class="form_label">得分区间：</div>
                                    <div class="form_content">
                                        <input type="text" id="startScore" name="startScore" value="${param.startScore}" class="input" style="width: 66px;"/>~<input type="text" id="endScore" name="endScore" value="${param.endScore}"class="input" style="width: 66px;"/>
                                    </div>
                                </div>
                                <div class="form_item form_item_hide">
                                    <div class="form_label">人员类型：</div>
                                    <div class="form_content" >
                                        <select name="studentType" class="select" >
                                            <option  value="">全部</option>
                                            <c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">
                                                <option value="${dict.dictId}" ${param.studentType eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form_item" style="text-align: right">
                                    <div class="form_label " style="width: auto">
                                        <a style="color: #54B2E5;margin-right: 15px ;margin-left: 20px" onclick="showOrHide()" id="open">展开</a>
                                    </div>
                                    <div class="form_content">
                                        <input type="button" class="btn_green" value="查&#12288;询" onclick="gradeSearch()">
                                        <c:if test="${'360teacher' ne param.gradeRole and '360doctor' ne param.gradeRole and  'ManageDoctorAssess360' ne param.gradeRole}">
                                            <input  type="button" class="btn_green" value="导&#12288;出" onclick="exportEval();"/>
                                        </c:if>
                                    </div>
                                </div>
                            </div>

                        </c:if>


                        <c:if test="${param.gradeRole eq 'teacher'}">

                            <div class="form_search">
                                <div class="form_item">
                                    <div class="form_label">姓&#12288;&#12288;名：</div>
                                    <div class="form_content" >
                                        <input type="text" name="userName" value="${param.userName}" class="input" />
                                    </div>
                                </div>

                                <div class="form_item">
                                    <div class="form_label">科&#12288;&#12288;室：</div>
                                    <div class="form_content">
                                        <select name="deptFlow" class="select" style="width: 182px;margin-right: 20px;    margin-left: 5px;">
                                            <option value=""></option>
                                            <c:forEach items="${depts}" var="dept">
                                                <option value="${dept.deptFlow}" <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>


                                <div class="form_item">
                                    <div class="form_label">开始时间：</div>
                                    <div class="form_content" >
                                        <input name="startTime" id="startTime" style="width: 161px;" placeholder="请选择开始时间"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                               onchange="checkStart()" value="${param.startTime}" class="input">
                                    </div>
                                </div>
                                <div class="form_item">
                                    <div class="form_label">结束时间：</div>
                                    <div class="form_content">
                                        <input name="endTime" id="endTime" style="width: 161px;" placeholder="请选择结束时间"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                               onchange="checkStart()" value="${param.endTime}" class="input">
                                    </div>
                                </div>

                                <div class="form_item form_item_hide">
                                    <div class="form_label">年&#12288;&#12288;级：</div>
                                    <div class="form_content">
                                        <input  type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="input"/>
                                    </div>
                                </div>

                                <div class="form_item form_item_hide">
                                    <div class="form_label">得分区间：</div>
                                    <div class="form_content">
                                        <input type="text" id="startScore" name="startScore" value="${param.startScore}" class="input" style="width: 66px;"/>~<input type="text" id="endScore" name="endScore" value="${param.endScore}"class="input" style="width: 66px;"/>
                                    </div>
                                </div>

                                <div class="form_item" style="text-align: right">
                                    <div class="form_label " style="width: auto">
                                        <a style="color: #54B2E5;margin-right: 15px ;margin-left: 20px" onclick="showOrHide()" id="open">展开</a>
                                    </div>
                                    <div class="form_content">
                                        <input type="button" class="btn_green" value="查&#12288;询" onclick="gradeSearch()">
                                        <c:if test="${'360teacher' ne param.gradeRole and '360doctor' ne param.gradeRole and  'ManageDoctorAssess360' ne param.gradeRole}">
                                            <input  type="button" class="btn_green" value="导&#12288;出" onclick="exportEval();"/>
                                        </c:if>
                                    </div>
                                </div>
                            </div>

                        </c:if>

                        <c:if test="${param.gradeRole eq 'head'}">


                            <div class="form_search">

                                <div class="form_item">
                                    <div class="form_label">科&#12288;&#12288;室：</div>
                                    <div class="form_content">
                                        <select name="deptFlow" class="select" style="width: 182px;margin-right: 20px;    margin-left: 5px;">
                                            <option value=""></option>
                                            <c:forEach items="${depts}" var="dept">
                                                <option value="${dept.deptFlow}" <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form_item ">
                                    <div class="form_label">年&#12288;&#12288;份：</div>
                                    <div class="form_content">
                                        <input  type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="input"/>
                                    </div>
                                </div>


                                <div class="form_item">
                                    <div class="form_label">开始时间：</div>
                                    <div class="form_content" >
                                        <input name="startTime" id="startTime" style="width: 161px;" placeholder="请选择开始时间"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                               onchange="checkStart()" value="${param.startTime}" class="input">
                                    </div>
                                </div>
                                <div class="form_item">
                                    <div class="form_label">结束时间：</div>
                                    <div class="form_content">
                                        <input name="endTime" id="endTime" style="width: 161px;" placeholder="请选择结束时间"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                               onchange="checkStart()" value="${param.endTime}" class="input">
                                    </div>
                                </div>

                                <div class="form_item form_item_hide">
                                    <div class="form_label">得分区间：</div>
                                    <div class="form_content">
                                        <input type="text" id="startScore" name="startScore" value="${param.startScore}" class="input" style="width: 66px;"/>~<input type="text" id="endScore" name="endScore" value="${param.endScore}"class="input" style="width: 66px;"/>
                                    </div>
                                </div>

                                <div class="form_item" style="text-align: right">
                                    <div class="form_label " style="width: auto">
                                        <a style="color: #54B2E5;margin-right: 15px ;margin-left: 20px" onclick="showOrHide()" id="open">展开</a>
                                    </div>
                                    <div class="form_content">
                                        <input type="button" class="btn_green" value="查&#12288;询" onclick="gradeSearch()">
                                        <c:if test="${'360teacher' ne param.gradeRole and '360doctor' ne param.gradeRole and  'ManageDoctorAssess360' ne param.gradeRole}">
                                            <input  type="button" class="btn_green" value="导&#12288;出" onclick="exportEval();"/>
                                        </c:if>
                                    </div>
                                </div>
                            </div>

                        </c:if>
                        <c:if test="${param.gradeRole eq 'nurse'}">

                            <div class="form_search">

                                <div class="form_item">
                                    <div class="form_label">姓&#12288;&#12288;名：</div>
                                    <div class="form_content">
                                        <input type="text" name="userName" value="${param.userName}" class="input" style="width:161px"/>
                                    </div>
                                </div>



                                <div class="form_item ">
                                    <div class="form_label">年&#12288;&#12288;份：</div>
                                    <div class="form_content">
                                        <input  type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="input"/>
                                    </div>
                                </div>

                                <div class="form_item">
                                    <div class="form_label">开始时间：</div>
                                    <div class="form_content" >
                                        <input name="startTime" id="startTime" style="width: 161px;" placeholder="请选择开始时间"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                               onchange="checkStart()" value="${param.startTime}" class="input">
                                    </div>
                                </div>
                                <div class="form_item">
                                    <div class="form_label">结束时间：</div>
                                    <div class="form_content">
                                        <input name="endTime" id="endTime" style="width: 161px;" placeholder="请选择结束时间"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"
                                               onchange="checkStart()" value="${param.endTime}" class="input">
                                    </div>
                                </div>

                                 <div class="form_item form_item_hide">
                                    <div class="form_label">科&#12288;&#12288;室：</div>
                                    <div class="form_content">
                                        <select name="deptFlow" class="select" style="width: 182px;margin-right: 20px;    margin-left: 5px;">
                                            <option value=""></option>
                                            <c:forEach items="${depts}" var="dept">
                                                <option value="${dept.deptFlow}" <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form_item form_item_hide">
                                    <div class="form_label">得分区间：</div>
                                    <div class="form_content">
                                        <input type="text" id="startScore" name="startScore" value="${param.startScore}" class="input" style="width: 66px;"/>~<input type="text" id="endScore" name="endScore" value="${param.endScore}"class="input" style="width: 66px;"/>
                                    </div>
                                </div>

                                <div class="form_item" style="text-align: right">
                                    <div class="form_label " style="width: auto">
                                        <a style="color: #54B2E5;margin-right: 15px;margin-left: 20px" onclick="showOrHide()" id="open">展开</a>
                                    </div>
                                    <div class="form_content">
                                        <input type="button" class="btn_green" value="查&#12288;询" onclick="gradeSearch()">
                                        <c:if test="${'360teacher' ne param.gradeRole and '360doctor' ne param.gradeRole and  'ManageDoctorAssess360' ne param.gradeRole}">
                                            <input  type="button" class="btn_green" value="导&#12288;出" onclick="exportEval();"/>
                                        </c:if>
                                    </div>
                                </div>
                            </div>

                        </c:if>
                        <c:if test="${param.gradeRole eq 'patient'}">


                            <div class="form_search">
                                <div class="form_item">
                                    <div class="form_label">姓&#12288;&#12288;名：</div>
                                    <div class="form_content" >
                                        <input type="text" name="userName" value="${param.userName}" class="input" />
                                    </div>
                                </div>
                                <div class="form_item">
                                    <div class="form_label">培训专业：</div>
                                    <div class="form_content" >
                                        <select name="trainingSpeId" class="select" style="width: 161px;">
                                            <option  value="">全部</option>
                                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                                <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form_item">
                                    <div class="form_label">培训科室：</div>
                                    <div class="form_content" >
                                        <select name="deptFlow" class="select" style="width: 150px;">
                                        <option value="">全部</option>
                                        <c:forEach items="${deptList}" var="dept">
                                            <option value="${dept.deptFlow}"
                                                    <c:if test="${deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                                        </c:forEach>
                                    </select>
                                    </div>
                                </div>


                                <div class="form_item">
                                    <div class="form_label">评价人名称：</div>
                                    <div class="form_content">
                                        <input type="text" name="patientName" value="${param.patientName}" class="input"
                                               />
                                    </div>
                                </div>

                                <div class="form_item">
                                    <div class="form_label">年&#12288;&#12288;级：</div>
                                    <div class="form_content">
                                        <input  type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="input"/>
                                    </div>
                                </div>

                                <div class="form_item">
                                    <div class="form_label">人员类型：</div>
                                    <div class="form_content">
                                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="docType" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
                                        </c:forEach>
                                    </div>

                                </div>


                                <div class="form_item" style="text-align: right">
                                    <div class="form_label " style="width: auto">
                                        <a style="color: #54B2E5;margin-right: 15px;margin-left: 20px" onclick="showOrHide()" id="open">展开</a>
                                    </div>
                                    <div class="form_content">
                                        <input type="button" class="btn_green" value="查&#12288;询" onclick="gradeSearch()">
                                        <c:if test="${'360teacher' ne param.gradeRole and '360doctor' ne param.gradeRole and  'ManageDoctorAssess360' ne param.gradeRole}">
                                            <input  type="button" class="btn_green" value="导&#12288;出" onclick="exportEval();"/>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:if>






<%--                        <td class="queryDiv" style="min-width: 960px;max-width: 960px;">--%>
<%--                            <table class="search_table">--%>


<%--                                <c:if test="${param.gradeRole eq 'teacher'}">--%>
<%--                                    <tr>--%>
<%--                                        <td class="td_left">姓&#12288;&#12288;名：</td>--%>
<%--                                        <td><input type="text" name="userName" value="${param.userName}" class="input" style="width:176px;margin-right: 20px;"/></td>--%>
<%--                                        <td class="td_left">科&#12288;&#12288;室：</td>--%>
<%--                                        <td>--%>
<%--                                            <select name="deptFlow" class="select" style="width: 182px;margin-right: 20px;    margin-left: 5px;">--%>
<%--                                                <option value=""></option>--%>
<%--                                                <c:forEach items="${depts}" var="dept">--%>
<%--                                                    <option value="${dept.deptFlow}" <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>--%>
<%--                                                </c:forEach>--%>
<%--                                            </select>--%>
<%--                                        </td>--%>
<%--                                        <td class="td_left">年&#12288;&#12288;份：</td>--%>
<%--                                        <td><input style="width: 176px;" type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="input"/></td>--%>
<%--                                    </tr>--%>
<%--                                    <tr>--%>
<%--                                        <td class="td_left">开始时间：</td>--%>
<%--                                        <td>--%>
<%--                                            <input name="startTime" id="startTime" style="margin-left: 5px;width: 176px;" placeholder="请选择开始时间"--%>
<%--                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"--%>
<%--                                                   onchange="checkStart()" value="${param.startTime}" class="input">--%>
<%--                                        </td>--%>
<%--                                        <td class="td_left">结束时间：</td>--%>
<%--                                        <td>--%>
<%--                                            <input name="endTime" id="endTime" style="margin-left: 5px;width: 176px;" placeholder="请选择结束时间"--%>
<%--                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"--%>
<%--                                                   onchange="checkStart()" value="${param.endTime}" class="input">--%>
<%--                                        </td>--%>
<%--                                        <td class="td_left">得分区间：</td>--%>
<%--                                        <td><input type="text" id="startScore" name="startScore" value="${param.startScore}" class="input" style="width: 75px;"/>~<input type="text" id="endScore" name="endScore" value="${param.endScore}"class="input" style="width: 75px;"/></td>--%>
<%--                                    </tr>--%>
<%--                                    <tr>--%>
<%--                                        <td id="func" colspan="6">--%>
<%--                                            <input type="button" class="btn_green" value="查&#12288;询" onclick="gradeSearch()">--%>
<%--                                            <c:if test="${'360teacher' ne param.gradeRole and '360doctor' ne param.gradeRole and  'ManageDoctorAssess360' ne param.gradeRole}">--%>
<%--                                                <input style="margin-left: 20px" type="button" class="btn_green" value="导&#12288;出" onclick="exportEval();"/>--%>
<%--                                            </c:if>--%>
<%--                                        </td>--%>
<%--                                    </tr>--%>
<%--                                </c:if>--%>

<%--                                <c:if test="${param.gradeRole eq 'head'}">--%>
<%--                                    <tr>--%>
<%--                                        <td class="td_left">科&#12288;&#12288;室：</td>--%>
<%--                                        <td>--%>
<%--                                            <select name="deptFlow" class="select" style="width: 182px;margin-right: 20px;    margin-left: 5px;">--%>
<%--                                                <option value=""></option>--%>
<%--                                                <c:forEach items="${depts}" var="dept">--%>
<%--                                                    <option value="${dept.deptFlow}" <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>--%>
<%--                                                </c:forEach>--%>
<%--                                            </select>--%>
<%--                                        </td>--%>
<%--                                        <td class="td_left">年&#12288;&#12288;份：</td>--%>
<%--                                        <td><input style="width: 176px;" type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="input"/></td>--%>
<%--                                        <td class="td_left">得分区间：</td>--%>
<%--                                        <td><input type="text" id="startScore" name="startScore" value="${param.startScore}" class="input" style="width: 75px;"/>~<input type="text" id="endScore" name="endScore" value="${param.endScore}"class="input" style="width: 75px;"/></td>--%>
<%--                                    </tr>--%>
<%--                                    <tr>--%>
<%--                                        <td class="td_left">开始时间：</td>--%>
<%--                                        <td>--%>
<%--                                            <input name="startTime" id="startTime" style="margin-left: 5px;width: 176px;" placeholder="请选择开始时间"--%>
<%--                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"--%>
<%--                                                   onchange="checkStart()" value="${param.startTime}" class="input">--%>
<%--                                        </td>--%>
<%--                                        <td class="td_left">结束时间：</td>--%>
<%--                                        <td>--%>
<%--                                            <input name="endTime" id="endTime" style="margin-left: 5px;width: 176px;" placeholder="请选择结束时间"--%>
<%--                                                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"--%>
<%--                                                   onchange="checkStart()" value="${param.endTime}" class="input">--%>
<%--                                        </td>--%>
<%--                                        <td id="func" colspan="2">--%>
<%--                                            <input type="button" class="btn_green" value="查&#12288;询" onclick="gradeSearch()">--%>
<%--                                            <c:if test="${'360teacher' ne param.gradeRole and '360doctor' ne param.gradeRole and  'ManageDoctorAssess360' ne param.gradeRole}">--%>
<%--                                                <input style="margin-left: 20px" type="button" class="btn_green" value="导&#12288;出" onclick="exportEval();"/>--%>
<%--                                            </c:if>--%>
<%--                                        </td>--%>
<%--                                    </tr>--%>
<%--                                </c:if>--%>
<%--                                <c:if test="${param.gradeRole eq 'nurse'}">--%>
<%--                                <tr>--%>
<%--                                    <td class="td_left">姓&#12288;&#12288;名：</td>--%>
<%--                                    <td><input type="text" name="userName" value="${param.userName}" class="input" style="width:176px;margin-right: 20px;"/></td>--%>
<%--                                    <td class="td_left">科&#12288;&#12288;室：</td>--%>
<%--                                    <td>--%>
<%--                                        <select name="deptFlow" class="select" style="width: 182px;margin-right: 20px;    margin-left: 5px;">--%>
<%--                                            <option value=""></option>--%>
<%--                                            <c:forEach items="${depts}" var="dept">--%>
<%--                                                <option value="${dept.deptFlow}" <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>--%>
<%--                                            </c:forEach>--%>
<%--                                        </select>--%>
<%--                                    </td>--%>
<%--                                    <td class="td_left">年&#12288;&#12288;份：</td>--%>
<%--                                    <td><input style="width: 176px;" type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="input"/></td>--%>
<%--                                </tr>--%>
<%--                                <tr>--%>
<%--                                    <td class="td_left">开始时间：</td>--%>
<%--                                    <td>--%>
<%--                                        <input name="startTime" id="startTime" style="margin-left: 5px;width: 176px;" placeholder="请选择开始时间"--%>
<%--                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"--%>
<%--                                               onchange="checkStart()" value="${param.startTime}" class="input">--%>
<%--                                    </td>--%>
<%--                                    <td class="td_left">结束时间：</td>--%>
<%--                                    <td>--%>
<%--                                        <input name="endTime" id="endTime" style="margin-left: 5px;width: 176px;" placeholder="请选择结束时间"--%>
<%--                                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"--%>
<%--                                               onchange="checkStart()" value="${param.endTime}" class="input">--%>
<%--                                    </td>--%>
<%--                                    <td class="td_left">得分区间：</td>--%>
<%--                                    <td><input type="text" id="startScore" name="startScore" value="${param.startScore}" class="input" style="width: 75px;"/>~<input type="text" id="endScore" name="endScore" value="${param.endScore}"class="input" style="width: 75px;"/></td>--%>
<%--                                </tr>--%>
<%--                                <tr>--%>
<%--                                    <td id="func" colspan="6">--%>
<%--                                        <input type="button" class="btn_green" value="查&#12288;询" onclick="gradeSearch()">--%>
<%--                                        <c:if test="${'360teacher' ne param.gradeRole and '360doctor' ne param.gradeRole and  'ManageDoctorAssess360' ne param.gradeRole}">--%>
<%--                                            <input style="margin-left: 20px" type="button" class="btn_green" value="导&#12288;出" onclick="exportEval();"/>--%>
<%--                                        </c:if>--%>
<%--                                    </td>--%>
<%--                                </tr>--%>
<%--                            </c:if>--%>
<%--                                <c:if test="${param.gradeRole eq 'patient'}">--%>
<%--                                    <tr>--%>
<%--                                        <td class="td_left">--%>
<%--                                            姓&#12288;&#12288;名：--%>
<%--                                        </td>--%>
<%--                                        <td>--%>
<%--                                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"--%>
<%--                                                   style="width: 100px;"/>--%>
<%--                                        </td>--%>
<%--                                        <td style="width: 80px;text-align: right;">--%>
<%--                                            培训专业：--%>
<%--                                        </td>--%>
<%--                                        <td>--%>
<%--                                            <select name="trainingSpeId" class="select">--%>
<%--                                                <option value="">全部</option>--%>
<%--                                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="training">--%>
<%--                                                    <option value="${training.dictId}"--%>
<%--                                                            <c:if test="${param.trainingSpeId eq training.dictId}">selected</c:if>>${training.dictName}</option>--%>
<%--                                                </c:forEach>--%>
<%--                                            </select>--%>
<%--                                        </td>--%>

<%--                                        <td style="width: 80px;text-align: right;">--%>
<%--                                            培训科室：--%>
<%--                                        </td>--%>
<%--                                        <td>--%>
<%--                                            <select name="deptFlow" class="select" style="width: 150px;">--%>
<%--                                                <option value="">全部</option>--%>
<%--                                                <c:forEach items="${deptList}" var="dept">--%>
<%--                                                    <option value="${dept.deptFlow}"--%>
<%--                                                            <c:if test="${deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>--%>
<%--                                                </c:forEach>--%>
<%--                                            </select>--%>
<%--                                        </td>--%>
<%--                                        <td style="width: 95px;text-align: right;">--%>
<%--                                            评价人名称：--%>
<%--                                        </td>--%>
<%--                                        <td>--%>
<%--                                            <input type="text" name="patientName" value="${param.patientName}" class="input"--%>
<%--                                                   style="width: 100px;"/>--%>
<%--                                        </td>--%>
<%--                                    </tr>--%>
<%--                                    <tr>--%>
<%--                                        <td class="td_left">--%>
<%--                                            年&#12288;&#12288;级：--%>
<%--                                        </td>--%>
<%--                                        <td>--%>
<%--                                            <input style="width: 100px;" type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="input"/>--%>
<%--                                        </td>--%>
<%--                                        <td style="width: 80px;text-align: right;">--%>
<%--                                            人员类型：--%>
<%--                                        </td>--%>
<%--                                        <td colspan="3">--%>
<%--                                            <c:forEach items="${jsResDocTypeEnumList}" var="type">--%>
<%--                                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="docType" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>--%>
<%--                                            </c:forEach>--%>
<%--                                        </td>--%>
<%--                                        <td colspan="2"  >--%>
<%--                                            <input type="button" style="margin-left: 15px;"  class="btn_green" value="查&#12288;询" id="searchDoctor" onclick="gradeSearch()">--%>
<%--                                        </td>--%>
<%--                                    </tr>--%>
<%--                                </c:if>--%>

<%--                            </table>--%>

                            <%--<c:if test="${param.gradeRole ne 'doctor'}">--%>
                                <%--<div class="inputDiv">--%>
                                    <%--<label class="qlable">科&#12288;&#12288;室：</label>--%>
                                    <%--<select name="deptFlow" class="select">--%>
                                        <%--<option value=""></option>--%>
                                        <%--<c:forEach items="${depts}" var="dept">--%>
                                            <%--<option value="${dept.deptFlow}" <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${param.gradeRole eq 'doctor'}">--%>
                                <%--<div class="inputDiv">--%>
                                    <%--<label class="qlable">培训专业：</label>--%>
                                    <%--<select name="trainingSpeId" class="select" >--%>
                                        <%--<option  value="">全部</option>--%>
                                        <%--<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
                                            <%--<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                            <%--</c:if>--%>
                            <%--<div class="inputDiv">--%>
                                <%--年&#12288;&#12288;份：--%>
                                <%--<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" class="input"/>--%>
                            <%--</div>--%>



                            <%--<div class="inputDiv" style="min-width: 275px;max-width: 275px;">--%>
                                <%--得分区间：--%>
                                <%--<input type="text" id="startScore" name="startScore" value="${param.startScore}" class="input" style="width: 75px;"/>~<input type="text" id="endScore" name="endScore" value="${param.endScore}"class="input" style="width: 75px;"/>--%>
                            <%--</div>--%>
                            <%--<c:if test="${param.gradeRole eq 'doctor'}">--%>
                                <%--<div class="inputDiv">--%>
                                    <%--<label class="qlable">人员类型：</label>--%>
                                    <%--<select name="studentType" class="select" >--%>
                                        <%--<option  value="">全部</option>--%>
                                        <%--<c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">--%>
                                            <%--<option value="${dict.dictId}" ${param.studentType eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                            <%--</c:if>--%>
                            <%--<div class="lastDiv" style="min-width: 182px;max-width: 182px;">--%>
                                <%--<input type="button" class="btn_green" value="查&#12288;询" onclick="gradeSearch()">--%>
                                <%--<input type="button" class="btn_green" value="导&#12288;出" onclick="exportEval();"/>--%>
                            <%--</div>--%>
                        </div>
                    </form>

                    <div class="search_table">

                        <c:if test="${param.gradeRole eq 'ManageDoctorAssess360'}">
                            <table class="grid" width="100%" style="margin-top: 10px;">
                                <colgroup>
                                        <col width="6%"/>
                                        <col width="10%"/>
                                        <col width="18%"/>
                                        <col width="8%"/>
                                        <col width="10%"/>
                                        <col width="8%"/>
                                        <col width="9%"/>
                                        <col width="9%"/>
                                        <col width="12%"/>
                                        <col width="10%"/>
                                </colgroup>
                                <tr>
                                    <th>序号</th>
                                    <th>姓名</th>
                                    <th>培训专业</th>
                                    <th>年级</th>
                                    <th>轮转时间</th>
                                    <th >标准分</th>
                                    <th >教学秘书</th>
                                    <th >教学主任</th>
                                    <th >专业基地主任</th>
                                    <th >基地管理员</th>
                                </tr>
                                <c:forEach items="${datas}" var="data" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td>${data.userName}</td>
                                        <td>${data.trainingSpeName}</td>
                                        <td>${data.sessionNumber}</td>
                                        <td>
                                                ${data.schStartDate} <br>
                                                ${data.schEndDate}
                                        </td>
                                        <td>${scoreSumMap[data.cfgFlow]}</td>
                                        <c:set value="${data.processFlow}secretary" var="info"/>
                                        <c:set value="${data.processFlow}secretaryrecFlow" var="recFlowKey"/>
                                        <td>
                                            <a style="cursor: pointer;color: blue;font-weight: normal;"
                                               onclick="operDetail2('${data.userName}','${scoreMap[recFlowKey]}','${data.sessionNumber}','${data.processFlow}');">
                                                    ${scoreMap[info]} </a>
                                        </td>
                                        <c:set value="${data.processFlow}head" var="info"/>
                                        <c:set value="${data.processFlow}headrecFlow" var="recFlowKey"/>
                                        <td>
                                            <a style="cursor: pointer;color: blue;font-weight: normal;"
                                               onclick="operDetail2('${data.userName}','${scoreMap[recFlowKey]}','${data.sessionNumber}','${data.processFlow}');">
                                                    ${scoreMap[info]} </a>
                                        </td>
                                        <c:set value="${data.processFlow}speAdmin" var="info"/>
                                        <c:set value="${data.processFlow}speAdminrecFlow" var="recFlowKey"/>
                                        <td>
                                            <a style="cursor: pointer;color: blue;font-weight: normal;"
                                               onclick="operDetail2('${data.userName}','${scoreMap[recFlowKey]}','${data.sessionNumber}','${data.processFlow}');">
                                                    ${scoreMap[info]} </a>
                                        </td>
                                        <c:set value="${data.processFlow}local" var="info"/>
                                        <c:set value="${data.processFlow}localrecFlow" var="recFlowKey"/>
                                        <td>
                                            <a style="cursor: pointer;color: blue;font-weight: normal;"
                                               onclick="operDetail2('${data.userName}','${scoreMap[recFlowKey]}','${data.sessionNumber}','${data.processFlow}');">
                                                    ${scoreMap[info]} </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty datas}">
                                    <tr>
                                        <td colspan="10">
                                            无记录！
                                        </td>
                                    </tr>
                                </c:if>
                            </table>
                        </c:if>
                        <c:if test="${param.gradeRole eq 'nurse'}">
                            <table class="grid" width="100%" style="margin-top: 10px;">
                                <colgroup>
                                    <col width="8%"/>
                                    <col width="12%"/>
                                    <col width="15%"/>
                                    <col width="15%"/>
                                    <col width="10%"/>
                                    <col width="10%"/>
                                    <col width="14%"/>
                                    <col width="8%"/>
                                    <col width="8%"/>
                                </colgroup>
                                <tr>
                                    <th>序号</th>
                                    <th>姓名</th>
                                    <th>人员类型</th>
                                    <th>培训专业</th>
                                    <th>年级</th>
                                    <th>科室</th>
                                    <th>轮转时间</th>
                                    <th >标准分</th>
                                    <th >得分</th>
                                </tr>
                                <c:forEach items="${datas}" var="data" varStatus="status">
                                    <tr>
                                        <td>${status.index + 1}</td>
                                        <td>${data.userName}</td>
                                        <td>${data.trainingTypeName}</td>
                                        <td>${data.trainingSpeName}</td>
                                        <td>${data.sessionNumber}</td>
                                        <td>${data.schDeptName}</td>
                                        <td>${data.schStartDate} <br>   ${data.schEndDate}</td>
                                        <c:if test="${data.totalScore != null && data.totalScore != ''}">
                                            <td style="font-weight:normal;" >${data.totalScore}</td>
                                        </c:if>
                                        <c:if test="${empty data.totalScore}">
                                            <td style="font-weight:normal;" >${nurseAllScore}</td>
                                        </c:if>
                                        <td>
                                            <a href="javascript:operNurseDetail('${data.userName}','${resRecTypeEnumNurseDoctorGrade.id}',
                                                '${data.recFlow}','${data.processFlow}','${data.schResultFlow}','${data.schDeptFlow}',
                                                '${result.rotationFlow}','${data.userFlow}')">
                                                    ${data.allScore}
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty datas}">
                                    <tr>
                                        <td colspan="10">
                                            无记录！
                                        </td>
                                    </tr>
                                </c:if>
                            </table>
                        </c:if>
                        <c:if test="${param.gradeRole eq 'patient'}">
                            <table class="grid" width="100%" style="margin-top: 10px;">
                                <colgroup>
                                    <col width="8%"/>
                                    <col width="12%"/>
                                    <col width="15%"/>
                                    <col width="15%"/>
                                    <col width="10%"/>
                                    <col width="10%"/>
                                    <col width="14%"/>
                                    <col width="8%"/>
                                    <col width="8%"/>
                                </colgroup>
                                <tr>
                                    <th style="min-width: 50px;">序号</th>
                                    <th style="min-width: 100px;">姓名</th>
                                    <th style="min-width: 50px;">年级</th>
                                    <th style="min-width: 100px;">培训专业</th>
                                    <th style="min-width: 100px;">人员类型</th>
                                    <th style="min-width: 100px;">轮转科室</th>
                                    <th style="min-width: 100px;">轮转时间</th>
                                    <th style="min-width:100px">评价人姓名</th>
                                    <th style="min-width:80px">分数</th>
                                </tr>
                                <c:forEach items="${datas}" var="r" varStatus="s">
                                    <tr>
                                        <td>${s.index + 1}</td>
                                        <td>${r.doctorName}</td>
                                        <td>${r.sessionNumber}</td>
                                        <td>${r.trainingSpeName}</td>
                                        <td>${r.doctorTypeName}</td>
                                        <td>${pdfn:cutString(r.schDeptName,8,true,3)}</td>
                                        <td>${r.schStartDate} <br> ${r.schEndDate}</td>
                                        <td>${r.patientName}</td>
                                        <td>
                                            <a href="javascript:grade('${r.recFlow}');">
                                                    ${r.allScore}
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty datas}">
                                    <tr>
                                        <td colspan="10">
                                            无记录！
                                        </td>
                                    </tr>
                                </c:if>
                            </table>
                        </c:if>
                        <c:if test="${param.gradeRole ne 'ManageDoctorAssess360' and param.gradeRole ne 'nurse' and param.gradeRole ne 'patient'}">
                            <table class="grid" width="100%" style="margin-top: 10px;">
                                <colgroup>
                                    <c:set value="3" var="col"/>
                                    <c:if test="${param.gradeRole eq 'doctor'  or param.gradeRole eq '360doctor'}">
                                        <c:set value="7" var="col"/>
                                        <col width="5%"/>
                                        <col width="10%"/>
                                        <col width="5%"/>
                                        <col width="8%"/>
                                        <col width="8%"/>
                                        <col width="15%"/>
                                        <col width="10%"/>
                                        <col width="19%"/>
                                        <col width="10%"/>
                                        <col width="10%"/>
                                    </c:if>
                                    <c:if test="${param.gradeRole eq 'teacher'  or param.gradeRole eq '360teacher'}">
                                        <c:set value="6" var="col"/>
                                        <col width="5%"/>
                                        <col width="15%"/>
                                        <col width="10%"/>
                                        <col width="15%"/>
                                        <col width="20%"/>
                                        <col width="15%"/>
                                        <col width="15%"/>
                                    </c:if>
                                    <c:if test="${param.gradeRole eq 'head'}">
                                        <c:set value="5" var="col"/>
                                        <col width="10%"/>
                                        <col width="30%"/>
                                        <col width="30%"/>
                                        <col width="15%"/>
                                        <col width="15%"/>
                                    </c:if>
                                </colgroup>
                                <tr>
                                    <th>序号</th>
                                    <c:if test="${param.gradeRole eq 'doctor' or param.gradeRole eq '360doctor'}">
                                        <th>姓名</th>
                                        <th>年级</th>
                                        <th>培训专业</th>
                                        <th>科室</th>
                                        <th>轮转时间</th>
                                        <th>人员类型</th>
                                        <th>评分表单</th>
                                    </c:if>
                                    <c:if test="${param.gradeRole eq 'teacher' or param.gradeRole eq '360teacher'}">
                                        <th>姓名</th>
                                        <th>年份</th>
                                        <th>科室</th>
                                        <th >评分表单</th>
                                    </c:if>
                                    <c:if test="${param.gradeRole eq 'head'}">

                                        <th>科室</th>
                                        <th>年份</th>
                                    </c:if>
                                    <th >总分</th>
                                    <th >得分</th>
                                </tr>
                                <c:forEach items="${datas}" var="data" varStatus="status">
                                    <c:if test="${param.gradeRole eq 'doctor'  or param.gradeRole eq '360doctor'}">
                                        <c:set var="key" value="${data.userFlow}"/>
                                        <c:set var="name" value="${data.userName}"/>
                                    </c:if>
                                    <c:if test="${param.gradeRole eq 'teacher'  or param.gradeRole eq '360teacher'}">
                                        <c:set var="key" value="${data.userFlow}"/>
                                        <c:set var="name" value="${data.userName}"/>
                                    </c:if>
                                    <c:if test="${param.gradeRole eq 'head'}">
                                        <c:set var="key" value="${data.deptFlow}"/>
                                        <c:set var="name" value="${data.deptName}"/>
                                    </c:if>
                                    <c:set var="avgScoreKey" value="${key}_Total"/>
                                    <tr>
                                        <td>${status.index + 1}</td>

                                        <td style="text-align: center;padding-left: 10px;">
                                                ${name}
                                        </td>
                                        <td>${data.sessionNumber}</td>
                                        <c:if test="${param.gradeRole eq 'teacher'  or param.gradeRole eq '360teacher'}">
                                            <td style="text-align: center;padding-left: 10px;">
                                                    ${data.deptName}</td>
                                            <td style="text-align: center;padding-left: 10px;">
                                                    ${data.cfgCodeName}</td>
                                        </c:if>
                                        <c:if test="${param.gradeRole eq 'doctor'  or param.gradeRole eq '360doctor'}">
                                            <td style="text-align: center;padding-left: 10px;">
                                                    ${data.trainingSpeName}</td>
                                        </c:if>
                                        <c:if test="${param.gradeRole eq 'doctor' or param.gradeRole eq '360doctor'}">
                                            <td style="text-align: center;padding-left: 10px;">${data.deptName}</td>
                                            <td style="text-align: center;padding-left: 10px;">${data.startTime}~${data.endTime}</td>
                                        </c:if>
                                        <c:if test="${param.gradeRole eq 'doctor' or param.gradeRole eq '360doctor'}">
                                            <td style="text-align: center;padding-left: 10px;">
                                                    ${data.doctorTypeName}</td>
                                            <td style="text-align: center;padding-left: 10px;">
                                                    ${data.cfgCodeName}</td>
                                            <td style="font-weight:normal;">${data.totalScore}</td>
                                        </c:if>
                                            <%--<th>--%>
                                            <%--<a style="cursor: pointer;color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}','${data.sessionNumber}');">${avgMap[avgScoreKey]}</a>--%>
                                            <%--</th>--%>
                                            <%--<th style="font-weight:normal;">${scoreMap[key]}</th>--%>
                                        <c:if test="${param.gradeRole ne 'doctor' and param.gradeRole ne '360doctor'}">
                                            <c:if test="${data.totalScore != null && data.totalScore != ''}">
                                                <td style="font-weight:normal;" >${data.totalScore}</td>
                                            </c:if>
                                            <c:if test="${empty data.totalScore}">
                                                <td style="font-weight:normal;" >${allScore}</td>
                                            </c:if>
                                        </c:if>
                                        <td>
                                            <c:if test="${param.gradeRole eq 'doctor'  or param.gradeRole eq '360doctor'}">
                                            <a style="cursor: pointer;color: blue;font-weight: normal;" onclick="operDetail('${name}','${key}','${data.sessionNumber}','${data.processFlow}');">
                                                </c:if>
                                                <c:if test="${param.gradeRole ne 'doctor' and param.gradeRole ne '360doctor'}">
                                                <a style="cursor: pointer;color: blue;font-weight: normal;" onclick="operDetailByCfgFlow('${name}','${key}','${data.sessionNumber}','${data.cfgFlow}');">
                                                    </c:if>
                                                    <c:if test="${!empty data.avg}">
                                                        ${data.avg}
                                                    </c:if>
                                                    <c:if test="${empty data.avg}">
                                                        <%--查看--%>
                                                    </c:if>
                                                </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty datas}">
                                    <tr>
                                        <td colspan="${col}">
                                            无记录！
                                        </td>
                                    </tr>
                                </c:if>
                            </table>
                        </c:if>


                    </div>
                    <div class="page" style="padding-right: 40px;">
                        <c:set var="pageView" value="${pdfn:getPageView(datas)}" scope="request"></c:set>
                        <pd:pagination-jsres toPage="gradeSearch"/>
                    </div>
                </div>
        </div>
    </div>
</div>