<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function checkYear(obj){
            var dates = $(':text',$(obj).closest("h1"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("起始日期不能大于结束日期！");
                obj.value = "";
            }
        }
        function updateDate(obj){
            jboxPost("<s:url value='/gzykdx/school/updateDate'/>", $("#"+obj).serialize(), function(resp){
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    location.reload();
                }
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="margin:50px 0px 0px 100px;line-height:50px;min-width:500px;">
            <form id="teaTarget" action="<s:url value="/gzykdx/school/updateDate"/>" method="post">
                <input type="hidden" name="abcd" value="1234">
                <h1>导师申请指标上报时间：
                    <input type="hidden" name="cfgCode" value="teacher_apply_start_date">
                    <input type="hidden" name="cfgCode" value="teacher_apply_end_date">
                    <input type="text" name="cfgValue" value="${sysCfgMap.teacher_apply_start_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">~
                    <input type="text" name="cfgValue" value="${sysCfgMap.teacher_apply_end_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">
                    &#12288;<a onclick="updateDate('teaTarget');" style="cursor:pointer;color:#4195c5;">保存</a>
                </h1>
            </form>
        </div>
    </div>
</div>
</body>
</html>