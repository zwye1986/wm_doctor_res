<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <style type="text/css">
        table.gridtable {
            font-family: verdana,arial,sans-serif;
            font-size:11px;
            color:#333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }
        table.gridtable th {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
            text-align: right;
            height:30px;
        }
        table.gridtable td {
            border-width: 1px;
            padding: 5px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
            text-align: left;
            height:30px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            window.print();
        });
        window.onload = function () {
            jboxClose();
        }
    </script>
</head>
<body>
    <div>
        <table class="gridtable" style="width:100%;margin-top:6px;">
            <tr>
                <td rowspan="5" style="min-width:150px;width:1%;padding:5px;"><img width="150px" height="200px" src="${sysCfgMap['upload_base_url']}${tutor.headUrl}"/></td>
                <th style="min-width:100px;width:1%;">导师姓名：</th>
                <td style="min-width:150px;width:1%;">${tutor.doctorName}&#12288;${tutor.spellName}</td>
                <th style="min-width:100px;width:1%;">出生年月：</th>
                <td>${tutor.birthDay}</td>
            </tr>
            <tr>
                <th>联系方式：</th>
                <td colspan="3">${tutor.mobilePhone}&#12288;${tutor.workPhone}&#12288;${tutor.emailNo}</td>
            </tr>
            <tr>
                <th>技术职称：</th>
                <td>${tutor.technicalTitleName}</td>
                <th>工作单位：</th>
                <td>${tutor.workUnit}</td>
            </tr>
            <tr>
                <th>学术任职：</th>
                <td colspan="3">${tutor.academicActivities}</td>
            </tr>
            <tr>
                <th>研究方向：</th>
                <td colspan="3">${tutor.researchDirection}</td>
            </tr>
            <tr>
                <th colspan="5" style="text-align:left;padding-left:10px;">学术专著：</th>
            </tr>
            <tr>
                <td colspan="5">${tutor.academicMonographs}</td>
            </tr>
            <tr>
                <th colspan="5" style="text-align:left;padding-left:10px;">个人获奖情况：</th>
            </tr>
            <tr>
                <td colspan="5">${tutor.awardSituation}</td>
            </tr>
            <tr>
                <th colspan="5" style="text-align:left;padding-left:10px;">从事的主要研究方向及其特点和意义、开展新医疗、新技术等情况、国内所处的学术地位：</th>
            </tr>
            <tr>
                <td colspan="5">${tutor.researchDescribe}</td>
            </tr>
        </table>
    </div>
    <div style="border:1px solid #bbbbbb;padding:0px 10px;line-height:35px;margin-top:10px;">
        <div style="font-weight:bold;">论文统计</div>
        <c:forEach items="${paperList}" var="pap" varStatus="i">
            <div style="word-break:break-all;line-height:25px;<c:if test="${!i.last}">border-bottom:1px solid #bbbbbb;</c:if>">论文标题：${pap.paperTitle}&#12288;&#12288;发表时间：${pap.publishTime}<br/>
                期刊名称：${pap.periodicalName}&#12288;&#12288;影响因子：${pap.influenceFactor}&#12288;&#12288;JCR分区：${pap.jcrPartitionName}
                <div style="text-align:center;padding-bottom:8px;"><img width="100px" height="150px" src="${sysCfgMap['upload_base_url']}${pap.periodicalPicUrl}" style="margin-right:5px;cursor:pointer;"/><img width="100px" height="150px" src="${sysCfgMap['upload_base_url']}${pap.paperPicUrl}"/></div>
            </div>
        </c:forEach>
    </div>
    <div style="border:1px solid #bbbbbb;padding:0px 10px;line-height:35px;margin-top:10px;">
            <h2>课题统计统计</h2>
            <c:forEach items="${topicList}" var="top" varStatus="i">
                <div style="word-break:break-all;line-height:25px;<c:if test="${!i.last}">border-bottom:1px solid #bbbbbb;</c:if>">课题名称：${top.topicTitle}&#12288;&#12288;课题级别：${top.topicLevelName}<br/>
                    立项单位：${top.projectUnit}&#12288;&#12288;课题经费：${top.topicMoney}万元&#12288;&#12288;结题时间：${top.knotTime}
                    <div style="text-align:center;padding-bottom:8px;"><img width="100px" height="150px" src="${sysCfgMap['upload_base_url']}${top.contractPicUrl}" style="margin-right:5px;cursor:pointer;"/><img width="100px" height="150px" src="${sysCfgMap['upload_base_url']}${top.memberPicUrl}" style="margin-right:5px;cursor:pointer;"/><img width="100px" height="150px" src="${sysCfgMap['upload_base_url']}${top.fundsPicUrl}" style="margin-right:5px;cursor:pointer;"/><img width="100px" height="150px" src="${sysCfgMap['upload_base_url']}${top.schedulePicUrl}" style="margin-right:5px;cursor:pointer;"/><img width="100px" height="150px" src="${sysCfgMap['upload_base_url']}${top.sealPicUrl}"/></div>
                </div>
            </c:forEach>
        </div>
</body>
</html>