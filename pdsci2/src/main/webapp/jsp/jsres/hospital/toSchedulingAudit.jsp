<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        td{text-align: left}
        th{width: 120px}
    </style>
    <script type="text/javascript">
        function aduit(type,resultFlow) {
            jboxGet("<s:url value='/jsres/doctorRecruit/schedulingAuditOpe'/>?type="+type+"&resultFlow="+resultFlow,null,function(resp){
                if (resp== '${GlobalConstant.OPERATE_SUCCESSED}'){
                    jboxTip("操作成功！");
                    window.parent.toPage(1);
                    setTimeout(function(){
                        jboxClose();
                    }, 1000);
                }
            },null,false);
        }
    </script>
</head>
<body>
<div class="mainright" style="overflow: auto;">
    <div class="basic" style="max-height: 435px;" >
        <form id="addForm" style="position: relative;">
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="text-align: right;">姓名：</th>
                    <td>
                        ${result.doctorName}
                    </td>
                    <th style="text-align: right;">人员类型：</th>
                    <td>
                        ${result.doctorTypeName}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">标准科室：</th>
                    <td>
                        [${result.groupName}]${result.standardDeptName}
                    </td>
                    <th style="text-align: right;">轮转科室：</th>
                    <td>
                        ${result.schDeptName}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">轮转时间</th>
                    <td colspan="3">${result.schStartDate} - ${result.schEndDate}</td>
                </tr>
            </table>

        </form>

        <p style="text-align: center;margin-top: 33px">
            <input class="btn_green" type="button" value="通过" onclick="aduit('Passed','${result.resultFlow}');"/>&#12288;
            <input class="btn_green" type="button" value="不通过" onclick="aduit('UnPassed','${result.resultFlow}');"/>&#12288;
            <input class="btn_green" type="button" value="取消" onclick="top.jboxClose();"/>
        </p>
    </div>
</div>
</body>
</html>
