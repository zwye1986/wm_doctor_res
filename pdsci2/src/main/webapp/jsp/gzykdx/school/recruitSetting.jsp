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
                <h1>二级机构导师指标上报时间：
                    <input type="hidden" name="cfgCode" value="teacher_target_start_date">
                    <input type="hidden" name="cfgCode" value="teacher_target_end_date">
                    <input type="text" name="cfgValue" value="${sysCfgMap.teacher_target_start_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">~
                    <input type="text" name="cfgValue" value="${sysCfgMap.teacher_target_end_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">
                    &#12288;<a onclick="updateDate('teaTarget');" style="cursor:pointer;color:#4195c5;">保存</a>
                </h1>
            </form>
            <form id="stuRecruit" action="<s:url value="/gzykdx/school/updateDate"/>" method="post">
                <h1>二级机构上报录取考生时间：
                    <input type="hidden" name="cfgCode" value="student_recruit_date">
                    <input type="text" name="cfgValue" value="${sysCfgMap.student_recruit_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    &#12288;<a onclick="updateDate('stuRecruit');" style="cursor:pointer;color:#4195c5;">保存</a>
                </h1>
            </form>
            <form id="orgAdjust" action="<s:url value="/gzykdx/school/updateDate"/>" method="post">
                <h1>院内调剂时间：
                    <input type="hidden" name="cfgCode" value="org_adjust_start_date">
                    <input type="hidden" name="cfgCode" value="org_adjust_end_date">
                    <input type="text" name="cfgValue" value="${sysCfgMap.org_adjust_start_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">~
                    <input type="text" name="cfgValue" value="${sysCfgMap.org_adjust_end_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">
                    &#12288;<a onclick="updateDate('orgAdjust');" style="cursor:pointer;color:#4195c5;">保存</a>
                </h1>
            </form>
            <form id="inAdjust" action="<s:url value="/gzykdx/school/updateDate"/>" method="post">
                <h1>校内调剂时间：
                    <input type="hidden" name="cfgCode" value="inSchool_adjust_start_date">
                    <input type="hidden" name="cfgCode" value="inSchool_adjust_end_date">
                    <input type="text" name="cfgValue" value="${sysCfgMap.inSchool_adjust_start_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">~
                    <input type="text" name="cfgValue" value="${sysCfgMap.inSchool_adjust_end_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">
                    &#12288;<a onclick="updateDate('inAdjust');" style="cursor:pointer;color:#4195c5;">保存</a>
                </h1>
            </form>
            <form id="outAdjust" action="<s:url value="/gzykdx/school/updateDate"/>" method="post">
                <h1>校外调剂时间：
                    <input type="hidden" name="cfgCode" value="outSchool_adjust_start_date">
                    <input type="hidden" name="cfgCode" value="outSchool_adjust_end_date">
                    <input type="text" name="cfgValue" value="${sysCfgMap.outSchool_adjust_start_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">~
                    <input type="text" name="cfgValue" value="${sysCfgMap.outSchool_adjust_end_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">
                    &#12288;<a onclick="updateDate('outAdjust');" style="cursor:pointer;color:#4195c5;">保存</a>
                </h1>
            </form>
            <form id="studentsInfoOpen" action="<s:url value="/gzykdx/school/studentsInfoOpen"/>" method="post">
                <h1>考生信息开放时间：
                    <input type="hidden" name="cfgCode" value="studentsInfoOpen_start_date">
                    <input type="hidden" name="cfgCode" value="studentsInfoOpen_end_date">
                    <input type="text" name="cfgValue" value="${sysCfgMap.studentsInfoOpen_start_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">~
                    <input type="text" name="cfgValue" value="${sysCfgMap.studentsInfoOpen_end_date}" class="validate[required]" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="checkYear(this)">
                    &#12288;<a onclick="updateDate('studentsInfoOpen');" style="cursor:pointer;color:#4195c5;">保存</a>
                </h1>
            </form>
        </div>
    </div>
</div>
</body>
</html>