<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <script type="text/javascript">
        function effectiveInfo(isEffective)
        {
            var msg="";
            if(isEffective=="Y")
            {
                msg="确定认可此活动信息？";
            }else{
                isEffective="N";
                msg="确定不认可此活动信息？若确认，将不统计该" +
                        "活动信息";
            }

            jboxConfirm(msg,function(){
                var url = "<s:url value='/jsres/activityQuery/effectiveActivity'/>?activityFlow=${activity.activityFlow}&isEffective="+isEffective;
                jboxStartLoading();
                jboxPost(url,null,function(resp){
                    jboxEndLoading();
                    if(resp=="审核成功"){
                        if(isEffective=="Y")
                        {
                            $("#brk").show();
                            $("#rk").hide();
                        }else{
                            $("#rk").show();
                            $("#brk").hide();
                        }
                    }
                },null,true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="basic" style="max-height: 392px;overflow: auto;">
        <form id="addForm">
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="text-align: right;width: 88px;">活动名称：</th>
                    <td colspan="3">
                       ${activity.activityName}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">开始时间：</th>
                    <td>
                        ${activity.startTime}
                    </td>
                    <th style="text-align: right;">结束时间：</th>
                    <td>
                        ${activity.endTime}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">活动形式：</th>
                    <td>
                        ${activity.activityTypeName}
                    </td>
                    <th style="text-align: right;">所在科室：</th>
                    <td>
                        ${activity.deptName}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">主&nbsp;讲&nbsp;人&nbsp;：</th>
                    <td>
                        ${activity.userName}
                    </td>
                    <th style="text-align: right;">联系方式：</th>
                    <td>
                        ${activity.speakerPhone}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">活动地点：</th>
                    <td colspan="3">
                        ${activity.activityAddress}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">活动简介：</th>
                    <td colspan="3">
                        ${activity.activityRemark}
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">附&#12288;&#12288;件：</th>
                    <td colspan="3">
                        <c:if test="${empty activity.fileFlow}">
                            无
                        </c:if>
                        <c:if test="${not empty activity.fileFlow}">
                            <a href="<s:url value='/jsres/activityQuery/downFile'/>?fileFlow=${activity.fileFlow}">${activity.fileName}</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
        <p style="text-align: center;">
            <c:if test="${param.roleFlag eq 'local'}">
                <input type="button" onclick="effectiveInfo('Y');"id="rk" style="display: ${activity.isEffective eq 'Y'?'none':''}" class="btn_green" value="认&#12288;可"/>&#12288;
                <input type="button" onclick="effectiveInfo('N');"id="brk" style="display: ${activity.isEffective eq 'N'?'none':''}" class="btn_green" value="不认可"/>&#12288;
            </c:if>
            <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
