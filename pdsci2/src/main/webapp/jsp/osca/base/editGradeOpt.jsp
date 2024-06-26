<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var scoreLst = [],docRoomFlowLst = [];
            $(":text[name='examScore']").each(function(){
                scoreLst.push($(this).val());
            });
            $("input[name='docRoomFlow']").each(function(){
                docRoomFlowLst.push($(this).val());
            });
            var stationFlowLst = [],stationNameLst = [];
            <c:forEach items="${stationList}" var="sta">
                stationFlowLst.push("${sta.stationFlow}");
                stationNameLst.push("${sta.stationName}");
            </c:forEach>
            var json = {"stationFlowLst":stationFlowLst,"stationNameLst":stationNameLst,"scoreLst":scoreLst,"docRoomFlowLst":docRoomFlowLst};
            var url = "<s:url value='/osca/base/saveGrade?jsonData='/>"+encodeURI(encodeURI(JSON.stringify(json)));
            $("[name='resultId']").attr("disabled",false);
            jboxPost(url, $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    <c:if test="${param.role ne 'province'}">
                    window.parent.frames['mainIframe'].toPage4(${currentPage});
                    </c:if>
                    <c:if test="${param.role eq 'province'}">
                    window.parent.frames['mainIframe'].search();
                    </c:if>
                    jboxClose();
                }
            });
        }
        function contains(arr, obj) {
            var i = arr.length;
            while (i--) {
                if (arr[i] === obj) {
                    return true;
                }
            }
            return false;
        }
        function calculate(){

            var parts = [];
            var stationScores=[];
            $(".score").each(function(){
                var partFlow = $(this).attr("partFlow");
                if(!contains(parts,partFlow)){
                    parts.push(partFlow);
                }

                var val=$(this).val();
                if(val!="")
                {
                    val=parseInt(val);
                }else val=null;
                var from ={
                    "stationFlow":$(this).attr("stationFlow"),
                    "stationScore":val
                };
                stationScores.push(from);
            });
            var requestData = {"stationExamScores":JSON.stringify(stationScores),"parts":JSON.stringify(parts),"subjectFlow":"${main.subjectFlow}"};
            var url = '<s:url value="/osca/base/chekIsPass"/>';
            jboxPostNoLoad(url,requestData,function(resp){
                if(resp!="Y"&&resp!="N")
                {
                    jboxTip(resp);
                }else {
                    if (resp == "Y") {
                        $("[name='resultId'] option[value='Passed']").attr("selected", "selected");
                    } else {
                        $("[name='resultId'] option[value='UnPassed']").attr("selected", "selected");
                    }
                }
            },null,false);
            return;
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="clinicalFlow" value="${dataMap.CLINICAL_FLOW}">
            <input type="hidden" name="clinicalName" value="${dataMap.CLINICAL_NAME}">
            <input type="hidden" name="doctorFlow" value="${dataMap.DOCTOR_FLOW}">
            <input type="hidden" name="doctorName" value="${dataMap.DOCTOR_NAME}">
            <%--<input type="hidden" name="currentPage" value="${currentPage}">--%>
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td>
                        <span style="padding-left:31px;"></span>姓名：${user.userName}<br/>
                        <span style="padding-left:5px;"></span>培训基地：${dataMap.ORG_NAME}<br/>
                        <span style="padding-left:5px;"></span>考核专业：${dataMap.SPE_NAME}<br>
                        <span style="padding-left:5px;"></span>考试阶段：${dataMap.TRAINING_TYPE_NAME}
                    </td>
                </tr>
                <tr>
                    <td>
                        <c:forEach items="${stationList}" var="station" varStatus="s">
                            <div style="float:left;margin:10px 0px 2px 18px;">
                                ${station.stationName}(${station.stationScore})：
                                <c:forEach items="${fn:split(dataMap.STATION_FLOW,',')}" var="sta" varStatus="si">
                                    <c:if test="${station.stationFlow eq sta}">
                                        <input style="width:100px;" type="text" class="validate[required,custom[number],max[${station.stationScore}]] score part_${station.partFlow}"
                                               partFlow="${station.partFlow}" stationFlow="${station.stationFlow}"  stationScore="${station.stationScore}" name="examScore" value="${fn:split(dataMap.EXAM_SCORE,',')[si.index]}" onchange="calculate()">
                                        <input type="hidden" name="docRoomFlow" value="${fn:split(dataMap.DOC_ROOM_FLOW,',')[si.index]}">
                                        <c:set var="exitFlag" value="${s.index}"></c:set>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${exitFlag ne s.index}">
                                    <input style="width:100px;" type="text" class="validate[required,custom[number],max[${station.stationScore}]] score part_${station.partFlow}"
                                           partFlow="${station.partFlow}" stationFlow="${station.stationFlow}" stationScore="${station.stationScore}" name="examScore" onchange="calculate()">
                                    <input type="hidden" name="docRoomFlow" value="docRoomFlowNewFlag">
                                </c:if>
                            </div>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td style="height:60px;text-align: center;">考核结果：
                        <select name="resultId" style="width:137px;" class="select"  disabled="true">
                            <option value=""></option>
                            <option value="Passed" ${dataMap.IS_PASS eq 'Passed'?'selected':''}>通过</option>
                            <option value="UnPassed" ${dataMap.IS_PASS eq 'UnPassed'?'selected':''}>未通过</option>
                        </select>
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>