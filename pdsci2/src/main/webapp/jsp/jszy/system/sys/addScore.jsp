<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>江苏省住院医师规范化培训管理平台</title>
    <jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="font" value="true" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker2" value="true"/>
    </jsp:include>
    <script src="<s:url value='/jsp/eval/base/common/js/DatePicker/WdatePicker.js'/>"></script>
    <script type="text/javascript">
        function saveCfg(){
            if(false==$("#cfgForm").validationEngine("validate")){
                return ;
            }
            var url = "<s:url value='/jszy/base/saveCfg'/>";
            var data = $('#cfgForm').serialize();
            jboxPost(url,data,function(resp){
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    window.parent.searchCfgInfo();
                    top.jboxClose();
                }
            },null,true);
        }
    </script>
</head>

<body>
<div class="">
    <form id="cfgForm" method="post" style="position: relative;">
    <table class="grid" width="400" cellpadding="0" cellspacing="0">
        <tr>
            <th width="20%">年份：</th>
            <td width="80%">
                <c:if test="${empty resPassScoreCfg.cfgYear}">
                    <input class="validate[required] input" type="text" name="cfgYear"
                           onClick="WdatePicker({dateFmt:'yyyy'})"/>
                </c:if>
                <c:if test="${!empty resPassScoreCfg.cfgYear}">
                    <input class="validate[required] input" type="text" name="cfgYear"
                           value="${resPassScoreCfg.cfgYear}" readonly="readonly"/>
                </c:if>

            </td>
        </tr>
        <tr>
            <th width="20%">分数：</th>
            <td width="80%"><input class="validate[required,custom[number]] input" name="cfgPassScore" type="text" value="${resPassScoreCfg.cfgPassScore}"/></td>
        </tr>
    </table>
    </form>
    <div class="button" style="width: 400px;">
        <input class="btn_brown" type="button" value="保&#12288;存" onclick="saveCfg();" />
        <input class="btn_brown" type="button" value="关&#12288;闭" onclick="top.jboxClose();" />
    </div>
</div>


</body>
</html>