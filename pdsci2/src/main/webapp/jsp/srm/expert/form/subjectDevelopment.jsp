<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        .basic tbody th{padding-right:0px;}
    </style>
    <script type="text/javascript">
        function accAdd(arg1,arg2){
            var r1,r2,m;
            try{r1=arg1.toString().split(".")[1].length;}catch(e){r1=0;}
            try{r2=arg2.toString().split(".")[1].length;}catch(e){r2=0;}
            m=Math.pow(10,Math.max(r1,r2));
            return (arg1*m+arg2*m)/m;
        }
        function accSub(arg1,arg2){
            var r1,r2,m,n;
            try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
            try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
            m=Math.pow(10,Math.max(r1,r2));
            n=(r1>=r2)?r1:r2;
            return ((arg1*m-arg2*m)/m).toFixed(n);
        }
        function accMul(arg1, arg2) {
            var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
            try { m += s1.split(".")[1].length } catch (e) { }
            try { m += s2.split(".")[1].length } catch (e) { }
            return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m)
        }
        function saveSecondScore(){
            if((parseFloat($("#scoreSum").val())/30).toFixed(2) > 10){
                jboxTip("二级表单折合分数不能超过10分！");
                return;
            }
            if("" == $("#scoreSum").val() || parseInt($("#scoreSum").val()) == 0){
                jboxTip("请评分后保存！");
                return;
            }
            if ($("#myForm").validationEngine("validate")) {
                jboxPost("<s:url value='/srm/expert/proj/saveSecondScore?expertProjFlow=${expertProjFlow}&itemFlow=${itemFlow}'/>",$('#myForm').serialize(),function(resp){
                    if(resp=="${GlobalConstant.SAVE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].$("#itemFlow${itemFlow}").text((parseFloat($("#scoreSum").val())/30).toFixed(2));
                        window.parent.frames['mainIframe'].$("#itemFlow${itemFlow}").prev().val((parseFloat($("#scoreSum").val())/30).toFixed(2));
                        var obj = window.parent.frames['mainIframe'].$("#scoreTotal");
                        $(obj).val(accSub(accAdd($(obj).val(),(parseFloat($("#scoreSum").val())/30).toFixed(2)),(parseFloat(${empty dataMap['scoreSum']?0:dataMap['scoreSum']})/30).toFixed(2)));
                        jboxClose();
                    }
                },null,true);
            }
        }
        function limitScore(obj,flag){
            if(flag == "Y"){//专利总分控制
                var zlScoreSum = 0;
                $(".zlScore").each(function(){
                    if(!isNaN(parseFloat($(this).val()))){
                        zlScoreSum = accAdd(zlScoreSum,accMul(parseFloat($(this).val()),parseFloat($(this).parent().prev().text())));
                    }
                });
                if(zlScoreSum > 20){
                    $(obj).val("");
                    $(obj).parent().next().text("");
                    jboxTip("专利证书合计最高20分!");
                    return;
                }
            }
//            var standard = $(obj).parent().prev().text();
//            if(parseInt(standard) < $(obj).val()){
//                $(obj).addClass("validate[max["+standard+"]]");
//            }
            var scoreSum = 0;
            $("input[name='score']").each(function(){
                if("" != $(this).val() && !isNaN($(this).val()) && $(this).attr("id") != "scoreSum"){//为数字,并且非合计分
                    scoreSum = accAdd(scoreSum,accMul(parseFloat($(this).val()),parseFloat($(this).parent().prev().text())));
                    $(this).parent().next().text(accMul(parseFloat($(this).val()),parseFloat($(this).parent().prev().text())));
                }else if("" == $(this).val() || isNaN($(this).val())){
                    $(this).parent().next().text("");
                }
            });
            $("#scoreSum").val(scoreSum);
            $("#showScoreSum").text(scoreSum);
        }
    </script>
