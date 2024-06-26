<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
    .content{margin:0 2px;}
    #mainbody{
        border-style: solid;
        border-width: 1px;
        border-color: lightgrey;
    }
    .index_show{padding-bottom:16px;text-align: justify;padding-top: 10px;padding-left:10px;padding-right:10px;}
    .index_tap_global{display: inline-block;vertical-align: top;text-align: center;}
    .index_tap_global.top_left{width: 50%; float:left;}
    .index_tap_global.top_left .inner{background-color: #e7f5fc;border:1px solid lightgrey;overflow: hidden;text-align: center;}
    .index_tap_global.top_right{width: 48%;margin-left:2%;}
    .index_tap_global.top_right .inner{background-color: #e7f5fc;border:1px solid lightgrey;overflow: hidden;text-align: center;}
    .index_item{ width:50%; float:left;}
    .index_item_global{ width:100%;}
    .index_item_global a{padding-top: 26px;padding-bottom: 26px;display: block;color: #fff;text-decoration: none;}
    .index_green{ width:100%;}
    .index_blue a:hover{ background:#e7f5fc;}
    .index_green a:hover{ background:#e7f5fc;}
    .tap_inner{display:block; height:75px;margin: 20px;}
    .tab_second{border-left: 1px solid lightgrey;margin: 20px 10px;}
    .number{margin-left: 5px;font-weight: 400;font-style: normal;vertical-align: middle;font-size: 35px;}
    .title{font-weight: 400;font-style: normal;font-size: 16px;letter-spacing: 2px;background: none;}
    .main_hd{line-height:40px;}
    .main_hd h2{font-size: 16px;padding: 0 30px;line-height: 75px;font-weight: 400;font-style: normal;display:block;}
    .main_bd{padding-bottom:0;}
    .score_frame{border:1px solid #e7e7eb;margin-bottom:15px;}
    .score_frame h1{padding:0 20px;height:40px; line-height:40px; background:#f4f5f9; border-bottom:1px solid #e7e7eb; font-size:15px; font-weight:normal;}
    .score_frame h1 span{ font-size:13px;}
    .grid .heb{ border-right:1px solid #e7e7eb;}
</style>
<c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}" var="unitKey"/>
<c:set var="isCustomResult" value="${GlobalConstant.FLAG_Y eq sysCfgMap['res_custom_result_flag']}"/>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
    require.config({
        paths: {
            echarts: "<s:url value='/js/echarts'/>"
        }
    });

    require(['echarts','echarts/chart/pie'],function(ec){
        var myChart = ec.init(document.getElementById('docChartForIndex'));
        option = {
            tooltip : {
                trigger: 'item',
                formatter: "{b} ({d}%)"
            },
            title : {
                text: '总数据自2014年起',
                x:'left',
                y:'bottom'
            },
            series : [
                {

                    type:'pie',
                    radius : '65%',
                    itemStyle : {
                        normal : {
                            label : {
                                show : true
                            },
                            labelLine : {
                                show : true
                            }
                        }
                    },
                    data:[
                        {value:"${currDocSumBef2014['_20count']}", name:'${dictTypeEnumDoctorStatusList.get(0).dictName}人数：${currDocSumBef2014['_20count']}人'},
                        <c:if test="${fn:length(dictTypeEnumDoctorStatusList) > 1}">//防止下标越界
                        {value:"${currDocSumBef2014['_21count']}", name:'${dictTypeEnumDoctorStatusList.get(1).dictName}人数：${currDocSumBef2014['_21count']}人'},
                        </c:if>
                    ]
                }
            ]
        };
// 为echarts对象加载数据
        myChart.setOption(option);
    });
</script>
<script>
    $(document).ready(function(){
        searchDoctorByJd();
        searchDoctorBySpe();
    });
    function searchDoctorByJd()
    {
        jboxLoad("doctorNum1","<s:url value='/res/doc/searchDoctorByJd'/>?"+$("#hospitalSearch").serialize(),true);
    }
    function searchDoctorBySpe()
    {
        jboxLoad("doctorNum2","<s:url value='/res/doc/searchDoctorBySpe'/>?"+$("#speSearch").serialize(),true);
    }
    function exportExcel(exp){
        var url = "<s:url value='/res/doc/exportForHbGlobal'/>";
        jboxExp($("#"+exp),url);
    }

$(document).ready(function(){
    var htmlContent='<table style="text-align: right;font-size: 16px;margin-left:15px">';
    <c:set var="yearpl" value="${pdfn:getCurrYear()}pl"></c:set>
    <c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
    <c:set value="0"  var="keyll"></c:set>
    <c:set value="${doctorCountExtMap[yearpl][type.dictId]}"  var="keyll"></c:set>
    htmlContent+='<tr><td style="text-align: right;min-width: 160px;">';
    var typeName ='${type.dictName}';
    var keyll = '${keyll}';
    if(keyll=='' ||keyll==null){
        keyll=0;
    }
    htmlContent=htmlContent+typeName+'：</td><td>'+keyll+'人&nbsp;</td></tr>';
    </c:forEach>
    htmlContent+='</table>';
    $("#labs").html(htmlContent);
});
</script>
</head>
<body>
<div class="mainright" style="overflow: auto">
    <div class="content">
        <div  id="mainbody">
            <div class="index_show">
                <div class="index_tap_global top_left">
                    <ul class="inner">
                        <li class="index_item index_blue">
                            <span class="tap_inner">
                                <c:set var="key" value="${pdfn:getCurrYear()}pl"></c:set>
                                <em class="number"><label>${doctorCountExtMap[key]["SUMCOUNT"]}人</label></em><br/>
                                <strong  class="title">${pdfn:getCurrYear()}
                                    <c:if test="${pdfn:getCurrYear()}">级</c:if>培训医师数</strong>
                            </span>
                        </li>
                        <li class="index_item index_blue">
                                <span class="tap_inner tab_second" style="min-width: 285px;">
                                    <lable class="title" id="labs"></lable>
                                </span>
                        </li>
                    </ul>
                </div>
                <div class="index_tap_global top_right">
                    <ul class="inner">
                        <li class="index_item index_green" style="width:50%; float:left;">
                                 <span class="tap_inner">
                                    <c:set var="key" value="${pdfn:getCurrYear()}pl"></c:set>
                                    <c:set var="num" value="0"></c:set>
                                     <c:if test="${fn:length(checkDocs)>=1}">
                                         <c:set var="num" value="${empty checkDocs?0:checkDocs.get(0)['COUNT']}"></c:set>
                                     </c:if>
                                    <em class="number"><label id="checkDoctor">${num}人</label></em><br/>
                                    <strong  class="title">${pdfn:getCurrYear()}
                                        <c:if test="${pdfn:getCurrYear()}}">年</c:if>考核医师数</strong>
                                 </span>
                        </li>
                        <li class="index_item index_green" style="width:50%; float:left;">
                                 <span class="tap_inner tab_second">
                                    <c:set var="key" value="${pdfn:getCurrYear()}pl"></c:set>
                                    <c:set var="num" value="0"></c:set>
                                     <c:if test="${fn:length(checkDocs)>=2}">
                                         <c:set var="num" value="${empty checkDocs?0:checkDocs.get(1)['COUNT']}"></c:set>
                                     </c:if>
                                    <em class="number"><label id="checkDoctor2">${num}人</label></em><br/>
                                    <strong  class="title">${pdfn:getCurrYear()+1}
                                        <c:if test="${pdfn:getCurrYear()}">年</c:if>考核医师数</strong>
                                 </span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="main_bd">
                <ul>
                    <li class="score_frame" >
                        <h1 style="background:#e7f5fc">人员信息概况</h1>
                        <c:set var="currYear" value="${pdfn:getCurrYear()}"></c:set>
                        <div class="grid">
                            <c:forEach step="1" begin="${currYear-2}" end="${currYear}" varStatus="num">
                                <table style="<c:if test='${num.count<3}'>float: left;margin-left:1%;</c:if> <c:if test='${num.count==3}'>margin-left:67%;</c:if>margin-top: 10px;margin-bottom: 10px;border: 1px solid #ccc;"  cellpadding="0" cellspacing="0" width="32%">
                                    <tbody>
                                    <c:set var="year" value="${currYear-num.count+1}"></c:set>
                                    <c:set var="key" value="${year}pl"></c:set>
                                    <c:if test="${num.count==3}">
                                        <tr style="height: 10px;"></tr>
                                    </c:if>
                                    <tr style="height: 40px;font-size: 16px;">
                                        <th align="center" style="background-color: #e7e7eb;" >${year}届&#12288;${doctorCountExtMap[key]["SUMCOUNT"]}人</th>
                                    </tr>
                                    <tr style="height: 50px;font-size: 15px;">
                                        <td align="left" style="padding-left: 10px;">
                                            <p style="width:100%">
                                                <span style="float: left;font-weight: 900;width:80px;">培训类别：</span>
                                                <span style="float: left;width:auto;text-align:left;padding-left: 5px;">住院医师<br/>&#12288;${doctorCountExtMap[key]["SUMCOUNT"]}人</span>
                                            </p>
                                        </td>
                                    </tr>
                                    <tr style="height: 50px;font-size: 15px;">
                                        <td align="left" style="padding-left: 10px;">
                                            <p style="width:100%">
                                                <span style="float: left;font-weight: 900;width:80px;">人员类型：</span>
                                                <c:forEach items="${dictTypeEnumDoctorTypeList}" var="type" varStatus="num">
                                                    <c:if test="${num.count ne 1}">
                                                        <span title="${type.dictName}" style="float: left;width:auto;text-align:left;padding-left: 18px;">${pdfn:cutString(type.dictName,4,true,3) }<br/>&#12288;${empty doctorCountExtMap[key][type.dictId]?0:doctorCountExtMap[key][type.dictId]}人</span>
                                                    </c:if>
                                                    <c:if test="${num.count eq 1}">
                                                        <span title="${type.dictName}" style="float: left;width:auto;text-align:left;padding-left: 5px;">${pdfn:cutString(type.dictName,4,true,3) }<br/>&#12288;${empty doctorCountExtMap[key][type.dictId]?0:doctorCountExtMap[key][type.dictId]}人</span>
                                                    </c:if>
                                                </c:forEach>
                                            </p>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </c:forEach>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="main_bd">
                <ul>
                    <li class="score_frame">
                        <h1 style="background:#e7f5fc">当前住培情况</h1>
                        <table class="in_table" style="float: left;width: 48%; margin-top: 10px;margin: 10px;margin-bottom: 10px;border: 1px solid #ccc;" cellpadding="0" cellspacing="0" width="100%">
                            <tbody>
                            <tr>
                                <td>
                                    <div id="docChartForIndex" style="height: 200px;"></div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        <div class="grid" style="overflow: auto;height: 200px;width: auto;margin: 10px">
                            <table class="in_table" style="border: 1px solid #ccc;" cellpadding="0" cellspacing="0" width="100%">
                                <colgroup>
                                    <col width="25%"/>
                                    <col width="25%"/>
                                    <col width="25%"/>
                                    <col width="25%"/>
                                </colgroup>
                                <tr style="height: 60px;font-size: 15px;border-bottom: 1px solid; border-color: lightgrey;">
                                    <td></td>
                                    <c:if test="${fn:length(dictTypeEnumDoctorStatusList) > 0}">
                                        <td>
                                            <div style="float: left;height: 10px;width: 10px;background: rgba(255,120,50,1);"></div><div style="float: left;line-height: 10px;"> ：${dictTypeEnumDoctorStatusList.get(0).dictName}人数</div>
                                        </td>
                                    </c:if>
                                    <c:if test="${fn:length(dictTypeEnumDoctorStatusList) > 1}">
                                    <td>
                                        <div style="float: left;height: 10px;width: 10px;background: rgba(100,200,255,1);"></div><div style="float: left;line-height: 10px;"> ：${dictTypeEnumDoctorStatusList.get(1).dictName}人数</div>
                                    </td>
                                    </c:if>
                                </tr>
                                <c:forEach items="${currDocDetailMaps}" var="currDocDetail">
                                    <tr style="height: 50px;font-size: 15px;border-bottom: 1px solid; border-color: lightgrey;">
                                        <td style="text-align: center">${currDocDetail['SESSIONNUMBER']}届</td>
                                        <c:forEach items="${dictTypeEnumDoctorStatusList}" var="type" varStatus="s">
                                            <c:if test="${s.count<=2}">
                                                <td style="text-align: left">&#12288;&#12288;&#12288;${currDocDetail[type.dictId] ==null? 0:currDocDetail[type.dictId]}人</td>
                                            </c:if>
                                        </c:forEach>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="main_bd" style="height:auto;">
                <ul>
                    <li class="score_frame">
                        <h1 style="background:#e7f5fc">基地信息概况</h1>
                        <div class="" style="margin-top: 15px;">
                            <form id="hospitalSearch">
                                <input hidden="hidden" name="flag" value="hospitalSearch">
                                <table cellspacing="8px">
                                    <tr>
                                        <td>&#12288;年级：
                                            <input type="text" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(){}})"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px;" />
                                        </td>
                                        <td>&#12288;结业年份：
                                            <input type="text" name="graduationNumber" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(){}})" class="input" readonly="readonly" style="width: 100px;margin-left: 0px;" />
                                        </td>
                                        <td>&#12288;专业：
                                            <select name="trainingSpeId" style="width: 150px;">
                                                <option  value="">全部</option>
                                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                                    <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>&#12288;状态：
                                            <select name="doctorStatusId" style="width: 100px;">
                                                <option  value="">全部</option>
                                                <c:forEach items="${dictTypeEnumDoctorStatusList}" var="dict">
                                                    <option value="${dict.dictId}" ${param.doctorStatusId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>I
                                            </select>
                                        </td>
                                        <td>&#12288;
                                            <input type="button" class="search" value="查&#12288;询" onclick="searchDoctorByJd();" />&#12288;
                                            <input type="button" class="search" value="导&#12288;出" onclick="exportExcel('hospitalSearch');" />
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <div style="margin: 10px">
                            <div class="index_table" id="doctorNum1"style="overflow: auto;height: auto;max-height:550px;width:100%;margin-top: 20px;">

                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="main_bd" style="height:auto;">
                <ul>
                    <li class="score_frame">
                        <h1 style="background:#e7f5fc">专业信息概况</h1>
                        <div class="" style="margin-top: 15px;">
                            <form id="speSearch" method="post">
                                <input hidden="hidden" name="flag" value="speSearch">
                                <table cellspacing="8px">
                                    <tr>
                                        <td>&#12288;年级：
                                            <input type="text" name="sessionNumber" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(){}})"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px;" />
                                        </td>
                                        <td>&#12288;结业年份：
                                            <input type="text" name="graduationNumber" onclick="WdatePicker({dateFmt:'yyyy',onpicked:function(){}})" class="input" readonly="readonly" style="width: 100px;margin-left: 0px;" />
                                        </td>
                                        <td>&#12288;基地：
                                            <select name="orgFlow" style="width: 150px;">
                                                <option  value="">全部</option>
                                                <c:forEach items="${applicationScope.sysOrgList}" var="org">
                                                    <option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>&#12288;状态：
                                            <select name="doctorStatusId" style="width: 100px;">
                                                <option  value="">全部</option>
                                                <c:forEach items="${dictTypeEnumDoctorStatusList}" var="dict">
                                                    <option value="${dict.dictId}" ${param.doctorStatusId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                                </c:forEach>I
                                            </select>
                                        </td>
                                        <td>&#12288;
                                            <input type="button" class="search" value="查&#12288;询" onclick="searchDoctorBySpe();" />&#12288;
                                            <input type="button" class="search" value="导&#12288;出" onclick="exportExcel('speSearch');" />
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                        <div style="margin: 10px">
                            <div class="index_table" id="doctorNum2"style="overflow: auto;height: auto;max-height:550px;width:100%;margin-top: 20px;">

                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>

