<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
    </jsp:include>
    <style type="text/css">
        .boxHome .item:HOVER{background-color: #eee;}
        .cur{color:red;}
    </style>
    <script type="text/javascript">
        function doclose()
        {
            jboxClose();
        }
        function  save()
        {

            if (false == $("#myform").validationEngine("validate")) {
                return;
            }
            var reason=$("#targetName").val();
            if(!reason)
            {
                jboxTip("请输入指标名称！");
                return false;
            }
            jboxConfirm("确认保存？" , function(){
                jboxStartLoading();
                jboxPost("<s:url value='/res/activityTarget/saveAdd'/>",
                    $("#myform").serialize(),
                    function(resp){
                        jboxEndLoading();
                        if ('${GlobalConstant.SAVE_SUCCESSED}' == resp) {
                            window.parent.frames['mainIframe'].window.toPage(1);
                            jboxClose();
                        }
                    },null,true);
            });
        }
    </script>
</head>
<body>
<div class="div_table">
    <form id="myform" style="position: relative;padding-top: 20px;" method="post">
        <input name="targetFlow" id="targetFlow" type="hidden"  value="${target.targetFlow}" />
        <table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;border:0px;"class="xllist" >
            <colgroup>
                <col width="25%"/>
                <col width="75%"/>
            </colgroup>
            <tbody>
            <tr style=" width:100%;text-align: center;">
                <td style="border: 0px solid;text-align: right;">
                    指标名称：
                </td>
                <td style="border: 0px solid;text-align: left;padding-left: 10px;">
                    <input name="targetName" type="text" id="targetName" <%--class="qtext validate[required,maxSize[300]]"--%>
                           value="${target.targetName}"
                           style="width: 380px;height: 30px;margin-left: 0px;" />
                </td>
                <%--<td style="border: 0px solid;text-align: left;padding-left: 10px;">
                    <textarea  style="width: 380px"
                           value="${target.targetName}"></textarea>
                           &lt;%&ndash;style="width: 380px;margin-left: 0px;" />&ndash;%&gt;
                </td>--%>
            </tr>
            </tbody>
        </table>
    </form>
    <div class="button">
        <input type="button" id="" class="search" onclick="save();" value="保&#12288;存"/>
        <input type="button" id="submitBtn" class="search" onclick="doclose();" value="关&#12288;闭"/>
    </div>
</div>
</body>
</html>
