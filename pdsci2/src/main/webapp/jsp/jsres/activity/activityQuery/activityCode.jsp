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
        };
        function initCode()
        {

            $("#qrcode").empty();
            $("#qrcode2").empty();
            var url = "func://funcFlow=activitySigin&activityFlow=${activityFlow}&time="+moment().format('YYYYMMDDHHmmss');
            var url2 = "func://funcFlow=activityOutSigin&activityFlow=${activityFlow}&time="+moment().format('YYYYMMDDHHmmss');
            $("#qrcode").qrcode({
                render: "canvas",
                width: 400,
                height:400,
                correctLevel:0,//纠错等级
                text: url
            });
            $("#qrcode2").qrcode({
                render: "canvas",
                width: 400,
                height:400,
                correctLevel:0,//纠错等级
                text: url2
            });
        }
        function initCode2()
        {
            var url = "func://funcFlow=activitySigin&activityFlow=${activityFlow}";
            var url2 = "func://funcFlow=activityOutSigin&activityFlow=${activityFlow}";
            $("#qrcode").qrcode({
                render: "canvas",
                width: 400,
                height:400,
                correctLevel:0,//纠错等级
                text: url
            });
            $("#qrcode2").qrcode({
                render: "canvas",
                width: 400,
                height:400,
                correctLevel:0,//纠错等级
                text: url2
            });
        }
        $(document).ready(function () {

            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_activity_code_type" var="key"/>
            if("${pdfn:jsresPowerCfgMap(key)}"=="Y")
            {
                initCode();
                setInterval("initCode()",15000);
            }else{
                initCode2();
            }
            selTag("Singn");
        });
        <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_activity_start_time" var="key"/>
        <c:set var="startTime" value="${pdfn:jsresPowerCfgMap(key)}"></c:set>
        <c:set var="startTime" value="${empty startTime ?10:startTime}"></c:set>
        <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_activity_end_time" var="key"/>
        <c:set var="endTime" value="${pdfn:jsresPowerCfgMap(key)}"></c:set>
        <c:set var="endTime" value="${empty endTime ?10:endTime}"></c:set>
        function selTag(tabTag){
            if("SingnOut"==tabTag)
            {
                $("#SingnOut").removeClass("tab");
                $("#SingnOut").addClass("tab_select");
                $("#Singn").addClass("tab");
                $("#Singn").removeClass("tab_select");

                $("#SingnOutDiv").show();
                $("#SingnDiv").hide();
            }else{
                $("#Singn").removeClass("tab");
                $("#Singn").addClass("tab_select");
                $("#SingnOut").addClass("tab");
                $("#SingnOut").removeClass("tab_select");
                $("#SingnOutDiv").hide();
                $("#SingnDiv").show();
            }
//            showSecondCode(tabTag);
        }
        function showSecondCode(tabTag)
        {
            jboxStartLoading();
            var canvas=null;
            if("SingnOut"==tabTag)
            {
                 canvas=$('#qrcode2 canvas');
            }else{
                 canvas=$('#qrcode canvas');
            }
            if(canvas!=null)
            {
                var canvas2=$('#qrcode3 canvas');
                var base64 =canvas[0].toDataURL("image/png");
                var pic  = $("#pic1");
                pic[0].src = base64;

                var ctx = canvas2[0].getContext('2d');
                ctx.clearRect(0,0,400,430);
                ctx.drawImage(pic[0], 0, 0, 400, 400);
                ctx.font = "12px 微软雅黑";
                if("SingnOut"==tabTag)
                {
                    ctx.fillText("                                                                ", 0, 415);
                    ctx.fillText("【签退二维码】（此二维码在活动结束前${startTime}分钟至结束后${endTime}分钟内有效）", 0, 415);   // 文字
//                    $("#qrcode2").hide();
                }else{
                    ctx.fillText("                                                                ", 0, 415);
                    ctx.fillText("【签到二维码】（此二维码在讲座开始前${startTime}分钟至结束后${endTime}分钟内有效）", 0, 415);   // 文字
//                    $("#qrcode").hide();
                }
//                $("#qrcode3").show();
            }
            setTimeout(function() {
               jboxEndLoading();
            }, 2000 );
        }
        function  down(name)
        {
            $('#qrcode canvas').downCanvasImg("${activity.activityName}【签到二维码】（此二维码在讲座开始前${startTime}分钟至结束后${endTime}分钟内有效）.png");
        }
        function  down2(name)
        {
            $('#qrcode2 canvas').downCanvasImg("${activity.activityName}【签退二维码】（此二维码在活动结束前${startTime}分钟至结束后${endTime}分钟内有效）.png");
        }
        function  down3(name)
        {
            var fileName="${activity.activityName}[签退二维码].png";
            if(!$("#SingnOut").hasClass("tab_select"))
            {
                fileName="${activity.activityName}[签到二维码].png";

                //showSecondCode("Singn");
            }else{
                //showSecondCode("SingnOut");
            }
            setTimeout(function(){
                //$('#qrcode3 canvas').downCanvasImg(fileName);
            },2000);

        }
    </script>
</head>
<body>
<div class="main_hd">
    <div class="title_tab">
        <ul id="reducationTab">
            <li id="Singn" class="tab" onclick="selTag('Singn');"><a id="regiestA">签到二维码</a></li>
            <li id="SingnOut" class="tab" onclick="selTag('SingnOut');"><a id="scanA">签退二维码</a></li>
        </ul>
    </div>
</div>
<c:set value="jsres_${sessionScope.currUser.orgFlow }_org_activity_code_type" var="key"/>
<div id="SingnDiv" style="display: none;">
    <div id="qrcode" style="text-align: center;margin-top: 5px;">
    </div>
    <div>
        【签到二维码】（此二维码在讲座开始前${startTime}分钟至结束后${endTime}分钟内有效）
    </div>
    <div id="downDiv"  style="text-align: center;margin-top: 5px;">
        ${pdfn:jsresPowerCfgMap(key) eq 'Y'? '<font color="red">此二维码为动态二维码，每15秒钟刷新一次</font>':''}
        <input  type="button" class="btn_green" onclick="down();" style="display:${pdfn:jsresPowerCfgMap(key) eq 'Y'? 'none':''}"  value="下&#12288;载"/>&nbsp;
    </div>
</div>
<div id="SingnOutDiv" style="display: none;">

    <div id="qrcode2" style="text-align: center;margin-top: 5px;">
    </div>
    <div>
        【签退二维码】（此二维码在活动结束前${startTime}分钟至结束后${endTime}分钟内有效）
    </div>
    <div id="downDiv2"  style="text-align: center;margin-top: 5px;">
        ${pdfn:jsresPowerCfgMap(key) eq 'Y'? '<font color="red">此二维码为动态二维码，每15秒钟刷新一次</font>':''}
        <input  type="button" class="btn_green" onclick="down2();" style="display:${pdfn:jsresPowerCfgMap(key) eq 'Y'? 'none':''}" value="下&#12288;载"/>&nbsp;
    </div>
</div>
<div style="display: none">
    <img src="" id="pic1" style="display: none">
    <div id="qrcode3" style="text-align: center;margin-top: 5px;">
        <canvas id="new" name="new" width="400" height="430"></canvas>
    </div>
    <div id="downDiv3"  style="text-align: center;margin-top: 5px;">
        <input  type="button" class="btn_green" onclick="down3();" value="下&#12288;载"/>&nbsp;
    </div>
</div>
</body>
</html>
