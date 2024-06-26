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
    <style>
        .xlselect{margin-right: 0px;}
        .xltext{margin-right: 0px;}
    </style>
    <script type="text/javascript">
        $(function(){
        });
        function checkYear(obj){
            var dates = $(':text',$(obj).closest("td"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            jboxPost("<s:url value='/study/hospital/saveSubject'/>", $("#myForm").serialize(), function (resp) {
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
            <input type="hidden" name="subjectFlow" value="${subject.subjectFlow}">
            <div id="dataTable">
                <table class="basic" style="width: 100%;margin: 10px 0px;">
                    <tr>
                        <td style="text-align:right;">课程名称：</td>
                        <td>
                                <input type="text" class="validate[required] xltext" name="subjectName" value="${subject.subjectName}"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right;">课程年份：</td>
                        <td>
                            <input type="text" class="validate[required] select xltext"  onClick="WdatePicker({dateFmt:'yyyy'})" name="subjectYear" value="${subject.subjectYear}"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right;">课程类型：</td>
                        <td>
                            <select name="subjectType" class="xlselect" >
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumCourseTypeList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${dict.dictId eq subject.subjectType}">selected</c:if>> ${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right;">预约开放时间：</td>
                        <td>
                            <input type="text" class="validate[required] xlselect" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" onchange="checkYear(this)" name="subjectStartTime" value="${subject.subjectStartTime}"/>
                                — <input type="text" class="validate[required] xlselect" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" onchange="checkYear(this)" name="subjectEndTime" value="${subject.subjectEndTime}"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right;">预约人员容量：</td>
                        <td>
                            <input type="text" class="validate[required,custom[integer],min[1]] xltext" name="reservationsNum" value="${subject.reservationsNum}"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right;">课程说明：</td>
                        <td>
                            <textarea name="subjectExplain" style="width: 98%;height: 100px;">${subject.subjectExplain}</textarea>
                        </td>
                    </tr>
                </table>
            </div>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存" id="saveBtn"/>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();" id="closeBtn"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>