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
            <th colspan="5">
                <h2 style="font-size:150%">病例分析评分表</h2>
            </th>
        </tr>
        <tr height="16" style="height:16px">
            <th >考生姓名</th>
            <th ><input class="input" name="doctorName" value="${dataMap['doctorName']}"/></th>
            <th >准考证号</th>
            <th colspan="2" ><input class="input" name="tickNum" value="${dataMap['tickNum']}" type="text"></th>
        </tr>
        <tr height="16" style="height:16px">
            <th  >考试科目</th>
            <th ><input class="input" name="examinationSubject" value="${dataMap['examinationSubject']}" type="text"></th>
            <th  >考试阶段</th>
            <th colspan="2" >
                <label><input name="examinationPeriod" type="radio" value="firstPeriod"
                              <c:if test="${dataMap['examinationPeriod'] eq 'firstPeriod'}">checked="checked"</c:if>>第一阶段</label>
                <label><input name="examinationPeriod" type="radio" value="secondPeriod"
                              <c:if test="${dataMap['examinationPeriod'] eq 'secondPeriod'}">checked="checked"</c:if>>第二阶段</label>
            </th>
        </tr>
        <tr height="24" style="height:16px">
            <th rowspan="2" align="center" >评分项目</th>
            <th rowspan="2" align="center">评分要素</th>
            <th colspan="2" align="center">标准分</th>
            <th rowspan="2" align="center">得分</th>
        </tr>
        <tr height="32" style="height:32px">
            <th >手术科室</th>
            <th >非手术科室</th>
        </tr>
        <tr  style="height:50px">
            <th align="center">诊断与依据</th>
            <td style="padding-left: 4px;text-align: left;">诊断明确，依据充分</td>
            <td align="center">20</td>
            <td align="center">20</td>
            <td align="center"><input name="score1" value="${dataMap['score1']}" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <th align="center">鉴别诊断与分析</th>
            <td style="padding-left: 4px;text-align: left;">鉴别诊断准确，分析得当</td>
            <td align="center">10</td>
            <td align="center">10</td>
            <td align="center"  ><input name="score2" value="${dataMap['score2']}" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <th align="center">诊断与依据</th>
            <td style="padding-left: 4px;text-align: left;">诊断明确，依据充分</td>
            <td align="center">20</td>
            <td align="center">20</td>
            <td align="center"  ><input name="score3" value="${dataMap['score3']}"class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <th align="center">检查项目与原则</th>
            <td style="padding-left: 4px;text-align: left;">检查项目得当</td>
            <td align="center">15</td>
            <td align="center">15</td>
            <td align="center"  ><input name="score4" value="${dataMap['score4']}" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <th align="center">治疗原则与方案</th>
            <td style="padding-left: 4px;text-align: left;">治疗方案明确有效</td>
            <td align="center">20</td>
            <td align="center">20</td>
            <td align="center"  ><input name="score5" value="${dataMap['score5']}" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <th align="center">预后判断与分析</th>
            <td style="padding-left: 4px;text-align: left;">预后判断准确</td>
            <td align="center">15</td>
            <td align="center">5</td>
            <td align="center"  ><input name="score6" value="${dataMap['score6']}" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <th align="center">思路与逻辑性</th>
            <td style="padding-left: 4px;text-align: left;">思路敏捷，逻辑性强</td>
            <td align="center">10</td>
            <td align="center">10</td>
            <td align="center"  ><input name="score7" value="${dataMap['score7']}" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <th align="center">知识面<br>（广度与深度）</th>
            <td style="padding-left: 4px;text-align: left;">知识面广且深</td>
            <td align="center">5</td>
            <td align="center">10</td>
            <td align="center"  ><input name="score8" value="${dataMap['score8']}" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <th align="center">对本学科医疗进展<br>的掌握</th>
            <td style="padding-left: 4px;text-align: left;">掌握本学科医疗进展的情况</td>
            <td align="center" >5</td>
            <td align="center">10</td>
            <td align="center"  ><input name="score9" value="${dataMap['score9']}" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        <tr  style="height:50px">
            <td style="padding-left: 4px;text-align: left;">总分</td>
            <td align="center"></td>
            <td align="center">100</td>
            <td align="center">100</td>
            <td align="center"  ><input name="scoreSum" value="${dataMap['scoreSum']}" class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
        </tr>
        </tbody>
    </table>
    &nbsp;&nbsp;(注：折合成20分后计入第二部分总分)
</div>
</body>
</html>

