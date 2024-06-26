<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_datePicker" value="false"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER{background-color: #eee;}
    .cur{color:red;}
    .grid td.rline{border-right:1px solid #e7e7eb;}
    .grid td.lline{border-left:1px solid #e7e7eb;}
    #doctorListZi{margin:5px;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
        });
        if ("${param.liId}" != "") {
            $("#${param.liId}").addClass("tab_select");
        } else {
            $('li').first().addClass("tab_select");
        }
        $(".tab_select a").click();
    });
    function show(tab){
        hides();
        document.getElementById(tab).style.display="block";
    }
    function hides(){
        document.getElementById("report_01").style.display="none";
        document.getElementById("report_02").style.display="none";
        document.getElementById("report_03").style.display="none";
        document.getElementById("report_04").style.display="none";
        document.getElementById("report_05").style.display="none";
        document.getElementById("report_06").style.display="none";
    }
    function exportExcel(fileno){
        var url;
        if(fileno == 1){
            url = "<s:url value='/jsp/jsres/global/completionReport/file/2017年结业考核学员统计表.xlsx' />";
        }else if(fileno == 2){
            url = "<s:url value='/jsp/jsres/global/completionReport/file/2017年结业报名统计表.xlsx' />";
        }else if(fileno == 3){
            url = "<s:url value='/jsp/jsres/global/completionReport/file/2017年结业理论考核统计表.xlsx' />";
        }else if(fileno == 4){
            url = "<s:url value='/jsp/jsres/global/completionReport/file/2017年结业技能考核统计表.xlsx' />";
        }else if(fileno == 5){
            url = "<s:url value='/jsp/jsres/global/completionReport/file/2017年结业考核学员统计表-按照专业统计.xlsx' />";
        }else if(fileno == 6){
            url = "<s:url value='/jsp/jsres/global/completionReport/file/2017年结业考核学员统计表-按照紧缺专业统计.xlsx' />";
        }
        window.open(url);
    }
</script>
<body>
<div class="main_hd">
    <h2>2017年结业考核统计</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li class="tab" onclick="show('report_01');" style="cursor: pointer;"><a >按照地市统计</a></li>
            <li class="tab" onclick="show('report_02');" style="cursor: pointer;"><a >结业报名统计</a></li>
            <li class="tab" onclick="show('report_03');" style="cursor: pointer;"><a >理论考核统计</a></li>
            <li class="tab" onclick="show('report_04');" style="cursor: pointer;"><a >技能考核统计</a></li>
            <li class="tab" onclick="show('report_05');" style="cursor: pointer;"><a >按照专业统计</a></li>
            <li class="tab" onclick="show('report_06');" style="cursor: pointer;"><a >紧缺专业统计</a></li>
        </ul>
    </div>
</div>
<div class="main_bd">
    <div id="report_01">
        <jsp:include page="report_01.jsp" />
    </div>
    <div id="report_02" style="display:none;">
        <jsp:include page="report_02.jsp" />
    </div>
    <div id="report_03" style="display:none;">
        <jsp:include page="report_03.jsp" />
    </div>
    <div id="report_04" style="display:none;">
        <jsp:include page="report_04.jsp" />
    </div>
    <div id="report_05" style="display:none;">
        <jsp:include page="report_05.jsp" />
    </div>
    <div id="report_06" style="display:none;">
        <jsp:include page="report_06.jsp" />
    </div>
</div>
</body>