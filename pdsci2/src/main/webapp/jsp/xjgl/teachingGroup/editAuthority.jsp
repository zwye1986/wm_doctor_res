<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="application/javascript">

    function sysCfgUpdate(){
        if(false == $("#myForm").validationEngine("validate")){
            return false;
        }
        var url="<s:url value='/xjgl/teachingGroup/sysCfgUpdate'/>";
        jboxPost(url,$('#myForm').serialize(),function(){
            window.parent.frames["mainIframe"].window.toPage(1);
            jboxCloseMessager();
        },null,true);
    }
    function checkBDDate(dt){
        var dates = $(':text',$(dt).closest("th"));
        if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
            jboxTip("开始时间不能大于结束时间！");
            dt.value = "";
        }
    }
    function selectCourse(){
        var url = "<s:url value='/xjgl/teachingGroup/editCourseAuthority'/>";
        jboxOpen(url, '选定课程',500,560,true);
    }
    function selectSession(flag){
        if(flag=="Y"){
            $("input[name='inputCourseSession']").val("");
            $("input[name='inputCourseSession']").attr("disabled","disabled");
        }
        if(flag=="N"){
            $("input[name='inputCourseSession']").removeAttr("disabled");
        }
    }

    $(document).ready(function(){
        var flag=$("input[name='inputSession']:checked").val();
        if(flag=="Y"){
            $("input[name='inputCourseSession']").val("");
            $("input[name='inputCourseSession']").attr("disabled","disabled");
        }
        if(flag=="N"){
            $("input[name='inputCourseSession']").removeAttr("disabled");
        }
    });
</script>
<form id="myForm" method="post">

    <table class="basic" style="width:100%;margin-top:10px;">
        <tr>
            <th style="text-align:right;">开放成绩管理权限：</th>
            <th style="text-align:left;">&#12288;&#12288;
                <input type="radio" name="allCourse" value="Y" ${GlobalConstant.FLAG_Y eq allCourse.cfgValue?'checked':''} id="allCourse_Y" style="cursor: pointer;"/>
                <label for="allCourse_Y" style="cursor: pointer;">所有课程</label>&#12288;
                <input type="radio" name="allCourse" value="N" onclick="selectCourse();" ${GlobalConstant.FLAG_N eq allCourse.cfgValue?'checked':''} id="allCourse_N" style="cursor: pointer;"/>
                <label for="allCourse_N" style="cursor: pointer;">选定课程</label>
            </th>
        </tr>
        <tr>
            <th style="text-align:right;">录入年度：</th>
            <th style="text-align:left;">&#12288;&#12288;
                <input type="radio" name="inputSession" value="Y" onclick="selectSession('Y');" ${GlobalConstant.FLAG_Y eq inputSession.cfgValue?'checked':''} id="inputSession_Y" style="cursor: pointer;"/>
                <label for="inputSession_Y" style="cursor: pointer;">所有年度</label>&#12288;
                <input type="radio" name="inputSession" value="N" onclick="selectSession('N');" ${GlobalConstant.FLAG_N eq inputSession.cfgValue?'checked':''} id="inputSession_N" style="cursor: pointer;"/>
                <label for="inputSession_N" style="cursor: pointer;">特定年度</label>
                <input type="text" style="width:120px;" readonly="readonly" name="inputCourseSession" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy'});" value="${inputCourseSession.cfgValue}"/>
            </th>
        </tr>
        <tr>
            <th style="text-align:right;">录入时间：</th>
            <th style="text-align:left;">&#12288;&#12288;
                <input type="text" style="width:120px;" readonly="readonly" name="inputStartDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${inputStartDate.cfgValue}" onchange="checkBDDate(this);"/>
                - <input type="text" style="width:120px;" readonly="readonly" name="inputEndDate" class="validate[required]" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${inputEndDate.cfgValue}" onchange="checkBDDate(this);"/>
            </th>
        </tr>
    </table>
    <div id="dataDiv" hidden="hidden"></div>
</form>
<div style="margin-top: 15px;text-align: center;">
    <input type="button" class="search"  onclick="sysCfgUpdate();" value="确&#12288;认" />
    <input type="button" class="search"  onclick="jboxCloseMessager();" value="取&#12288;消" />
</div>
