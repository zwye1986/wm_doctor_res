<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>住院医师规范化培训结业考核管理平台</title>
    <jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
    </jsp:include>

    <script type="text/javascript">
        function pt() {
            jboxTip("正在准备打印…")
            setTimeout(function(){
                var newstr = $("#printDiv").html();
                var oldstr = document.body.innerHTML;
                document.body.innerHTML = newstr;
                window.print();
                document.body.innerHTML = oldstr;
                jboxEndLoading();
                return false;
            },2000);
        }
    </script>
</head>
<style type="text/css">
</style>
<body>
    <div style="height: 550px;overflow: auto">
    <div style="position:absolute;float:right;z-index:100;left:0;top:0;">
        <input type="button" class="btn_blue" value="打印" onclick="pt()">
    </div>
    <div style="padding:42px 56px 56px 56px;margin:56px 42px;border: double;">
        <h1 style="font-size: 25px;font-family: 宋体">住院医师规范化培训合格证书</h1>
        <div style="font-family: 楷体;height: 127px;width: 91px;border:1px solid;margin: 42px auto 34px auto;text-align: center;line-height: 127px;">
            照片
        </div>
        <div style="font-size: 14px;font-family: 宋体;">
            &#12288;&#12288;&#12288;证书编号：<span id="no" style="font-weight:bold; "></span>
        </div>
        <script>
            $(function(){
                var begins = '${docinfo.begindate}'.split('-');
                var ends = '${docinfo.enddate}'.split('-');
                var no0 = '${docinfo.completeNo}';
                var no = no0.substring(0,4)+" "+no0.substring(4,6)+" "+no0.substring(6,10)+" "+no0.substring(10,13)+" "+no0.substring(13,16);
                var orgName0 = '${docinfo.orgName}';
                var orgName = "";
                if(orgName0.length>6){
                    $("#orgName").css("top","157mm");
                    while(orgName0.length>0){
                        orgName+=orgName0.substring(0,6)+'<br/>';
                        orgName0 = orgName0.substring(6);
                    }
                    $("#orgName").html(orgName);
                }else{
                    $("#orgName").text(orgName0);
                }

                var speName0 = '${docinfo.speName}';
                var speName = "";
                if(speName0.length>3){
                    $("#speName").css("top","157mm");
                    while(speName0.length>0){
                        speName+=speName0.substring(0,3)+'<br/>';
                        speName0 = speName0.substring(3);
                    }
                    $("#speName").html(speName);
                }else{
                    $("#speName").text(speName0);
                }

                var userName0 = '${docinfo.userName}';
                var userName = "";
                if(userName0.length>4){
                    $("#userName").css("top","142mm");
                    while(userName0.length>0){
                        userName+=userName0.substring(0,4)+'<br/>';
                        userName0 = userName0.substring(4);
                    }
                    $("#userName").html(userName);
                }else{
                    $("#userName").text(userName0);
                }

                if(begins[0]){
                    $("#year1,#y1").text(begins[0]);
                }else{
                    $("#year1,#y1").text(begins[0]).css("padding-left","25px");
                }
                if(ends[0]){
                    $("#year2,#y2").text(ends[0]);
                }else{
                    $("#year2,#y2").text(ends[0]).css("padding-left","25px");
                }
                if(begins[1]){
                    $("#month1,#m1").text(begins[1]);
                }else{
                    $("#month1,#m1").text(begins[1]).css("padding-left","25px");
                }
                if(ends[1]){
                    $("#month2,#m2").text(ends[1]);
                }else{
                    $("#month2,#m2").text(ends[1]).css("padding-left","25px");
                }
                $("#qualifiedNo,#no").text(no);
            })
        </script>
        <div style="font-size: 14px;margin-top: 64px;line-height: 22px;letter-spacing: 2px;font-family: 宋体">
            &#12288;&#12288;<span style="font-weight: bold;">${docinfo.userName}</span>于<font id="year1"></font>年<font id="month1"></font>月至<font id="year2"></font>年<font id="month2"></font>月期间，<br/>
            在<span style="font-weight: bold;">${docinfo.orgName}</span >培训基地参加<span style="font-weight: bold;">${docinfo.speName}</span>专业住院医师规范化培训，经考核合格，特发此证。
        </div>
        <div style="margin-top: 44px;font-family: 宋体">
            <table style="font-size: 14px;letter-spacing: 2px;">
                <tr>
                    <td style="text-align: left">专业基地主任（签字）</td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="text-align: right">省级卫生计生（中医药）</td>
                </tr>
                <tr>
                    <td style="text-align: left">培训基地院长（签字）</td>
                    <td style="text-align: right">行政管理部门（盖章）</td>
                </tr>
                <tr style="height: 77px;">
                    <td></td>
                    <td style="text-align: right">2016年12月31日</td>
                </tr>
            </table>
        </div>
    </div>
    <div id="printDiv" style="display: none;height: 297mm;width: 210mm;">
        <p id="qualifiedNo" style="position: absolute;top: 117mm;left: 85mm;font-size: 5mm;font-family: 宋体;"></p>
        <p id="userName" style="position: absolute;top: 145mm;left: 36mm;font-size: 5mm;font-family: 楷体;"></p>
        <p id="y1" style="position: absolute;top: 145mm;left: 65mm;font-size: 5mm;font-family: 宋体;"></p>
        <p id="m1" style="position: absolute;top: 145mm;left: 89mm;font-size: 5mm;font-family: 宋体;"></p>
        <p id="y2" style="position: absolute;top: 145mm;left: 111mm;font-size: 5mm;font-family: 宋体;"></p>
        <p id="m2" style="position: absolute;top: 145mm;left: 136mm;font-size: 5mm;font-family: 宋体;"></p>
        <p id="orgName" style="position: absolute;top: 160mm;left: 31mm;font-size: 5mm;font-family: 楷体;"></p>
        <p id="speName" style="position: absolute;top: 160mm;left: 100mm;font-size: 5mm;font-family: 楷体;"></p>
        <p style="position: absolute;top: 234mm;left: 121mm;font-size: 5mm;font-family: 宋体;">2016&#12288;&#12288;12&#12288;&#12288;31</p>
    </div>
    </div>
</body>
</html>
