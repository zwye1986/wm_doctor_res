<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="font" value="true" />
        <jsp:param name="jquery_validation" value="true" />
    </jsp:include>
    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    <style type="text/css">
        table{
            border-collapse: collapse;
            border: 1px solid #D3D3D3;
        }
        table td {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
            padding-left: 4px;
            padding-right: 2px;
        }
        table th {
            border-top: 0;
            border-right: 1px solid #D3D3D3;
            border-bottom: 1px solid #D3D3D3;
            border-left: 0;
        }
        table tr.lastrow td {
            border-bottom: 0;
        }
        table tr td.lastCol {
            border-right: 0;
        }
    </style>
</head>
<body>
<div class="search_table">
    <table cellpadding="4">
        <tbody>
        <tr height="34" class="firstRow">
            <th colspan="7">
                <h2 style="font-size:150%">体格检查评分表</h2>
            </th>
        </tr>
        <tr height="16" style="height:16px">
            <th >考生姓名</th>
            <th colspan="3"><input class="input" name="doctorName" type="text" value="${dataMap['doctorName']}"/></th>
            <th >准考证号</th>
            <th colspan="2" ><input class="input" name="tickNum" value="${dataMap['tickNum']}" type="text"/></th>
        </tr>
        <tr height="16" style="height:16px">
            <th  >考试科目</th>
            <th colspan="3"><input class="input" name="examinationSubject" value="${dataMap['examinationSubject']}"/></th>
            <th  >考试阶段</th>
            <th colspan="2" >
                <label><input name="examinationPeriod" type="radio" value="firstPeriod"
                              <c:if test="${dataMap['examinationPeriod'] eq 'firstPeriod'}">checked="checked"</c:if>>第一阶段</label>
                <label><input name="examinationPeriod" type="radio" value="secondPeriod"
                              <c:if test="${dataMap['examinationPeriod'] eq 'secondPeriod'}">checked="checked"</c:if>>第二阶段</label>
            </th>
        </tr>
        <tr height="24" style="height:16px">
            <th align="center" >评分项目</th>
            <th colspan="4" align="center"><input  >评分要素</th>
            <th align="center">标准分</th>
            <th align="center">得分</th>
        </tr>
        <tr  style="height:50px">
            <th align="center" rowspan="23">体格检查</th>
            <td align="center" rowspan="20">系统检查</td>
            <td colspan="3" style="padding-left: 4px;text-align: left;">暖手和听诊器、准备各种检查用具</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score1" value="${dataMap['score1']}" onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
        <td colspan="3" style="padding-left: 4px;text-align: left;">眼：结膜、巩膜、对光反射</td>
        <td align="center">3</td>
        <td align="center"  ><input name="score2" value="${dataMap['score2']}" onchange="saveScore(this,3);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3" style="padding-left: 4px;text-align: left;">头、浅表淋巴结：颈部、颔下、锁骨上、腋下淋巴结</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score3" value="${dataMap['score3']}" onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3" style="padding-left: 4px;text-align: left;">口腔</td>
            <td align="center">2</td>
            <td align="center"  ><input name="score4" value="${dataMap['score4']}" onchange="saveScore(this,2);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3" style="padding-left: 4px;text-align: left;">皮肤：口述皮肤有无黄染、紫绀、皮疹、苍白等</td>
            <td align="center">3</td>
            <td align="center"  ><input name="score5" value="${dataMap['score5']}" onchange="saveScore(this,3);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3" style="padding-left: 4px;text-align: left;">颈部：颈强直、甲状腺触诊</td>
            <td align="center">3</td>
            <td align="center"  ><input name="score6" value="${dataMap['score6']}" onchange="saveScore(this,3);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">胸部</td>
            <td align="center">（20）</td>
            <td align="center"  >-</td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">&nbsp;肺（望诊、触诊）呼吸运动</td>
            <td align="center">4</td>
            <td align="center"  ><input name="score7" value="${dataMap['score7']}" onchange="saveScore(this,4);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">&nbsp;&nbsp;（叩诊）上下左右对比，不要求肺尖和肺下界活动度叩诊</td>
            <td align="center">4</td>
            <td align="center"  ><input name="score8" value="${dataMap['score8']}" onchange="saveScore(this,4);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">&nbsp;&nbsp;（听诊）至少2个呼吸周期，包括胸背部听诊，上下左右对比</td>
            <td align="center">4</td>
            <td align="center"  ><input name="score9" value="${dataMap['score9']}" onchange="saveScore(this,4);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">&nbsp;心（望诊、触诊）心尖搏动</td>
            <td align="center">4</td>
            <td align="center"  ><input name="score10" value="${dataMap['score10']}" onchange="saveScore(this,4);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">&nbsp;&nbsp;（叩诊、听诊）5个瓣膜听诊区均需听诊</td>
            <td align="center">4</td>
            <td align="center"  ><input name="score11" value="${dataMap['score11']}" onchange="saveScore(this,4);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">腹部</td>
            <td align="center">（18）</td>
            <td align="center"  >-</td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">&nbsp;&nbsp;（望诊、触诊 ）全腹浅和深触诊，肝脾触诊，脾需要侧位触诊</td>
            <td align="center">8</td>
            <td align="center"  ><input name="score12" value="${dataMap['score12']}" onchange="saveScore(this,8);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">&nbsp;&nbsp;（叩诊）包括移动性浊音，不要求脾区叩诊</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score13" value="${dataMap['score13']}" onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">&nbsp;&nbsp;（听诊）肠鸣音</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score14" value="${dataMap['score14']}" onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">神经系统及四肢</td>
            <td align="center">（11）</td>
            <td align="center"  >-</td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">&nbsp;&nbsp;巴氏征、膝反射</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score15" value="${dataMap['score15']}" onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">&nbsp;&nbsp;下肢水肿（一定检查双侧）</td>
            <td align="center">3</td>
            <td align="center"  ><input name="score16" value="${dataMap['score16']}"onchange="saveScore(this,3);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">&nbsp;&nbsp;足背动脉、挠动脉搏动（一定检查双侧）</td>
            <td align="center">3</td>
            <td align="center"  ><input name="score17" value="${dataMap['score17']}"onchange="saveScore(this,3);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td align="center"  rowspan="3">重点查体</td>
            <td colspan="3" style="padding-left: 4px;text-align: left;">体格检查系统性、规范性</td>
            <td align="center">10</td>
            <td align="center"  ><input name="score18" value="${dataMap['score18']}"onchange="saveScore(this,10);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">根据病例特点重点器官检查（可超出上述系统查体范围）</td>
            <td  align="center">15</td>
            <td  align="center"  ><input name="score19" value="${dataMap['score19']}"onchange="saveScore(this,15);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">查体熟练，在规定时间内完成</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score20" value="${dataMap['score20']}"onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="5" style="padding-left: 4px;text-align: left;">合计</td>
            <td align="center">100</td>
            <td align="center"  ><input name="score21" value="${dataMap['score21']}"class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        </tbody>
    </table>
    &nbsp;&nbsp;(注：折合成20分后计入第二部分总分)
    </div>
</body>
</html>
