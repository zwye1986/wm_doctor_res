<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        $.fn.downCanvasImg = function(name){
            if(this[0].tagName == 'CANVAS'){
                var base64 = this[0].toDataURL();
                var obj=$('<a/>').attr({
                    'href':base64,
                    'download':name
                })[0];
                if (document.all) {//如果支持的话，是ie下，默认有这个事件，
                    obj.click();
                } else {   //否则就自己添加一个
                    var evt = document.createEvent("MouseEvents");
                    evt.initEvent("click", true, true);
                    obj.dispatchEvent(evt);
                }
            }
        }
        function initCode()
        {

            $("#qrcode").empty();
            var url = "${signUrl}&time="+moment().format('YYYYMMDDHHmmss');
            $("#qrcode").qrcode({
                render: "canvas",
                width: 400,
                height:400,
                correctLevel:0,//纠错等级
                text: url
            });
        }
        function initCode2()
        {
            var url = "${signUrl}";
            $("#qrcode").qrcode({
                render: "canvas",
                width: 400,
                height:400,
                correctLevel:0,//纠错等级
                text: url
            });
        }
        $(document).ready(function () {
            if("${randomSign.codeStatusType}"=="Y")
            {
                initCode();
                setInterval("initCode()",5000);
            }else{
                initCode2();
            }
            selTag("Singn");
        });

        function selTag(tabTag){
            if("Singn"==tabTag)
            {
                $("#Singn").removeClass("tab");
                $("#Singn").addClass("tab_select");
                $("#SingnDiv").show();
            }
        }
        function  down(name,codeStartTime,codeEndTime)
        {
            var startTimes = codeStartTime.split(":");
            var endTimes = codeEndTime.split(":");
            $('#qrcode canvas').downCanvasImg("【随机签到二维码】（此二维码在"+startTimes[0]+"："+startTimes[1]+"："+startTimes[2]+"分钟" +
                "至"+endTimes[0]+"："+endTimes[1]+"："+endTimes[2]+ "分钟内有效）.png");
            //alert("【随机签到二维码】（此二维码在${pdfn:transDate(randomSign.codeStartTime)}分钟至${randomSign.codeEndTime}分钟内有效）.png");
        }
        function searchRandomSign(lectureFlow){
            var url = "<s:url value='/jsres/lecture/randomSignIn'/>?lectureFlow=" + lectureFlow;
            location.replace(url);
        }

    </script>
</head>
<body>
<div class="main_hd">
    <div class="title_tab">
        <ul id="reducationTab">
            <li id="Singn" class="tab" onclick="selTag('Singn');">随机签到二维码</li>
        </ul>
    </div>
</div>

<div id="SingnDiv" style="display: none;">
    <div id="qrcode" style="text-align: center;margin-top: 5px;">
    </div>
    <div>
        【随机签到二维码】（此二维码在${randomSign.codeStartTime}分钟至${randomSign.codeEndTime}分钟内有效）
    </div>

    <div id="downDiv"  style="text-align: center;margin-top: 5px;">
        <c:if test="${randomSign.codeStatusType == 'N'}">
            ${randomSign.codeStatusType eq 'Y'? '<font color="red">此二维码为动态二维码，每5秒钟刷新一次</font>':''}
            <input  type="button" class="btn_green" onclick="down(null,'${randomSign.codeStartTime}','${randomSign.codeEndTime}');" value="下&#12288;载"/>&nbsp;
        </c:if>
        <input  type="button" class="btn_green" onclick="searchRandomSign('${randomSign.lectureFlow}');"  value="返&#12288;回"/>&nbsp;
    </div>

</div>
</body>
</html>
