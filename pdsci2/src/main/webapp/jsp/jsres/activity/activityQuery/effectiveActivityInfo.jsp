<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
    function saveInfo() {
        jboxPost("<s:url value='/jsres/activityQuery/saveEffectiveActivityInfo'/>",$('#searchForm').serialize(),function(resp){
            jboxTip(resp);
            if('${GlobalConstant.SAVE_SUCCESSED}'==resp){
                setTimeout(function(){
                    window.parent.toPage(${currentPage});
                    jboxClose();
                },1000);
            }
        },null,true);

    }
</script>

<div class="main_hd">
    <form  id="searchForm" method="post">
        <input type="hidden" name="activityFlow" value="${activityFlow}">
        <input type="hidden" name="isEffective" value="${isEffective}">
        <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
            <tr>
                <th style="text-align: right;width: 88px;">活动名称：</th>
                <td style="text-align: left">
                    ${activity.activityName}
                </td>
            </tr>
            <tr>
                <th style="text-align: right;width: 88px;">详细原因：</th>
                <td>
                    <textarea style="width: 100%;height: 100px;" placeholder="请输入不认可的详细原因"
                              name="reasonForDisagreement">${activity.reasonForDisagreement}</textarea>
                </td>
            </tr>
        </table>
        <span style="color: red;">*提示：确定不认可此活动信息？若确认，将不统计该活动信息。</span>
    </from>
    <div style="text-align: center;margin-top: 20px;">
        <input class="btn_green" type="button" value="取消" onclick="jboxClose()"/>&#12288;
        <input class="btn_green" type="button" value="保存" onclick="saveInfo()"/>
    </div>
</div>
</html>