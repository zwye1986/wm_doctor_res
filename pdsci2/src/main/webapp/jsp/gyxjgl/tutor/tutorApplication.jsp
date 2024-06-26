<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>

    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function applyInfo(tutorFlow){
            jboxConfirm("确认提交导师申请？", function(){
                var url = "<s:url value='/gyxjgl/tutor/applyOption?tutorFlow='/>"+tutorFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function detailInfo(tutorFlow){
            var url = "<s:url value='/gyxjgl/tutor/detailInfo?tutorFlow='/>"+tutorFlow;
            jboxOpen(url, "查看",800,600);
        }
        function printInfo(tutorFlow){
            var url = '<s:url value="/gyxjgl/tutor/printDetail?tutorFlow="/>'+tutorFlow;
            jboxOpen(url,"打印",800,600,true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="line-height:40px;"><span style="color:red;">&#12288;注意：导师申请后进入审核流程，无法更正导师信息！</span></div>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>导师姓名</th>
                <th>导师类型</th>
                <th>培养单位</th>
                <th>申请时间</th>
                <th>二级机构审核</th>
                <th>研究生院审核</th>
                <th style="min-width:140px;">操作</th>
            </tr>
            <tr>
                <td>${tutor.doctorName}</td>
                <td>${tutor.doctorTypeName}</td>
                <td>${tutor.pydwOrgName}</td>
                <td>${tutor.applyTime}</td>
                <td style="cursor:pointer;" title="<div style='max-width:400px;white-space:nowrap;white-space:pre-wrap;'>${empty tutor.pydwAuditAdvice?'无':tutor.pydwAuditAdvice}</div>">${empty tutor.pydwAuditStatusName?'--':tutor.pydwAuditStatusName}</td>
                <td style="cursor:pointer;" title="<div style='max-width:400px;white-space:nowrap;white-space:pre-wrap;'>${empty tutor.xwkAuditAdvice?'无':tutor.xwkAuditAdvice}</div>">${empty tutor.xwkAuditStatusName?'--':tutor.xwkAuditStatusName}</td>
                <td>
                    <c:if test="${!empty tutor.doctorFlow && tutor.applyFlag ne GlobalConstant.FLAG_Y}">
                        <a onclick="applyInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">导师申请</a>
                    </c:if>
                    <a onclick="detailInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">查看</a>
                </td>
            </tr>
        </table>
    </div>
</div>
<div id="printDiv" style="display: none;height:10000px;"></div>
</body>
</html>