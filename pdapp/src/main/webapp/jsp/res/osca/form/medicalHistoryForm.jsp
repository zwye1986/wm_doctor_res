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
                <h2 style="font-size:150%">病史采集评分表</h2>
            </th>
        </tr>
        <tr height="16" style="height:16px">
            <th >考生姓名</th>
            <th colspan="3"><input class="input" name="doctorName" type="text" value="${dataMap['doctorName']}"/></th>
            <th >准考证号</th>
            <th colspan="2" ><input class="input" name="tickNum" value="${dataMap['tickNum']}" type="text"></th>
        </tr>
        <tr height="16" style="height:16px">
            <th  >考试科目</th>
            <th colspan="3"><input class="input" name="examinationSubject" value="${dataMap['examinationSubject']}" type="text"></th>
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
            <th align="center" rowspan="12">询问病史</th>
            <td align="center" rowspan="2">基本常识</td>
            <td colspan="3" style="padding-left: 4px;text-align: left;">检查者自我介绍（姓名、职务或职责）</td>
            <td align="center">10</td>
            <td align="center"  ><input name="score1" value="${dataMap['score1']}"onchange="saveScore(this,10);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3" style="padding-left: 4px;text-align: left;">检查者询问患者的姓名、年龄、职业、籍贯等基本情况</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score2" value="${dataMap['score2']}"onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td align="center" rowspan="3">现病史</td>
            <td colspan="3" style="padding-left: 4px;text-align: left;">主要症状及时间</td>
            <td align="center">10</td>
            <td align="center"  ><input name="score3" value="${dataMap['score3']}"onchange="saveScore(this,10);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3" style="padding-left: 4px;text-align: left;">伴随症状及诱因</td>
            <td align="center">10</td>
            <td align="center"  ><input name="score4" value="${dataMap['score4']}"onchange="saveScore(this,10);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3" style="padding-left: 4px;text-align: left;">诊疗经过及目前一般状况</td>
            <td align="center">20</td>
            <td align="center"  ><input name="score5" value="${dataMap['score5']}"onchange="saveScore(this,20);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td align="center" rowspan="4">既往及家族史</td>
            <td colspan="3" style="padding-left: 4px;text-align: left;">既往就诊经过</td>
            <td align="center">10</td>
            <td align="center"  ><input name="score6" value="${dataMap['score6']}"onchange="saveScore(this,10);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">既往治疗用药</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score7" value="${dataMap['score7']}"onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">个人史（强调药物过敏史）</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score8" value="${dataMap['score8']}"onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">家族史</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score9" value="${dataMap['score9']}"onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td align="center" rowspan="3">用语技巧</td>
            <td colspan="3"style="padding-left: 4px;text-align: left;">收集资料技巧</td>
            <td align="center">10</td>
            <td align="center"  ><input name="score10" value="${dataMap['score10']}"onchange="saveScore(this,10);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">交流技巧</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score11" value="${dataMap['score11']}"onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="3"style="padding-left: 4px;text-align: left;">医生态度</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score12" value="${dataMap['score12']}"onchange="saveScore(this,5);" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td colspan="5" style="padding-left: 4px;text-align: left;">合计</td>
            <td align="center">100</td>
            <td align="center"  ><input name="scoreSum" value="${dataMap['scoreSum']}"class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        </tbody>
    </table>
    &nbsp;&nbsp;(注：折合成20分后计入第二部分总分)
</div>
</body>
</html>