</head>
<body>
<div style="width:100%;height:100%;overflow:auto;">
    <form id="myForm">
        <input type="hidden" name="jzScore" value="30"><!-- 基准分 -->
        <table  class="basic" style="margin:10px 0px 0px 0px;">
            <tr>
                <th colspan="7" style="text-align: center"><h2 style="font-size:150%">学科发展潜力</h2></th>
            </tr>
            <tr>
                <th style="text-align: center;width:10%;">一级指标</th>
                <th style="text-align: center;width:32%;">二级指标</th>
                <th style="text-align: center;width:10%;">计分标准</th>
                <th style="text-align: center;width:10%;">统计数据</th>
                <th style="text-align: center;width:10%;">得分</th>
                <th style="text-align: center;width:14%;">评分说明</th>
                <th style="text-align: center;width:14%;">备注</th>
            </tr>
            <tr>
                <th rowspan="2" style="text-align: center;">人才称号</th>
                <th style="text-align: left;">&nbsp;1、国家级有突出贡献的中青年科学、技术专家</th>
                <th style="text-align: center;">10</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score1']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score1']?'':dataMap['score1'] * 10}"/></th>
                <th rowspan="2" style="text-align: center;">人才称号按照最高得分记一次，不重复计分。</th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score1']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;2、省部级有突出贡献的中青年科学、技术专家、省&#12288;&#12288;333人才工程、六大人才工程</th>
                <th style="text-align: center;">5</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score2']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score2']?'':dataMap['score2'] * 5}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score2']}"/></th>
            </tr>
            <tr>
                <th rowspan="2" style="text-align: center;">研究生导师</th>
                <th style="text-align: left;">&nbsp;1、博士生导师</th>
                <th style="text-align: center;">15</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score3']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score3']?'':dataMap['score3'] * 15}"/></th>
                <th rowspan="2" style="text-align: center;">导师资格按最高层次得分记一次，不重复计分。</th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score3']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;2、硕士生导师</th>
                <th style="text-align: center;">10</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score4']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score4']?'':dataMap['score4'] * 10}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score4']}"/></th>
            </tr>
            <tr>
                <th rowspan="5" style="text-align: center;">学会任职</th>
                <th style="text-align: left;">&nbsp;1、全国学会常委及以上</th>
                <th style="text-align: center;">20</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score5']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score5']?'':dataMap['score5'] * 20}"/></th>
                <th rowspan="5" style="text-align: center;">中华医学会、中华中医药学会系列学会任职按最高层次得分记一次，不重复计分。</th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score5']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;2、省级学会主任委员</th>
                <th style="text-align: center;">15</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score6']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score6']?'':dataMap['score6'] * 15}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score6']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;3、省级学会副主任委员</th>
                <th style="text-align: center;">10</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score7']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score7']?'':dataMap['score7'] * 10}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score7']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;4、国家青年委员、省级学会专科分会专业学组组&#12288;&#12288;&#12288;长、委员（减半计分）、市级学会主委、副主委&#12288;&#12288;（减半计分）</th>
                <th style="text-align: center;">5</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score8']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score8']?'':dataMap['score8'] * 5}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score8']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;5、省级青年委员、市级委员</th>
                <th style="text-align: center;">2</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score9']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score9']?'':dataMap['score9'] * 5}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score9']}"/></th>
            </tr>
            <tr>
                <th rowspan="3" style="text-align: center;">科研项目</th>
                <th style="text-align: left;">&nbsp;1、国家、省自然（社会）科学基金项目</th>
                <th style="text-align: center;">10</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score10']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score10']?'':dataMap['score10'] * 10}"/></th>
                <th rowspan="3" style="text-align: center;">同一项目按照最高层次得分记一次，不同项目可累加计分。</th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score10']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;2、厅级科研项目（省科技厅、省卫计委）</th>
                <th style="text-align: center;">5</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score11']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score11']?'':dataMap['score11'] * 5}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score11']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;3、市级科研项目（市科技局、市卫计委）</th>
                <th style="text-align: center;">3</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score12']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score12']?'':dataMap['score12'] * 3}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score12']}"/></th>
            </tr>
            <tr>
                <th rowspan="5" style="text-align: center;">科技成果</th>
                <th style="text-align: left;">&nbsp;1、省部级科技成果二等奖（前5）</th>
                <th style="text-align: center;">20</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score13']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score13']?'':dataMap['score13'] * 20}"/></th>
                <th rowspan="5" style="text-align: center;">只计排名前三成果，按照排名倒比计分。同一成果按照最高层次得分记一次，不同成果可累加计分。</th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score13']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;2、省部级科技成果三等奖（前5）</th>
                <th style="text-align: center;">15</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score14']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score14']?'':dataMap['score14'] * 15}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score14']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;3、市级科技成果一等奖（前5）</th>
                <th style="text-align: center;">10</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score15']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score15']?'':dataMap['score15'] * 10}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score15']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;4、市级科技成果二等奖、厅新技术引进一等奖（前&#12288;&#12288;3）</th>
                <th style="text-align: center;">7</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score16']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score16']?'':dataMap['score16'] * 7}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score16']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;5、市级科技成果三等奖、厅新技术引进二等奖（前&#12288;&#12288;3）</th>
                <th style="text-align: center;">5</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score17']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score17']?'':dataMap['score17'] * 5}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score17']}"/></th>
            </tr>
            <tr>
                <th rowspan="2" style="text-align: center;">发表论文</th>
                <th style="text-align: left;">&nbsp;1、SCI、EI收录论文（以影响因子计分）</th>
                <th style="text-align: center;">1</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score18']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score18']?'':dataMap['score18'] * 1}"/></th>
                <th rowspan="2" style="text-align: center;">论文只统计第一作者（含通讯作者）的论著、综述。</th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score18']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;2、中华医学系列杂志（非核心期刊减半计分）</th>
                <th style="text-align: center;">1</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score19']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score19']?'':dataMap['score19'] * 1}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score19']}"/></th>
            </tr>
            <tr>
                <th rowspan="2" style="text-align: center;">专利证书</th>
                <th style="text-align: left;">&nbsp;1、发明专利</th>
                <th style="text-align: center;">5</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]] zlScore" size="10" name="score" value="${dataMap['score20']}" onblur="limitScore(this,'Y')"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score20']?'':dataMap['score20'] * 5}"/></th>
                <th rowspan="2" style="text-align: center;">合计最高20分</th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score20']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;2、实用新型专利</th>
                <th style="text-align: center;">2</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]] zlScore" size="10" name="score" value="${dataMap['score21']}" onblur="limitScore(this,'Y')"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score21']?'':dataMap['score21'] * 2}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score21']}"/></th>
            </tr>
            <tr>
                <th rowspan="3" style="text-align: center;">学科基础</th>
                <th style="text-align: left;">&nbsp;1、国家临床重点专科</th>
                <th style="text-align: center;">10</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score22']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score22']?'':dataMap['score22'] * 10}"/></th>
                <th rowspan="3"></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score22']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;2、省临床重点专科</th>
                <th style="text-align: center;">5</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score23']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score23']?'':dataMap['score23'] * 5}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score23']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;3、市临床重点专科</th>
                <th style="text-align: center;">2</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score24']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score24']?'':dataMap['score24'] * 2}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score24']}"/></th>
            </tr>
            <tr>
                <th rowspan="3" style="text-align: center;">团队结构</th>
                <th style="text-align: left;">&nbsp;1、年龄、职称、专业结构合理，博士占30%以上</th>
                <th style="text-align: center;">10</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score25']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score25']?'':dataMap['score25'] * 10}"/></th>
                <th rowspan="3"></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score25']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;2、年龄、职称、专业结构合理，博士占20%以上</th>
                <th style="text-align: center;">6</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score26']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score26']?'':dataMap['score26'] * 6}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score26']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;3、年龄、职称、专业结构合理，博士占10%以上</th>
                <th style="text-align: center;">3</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score27']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score27']?'':dataMap['score27'] * 3}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score27']}"/></th>
            </tr>
            <tr>
                <th rowspan="3" style="text-align: center;">支撑条件</th>
                <th style="text-align: left;">&nbsp;1、实验室条件、图书资料、管理激励机制等条件完&#12288;&#12288;全满足发展要求</th>
                <th style="text-align: center;">10</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score28']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score28']?'':dataMap['score28'] * 10}"/></th>
                <th rowspan="3"></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score28']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;2、实验室条件、图书资料、管理激励机制等条件较&#12288;&#12288;好满足发展要求</th>
                <th style="text-align: center;">6</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score29']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score29']?'':dataMap['score29'] * 6}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score29']}"/></th>
            </tr>
            <tr>
                <th style="text-align: left;">&nbsp;3、实验室条件、图书资料、管理激励机制等条件一&#12288;&#12288;般，仅部分满足发展要求</th>
                <th style="text-align: center;">3</th>
                <th style="text-align: center;"><input type="text" class="validate[thcustom[number]]" size="10" name="score" value="${dataMap['score30']}" onblur="limitScore(this)"></th>
                <th style="text-align: center;"><fmt:formatNumber type="number" value="${empty dataMap['score30']?'':dataMap['score30'] * 3}"/></th>
                <th style="text-align: center;"><input type="text" name="remark" style="width:110px;" value="${remarkMap['score30']}"/></th>
            </tr>
            <tr>
                <th colspan="2" style="text-align: right;">合计：</th>
                <th></th>
                <th><input type="hidden" size="10" name="score" id="scoreSum" value="${dataMap['scoreSum']}"></th>
                <th style="text-align: center;" id="showScoreSum">${dataMap['scoreSum']}</th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <th style="text-align:left;" colspan="7">&#12288;等次评定标准：每30分折算“领军人才学术水平”1分，得分≥240分为优秀；≥150为良好；<150分为一般。</th>
            </tr>
        </table>
    </form>
    <div style="margin-top:20px;text-align:center;">
        <input type="button" value="保&#12288;存" class="search" onclick="saveSecondScore();"/>
    </div>
</div>
</body>
</html>
