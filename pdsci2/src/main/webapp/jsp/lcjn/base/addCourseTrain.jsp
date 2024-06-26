<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(function () {
            <c:if test="${!empty param.courseFlow}">
                var skillRecordList =[];
                var skillFlowList =[];
                var skillNameList =[];
                var trainTimeRecordList =[];
                var userRecordList =[];
                var userFlowList =[];
                var userNameList =[];
                <c:forEach items="${skillsList}" var="skill">
                    skillRecordList.push('${skill.recordFlow}');
                    skillFlowList.push('${skill.skillFlow}');
                    skillNameList.push('${skill.skillName}');
                </c:forEach>
                <c:forEach items="${timeList}" var="time">
                    trainTimeRecordList.push('${time.recordFlow}');
                </c:forEach>
                <c:forEach items="${teacherList}" var="tea">
                    userRecordList.push('${tea.recordFlow}');
                    userFlowList.push('${tea.userFlow}');
                    userNameList.push('${tea.userName}');
                </c:forEach>
                $("#skillRecordList").val(skillRecordList);
                $("#skillFlowList").val(skillFlowList);
                $("#skillNameList").val(skillNameList);
                $("#trainTimeRecordList").val(trainTimeRecordList);
                $("#userRecordList").val(userRecordList);
                $("#userFlowList").val(userFlowList);
                $("#userNameList").val(userNameList);
            </c:if>
        });
        function checkYear(obj,val){
            var dates = $(':text',$(obj).closest("div"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                if(val == 'yytime'){
                    jboxTip("预约开始时间不能大于结束结束！");
                }else{
                    jboxTip("培训开始日期不能大于结束日期！");
                }
                obj.value = "";
            }
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("div"));
            if(dates[2].value && dates[3].value && dates[2].value > dates[3].value){
                jboxTip("培训开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function addSkill(){
            var url ="<s:url value='/lcjn/base/selectSkills?courseFlow=${param.courseFlow}'/>";
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"选择所需技能",300,400,false);
        }
        function addTrainTime(){
            $('#trainTimeTd').append($("#trainTimeTemp div").clone());
        }
        function delTrainTime(obj,recordFlow){
            $(obj).parent("div").remove();
            <c:if test="${!empty param.courseFlow}">
                var recordArray = $("#trainTimeRecordList").val().split(",");
                recordArray.splice($.inArray(recordFlow, recordArray), 1)
                $("#trainTimeRecordList").val(recordArray);
            </c:if>
        }
        function addTeacher(){
            var url ="<s:url value='/lcjn/base/selectTeachers?courseFlow=${param.courseFlow}'/>";
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,"选择任课老师",300,400,false);
        }
        function delTeacher(obj,userFlow){
            if($("#userTD td").length == 1){
                $(obj).closest("table").remove();
                $("#userTD").find("img").attr("style","cursor:pointer;");
            }else{
                $(obj).parent("td").remove();
            }
            var userFlowArray = $("#userFlowList").val().split(",");
            var index = $.inArray(userFlow, userFlowArray);
            userFlowArray.splice(index, 1);
            $("#userFlowList").val(userFlowArray);
            var userNameArray = $("#userNameList").val().split(",");
            userNameArray.splice(index, 1);
            $("#userNameList").val(userNameArray);
            if($("#userRecordList").val()!=""){
                var recordArray = $("#userRecordList").val().split(",");
                recordArray.splice(index, 1);
                $("#userRecordList").val(recordArray);
            }
        }
        function save(){
            var bealoon = true;
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            $(".trainStartDate").each(function(i){
                var trainStartTime = $(this).val()+" "+$(".startTime:eq("+i+")").val();
                if(trainStartTime.trim() !="" && trainStartTime <= $("#appointEndTime").val()){
                    bealoon = false;
                }
            });
            if(!bealoon){
                jboxTip("培训时间和预约有冲突！");
                return;
            }
            if($("#skillsTD span").length == 0){
                jboxTip("所需技能不能为空！");
                return;
            }
            if($("#userTD table").length == 0){
                jboxTip("任课老师不能为空！");
                return;
            }
            jboxPost("<s:url value='/lcjn/base/saveCourseTrain'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="courseFlow" value="${param.courseFlow}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;width:25%;">课程名称：</td>
                    <td style="width:75%;">
                        <c:if test="${param.flag ne 'view'}">
                            <input type="text" style="width:308px;" class="validate[required]" name="courseName" value="${course.courseName}"/>
                        </c:if>
                        <c:if test="${param.flag eq 'view'}">${course.courseName}</c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">所需技能：</td>
                    <td id="skillsTD">
                        <c:if test="${param.flag ne 'view'}">
                            <c:if test="${!empty param.courseFlow}">
                                <c:forEach items="${skillsList}" var="skill" varStatus="i">
                                    <span>${i.index+1}.${skill.skillName}</span>&#12288;
                                </c:forEach>
                                <input type='hidden' id="skillRecordList" name='skillRecordList'/>
                                <input type='hidden' id="skillFlowList" name='skillFlowList'/>
                                <input type='hidden' id="skillNameList" name='skillNameList'/>
                            </c:if>
                            <img style='cursor:pointer;' src='<s:url value='/jsp/inx/lcjn/images/add.png'/>' onclick='addSkill()' title='添加'>
                        </c:if>
                        <c:if test="${param.flag eq 'view'}">
                            <c:forEach items="${skillsList}" var="skill" varStatus="i">
                                <span>${i.index+1}.${skill.skillName}</span>&#12288;
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">预约开放时间：</td>
                    <td>
                        <c:if test="${param.flag ne 'view'}">
                            <div>
                                <input type="text" class="validate[required] select" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" onchange="checkYear(this,'yytime')" name="appointStartTime" value="${course.appointStartTime}"/>
                                —— <input type="text" id="appointEndTime" class="validate[required] select" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" onchange="checkYear(this,'yytime')" name="appointEndTime" value="${course.appointEndTime}"/>
                            </div>
                        </c:if>
                        <c:if test="${param.flag eq 'view'}">${course.appointStartTime}  —— ${course.appointEndTime}</c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">预约人员容量：</td>
                    <td>
                        <c:if test="${param.flag ne 'view'}">
                            <input type="text" class="validate[required,custom[positiveNum,custom[integer]" name="coursePeopleNum" value="${course.coursePeopleNum}"/>
                        </c:if>
                        <c:if test="${param.flag eq 'view'}">${course.coursePeopleNum}</c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">培训时间：</td>
                    <td id="trainTimeTd">
                        <c:if test="${param.flag ne 'view'}">
                            <img style="cursor:pointer;float:right;margin:5px 30% 0px 0px;" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addTrainTime();" title="添加" />
                            <c:if test="${empty param.courseFlow}">
                                <div><input type="text" style="width:80px;" class="validate[required] trainStartDate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)" name="trainStartDate"/>
                                    — <input type="text" style="width:80px;" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)" name="trainEndDate"/>
                                    &#12288;<input type="text" style="width:40px;" class="validate[required] startTime" readonly="readonly" onClick="WdatePicker({dateFmt:'HH:mm'})" onchange="checkTime(this)" name="startTime"/>
                                    — <input type="text" style="width:40px;" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'HH:mm'})" onchange="checkTime(this)" name="endTime"/>
                                </div>
                            </c:if>
                            <c:if test="${!empty param.courseFlow}">
                                <c:forEach items="${timeList}" var="time" varStatus="i">
                                    <div>
                                        <c:if test="${i.index gt 0}"><img style="cursor:pointer;float:right;margin:5px 30% 0px 0px;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delTrainTime(this,'${time.recordFlow}');" title="删除" /></c:if>
                                        <input type="text" style="width:80px;" class="validate[required] trainStartDate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)" name="trainStartDate" value="${time.trainStartDate}"/>
                                        — <input type="text" style="width:80px;" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)" name="trainEndDate" value="${time.trainEndDate}"/>
                                        &#12288;<input type="text" style="width:40px;" class="validate[required] startTime" readonly="readonly" onClick="WdatePicker({dateFmt:'HH:mm'})" onchange="checkTime(this)" name="startTime" value="${time.startTime}"/>
                                        — <input type="text" style="width:40px;" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'HH:mm'})" onchange="checkTime(this)" name="endTime" value="${time.endTime}"/>
                                    </div>
                                </c:forEach>
                                <input type="hidden" id="trainTimeRecordList" name="trainTimeRecordList">
                            </c:if>
                        </c:if>
                        <c:if test="${param.flag eq 'view'}">
                            <c:forEach items="${timeList}" var="time">
                                <div>${time.trainStartDate} — ${time.trainEndDate}&#12288;${time.startTime} — ${time.endTime}</div>
                            </c:forEach>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">培训地点：</td>
                    <td>
                        <c:if test="${param.flag ne 'view'}">
                            <input type="text" style="width:308px;" class="validate[required]" name="courseAddress" value="${course.courseAddress}"/>
                        </c:if>
                        <c:if test="${param.flag eq 'view'}">${course.courseAddress}</c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">任课老师：</td>
                    <td id="userTD">
                        <c:if test="${param.flag ne 'view'}">
                            <c:if test="${empty param.courseFlow}">
                                <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addTeacher();" title="添加" />
                            </c:if>
                            <c:if test="${!empty param.courseFlow}">
                                <table width=70%><tr><td style='border:0px;padding:0px;'>
                                    <c:forEach items="${teacherList}" var="tea" varStatus="i">
                                       ${tea.userName}&nbsp;<input type='text' name="courseFee" value="${tea.teachingCost}" placeholder="填写课程费用" style='width:80px;border-top:0px;border-left:0px;border-right:0px;border-bottom:1px solid gray;'>
                                        <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delTeacher(this,'${tea.userFlow}');" title="删除" />
                                        <c:if test="${i.count ne fn:length(teacherList)}"></td>
                                            <c:if test="${i.count % 2 eq 0}"></tr><tr></c:if>
                                            <td style="border:0px;padding:0px;">
                                        </c:if>
                                    </c:forEach>
                                </td></tr></table>
                                <input type='hidden' id="userRecordList" name="userRecordList" />
                                <input type='hidden' id="userFlowList" name="userFlowList" />
                                <input type="hidden" id="userNameList" name="userNameList">
                                <img style='cursor:pointer;float:right;margin:-26px 147px 0px 0px;' src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addTeacher()" title='添加'>
                            </c:if>
                        </c:if>
                        <c:if test="${param.flag eq 'view'}">
                            <table width=70%><tr><td style='border:0px;padding:0px;'>
                                <c:forEach items="${teacherList}" var="tea" varStatus="i">
                                    ${tea.userName}：${tea.teachingCost}
                                    <c:if test="${i.count ne fn:length(teacherList)}"></td>
                                        <c:if test="${i.count % 2 eq 0}"></tr><tr></c:if>
                                        <td style="border:0px;padding:0px;">
                                    </c:if>
                                </c:forEach>
                            </td></tr></table>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">课程说明：</td>
                    <td>
                        <c:if test="${param.flag ne 'view'}">
                            <textarea style="margin:8px 0px;width:70%;height:80px;" name="courseDemo" placeholder="可备注课程情况说明">${course.courseDemo}</textarea>
                        </c:if>
                        <c:if test="${param.flag eq 'view'}">
                            <div style="margin:8px 0px;">${course.courseDemo}</div>
                        </c:if>
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <c:if test="${param.flag ne 'view'}"><input type="button" class="search" onclick="save();" value="保&#12288;存"/></c:if>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>

        <div id="trainTimeTemp" style="display:none;">
            <div>
                <img style="cursor:pointer;float:right;margin:5px 30% 0px 0px;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delTrainTime(this);" title="删除" />
                <input type="text" style="width:80px;" class="validate[required] trainStartDate" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)" name="trainStartDate"/>
                — <input type="text" style="width:80px;" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)" name="trainEndDate"/>
                &#12288;<input type="text" style="width:40px;" class="validate[required] startTime" readonly="readonly" onClick="WdatePicker({dateFmt:'HH:mm'})" onchange="checkTime(this)" name="startTime"/>
                — <input type="text" style="width:40px;" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'HH:mm'})" onchange="checkTime(this)" name="endTime"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>