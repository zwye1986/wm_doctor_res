<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        $(function(){
            <c:set value="res_${sessionScope.currUser.orgFlow }_org_jiangzuo_code_type" var="key"/>
            if("${pdfn:resPowerCfgMap(key).cfgValue}"=="Y") {
                initCode();
                setInterval("initCode()",5000);
            }else{
                initCode2();
            }
            selTag("Singn");
        });

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
            $("#qrcode2").empty();
            var url = "${signUrl}"+"&time="+moment().format('YYYYMMDDHHmmss');
            var url2 = "${signOutUrl}"+"&time="+moment().format('YYYYMMDDHHmmss');
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
            var url = "${signUrl}";
            var url2 = "${signOutUrl}";
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

        <c:set value="res_${sessionScope.currUser.orgFlow }_org_jiangzuo_start_time" var="key"/>
        <c:set var="startTime" value="${pdfn:resPowerCfgMap(key).cfgValue}"></c:set>
        <c:set var="startTime" value="${empty startTime ?10:startTime}"></c:set>
        <c:set value="res_${sessionScope.currUser.orgFlow }_org_jiangzuo_end_time" var="key"/>
        <c:set var="endTime" value="${pdfn:resPowerCfgMap(key).cfgValue}"></c:set>
        <c:set var="endTime" value="${empty endTime ?10:endTime}"></c:set>

        function selTag(tabTag){
            if("SingnOut" == tabTag) {
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
                    ctx.fillText("【签退二维码】", 0, 415);   // 文字
                }else{
                    ctx.fillText("                                                                ", 0, 415);
                    ctx.fillText("【签到二维码】（此二维码在讲座开始前${startTime}分钟至开始后${startTime}分钟内有效）", 0, 415);   // 文字
                }
            }
            setTimeout(function() {
                jboxEndLoading();
            }, 2000 );
        }
        function  down(name)
        {
            $('#qrcode canvas').downCanvasImg("【签到二维码】.png");
        }
        function  down2(name)
        {
            $('#qrcode2 canvas').downCanvasImg("【签退二维码】.png");
        }
        function  down3(name)
        {
            var fileName="[签退二维码].png";
            if(!$("#SingnOut").hasClass("tab_select")) {
                fileName="[签到二维码].png";
            }
        }
    </script>
</head>
<body>
<div class="title_tab">
    <ul>
        <li id="Singn" class="tab_select" style="cursor: pointer;" onclick="selTag('Singn');"><a id="regiestA">签到二维码</a></li>
        <li id="SingnOut" class="tab" style="cursor: pointer;" onclick="selTag('SingnOut');"><a id="scanA">签退二维码</a></li>
    </ul>
</div>
<c:set value="res_${sessionScope.currUser.orgFlow }_org_jiangzuo_code_type" var="key"/>
<div id="SingnDiv" style="display: none;">
    <div id="qrcode" style="text-align: center;margin-top: 5px;">
    </div>
    <div>
        【签到二维码】（此二维码在讲座开始前${startTime}分钟至开始后${startTime}分钟内有效）
    </div>
    <div id="downDiv"  style="text-align: center;margin-top: 5px;">
        ${pdfn:resPowerCfgMap(key).cfgValue eq 'Y'? '<font color="red">此二维码为动态二维码，每5秒钟刷新一次</font>':''}
        <input  type="button" class="btn_green" onclick="down();" style="display:${pdfn:resPowerCfgMap(key).cfgValue eq 'Y'? 'none':''}"  value="下&#12288;载"/>&nbsp;
    </div>
</div>
<div id="SingnOutDiv" style="display: none;">

    <div id="qrcode2" style="text-align: center;margin-top: 5px;">
    </div>
    <div>

    </div>
    <div id="downDiv2"  style="text-align: center;margin-top: 5px;">
        ${pdfn:resPowerCfgMap(key).cfgValue eq 'Y'? '<font color="red">此二维码为动态二维码，每5秒钟刷新一次</font>':''}
        <input  type="button" class="btn_green" onclick="down2();" style="display:${pdfn:resPowerCfgMap(key).cfgValue eq 'Y'? 'none':''}"  value="下&#12288;载"/>&nbsp;
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
