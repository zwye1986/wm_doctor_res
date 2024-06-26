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
    <script type="text/javascript">
            function checkTime(obj){
                var dates = $(':text',$(obj).closest("td"));
                if(dates[0].value && dates[1].value && dates[0].value >= dates[1].value){
                    jboxTip("开始时间不能大于结束时间！");
                    obj.value = "";
                }
            }
            function roomTip(){
                jboxPost("<s:url value='/zsey/base/checkRoom'/>", $("#myForm").serialize(), function (resp) {
                    if (resp > 0) {
                        $("#tipSpan").text("该时间段该房间已被占用");
                    }else{
                        $("#tipSpan").text("");
                    }
                },null,false);
            }
            function save(){
                if (!$("#myForm").validationEngine("validate")) {
                    return;
                }
                jboxConfirm("确认审核通过吗？",function () {
                    $("input[name='trainRoomName']").val($("select[name='trainRoomId'] option:selected").text());
                    jboxPost("<s:url value='/zsey/base/saveAppointRoom'/>", $("#myForm").serialize(), function (resp) {
                        if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                            window.parent.frames['mainIframe'].location.reload();
                            jboxClose();
                        }
                    });
                });
            }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="appointFlow" value="${appoint.appointFlow}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;">培训项目：</td>
                    <td>${appoint.projectName}</td>
                </tr>
                <tr>
                    <td style="text-align:right;">培训日期：</td>
                    <td>
                        <input type="text" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="trainDate" value="${appoint.trainDate}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">培训时间：</td>
                    <td>
                        <input type="text" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'HH:mm'})" name="beginTime" value="${appoint.beginTime}" onchange="checkTime(this)"/>
                        -- <input type="text" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'HH:mm'})" name="endTime" value="${appoint.endTime}" onchange="checkTime(this)"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">安排教室：</td>
                    <td>
                        <select name="trainRoomId" style="width:137px;" class="validate[required] select" onchange="roomTip(this.value)">
                            <option value="">请选择</option>
                            <c:forEach var="dict" items="${dictTypeEnumZseyTrainRoomList}">
                                <option value="${dict.dictId}" ${appoint.trainRoomId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="trainRoomName">
                        <span id="tipSpan" style="color:red;"></span>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">实际培训人数：</td>
                    <td>
                        <input type="text" class="validate[required,custom[integer]]" name="traineesNumber" value="${appoint.traineesNumber}"/>
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="确&#12288;认"/>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>