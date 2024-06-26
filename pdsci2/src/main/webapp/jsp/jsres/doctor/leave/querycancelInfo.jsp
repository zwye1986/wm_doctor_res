<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="ySelect" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
    <link rel="stylesheet" href="<s:url value="/jsp/jsres/css/detail.css"/>"/>

   <link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer2.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
    <script type="text/javascript" src="<s:url value='/js/viewer/viewer.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <style type="text/css">
        .boxHome .item:HOVER{background-color: #eee;}
        .cur{color:red;}
        .grid.lz_table tbody th {padding:0}
        .sh_ul{
            margin: 36px 0;
        }
        .sh_ul li{
            height: 36px;
            line-height: 36px;
        }
        .sh_ul li .icon-blue{
            margin-right: 24px;
            color: #686868;
        }
        .sh_ul li>span{
            margin-right: 42px;
        }
        .icon-blue{
            width: 16px;
            height: 16px;
            border-radius: 100%;
            border: 1px solid #b4b4b4;
            background-color: lightgreen;
            box-shadow: inset 0 0 0 3px #f4f4f4;
            position: relative;
            top: 9px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            <c:forEach items="${files}" var="file" varStatus="status">
            var id = "ratateImg${status.index+1}";
            if(${not empty file.filePath}) {
                var viewer = new Viewer(document.getElementById(id), {
                    url: 'data-original'
                });
            }
            </c:forEach>
        });

        function save(status) {
            if(false==$("#editForm").validationEngine("validate")){
                return false;
            }
            if('N' == status){
                var auditInfo = $("#auditInfo").val();
                if(auditInfo == ""){
                    jboxTip("不同意需填写审核意见！");
                    return;
                }
            }
            $("#status").val(status);
            var url = "<s:url value='/jsres/attendanceNew/saveCancelLeaveInfo'/>";
            jboxSubmit($("#editForm"), url, function (resp) {
                window.parent.search();
                setTimeout(function(){
                    if('${GlobalConstant.OPERATE_SUCCESSED}' == resp){
                        jboxClose();
                    }
                }, 1000);
            }, null, true);
        }
    </script>
</head>

<body>
<div class="div_table">
    <form id="editForm"  style="overflow: auto;height:450px;width: 800px" method="post">
        <input name="recordFlow" type="hidden" value="${resDoctorKq.recordFlow}"  />
        <input name="roleFlag" type="hidden" value="${roleFlag}"  />
        <input name="status" id="status" type="hidden"   />
        <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
            <colgroup>
                <col width="20%" />
                <col width="30%" />
                <col width="20%" />
                <col width="30%" />
            </colgroup>
            <tbody>
            <tr>
                <td colspan="4">
                    <h4>${resDoctorKq.doctorName}的销假申请</h4>
                </td>
            </tr>
            <tr>

                <th  style="text-align: right;">请假类型：</th>
                <td >
                    ${resDoctorKq.typeName}
                </td>
                <th  style="text-align: right;">请假天数：</th>
                <td >
                    ${resDoctorKq.intervalDays}天
                </td>
            </tr>
            <tr>
                <th  style="text-align: right;">开始时间：</th>
                <td>
                    ${resDoctorKq.startDate}
                </td>
                <th  style="text-align: right;">结束时间：</th>
                <td>
                    ${resDoctorKq.endDate}
                </td>
            </tr>
            <tr>
                <th  style="text-align: right;">请假原因：</th>
                <td colspan="3">
                    <textarea class="txt2" style="width: 80%;height: 100px" readonly>${resDoctorKq.doctorRemarks}</textarea>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">附件：</th>
                <td colspan="3" style="border-bottom: 1px solid #e3e3e3;" >
                    <c:forEach items="${files}" var="list" varStatus="status">
                        <div class="imageOper" style="border: 1px solid #e3e3e3; margin-left: 5px; margin-top: 5px;margin-bottom:5px;  width: 150px;height: 140px;float: left;text-align: center;" >
                            <ul >
                                <li id="ratateImg${status.index+1}">
                                    <img src="${sysCfgMap['upload_base_url']}/${list.filePath}" style="width: 150px;height: 140px;" >
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                    <c:if test="${empty files}">
                        <label>无</label>
                    </c:if>
                </td>
            </tr>
            <c:if test="${'show' ne type}">
                <tr>
                    <th >审核意见：</th>
                    <td style="text-align: left;" colspan="3">
                        <textarea class="txt2" style="width: 90%;height: 100px" id="auditInfo" name="auditInfo"></textarea>
                    </td>
                </tr>
            </c:if>
            </tbody>
        </table>
        <c:if test="${not empty leaveLogs}">
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
                <colgroup>
                    <col width="15%" />
                    <col width="20%" />
                    <col width="20%" />
                    <col width="10%" />
                    <col width="35%" />
                </colgroup>
                <tr>
                    <td colspan="5">
                        <h4>请假审批记录</h4>
                    </td>
                </tr>
                <c:forEach items="${leaveLogs}" var="log" varStatus="status">
                    <tr>
                        <td style="text-align: center;">${log.auditUserName}</td>
                        <td style="text-align: center;">${log.auditTime}</td>
                        <td style="text-align: center;">${log.auditStatusName}</td>
                        <td style="text-align: center;">${log.typeName}</td>
                        <td style="text-align: center;" <c:if test="${not empty log.auditRemake}">title="${log.auditRemake}" </c:if>>
                                ${empty log.auditRemake ? "无" : pdfn:cutString(log.auditRemake,6,true,3)}
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
            <colgroup>
                <col width="15%" />
                <col width="20%" />
                <col width="20%" />
                <col width="10%" />
                <col width="35%" />
            </colgroup>
            <tr>
                <td colspan="5">
                    <h4>销假审批记录</h4>
                </td>
            </tr>
            <c:forEach items="${cancelLogs}" var="log" varStatus="status">
                <tr>
                    <td style="text-align: center;">${log.auditUserName}</td>
                    <td style="text-align: center;">${log.auditTime}</td>
                    <td style="text-align: center;">${log.auditStatusName}</td>
                    <td style="text-align: center;">${log.typeName}</td>
                    <td style="text-align: center;" <c:if test="${not empty log.auditRemake}">title="${log.auditRemake}" </c:if>>
                            ${empty log.auditRemake ? "无" : pdfn:cutString(log.auditRemake,6,true,3)}
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty cancelLogs}">
                <tr>
                    <td colspan="5" style="text-align: center;">暂无销假审批记录</td>
                </tr>
            </c:if>
        </table>
    </form>

    <div class="button">
        <c:if test="${'show' ne type}">
            <input type="button" class="btn_green" onclick="save('Y');" value="同&#12288;意"/>&#12288;
            <input type="button" class="btn_green" onclick="save('N');" value="不同意"/>
        </c:if>
        <c:if test="${'show' eq type}">
            <input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
        </c:if>

    </div>
</div>
</body>
</html>