<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        //异步
        function jboxPost(posturl,postdata,funcOk,funcErr,showResp){
            $.ajax({
                type : "post",
                url : posturl,
                data : postdata,
                cache : false,
                beforeSend : function(){
                    jboxStartLoading();
                },
                success : function(resp) {
                    jboxEndLoading();
                    if(showResp==false){

                    }else{
                        jboxTip(resp);
                    }
                    if(funcOk!=null){
                        funcOk(resp);
                    }
                },
                error : function() {
                    jboxEndLoading();
                    jboxTip("操作失败,请刷新页面后重试");
                    if(funcErr!=null){
                        funcErr();
                    }
                },
                complete : function(){
                    jboxEndLoading();
                }
            });
        }
        function save() {
            if(false==$("#saveCfgForm").validationEngine("validate")){
                return ;
            }
            var url = "<s:url value='/res/cfg/saveActivityCfg'/>";
            var data = $('#saveCfgForm').serialize();
            jboxPost(url, data, function(resp) {
                jboxTip(resp);
                setTimeout(function(){
                    window.location.reload();
                },1500);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="saveCfgForm">
                <table class="xllist" style="margin-top: 10px;">
                    <thead>
                    <tr>
                        <th width="20%" style="text-align: center;">配置项</th>
                        <th width="25%" style="text-align: center;">配置内容</th>
                        <th width="60%" style="text-align: center;">配置参数值</th>
                    </tr>
                    </thead>
                    <tr>
                        <td style="text-align: center;width: 100px;"  rowspan="2">讲座活动扫码配置：</td>
                        <td style="text-align: center"  rowspan="2">

                            <c:set value="res_${sessionScope.currUser.orgFlow }_org_jiangzuo_code_type" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="N"
                                   <c:if test="${pdfn:resPowerCfgMap(key).cfgValue ne 'Y'}">checked</c:if>/><label
                                for="${key}_y">静态二维码</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="Y"
                                   <c:if test="${pdfn:resPowerCfgMap(key).cfgValue eq 'Y'}">checked</c:if>/><label
                                for="${key}_n">动态二维码</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="讲座活动活动二维码类型">
                        </td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="res_${sessionScope.currUser.orgFlow }_org_jiangzuo_start_time" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            讲座开始时间前后<input type="text" style="width:50px;" class="input validate[custom[number],min[1]]" name="${key}" value="${pdfn:resPowerCfgMap(key).cfgValue}">分钟内扫码签到有效。(默认值为：10分钟)
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="讲座签到时间">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="res_${sessionScope.currUser.orgFlow }_org_jiangzuo_end_time" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            讲座结束时间前后<input type="text"  style="width:50px;" class="input validate[custom[number],min[1]]" name="${key}" value="${pdfn:resPowerCfgMap(key).cfgValue}">分钟内扫码签到有效。(默认值为：10分钟)
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="讲座签退时间">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center"  rowspan="2">教学活动扫码配置：</td>
                        <td style="text-align: center"  rowspan="2">
                            <c:set value="res_${sessionScope.currUser.orgFlow }_org_activity_code_type" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="N"
                                   <c:if test="${pdfn:resPowerCfgMap(key).cfgValue  ne 'Y'}">checked</c:if>/><label
                                for="${key}_y">静态二维码</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="Y"
                                   <c:if test="${pdfn:resPowerCfgMap(key).cfgValue eq 'Y'}">checked</c:if>/><label
                                for="${key}_n">动态二维码</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="教学活动活动二维码类型">
                        </td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="res_${sessionScope.currUser.orgFlow }_org_activity_start_time" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            活动开始时间前后<input type="text"  style="width:50px;" class="input validate[custom[number],min[1]]" name="${key}" value="${pdfn:resPowerCfgMap(key).cfgValue}">分钟内扫码签到有效。(默认值为：10分钟)
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="教学活动签到时间">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="res_${sessionScope.currUser.orgFlow }_org_activity_end_time" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            活动结束时间前后<input type="text" style="width:50px;"  class="input validate[custom[number],min[1]]" name="${key}" value="${pdfn:resPowerCfgMap(key).cfgValue}">分钟内扫码签到有效。(默认值为：10分钟)
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="教学活动签退时间">
                        </td>
                    </tr>
                </table>
                <div class="button" >
                    <input type="button" class="search" onclick="save();" value="保&#12288;存">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>