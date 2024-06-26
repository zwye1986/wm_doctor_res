<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            jboxPost("<s:url value='/zsey/base/saveDevices'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="deviceFlow" value="${devices.deviceFlow}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>设备名称：</td>
                    <td>
                        <input type="text" class="validate[required]" name="deviceName" value="${devices.deviceName}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>品牌：</td>
                    <td>
                        <input type="text" class="validate[required]" name="brandName" value="${devices.brandName}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>规格型号：</td>
                    <td>
                        <input type="text" class="validate[required]" name="deviceMode" value="${devices.deviceMode}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>数量：</td>
                    <td>
                        <input type="text" class="validate[required,custom[integer]]" name="deviceNumber" value="${devices.deviceNumber}"/>
                    </td>
                </tr>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>