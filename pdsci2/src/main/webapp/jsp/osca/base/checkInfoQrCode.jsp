<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var url = "${codeInfo}";
            $("#qrcode").qrcode({
                render: "canvas",
                width: 460,
                height:460,
                correctLevel:0,//纠错等级
                text: url
            });
            canvasToImage();
        });
        function canvasToImage(){
            var dataurl=document.getElementsByTagName("canvas")[0].toDataURL();
            $("#codeImg").attr("src",dataurl);
            $("#codeImg1").attr("src",dataurl);
        }
        function printQrcode() {
            jboxTip("打印中，请稍等...");
            $("#base").jqprint({
                debug: false,
                importCSS: true,
                printContainer: true,
                operaSupport: false
            });
        }
    </script>
</head>
<body>
    <div style="text-align: center;margin: 20px 0px 15px 0px;">
        <label style="font-size: 18px;">${clinicalName}</label>
    </div>
    <div style="text-align: center;">
        <img src="" id="codeImg1" style="text-align: center;" width="200px" height="200px"/>
    </div>
    <div style="text-align: center;margin: 30px 0px 10px 0px;">
        <input type="button" class="search" value="打&#12288;印" onclick="printQrcode()"/>
    </div>

    <div id="base">
        <div style="text-align: center;margin: 180px 0px 36px 0px;">
            <label style="font-size: 32px;">${clinicalName}</label>
        </div>
        <div style="text-align: center">
            <img src="" id="codeImg" style="text-align: center;" width="460px" height="460px"/>
        </div>
    </div>
    <div id="qrcode" style="text-align: center">
    </div>
</body>
</html>
