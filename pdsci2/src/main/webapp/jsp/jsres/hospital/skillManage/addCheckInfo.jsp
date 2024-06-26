<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">

        $(function(){
            <c:if test="${empty param.clinicalFlow}"><%-- 新增 --%>
            <c:if test="${param.isLocal eq 'N'}">
            $('#actionTypeId').find("option").eq(0).attr("selected",true);
            $('#actionTypeId').find("option").eq(1).hide();
            </c:if>
            jboxPost("<s:url value='/jsres/skillTimeConfig/queryInitSpe'/>", $("#myForm").serialize(), function (resp) {
                $("#speId").empty();
                if (null != resp && resp.length > 0) {
                    for(var i= 0;i<resp.length;i++){
                        var typeName = resp[i].typeName;
                        if(!typeName) {typeName = '';}
                        else{ typeName = "("+typeName+")"}
                        $("#speId").append("<option value='"+resp[i].speId+"' speName='"+resp[i].speName+"'>"+resp[i].speName+typeName+"</option>");
                    }
                    if(resp[0].speId != "" || resp[0].speId != null){
                        jboxPost("<s:url value='/jsres/skillTimeConfig/querySpeRelation'/>", $("#myForm").serialize(), function (resp) {
                            $("#subjectFlow").empty();
                            if (null != resp && resp.length > 0) {
                                for(var i= 0;i<resp.length;i++){
                                    $("#subjectFlow").append("<option value='"+resp[i].subjectFlow+"'>"+resp[i].subjectName+"</option>");
                                }
                            }
                        },null,false)
                    }
                }
            },null,false);
            //生成二维码
            $("#code").empty();
            var url = "${signUrl}";
            $("#qrcode").qrcode({
                render: "canvas",
                width: 300,
                height:300,
                correctLevel:0,//纠错等级
                text: url
            });
            </c:if>
            <c:if test="${not empty param.clinicalFlow}"><%-- 编辑 --%>
            $("#speId").append("<option value='${osa.speId}'speName='${osa.speName}'>${osa.speName}</option>");
            if($('.isLocal:selected') == "N"){
                $('#actionTypeId').find("option").eq(0).attr("selected",true);
                $('#actionTypeId').find("option").eq(1).hide();
            }else{
                $('#actionTypeId').find("option").eq(1).show();
            }
            <c:if test="${flag eq 'edit'}">
            $("#dataTable input").attr("disabled","disabled");
            $("#dataTable select").attr("disabled","disabled");
            $("#saveBtn").removeAttr("disabled");
            $("#closeBtn").removeAttr("disabled");
            $("input[name='examStartTime']").removeAttr("disabled");
            $("input[name='examEndTime']").removeAttr("disabled");
            $("input[name='testNumber']").removeAttr("disabled");
            $("input[name='examAddress']").removeAttr("disabled");
            </c:if>
            </c:if>
            //自动选择考核类型
            $("[name='isLocal'] option[value=${param.isLocal}]").attr("selected","selected");
        });

        function checkYear(obj){
            var dates = $(':text',$(obj).closest("td"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function checkYear1(obj){
            var dates = $(':text',$(obj).closest("div"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
            var appointEndTime = $("#appointEndTime").val();
            var examStartTime = $("#examStartTime").val();
            if(examStartTime < appointEndTime){
                jboxTip("考核时间不能小于预约结束时间！");
                $("#examStartTime").val("");
            }
        }
        function selectSpe(){
            if($('#speId').val() == "" || $('#speId').val() == null){
                return;
            }
            jboxPost("<s:url value='/jsres/skillTimeConfig/querySpeRelation'/>", $("#myForm").serialize(), function (resp) {
                $("#subjectFlow").empty();
                if (null != resp && resp.length > 0) {
                    for(var i= 0;i<resp.length;i++){
                        $("#subjectFlow").append("<option value='"+resp[i].subjectFlow+"'>"+resp[i].subjectName+"</option>");
                    }
                }
            },null,false);
        }

        function initSpe(value){
            if(value == "N"){
                $('#actionTypeId').find("option").eq(0).attr("selected",true);
                $('#actionTypeId').find("option").eq(1).hide();
                jboxPost("<s:url value='/jsres/skillTimeConfig/queryInitSpe'/>", $("#myForm").serialize(), function (resp) {
                    $("#speId").empty();
                    if (null != resp && resp.length > 0) {
                        for(var i= 0;i<resp.length;i++){
                            var typeName = resp[i].typeName;
                            if(!typeName) {typeName = '';}
                            else{ typeName = "("+typeName+")"}
                            $("#speId").append("<option value='"+resp[i].speId+"' speName='"+resp[i].speName+"'>"+resp[i].speName+typeName+"</option>");
                        }
                    }
                },null,false);
            }else if(value == "Y"){
                $('#actionTypeId').find("option").eq(1).show();
                $("#speId").empty();
                var html = "<option/><c:forEach items='${dictTypeEnumCheckSpeList}' var='dict'><option value='${dict.dictId}' ${osa.speId eq dict.dictId?'selected':''}>${dict.dictName}</option></c:forEach>";
                $("#speId").append(html);
            }
        }

        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            $("[name='isLocal']").attr("disabled",false);
            var speText = $("#speId option:selected").attr("speName");
            $("#speName").val(speText);
            var actionTypeText = $("#actionTypeId option:selected").text();
            $("#actionTypeName").val(actionTypeText);
            var subjectText = $("#subjectFlow option:selected").text();
            $("#subjectName").val(subjectText);
            var count = 0;
            var trs1 = $('#testTimeTd').find(".timeDiv");
            var datas1 = [];
            var timeFlag=false;
            var timeFlag2=false;
            var periods = [];
            trs1.each(function(){
                var startTime = $(this).find("input[name='examStartTime']").val();
                var endTime = $(this).find("input[name='examEndTime']").val();
                var step = $(this).find("#step").val();
                var time = {"startTime":startTime,"endTime":endTime,"step":step};
                periods.push(time);
            });
            $.each(trs1, function (i, n) {
                var examStartTime = $(n).find("input[name='examStartTime']").val();
                var examEndTime = $(n).find("input[name='examEndTime']").val();
                var step =  $(n).find("#step").val();
                var testNumber =  $(n).find("input[name='testNumber']").val();
                var recrodFlow =  $(n).find("input[name='recrodFlow']").val();
                for(var i=0; i<periods.length; i++){
                    var startT = periods[i]["startTime"];
                    var endT = periods[i]["endTime"];
                    var stepS = periods[i]["step"];
                    if(step!=stepS&&((examStartTime<=startT&&examEndTime>=startT)||(examStartTime>=startT&&examEndTime<=endT))){
                        timeFlag2 = true;
                    }
                }
                if(trs1.length==1){
                    testNumber='${osa.appointNum}';
                }
                var data = {
                    "recrodFlow": recrodFlow,
                    "examStartTime": examStartTime,
                    "examEndTime": examEndTime,
                    "testNumber": testNumber
                };
                datas1.push(data);
                count=count+Number(testNumber);
                if('${osa.appointEndTime}'>examStartTime){
                    timeFlag=true;
                }
            });
            if(${osa.isReleased eq 'Y'}){
                if(count<'${osa.appointNum}'){
                    jboxTip("考核人员容量总数低于预约人员容量，请重新输入!");
                    return;
                }
            }
            if(timeFlag){
                jboxTip("考核时间小于预约时间，请重新选择!");
                return;
            }
            if(timeFlag2){
                jboxTip("考核时间冲突，请重新选择!");
                return;
            }
            var t = {'timeList': datas1};
            jboxPost("<s:url value='/jsres/skillTimeConfig/saveCheckInfo'/>", $("#myForm").serialize()+"&jsondata="+JSON.stringify(t), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    // window.parent.frames['mainIframe'].location.reload();
                    window.parent.toPage(1);
                    jboxClose();
                }
            });
        }

        function addTestTime(){
            $('#testTimeTd').append($("#testTimeTemp div").clone());
            parseInput();
        }
        function parseInput() {
            var trs1 = $('#testTimeTd').find(".timeDiv");
            if(trs1.length==1){
                $('#testTimeTd').find("[name='timeDiv']").find(".num").hide();
                $('#testTimeTd').find("[name='timeDiv']").find(".numtext").hide();
            }else{
                $('#testTimeTd').find("[name='timeDiv']").find(".num").show();
                $('#testTimeTd').find("[name='timeDiv']").find(".numtext").show();
            }
        }
        function delTestTime(obj){
            $(obj).parent("div").remove();
            parseInput();
        }

        /*function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }

            var speText = $("#speId option:selected").text();
            $("#speName").val(speText);
            jboxPost("<s:url value='/jsres/skillTimeConfig/saveCheckInfo'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.location.reload();
                    jboxClose();
                }
            });
        }*/

    </script>
</head>
<body>
<div class="mainright" style="padding: 0px 20px;max-height: 480px;overflow: auto">
    <form id="myForm">
        <input type="hidden" name="clinicalFlow" value="${osa.clinicalFlow}">
        <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 30px;">
            <tr>
                <th style="text-align:right;">考试编号：</th>
                <td>
                    <input type="text" class="input validate[required]" name="testId" value="${skillConfig.testId}" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <th style="text-align:right;">考核名称：</th>
                <td>
                    <c:if test="${param.flag ne 'view'}">
                        <input type="text" class="validate[required] input" name="clinicalName" value="${osa.clinicalName}"/>
                    </c:if>
                    <c:if test="${param.flag eq 'view'}">${osa.clinicalName}</c:if>
                </td>
            </tr>
            <tr>
                <th style="text-align:right;">考核年份：</th>
                <td>
                    <c:if test="${param.flag ne 'view'}">
                        <input type="text" style="width: 159px" class="validate[required] select input" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" name="clinicalYear" value="${osa.clinicalYear}"/>
                    </c:if>
                    <c:if test="${param.flag eq 'view'}">${osa.clinicalYear}</c:if>
                </td>
            </tr>
            <tr>
                <th style="text-align:right;">考核类型：</th>
                <td>
                    <c:if test="${param.flag ne 'view'}">
                        <select name="isLocal" style="width: 159px" class="validate[required] select isLocal" onchange="initSpe(this.value)" disabled="disabled">
                            <%--<option value=""></option>--%>
                            <%--<option value="Y" ${osa.isLocal eq 'Y'?'selected':''}>院内考核</option>--%>
                            <option value="N" ${osa.isLocal eq 'N'?'selected':''}>结业考核</option>
                        </select>
                    </c:if>
                    <c:if test="${param.flag eq 'view'}">
                        <c:if test="${osa.isLocal eq 'Y'}">院内考核</c:if>
                        <c:if test="${osa.isLocal eq 'N'}">结业考核</c:if>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th style="text-align:right;">培训专业：</th>
                <td>
                    <c:if test="${param.flag ne 'view'}">
                        <select id="speId" style="width: 159px" name="speId" class="validate[required] select" onchange="selectSpe()">
                        </select>
                        <input type="hidden" id="speName" name="speName" value="${osa.speName}">
                    </c:if>
                    <c:if test="${param.flag eq 'view'}">${osa.speName}</c:if>
                </td>
            </tr>
            <tr>
                <th style="text-align:right;">考核方案类型：</th>
                <td>
                    <c:if test="${param.flag ne 'view'}">
                        <select id="actionTypeId" style="width: 159px" name="actionTypeId" class="validate[required] select" onchange="selectSpe()">
                            <c:forEach var="assess" items="${assessmentProEnumList}">
                                <option value="${assess.id}" <c:if test="${osa.actionTypeId eq assess.id}">selected="selected"</c:if>>${assess.name}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="actionTypeName" name="actionTypeName" value="${osa.actionTypeName}">
                    </c:if>
                    <c:if test="${param.flag eq 'view'}">${osa.actionTypeName}</c:if>
                </td>
            </tr>
            <tr>
                <th style="text-align:right;">考核方案名称：</th>
                <td>
                    <c:if test="${param.flag ne 'view'}">
                        <select id="subjectFlow" style="width: 159px" name="subjectFlow" class="validate[required] select">
                            <c:if test="${not empty param.clinicalFlow}"><option value="${osa.subjectFlow}">${osa.subjectName}</option></c:if>
                        </select>
                        <input type="hidden" id="subjectName" name="subjectName" value="${osa.subjectName}">
                    </c:if>
                    <c:if test="${param.flag eq 'view'}">${osa.subjectName}</c:if>
                </td>
            </tr>
            <tr>
                <th style="text-align:right;">预约开放时间：</th>
                <td>
                    <c:if test="${param.flag ne 'view'}">
                        <input type="text" class="validate[required] input" readonly="readonly"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '${skillConfig.skillStartTime}',maxDate: '${skillConfig.skillEndTime}'})"
                               onchange="checkYear(this)" name="appointStartTime" value="${osa.appointStartTime}"/>
                        — <input type="text" class="validate[required] input" readonly="readonly"
                                 onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '${skillConfig.skillStartTime}',maxDate: '${skillConfig.skillEndTime}'})"
                                 onchange="checkYear(this)" name="appointEndTime" value="${osa.appointEndTime}"/>
                    </c:if>
                    <c:if test="${param.flag eq 'view'}">${osa.appointStartTime} — ${osa.appointEndTime}</c:if>
                </td>
            </tr>
            <tr>
                <th style="text-align:right;">预约人员容量：</th>
                <td>
                    <c:if test="${param.flag ne 'view'}">
                        <input type="text" class="validate[required,custom[number]] input" name="appointNum" value="${osa.appointNum}"/>
                    </c:if>
                    <c:if test="${param.flag eq 'view'}">${osa.appointNum}</c:if>
                </td>
            </tr>
            <c:if test="${osa.isReleased eq 'Y'}">
            <tr><th style="text-align:right;">审核通过人数：</th><td>${passedNum}</td></tr>
            <tr>
                <th style="text-align:right;">考核时间：</th>
                <td id="testTimeTd">
                    <c:if test="${param.flag ne 'view'}">
                    <img style="cursor:pointer;float:right;margin:8px 2% 0px 0px;" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addTestTime();" title="添加" />
                    <c:if test="${empty times}">
                        <div class="timeDiv" name="timeDiv">
                            <input type="text" class="validate[required] input" style="width:120px;" readonly="readonly"
                                   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '${skillConfig.skillStartTime}',maxDate: '${skillConfig.skillEndTime}'})"
                                   onblur="checkYear1(this)" name="examStartTime" value=""/>
                            — <input type="text" class="validate[required] input" style="width:120px;" readonly="readonly"
                                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '${skillConfig.skillStartTime}',maxDate: '${skillConfig.skillEndTime}'})"
                                     onblur="checkYear1(this)" name="examEndTime" value=""/>
                            <label class="numtext" style="display: none;">&#12288;考核人员容量：</label><input  type="text" style="width:40px;display: none;" class="input validate[required] num"  name="testNumber" value="">
                        </div>
                    </c:if>
                    <c:if test="${not empty times}">
                    <c:forEach items="${times}" var="time" varStatus="s">
                    <c:if test="${s.index eq 0}">
                    <div class="timeDiv" name="timeDiv">
                        </c:if>
                        <c:if test="${s.index ne 0}">
                        <div class="timeDiv">
                            </c:if>
                            <input type="hidden" id="step" value="${s.index}">
                            <input type="text" class="validate[required] input" style="width:120px;" readonly="readonly" name="examStartTime" value="${time.examStartTime}"/>
                            — <input type="text" class="validate[required] input" style="width:120px;" readonly="readonly" name="examEndTime" value="${time.examEndTime}"/>
                            <label>&#12288;考核人员容量：</label><input type="text" style="width:40px;" class="input validate[required]" onblur="" name="testNumber" value="${time.testNumber}" readonly="readonly"/>
                            <input type="hidden" id="recrodFlow" name="recrodFlow" value="${time.recrodFlow}">
                        </div>
                        </c:forEach>
                        </c:if>
                        </c:if>
                </td>
            </tr>
            <tr>
                <th style="text-align:right;">考点地址：</th>
                <td>
                    <c:if test="${param.flag ne 'view'}">
                        <input type="text" class="input" name="examAddress" value="${osa.examAddress}" <c:if test="${not empty osa.examAddress}">readonly="readonly"</c:if> style="width:266px;"/>
                    </c:if>
                    <c:if test="${param.flag eq 'view'}">${osa.examAddress}</c:if>
                </td>
            </tr>
            <tr>
                <th style="text-align:right;">备注说明：</th>
                <td>
                    <c:if test="${param.flag ne 'view'}">
                        <textarea name="remarks" style="width: 98%">${osa.remarks}</textarea>
                    </c:if>
                    <c:if test="${param.flag eq 'view'}">${osa.remarks}</c:if>
                </td>
            </tr>
            </c:if>
            <%--<tr>--%>
                <%--<th style="text-align:right;">考核名称：</th>--%>
                <%--<td>--%>
                    <%--<input type="text" class="input validate[required]" name="clinicalName" value="${osa.clinicalName}"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th style="text-align:right;">考核专业：</th>--%>
                <%--<td>--%>
                    <%--<c:if test="${param.flag ne 'view'}">--%>
                        <%--<select id="speId" name="speId" class="select validate[required]" style="width: 159px;" onchange="selectSpe()">--%>
                            <%--<option value=""></option>--%>
                            <%--<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">--%>
                                <%--<option value="${spe.dictId}"--%>
                                        <%--<c:if test="${osa.speId eq spe.dictId}">selected</c:if>--%>
                                <%-->${spe.dictName}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                        <%--<input type="hidden" id="speName" name="speName" value="${osa.speName}">--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${param.flag eq 'view'}">${osa.speName}</c:if>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th style="text-align:right;">预约考核时间：</th>--%>
                <%--<td>--%>
                    <%--<c:if test="${param.flag ne 'view'}">--%>
                        <%--<input type="text" class="input validate[required]" readonly="readonly"--%>
                               <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '${skillConfig.skillStartTime}',maxDate: '${skillConfig.skillEndTime}'})"--%>
                               <%--onchange="checkYear(this)" name="appointStartTime" id="appointStartTime" value="${osa.appointStartTime}"/>--%>
                        <%--— <input type="text" class="input validate[required]" readonly="readonly"--%>
                                 <%--onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '${skillConfig.skillStartTime}',maxDate: '${skillConfig.skillEndTime}'})"--%>
                                 <%--onchange="checkYear(this)" name="appointEndTime" id="appointEndTime" value="${osa.appointEndTime}"/>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${param.flag eq 'view'}">${osa.appointStartTime} — ${osa.appointEndTime}</c:if>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th style="text-align:right;">预约人数：</th>--%>
                <%--<td>--%>
                    <%--<c:if test="${param.flag ne 'view'}">--%>
                        <%--<input type="text" class="validate[required,custom[number]] input" name="appointNum" value="${osa.appointNum}"/>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${param.flag eq 'view'}">${osa.appointNum}</c:if>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th style="text-align:right;">考核时间：</th>--%>
                <%--<td>--%>
                    <%--<c:if test="${param.flag ne 'view'}">--%>
                        <%--<input type="text" class="input validate[required]" readonly="readonly"--%>
                               <%--onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate: '${skillConfig.skillStartTime}',maxDate: '${skillConfig.skillEndTime}'})"--%>
                               <%--onchange="checkYear1(this)" name="examStartTime" id="examStartTime" value="${times.examStartTime}"/>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${param.flag eq 'view'}">${osa.examStartTime}</c:if>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th style="text-align:right;">考核地点：</th>--%>
                <%--<td>--%>
                    <%--<c:if test="${param.flag ne 'view'}">--%>
                        <%--<input type="text" class="input validate[required]" name="examAddress" value="${osa.examAddress}"/>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${param.flag eq 'view'}">${osa.examAddress}</c:if>--%>
                <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<th style="text-align:right;">联系电话：</th>--%>
                <%--<td>--%>
                    <%--<c:if test="${param.flag ne 'view'}">--%>
                        <%--<input type="text" class="input validate[required]" name="skillOrgPhone" value="${osa.skillOrgPhone}"/>--%>
                    <%--</c:if>--%>
                    <%--<c:if test="${param.flag eq 'view'}">${osa.skillOrgPhone}</c:if>--%>
                <%--</td>--%>
            <%--</tr>--%>
        </table>
        <div style="text-align: center;margin-top:20px;">
            <c:if test="${param.flag ne 'view'}">
                <input type="button" class="btn_green" onclick="save();" value="保&#12288;存" id="saveBtn"/>&#12288;
            </c:if>
            <input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();" id="closeBtn"/>
        </div>
    </form>
    <div id="testTimeTemp" style="display:none;">
        <div class="timeDiv">
            <img style="cursor:pointer;float:right;margin:8px 2% 0px 0px;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delTestTime(this);" title="删除" />
            <input type="text" class="validate[required] input" style="width:120px;" readonly="readonly"
                   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '${skillConfig.skillStartTime}',maxDate: '${skillConfig.skillEndTime}'})"
                   onblur="checkYear1(this)" name="examStartTime"/>
            — <input type="text" class="validate[required] input" style="width:120px;" readonly="readonly"
                     onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate: '${skillConfig.skillStartTime}',maxDate: '${skillConfig.skillEndTime}'})"
                     onblur="checkYear1(this)" name="examEndTime"/>
            <label>&#12288;考核人员容量：</label><input type="text" style="width:40px;" class="validate[required] input" onblur="" name="testNumber"/>
        </div>
    </div>
</div>
</body>
</html>