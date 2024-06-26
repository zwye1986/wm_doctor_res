<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <c:if test="${param.flag ne 'view'}">
        <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
        <script type="text/javascript">
            $(function(){
                //加载任课老师检索
                loadTeacherList();
                //加载设备耗材信息
                <c:if test="${!empty appoint.appointFlow}">
                var recordFlowList =[];
                var materialFlowList =[];
                var materialNameList =[];
                <c:forEach items="${materialList}" var="mat">
                recordFlowList.push('${mat.recordFlow}');
                materialFlowList.push('${mat.materialFlow}');
                materialNameList.push('${mat.materialName}');
                </c:forEach>
                $("#recordFlowList").val(recordFlowList);
                $("#materialFlowList").val(materialFlowList);
                $("#materialNameList").val(materialNameList);
                </c:if>
            });
            function loadTeacherList(){
                var teacherArray = new Array();
                var url = "<s:url value='/zsey/dept/searchTeacher'/>";
                jboxGetAsync(url,null,function(data){
                    if(data){
                        for (var i = 0; i < data.length; i++) {
                            var teacherFlow=data[i].userFlow;
                            if(data[i].userFlow==null){
                                teacherFlow="";
                            }
                            var userCode=data[i].userCode;
                            if(data[i].userCode==null){
                                userCode="";
                            }
                            var teacherName=data[i].userName;
                            if(data[i].userName==null){
                                teacherName="";
                            }
                            teacherArray[i]=new Array(teacherFlow,teacherName,userCode);
                        }
                        jboxStartLoading();
                        $("#searchParam_Teacher").suggest(teacherArray,{
                            attachObject:'#suggest_Teacher',
                            dataContainer:'#result_Teacher',
                            triggerFunc:function(teacherFlow){},
                            enterFunc:function(teacherFlow){}
                        });
                        jboxEndLoading();
                    }

                },null,false);
            }
            function adjustResults() {
                $("#suggest_Teacher").css("left",$("#searchParam_Teacher").offset().left);
                $("#suggest_Teacher").css("top",$("#searchParam_Teacher").offset().top+$("#searchParam_Teacher").outerHeight());
            }
            function checkTime(obj){
                var dates = $(':text',$(obj).closest("td"));
                if(dates[0].value && dates[1].value && dates[0].value >= dates[1].value){
                    jboxTip("开始时间不能大于结束时间！");
                    obj.value = "";
                }
            }
            function showDate(value){
                $("#date").text("星期"+"日一二三四五六".charAt(new Date(value).getDay()))
            }
            function save(){
                if (!$("#myForm").validationEngine("validate")) {
                    return;
                }
                jboxPost("<s:url value='/zsey/dept/saveAppoint'/>", $("#myForm").serialize(), function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    }
                });
            }
            function addMaterial(){
                var url ="<s:url value='/zsey/dept/selectMaterial?appointFlow=${appoint.appointFlow}'/>";
                var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
                jboxMessager(iframe,"选择设备耗材",600,400,false);
            }
            function delMaterial(obj,materialFlow){
                if($("#materialTD td").length == 1){
                    $(obj).closest("table").remove();
                    $("#tip").html("请选择所需培训设备及耗材信息");
                    $("#materialTD").find("img").attr("style","cursor:pointer;");
                }else{
                    $(obj).closest("td").remove();
                    if($("#materialTD td").length == 1){
                        $("#materialTD div").css("text-align","left");
                    }
                }
                var materialFlowArray = $("#materialFlowList").val().split(",");
                var index = $.inArray(materialFlow, materialFlowArray);
                materialFlowArray.splice(index, 1);
                $("#materialFlowList").val(materialFlowArray);
                var materialNameArray = $("#materialNameList").val().split(",");
                materialNameArray.splice(index, 1);
                $("#materialNameList").val(materialNameArray);
                if($("#recordFlowList").val()!=""){
                    var recordArray = $("#recordFlowList").val().split(",");
                    recordArray.splice(index, 1);
                    $("#recordFlowList").val(recordArray);
                }
            }
        </script>
    </c:if>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="appointFlow" value="${appoint.appointFlow}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;width:20%;">培训日期：</td>
                    <td style="width:80%;">
                        <c:if test="${param.flag eq 'view'}">${appoint.trainDate}</c:if>
                        <c:if test="${param.flag ne 'view'}">
                            <input type="text" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="trainDate" value="${appoint.trainDate}" onchange="showDate(this.value)"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">星期：</td>
                    <c:set var="date" value="${pdfn:getWeekFromDate(appoint.trainDate,'3')}"/>
                    <td id="date">${!empty appoint.trainDate?date:''}</td>
                </tr>
                <tr>
                    <td style="text-align:right;">培训时间：</td>
                    <td>
                        <c:if test="${param.flag eq 'view'}">
                            ${appoint.beginTime} -- ${appoint.endTime}
                        </c:if>
                        <c:if test="${param.flag ne 'view'}">
                            <input type="text" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'HH:mm'})" name="beginTime" value="${appoint.beginTime}" onchange="checkTime(this)"/>
                            -- <input type="text" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'HH:mm'})" name="endTime" value="${appoint.endTime}" onchange="checkTime(this)"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">培训对象：</td>
                    <td>
                        <c:if test="${param.flag eq 'view'}">
                            ${appoint.traineesName}
                        </c:if>
                        <c:if test="${param.flag ne 'view'}">
                            <select style="width: 137px;" name="traineesName" class="validate[required] select">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumTraineesList}" var="major">
                                    <option value="${major.dictName}" ${appoint.traineesName eq major.dictName?'selected':''}>
                                        ${major.dictName}
                                    </option>
                                </c:forEach>
                            </select>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">专业：</td>
                    <td>
                        <c:if test="${param.flag eq 'view'}">
                            ${appoint.traineesSpeName}
                        </c:if>
                        <c:if test="${param.flag ne 'view'}">
                            <input type="text" class="validate[required]" name="traineesSpeName" value="${appoint.traineesSpeName}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;white-space:nowrap;">学员人数/分组数：</td>
                    <td>
                        <c:if test="${param.flag eq 'view'}">
                            ${appoint.traineesNumber}
                        </c:if>
                        <c:if test="${param.flag ne 'view'}">
                            <input type="text" class="validate[required,custom[integer]]" name="traineesNumber" value="${appoint.traineesNumber}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">培训项目：</td>
                    <td>
                        <c:if test="${param.flag eq 'view'}">
                            ${appoint.projectName}
                        </c:if>
                        <c:if test="${param.flag ne 'view'}">
                            <input type="text" class="validate[required]" name="projectName" value="${appoint.projectName}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">培训设备：</td>
                    <td id="materialTD">
                        <c:if test="${param.flag eq 'view'}">
                            <table width=80%><tr><td style='border:0px;padding:0px;'>
                                <c:forEach items="${materialList}" var="mat" varStatus="i">
                                    ${mat.materialName}：${mat.materialNumber}
                                <c:if test="${i.count ne fn:length(materialList)}"></td>
                                <c:if test="${i.count % 2 eq 0}"></tr><tr></c:if>
                                <td style="border:0px;padding:0px;">
                                    </c:if>
                                    </c:forEach>
                                </td></tr></table>
                        </c:if>
                        <c:if test="${param.flag ne 'view'}">
                            <c:if test="${empty appoint.appointFlow}">
                                <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addMaterial();" title="添加" />
                                <span id="tip" style="color:grey;padding-top:2px;">请选择所需培训设备及耗材信息</span>
                            </c:if>
                            <c:if test="${!empty appoint.appointFlow}">
                                <c:if test="${!empty materialList}">
                                <table width=85%><tr><td style='border:0px;padding:0px;width:50%;'><div style="white-space:nowrap;width:96%;text-align:${fn:length(materialList) eq 1?'left':'right'}">
                                    <c:forEach items="${materialList}" var="mat" varStatus="i">
                                        ${mat.materialName}&nbsp;<input type='text' name="materialNum" value="${mat.materialNumber}" placeholder="填写设备数量" style='width:80px;border-top:0px;border-left:0px;border-right:0px;border-bottom:1px solid gray;'>
                                    <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delMaterial(this,'${mat.materialFlow}');" title="删除" />
                                    <c:if test="${i.count ne fn:length(materialList)}"></div></td>
                                    <c:if test="${i.count % 2 eq 0}"></tr><tr></c:if>
                                    <td style="border:0px;padding:0px;width:50%;"><div style='white-space:nowrap;width:96%;text-align:right'>
                                        </c:if>
                                        </c:forEach>
                                    </div></td></tr></table>
                                </c:if>
                                <input type='hidden' id="recordFlowList" name="recordFlowList" />
                                <input type='hidden' id="materialFlowList" name="materialFlowList" />
                                <input type="hidden" id="materialNameList" name="materialNameList">
                                <img style="cursor:pointer;${!empty materialList?'float:right;margin:-26px 20px 0px 0px;':''}" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addMaterial()" title='添加'>
                                <span id="tip" style="color:grey;padding-top:2px;"></span>
                            </c:if>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">任课老师：</td>
                    <td>
                        <c:if test="${param.flag eq 'view'}">
                            ${appoint.teacherName}
                        </c:if>
                        <c:if test="${param.flag ne 'view'}">
                            <input id="searchParam_Teacher" name="teacherName"  value="${appoint.teacherName}" placeholder="模糊检索" class="inputText" style="width:137px;text-align:left;" onkeydown="adjustResults();" onfocus="adjustResults();"/>
                            <div id="suggest_Teacher" class="ac_results" style="margin:0;position:fixed;z-index:100;width:180px;height:180px;"></div>&#12288;
                            <input type="hidden" id="result_Teacher" name="teacherFlow" value="${appoint.teacherFlow}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;white-space:nowrap;">任课老师联系方式：</td>
                    <td>
                        <c:if test="${param.flag eq 'view'}">
                            ${appoint.teacherPhone}
                        </c:if>
                        <c:if test="${param.flag ne 'view'}">
                            <input type="text" class="validate[required]" name="teacherPhone" value="${appoint.teacherPhone}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">申请人：</td>
                    <td>
                        <c:if test="${param.flag eq 'view'}">
                            ${appoint.applicantName}
                        </c:if>
                        <c:if test="${param.flag ne 'view'}">
                            <input type="text" class="validate[required]" name="applicantName" value="${appoint.applicantName}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">申请人联系方式：</td>
                    <td>
                        <c:if test="${param.flag eq 'view'}">
                            ${appoint.applicantPhone}
                        </c:if>
                        <c:if test="${param.flag ne 'view'}">
                            <input type="text" class="validate[required]" name="applicantPhone" value="${appoint.applicantPhone}"/>
                        </c:if>
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <c:if test="${param.flag ne 'view'}">
                    <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                </c:if>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>